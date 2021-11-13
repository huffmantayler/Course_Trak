/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class Code2FAController implements Initializable {

    @FXML
    private Button verifyCode;
    @FXML
    private Button requestCode;
    @FXML
    private TextField code2FA;
    
    private String user;
    private String userName;
    private int userID;
    
    public AnchorPane code2FAPane;
    
    private static Alerts alertAuth;
    private static Auth auth = new Auth();
    private int faCode;
    private String phoneNumber  = "5407978441";
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        faCode  = auth.verificationCode();
        alertAuth = new Alerts(phoneNumber);
        try {
            alertAuth.loginCode(faCode);
        } catch (IOException ex) {
            Logger.getLogger(Code2FAController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    public TextField getCode2FA() {
        return code2FA;
    }

    public String getUser() {
        return user;
    }

    public String getUserName() {
        return userName;
    }

    public int getUserID() {
        return userID;
    }

    public void setUser(String userType) {
        this.user = userType;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
  
    
    @FXML
    public void checkVerify(ActionEvent event) throws IOException{

        if(Integer.valueOf(code2FA.getText()) == faCode){
            System.out.println("Match");
            System.out.println(faCode + " : " + code2FA.getText());
            alertAuth.accessGranted(phoneNumber);
            if(user.equals("Admin")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../Teacher/adminDashboard.fxml"));
                Parent root = (Parent) loader.load();
                AdminDashboardController ad = loader.getController();
                ad.setAdminUserName(userName);
                ad.setUserID(userID);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                Node source = (Node) event.getSource();
                Stage parentStage = (Stage) source.getScene().getWindow();
                parentStage.close();  
            }else if(user.equals("Student")){
                AnchorPane pane = FXMLLoader.load(getClass().getResource("../FXML/studentDashboard.fxml"));
                code2FAPane.getChildren().setAll(pane);
            }else{
               AnchorPane pane = FXMLLoader.load(getClass().getResource("../FXML/teacherDashboard.fxml"));
                code2FAPane.getChildren().setAll(pane); 
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("2FA Verification");
            alert.setHeaderText("Code entered was invalid");
            alert.setContentText("Please try again or request a new code");
            alert.showAndWait();
            System.out.println("No Match");
            System.out.println(faCode + " : " + code2FA.getText());
            alertAuth.accessFailed(phoneNumber);
        }
    }
    
    @FXML
    public void codeRequest(ActionEvent event) throws IOException{
        faCode  = auth.verificationCode();
        alertAuth = new Alerts(phoneNumber);
        try {
            alertAuth.loginCode(faCode);
        } catch (IOException ex) {
            Logger.getLogger(Code2FAController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}

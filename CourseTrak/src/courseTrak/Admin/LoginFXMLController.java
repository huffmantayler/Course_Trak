/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class LoginFXMLController implements Initializable {

    public AnchorPane loginpane;
    private Code2FAController cc;
    @FXML
    private Button forgotPasswordBtn;
    @FXML
    private Button signInBtn;
    @FXML
    private Button signUpBtn;
    @FXML
    private CheckBox adminCheck;
    @FXML
    private TextField Username;
    @FXML
    private TextField passTextField;
    @FXML
    private PasswordField Password;
    @FXML
    private CheckBox showPassword;
    @FXML
    private CheckBox studentCheck;
    @FXML
    private CheckBox teacherCheck;
    
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
   @FXML
    public void showPassword(ActionEvent event){
        if(this.showPassword.isSelected()){
            this.passTextField.setText(this.Password.getText());
            this.passTextField.setVisible(true);
            this.Password.setVisible(false);
        }else{
            this.Password.setText(this.passTextField.getText());
            this.Password.setVisible(true);
            this.passTextField.setVisible(false);
        }
    }
    
    @FXML
    public void signIn(ActionEvent event) throws IOException{
        DBConnection dbConnect = new DBConnection();

        boolean checkUser = false;
        if(adminCheck.isSelected()){
            checkUser = dbConnect.adminLogin(event, Username.getText(), Password.getText());
            if(checkUser){
                System.out.println(dbConnect.getUserID());
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Valid Credentials");
                alert.setHeaderText("2FA Code Verification");
                alert.setContentText("Click [OK] to recieve a 2FA code for verification");
                alert.showAndWait();
            if(alert.getResult().getText().equalsIgnoreCase("Ok")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Code2FA.fxml"));
                Parent root = (Parent) loader.load();
                Code2FAController cc = loader.getController();
                cc.setUser("Admin");
                cc.setUserName(Username.getText());
                cc.setUserID(dbConnect.getUserID());
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                Node source = (Node) event.getSource();
                Stage parentStage = (Stage) source.getScene().getWindow();
                parentStage.close(); 
            }else{
              AnchorPane pane = FXMLLoader.load(getClass().getResource("../FXML/LoginFXML.fxml"));
              loginpane.getChildren().setAll(pane); 
            }
           }else{
           
           } 
        }else if(studentCheck.isSelected()){
            checkUser = dbConnect.studentLogin(event, Username.getText(), Password.getText());
            if(checkUser){
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Valid Credentials");
            alert.setHeaderText("2FA Code Verification");
            alert.setContentText("Click [OK] to recieve a 2FA code for verification");
            alert.showAndWait();
            if(alert.getResult().getText().equalsIgnoreCase("Ok")){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Code2FA.fxml"));
                Parent root = (Parent) loader.load();
                Code2FAController cc = loader.getController();
                cc.setUser(Username.getText());
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
                Node source = (Node) event.getSource();
                Stage parentStage = (Stage) source.getScene().getWindow();
                parentStage.close();   
            }else{
              AnchorPane pane = FXMLLoader.load(getClass().getResource("../FXML/LoginFXML.fxml"));
              loginpane.getChildren().setAll(pane); 
            }
            }else {
            }  
        }else if(teacherCheck.isSelected()){
            checkUser = dbConnect.teacherLogin(event, Username.getText(), Password.getText());
            if(checkUser){   
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Valid Credentials");
            alert.setHeaderText("2FA Code Verification");
            alert.setContentText("Click [OK] to recieve a 2FA code for verification");
            alert.showAndWait();
                if(alert.getResult().getText().equalsIgnoreCase("Ok")){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Code2FA.fxml"));
                    Parent root = (Parent) loader.load();
                    Code2FAController cc = loader.getController();
                    cc.setUser(Username.getText());
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                    Node source = (Node) event.getSource();
                    Stage parentStage = (Stage) source.getScene().getWindow();
                    parentStage.close();   
                }else{
                    AnchorPane pane = FXMLLoader.load(getClass().getResource("../FXML/LoginFXML.fxml"));
                    loginpane.getChildren().setAll(pane); 
                }
            }else{
            
            }
        }else{
            System.out.println("Invalid");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Failed to log in");
            alert.setHeaderText("Invalid Email / Password");
            alert.setContentText("Please sign up or contact support");
            alert.showAndWait();
            Username.clear();
            Password.clear();
        } 
    }
    
    @FXML
    public void signUpWindow(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../FXML/SignUp.fxml"));
        loginpane.getChildren().setAll(pane);
    }
    
}

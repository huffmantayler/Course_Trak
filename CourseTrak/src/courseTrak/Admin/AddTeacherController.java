/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class AddTeacherController implements Initializable {

    @FXML
    private Button cancelBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private TextField lastName;
    @FXML
    private TextField firstName;
    @FXML
    private TextField Room;
    @FXML
    private TextField Class;
    
    private String defaultuserName;
    private String defaultPassword;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void exitWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void registerTeacher(ActionEvent event) {
        try{
            String firstName = this.firstName.getText();
            String lastName = this.lastName.getText();
            
            Random rand = new Random();
            String passCode = "";
            int code = rand.nextInt(999999) + 100000;
            passCode = String.format("%06d", code);
            
            defaultuserName = (firstName.substring(0, 1) + lastName).toLowerCase();
            defaultPassword = passCode;
            
            System.out.println(defaultPassword);
            
            
            PassHash passHash = new PassHash();
            defaultPassword = passHash.getPasswordHasher(defaultPassword);
            String room = this.Room.getText();
            String class1 = this.Class.getText();
            DBConnection dbConnect = new DBConnection();
            dbConnect.addTeacher(event, defaultuserName.toLowerCase(), defaultPassword, firstName, lastName, room, class1);
            
            //Alert admin of a succesful student registration
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Teacher Registration");
            alert.setHeaderText("New teacher successfully registered");
            alert.setContentText("First Name :  " + firstName.toUpperCase() + ""
                    + "\nLast Name:  " + lastName.toUpperCase() + ""
                            + "\nRoom Number:  " + room.toUpperCase() + ""
                                    + "\n-------------------------------------------------\n"
                                    + "User Name: " + defaultuserName + "\t\tPassword: " + passCode );
            alert.showAndWait();
            if(alert.getResult().getText().equalsIgnoreCase("Ok")){
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close(); 
            }
        }catch(NullPointerException e){
            System.out.println("Opps");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Teacher Registration");
            alert.setHeaderText("Please fill required fields");
            alert.setContentText("First name, last name and room number should all be filled");
            alert.showAndWait();
    }
   }

    
}
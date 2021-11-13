/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class StudentRegController implements Initializable {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private ChoiceBox gradeLevel;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button registerBtn;
    
    private String defaultuserName;
    private String defaultPassword;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] levels = new String[]{"Freshmen", "Sophmore", "Junior", "Senior"};
        gradeLevel.setStyle("-fx-font: 15px \"Helvetica Neue\";");
        gradeLevel.setItems(FXCollections.observableArrayList(levels));
    }  
    
    @FXML
    public void exitWindow(ActionEvent event) throws IOException{
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    public void registerStudent(ActionEvent event) {
        try{
            //generate random six digit to append student's last name
            Random rand = new Random();
            String passCode = "";
            int code = rand.nextInt(999999) + 100000;
            passCode = String.format("%06d", code);
            
            String firstName = this.firstName.getText();
            String lastName = this.lastName.getText();
            
            //default user and password
            defaultuserName = (firstName.substring(0, 1) + lastName).toLowerCase();
            defaultPassword = passCode;
            
            
            System.out.println(defaultPassword);
            
            //hash default password
            PassHash passHash = new PassHash();
            defaultPassword = passHash.getPasswordHasher(defaultPassword);
            String level = this.gradeLevel.getSelectionModel().getSelectedItem().toString();
            DBConnection dbConnect = new DBConnection();
            dbConnect.registerStudent(event, defaultuserName.toLowerCase(), defaultPassword, firstName, lastName, level);
            
            //Alert admin of a succesful student registration
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Student Registration");
            alert.setHeaderText("New student successfully registered");
            alert.setContentText("First Name :  " + firstName.toUpperCase() + ""
                    + "\nLast Name:  " + lastName.toUpperCase() + ""
                            + "\nGrade Level:  " + level.toUpperCase() + ""
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
            alert.setTitle("Student Registration");
            alert.setHeaderText("Please fill required fields");
            alert.setContentText("First name, last name and grade level should all be filled");
            alert.showAndWait();
        }
    }

    public String getFirstName() {
        return firstName.getText();
    }

    public String getLastName() {
        return lastName.getText();
    }

    public String getGradeLevel() {
        return gradeLevel.getSelectionModel().getSelectedItem().toString();
    }

    public String getDefaultuserName() {
        return defaultuserName;
    }

    public String getDefaultPassword() {
        return defaultPassword;
    }
    
    
    
}

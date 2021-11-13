/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class SignUpController implements Initializable {

    DBConnection dbConnect = new DBConnection();
    @FXML
    private AnchorPane signUpPane;
    @FXML
    private Button signUpBtn;
    @FXML
    private Button cancleBtn;
    @FXML
    private CheckBox adminCheck;
    @FXML
    private CheckBox studentCheck;
    @FXML
    private CheckBox parentCheck;
    @FXML
    private TextField username;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private PasswordField Password;
    @FXML
    private CheckBox showPassword;
    @FXML
    private TextField passTextField;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }  
    
     @FXML
    public void signUp(ActionEvent event) throws IOException{
        DBConnection dbConnect = new DBConnection();
        PassHash passHash = new PassHash();
        String hashedPassword = passHash.getPasswordHasher(this.Password.getText());
        System.out.println(hashedPassword);
        if(adminCheck.isSelected()){
            dbConnect.adminSignUp(event, this.username.getText(), hashedPassword, this.firstName.getText(), this.lastName.getText());  
        }else if(studentCheck.isSelected()){
            dbConnect.studentSignUp(event, this.username.getText(), hashedPassword, this.firstName.getText(), this.lastName.getText());   
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Account Creation");
        alert.setHeaderText("Account successfully created");
        alert.setContentText("Hello " + this.username.getText() + ", welcome to Course-Trak");
        alert.showAndWait();
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../FXML/LoginFXML.fxml"));
        signUpPane.getChildren().setAll(pane);
    }
    
    @FXML
    public void cancleSignUp(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../FXML/LoginFXML.fxml"));
        signUpPane.getChildren().setAll(pane);
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
    
}

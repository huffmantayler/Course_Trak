/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
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
 * @author markduah, caitlyn
 */
public class AddClassController implements Initializable {

    @FXML
    private Button cancelBtn;
    @FXML
    private Button registerBtn;
    @FXML
    private TextField className;
    @FXML
    private TextField classDescription;
    @FXML
    private ChoiceBox<String> classCredit;
    @FXML
    private ChoiceBox<String> classType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] credit = new String[]{"1", "3"};
        String[] type = new String[]{"English", "Math", "Science", "Social", "Other"};
        classCredit.setStyle("-fx-font: 15px \"Helvetica Neue\";");
        classCredit.setItems(FXCollections.observableArrayList(credit));
        classType.setStyle("-fx-font: 15px \"Helvetica Neue\";");
        classType.setItems(FXCollections.observableArrayList(type));
    }    

    @FXML
    private void exitWindow(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void registerClass(ActionEvent event){
        try{
            DBConnection dbConnect = new DBConnection();
            dbConnect.addClass(className.getText(), Integer.valueOf(classCredit.getValue()), classDescription.getText(), classType.getValue());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Classes");
            alert.setHeaderText("New Class Registeration");
            alert.setContentText(className.getText() + " has been added succesfully");
            alert.showAndWait();
            if(alert.getResult().getText().equalsIgnoreCase("Ok")){
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        }catch (NullPointerException e){
            System.out.println("Opps");
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Classes");
            alert.setHeaderText("New Class Registeration");
            alert.setContentText("Please make sure all fields are filled");
            alert.showAndWait();
        }    
    }  
}

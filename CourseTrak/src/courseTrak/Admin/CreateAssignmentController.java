/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class CreateAssignmentController implements Initializable {

    @FXML
    private ChoiceBox<?> classes;
    @FXML
    private ListView<?> attachments;
    @FXML
    private DatePicker startDate;
    @FXML
    private DatePicker dueDate;
    @FXML
    private TextArea assignmentsInstructions;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void exitWindow(ActionEvent event) throws IOException{
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void uploadAttachments(ActionEvent event) throws IOException{
    }

    @FXML
    private void createAssignment(ActionEvent event) throws IOException{
    }
}

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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class TeacherDashboardController implements Initializable {

    @FXML
    private Label schoolName;
    @FXML
    private Label adminUserName;
    @FXML
    private ImageView adminProfile;
    @FXML
    private BorderPane teachersPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Mark");
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/THome.fxml");
        teachersPane.setCenter(view);
    }    
    
    @FXML
    public void loadDashboard(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();        
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/THome.fxml");
        teachersPane.setCenter(view);
    }
    
    @FXML
    public void loadClasses(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/TClasses.fxml");
        teachersPane.setCenter(view);

    }
    
    @FXML
    public void loadAssignment(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/TAssignment.fxml");
        teachersPane.setCenter(view);

    }
    
    @FXML
    public void loadWeather(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/Weather.fxml");
        teachersPane.setCenter(view);

    }
    
    @FXML
    public void loadReport(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/Report.fxml");
        teachersPane.setCenter(view);

    }
    
    @FXML
    public void loadMessages(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/Message.fxml");
        teachersPane.setCenter(view);

    }
}

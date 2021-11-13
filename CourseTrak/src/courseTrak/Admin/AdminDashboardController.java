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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class AdminDashboardController implements Initializable {

    
    public AnchorPane dashboardPane;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Label schoolName;
    @FXML
    private Label currentYear;
    @FXML
    private Label adminUserName;
    @FXML
    private ImageView adminProfile;
    
    private int userID;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(adminUserName.getText());
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/Home.fxml");
        mainPane.setCenter(view);
    }    

    public AdminDashboardController(){}
    
    @FXML
    public void loadDashboard(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/Home.fxml");
        mainPane.setCenter(view);
    }
    
    @FXML
    public void loadWeather(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/Weather.fxml");
        mainPane.setCenter(view);

    }
    
    @FXML
    public void loadReport(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/Report.fxml");
        mainPane.setCenter(view);

    }
    
    @FXML
    public void loadMessages(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/Message.fxml");
        mainPane.setCenter(view);

    }

    @FXML
    public void loadStudent(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/Student.fxml");
        mainPane.setCenter(view);

    }
    
    @FXML
    public void loadTeacher(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/Teachers.fxml");
        mainPane.setCenter(view);

    }
    
    @FXML
    public void loadClasses(ActionEvent event) throws IOException {
        FxmlLoader object = new FxmlLoader();
        System.out.println("you clicked me");
        Pane view = object.getPage("../FXML/Classes.fxml");
        mainPane.setCenter(view);

    }

    public void setSchoolName(Label schoolName) {
        this.schoolName = schoolName;
    }

    public void setCurrentYear(Label currentYear) {
        this.currentYear = currentYear;
    }

    public void setAdminUserName(String userName) {
        this.adminUserName.setText(userName);
    }

    public void setAdminProfile(ImageView adminProfile) {
        this.adminProfile = adminProfile;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseTrak.Admin;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author markduah
 */
public class THomeController implements Initializable {

    @FXML
    private AnchorPane homePane;
    @FXML
    private Pane adminTab1;
    @FXML
    private Label numOfStudents;
    @FXML
    private Group datesGroup;
    @FXML
    private Pane classesTab;
    @FXML
    private Label numOfClasses;
    @FXML
    private Pane info;
    @FXML
    private Circle makeSchedule;
    @FXML
    private Pane accountMan;
    @FXML
    private Pane reportMan;
    @FXML
    private Pane adminTab11;
    @FXML
    private Label numOfStudents1;
    @FXML
    private Pane adminTab1111;
    @FXML
    private Label numOfStudents11;
    @FXML
    private Label welcome;
    
    UserSession us = UserSession.getInstance();

    private Code2FAController cf2a;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setWelcome();
        setClasses();
        setStudents();
    }

    @FXML
    public void setWelcome() {

        welcome.setText("Welcome back, " + us.getFirstName());
    }

    @FXML
    private void loadStudent(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/TStudent.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        try {
            homePane.getChildren().clear();
            homePane.getChildren().add(registerPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadClasses(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/TClasses.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        try {
            homePane.getChildren().clear();
            homePane.getChildren().add(registerPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadAssignment(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/TAssignment.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        try {
            homePane.getChildren().clear();
            homePane.getChildren().add(registerPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadReport(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/TReport.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        try {
            homePane.getChildren().clear();
            homePane.getChildren().add(registerPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadGradeBook(MouseEvent event) throws IOException {
        System.out.println("you clicked me");
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/AssignGrade.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Grade Book");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void setClasses() {
        try {
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM Classes c INNER JOIN TeacherClasses tc WHERE c.ClassNum = tc.ClassNum AND TeacherID = ? AND Semester = ? AND Year = (SELECT year(curdate()))");
            ps.setInt(1, us.getUserID());
            ps.setString(2, semester());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int totalClasses = rs.getInt("COUNT(*)");
            numOfClasses.setText(String.valueOf(totalClasses));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void setStudents(){
        try{
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT COUNT(*) FROM ClassesStudents WHERE TeacherID = ? AND Semester = ? AND Year = year(curdate())");
            ps.setInt(1, us.getUserID());
            ps.setString(2, semester());
            ResultSet rs = ps.executeQuery();
            rs.next();
            int totalStudents = rs.getInt("COUNT(*)");
            numOfStudents.setText(String.valueOf(totalStudents));
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public String semester(){
        String semester = "";
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        System.out.println(month);
        if(month < 6){
            semester = "Spring";
        } else if(month >= 8){
            semester = "Fall";
        }
        System.out.println(semester);
        return semester;
    }

}

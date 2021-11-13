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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import java.sql.*;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class HomeController implements Initializable {

    @FXML
    private AnchorPane homePane;
    @FXML
    private Pane teachersTab;
    @FXML
    private Pane adminTab1;
    @FXML
    private Pane adminTab11;
    @FXML
    private Group datesGroup;
    @FXML
    private Pane adminTab111;
    @FXML
    private Pane info;
    @FXML
    private Label numOfTeachers;
    @FXML
    private Label numOfStudents;
    @FXML
    private Label numOfParents;
    @FXML
    private Label numOfClasses;
    @FXML
    private Circle makeSchedule;
    @FXML
    private Label overallGpa;
    @FXML
    private Label previousGpa;
    @FXML
    private Pane accountMan;
    @FXML
    private Pane reportMan;

    AdminDashboardController ac = new AdminDashboardController();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setTeachers();
        setStudents();
        setParents();
        setClasses();
        setOverallGPA();
    }

    @FXML
    private void loadStudent(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/Student.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        try {
            homePane.getChildren().clear();
            homePane.getChildren().add(registerPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void loadTecher(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../FXML/Teachers.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Classes.fxml"));
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Report.fxml"));
        Pane registerPane = (Pane) fxmlLoader.load();
        try {
            homePane.getChildren().clear();
            homePane.getChildren().add(registerPane);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setTeachers() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) FROM Teachers");
            rs.next();
            int total = rs.getInt("COUNT(*)");
            System.out.println(total);
            numOfTeachers.setText(String.valueOf(total));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setStudents() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) FROM Students");
            rs.next();
            int totalStu = rs.getInt("COUNT(*)");
            System.out.println(totalStu);
            numOfStudents.setText(String.valueOf(totalStu));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setParents() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) FROM Parent");
            rs.next();
            int totalParents = rs.getInt("COUNT(*)");
            numOfParents.setText(String.valueOf(totalParents));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setClasses() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT COUNT(*) FROM Classes");
            rs.next();
            int totalClasses = rs.getInt("COUNT(*)");
            numOfClasses.setText(String.valueOf(totalClasses));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void setOverallGPA() {
        try {
            Connection conn = DBConnection.getConnection();
            ResultSet rs = conn.createStatement().executeQuery("SELECT ROUND(SUM(CumulativeGPA) / COUNT(*), 2) AS GPA FROM Students WHERE CumulativeGPA IS NOT NULL");
            rs.next();
            double overallGPA = rs.getInt("GPA");
            overallGpa.setText(String.valueOf(overallGPA));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
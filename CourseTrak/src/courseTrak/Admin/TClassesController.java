/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.sql.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.MenuItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author taylor
 */
public class TClassesController implements Initializable {

    @FXML
    private TableView<Student> classTable;
    @FXML
    private TableColumn<Student, String> studentID;
    @FXML
    private TableColumn<Student, String> stulastName;
    @FXML
    private TableColumn<Student, String> stufirstName;
    @FXML
    private TableColumn<Student, String> stucurrentGrade;
    @FXML
    private ChoiceBox<String> classes;
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label gradeNumber;
    @FXML 
    private Label classNameLabel;
    @FXML
    private Label numOfStudents;
    @FXML
    private Label absences;
    @FXML
    private Label present;
    @FXML
    private Label counselor;
    
    private int userID;
    
    UserSession us = UserSession.getInstance();
    
    private ContextMenu contextMenu = new ContextMenu();
    private Label addGradeLbl = new Label("Assign Grade");
    private MenuItem addGrade = new CustomMenuItem(addGradeLbl);
    private final ObservableList<Student> obList = FXCollections.observableArrayList();

    private int id;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadChoiceBoxData();
        setStudentTextFields();
        contextMenu.getItems().addAll(addGrade);
        classTable.setContextMenu(contextMenu);
    }

    
    //load choicebox options based on which teacher is logged into the system
    @FXML
    public void loadChoiceBoxData() {
        
        try {
            System.out.println("loading choice box data");
            System.out.println(us.getUserID());
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT Name FROM Classes c INNER JOIN TeacherClasses tc WHERE c.ClassNum = tc.ClassNum AND TeacherID = ? AND Semester = ? AND Year = (SELECT year(curdate()))");
            ps.setInt(1, us.getUserID());
            ps.setString(2, semester());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String className = rs.getString("Name");
                System.out.println(className);
                classes.getItems().add(className);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TClassesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    void refreshTable() {
        studentID.setText("");
        stufirstName.setText("");
        stulastName.setText("");
    }

    //loading table data from database based on class chosen from dropdown menu
    @FXML
    void loadTableData() {
        classTable.getItems().clear();
        // System.out.println(classes.getSelectionModel().getSelectedItem());
        String classChoice = classes.getSelectionModel().getSelectedItem();
        classNameLabel.setText(classChoice);
        System.out.println(classChoice);

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT tb1.StudentID, lastName, firstName, GradeLevel, grade, Counselor, Absences, COUNT(Absent) AS Present\n"
                    + "FROM\n"
                    + "(SELECT s.StudentID, lastName, firstName, GradeLevel, grade, Counselor, COUNT(Absent) AS Absences FROM Students s \n"
                    + "INNER JOIN ClassesStudents cs ON s.StudentID = cs.StudentID\n"
                    + "INNER JOIN Classes c ON c.ClassNum = cs.ClassNum\n"
                    + "INNER JOIN Attendance a ON a.StudentID = s.StudentID\n"
                    + "WHERE Name = ? AND Absent = 1 AND Semester = ?\n"
                    + "GROUP BY StudentID) tb1\n"
                    + "INNER JOIN Attendance a ON a.StudentID = tb1.StudentID\n"
                    + "WHERE Absent = 0\n"
                    + "GROUP BY StudentID");
            ps.setString(1, classChoice);
            ps.setString(2, semester());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obList.addAll(new Student(rs.getInt("StudentID"),
                        rs.getString("lastName"),
                        rs.getString("firstName"),
                        rs.getString("GradeLevel"),
                        rs.getString("grade"),
                        rs.getInt("Absences"),
                        rs.getInt("Present"),
                        rs.getString("Counselor")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        studentID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        stulastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        stufirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        stucurrentGrade.setCellValueFactory(new PropertyValueFactory<>("grade"));

        classTable.setItems(obList);
        numOfStudents.setText(String.valueOf(classTable.getItems().size()));

    }
    //Loading info into bottom right pane with student data from table on click
    public void setStudentTextFields() {
        classTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                Student st = classTable.getItems().get(classTable.getSelectionModel().getSelectedIndex());
                id = st.getID();
                lastName.setText(st.getLastName());
                firstName.setText(st.getFirstName());
                absences.setText(String.valueOf(st.getAbsences()));
                present.setText(String.valueOf(st.getPresent()));
                counselor.setText(String.valueOf(st.getCounselor()));
                System.out.println(st.getGradeLevel());
                switch (st.getGradeLevel()) {
                    case "Freshmen":
                        gradeNumber.setText("9th");
                        break;
                    case "Sophmore":
                        gradeNumber.setText("10th");
                        break;
                    case "Junior":
                        gradeNumber.setText("11th");
                        break;
                    case "Senior":
                        gradeNumber.setText("12th");
                        break;
                    default:
                        gradeNumber.setText("");
                        break;
                }

            }
        });
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
    
    public String semester(){
        String semester = "";
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        if(month < 6){
            semester = "Spring";
        } else if(month >= 8){
            semester = "Fall";
        }
                
        return semester;
    }

}
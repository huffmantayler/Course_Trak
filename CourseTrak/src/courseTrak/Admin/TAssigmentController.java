/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class TAssigmentController implements Initializable {

    @FXML
    private ChoiceBox<String> Classes;
    @FXML
    private Label Class;
    @FXML
    private TableView<Assignment> assignmentTable;
    @FXML
    private TableColumn<Assignment, String> assignmentID;
    @FXML
    private TableColumn<Assignment, String> description;
    @FXML
    private TableColumn<Assignment, String> startDate;
    @FXML
    private TableColumn<Assignment, String> dueDate;
    
    private final ObservableList<Assignment> obList = FXCollections.observableArrayList();
    UserSession us = UserSession.getInstance();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadChoiceBoxData();
    }  
    
    @FXML
    void loadTableData() {
        assignmentTable.getItems().clear();
        // System.out.println(classes.getSelectionModel().getSelectedItem());
        String classChoice = Classes.getSelectionModel().getSelectedItem();
        Class.setText(classChoice);
        System.out.println(classChoice);

        try {
            Connection con = DBConnection.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT AssignmentID, a.Description , StartDate, DueDate FROM Assignments a INNER JOIN Classes c\n" +
                    "ON a.ClassNum = c.ClassNum \n" +
                    "WHERE Name = ?;");
            ps.setString(1, classChoice);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                obList.addAll(new Assignment(rs.getInt("AssignmentID"),
                        rs.getString("Description"),
                        rs.getString("StartDate"),
                        rs.getString("DueDate")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        assignmentID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        startDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        dueDate.setCellValueFactory(new PropertyValueFactory<>("DueDate"));

        assignmentTable.setItems(obList);

    }
    
    
    @FXML
    public void createAssignment(ActionEvent event) throws IOException{
        System.out.println("you clicked me");
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/CreateAssignment.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Assignments");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    @FXML
    public void deleteAssignment(ActionEvent event) throws SQLException{
        Connection con = DBConnection.getConnection();
        Assignment as = assignmentTable.getItems().get(assignmentTable.getSelectionModel().getSelectedIndex());
        PreparedStatement pst = con.prepareStatement("DELETE FROM Assignments WHERE AssignmentID = ?");
        pst.setInt(1, as.getID());
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Student Account Deletion");
        alert.setHeaderText("Student deleted");
        alert.setContentText("Assigment will the ID: " + as.getID() +  " will be permenantly deleted");
        alert.showAndWait();
        if(alert.getResult() != null){
          pst.execute();
          refreshTable();
        }else{
            
        }
    }
    
    @FXML
    public void refreshTable(){
        obList.clear();
        loadTableData();
    }
    
    public void loadChoiceBoxData() {

        try {
            System.out.println("loading choice box data");
            Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT Name FROM Classes c INNER JOIN TeacherClasses tc WHERE c.ClassNum = tc.ClassNum AND TeacherID = ? AND Semester = ? AND Year = (SELECT year(curdate()))");
            ps.setInt(1, us.getUserID());
            ps.setString(2, semester());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String className = rs.getString("Name");
                System.out.println(className);
                Classes.getItems().add(className);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TClassesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
    @FXML
    public void editAssignment(ActionEvent event) throws IOException{

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

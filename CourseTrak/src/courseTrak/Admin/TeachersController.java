/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author taylorhuffman
 */

public class TeachersController implements Initializable {

    @FXML
    private TableView<Teacher> teacherTableView;
    @FXML
    private TableColumn<Teacher, String> lastName;
    @FXML
    private TableColumn<Teacher, String> firstName;
    @FXML
    private TableColumn<Teacher, String> Subject;
    @FXML
    private TableColumn<Teacher, String> Room;
    @FXML
    private TextField teaSearchBox;
    @FXML
    private Button addNewTeacher;
    @FXML
    private Button updateTeacher;
    @FXML
    private Button deleteTeacher;
    @FXML
    private ImageView teacherPic;

    private final ObservableList<Teacher> obList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadTableData();
        searchUser();
    }    
    
     public void loadTableData(){
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Teachers");

            while(rs.next()){
                //System.out.println(rs.getString("lastName"));
                obList.addAll(new Teacher(
                rs.getString("firstName"),
                rs.getString("lastName"),
                rs.getString("roomNumber"),
                rs.getString("Subject"),
                rs.getString("phoneNumber")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(TeachersController.class.getName()).log(Level.SEVERE, null, ex);
        }

        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        Subject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        Room.setCellValueFactory(new PropertyValueFactory("roomNumber"));

        teacherTableView.setItems(obList);
    }
     
    public void searchUser(){
        FilteredList<Teacher> filteredData = new FilteredList<>(obList, b -> true);
            teaSearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(teacher -> {
                    if(newValue == null || newValue.isEmpty()){
                      return true;
                    }
                    
                    String lowerCasefilter = newValue.toLowerCase();
                    if(teacher.getFirstName().toLowerCase().indexOf(lowerCasefilter) != -1){
                        return true;
                    }else if(teacher.getLastName().toLowerCase().indexOf(lowerCasefilter) != -1){
                        return true;
                    }
                    else if (String.valueOf(teacher.getRoomNumber()).indexOf(lowerCasefilter) != -1)
                        return true;
                        else
                        return false;
                    });
                 });
            SortedList<Teacher> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(teacherTableView.comparatorProperty());
            teacherTableView.setItems(sortedData);
    } 

    @FXML
    public void refreshTable(){
        lastName.setText("");
        firstName.setText("");
        Room.setText("");
        Subject.setText("");
        obList.clear();
        teaSearchBox.clear();
        loadTableData();
        searchUser();
    }
    
    @FXML
    private void addTeacher(ActionEvent event) throws IOException {
        System.out.println("you clicked me");
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/AddTeacher.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setTitle("Teacher Resgistration");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    @FXML
    private void deleteTeacher(ActionEvent event) throws SQLException {
        System.out.println("I work");
        Connection con = DBConnection.getConnection();
        System.out.println("successful Connection");
        Teacher tea = teacherTableView.getItems().get(teacherTableView.getSelectionModel().getSelectedIndex());
        PreparedStatement pst = con.prepareStatement("DELETE FROM Teachers WHERE lastName = ?");
        pst.setString(1, tea.getLastName());
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Teacher Account Deletion");
        alert.setHeaderText("Teacher deleted");
        alert.setContentText(tea.getLastName() +  " will be permenantly deleted");
        alert.showAndWait();
        if(alert.getResult() != null){
          pst.execute();
          System.out.println(tea.getFirstName());
          refreshTable();
        }else{
            
        }
    }
}

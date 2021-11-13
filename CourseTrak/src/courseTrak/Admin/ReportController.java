/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.Initializable;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class ReportController implements Initializable {

    
    @FXML
    private Label teachersReport;
    @FXML
    private Label studentsReport;
    @FXML
    private Pane parentsReport;
    @FXML
    private TableView<Report> reportTableView;

    @FXML
    private TableColumn<Report, String> reportCol;
    @FXML
    private TableColumn<Report, String> dateCol;
    @FXML
    private TableColumn<Report, String> id;
    @FXML
    private Button editBtn;
    @FXML
    private Button deleteBtn;
    
    private final ObservableList<Report> obList = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	loadTableData();
    }    

    @FXML
    private void uploadDoc(ActionEvent event) throws IOException {
        System.out.println("you clicked me");
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/Document.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("Class Resgistration");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        clearTable();
        loadTableData();
       
    }

    
    public void loadTableData(){
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT DocumentID, Description, Date FROM Documents");
            //System.out.println(rs.getInt("StudentID"));
             
            while(rs.next()){
                obList.addAll(new Report(rs.getInt("DocumentID"),
                rs.getString("Description"),
                rs.getString("Date")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));
        reportCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("Date"));
        
        
        reportTableView.setItems(obList);
    }
    
    @FXML
    public void clearTable(){
        obList.clear();
        reportTableView.setItems(obList);
        
    }
    
    @FXML
    public void deleteDoc(ActionEvent event) throws SQLException{
    	System.out.println("1");
        Connection con = DBConnection.getConnection();
        Report report = reportTableView.getItems().get(reportTableView.getSelectionModel().getSelectedIndex());
        PreparedStatement pst = con.prepareStatement("DELETE FROM Documents WHERE DocumentID = ?");
        pst.setString(1, Integer.toString(report.getID()));
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Document Deletion");
        alert.setHeaderText("Document deleted");
        alert.setContentText(report.getDescription() +  " will be permanently deleted");
        alert.showAndWait();
        if(alert.getResult() != null){
          pst.execute();
          clearTable();
          loadTableData();
        }else{
            
        }
    }
    
}
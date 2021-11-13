/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



/**
 * FXML Controller class
 *
 * @author caitlyn
 */
public class ClassesController implements Initializable {
    private final ObservableList<String> obList = FXCollections.observableArrayList();
    @FXML
    private ListView<String> scienceList;
    @FXML
    private ListView<String> mathList;
    @FXML
    private ListView<String> socialList;
    @FXML
    private ListView<String> englishList;
    @FXML
    private ListView<String> otherList;
    @FXML
    private Pane addClass;
	
        
	/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	loadTableData();
    }    
    
    
    public void loadTableData(){
        try {
        	//connect to database, and create a new result set of the classes table
            Connection connect = DBConnection.getConnection();
            ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM Classes");

            while(rs.next()){
                //adds each class to their own observable list by subject
            	if(rs.getString("Type").equals("English")) {
                    ClassObj eng = new ClassObj(rs.getString("Name"),rs.getInt("ClassNum"), rs.getInt("Credit"), rs.getString("Description"), rs.getString("Type"));
                    System.out.println(eng.getClassName()); 
                    englishList.getItems().addAll(eng.getClassName());
            	}
            	else if(rs.getString("Type").equals("Science")) {
                    ClassObj sci = new ClassObj(rs.getString("Name"),rs.getInt("ClassNum"), rs.getInt("Credit"), rs.getString("Description"), rs.getString("Type"));
                    scienceList.getItems().addAll(sci.getClassName());
           	}
           	else if(rs.getString("Type").equals("Math")) {
                    ClassObj mat = new ClassObj(rs.getString("Name"),rs.getInt("ClassNum"), rs.getInt("Credit"), rs.getString("Description"), rs.getString("Type"));
                    mathList.getItems().addAll(mat.getClassName());
            	}
            	else if(rs.getString("Type").equals("Social")) {
                    ClassObj soc = new ClassObj(rs.getString("Name"),rs.getInt("ClassNum"), rs.getInt("Credit"), rs.getString("Description"), rs.getString("Type"));
                    socialList.getItems().addAll(soc.getClassName());
            	}
            	else {
                    ClassObj oth = new ClassObj(rs.getString("Name"),rs.getInt("ClassNum"), rs.getInt("Credit"), rs.getString("Description"), rs.getString("Type"));
                    otherList.getItems().addAll(oth.getClassName());
           	}
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Add class info from database into gui here
        //put eng in english table, sci in science, etc.
    }
    
    //clears the table and all of the observable lists
    public void refreshTable(){
        englishList.getItems().clear();
        scienceList.getItems().clear();
        mathList.getItems().clear();
        socialList.getItems().clear();
        otherList.getItems().clear();
        loadTableData();
    }
    
    
    //adds a new class into the database
    public void addClass(MouseEvent event) throws IOException {
        System.out.println("you clicked me");
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/AddClass.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setTitle("Class Resgistration");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
        refreshTable();
    }
}
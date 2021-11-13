/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class RegistrationController implements Initializable {

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField gradeLevel;
    @FXML
    private TextField counselor;
    @FXML
    private TextField address;
    @FXML
    private TextField emgC;
    @FXML
    private TextField emgCNumber;
    @FXML
    private TextField zipCode;
    @FXML
    private ChoiceBox state;
    @FXML
    private ChoiceBox sex;
    @FXML
    private ChoiceBox race;
    @FXML
    private DatePicker dob;
    @FXML
    private Button cancelBtn;
///////////////////////////////////////////////////
    @FXML
    private TextField id;
    @FXML
    private TextField City;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String[] s = new String[]{"Male", "Female"};
        String[] r = new String[]{"White", 
            "Black or African American",
            "Asian",
            "American Indian or Alaska Native",
            "Native Hawaiian or Pacific Islander"};
        String[] st = new String[]{"Alabama - AL", "Alaska - AK", "Arizona - AZ", "Arkansas - AR", "California - CA", 
            "Colorado - CO", "Connecticut - CT", "Delaware - DE", "Florida - FL", "Georgia - GA ", "Hawaii - HI", "Idaho - ID", 
            "Illinois - IL", "Indiana - IN", "Iowa - IA", "Kansas - KS", "Kentucky - KY", "Louisiana - LA", "Maine - ME", "Maryland - MD", 
            "Massachusetts - MA", "Michigan - MI", "Minnesota - MN", "Mississippi - MS", "Missouri - MO",
            "Montana - MT", "Nebraska - NE", "Nevada - NV", "New Hampshire - NH", "New Jersey - NJ", "New Mexico - NM", "New York - NY", 
            "North Carolina - NC", "North Dakota - ND", "Ohio - OH", "Oklahoma - OK", "Oregon - OR", "Pennsylvania - PA", 
            "Rhode Island - RI", "South Carolina - SC", "South Dakota - SD", "Tennessee - TN", "Texas - TX",
            "Utah - UT", "Vermont - VT", "Virginia - VA ", "Washington - WA", "West Virginia - WV", "Wisconsin - WI", "Wyoming - WY"};
        sex.setStyle("-fx-font: 15px \"Helvetica Neue\";");
        sex.setItems(FXCollections.observableArrayList(s));
        race.setStyle("-fx-font: 15px \"Helvetica Neue\";");
        race.setItems(FXCollections.observableArrayList(r));
        state.setStyle("-fx-font: 15px \"Helvetica Neue\";");
        state.setItems(FXCollections.observableArrayList(st));
        String len = "Native Hawaiian or Pacific Islander";
        System.out.println(len.length());
    }    
    
    /**
     *
     * @param event
     */
    @FXML
    public void cancelBtn(ActionEvent event){
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();  
    }

    public void setId(String id) {
        this.id.setText(id);
    }

    
    public void setFirstName(String firstName) {
        this.firstName.setText(firstName);
    }

    public void setLastName(String lastName) {
        this.lastName.setText(lastName);
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel.setText(gradeLevel);
    }

    public void setCounselor(String counselor) {
        this.counselor.setText(counselor);
    }

    public void setAddress(String address) {
        this.address.setText(address);
    }

    public void setEmgC(String emgC) {
        this.emgC.setText(emgC);
    }

    public void setEmgCNumber(String emgCNumber) {
        this.emgCNumber.setText(emgCNumber);
    }

    public void setZipCode(String zipCode) {
        this.zipCode.setText(zipCode);
    }

    public void setState(String state) {
        this.state.setValue(state);
    }

    public void setCity(String City) {
        this.City.setText(City);
    }

    public void setSex(String sex) {
        this.sex.setValue(sex);
    }

    public void setRace(String race) {
        this.race.setValue(race);
    }

    public void setDob(String dob) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        if(dob == null){
            this.dob.setValue(null);
        }else{
            LocalDate localDate = LocalDate.parse(dob, formatter);
            this.dob.setValue(localDate);   
        }
    }
    
    @FXML
    public void updateData(ActionEvent event) throws SQLException{
        String sexL = sex.getValue().toString().substring(0, 1);
        int ID = Integer.valueOf(id.getText());
        String dobString = dob.getValue().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        DBConnection dbConnect = new DBConnection();
        dbConnect.updateStudentData(ID, firstName.getText(), lastName.getText(), gradeLevel.getText(), counselor.getText(), dobString, sexL, emgC.getText(), emgCNumber.getText(), address.getText(), City.getText(),state.getValue().toString(), zipCode.getText());
        cancelBtn(event);
    }
       
}

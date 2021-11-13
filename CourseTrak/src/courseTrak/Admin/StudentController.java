/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.sql.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author markduah
 */
public class StudentController implements Initializable {

    @FXML
    private ImageView studPic;
    @FXML
    private Label lastName;
    @FXML
    private Label firstName;
    @FXML
    private Label gradeLevel;
    @FXML
    private Label age;
    @FXML
    private Label dob;
    @FXML
    private Label sex;
    @FXML
    private Label crs1;
    @FXML
    private Label crs2;
    @FXML
    private Label crs3;
    @FXML
    private Label crs4;
    @FXML
    private Label crs5;
    @FXML
    private Label crs6;
    @FXML
    private Label emg1;
    @FXML
    private Label emg2;
    @FXML
    private Label emg1C;
    @FXML
    private Label emg2C;
    @FXML
    private TextField stuSearchBox;
    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private Button addStu;
    @FXML
    private Button editStu;
    @FXML
    private Button deleteStu;
    @FXML
    private TableColumn<Student, String> stu_lastName;
    @FXML
    private TableColumn<Student, String> stu_firstName;
    @FXML
    private TableColumn<Student, String> stu_year;
    @FXML
    private TableColumn<Student, String> stu_gpa;
    @FXML
    private TableColumn<Student, String> stuID;
    
    private final ObservableList<Student> obList = FXCollections.observableArrayList();
    @FXML
    private Button refreshTable;
    @FXML
    private Label counselorName;
    
    private int id;
    private String Street;
    private String City;
    private String State;
    private String ZipCode;
    //update
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //System.out.println(Race.getText());
        loadTableData();
        searchUser();
        setStudentTextFields();
        //System.out.println(Race.getText());
    }   
    
    
    @FXML
    public void loadStudentReg(ActionEvent event) throws IOException{
        System.out.println("you clicked me");
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/StudentReg.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setTitle("Student Resgistration");
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    @FXML
    public void loadRegistration(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/Registration.fxml"));
        Parent root = (Parent) loader.load();
        RegistrationController register = loader.getController();
        register.setId(String.valueOf(id));
        register.setLastName(lastName.getText());
        register.setFirstName(firstName.getText());
        register.setGradeLevel(gradeLevel.getText());
        register.setCounselor(counselorName.getText());
        register.setAddress(Street);
        register.setCity(City);
        register.setState(State);
        register.setZipCode(ZipCode);
        register.setDob(dob.getText());
        if(sex.getText().equals("F")){
            register.setSex("Female");
        }else if(sex.getText().equals("F")){
           register.setSex("Male"); 
        }else{
            register.setSex(" "); 
        }
        register.setEmgC(emg1.getText());
        register.setEmgCNumber(emg1C.getText());
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        stage.setScene(new Scene(root));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
    
    public void loadTableData(){
        try {
            Connection con = DBConnection.getConnection();
            ResultSet rs = con.createStatement().executeQuery("SELECT * FROM Students");
             
            while(rs.next()){
                obList.addAll(new Student(rs.getInt("StudentID"),
                rs.getString("lastName"),
                rs.getString("firstName"),
                rs.getString("DOB"),
                rs.getString("SEX"),
                rs.getString("GradeLevel"),
                rs.getString("Counselor"),
                rs.getDouble("CumulativeGPA"),
                rs.getString("EMCName"),
                rs.getString("EMCContact"),
                rs.getString("Street"),
                rs.getString("City"),
                rs.getString("State"),
                rs.getString("Zip")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        stuID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        stu_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        stu_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        stu_year.setCellValueFactory(new PropertyValueFactory<>("GradeLevel"));
        stu_gpa.setCellValueFactory(new PropertyValueFactory<>("CumulativeGPA"));
        
        studentTableView.setItems(obList);
    }
    
    public void searchUser(){
        FilteredList<Student> filteredData = new FilteredList<>(obList, b -> true);
            stuSearchBox.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate(student -> {
                    if(newValue == null || newValue.isEmpty()){
                      return true;
                    }
                    
                    String lowerCasefilter = newValue.toLowerCase();
                    if(student.getFirstName().toLowerCase().indexOf(lowerCasefilter) != -1){
                        return true;
                    }else if(student.getLastName().toLowerCase().indexOf(lowerCasefilter) != -1){
                        return true;
                    }
                    else if (String.valueOf(student.getCumulativeGPA()).indexOf(lowerCasefilter) != -1)
                        return true;
                        else
                        return false;
                    });
                 });
            SortedList<Student> sortedData = new SortedList<>(filteredData);
            sortedData.comparatorProperty().bind(studentTableView.comparatorProperty());
            studentTableView.setItems(sortedData);
    } 
    
    @FXML
    public void refreshTable(){
        lastName.setText("");
        firstName.setText("");
        gradeLevel.setText("");
        age.setText("");
        dob.setText("");
        emg1.setText("");
        emg1C.setText("");
        sex.setText("");
        obList.clear();
        stuSearchBox.clear();
        loadTableData();
        searchUser();
    }
    
    @FXML
    public void deleteData() throws SQLException{
        Connection con = DBConnection.getConnection();
        Student st = studentTableView.getItems().get(studentTableView.getSelectionModel().getSelectedIndex());
        PreparedStatement pst = con.prepareStatement("DELETE FROM Students WHERE lastName = ?");
        pst.setString(1, st.getLastName());
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Student Account Deletion");
        alert.setHeaderText("Student deleted");
        alert.setContentText(st.getLastName() +  " will be permenantly deleted");
        alert.showAndWait();
        if(alert.getResult() != null){
          pst.execute();
          refreshTable();
        }else{
            
        }
    }
    
    public void setStudentTextFields(){
        studentTableView.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent t) {
                Student st = studentTableView.getItems().get(studentTableView.getSelectionModel().getSelectedIndex());
                id = st.getID();
                lastName.setText(st.getLastName());
                firstName.setText(st.getFirstName());
                String DOB = st.getDOB();
                if(DOB == ""){
                    System.out.println("DOB is NULL");
                }else{
                    dob.setText(DOB);
                }
                try {
                    String Age = st.getAge();
                    if(Age == ""){
                        System.out.println("Age is NULL");
                        age.setText("");
                    }else{
                        age.setText(Age);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
                }
                sex.setText(st.getSex());
                gradeLevel.setText(st.getGradeLevel());
                emg1.setText(st.getEMCName());
                emg1C.setText(st.getEMCContatc());
                counselorName.setText(st.getCounselor());
                Street = st.getStreets();
                City = st.getCity();
                State = st.getState();
                ZipCode = st.getZipCode();
                loadStudentClasses();
                
            }
            
        });
    }
    
    public void loadStudentClasses(){
        try{
            DBConnection dbConnect = new DBConnection();
            String[] classes = dbConnect.getClasses(id);
            switch(classes.length-1){
                case 6:
                    crs1.setText(classes[0]);
                    crs2.setText(classes[1]);
                    crs3.setText(classes[2]);
                    crs4.setText(classes[3]);
                    crs5.setText(classes[4]);
                    crs6.setText(classes[5]);
                    break;
                case 5:
                    crs1.setText(classes[0]);
                    crs2.setText(classes[1]);
                    crs3.setText(classes[2]);
                    crs4.setText(classes[3]);
                    crs5.setText(classes[4]);
                    break;
                case 4:
                    crs1.setText(classes[0]);
                    crs2.setText(classes[1]);
                    crs3.setText(classes[2]);
                    crs4.setText(classes[3]);
                    break;
                case 3:
                    crs1.setText(classes[0]);
                    crs2.setText(classes[1]);
                    crs3.setText(classes[2]);
                    break;
                case 2:
                    crs1.setText(classes[0]);
                    crs2.setText(classes[1]);
                    break;
                case 1:
                    crs1.setText(classes[0]);
                    break;
                default:
                    crs1.setText("");
                    crs2.setText("");
                    crs3.setText("");
                    crs4.setText("");
                    crs5.setText("");
                    crs6.setText("");
            }
        }catch (ArrayIndexOutOfBoundsException ioe){
            System.out.println("Less classes");
        }
    }
    
    public String getLastName(){
        return this.lastName.getText();
    }
    
}

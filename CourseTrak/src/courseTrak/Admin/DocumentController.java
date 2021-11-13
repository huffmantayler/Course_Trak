/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package courseTrak.Admin;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author markduah, Alex Reep
 * 
 */
public class DocumentController implements Initializable {

    private ImageView document;
    @FXML
    private BorderPane imgBorder;

    private String filePath;
    @FXML
    private TextField description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void fileChooser(ActionEvent event) throws MalformedURLException, IOException {
        FileChooser file = new FileChooser();
        file.setTitle("Document File");
        file.getExtensionFilters().addAll(
            new ExtensionFilter("All Files", "*.*"),
            new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
            new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
            new ExtensionFilter("Text Files", "*.txt"));
        File selectedFile = file.showOpenDialog(new Stage());
        if (selectedFile != null) {
            filePath = selectedFile.toURI().toURL().toString();
            String fileName = selectedFile.getName();
//            if(checkFile(fileName)){
//               System.out.println(filePath);
//               System.out.println(fileName);
//            }else{
                document = new ImageView(filePath);
                document.setPreserveRatio(true);
                document.setFitWidth(568);
                document.setFitHeight(525);
                imgBorder.setCenter(document);
            //}
        }
    }

    private boolean checkFile(String filePath){
        String fileExtension = "";
        boolean notImage = false;
        
        int i = filePath.lastIndexOf('.');
        if(i > 0){
            fileExtension = filePath.substring(i + 1);
        }
        
        if(fileExtension != "png" || fileExtension != "jpg" || fileExtension != "gif"){
            notImage = true;
        }
        return notImage;
    }
    @FXML
    public void uploadDoc(ActionEvent event) throws SQLException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.now();
        String date = formatter.format(localDate);
        System.out.println(date);
        int myID = 10010;
        DBConnection dbConnect = new DBConnection();
        dbConnect.uploadDocument(myID, filePath, description.getText(), date);
        //close window
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
}

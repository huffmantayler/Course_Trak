/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package courseTrak.Admin;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author markduah
 */
public class CourseTrak extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        String loginScene = "../FXML/LoginFXML.fxml";
        String adminScene = "../FXML/adminDashboard.fxml";
        String teacherScene = "../FXML/TeacherDashboard.fxml";
        Parent root = FXMLLoader.load(getClass().getResource(loginScene));
        primaryStage.setTitle("Course-Trak");
        primaryStage.setScene(new Scene(root, 1300, 800));
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

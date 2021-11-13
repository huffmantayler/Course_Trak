/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courseTrak.Admin;

import java.io.FileNotFoundException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 *
 * @author markduah
 */
class FxmlLoader {
    
    private Pane view;
    public Pane getPage(String filename) {
        try {

            URL fileUrl = CourseTrak.class.getResource(filename);
            if (fileUrl == null) {
                throw new FileNotFoundException("fxml file not found");
            }
            view = FXMLLoader.load(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("no page " + filename + "  please check fxml loader");
        }
        return view;
    }
    
}

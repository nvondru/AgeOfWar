/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ageofwar.AgeOfWar;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author nicolasvondru
 */
public class StartWindowController implements Initializable {
    
//   Variables
    private AgeOfWar master;
    private Scene scene;
    
//    FXML Variables
    
    @FXML
    private VBox root;  
      
//    Constructor
    public void init(AgeOfWar master) {
        scene = new Scene(root);
        this.master = master;      
    }
    
//    FXML-Methods
    
    @FXML
    private void startGame(ActionEvent event) throws IOException {
        master.loadPlayground();
    }

    @FXML
    private void openSettings(ActionEvent event) {
    }

    @FXML
    private void quitGame(ActionEvent event) {
    }

//    Helper-Methods
    
//    Getter/Setter

    public Scene getScene() {
        return scene;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
}

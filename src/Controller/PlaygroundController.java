/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ageofwar.AgeOfWar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author nicolasvondru
 */
public class PlaygroundController implements Initializable {
    
//    Variables
    private AgeOfWar master;
    private Scene scene;
    
//    FXML-Variables
    @FXML
    private AnchorPane root;    
    @FXML
    private AnchorPane playfield;

//    Konstruktor    
    public void init(AgeOfWar master) {
        scene = new Scene(root);
        this.master = master;      
    }
    

    
//    FXML Methods
    @FXML
    private void movePlayfield(ScrollEvent event) {
        if(event.getDeltaY() < 0){
            playfield.setLayoutX(playfield.getLayoutX() - 50);
        }else{
            playfield.setLayoutX(playfield.getLayoutX() + 50);
        }
    }
    
//    Helper Methods
    
    
//  Getter / Setter

    public Scene getScene() {
        return scene;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
}

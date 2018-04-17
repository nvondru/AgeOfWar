/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author emazi
 */
public class Healer  extends Unit{
    //Variables 
    
    
    //Constructor
    public Healer(double position){
        form = new ImageView();
        form.setFitWidth(100);
        form.setFitHeight(200);
        form.getStyleClass().add("meele");
        form.setLayoutX(position);
        form.setLayoutY(505);
        if(position > 4000){
            form.setImage(new Image(getClass().getResource("/Images/healer_mirrored.bmp").toString()));
        }else{
            form.setImage(new Image(getClass().getResource("/Images/healer.bmp").toString()));
        }
        setCost(30);
    }
    //Helper Methods
    
    // Getter / Setter
}

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
public class Range extends Unit {
    //Variables 
    
    
    //Constructor
    public Range(double position, Player myPlayer){
        super(position, myPlayer); 
        
        form.getStyleClass().add("meele");
        cost = 20;
        health = 100;
        damage = 20;
        armor = 3;
        
        if(myPlayer.getName().equals("Player 2")){
            form.setImage(new Image(getClass().getResource("/Images/range_mirrored.bmp").toString()));
        }else{
            form.setImage(new Image(getClass().getResource("/Images/range.bmp").toString()));
        }
        
    }
    //Helper Methods
    
    // Getter / Setter
}

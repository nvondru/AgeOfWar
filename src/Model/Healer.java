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
    public Healer(double position, Player myPlayer){
        super(position, myPlayer);  
        
        form.getStyleClass().add("meele");
        cost = 30;
        health = 60;
        damage = 0;
        armor = 0;
        
        if(myPlayer.getName().equals("Player 2")){
            form.setImage(new Image(getClass().getResource("/Images/healer_mirrored.bmp").toString()));
        }else{
            form.setImage(new Image(getClass().getResource("/Images/healer.bmp").toString()));
        }
        
    }
    //Helper Methods
    
    // Getter / Setter
}

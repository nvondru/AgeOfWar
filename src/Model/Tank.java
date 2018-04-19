/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.effect.Effect;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author emazi
 */
public class Tank extends Unit{
    //Variables 
    
    
    //Constructor
    public Tank(double position, Player myPlayer){
        super(position, myPlayer);    
        
        form.getStyleClass().add("meele");
        cost = 40;
        health.set(120);
        damage = 10;
        armor = 10;
        salary = 30;
        range = 0;
        
        if(myPlayer.getName().equals("Player 2")){
            form.setImage(new Image(getClass().getResource("/Images/tank_mirrored.bmp").toString()));
        }else{
            form.setImage(new Image(getClass().getResource("/Images/tank.bmp").toString()));        
        }
        
    }
    //Helper Methods
    
    // Getter / Setter

}

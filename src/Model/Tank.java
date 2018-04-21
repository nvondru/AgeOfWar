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
   
        cost = 40;
        maxHealth.set(120);
        health.set(maxHealth.get());
        damage = 15;
        armor = 10;
        salary = 30;
        range = 1;
        
        if(myPlayer.getName().equals("Player 2")){
            unitImage.setImage(new Image(getClass().getResource("/Images/tank_mirrored.bmp").toString()));
        }else{
            unitImage.setImage(new Image(getClass().getResource("/Images/tank.bmp").toString()));        
        }
        
    }
    //Helper Methods
    @Override
    protected void useSkill(){
        hitTransition.play();
    }
    // Getter / Setter

}

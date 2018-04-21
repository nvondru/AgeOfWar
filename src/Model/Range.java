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
        
        cost = 20;
        maxHealth.set(120);
        health.set(maxHealth.get());
        damage = 20;
        armor = 3;
        salary = 10;
        range = 3;
        
        if(myPlayer.getName().equals("Player 2")){
            unitImage.setImage(new Image(getClass().getResource("/Images/range_mirrored.bmp").toString()));
        }else{
            unitImage.setImage(new Image(getClass().getResource("/Images/range.bmp").toString()));
        }
        
    }
    //Helper Methods
    @Override
    protected void useSkill(){
        hitTransition.play();
    }   
    // Getter / Setter
}

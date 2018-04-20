/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author nicolasvondru
 */
public class Meele extends Unit{   
    
    //Variables 
    
    
    //Constructor
    public Meele(double position, Player myPlayer){
        super(position, myPlayer);
                
        form.getStyleClass().add("meele");
        
        cost = 10;
        maxHealth.set(120);
        health.set(maxHealth.get());        
        damage = 30;
        armor = 5;
        salary = 5;
        range = 1;
        
                
        if(myPlayer.getName().equals("Player 2")){
            form.setImage(new Image(getClass().getResource("/Images/meele_mirrored.png").toString()));
        }else{
            form.setImage(new Image(getClass().getResource("/Images/meele.png").toString()));
        }
        
    }
    //Helper Methods
    @Override
    protected void doSkill(){
        hitTransition.play();
    }
        
    
    // Getter / Setter

    
    
}

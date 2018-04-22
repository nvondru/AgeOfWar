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
                
        
        cost = 10;
        maxHealth.set(120);
        health.set(maxHealth.get());        
        damage = 30;
        armor = 5;
        salary = 5;
        range = 1;
        
        framesCount = 6;
        columnsCount = 6;        
        
                
        if(myPlayer.getName().equals("Player 1")){
            waitingSprite = new Image(getClass().getResource("/Images/Meele/waiting_sprite.png").toString());
            skillSprite = new Image(getClass().getResource("/Images/Meele/skill_sprite.png").toString());
        }else{
            waitingSprite = new Image(getClass().getResource("/Images/Meele/waiting_sprite_mirrored.png").toString());
            skillSprite = new Image(getClass().getResource("/Images/Meele/skill_sprite_mirrored.png").toString());
        }
        
        unitImage.setImage(waitingSprite);
        
        initSkillAnimation();
        
    }
    //Helper Methods
    @Override
    protected void useSkill(){
        skillEvent.play();
    }
        
    
    // Getter / Setter

    
    
}

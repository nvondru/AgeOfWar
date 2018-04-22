/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.image.Image;

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
        
        framesCount = 4;
        columnsCount = 4;
        
        if(myPlayer.getName().equals("Player 1")){
            waitingSprite = new Image(getClass().getResource("/Images/Range/waiting_sprite.png").toString());
            skillSprite = new Image(getClass().getResource("/Images/Range/skill_sprite.png").toString());
        }else{
            waitingSprite = new Image(getClass().getResource("/Images/Range/waiting_sprite_mirrored.png").toString());
            skillSprite = new Image(getClass().getResource("/Images/Range/skill_sprite_mirrored.png").toString());
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

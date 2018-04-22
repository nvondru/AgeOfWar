/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.util.Duration;

/**
 *
 * @author emazi
 */
public class Healer  extends Unit{
    //Variables 
    Timeline healAnimation = new Timeline();
    private int intellect;
    ArrayList<Image> imageList = new ArrayList<>();
   
    
    //Constructor
    public Healer(double position, Player myPlayer){
        super(position, myPlayer);  
        
        cost = 30;
        maxHealth.set(60);
        health.set(40);
        damage = 10;
        armor = 0;
        salary = 20;
        range = 2;
        intellect = 8;
        
        framesCount = 6;
        columnsCount = 6;
        
        if(myPlayer.getName().equals("Player 1")){
            waitingSprite = new Image(getClass().getResource("/Images/Healer/waiting_sprite.png").toString());
            skillSprite = new Image(getClass().getResource("/Images/Healer/skill_sprite.png").toString());
        }else{
            waitingSprite = new Image(getClass().getResource("/Images/Healer/waiting_sprite_mirrored.png").toString());
            skillSprite = new Image(getClass().getResource("/Images/Healer/skill_sprite_mirrored.png").toString());
        }
        
        unitImage.setImage(waitingSprite);
        initSkillAnimation();
    }
//      Helper Methods
    
        @Override
        protected void useSkill(){
            skillEvent.play();
        }
        
       
       // Getter / Setter
    }
    
        
        
    
    


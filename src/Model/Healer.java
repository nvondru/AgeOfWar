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
        initHealingAnimation();
        
        if(myPlayer.getName().equals("Player 2")){
            imageList.add(new Image(getClass().getResource("/Images/healer_mirrored.bmp").toString()));
            imageList.add(new Image(getClass().getResource("/Images/healer_HealingAnimation_mirrored.bmp").toString()));
            unitImage.setImage(imageList.get(0));
        }else{
            imageList.add(new Image(getClass().getResource("/Images/healer.bmp").toString()));
            imageList.add(new Image(getClass().getResource("/Images/healer_HealingAnimation.bmp").toString()));
            unitImage.setImage(imageList.get(0));            
        }
        
        
    
    }
//      Helper Methods
    private void initHealingAnimation(){
        KeyFrame k1 = new KeyFrame(Duration.millis(0), (ActionEvent t) -> {
                unitImage.setImage(imageList.get(1));
                heal();
                System.out.println("healed");
                
        });
        
        KeyFrame k2 = new KeyFrame(Duration.millis(1000), (ActionEvent t) -> {
                unitImage.setImage(imageList.get(0));
                
        });
        KeyFrame k3= new KeyFrame(Duration.millis(2000), (ActionEvent t) -> {
                
        });        
         
        healAnimation.getKeyFrames().addAll(k1,k2, k3);

    }
        @Override
        protected void searchForTarget(){
            indexOfUnit = myPlayer.getListUnits().indexOf(this);
            if(myPlayer.getListUnits().size() >= 2 && indexOfUnit != 0){ //When u are behind an ally Unit
                target = myPlayer.getListUnits().get(0);
            }else if(!enemyPlayer.getListUnits().isEmpty()){ //when u are u are in front of an enemy Unit
                target = enemyPlayer.getListUnits().get(0);
            }else if(form.getBoundsInParent().intersects(enemyPlayer.getBase().getForm().getBoundsInParent())){//When u are in front of enemy Base
                target = enemyPlayer.getBase();
            }else if(enemyPlayer.getBase().getHealth() <= 0){//When enemy base is down
                target = null;
            }else{
                target = this;
            }
        } 
        @Override
        protected void useSkill(){
            if(enemyPlayer.getListUnits().contains(target)){
               hitTransition.play();
           }else if(myPlayer.getListUnits().contains(target) && target.health.get() < target.maxHealth.get()){
                healAnimation.play();
           }else if(target.equals(enemyPlayer.getBase())){
               hitTransition.play();
           }
        }
        private void heal(){
            if((target.health.get() + intellect) > target.maxHealth.get()){ //If u are overhealing the target
                target.health.set(target.maxHealth.get());
            }else{
                target.health.set(target.health.get() + intellect); 
            }            
            
        }
       
       // Getter / Setter
    }
    
        
        
    
    


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
        health.set(60);
        damage = 5;
        armor = 0;
        salary = 20;
        range = 0;
        
        if(myPlayer.getName().equals("Player 2")){
            form.setImage(new Image(getClass().getResource("/Images/healer_mirrored.bmp").toString()));
        }else{
            form.setImage(new Image(getClass().getResource("/Images/healer.bmp").toString()));
        }
        
        
    
    }
        //Helper Methods
        @Override
        protected void searchForTarget(){
            if(!myPlayer.getListUnits().isEmpty()){
                target = enemyPlayer.getListUnits().get(0);
            }else{
                target = this;
            } 
        } 
       
       // Getter / Setter
    }
    
        
        
    
    


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author nicolasvondru
 */
public abstract class Unit {
    
    //    Variables
    protected int health;
    protected int armor;
    protected int damage;
    protected int attackSpeed;
    protected int moveSpeed;
    protected int cost;
    protected int range;
    protected double creationTime;
    protected ImageView form;
    protected double position;
    protected boolean isDead;
    protected AnimationTimer moveTimer;
    protected ScaleTransition attackTransition;
    protected ScaleTransition attackBaseTransition;
    
    protected Player myPlayer;    
    protected Player enemyPlayer;
    protected Unit target;
    
   

//    Constructor    
    public Unit(double position, Player myPlayer){   
        
        this.myPlayer = myPlayer;
        this.position = position;
        
        if(myPlayer.getName().equals("Player 1")){
            enemyPlayer = myPlayer.getRecentGame().getPlayer2(); 
        }else{
            enemyPlayer = myPlayer.getRecentGame().getPlayer1();
        }  
        
        form = new ImageView();
        form.setFitWidth(100);
        form.setFitHeight(200);
        form.setLayoutX(position);
        form.setLayoutY(505);   
        
        isDead = false;
        initMoveTimer();
        initAttackTransition();
        initAttackBaseTransition();
        move();
    }
    
    
//    Methods
    private void move(){        
        moveTimer.start();
    }
    
    private void fight(){
        moveTimer.stop();
        attackTransition.playFromStart();
    }
      
    private void attack(){
        target.takeDamage(damage);
    }
    private void attackBase(){
        moveTimer.stop();
        attackBaseTransition.playFromStart();
        //enemyPlayer.getBase().getDamage(damage);
    }
    
    public void takeDamage(int damageTaken){
        int reducedDamage = reduceDamge(damageTaken);
        health -= reducedDamage;        
        System.out.println("Life Unit from " + myPlayer.getName() + " is: " + health);        
        if(health <= 0){
            die();
        }        
    }
    
    private int reduceDamge(int damageTaken) {
        int reducedDamage = damageTaken - armor;
        if(reducedDamage < 0){
            reducedDamage = 0;
        }
        return reducedDamage;
    }
    private void die(){
        
        isDead = true;
        attackTransition.stop();
        myPlayer.getRecentGame().getPlaygroundController().getPlayfield().getChildren().remove(form);
        myPlayer.getListUnits().remove(this);
    }
    
    private void initMoveTimer() {
        moveTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                int index = findIndexOfUnit();
                checkToFight(index);
                checkToWait(index);
            }                     
        };    
    }
    //Check if Unit has to fight
    private void checkToFight(int index){
        if(!enemyPlayer.getListUnits().isEmpty() && index == 0){
            target = enemyPlayer.getListUnits().get(0);
            if(form.getBoundsInParent().intersects(target.getForm().getBoundsInParent())){                        
                fight();      
            }
        }else if(form.getBoundsInParent().intersects(enemyPlayer.getBase().getForm().getBoundsInParent())){
            attackBase();
        }
    }
    //Check if Unit has to wait one after the other 
    private void checkToWait(int index){
        if(index != 0){
            target = myPlayer.getListUnits().get(index - 1);
            if(form.getBoundsInParent().intersects(target.getForm().getBoundsInParent())){
               // waiting
            }else{
                walk();
            }
        }else{
            walk();
        }
    }
    //Move object
    private void walk(){
        if(myPlayer.getName().equals("Player 1")){
            form.setLayoutX(form.getLayoutX() + 2);
        }else{
            form.setLayoutX(form.getLayoutX() - 2);
       }         
    }
    //Index of Unit Array 
    private int findIndexOfUnit(){
        for(Unit index : myPlayer.getListUnits()){
            if(index.getForm().equals(form)){
                return myPlayer.getListUnits().indexOf(index);
            }
        }
        return -1;
    }
    
    //Attack Unit
    private void initAttackTransition() {
        attackTransition = new ScaleTransition(Duration.millis(500), form);
        attackTransition.setByX(1.2);
        attackTransition.setByY(1.2);
        attackTransition.setAutoReverse(true);   
        attackTransition.setCycleCount(2);        
        attackTransition.setOnFinished((event) -> {
            
            attack();
            
            if(target.isDead){
                attackTransition.stop();                
                move();
            }else{
                attackTransition.setDelay(Duration.millis(500));            
                attackTransition.playFromStart();  
            }
            
        });        
    }
    //Attack Base
    private void initAttackBaseTransition() {
        attackBaseTransition = new ScaleTransition(Duration.millis(500), form);
        attackBaseTransition.setByX(1.5);
        attackBaseTransition.setByY(1.5);
        attackBaseTransition.setAutoReverse(true);   
        attackBaseTransition.setCycleCount(2);        
        attackBaseTransition.setOnFinished((event) -> {
            
            if(!enemyPlayer.getListUnits().isEmpty()){//If enemy Player are alive
                target = enemyPlayer.getListUnits().get(0);
                attackBaseTransition.stop();
                fight();
            }else if(enemyPlayer.getBase().getHealth() <= 0){ //If enemy Base is destroyed
                attackBaseTransition.stop();
            }else{ //Attack
                enemyPlayer.getBase().getDamage(damage);
                attackBaseTransition.setDelay(Duration.millis(500));            
                attackBaseTransition.playFromStart();  
            }
            
        });        
    }    
//    Getter/Setter

    public ImageView getForm() {
        return form;
    }

    public int getCost() {
        return cost;
    }

    

    
    

    

    
    
    
    
    
}

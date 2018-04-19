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
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author nicolasvondru
 */
public abstract class Unit {
    
    //    Variables
    protected final IntegerProperty health = new SimpleIntegerProperty();
    protected int armor;
    protected int damage;
    protected int attackSpeed;
    protected int moveSpeed;
    protected int cost;
    protected int range;
    protected int salary;
    protected double creationTime;
    protected ImageView form;
    protected double position;
    protected boolean isDead;
    protected AnimationTimer moveTimer;
    protected ScaleTransition attackTransition;
    
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
        move();
    }
    //Constructor for Base
    public Unit(ImageView form, Player myPlayer){
       this.myPlayer = myPlayer;
       this.form = form; 
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

    public void takeDamage(int damageTaken){
        int reducedDamage = reduceDamge(damageTaken);
        health.set(health.get() - reducedDamage);       
        System.out.println("Life Unit from " + myPlayer.getName() + " is: " + health);    
        System.out.println("health" + health.get());
        if(health.get() <= 0){
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
        if(target.getClass().toString().contains("Base")){

        }else{
            attackTransition.stop();
            myPlayer.getRecentGame().getPlaygroundController().getPlayfield().getChildren().remove(form);
            enemyPlayer.setMoney(enemyPlayer.getMoney() + salary); //earn money after kill
            myPlayer.getListUnits().remove(this);
        }
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
            if(form.getBoundsInParent().intersects(target.getForm().getBoundsInParent())){  //                       
                fight();      
            }
        }else if(form.getBoundsInParent().intersects(enemyPlayer.getBase().getForm().getBoundsInParent())){
            target = enemyPlayer.getBase();
            fight();
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
            form.setLayoutX(form.getLayoutX() + 20);
        }else{
            form.setLayoutX(form.getLayoutX() - 20);
       }         
    }
    //Index of Unit Array 
    private int findIndexOfUnit(){
            return myPlayer.getListUnits().indexOf(this);
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
            }else if(target.getClass().toString().contains("Base") && !enemyPlayer.getListUnits().isEmpty()){
                target = enemyPlayer.getListUnits().get(0);
                attackTransition.setDelay(Duration.millis(500));            
                attackTransition.playFromStart();  
            }else{
                attackTransition.setDelay(Duration.millis(500));            
                attackTransition.playFromStart();               
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

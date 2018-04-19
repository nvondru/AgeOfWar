/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.util.Duration;

/**
 *
 * @author nicolasvondru
 */
public abstract class Unit {
    
    protected Player myPlayer;    
    protected Player enemyPlayer;
    protected Unit target;
    
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
    protected  boolean isFighting = false;
    protected double distanceToTarget;
    
    protected static int UNIT_WIDTH = 100;
    protected static int UNIT_HEIGHT = 200;
    protected int stepWidth;    
    
    protected FadeTransition hitTransition;
    protected AnimationTimer unitBehaviour;
    
//    Constructor    
    public Unit(double position, Player myPlayer){   
        
        this.myPlayer = myPlayer;
        this.position = position;
        
        if(myPlayer.getName().equals("Player 1")){
            enemyPlayer = myPlayer.getRecentGame().getPlayer2();
            stepWidth = 4;
        }else{
            enemyPlayer = myPlayer.getRecentGame().getPlayer1();
            stepWidth = -4;
        }          
        form = new ImageView();
        form.setFitWidth(UNIT_WIDTH);
        form.setFitHeight(UNIT_HEIGHT);
        form.setLayoutX(position);
        form.setLayoutY(505);   
        
        isDead = false;
        
        initUnitBehaviour();        
        initAttackTransition();
        
        unitBehaviour.start();        
    }
    //Constructor for Base
    public Unit(ImageView form, Player myPlayer){
       this.myPlayer = myPlayer;
       this.form = form; 
    }
    
//    Methods
    private void initUnitBehaviour() {
        unitBehaviour = new AnimationTimer() {
            @Override
            public void handle(long now) {
                searchForTarget();
                moveIfNeeded();                
                fightIfPossible();                
            }
        };
    }  
    
    
    protected void searchForTarget() {
        if(!enemyPlayer.getListUnits().isEmpty()){
            target = enemyPlayer.getListUnits().get(0);
        }else{
            target = enemyPlayer.getBase();
        }                 
    }
    private void moveIfNeeded() {
        int indexOfUnit = myPlayer.getListUnits().indexOf(this);
        if(indexOfUnit == 0){
            if(!form.getBoundsInParent().intersects(target.getForm().getBoundsInParent())){
                walkOneStep();
            }
       }else if(indexOfUnit > 0){
            if(!form.getBoundsInParent().intersects(myPlayer.getListUnits().get(indexOfUnit - 1).getForm().getBoundsInParent())){
                walkOneStep();
            }
        }
    }
    //Move object
    private void walkOneStep(){        
        form.setLayoutX(form.getLayoutX() + stepWidth);                 
    }
    private void fightIfPossible() {
        if(isInRange()){   
            hitTransition.play();  
        }
    }
    private boolean isInRange() {
        distanceToTarget = form.getLayoutX() - target.getForm().getLayoutX();
        if(distanceToTarget < 0){
            distanceToTarget = - distanceToTarget;
        }
        if(distanceToTarget <= range * UNIT_WIDTH ){
            return true;
        }else{
            return false;
        }        
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
        enemyPlayer.receiveSalary(salary);
        myPlayer.getRecentGame().getPlaygroundController().getPlayfield().getChildren().remove(form);        
        myPlayer.getListUnits().remove(this);
        unitBehaviour.stop();        
    }
    
    //Attack Unit
    private void initAttackTransition() {
        hitTransition = new FadeTransition(Duration.millis(500), form);          
        hitTransition.setToValue(0.5);
        hitTransition.setAutoReverse(true);
        hitTransition.setCycleCount(2);
        hitTransition.setOnFinished((event) -> {
            attack();
        });       
    }
  
//    Getter/Setter

    public ImageView getForm() {
        return form;
    }

    public int getCost() {
        return cost;
    }

    public void setStepWidth(int stepWidth) {
        this.stepWidth = stepWidth;
    }

    public int getStepWidth() {
        return stepWidth;
    }
    

    

    

    
    

    
    

    

    
    
    
    
    
}

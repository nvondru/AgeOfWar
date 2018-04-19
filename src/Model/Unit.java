/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.ImageView;
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
    protected int range = 0;
    protected int salary;
    
    protected double creationTime;
    protected ImageView form;
    protected double position;
    protected boolean isDead;
    protected  boolean isFighting = false;
    protected AnimationTimer moveTimer;
    protected ScaleTransition hitTransition;
    protected ScaleTransition FUCKYOUTransition;
    protected AnimationTimer unitBehaviour;
    
    protected Player myPlayer;    
    protected Player enemyPlayer;
    protected Unit target;
    
    protected static int UNIT_WIDTH = 100;
    protected static int UNIT_HEIGHT = 200;
    protected int stepWidth;
    protected double distanceToTarget;
    
   

//    Constructor    
    public Unit(double position, Player myPlayer){   
        
        this.myPlayer = myPlayer;
        this.position = position;
        
        if(myPlayer.getName().equals("Player 1")){
            enemyPlayer = myPlayer.getRecentGame().getPlayer2();
            stepWidth = 1;
        }else{
            enemyPlayer = myPlayer.getRecentGame().getPlayer1();
            stepWidth = -1;
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
//                fightIfPossible();
            }

            
        };
    }
    
    
    private void searchForTarget() {
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
        }else{
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
                        
            hitTransition.playFromStart();
            unitBehaviour.stop();
            
        }
    }
    private boolean isInRange() {
        distanceToTarget = form.getLayoutX() - target.getForm().getLayoutX();
        if(distanceToTarget < 0){
            distanceToTarget = - distanceToTarget;
        }
        if(distanceToTarget <= range * UNIT_WIDTH + UNIT_WIDTH){
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
        if(target.equals(enemyPlayer.getBase())){

        }else{
            myPlayer.getRecentGame().getPlaygroundController().getPlayfield().getChildren().remove(form);
            enemyPlayer.receiveSalary(salary);
            myPlayer.getListUnits().remove(this);
        }
    }

    
    //Attack Unit
    private void initAttackTransition() {
        hitTransition = new ScaleTransition(Duration.millis(1000), form);
        hitTransition.setByX(1.2);
        hitTransition.setByY(1.2); 
        hitTransition.setOnFinished((event) -> {
            
            attack();
            FUCKYOUTransition.playFromStart();
        });
        
        FUCKYOUTransition = new ScaleTransition(Duration.millis(1000), form);
        FUCKYOUTransition.setByX(0.0);
        FUCKYOUTransition.setByY(0.0);
        FUCKYOUTransition.setOnFinished((event) -> {
            System.out.println("hit");
            unitBehaviour.start();
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

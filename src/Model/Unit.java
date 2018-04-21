/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author nicolasvondru
 */
public abstract class Unit {
    //    Variables
    
    // Associations
    protected Player myPlayer;    
    protected Player enemyPlayer;
    protected Unit target;   
     
//    Stats
    protected final DoubleProperty maxHealth = new SimpleDoubleProperty();
    protected final DoubleProperty health = new SimpleDoubleProperty();
    protected int armor;
    protected int damage;
    protected int attackSpeed;
    protected int moveSpeed;
    protected int cost;
    protected int range;
    protected int salary;    
    protected double creationTime;
        
//    needed for calculating behaviour
    protected double spawnPosition;
    protected boolean isDead;
    protected  boolean isFighting = false;
    protected double distanceToTarget;
    protected int stepWidth; 
    protected int indexOfUnit;
    
    //    visual components
    protected VBox form;
    protected HBox healthBarForm;
    protected AnchorPane healthBar;
    protected Rectangle healthBarMax;
    protected Rectangle healthBarRemaining;
    protected ImageView unitImage;
    
    //    animations
    protected FadeTransition hitTransition;
    protected AnimationTimer unitBehaviour;

//    predefined widths & heights for visual components
    protected final int UNIT_WIDTH = 100;
    protected final int UNIT_HEIGHT = 200;
    
    protected final int BASE_WIDTH = 400;
    protected final int BASE_HEIGHT = 400;
    
    protected final int UNIT_FORM_WIDTH = 100;
    protected final int UNIT_FORM_HEIGHT = 230;    
    
    protected final  int BASE_FORM_WIDTH = 400;
    protected final  int BASE_FORM_HEIGHT = 460;
    
    protected final  int UNIT_HEALTHBAR_WIDTH= 80;
    protected final  int UNIT_HEALTHBAR_HEIGHT = 20;
    
    protected final  int BASE_HEALTHBAR_WIDTH = 350;
    protected final  int BASE_HEALTHBAR_HEIGHT = 40;
    
    
//    Constructor    
    public Unit(double spawnPosition, Player myPlayer){        
        this.myPlayer = myPlayer;
        this.spawnPosition = spawnPosition;
        
        if(myPlayer.getName().equals("Player 1")){
            enemyPlayer = myPlayer.getRecentGame().getPlayer2();
            stepWidth = 4;
        }else{
            enemyPlayer = myPlayer.getRecentGame().getPlayer1();
            stepWidth = -4;
        }          
        
        isDead = false;
        
//        functionality is added
        buildForm(UNIT_HEALTHBAR_WIDTH, UNIT_HEALTHBAR_HEIGHT, UNIT_WIDTH, UNIT_HEIGHT, UNIT_FORM_WIDTH, UNIT_FORM_HEIGHT);
        initUnitBehaviour();   
        connectHealthToBar();
        initAttackTransition();
                
        unitBehaviour.start();        
    }
    
    //Constructor for Base
    public Unit(double spawnPosition, Player myPlayer, boolean isBase){
       this.myPlayer = myPlayer;
       this.spawnPosition = spawnPosition;
       
        if(myPlayer.getName().equals("Player 1")){
            enemyPlayer = myPlayer.getRecentGame().getPlayer2();
        }else{
            enemyPlayer = myPlayer.getRecentGame().getPlayer1();
        }  
        
       buildForm(BASE_HEALTHBAR_WIDTH, BASE_HEALTHBAR_HEIGHT, BASE_WIDTH, BASE_HEIGHT, BASE_FORM_WIDTH, BASE_FORM_HEIGHT);       
       connectHealthToBar();
    }
    
//    Methods
    
//    A unit with unitBahviour will do the following actions every frame (60 per second):
//      - search for its preferred target (is overriden by healer), 
//      - move one stepWidth if: doesnt collide with first enemy unit or doesn't collide with neighbour ally unit)
//      - use its own standard skill if: there is a target (always unless enemy base is down) and target is in range 
    private void initUnitBehaviour() {
        unitBehaviour = new AnimationTimer() {
            @Override
            public void handle(long now) {
                searchForTarget();
                moveIfNeeded();                
                useSkillIfPossible();                
            }
        };
    }  
    
//    Possible targets: First enemy unit, enemy base, no target
    protected void searchForTarget() {
        if(!enemyPlayer.getListUnits().isEmpty()){
            target = enemyPlayer.getListUnits().get(0);
        }else if(enemyPlayer.getBase().getHealth() > 0){
            target = enemyPlayer.getBase();
        }else{
            target = null;
        }                 
    }
    

    private void moveIfNeeded() {
        //    the condition depends on units position on the field
        indexOfUnit = myPlayer.getListUnits().indexOf(this);
        
        
        if(hasTarget()){
            // Behaviour for first unit
            if(indexOfUnit == 0){                
                if(!form.getBoundsInParent().intersects(target.getForm().getBoundsInParent()) || target.equals(this)){
                    walkOneStep();
                }
            // Behaviour for following units
            }else if(indexOfUnit > 0){
                if(!form.getBoundsInParent().intersects(myPlayer.getListUnits().get(indexOfUnit - 1).getForm().getBoundsInParent())){
                    walkOneStep();
                }
            }
        }        
    }
    //Move object
    private void walkOneStep(){  
        form.setLayoutX(form.getLayoutX() + stepWidth);                
          
    }
    private void useSkillIfPossible() {
        if(hasTarget()){
            if(isInRange()){   
                useSkill();  
            }
        }        
    }
    
    private boolean hasTarget() {
        if(target == null){
            return false;
        }else{
            return true;
        }
    }
    
    private boolean isInRange() {
        distanceToTarget = form.getLayoutX() - target.getForm().getLayoutX();
        boolean isOnBase = form.getBoundsInParent().intersects(enemyPlayer.getBase().getForm().getBoundsInParent());
        if(distanceToTarget < 0){
            distanceToTarget = - distanceToTarget;
        }
        if(distanceToTarget <= range * UNIT_WIDTH || isOnBase){
            return true;
        }else{
            return false;
        }        
    }
    abstract protected void useSkill();
    
    private void attack(){
        if(hasTarget()){
            target.takeDamage(damage);
        }      
    }

    public void takeDamage(int damageTaken){
        int reducedDamage = reduceDamge(damageTaken);
        health.set(health.get() - reducedDamage);    
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
    
    protected void die(){
        isDead = true;
        enemyPlayer.receiveSalary(salary);
        myPlayer.getRecentGame().getPlaygroundController().getPlayfield().getChildren().remove(form);        
        myPlayer.getListUnits().remove(this);
        unitBehaviour.stop();        
    }
    
    //Attack target
    private void initAttackTransition() {
        hitTransition = new FadeTransition(Duration.millis(500), unitImage);          
        hitTransition.setToValue(0.5);
        hitTransition.setAutoReverse(true);
        hitTransition.setCycleCount(2);
        hitTransition.setOnFinished((event) -> {
            attack();
        });       
    }
    
//    A visual implementation of unit is built 
//    Contains: Containers for healthBar & unitImage
//    Parameters are needed to make the method useable for both, moving units as well as bases
    private void buildForm(int healtBarWidth, int healthBarHeight, int unitWidth, int unitHeight, int formWidth, int formHeight) {
        form = new VBox();
        form.setPrefWidth(formWidth);
        form.setPrefHeight(formHeight);
        if(this instanceof Base){
            if(myPlayer.getName().equals("Player 1")){
                form.setLayoutX(spawnPosition - unitWidth);
            }else{
                form.setLayoutX(spawnPosition + UNIT_WIDTH);
            }            
            form.setLayoutY(300);
        }else{
            form.setLayoutX(spawnPosition);
            form.setLayoutY(500); 
        }           
        form.setAlignment(Pos.TOP_CENTER);

        healthBarForm = new HBox();
        healthBarForm.setAlignment(Pos.TOP_CENTER);
        if(this instanceof Base){
            healthBarForm.setPrefHeight(60);
        }else{
            healthBarForm.setPrefHeight(30);
        } 
        
        healthBar = new AnchorPane();
        
        healthBarMax = new Rectangle(healtBarWidth, healthBarHeight);
        healthBarMax.setFill(Color.RED);   
        
        healthBarRemaining = new Rectangle(healtBarWidth, healthBarHeight);
        healthBarRemaining.setFill(Color.LIGHTGREEN);  
        
        unitImage = new ImageView();
        unitImage.setFitWidth(unitWidth);
        unitImage.setFitHeight(unitHeight);  
        
        healthBar.getChildren().addAll(healthBarMax, healthBarRemaining);
        healthBarForm.getChildren().add(healthBar);
        form.getChildren().addAll(healthBarForm, unitImage);        
        
    }

//    Whenever the health of unit changes the new width of healthBarRemaining is recalculated
    private void connectHealthToBar() {
        health.addListener((observable, oldValue, newValue) -> {            
            healthBarRemaining.setWidth(healthBarMax.getWidth() * ((double)newValue / maxHealth.get()));
        });
    }
    
    
  
//    Getter/Setter

    public VBox getForm(){
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

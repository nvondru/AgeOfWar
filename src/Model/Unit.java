/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.scene.shape.Rectangle;

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
    protected Rectangle form;
    
//    Constructor    
    
    public Unit(){
        
    }
    
    
//    Methods
    public void die(){
        
    }
    public void attack(){
        
    }
    public void move(){
        
    }
    public void getDamage(){
        
    }
    
//    Getter/Setter

    public Rectangle getForm() {
        return form;
    }
    
    
    
}

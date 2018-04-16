/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
 *
 * @author nicolasvondru
 */
public class Base {
    
    
//    Variables
    private final IntegerProperty health = new SimpleIntegerProperty();
    
    
//    Constructor
    
    public Base(){
        health.set(5000);
    }
    
//    Methods
    public void getDamage(int value){
        setHealth(getHealth() - value);
    }
//    Getter/Setter

    public int getHealth() {
        return health.get();
    }

    public void setHealth(int value) {
        health.set(value);
    }

    public IntegerProperty healthProperty() {
        return health;
    }
    
}

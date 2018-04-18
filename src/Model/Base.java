/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author nicolasvondru
 */
public class Base {
    
    
//    Variables
    private final IntegerProperty health = new SimpleIntegerProperty();
    private Rectangle form;
    
//    Constructor
    
    public Base(Rectangle form){
        health.set(5000);
        this.form = form;
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

    public Rectangle getForm() {
        return form;
    }

    public void setForm(Rectangle form) {
        this.form = form;
    }
    
}

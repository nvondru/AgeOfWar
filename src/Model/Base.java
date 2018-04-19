/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author nicolasvondru
 */
public class Base extends Unit{  
//    Variables


    
//    Constructor
public Base(ImageView form, Player myPlayer){
    super(form,myPlayer);
    health.set(500);
    

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

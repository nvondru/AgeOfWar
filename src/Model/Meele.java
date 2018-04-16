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
public class Meele extends Unit{   
    
    //Variables 
    
    
    //Constructor
    public Meele(double position){
        form = new Rectangle();
        form.setWidth(100);
        form.setHeight(200);
        form.getStyleClass().add("meele");
        form.setLayoutX(position);
        form.setLayoutY(505);
    }
    //Helper Methods
    
    // Getter / Setter

    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

/**
 *
 * @author nicolasvondru
 */
public class Base extends Unit{  
//    Variables
    
private IntegerProperty test = new SimpleIntegerProperty();
    
//    Constructor
public Base(double spawnPosition, Player myPlayer){
    super(spawnPosition, myPlayer, true);

    maxHealth.set(500);
    health.set(maxHealth.get());
    salary = 0;
    
    if(myPlayer.getName().equals("Player 2")){
        unitImage.setImage(new Image(getClass().getResource("/Images/base_mirrored.bmp").toString()));
    }else{
        unitImage.setImage(new Image(getClass().getResource("/Images/base.bmp").toString()));        
    }
}

//    Methods
    
    @Override
    public void useSkill(){
        
    }
    @Override
    public void die(){
        myPlayer.getRecentGame().getPlaygroundController().getPlayfield().getChildren().remove(form);        
        myPlayer.getListUnits().remove(this);
    }
    
//    Getter/Setter

    public double getHealth() {
        return health.get();
    }

    public void setHealth(double value) {
        health.set(value);
    }

    public DoubleProperty healthProperty() {
        return health;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.PlaygroundController;
import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author nicolasvondru
 */
public class Player {
    
//    Associations
    private ArrayList<Unit> listUnits;
    private Base base;
    private Game recentGame;
    
//    Variables
    private final IntegerProperty money = new SimpleIntegerProperty();    
    private final StringProperty name = new SimpleStringProperty();
    private double spawnPosition;
    

//    Constructor
    
    public Player(String name, Game recentGame){
        this.recentGame = recentGame;        
        this.name.set(name);
        
        // defines the default spawn position for a moving unit, depending on players name
        // has to be adjusted when a base is created
        if(name.equals("Player 1")){
            spawnPosition = 400;
        }else{
            spawnPosition = recentGame.getPlaygroundController().getPlayfield().getPrefWidth() - 500;
        }
        
        money.set(2000);
        listUnits = new ArrayList<>();
        base = new Base(spawnPosition, this);
        getRecentGame().getPlaygroundController().getPlayfield().getChildren().add(base.getForm());        
    }
    
    
//    Methods
   
    //creates new unit for this player, if he can afford it
    public void buyUnit(String unitType){ 
        Unit newUnit;
        switch(unitType){
            case "meele":
                newUnit = new Meele(spawnPosition, this);   
                break;
            case "range":
                newUnit = new Range(spawnPosition, this);                
                break;
            case "tank":
                newUnit = new Tank(spawnPosition, this);                
                break;
            case "healer":
                newUnit = new Healer(spawnPosition, this); 
                break;
            default:
                newUnit = new Meele(spawnPosition, this);
                break;
        }
        if(canAffordUnit(newUnit)){
            setMoney(getMoney() - newUnit.getCost());
            listUnits.add(newUnit);
            recentGame.getPlaygroundController().getPlayfield().getChildren().add(newUnit.getForm());
        }        
    }
    
    //checks if player has enough money for the requested unit 
    public boolean canAffordUnit(Unit unitToCheck){
        if(getMoney() >= unitToCheck.getCost()){           
           return true;
        }else{
           return false;  
        }              
    }
    
    // receives money, whenever an enemy unit dies
    public void receiveSalary(int salary){
        money.set(money.get() + salary);
    }
//    Getter/Setter

    public ArrayList<Unit> getListUnits() {
        return listUnits;
    }
    
    public String getName() {
        return name.get();
    }

    public void setName(String value) {
        name.set(value);
    }

    public StringProperty nameProperty() {
        return name;
    }

    public int getMoney() {
        return money.get();
    }

    public void setMoney(int value) {
        money.set(value);
    }

    public IntegerProperty moneyProperty() {
        return money;
    }
    

    public Base getBase() {
        return base;
    }

    public Game getRecentGame() {
        return recentGame;
    }
    
    
    
    
}

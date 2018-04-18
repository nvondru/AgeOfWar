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
import javafx.scene.shape.Rectangle;

/**
 *
 * @author nicolasvondru
 */
public class Player {
    
//    Variables
    private final IntegerProperty money = new SimpleIntegerProperty();
    private ArrayList<Unit> listUnits;
    private Base base;
    private final StringProperty name = new SimpleStringProperty();
    private double position;
    private Game recentGame;

//    Constructor
    
    public Player(String name, double position, Game recentGame, Rectangle form){
        this.recentGame = recentGame;
        this.name.set(name);
        this.position = position;
        money.set(2000);
        listUnits = new ArrayList<>();
        base = new Base(form);
        
    }
    
    
//    Methods
    //create unit 
    public boolean createUnit(String unitType){
        switch(unitType){
            case "meele":
                Meele meele = new Meele(position, this);
                return buyUnit(meele);
            case "range":
                Range range = new Range(position, this);
                return buyUnit(range);
            case "tank":
                Tank tank = new Tank(position, this);
                return buyUnit(tank);
            case "healer":
                Healer healer = new Healer(position, this);
                return buyUnit(healer);
        }
        return false;
    }
    //Check money and add the unit to the array
    public boolean buyUnit(Unit unit){
        if(getMoney() >= unit.getCost()){
           listUnits.add(unit); 
           setMoney(getMoney() - unit.getCost());
           return true;
        }
        return false;
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

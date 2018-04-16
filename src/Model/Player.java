/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

//    Constructor
    
    public Player(String name, double position){
        this.name.set(name);
        this.position = position;
        money.set(2000);
        listUnits = new ArrayList<>();
        base = new Base();
        
    }
    
    
//    Methods
    public void createUnit(String unitType){
        switch(unitType){
            case "meele":
                Meele meele = new Meele(position);
                listUnits.add(meele);
            case "range":
                Range range = new Range(position);
                listUnits.add(range);
            case "tank":
                Tank tank = new Tank(position);
                listUnits.add(tank);
            case "healer":
                Healer healer = new Healer(position);
                listUnits.add(healer);
        }
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
    
    
    
}

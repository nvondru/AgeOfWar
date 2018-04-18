/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Game;
import ageofwar.AgeOfWar;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author nicolasvondru
 */
public class PlaygroundController implements Initializable {
    
//    Variables
    private AgeOfWar master;
    private Scene scene;
    private Game recentGame;
    
//    FXML-Variables
    @FXML
    private AnchorPane root;    
    @FXML
    private AnchorPane playfield;
    @FXML
    private Rectangle ground;
    @FXML
    private Label basePlayer1;
    @FXML
    private Label basePlayer2;
    @FXML
    private Label moneyLabel;
    @FXML
    private Label recentPlayerLabel;
    @FXML
    private Rectangle base1;
    @FXML
    private Rectangle base2;

//    Konstruktor    
    public void init(AgeOfWar master) { 
        
        scene = new Scene(root);
        this.master = master; 
        
        recentGame = new Game(this);        
        connectBaseLabels();        
        connectMoneyLabel();
        
        recentPlayerLabel.setText(recentGame.getRecentPLayer().getName());
        
        System.out.println("Labels are connected!");
       
    }
    
//    FXML Methods
    @FXML
    private void movePlayfield(ScrollEvent event) {
        if(event.getDeltaY() < 0 && playfield.getLayoutX() >= -4000){
            playfield.setLayoutX(playfield.getLayoutX() - 200);
        }else if (event.getDeltaY() > 0 && playfield.getLayoutX() <= 0){
            playfield.setLayoutX(playfield.getLayoutX() + 200);            
        }
    }
    
    /* Create Units */
    @FXML
    private void changePlayer(KeyEvent event) {
        if(event.getCode().equals(KeyCode.Z)){
            recentGame.changePlayer();
            recentPlayerLabel.setText(recentGame.getRecentPLayer().getName());
            connectMoneyLabel();
        }        
    }
    
    @FXML
    private void createMeele(ActionEvent event) { 
        
        if(recentGame.getRecentPLayer().createUnit("meele")){
            playfield.getChildren().add(recentGame.getRecentPLayer().getListUnits().get(recentGame.getRecentPLayer().getListUnits().size() - 1).getForm());
        }
    }
    
    @FXML
    private void createRange(ActionEvent event) {
        if(recentGame.getRecentPLayer().createUnit("range")){
            playfield.getChildren().add(recentGame.getRecentPLayer().getListUnits().get(recentGame.getRecentPLayer().getListUnits().size() - 1).getForm());
        }
    }
    
    @FXML
    private void createTank(ActionEvent event) {
        if(recentGame.getRecentPLayer().createUnit("tank")){
            playfield.getChildren().add(recentGame.getRecentPLayer().getListUnits().get(recentGame.getRecentPLayer().getListUnits().size() - 1).getForm());
        }
    }

    @FXML
    private void createHealer(ActionEvent event) {
        if(recentGame.getRecentPLayer().createUnit("healer")){
            playfield.getChildren().add(recentGame.getRecentPLayer().getListUnits().get(recentGame.getRecentPLayer().getListUnits().size() - 1).getForm());
        }
    }
    
//    Helper Methods
    private void connectBaseLabels() {
        basePlayer1.setText(String.valueOf(recentGame.getPlayer1().getBase().getHealth()));
        basePlayer2.setText(String.valueOf(recentGame.getPlayer2().getBase().getHealth()));

        recentGame.getPlayer1().getBase().healthProperty().addListener(((observable, oldValue, newValue) -> {
            basePlayer1.setText(String.valueOf(newValue));
        }));

        recentGame.getPlayer2().getBase().healthProperty().addListener(((observable, oldValue, newValue) -> {
            basePlayer2.setText(String.valueOf(newValue));
        }));
    }
    
    private void connectMoneyLabel() {
        moneyLabel.setText(String.valueOf(recentGame.getRecentPLayer().getMoney()));
        
        recentGame.getRecentPLayer().moneyProperty().addListener((observable, oldValue, newValue) -> {
            moneyLabel.setText(String.valueOf(newValue));
        });
    }
    
//  Getter / Setter

    public Scene getScene() {
        return scene;
        
    }

    public AnchorPane getPlayfield() {
        return playfield;
    }

    public Rectangle getBase1() {
        return base1;
    }

    public void setBase1(Rectangle base1) {
        this.base1 = base1;
    }

    public Rectangle getBase2() {
        return base2;
    }

    public void setBase2(Rectangle base2) {
        this.base2 = base2;
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    

    

    

    
    
}

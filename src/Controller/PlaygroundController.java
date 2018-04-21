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
import javafx.scene.image.ImageView;
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
    
//    Associations
    private AgeOfWar master;    
    private Game recentGame;
    
//    Variables
    private Scene scene;
    
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
    

//    Konstruktor    
    public void init(AgeOfWar master) { 
        
        scene = new Scene(root);
        this.master = master; 
        
        recentGame = new Game(this);        
//        connectBaseLabels();        
        connectMoneyLabel();
        
        recentPlayerLabel.setText(recentGame.getRecentPLayer().getName());
        
        System.out.println("Labels are connected!");
       
    }
    
//    FXML Methods
    @FXML
    private void handleScrollActions(ScrollEvent event) {
        
        
        if(event.isControlDown()){
            if(event.getDeltaY() < 0){
                recentGame.getPlayer1().getListUnits().forEach((unit) -> {
                    unit.setStepWidth(unit.getStepWidth() - 1);
                });
                recentGame.getPlayer2().getListUnits().forEach((unit) -> {
                    unit.setStepWidth(unit.getStepWidth() + 1);
                });
            }else if (event.getDeltaY() > 0){
                recentGame.getPlayer1().getListUnits().forEach((unit) -> {
                    unit.setStepWidth(unit.getStepWidth() + 1);
                });
                recentGame.getPlayer2().getListUnits().forEach((unit) -> {
                    unit.setStepWidth(unit.getStepWidth() - 1);
                });
            }
        }else{
            if(event.getDeltaY() < 0 && playfield.getLayoutX() >= -4000){
                playfield.setLayoutX(playfield.getLayoutX() - 200);
                if(playfield.getLayoutX() < root.getPrefWidth() - playfield.getPrefWidth()){
                    playfield.setLayoutX(root.getPrefWidth() - playfield.getPrefWidth());
                }
                
            }else if (event.getDeltaY() > 0 && playfield.getLayoutX() <= 0){
                playfield.setLayoutX(playfield.getLayoutX() + 200); 
                if(playfield.getLayoutX() > 0){
                    playfield.setLayoutX(0);
                }
            }
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
        recentGame.getRecentPLayer().buyUnit("meele");
    }
    
    @FXML
    private void createRange(ActionEvent event) {
        recentGame.getRecentPLayer().buyUnit("range");
    }
    
    @FXML
    private void createTank(ActionEvent event) {
        recentGame.getRecentPLayer().buyUnit("tank");
    }

    @FXML
    private void createHealer(ActionEvent event) {
        recentGame.getRecentPLayer().buyUnit("healer");
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

    public AnchorPane getRoot() {
        return root;
    }
    
    

    public Scene getScene() {
        return scene;
        
    }

    public AnchorPane getPlayfield() {
        return playfield;
    }

//    public ImageView getBase1() {
//        return base1;
//    }
//
//    public void setBase1(ImageView base1) {
//        this.base1 = base1;
//    }
//
//    public ImageView getBase2() {
//        return base2;
//    }
//
//    public void setBase2(ImageView base2) {
//        this.base2 = base2;
//    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    

    

    

    
    
}

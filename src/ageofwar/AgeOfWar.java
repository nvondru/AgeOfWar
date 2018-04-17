/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ageofwar;

import Controller.PlaygroundController;
import Controller.StartWindowController;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author nicolasvondru
 */
public class AgeOfWar extends Application {
    
    private PlaygroundController playgroundController;
    private StartWindowController startWindowController;
    private Stage mainStage;
    
    
    @Override
    public void start(Stage stage) throws Exception {
        mainStage = stage;
        mainStage.setResizable(false);
        FXMLLoader loaderStart = new FXMLLoader();   
        loaderStart.setLocation(getClass().getResource("/View/StartWindow.fxml"));
        
        loaderStart.load();
        startWindowController = loaderStart.getController(); 
        startWindowController.init(this);
        
        mainStage.setScene(startWindowController.getScene());
        mainStage.show();
    
    }
    
    public void loadPlayground() throws IOException{
        FXMLLoader loaderPlay = new FXMLLoader();   
        loaderPlay.setLocation(getClass().getResource("/View/Playground.fxml")); 
        
        loaderPlay.load();
        playgroundController = loaderPlay.getController(); 
        playgroundController.init(this);
        
        mainStage.setScene(playgroundController.getScene());
             
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

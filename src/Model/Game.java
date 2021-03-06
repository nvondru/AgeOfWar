/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.PlaygroundController;

/**
 *
 * @author nicolasvondru
 */
public class Game {
//    Associations
    private Player player1;
    private Player player2;        
    private PlaygroundController playgroundController;
    
//    Variables
    private Player recentPlayer; 
    
    
//    Constructor    
    public Game(PlaygroundController playgroundController) {
        this.playgroundController = playgroundController;
        player1 = new Player("Player 1", this);
        player2 = new Player("Player 2", this);        
        recentPlayer = player1;
    }
    
//    Methods
    public void changePlayer() {
        if(recentPlayer.equals(player1)){
            recentPlayer = player2;
        }else{
            recentPlayer = player1;
        }
    }
    
//    Getter/Setter

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
    public Player getRecentPLayer(){
        return recentPlayer;
    }

    public PlaygroundController getPlaygroundController() {
        return playgroundController;
    }
    

    
    
    
    
}

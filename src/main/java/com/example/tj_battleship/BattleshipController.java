package com.example.tj_battleship;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class BattleshipController implements Initializable {

    @FXML
    AnchorPane anchorPane;

    @FXML
    Rectangle rect;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Player player1 = new Player(1);
        Player player2 = new Player(2);

        Board player1Board = new Board(player1);
        player1Board.placeShips();

        Board player2Board = new Board(player2);
        player2Board.placeShips();

        while(true){

            boolean player1GameOver = player1.takeTurn(player2Board);
            if(player1GameOver){
                break;
            }
            player2Board.printObfuscatedBoard();

            boolean player2GameOver = player2.takeTurn(player1Board);
            if(player2GameOver){
                break;
            }
            player1Board.printObfuscatedBoard();
        }

        for(int i = 0; i < 10; i++){

            int xCoord = 50 + (100 * i);
            int yCoord = 50;

            Rectangle myRectangle = new Rectangle(xCoord, yCoord, 50, 50);
            anchorPane.getChildren().add(myRectangle);
        }
    }
}
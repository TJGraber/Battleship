package com.example.tj_battleship;

public class Player {

    int playerNumber;

    public Player(int _playerNum){
        playerNumber = _playerNum;
    }

    public int getPlayerNumber(){
        return playerNumber;
    }

    public boolean takeTurn(Board enemyBoard){
        System.out.println("Player " + playerNumber + "'s turn.");

        System.out.println("Enter the location of your guess.");
        enemyBoard.guess(Main.input.nextLine());

        if(enemyBoard.checkWin()){
            if(enemyBoard.player.getPlayerNumber() == 1){
                System.out.println("Player 2 wins!");
            }else{
                System.out.println("Player 1 wins!");
            }
            return true;
        }else{
            System.out.println("Player " + enemyBoard.player.getPlayerNumber() + " still has ships.");
        }
        return false;
    }
}

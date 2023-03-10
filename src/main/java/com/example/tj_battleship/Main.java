package com.example.tj_battleship;

import java.util.Scanner;

public class Main {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        //I might have users enter names for each player instead of just using player 1 and player 2


        //Initialize the players and have them place their ships
        Player player1 = new Player(1);
        Player player2 = new Player(2);

        Board player1Board = new Board(player1);
        player1Board.placeShips();

        Board player2Board = new Board(player2);
        player2Board.placeShips();

        //Start the game and run until a player wins by repeatedly guessing
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
    }

    //region printarray methods (mostly just for testing)
    public static void printArray(String[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }
    public static void printArray(int[] arr){
        for(int i = 0; i < arr.length; i++){
            System.out.println(arr[i]);
        }
    }
    public static void print2DArray(String[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                System.out.println(arr[i][j]);
            }
        }
    }
    public static void print2DArray(Cell[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[i].length; j++){
                System.out.println(arr[i][j]);
            }
        }
    }
    //endregion()
}

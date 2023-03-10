package com.example.tj_battleship;

public class Board {

    public static final String[] letters = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J"};
    public static final int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    //region Ships
    static final int destroyerSize = 2;
    static final int submarineSize = 3;
    static final int cruiserSize = 3;
    static final int battleshipSize = 4;
    static final int carrierSize = 5;

//    Ship destroyer;
//    Ship submarine;
//    Ship cruiser;
//    Ship battleship;
//    Ship carrier;

    //endregion

    Cell[][] board = new Cell[10][10];
    Ship[] shipList = new Ship[5];
    Player player;


    public Board(Player _player){
        player = _player;
        initializeBoard();
    }

    public Ship[] getShipList(){
        return shipList;
    }

    public void initializeBoard(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                board[i][j] = new Cell(letters[i] + numbers[j], "water", this);
            }
        }
    }

    //Creates ship objects and then calls the createShips method for each which begins that placing process
    public void placeShips(){
        shipList[0] = createShips("destroyer", destroyerSize);
        shipList[1] = createShips("submarine", submarineSize);
        shipList[2] = createShips("cruiser", cruiserSize);
        shipList[3] = createShips("battleship", battleshipSize);
        shipList[4] = createShips("carrier", carrierSize);
    }

    public String getLocationInput(String type){
        System.out.println("P" + player.getPlayerNumber() + " - Enter the center location of the " + type + " : ");
        return Main.input.nextLine().toUpperCase();
    }
    public char getAlignmentInput(String type){
        System.out.println("Would you like to place the " + type + " horizontally[h] or vertically[v]? ");
        return Character.toLowerCase(Main.input.nextLine().charAt(0));
    }

    //Gets user input for the location and alignment of a ship and then checks to see if the ship placement is valid
    public Ship createShips(String type, int size){

        Ship ship;

        while(true){
            String center = getLocationInput(type);
            char align = getAlignmentInput(type);

            if(validShipPlacement(center, align, size)){
                Cell[] cells = placeShip(center, size, align);
                ship = new Ship(false, size, align, cells, type);
                break;
            }else{
                System.out.println("Invalid Location.");
            }
        }
        return ship;
    }

    //Changes the tiles of a board where the ship is going to the ship property and returns a list of the cells of a ship
    public Cell[] placeShip(String location, int size, char alignment) {

        Cell[] cells = new Cell[size];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getLocation().equalsIgnoreCase(location)) {

                    board[i][j].setType("ship");
                    cells[0] = board[i][j];

                    if (alignment == 'h') {
                        if (size == 2) {
                            board[i][j + 1].setType("ship");
                            cells[1] = board[i][j + 1];
                        } else if (size == 3) {
                            board[i][j + 1].setType("ship");
                            board[i][j - 1].setType("ship");
                            cells[1] = board[i][j + 1];
                            cells[2] = board[i][j - 1];
                        } else if (size == 4) {
                            board[i][j + 1].setType("ship");
                            board[i][j - 1].setType("ship");
                            board[i][j + 2].setType("ship");
                            cells[1] = board[i][j + 1];
                            cells[2] = board[i][j - 1];
                            cells[3] = board[i][j + 2];
                        } else if (size == 5) {
                            board[i][j + 1].setType("ship");
                            board[i][j - 1].setType("ship");
                            board[i][j + 2].setType("ship");
                            board[i][j - 2].setType("ship");
                            cells[1] = board[i][j + 1];
                            cells[2] = board[i][j - 1];
                            cells[3] = board[i][j + 2];
                            cells[4] = board[i][j - 2];
                        }
                    } else if (alignment == 'v') {
                        if (size == 2) {
                            board[i + 1][j].setType("ship");
                            cells[1] = board[i + 1][j];
                        } else if (size == 3) {
                            board[i + 1][j].setType("ship");
                            board[i - 1][j].setType("ship");
                            cells[1] = board[i + 1][j];
                            cells[2] = board[i - 1][j];
                        } else if (size == 4) {
                            board[i + 1][j].setType("ship");
                            board[i - 1][j].setType("ship");
                            board[i + 2][j].setType("ship");
                            cells[1] = board[i + 1][j];
                            cells[2] = board[i - 1][j];
                            cells[3] = board[i + 2][j];
                        } else if (size == 5) {
                            board[i + 1][j].setType("ship");
                            board[i - 1][j].setType("ship");
                            board[i + 2][j].setType("ship");
                            board[i - 2][j].setType("ship");
                            cells[1] = board[i + 1][j];
                            cells[2] = board[i - 1][j];
                            cells[3] = board[i + 2][j];
                            cells[4] = board[i - 2][j];
                        }
                    }
                }
            }
        }
        return cells;
    }
    public boolean validShipPlacement(String center, char alignment, int shipSize){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j].getLocation().equals(center)){

                    if(alignment == 'v'){

                        if(shipSize == 2){

                            if(i + 1 >= board.length){
                                return false;
                            }
                        }
                        else if(shipSize == 3){

                            if(i - 1 < 0 || i + 1 >= board.length){
                                return false;
                            }
                        }else if(shipSize == 4){

                            if(i - 1 < 0 || i + 2 >= board.length){
                                return false;
                            }
                        } else if(shipSize == 5){

                            if(i - 2 < 0 || i + 2 >= board.length){
                                return false;
                            }
                        }
                    }

                    if(alignment == 'h'){
                        if(shipSize == 2){

                            if(j + 1 >= board.length){
                                return false;
                            }
                        }
                        else if(shipSize == 3){

                            if(j - 1 < 0 || j + 1 >= board.length){
                                return false;
                            }
                        }else if(shipSize == 4){

                            if(j - 1 < 0 || j + 2 >= board.length){
                                return false;
                            }
                        } else if(shipSize == 5){

                            if(j - 2 < 0 || j + 2 >= board.length){
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    //Finds the cell that you want to guess and calls the cellGuess function
    public void guess(String _location){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j].getLocation().equalsIgnoreCase(_location)){

                    board[i][j].cellGuess(board[i][j].getLocation());
                }
            }
        }
    }

    //Returns a boolean containing true or false if the board is clear of ships
    public boolean checkWin(){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j].getType().equals("ship")){
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard(){
        System.out.print("\n Player " + player.getPlayerNumber() + "'s board: \n  ");
        for(int i = 0; i < numbers.length; i++){
            System.out.print(numbers[i] + " ");
        }

        for(int i = 0; i < board.length; i++){
            System.out.println();
            System.out.print(letters[i] + " ");

            for(int j = 0; j < board[i].length; j++){
                System.out.print(board[i][j] + " ");
            }
        }
        System.out.println();
    }

    //Prints out a player's board but replaces all of the "s" or ship spaces with a dash.
    public void printObfuscatedBoard(){
        System.out.print("\nPlayer " + player.getPlayerNumber() + "'s board: \n  ");
        for(int i = 0; i < numbers.length; i++){
            System.out.print(numbers[i] + " ");
        }

        for(int i = 0; i < board.length; i++){
            System.out.println();
            System.out.print(letters[i] + " ");

            for(int j = 0; j < board[i].length; j++){
                if(board[i][j].getCellSymbol().equals("s")){
                    System.out.print( "- ");
                }else{
                    System.out.print(board[i][j] + " ");
                }
            }
        }
        System.out.println();
    }

    //Prints a specified cell
    public void printCellContains(String _location){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j].getLocation().equalsIgnoreCase(_location)){

                    System.out.println(board[i][j].getType());
                }
            }
        }
    }

    //Allows for modification of a specific cell
    public void setTile(String _location, String type){
        for(int i = 0; i < board.length; i++){
            for(int j = 0; j < board[i].length; j++){
                if(board[i][j].getLocation().equals(_location)){
                    board[i][j].setType(type);
                }
            }
        }
    }
}

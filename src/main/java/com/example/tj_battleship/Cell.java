package com.example.tj_battleship;

public class Cell {

    String location;
    String type;

    Board board;

    public Cell(String _location, String _type, Board _board){
        location = _location;
        type = _type;
        board = _board;
    }

    //region Getters
    public String getLocation(){
        return location;
    }
    public String getType(){
        return type;
    }
    //endregion

    //region Setters
    public void setLocation(String _location){
        location = _location;
    }
    public void setType(String _type){
        type = _type;
    }
    //endregion


    //Returns a symbol based on what is contained within the cell
    public String getCellSymbol(){
        if(type.equals("water")){
            return "-";
        }else if(type.equals("ship")){
            return "s";
        }else if(type.equals("hit")){
            return "x";
        }else if(type.equals("missed")){
            return "o";
        }
        return "error";
    }

    //Checks what is contained in a cell at the location guessed and sets that cell to the proper type
    public void cellGuess(String location){
        if(type.equals("water")){
            System.out.println("Missed!");
            setType("missed");
        }else if(type.equals("ship")){
            System.out.println("Hit!");

            Ship[] shipList = board.getShipList();

            for(int i = 0; i < shipList.length; i++){

                Cell[] cellList = shipList[i].getCells();

                for(int j = 0; j < cellList.length; j++){
                    if(cellList[j].getLocation().equalsIgnoreCase(location)){
                        shipList[i].removeCell();

                        if(shipList[i].getSpaces() <= 0){
                            System.out.println("Player " + board.player.getPlayerNumber() + "'s "  + shipList[i].getType() + " has been sunk.");
                            shipList[i].setSank(true);
                        }
                    }
                }
            }

            setType("hit");
        }else if(type.equals("missed") || type.equals("hit")){
            System.out.println("Already guessed!");
        }
    }

    public String toString(){
        return getCellSymbol();
    }
}

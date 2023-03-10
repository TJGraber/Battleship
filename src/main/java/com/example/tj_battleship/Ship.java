package com.example.tj_battleship;

public class Ship {

    String location;
    char alignment;
    boolean sank;
    int spaces;
    Cell[] cells;
    String type;


    //This class is mainly unused, but I want to keep it around for the future just in case
    //I should also probably say when a ship gets sunk


    public Ship(boolean _sank, int _spaces, char _alignment, Cell[] _cells, String _type){
        sank = _sank;
        spaces = _spaces;
        alignment = _alignment;
        cells = _cells;
        type = _type;
    }

    public String getLocation(){
        return location;
    }
    public char getAlignment(){
        return alignment;
    }
    public Cell[] getCells(){
        return cells;
    }
    public int getSpaces(){
        return spaces;
    }
    public String getType(){
        return type;
    }

    public void setSank(boolean _sank){
        sank = _sank;
    }
    public void removeCell(){
        spaces--;
    }
}

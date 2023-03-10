module com.example.tj_battleship {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tj_battleship to javafx.fxml;
    exports com.example.tj_battleship;
}
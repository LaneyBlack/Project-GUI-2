module bits.squad.project2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens bits.squad.project2 to javafx.fxml;
    exports bits.squad.project2;
}
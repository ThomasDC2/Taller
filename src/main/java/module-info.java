module co.edu.uniquindio.poo.taller {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.taller to javafx.fxml;
    exports co.edu.uniquindio.poo.taller;
}
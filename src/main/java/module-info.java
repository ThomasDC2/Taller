module co.edu.uniquindio.poo.taller {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.poo.taller to javafx.fxml;
    exports co.edu.uniquindio.poo.taller;
    exports co.edu.uniquindio.poo.taller.model;
    opens co.edu.uniquindio.poo.taller.model to javafx.fxml;
    exports co.edu.uniquindio.poo.taller.controller;
    opens co.edu.uniquindio.poo.taller.controller to javafx.fxml;
}
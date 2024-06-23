module dev.yuriyprogg.minesweeperjava {
    requires javafx.controls;
    requires javafx.fxml;


    opens dev.yuriyprogg.minesweeperjava to javafx.fxml;
    exports dev.yuriyprogg.minesweeperjava;
}
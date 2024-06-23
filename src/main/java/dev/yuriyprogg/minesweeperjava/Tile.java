package dev.yuriyprogg.minesweeperjava;

import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Objects;

public class Tile extends StackPane {
    private short x, y;
    private short bombs = 0;
    private boolean hasBomb, isFlag = false;
    private boolean isOpen = false;
    private boolean notVoid = true;
    private Rectangle field;
    private boolean secondField;


    private final Text text = new Text();

    public Tile(short tile_size, short x, short y, boolean hasBomb, boolean secondColor, MainApplication mainApplication) {
        this.x = x;
        this.y = y;
        this.hasBomb = hasBomb;

        secondField = secondColor;

        field = new Rectangle(tile_size, tile_size);
        field.setFill(secondField ? Color.LIMEGREEN : Color.LIME);

        text.setText(hasBomb ? "\uD83D\uDCA5" : "");
        text.setFont(Font.font(22));
        text.setFill(hasBomb ? Color.ORANGERED : Color.BLUE);
        text.setVisible(false);

        getChildren().addAll(field, text);
        setTranslateX(x * tile_size);
        setTranslateY(y * tile_size);

        setOnMouseClicked(e -> open(mainApplication));
    }

    public void open(MainApplication mainApplication) {
        if (isOpen) return;
        isOpen = true;

        text.setVisible(!text.getText().isEmpty());
        field.setFill(secondField ? Color.WHITESMOKE : Color.LIGHTGRAY);

        if (hasBomb) {
            System.out.println("Game over!");
            System.exit(0);
            return;
        }

        if (text.getText().isEmpty())
            mainApplication.getNeighbours(this).forEach(t -> t.open(mainApplication));
    }

    public short getX() {
        return x;
    }

    public void setX(short x) {
        this.x = x;
    }

    public short getY() {
        return y;
    }

    public void setY(short y) {
        this.y = y;
    }


    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }


    public short getBombs() {
        return bombs;
    }

    public void setBombs(short bombs) {
        this.bombs = bombs;
    }

    public boolean isHasBomb() {
        return hasBomb;
    }

    public void setHasBomb(boolean hasBomb) {
        this.hasBomb = hasBomb;
    }

    public String getText() {
        return text.getText();
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    public boolean isNotVoid() {
        return notVoid;
    }
}

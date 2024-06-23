package dev.yuriyprogg.minesweeperjava;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainApplication extends Application {
    private static final short
    TILE_SIZE = 50,
    WIDTH = 800,
    HEIGHT = 600,
    X_TILE = WIDTH / TILE_SIZE,
    Y_TILE = HEIGHT / TILE_SIZE;

    public Scene scene;

    private Tile[][] grid = new Tile[X_TILE][Y_TILE];

    public Parent createContent() {
        Pane root = new Pane();
        root.setPrefSize(WIDTH, HEIGHT);

        boolean secondField = false;

        for (short y = 0; y < Y_TILE; y++) {
            for (short x = 0; x < X_TILE; x++) {
                Tile tile = new Tile(TILE_SIZE, x, y, Math.random() < 0.2, secondField, this);
                secondField = !secondField;
                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
            secondField = !secondField;
        }

        for (short y = 0; y < Y_TILE; y++) {
            for (short x = 0; x < X_TILE; x++) {
                Tile tile = grid[x][y];
                if (tile.isHasBomb()) continue;

                short bombs = (short) getNeighbours(tile).stream().filter(Tile::isHasBomb).count();
                if (bombs > 0) tile.setText(String.valueOf(bombs));
            }
        }

        return root;
    }

    public List<Tile> getNeighbours(Tile tile) {
        List<Tile> neighbours = new ArrayList<>();

        byte[][] points = {
                {-1, -1},
                {-1, 0},
                {-1, 1},
                {0, -1},
                {0, 1},
                {1, 0},
                {1, 1},
        };



        for (byte[] point : points) {
            short dx = point[0], dy = point[1];
            short newX = (short) (tile.getX() + dx);
            short newY = (short) (tile.getY() + dy);


            if (newX >= 0 && newX < X_TILE && newY >= 0 && newY < Y_TILE)
                neighbours.add(grid[newX][newY]);
        }
        return neighbours;
    }

    @Override
    public void start(Stage stage) {
        scene = new Scene(createContent());
        stage.setResizable(false);
        stage.setTitle("Minesweeper");
        stage.setScene(scene);
        stage.show();
    }

    public void newGame() {
        scene = new Scene(createContent());
    }

    public static void main(String[] args) {
        launch();
    }
}
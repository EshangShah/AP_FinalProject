package com.example.snakesandladder;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile (int x,int y){
        setWidth(DiceRoll.tileSize);
        setHeight(DiceRoll.tileSize);
        setFill(Color.PINK);
        setStroke(Color.BLACK);
    }
}

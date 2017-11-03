/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project.othello.breton.viewFx;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import project.othello.breton.model.PlayerColor;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Gabriel Breton - 43397
 */
class Tile extends StackPane {
    
    private final PlayerColor pawnColor;
    
    Tile(int i, int j, PlayerColor color) {
        this.pawnColor = color;
        Rectangle border = new Rectangle(75, 75);

        if ((i == 0) || (j == 0)) {
            border.setFill(null);
            border.setStroke(null);
        } else {
            border.setFill(Color.GREEN);
            border.setStroke(Color.BLACK);
        }

        setAlignment(Pos.CENTER);
        getChildren().addAll(border);
    }

    public PlayerColor getPawnColor() {
        return pawnColor;
    }
}

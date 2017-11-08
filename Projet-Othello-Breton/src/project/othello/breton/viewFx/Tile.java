package project.othello.breton.viewFx;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import project.othello.breton.model.PlayerColor;

/**
 * This class provides methods to creates tiles and place pawn on it.
 *
 * @author Gabriel Breton - 43397
 */
class Tile extends StackPane {

    private Pawn pawn;

    /**
     * Creates a new tile.
     *
     * @param i the number of the row.
     * @param j the number of the column.
     */
    Tile(int i, int j, PlayerColor color) {
        Rectangle border = new Rectangle(75, 75);
        pawn = null;

        if ((i == 0) || (j == 0)) {
            border.setFill(null);
            border.setStroke(null);
        } else {
            if (color == PlayerColor.RED) {
                border.setFill(Color.RED);
            } else {
                border.setFill(Color.GREEN);
            }
            border.setOpacity(0.5);
            border.setStroke(Color.BLACK);
        }

        setAlignment(Pos.CENTER);
        getChildren().addAll(border);
    }

    /**
     * Gives the pawn placed on a tile.
     *
     * @return the pawn on the tile.
     */
    Pawn getPawn() {
        return pawn;
    }

    /**
     * Places a pawn on a tile.
     *
     * @param pawn the pawn to place on the tile.
     */
    void setPawn(Pawn pawn) {
        this.pawn = pawn;
    }

}

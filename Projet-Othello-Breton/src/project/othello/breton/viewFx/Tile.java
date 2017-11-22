package project.othello.breton.viewFx;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import project.othello.breton.model.OthelloImpl;
import project.othello.breton.model.PlayerColor;

/**
 * This class provides methods to creates tiles and place pawn on it.
 *
 * @author Gabriel Breton - 43397
 */
class Tile extends StackPane {

    private Pawn pawn;
    int row;
    int col;
    Rectangle border;
    
    /**
     * Creates a new tile.
     *
     * @param row the number of the row.
     * @param col the number of the column.
     */
    Tile(int row, int col, PlayerColor color, OthelloImpl game) {
        border = new Rectangle(75, 75);
        pawn = null;
        this.row = row;
        this.col = col;
        
        if ((row == 0) || (col == 0)) {
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
     //   setMouseEvent(game);
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
//
//    void setMouseEvent(OthelloImpl game) {
//        EventHandler<MouseEvent> eventHandler = (MouseEvent e) -> {
//            if (game.ge)
//        };
//        addEventFilter(MouseEvent.MOUSE_CLICKED, eventHandler);
//    }
}

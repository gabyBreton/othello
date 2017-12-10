package project.othello.breton.viewFx;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import project.othello.breton.model.OthelloImpl;
import project.othello.breton.model.GameColor;

/**
 * This class provides methods to creates tiles and place pawn on it.
 *
 * @author Gabriel Breton - 43397
 */
class Tile extends StackPane {

    private final Pawn pawn;
    int row;
    int col;
    Rectangle border;

    /**
     * Creates a new tile.
     *
     * @param row the number of the row.
     * @param col the number of the column.
     */
    Tile(int row, int col, GameColor color, OthelloImpl game) {
        border = new Rectangle(75, 75);
        pawn = null;
        this.row = row;
        this.col = col;

        setTileRendering(row, col, color);

        setAlignment(Pos.CENTER);
        setMouseEvent(game);
        getChildren().addAll(border);
    }

    /**
     * Sets the color, stroke and opacity of the tiles.
     * 
     * @param row the number of the line where is the tile.
     * @param col the number of the column where is the tile.
     * @param color to know if there is a wall on this tile, identified by
     * the red GameColor.
     */
    private void setTileRendering(int row, int col, GameColor color) {
        if ((row == 0) || (col == 0)) {
            border.setFill(null);
            border.setStroke(null);
        } else {
            if (color == GameColor.RED) {
                border.setFill(Color.DARKCYAN);
            } else {
                border.setFill(Color.GREY);
            }
            border.setOpacity(0.5);
            border.setStroke(Color.BLACK);
            border.setStrokeWidth(3);
        }
    }

    /**
     * Creates and sets the differents mouse events for a tile.
     * 
     * @param game the current session of Othello.
     */
    void setMouseEvent(OthelloImpl game) {
        InnerShadow innerShadow = new InnerShadow();
        innerShadow.setChoke(0.4);

        whenMouseEnter(innerShadow);
        whenMouseExit();
        whenMousePressed(game);
    }

    /**
     * Creates the event for when the mouse enter the tile. This event is to
     * set an inner shadow effect on the tile.
     * 
     * @param innerShadow the inner shadow to apply on the tile.
     */
    private void whenMouseEnter(InnerShadow innerShadow) {
        EventHandler<MouseEvent> mouseEnter = (MouseEvent e) -> {
            border.setEffect(innerShadow);
        };
        addEventFilter(MouseEvent.MOUSE_ENTERED, mouseEnter);
    }

    /**
     * Creates the event for when the mouse exit the tile. This event is to
     * remove the inner shadow effect.
     */
    private void whenMouseExit() {
        EventHandler<MouseEvent> mouseExit = (MouseEvent e) -> {
            border.setEffect(null);
        };
        addEventFilter(MouseEvent.MOUSE_EXITED, mouseExit);
    }
    
    /**
     * Creates the event for when the mouse is pressed on the tile. This event 
     * is to place a pawn or to place a wall.
     * 
     * @param game the current session of Othello.
     */
    private void whenMousePressed(OthelloImpl game) {
        addEventFilter(MouseEvent.MOUSE_PRESSED, (event) -> {
            if (col > 0 && row > 0) {
                if (event.isPrimaryButtonDown()) {
                    game.getCurrentPlayer()
                        .executePlayGameStrategy(row - 1, col - 1);
                } else if (event.isSecondaryButtonDown()) {
                    game.getCurrentPlayer()
                        .executeWallGameStrategy(row - 1, col - 1);                }
            }
        });
    }
    
    void refresh(OthelloImpl game) {
        setMouseEvent(game);
    }
}

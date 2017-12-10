package project.othello.breton.viewFx;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import project.othello.breton.model.GameColor;

/**
 * This class provides methods to create and set pawns.
 *
 * @author Gabriel Breton - 43397
 */
class Pawn extends StackPane {

    private final GameColor color;

    /**
     * Creates a new pawn.
     *
     * @param color the color of the owner of the pawn.
     */
    Pawn(GameColor color) {
        this.color = color;

        Ellipse bckgrnd = makeEllipse(24, 20, 2.25, 0, 6);
        Ellipse ellipse = makeEllipse(24, 20, 2.25, 0, 1) ;
        setColorsPawn(color, bckgrnd, ellipse);

        getChildren().addAll(bckgrnd, ellipse);
    }

    /**
     * Creates and sets an ellipse.
     *
     * @return the new ellipse.
     */
    private Ellipse makeEllipse(int radiusX, int radiusY, double strokeWidth, 
                                int translateX, int translateY) {
        Ellipse ellipse = new Ellipse(radiusX, radiusY);
        ellipse.setStrokeWidth(strokeWidth);
        ellipse.setTranslateX(translateX);
        ellipse.setTranslateY(translateY);
        return ellipse;
    }

    /**
     * Sets the colors of a pawn, depending on the owner.
     *
     * @param color the color of the owner of the pawn.
     * @param bckgrnd the lower part of the pawn.
     * @param ellipse the upper part of the pawn.
     */
    private void setColorsPawn(GameColor color, Ellipse bckgrnd,
                               Ellipse ellipse) {
        if (color == GameColor.BLACK) {
            setFillAndStroke(bckgrnd, ellipse, Color.WHITE, Color.BLACK);
        } else {
            setFillAndStroke(bckgrnd, ellipse, Color.BLACK, Color.WHITE);
        }
    }

    /**
     * Sets the fill and the stroke of a pawn.
     * 
     * @param bckgrnd the ellipse that is in the background.
     * @param ellipse the ellipse that is on the upper side.
     * @param bckgrndColor the color of the background ellipse.
     * @param ellipseColor the color of the upper ellipse.
     */
    private void setFillAndStroke(Ellipse bckgrnd, Ellipse ellipse, 
                                  Color bckgrndColor, Color ellipseColor) {
        bckgrnd.setFill(bckgrndColor);
        bckgrnd.setStroke(bckgrndColor);
        ellipse.setFill(ellipseColor);
        ellipse.setStroke(ellipseColor);
    }
}

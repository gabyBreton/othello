package project.othello.breton.viewFx;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import project.othello.breton.model.PlayerColor;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class Pawn extends StackPane {

    private final PlayerColor color;

    public Pawn(PlayerColor color) {
        this.color = color;

        Ellipse bckgrnd = makeBackground();
        Ellipse ellipse = makeEllipse();
        setColorsPawn(color, bckgrnd, ellipse);

        getChildren().addAll(bckgrnd, ellipse);
    }

    private Ellipse makeEllipse() {
        Ellipse ellipse = new Ellipse(24, 20);
        ellipse.setStrokeWidth(2.25);
        ellipse.setTranslateX(0);
        ellipse.setTranslateY(1);
        return ellipse;
    }

    private Ellipse makeBackground() {
        Ellipse bckgrnd = new Ellipse(24, 20);
        bckgrnd.setStrokeWidth(2.25);
        bckgrnd.setTranslateX(0);
        bckgrnd.setTranslateY(6);
        return bckgrnd;
    }

    private void setColorsPawn(PlayerColor color, Ellipse bckgrnd, Ellipse ellipse) {
        if (color == PlayerColor.BLACK) {
            bckgrnd.setFill(Color.WHITE);
            bckgrnd.setStroke(Color.WHITE);
            ellipse.setFill(Color.BLACK);
            ellipse.setStroke(Color.BLACK);
        } else {
            bckgrnd.setFill(Color.BLACK);
            bckgrnd.setStroke(Color.BLACK);
            ellipse.setFill(Color.WHITE);
            ellipse.setStroke(Color.WHITE);
        }
    }
}

package project.othello.breton.viewFx;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import project.othello.breton.model.GameColor;

/**
 * This class permits to create a pawn.
 *
 * @author Gabriel Breton - 43397
 */
public class Pawn extends StackPane {

    private final GameColor color;

    /**
     * Creates a new pawn.
     *
     * @param color the color of the owner of the pawn.
     */
    public Pawn(GameColor color) {
        this.color = color;

        Ellipse bckgrnd = makeBackground();
        Ellipse ellipse = makeEllipse();
        setColorsPawn(color, bckgrnd, ellipse);

        getChildren().addAll(bckgrnd, ellipse);
    }

    /**
     * Creates the upper part of the pawn.
     *
     * @return the upper part of the pawn.
     */
    private Ellipse makeEllipse() {
        Ellipse ellipse = new Ellipse(24, 20);
        ellipse.setStrokeWidth(2.25);
        ellipse.setTranslateX(0);
        ellipse.setTranslateY(1);
        return ellipse;
    }

    /**
     * Creates the lower part of the pawn.
     *
     * @return the lower part of the pawn.
     */
    private Ellipse makeBackground() {
        Ellipse bckgrnd = new Ellipse(24, 20);
        bckgrnd.setStrokeWidth(2.25);
        bckgrnd.setTranslateX(0);
        bckgrnd.setTranslateY(6);
        return bckgrnd;
    }

    /**
     * Sets the colors of a pawn.
     *
     * @param color the color of the owner of the pawn.
     * @param bckgrnd the lower part of the pawn.
     * @param ellipse the upper part of the pawn.
     */
    private void setColorsPawn(GameColor color, Ellipse bckgrnd,
            Ellipse ellipse) {
        if (color == GameColor.BLACK) {
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

    GameColor getColor() {
        return color;
    }

//    private ScaleTransition createFlipEffect() {
//        Pawn back;
//
//        ScaleTransition stHideFront = new ScaleTransition(Duration.millis(1500), this);
//        stHideFront.setFromX(1);
//        stHideFront.setToX(0);
//
//        if (this.color == PlayerColor.BLACK) {
//            back = new Pawn(PlayerColor.WHITE);
//        } else {
//            back = new Pawn(PlayerColor.BLACK);
//        }
//        back.setScaleX(0);
//
//        ScaleTransition stShowBack = new ScaleTransition(Duration.millis(1500), back);
//        stShowBack.setFromX(0);
//        stShowBack.setFromY(1);
//
//        stHideFront.setOnFinished(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                stShowBack.play();
//            }
//        });
//
//        return stHideFront;
//    }
}

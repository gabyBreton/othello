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
    
    
    private Color switchColor() {
        Color fillColor;
        
        switch (color){
            case BLACK:
                fillColor = Color.BLACK;
                break;
            case WHITE:
                fillColor = Color.WHITE;
                break;
            default:
                fillColor = Color.GREY;
                break;
        }

        return fillColor;
    }
    
    public Pawn(PlayerColor color) {
        this.color = color;
        
        Ellipse bckgrnd = new Ellipse(75 * 0.3125, 75 * 0.3125);
        bckgrnd.setFill(Color.BLACK);
        bckgrnd.setStroke(Color.BLACK);
        bckgrnd.setStrokeWidth(75 * 0.03);
        
        Ellipse ellipse = new Ellipse(75 * 0.3125, 75 * 0.3125);
        ellipse.setFill(switchColor());
        ellipse.setStroke(Color.BLACK);
        ellipse.setStrokeWidth(75 * 0.03);
        
        getChildren().addAll(bckgrnd, ellipse);
    }
}

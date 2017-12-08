package project.othello.breton.viewFx;

import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import project.othello.breton.model.OthelloImpl;

/**
 * This class provides methods to create and use the final layout for when the
 * party is over.
 *
 * @author Gabriel Breton - 43397
 */
class FinalLayout extends VBox {

    private Text winner;
    private Text gameOver;
    private GridPane buttonsZone;

    FinalLayout(OthelloImpl game, String whoIsWinner) {
        super();

        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(4.0f);
        dropShadow.setOffsetY(6.0f);
        dropShadow.setColor(Color.rgb(0, 0, 0, 1.0));        
        
        gameOver = new Text("Game Over");
        gameOver.setEffect(dropShadow);
        gameOver.setCache(true);
        gameOver.setId("gameOver");
        gameOver.setFill(Color.rgb(168, 73, 73, 0.8));

        if (whoIsWinner.matches("Equality !")) {
            winner = new Text(whoIsWinner);
        } else {
            winner = new Text(whoIsWinner + " win !");
        }
        
        winner.setEffect(dropShadow);
        winner.setCache(true);
        winner.setFill(Color.rgb(168, 73, 73, 0.8));
        winner.setId("winner");

        VBox infoBox = new VBox();
        infoBox.getChildren().addAll(gameOver, winner);
        infoBox.setSpacing(30);
        infoBox.setPadding(new Insets(170, 0, 100, 230));
        infoBox.setMinWidth(1000);
        
        Image soil = new Image(getClass()
                .getResource("soil.jpg").toExternalForm());
        ImageView ivSoil = new ImageView(soil);
        
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(ivSoil, infoBox);
        
        getChildren().addAll(stackPane);

        setId("finalLayout");
    }

}

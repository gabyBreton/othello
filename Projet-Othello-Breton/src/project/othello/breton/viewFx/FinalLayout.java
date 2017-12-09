package project.othello.breton.viewFx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import project.othello.breton.model.OthelloImpl;

/**
 * This class provides methods to create and use the final layout for when the
 * party is over. The final scene offer the restart or quit game and displays 
 * the score and who wins.
 *
 * @author Gabriel Breton - 43397
 */
class FinalLayout extends VBox {

    private Text winner;
    private Text gameOver;
    private GridPane buttonsZone;
    
    private Button btnRestart;
    private Button btnQuit;
    
    /**
     * Creates the final scene where is displayed who wins, the score and where
     * the user can restart or quit the game.
     * 
     * @param game the current session of Othello.
     * @param whoIsWinner the pseudo of the winner, or the String "Equality !"
     */
    FinalLayout(OthelloImpl game, String whoIsWinner) {
        super();
        setId("finalLayout");

        VBox infoBox = makeInfoBox(whoIsWinner);        
        ImageView ivSoil = placeBackgroundImage();
        makeButtonsZone(game);
        
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(ivSoil, infoBox, buttonsZone);
        
        getChildren().addAll(stackPane);
    }

    /**
     * Place the background image.
     * 
     * @return the background image view.
     */
    private ImageView placeBackgroundImage() {
        Image soil = new Image(getClass()
                               .getResource("soil.jpg")
                               .toExternalForm());
        ImageView ivSoil = new ImageView(soil);
        
        return ivSoil;
    }

    /**
     * Creates the info box and its components where is displayed the winner 
     * and the two scores.
     * 
     * @param whoIsWinner the pseudo of the winner.
     * @return the new info box.
     */
    private VBox makeInfoBox(String whoIsWinner) {
        DropShadow dropShadow = makeDropShadow();        
        makeGameOver(dropShadow);
        makeWinner(whoIsWinner, dropShadow);
        
        VBox infoBox = new VBox();
        setInfoBox(infoBox);
       
        return infoBox;
    }

    /**
     * Creates the drop shadow effect that will be applied on the differents 
     * text of the scene.
     * 
     * @return the new drop shadow effect.
     */
    private DropShadow makeDropShadow() {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(4.0f);
        dropShadow.setOffsetY(6.0f);
        dropShadow.setColor(Color.rgb(0, 0, 0, 1.0));
        return dropShadow;
    }

    /**
     * Creates the displayed text game over.
     * 
     * @param dropShadow a drop shadow effect apply on the game over text.
     */
    private void makeGameOver(DropShadow dropShadow) {
        gameOver = new Text("Game Over");
        gameOver.setEffect(dropShadow);
        gameOver.setCache(true);
        gameOver.setId("gameOver");
        gameOver.setFill(Color.rgb(168, 73, 73, 0.8));
    }    
    
    /**
     * Set the elements spacing, padding and the size of the infobox.
     * 
     * @param infoBox the info box where is displayed the winner and the two 
     * scores.
     */
    private void setInfoBox(VBox infoBox) {
        infoBox.setSpacing(30);
        infoBox.setPadding(new Insets(170, 0, 100, 230));
        infoBox.setMinWidth(1000);
        infoBox.getChildren().addAll(gameOver, winner);
    }

    /**
     * Creates the winner text displayed.
     * 
     * @param whoIsWinner the pseudo of the winner, or the text "Equality !".
     * @param dropShadow a drop shadow effect apply on the winner text.
     */
    private void makeWinner(String whoIsWinner, DropShadow dropShadow) {
        determineWinner(whoIsWinner);        
        winner.setEffect(dropShadow);
        winner.setCache(true);
        winner.setFill(Color.rgb(168, 73, 73, 0.8));
        winner.setId("winner");
    }

    /**
     * Determine the winner text to display based on the equality or not.
     * 
     * @param whoIsWinner the pseudo of the winner, or the text "Equality !".
     */
    private void determineWinner(String whoIsWinner) {
        if (whoIsWinner.matches("Equality !")) {
            winner = new Text(whoIsWinner);
        } else {
            winner = new Text(whoIsWinner + " win !");
        }
    }
 
    private void makeButtonsZone(OthelloImpl game) {
        buttonsZone = new GridPane();
        makeRestart(game);
        makeQuit();
        buttonsZone.setHgap(80);
        buttonsZone.setPadding(new Insets(500, 0, 0, 310));
        buttonsZone.add(btnRestart, 0, 0);
        buttonsZone.add(btnQuit, 1, 0);
    }
        
    /**
     * Creates the restart button, used to restart the game.
     */
    private void makeRestart(OthelloImpl game) {
        btnRestart = makeAButton("Restart", "buttonFinal", (event) -> {
            game.start(8, 8);
        });
    }

    /**
     * Creates the quit button, used to exit the game.
     */
    private void makeQuit() {
        btnQuit = makeAButton("Quit", "buttonFinal", (event) ->{
            System.exit(0);
        }); 
        btnQuit.setMinWidth(150);
    }
    
    /**
     * Creates a button.
     * 
     * @param text the text displayed on the button.
     * @param id the id of the button, for the css styling.
     * @param value the action activated when clicked.
     * @return the new button.
     */
    private Button makeAButton(String text, String id,
                               EventHandler<ActionEvent> value) {
        Button button = new Button(text);
        button.setMaxWidth(150);
        button.setMinHeight(50);
        button.setId(id);
        button.setOnAction(value);
       
        return button;
    }
    
}

package project.othello.breton.viewFx;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.othello.breton.model.OthelloImpl;

/**
 * This class creates and sets the layout for the start interface.
 *
 * @author Gabriel Breton - 43397
 */
class StartLayout extends BorderPane {

    private TextFieldLimited tfdPseudoBlack;
    private TextFieldLimited tfdPseudoWhite;
    
    /**
     * Creates the start interface, that will contains the name of the game,
     * the box to enter the pseudos, and the start button.
     * 
     * @param game the current session of Othello.
     * @param primaryStage the primary stage that will contains the game scene.
     * @param gameScene the game scene.
     * @param gameRoot the game interface.
     */
    StartLayout(OthelloImpl game, Stage primaryStage, Scene gameScene, 
                GameLayout gameRoot) {
        super();
        setId("startPane");

        makeGameNameBox();
        makePseudoZone();
        makeBtnPlay(game, primaryStage, gameScene, gameRoot);
    }   
    
    /**
     * Creates the box where is displayed the name of the game.
     */
    private void makeGameNameBox() {
        VBox gameNameBox = new VBox();
        gameNameBox.setPadding(new Insets(100, 50, 50, 300));
        
        Label gameName = new Label("Ot.hello() ");
        gameName.setId("gameName");
        
        gameNameBox.getChildren().add(gameName);
        setTop(gameNameBox);
    }
    
    /**
     * Creates the zone that will contains the two input textfield to enter
     * the pseudos of the players.
     */
    private void makePseudoZone() {
        VBox pseudosBox = makePseudosBox();

        tfdPseudoBlack = makeAPseudoTfd("pseudoInput", "Black player");
        tfdPseudoWhite = makeAPseudoTfd("pseudoInput", "White player");
        tfdPseudoBlack.se
        
        pseudosBox.getChildren().addAll(tfdPseudoBlack, tfdPseudoWhite);
        setCenter(pseudosBox);
    } 
    
    /**
     * Creates and set the spacing and padding of the box for the pseudos 
     * inputs.
     * 
     * @return the new pseudo box.
     */
    private VBox makePseudosBox() {
        VBox pseudosBox = new VBox();
        pseudosBox.setSpacing(40);
        pseudosBox.setPadding(new Insets(50, 0, 0, 350));
      
        return pseudosBox;
    }

    /**
     * Creates a text field where to enter the pseudo of a player. The text 
     * field have a limitation on the length of the input set on 10.
     * 
     * @param id the id of this text field.
     * @param promptText the prompt text of this text field.
     * @return the new pseudo text field.
     */
    private TextFieldLimited makeAPseudoTfd(String id, String promptText) {
        TextFieldLimited tfdPseudo = new TextFieldLimited();
        tfdPseudo.setId(id);
        tfdPseudo.setPromptText(promptText);
        tfdPseudo.setMaxWidth(300);
      
        return tfdPseudo;
    }

    /**
     * Creates the button play.
     * 
     * @param game the current session of Othello.
     * @param primaryStage the primary stage that will contains the game scene.
     * @param gameScene the game scene.
     * @param gameRoot the game interface.
     */
    private void makeBtnPlay(OthelloImpl game, Stage primaryStage, 
                             Scene gameScene, GameLayout gameRoot) {
        
        VBox buttonsBox = new VBox();
        buttonsBox.setPadding(new Insets(50, 50, 120, 425));
        
        Button btnPlay = makeAButton("Play", "button", 150, 50, (event) -> {
            makeActionPlay(game, primaryStage, gameScene, gameRoot); 
        });
        
        buttonsBox.getChildren().add(btnPlay);
        setBottom(buttonsBox);
    }

    /**
     * Creates a button.
     * 
     * @param text the text displayed on the button.
     * @param id the id of the button, for the css styling.
     * @param value the action activated when clicked.
     * @return the new button.
     */
    private Button makeAButton(String text, String id, int maxWidth,
                               int minHeight, 
                               EventHandler<ActionEvent> value) {
        Button button = new Button(text);
        button.setMaxWidth(maxWidth);
        button.setMinHeight(minHeight);
        button.setId(id);
        button.setOnAction(value);
       
        return button;
    }
    
    /**
     * Creates the action for when the button play is clicked. This action 
     * launch the game scene.
     * 
     * @param game the current session of Othello.
     * @param primaryStage the primary stage that will contains the game scene.
     * @param gameScene the game scene.
     * @param gameRoot the game interface. 
     */
    private void makeActionPlay(OthelloImpl game, Stage primaryStage, 
                                Scene gameScene, GameLayout gameRoot) {
    
        if ((tfdPseudoBlack.getLength() == 0) 
             || (tfdPseudoWhite.getLength() == 0)) {
            makeAlertPseudo();
        } else {
            game.makePlayers(tfdPseudoBlack.getText(), tfdPseudoWhite.getText());
            gameRoot.getScoreInfos().setPseudos(tfdPseudoBlack.getText(),
                    tfdPseudoWhite.getText());
            setSizesNewScene(primaryStage, gameScene);
        }

    }

    /**
     * Creates a warning dialog box, if at least one pseudo is not entered.
     */
    private void makeAlertPseudo() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Pseudos Warning");
        alert.setHeaderText(null);
        alert.setContentText("Pseudos should contains at least one "
                             + "character");
        alert.showAndWait();
    }

    /**
     * Sets the sizes of the new scene.
     * 
     * @param primaryStage the primary stage that will contains the game scene.
     * @param gameScene the game scene.
     */
    private void setSizesNewScene(Stage primaryStage, Scene gameScene) {
        primaryStage.setScene(gameScene);
        primaryStage.setMinWidth(1300);
        primaryStage.setMaxWidth(1300);
        primaryStage.setMinHeight(820);
        primaryStage.setMaxHeight(820);
    }
}
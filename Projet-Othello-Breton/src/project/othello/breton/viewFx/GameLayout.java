package project.othello.breton.viewFx;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import project.othello.breton.model.OthelloImpl;

/**
 * This class provides methods to create and set the layout of the game scene.
 * 
 * @author Gabriel Breton - 43397
 */
class GameLayout extends BorderPane {

    private AudioClip music;
    
    private ScoresInfos scoreInfos;
    private BoardPane board;
    private GridPane buttonsZone;
    
    private GameInfo gameInfo;

    private Button btnAbandon;
    private Button btnPass;
    private Button btnRestart;

    private final Stage primaryStage; 
    private Scene finalScene;
    private FinalLayout finalRoot;
    
    /**
     * Creates a new game interface, that contains the board, the scores 
     * informations, the buttons and the game informations.
     * 
     * @param primaryStage the primary stage that contains the scene.
     * @param game the current session of Othello.
     * @param scoreInfos the elements of the score informations.
     * @param board the board of the game.
     */
    GameLayout(Stage primaryStage, OthelloImpl game, ScoresInfos scoreInfos, 
               BoardPane board) {
        super();
        this.primaryStage = primaryStage;
        makeLeftZone(board, scoreInfos);
        makeCenterZone(game);
        makeRightZone(game);     
        makeAndStartPlayer();
        setId("gamePane");
    }

    /**
     * Creates the left zone of the game interface, where are the score 
     * informations and the game board.
     * 
     * @param board the board of the game.
     * @param scoreInfos the score informations elements.
     */
    private void makeLeftZone(BoardPane board, ScoresInfos scoreInfos) {
        this.board = board;
        this.scoreInfos = scoreInfos;
        scoreInfos.setAlignment(Pos.CENTER_RIGHT);
        VBox boardScoreBox = makeBoardScoreBox(board, scoreInfos);
        setLeft(boardScoreBox);
    }

    /**
     * Creates and set the box that will contains the score informations and
     * the board.
     * 
     * @param board the board of the game.
     * @param scoreInfosthe score informations elements.
     * @return the new box.
     */
    private VBox makeBoardScoreBox(BoardPane board, ScoresInfos scoreInfos) {
        VBox boardScoreBox = new VBox();
        boardScoreBox.setSpacing(30);
        boardScoreBox.getChildren().addAll(board, scoreInfos);
        return boardScoreBox;
    }
    
    /**
     * Creates the center zone that contains the buttons.
     * 
     * @param game the current session of Othello.
     */
    private void makeCenterZone(OthelloImpl game) {
        makeButtonsZone(game);
        setCenter(buttonsZone);
    }

    /**
     * Creates the zone that will contains the buttons.
     *
     * @param game the current session of Othello.
     */
    private void makeButtonsZone(OthelloImpl game) {
        buttonsZone = new GridPane();
        setPaddingAndGapBtnZone();
        makeButtons(game);
        addElementsBtnZone();
    }

    /**
     * Set the paddings and gaps of the buttons zone.
     */
    private void setPaddingAndGapBtnZone() {
        buttonsZone.setPadding(new Insets(225, 0, 0, 50));
        buttonsZone.setHgap(10);
        buttonsZone.setVgap(15);
    }

    /**
     * Creates the three buttons: Abandon, Pass and Restart.
     * 
     * @param game the current session of Othello.
     */
    private void makeButtons(OthelloImpl game) {
        makeAbandon(game);
        makePass(game);
        makeRestart(game);
    }
    
    /**
     * Creates the abandon button, used to abandon the game.
     *
     * @param game the current session of Othello.
     */
    private void makeAbandon(OthelloImpl game) {
        btnAbandon = makeAButton("Abandon", "button", (event) -> {
            makeAlertAbandon(game);
        });
    }

    /**
     * Creates the pass button, used to pass a turn.
     * 
     * @param game the current session of Othello.
     */
    private void makePass(OthelloImpl game) {
        btnPass = makeAButton("Pass", "button", (event) -> {
            game.pass();
        });
    }
    
    /**
     * Creates the restart button, used to restart the game.
     */
    private void makeRestart(OthelloImpl game) {
        btnRestart = makeAButton("Restart", "button", (event) -> {
//            game.start(8, 8);
//            refresh(game);
            makeAlertRestart();
        });
    }

    /**
     * Creates an error dialog to explains that the restart functionality is 
     * still available.
     */
    private void makeAlertRestart() {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Restart");
        alert.setHeaderText("Oups ! This functionality is still not "
                            + "available");
        alert.setContentText("Use the old fashioned way: quit and "
                             + "relaunch.");
        alert.showAndWait();
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

    /**
     * Makes the abandon alert window, displayer when the button alert is 
     * clicked.
     * 
     * @param game the current session of Othello.
     */
    private void makeAlertAbandon(OthelloImpl game) {
        Alert alertAbandon = creatAndSetAlertAbandon();
        ButtonType btnTypeSure = new ButtonType("Yes I give up ...");
        ButtonType btnTypeNo = new ButtonType("No !");

        alertAbandon.getButtonTypes().setAll(btnTypeNo, btnTypeSure);
        applyChoiceConfirmation(game, alertAbandon, btnTypeSure);
    }    

    /**
     * Creates and sets the alert abandon window.
     * 
     * @return the new alert abandon window.
     */
    private Alert creatAndSetAlertAbandon() {
        Alert alertAbandon = new Alert(Alert.AlertType.CONFIRMATION);
        alertAbandon.setTitle("Abandon");
        alertAbandon.setHeaderText(null);
        alertAbandon.setContentText("Do you really want to give up ?");
      
        return alertAbandon;
    }    

    /**
     * Applies the choice confirmation to abandon or not. If yes is clicked, 
     * the game scene load the final scene that display the winner and scores,
     * else the user alert window disappear and the user can continue to play.
     * 
     * @param game the current session of Othello.
     * @param alertAbandon the alert abandon window.
     * @param btnTypeSure the button to confirm the abandon.
     */
    private void applyChoiceConfirmation(OthelloImpl game, Alert alertAbandon, 
                                         ButtonType btnTypeSure) {
        Optional<ButtonType> result = alertAbandon.showAndWait();
        if (result.get() == btnTypeSure) {
            makeFinalScene(game);
            primaryStage.setScene(finalScene);
            primaryStage.setMinWidth(1000);
            primaryStage.setMaxWidth(1000);
        } else {
            alertAbandon.close();
        }
    }
    
    /**
     * Creates the right zone, that will contains the game informations 
     * elements such as the history table, progress indication tools and a 
     * counter of the number of walls on the board.
     * 
     * @param game the current session of Othello.
     */
    private void makeRightZone(OthelloImpl game) {
        this.gameInfo = new GameInfo(game);
        setMargin(gameInfo, new Insets(0, 50, 50, 0));
        setRight(gameInfo);
    }
    
    /**
     * Add the elements of the buttons zone to it.
     */
    private void addElementsBtnZone() {
        buttonsZone.add(btnPass, 0, 3);
        buttonsZone.add(btnRestart, 0, 5);
        buttonsZone.add(btnAbandon, 0, 7);
    }

    /**
     * Gives the score informations elements.
     * 
     * @return the score informations elements.
     */
    ScoresInfos getScoreInfos() {
        return scoreInfos;
    }

    /**
     * Creates and sets the final scene and its parent. This scene displays who 
     * wins and the scores.
     * 
     * @param game the current session of Othello.
     */
    private void makeFinalScene(OthelloImpl game) {
        finalRoot = new FinalLayout(game.getWinner());
        finalScene = makeScene(finalRoot);
    }

    /**
     * Creates a scene and set it with the style sheet.
     * 
     * @param root the parent of the scene.
     * @return 
     */
    private Scene makeScene(Parent root) {
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(
                this.getClass().getResource("style.css").toExternalForm());
        return scene;
    }
    
    /**
     * Refresh the elements of the game interface when an action is proceed,
     * such as placing a wall, a pawn or passing.
     * 
     * @param game the current session of Othello.
     */
    public void refresh(OthelloImpl game) {
        scoreInfos.refresh(game.getScoreBlack(), game.getScoreWhite(),
                           game.getCurrentColor());
        board.refresh(game);        
        gameInfo.refresh(game);
        if(game.isOver()) {
            makeFinalScene(game);            
            primaryStage.setScene(finalScene);
            primaryStage.setMinWidth(1000);
            primaryStage.setMaxWidth(1000);           
        }
    }
    
    private void makeAndStartPlayer() {
        music = new AudioClip(this.getClass()
                                  .getResource("LayaProjectANewDayRemix.mp3")
                                  .toString());
        music.play();
    }
}

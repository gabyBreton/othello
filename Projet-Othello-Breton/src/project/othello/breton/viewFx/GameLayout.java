package project.othello.breton.viewFx;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.othello.breton.model.OthelloImpl;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class GameLayout extends BorderPane {

    private final ScoresInfos scoreInfos;
    private final BoardPane board;
    private GridPane rightSideZone;
    private GridPane gridPaneWalls;

    private Button btnAbandon;
    private Button btnPass;
    private Button btnRestart;

    private Label nameCptWalls;
    private Label nbWalls;

    private ProgressBar pBar;
    private ProgressIndicator pIndicator;

    GameLayout(OthelloImpl game, ScoresInfos scoreInfos, BoardPane board) {
        super();
        setId("gamePane");

        this.board = board;
        this.scoreInfos = scoreInfos;
        rightSideZone = makeRightSideZone(game);

        setLeft(board);
        setCenter(rightSideZone);
    }

    /**
     * Creates the side zone with the scores infos and the buttons.
     *
     * @param scoreInfos the scores infos.
     * @param btnAbandon the button abandon.
     * @return
     */
    private GridPane makeRightSideZone(OthelloImpl game) {
        rightSideZone = new GridPane();
        setPaddingAndGap();
        makeElementsOfRightSideZone(game);
        addElements();
        return rightSideZone;
    }

    private void setPaddingAndGap() {
        rightSideZone.setPadding(new Insets(75, 50, 0, 50));
        setMinWidth(200);
        rightSideZone.setHgap(10);
        rightSideZone.setVgap(15);
    }

    private void makeElementsOfRightSideZone(OthelloImpl game) {
        makeButtons(game);
        makeProgressBar(game);
        makeProgressIndicator(game);
        //      scoreInfos.setGridLinesVisible(true); // FOR DEBUG!
        makeGridPaneWalls();
    }

    private void makeButtons(OthelloImpl game) {
        btnAbandon = makeAButton("Abandon", "button", (event) -> {
            makeAlertAbandon();
        });
        btnPass = makeAButton("Pass", "button", (event) -> {
            game.pass();
        });
        btnRestart = makeAButton("Restart", "button", (event) -> {
            //restart
        });
    }

    private void makeProgressBar(OthelloImpl game) {
        pBar = new ProgressBar();
        pBar.setMinWidth(250);
        pBar.setMinHeight(10);
        pBar.setId("pBar");
        //pBar.setStyle("-fx-accent: black;");
        refreshProgressBar(game.getScoreBlack(), game.getCounterPawnsOnBoard());
    }
    
    private void refreshProgressBar(int scoreBlack, int scoreTotal) {
        pBar.setProgress((double) scoreBlack / (double) scoreTotal);
    }

    private void makeProgressIndicator(OthelloImpl game) {
        pIndicator = new ProgressIndicator();
        pIndicator.setId("pIndicator");
        //pIndicator.setStyle("-fx-accent: grey;");
        pIndicator.setMinSize(100, 100);
        refreshProgressIndicator(game.getCounterPawnsOnBoard());
    }
    
    private void refreshProgressIndicator(int nbPawnsAndWalls) {
        pIndicator.setProgress((double)nbPawnsAndWalls / 64);
    }
    
    private void makeGridPaneWalls() {
        gridPaneWalls = new GridPane();
        gridPaneWalls.setGridLinesVisible(true);
        gridPaneWalls.setHgap(10);
        gridPaneWalls.setVgap(15);

        nameCptWalls = makeLabelWithId("Walls:", "nbWall");
        nbWalls = makeLabelWithId(String.valueOf(board.getCounterWallsOnBoard()), "nbWall");
        gridPaneWalls.add(nbWalls, 1, 0);
        gridPaneWalls.add(nameCptWalls, 0, 0);
    }

    private void makeAlertAbandon() {
        Alert alertAbandon = setAlertAbandon();
        ButtonType buttonTypeSure = new ButtonType("Yes I give up ...");
        ButtonType buttonTypeNo = new ButtonType("No !");

        alertAbandon.getButtonTypes().setAll(buttonTypeNo, buttonTypeSure);

        makeResultConfirmation(alertAbandon, buttonTypeSure);
    }

    private void makeResultConfirmation(Alert alertAbandon, ButtonType buttonTypeSure) {
        Optional<ButtonType> result = alertAbandon.showAndWait();
        if (result.get() == buttonTypeSure) {
            // ... display final score etc ...
        } else {
            alertAbandon.close();
        }
    }

    private Alert setAlertAbandon() {
        Alert alertAbandon = new Alert(Alert.AlertType.CONFIRMATION);
        alertAbandon.setTitle("Abandon");
        alertAbandon.setHeaderText(null);
        alertAbandon.setContentText("Is it the moment to give up ?");
        return alertAbandon;
    }

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
     * Makes a label and set its Id.
     *
     * @return the label.
     */
    private Label makeLabelWithId(String text, String id) {
        Label label = new Label();
        label.setText(text);
        label.setId(id);
        return label;
    }

    private void addElements() {
        rightSideZone.setGridLinesVisible(true); // FOR DEBUG!
        rightSideZone.add(scoreInfos, 0, 0);
        rightSideZone.add(gridPaneWalls, 0, 1);
        rightSideZone.add(btnPass, 0, 3);
        rightSideZone.add(btnRestart, 0, 5);
        rightSideZone.add(btnAbandon, 0, 7);
        rightSideZone.add(pIndicator, 0, 9);
        rightSideZone.add(pBar, 0, 11);
    }

    ScoresInfos getScoreInfos() {
        return scoreInfos;
    }

    BoardPane getBoard() {
        return board;
    }

    public void refresh(OthelloImpl game) {
        nbWalls.setText(String.valueOf(game.getCounterWallsOnBoard()));
        refreshProgressBar(game.getScoreBlack(), game.getCounterPawnsOnBoard());
        refreshProgressIndicator(game.getCounterPawnsOnBoard() 
                                 + game.getCounterWallsOnBoard());
    }

}

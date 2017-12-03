package project.othello.breton.viewFx;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private GridPane centerZone;
    private GridPane gridPaneWalls;
    private VBox rightZone;

    private Button btnAbandon;
    private Button btnPass;
    private Button btnRestart;

    private Label nameCptWalls;
    private Label nbWalls;

    private ProgressBar pBar;
    private ProgressIndicator pIndicator;
    
    private ActionsHistoryTable table;

    GameLayout(OthelloImpl game, ScoresInfos scoreInfos, BoardPane board) {
        super();
        setId("gamePane");

        this.board = board;
        this.scoreInfos = scoreInfos;
        makeCenterZone(game);
        rightZone = new VBox();
        rightZone.setPadding(new Insets(70, 10, 10, 10));
        rightZone.setSpacing(40);
        table = new ActionsHistoryTable();
        
        setLeft(board);
        setCenter(centerZone);
        rightZone.getChildren().addAll(scoreInfos, table);
        setRight(rightZone);
    }

    /**
     * Creates the side zone with the scores infos and the buttons.
     *
     * @param scoreInfos the scores infos.
     * @param btnAbandon the button abandon.
     * @return
     */
    private void makeCenterZone(OthelloImpl game) {
        centerZone = new GridPane();
        setPaddingAndGap();
        makeElementsOfCenter(game);
        addElements();
    }

    private void setPaddingAndGap() {
        centerZone.setPadding(new Insets(75, 50, 0, 50));
        setMinWidth(200);
        centerZone.setHgap(10);
        centerZone.setVgap(15);
    }

    private void makeElementsOfCenter(OthelloImpl game) {
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
        refreshProgressBar(game.getScoreBlack(), game.getCounterPawnsOnBoard());
    }
    
    private void refreshProgressBar(int scoreBlack, int scoreTotal) {
        pBar.setProgress((double) scoreBlack / (double) scoreTotal);
    }

    private void makeProgressIndicator(OthelloImpl game) {
        pIndicator = new ProgressIndicator();
        pIndicator.setId("pIndicator");
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
        //button.setPadding(new Insets(1000, 1000, 1000, 700));
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
        centerZone.setGridLinesVisible(true); // FOR DEBUG!
        centerZone.add(gridPaneWalls, 0, 1);
        centerZone.add(btnPass, 0, 3);
        centerZone.add(btnRestart, 0, 5);
        centerZone.add(btnAbandon, 0, 7);
        centerZone.add(pIndicator, 0, 9);
        centerZone.add(pBar, 0, 11);
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

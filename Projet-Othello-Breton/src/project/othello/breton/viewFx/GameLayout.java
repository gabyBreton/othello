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
        rightSideZone.setHgap(10);
        rightSideZone.setVgap(15);
    }

    private void makeElementsOfRightSideZone(OthelloImpl game) {
        makeButtons(game);
  //      scoreInfos.setGridLinesVisible(true); // FOR DEBUG!
        makeGridPaneWalls();
    }
    
    private void makeButtons(OthelloImpl game) {
        btnAbandon = makeAButton("Abandon", "btnAbandon", (event) -> {
            makeAlertAbandon();
        });
        btnPass = makeAButton("Pass", "btnPass", (event) -> {
            game.pass(); 
        });
        btnRestart = makeAButton("Restart", "btnRestart", (event) -> {
            //restart
        });
    }

    private void makeGridPaneWalls() {
        gridPaneWalls = new GridPane();
        gridPaneWalls.setGridLinesVisible(true);
        gridPaneWalls.setHgap(10);
        gridPaneWalls.setVgap(15);
        
        nameCptWalls = makeLabelWithId("Walls:", "cptWall");
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
        rightSideZone.add(btnPass, 0, 10);
        rightSideZone.add(btnRestart, 0, 12);
        rightSideZone.add(btnAbandon, 0, 14);
    }

    ScoresInfos getScoreInfos() {
        return scoreInfos;
    }

    BoardPane getBoard() {
        return board;
    }
    
    public void refresh(int nbWallsOnBoard) {
        nbWalls.setText(String.valueOf(nbWallsOnBoard));
    }
   
}

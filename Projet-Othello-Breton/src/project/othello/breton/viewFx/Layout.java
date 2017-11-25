package project.othello.breton.viewFx;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import project.othello.breton.model.OthelloImpl;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class Layout extends BorderPane {

    private final ScoresInfos scoreInfos;
    private final BoardPane board;
    private GridPane rightSideZone;
    private GridPane paneWalls;
    
    private Button btnAbandon;
    private Button btnPass;
    private Button btnRestart;

    private Label cptWall;
    private Label nbWall;

    Layout(OthelloImpl game, ScoresInfos scoreInfos, BoardPane board) {
        super();
        setId("pane");
        
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
        scoreInfos.setGridLinesVisible(true); // FOR DEBUG!
        
        cptWall = makeCptWall();
        nbWall = makeNbWall();
    }

    private void makeButtons(OthelloImpl game) {
        btnAbandon = makeButtonAbandon();
        btnPass = makeButtonPass(game);
        btnRestart = makeButtonRestart();
    }

    /**
     * Creates a button to abandon the game.
     *
     * @return the abandon button.
     */
    private Button makeButtonAbandon() {
        btnAbandon = new Button("Abandon");
        btnAbandon.setMaxWidth(150);
        btnAbandon.setMinHeight(50);
        btnAbandon.setId("btnAbandon");
        btnAbandon.setOnAction((ActionEvent t) -> {
            makeAlertAbandon();
        });
        return btnAbandon;
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

    /**
     * Creates a button to abandon the game.
     *
     * @return the abandon button.
     */
    private Button makeButtonPass(OthelloImpl game) {
        btnPass = new Button("Pass");
        btnPass.setMaxWidth(150);
        btnPass.setMinHeight(50);
        btnPass.setId("btnPass");
        btnPass.setOnAction((ActionEvent t) -> {
            game.pass(); 
        });
        return btnPass;
    }

    /**
     * Creates a button to restart the game.
     *
     * @return the restart button.
     */
    private Button makeButtonRestart() {
        btnRestart = new Button("Restart");
        btnRestart.setMaxWidth(150);
        btnRestart.setMinHeight(50);
        btnRestart.setId("btnRestart");
        btnRestart.setOnAction((ActionEvent t) -> {
            //make the game restart
        });
        return btnRestart;
    }    
    
    /**
     * Make the label who say how many walls there is on the board.
     *
     * @return the label who say how many walls there is on the board.
     */
    private Label makeCptWall() {
        cptWall = new Label();
        cptWall.setText("Walls:");
        cptWall.setId("cptWall");
        return cptWall;
    }

    /**
     * Make the view of the number of wall on the board.
     *
     * @return the label for the number of wall.
     */
    private Label makeNbWall() {
        nbWall = new Label();
        nbWall.setText(String.valueOf(board.getCounterWallsOnBoard()));
        nbWall.setId("nbWall");
        return nbWall;
    }

    private void addElements() {
        rightSideZone.setGridLinesVisible(true); // FOR DEBUG!
        rightSideZone.add(scoreInfos, 0, 0);
        rightSideZone.add(cptWall, 0, 5);
        rightSideZone.add(nbWall, 0, 6);
        rightSideZone.add(btnPass, 0, 10);
        rightSideZone.add(btnRestart, 0, 12);
        rightSideZone.add(btnAbandon, 0, 14);
    }

    public ScoresInfos getScoreInfos() {
        return scoreInfos;
    }

    public BoardPane getBoard() {
        return board;
    }
}

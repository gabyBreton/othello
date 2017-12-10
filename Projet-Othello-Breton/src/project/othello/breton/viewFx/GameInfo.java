package project.othello.breton.viewFx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import project.othello.breton.model.OthelloImpl;

/**
 * This class provides methods to create and update the game informations 
 * elements such as the history table, the progression indicators and the 
 * number of walls on the board.
 * 
 * @author Gabriel Breton - 43397
 */
public class GameInfo extends VBox {
    
    private ActionsHistoryTable table;
    private ProgressBar pBar;
    private ProgressIndicator pIndicator;
    private Label nameCptWalls;
    private Label nbWalls;    
    private GridPane gridPaneWalls;
   
    /**
     * Creates a new game informations box.
     * 
     * @param game the current session of Othello.
     */
    GameInfo(OthelloImpl game) {
        super();
        setSizes();
        makeElements(game);
        addElements();
    }

    /**
     * Sets the sizes of the game informations box.
     */
    private void setSizes() {
        setMaxWidth(382);
        setSpacing(30);
    }

    /**
     * Makes the elements of the game informations box as said for the 
     * constructor.
     * 
     * @param game the current session of Othello.
     */
    private void makeElements(OthelloImpl game) {
        makeHistoryTable(game);
        makeProgressBar(game);
        makeProgressIndicator(game);
        makeGridPaneWalls(game);
    }

    /**
     * Add the elements of the game informations box to it.
     */
    private void addElements() {
        VBox boxPIndicator = makeBoxPIndicator();
        
        getChildren().addAll(boxPIndicator, pBar, table, gridPaneWalls);
        setSpacing(20);
    }

    /**
     * Makes the box that will contains the progress indicator.
     * 
     * @return the new box.
     */
    private VBox makeBoxPIndicator() {
        VBox boxPIndicator = new VBox();
        boxPIndicator.setMargin(pIndicator, new Insets(70, 20, 0, 20));
        boxPIndicator.getChildren().add(pIndicator);
        return boxPIndicator;
    }    
    
    /**
     * Creates the history table view that shows all the past actions of the 
     * current session of Othello such as:
     * Placing a pawn, a wall, starting the game or passing.
     * 
     * @param game the current session of Othello.
     */
    private void makeHistoryTable(OthelloImpl game) {
        table = new ActionsHistoryTable(game);
        table.setMaxWidth(327);
    }
    
    /**
     * Creates the progress bar that shows the proportion of black and white 
     * pawns on the board.
     * 
     * @param game the current session of Othello.
     */
    private void makeProgressBar(OthelloImpl game) {
        pBar = new ProgressBar();
        pBar.setMinWidth(366);
        pBar.setMinHeight(10);
        pBar.setId("pBar");
        refreshProgressBar(game.getScoreBlack(), game.getCounterPawnsOnBoard());
    }
    
    /**
     * Refresh the progress bar based on the new postions and colors of the 
     * pawns on the board.
     * 
     * @param scoreBlack the score of the black player.
     * @param nbPawnsOnBoard the number of pawns on the board.
     */
    private void refreshProgressBar(int scoreBlack, int nbPawnsOnBoard) {
        pBar.setProgress((double) scoreBlack / (double) nbPawnsOnBoard);
    }
    
    /**
     * Creates a new progress indicator that represents the percentage of the
     * filling of the board.
     * 
     * @param game the current session of Othello.
     */
    private void makeProgressIndicator(OthelloImpl game) {
        pIndicator = new ProgressIndicator();
        pIndicator.setId("pIndicator");
        pIndicator.setMinSize(100, 100);
        refreshProgressIndicator(game.getCounterPawnsOnBoard());
    }
    
    /**
     * Refresh the progress indicator based on the number of pawns and walls 
     * on the board and the total number of cells.
     * 
     * @param nbPawnsAndWalls the number of pawns and walls on the board.
     */
    private void refreshProgressIndicator(int nbPawnsAndWalls) {
        pIndicator.setProgress((double) nbPawnsAndWalls / 64);
    }    
    
    /**
     * Makes the gridpane that contains the number of walls on board.
     * 
     * @param game the current session of Othello.
     */
    private void makeGridPaneWalls(OthelloImpl game) {
        gridPaneWalls = new GridPane();
        setGapsGridPaneWalls();
        makeElementsGridPaneWalls(game);
        addElementsGridPaneWalls();
        gridPaneWalls.setAlignment(Pos.CENTER);
    }

    /**
     * Sets the gaps of the gridpane walls.
     */
    private void setGapsGridPaneWalls() {
        gridPaneWalls.setHgap(10);
        gridPaneWalls.setVgap(15);
    }

    /**
     * Makes the elements of the gridpane walls.
     * 
     * @param game the current session of Othello.
     */
    private void makeElementsGridPaneWalls(OthelloImpl game) {
        nameCptWalls = makeLabel("Walls:", "nbWall");
        nbWalls = makeLabel(String.valueOf(game.getCounterWallsOnBoard()), 
                            "nbWall");
    }

    /**
     * Makes a label and set its id.
     *
     * @return the new label.
     */
    private Label makeLabel(String text, String id) {
        Label label = new Label();
        label.setText(text);
        label.setId(id);
        return label;
    }   
    
    /**
     * Add the elements of the gripane walls to it.
     */
    private void addElementsGridPaneWalls() {
        gridPaneWalls.add(nbWalls, 1, 0);
        gridPaneWalls.add(nameCptWalls, 0, 0);
    }
 
    /**
     * Refresh the content of the game informations box.
     * 
     * @param game the current session of Othello.
     */
    void refresh(OthelloImpl game) {
        nbWalls.setText(String.valueOf(game.getCounterWallsOnBoard()));
        refreshProgressBar(game.getScoreBlack(), game.getCounterPawnsOnBoard());
        refreshProgressIndicator(game.getCounterPawnsOnBoard()
                                 + game.getCounterWallsOnBoard());
        table.refresh(game);
    }
}

package project.othello.breton.viewFx;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Border;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import project.othello.breton.model.OthelloImpl;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class GameInfo extends VBox {
    
    private final ActionsHistoryTable table;
    private ProgressBar pBar;
    private ProgressIndicator pIndicator;
    private Label nameCptWalls;
    private Label nbWalls;    
    private GridPane gridPaneWalls;
    
    GameInfo(OthelloImpl game) {
        super();
        setMaxWidth(327);
        setSpacing(30);
//        String cssLayout = "-fx-border-color: red;\n" +
//                   "-fx-border-insets: 5;\n" +
//                   "-fx-border-width: 3;\n" +
//                   "-fx-border-style: dashed;\n";
//        setStyle(cssLayout);
        table = new ActionsHistoryTable(game);
        table.setMaxWidth(327);
        makeProgressBar(game);
        makeProgressIndicator(game);
        makeGridPaneWalls(game);        
        addElements();
    }
    
    private void makeProgressBar(OthelloImpl game) {
        pBar = new ProgressBar();
        pBar.setMinWidth(327);
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
        pIndicator.setProgress((double) nbPawnsAndWalls / 64);
    }    
    
    private void makeGridPaneWalls(OthelloImpl game) {
        gridPaneWalls = new GridPane();
        gridPaneWalls.setHgap(10);
        gridPaneWalls.setVgap(15);
        
        nameCptWalls = makeLabelWithId("Walls:", "nbWall");
        nbWalls = makeLabelWithId(String.valueOf(game.getCounterWallsOnBoard()), "nbWall");
        gridPaneWalls.add(nbWalls, 1, 0);
        gridPaneWalls.add(nameCptWalls, 0, 0);
        gridPaneWalls.setAlignment(Pos.CENTER);
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
    
    public void refresh(OthelloImpl game) {
        nbWalls.setText(String.valueOf(game.getCounterWallsOnBoard()));
        refreshProgressBar(game.getScoreBlack(), game.getCounterPawnsOnBoard());
        refreshProgressIndicator(game.getCounterPawnsOnBoard()
                + game.getCounterWallsOnBoard());
        table.refresh(game);
    }
    
    void addElements() {
        VBox boxPIndicator = new VBox();
        boxPIndicator.setMargin(pIndicator, new Insets(70, 20, 0, 20));
        boxPIndicator.getChildren().add(pIndicator);
        
        getChildren().addAll(boxPIndicator, pBar, table, gridPaneWalls);
        setSpacing(20);
    }
}

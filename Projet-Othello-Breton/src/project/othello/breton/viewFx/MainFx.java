package project.othello.breton.viewFx;

import java.util.Optional;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import project.othello.breton.model.OthelloImpl;
import project.othello.breton.util.Observer;

/**
 * This class is the main class of the GUI implementation of the Othello game.
 *
 * @author Gabriel Breton - 43397
 */
public class MainFx extends Application implements Observer {

    private OthelloImpl game;
    private final int rows = 8;
    private final int columns = 8;
    
    private ScoresInfos scoreInfos;
    private BoardPane board;
    private BorderPane root;
    
    /**
     * Starts and sets all the components of the game.
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        setLayyoutPrimaryStage(primaryStage);

        root = makeLayout();

        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(
                this.getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setLayyoutPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle("Othello");
        primaryStage.setMinWidth(1000);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMinHeight(750);
        primaryStage.setMaxHeight(750);
    }

    /**
     * Creates a game to creates the differents elements of the GUI.
     *
     * @return a ready to use root.
     */
    private BorderPane makeLayout() {
        game = new OthelloImpl(rows, columns);
        scoreInfos = new ScoresInfos(game);
        board = new BoardPane(game);
        root = new Layout(game, scoreInfos, board); 

        game.addObserver(this);
        return root;
    }
    
    /**
     * Launches the game.
     *
     * @param args non used.
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void update() {
        scoreInfos.refresh(game.getScoreBlack(), game.getScoreWhite());
        board.refresh(game);
    }
}

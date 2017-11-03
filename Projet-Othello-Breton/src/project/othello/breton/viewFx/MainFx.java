package project.othello.breton.viewFx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import project.othello.breton.model.OthelloImpl;

/**
 * This class is the main class of the GUI implementation of the Othello game.
 * 
 * @author Gabriel Breton - 43397
 */
public class MainFx extends Application {
    private OthelloImpl game;

    /**
     * Starts and sets all the components of the game.
     * @param primaryStage 
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Othello");
        primaryStage.setMaxWidth(1000);
        primaryStage.setMaxHeight(750);

        BorderPane root = makeLayout();
        
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Creates a game to creates the differents elements of the GUI.
     * 
     * @return a ready to use root.
     */
    private BorderPane makeLayout() {
        BorderPane root = new BorderPane();
        root.setId("pane");
     
        game = new OthelloImpl(8, 8);
        GridPane board = new BoardPane(game).getBoard();
        GridPane scoreInfos = new ScoresInfos(game).getSidePane();
        //    scoreInfos.setGridLinesVisible(true); // FOR DEBUG !
        
        root.setLeft(board);
        root.setCenter(scoreInfos);
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
}

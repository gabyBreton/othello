package project.othello.breton.viewFx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import project.othello.breton.model.OthelloImpl;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class MainFx extends Application {

    private OthelloImpl game;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Othello");

        game = new OthelloImpl(8, 8);

        BorderPane root = new BorderPane();
        root.setId("pane");

        GridPane board = new BoardPane(game).getBoard();
        GridPane scoreInfos = new ScoresInfos(game).getSidePane();
    //    scoreInfos.setGridLinesVisible(true);
        
        root.setLeft(board);
        root.setCenter(scoreInfos);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

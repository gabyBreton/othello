package project.othello.breton.viewFx;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        primaryStage.setMinWidth(1000);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMinHeight(750);
        primaryStage.setMaxHeight(750);
        
        game = new OthelloImpl(8, 8);
        VBox root = new VBox();

        GridPane sideZone = new ScoresInfos(game).getSidePane();
        GridPane board = new BoardPane().getBoard();
        
        sideZone.setAlignment(Pos.TOP_RIGHT);
        root.getChildren().addAll(board, sideZone);
        
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}


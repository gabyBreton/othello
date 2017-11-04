package project.othello.breton.viewFx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
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
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Othello");
        primaryStage.setMinWidth(1000);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMinHeight(750);
        primaryStage.setMaxHeight(750);

        BorderPane root = makeLayout();

        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(
                     this.getClass().getResource("style.css").toExternalForm());

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
  //      scoreInfos.setGridLinesVisible(true); // FOR DEBUG !
        
        Button btnQuit = makeButtonQuit();
        GridPane sideZone = makeSideZone(scoreInfos, btnQuit);
        
        root.setLeft(board);
        root.setCenter(sideZone);
        return root;
    }

    /**
     * Creates a button to quit the game.
     * 
     * @return the quit button.
     */
    private Button makeButtonQuit() {
        Button btnQuit = new Button("Quit");
        btnQuit.setMaxWidth(100);
        btnQuit.setMinHeight(50);
        btnQuit.setId("btnQuit");
        btnQuit.setOnAction((ActionEvent t) -> {
            System.exit(0);
        });
        return btnQuit;
    }
    
    /**
     * Creates the side zone with the scores infos and the buttons.
     * 
     * @param scoreInfos the scores infos.
     * @param btnQuit the button quit.
     * @return 
     */
    private GridPane makeSideZone(GridPane scoreInfos, Button btnQuit) {
        GridPane sideZone = new GridPane();
        sideZone.setPadding(new Insets(75, 50, 0, 50));
        sideZone.setHgap(10);
        sideZone.setVgap(15);
        //  sideZone.setGridLinesVisible(true); // FOR DEBUG!
        sideZone.add(scoreInfos, 0, 0);
        sideZone.add(btnQuit, 0, 10);
        return sideZone;
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

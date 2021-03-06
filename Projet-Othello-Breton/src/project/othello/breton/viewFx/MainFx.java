package project.othello.breton.viewFx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
    private StartLayout startRoot;
    private GameLayout gameRoot;

    /**
     * Sets and starts all the components of the game.
     *
     * @param primaryStage the stage that will contains the differents scene.
     */
    @Override
    public void start(Stage primaryStage) {
        setLayoutPrimaryStage(primaryStage);

        Scene startScene = makeStartScene(primaryStage);
        primaryStage.setScene(startScene);

        primaryStage.show();
    }

    /**
     * Sets the layout of the primary stage.
     * 
     * @param primaryStage the stage that will contains the differents scene.
     */
    private void setLayoutPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle("Othello");
        primaryStage.setMinWidth(1000);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMinHeight(750);
        primaryStage.setMaxHeight(750);
    }

    /**
     * Creates and sets the start scene, and all the elements that are needed 
     * for that.
     * 
     * @param primaryStage the stage that will contains the differents scene.
     * @return the new start scene.
     */
    private Scene makeStartScene(Stage primaryStage) {
        Scene gameScene = makeGameScene(primaryStage);

        startRoot = new StartLayout(game, primaryStage, gameScene, gameRoot);
        Scene startScene = makeScene(startRoot);
        return startScene;
    }

    /**
     * Creates and sets the start scene, and all the elements that are needed 
     * for that.
     * 
     * @param primaryStage the stage that will contains the differents scene.
     * @return the new game scene.
     */
    private Scene makeGameScene(Stage primaryStage) {
        game = new OthelloImpl(rows, columns);
        game.addObserver(this);
        
        makeGameRoot(primaryStage);
        Scene gameScene = makeScene(gameRoot);
        return gameScene;
    }

    /**
     * Creates and sets the game root that is the parent for the game scene and
     * contains the elements of the game interface.
     * 
     * @param primaryStage the stage that will contains the differents scene.
     */
    private void makeGameRoot(Stage primaryStage) {
        scoreInfos = new ScoresInfos(game);
        board = new BoardPane(game);
        gameRoot = new GameLayout(primaryStage, game, scoreInfos, board);
        gameRoot.setMinHeight(750);
        gameRoot.setMinWidth(1000);
    }

    /**
     * Creates a scene and set it the style sheet.
     * 
     * @param root the parent of the scene.
     * @return the new scene.
     */
    private Scene makeScene(Parent root) {
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(
                this.getClass().getResource("style.css").toExternalForm());
        return scene;
    }

    /**
     * Launches the game.
     *
     * @param args non used.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Updates the game interface that will refresh the differents components 
     * that needs to be.
     */
    @Override
    public void update() {
        gameRoot.refresh(game);
    }
}

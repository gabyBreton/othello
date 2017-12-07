package project.othello.breton.viewFx;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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
    private FinalLayout finalRoot;

    private TextField tfdPseudoBlack;
    private TextField tfdPseudoWhite;
    
    /**
     * Starts and sets all the components of the game.
     *
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        setLayoutPrimaryStage(primaryStage);

        Scene startScene = makeLayoutsAndScenes(primaryStage);

        primaryStage.setScene(startScene);
        primaryStage.show();
    }

    private void setLayoutPrimaryStage(Stage primaryStage) {
        primaryStage.setTitle("Othello");
        primaryStage.setMinWidth(1000);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMinHeight(750);
        primaryStage.setMaxHeight(750);
    }

    private Scene makeLayoutsAndScenes(Stage primaryStage) {
        finalRoot = new FinalLayout(game);
        Scene finalScene = makeScene(finalRoot);
        
        tfdPseudoBlack = makeAPseudoTfd("pseudoInput", "Black player");
        tfdPseudoWhite = makeAPseudoTfd("pseudoInput", "White player");

        gameRoot = makeGameLayout(primaryStage, finalScene);
        gameRoot.setMinHeight(750);
        gameRoot.setMinWidth(1000);
        Scene gameScene = makeScene(gameRoot);

        startRoot = new StartLayout(game, primaryStage, gameScene, gameRoot);
        Scene startScene = makeScene(startRoot);
        return startScene;
    }

    private TextField makeAPseudoTfd(String id, String promptText) {
        TextField tfdPseudo = new TextField();
        tfdPseudo.setId(id);
        tfdPseudo.setPromptText(promptText);
        tfdPseudo.setMaxWidth(300);
        return tfdPseudo;
    }

    private Scene makeScene(Parent root) {
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(
                this.getClass().getResource("style.css").toExternalForm());
        return scene;
    }

    /**
     * Creates a game to creates the differents elements of the GUI.
     *
     * @return a ready to use root.
     */
    private GameLayout makeGameLayout(Stage primaryStage, Scene finalScene) {
        game = new OthelloImpl(rows, columns);
        scoreInfos = new ScoresInfos(game);
        board = new BoardPane(game);
        gameRoot = new GameLayout(primaryStage, finalScene, game, scoreInfos, board);

        game.addObserver(this);
        return gameRoot;
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
        scoreInfos.refresh(game.getScoreBlack(), game.getScoreWhite(),
                game.getCurrentColor());
        board.refresh(game);
        gameRoot.refresh(game);
    }
}

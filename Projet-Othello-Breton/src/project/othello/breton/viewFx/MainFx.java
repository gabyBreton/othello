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

        game = new OthelloImpl(rows, columns);
        game.wall(0, 0); // To verify if we see a wall

        board = new BoardPane(game);
        scoreInfos = new ScoresInfos(game);
        //      scoreInfos.setGridLinesVisible(true); // FOR DEBUG !

        game.addObserver(this);
        Button btnAbandon = makeButtonAbandon();
        Label cptWall = makeCptWall();
        Label nbWall = makeNbWall();
        GridPane sideZone = makeSideZone(scoreInfos, btnAbandon, cptWall, nbWall);
        root.setLeft(board);
        root.setCenter(sideZone);
        return root;
    }

    /**
     * Make the view of the number of wall on the board.
     *
     * @return the label for the number of wall.
     */
    private Label makeNbWall() {
        Label nbWall = new Label();
        nbWall.setText(String.valueOf(board.getCounterWallsOnBoard()));
        nbWall.setId("nbWall");
        return nbWall;
    }

    /**
     * Make the label who say how many walls there is on the board.
     *
     * @return the label who say how many walls there is on the board.
     */
    private Label makeCptWall() {
        Label cptWall = new Label();
        cptWall.setText("Walls:");
        cptWall.setId("cptWall");
        return cptWall;
    }

    /**
     * Creates a button to abandon the game.
     *
     * @return the abandon button.
     */
    private Button makeButtonAbandon() {
        Button btnAbandon = new Button("Abandon");
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
     * Creates the side zone with the scores infos and the buttons.
     *
     * @param scoreInfos the scores infos.
     * @param btnAbandon the button abandon.
     * @return
     */
    private GridPane makeSideZone(GridPane scoreInfos, Button btnAbandon,
            Label cptWall, Label nbWall) {
        GridPane sideZone = new GridPane();
        setPaddingAndGap(sideZone);
        addElements(sideZone, scoreInfos, cptWall, nbWall, btnAbandon);
        return sideZone;
    }

    private void setPaddingAndGap(GridPane sideZone) {
        sideZone.setPadding(new Insets(75, 50, 0, 50));
        sideZone.setHgap(10);
        sideZone.setVgap(15);
    }

    private void addElements(GridPane sideZone, GridPane scoreInfos,
            Label cptWall, Label nbWall, Button btnAbandon) {
        //  sideZone.setGridLinesVisible(true); // FOR DEBUG!
        sideZone.add(scoreInfos, 0, 0);
        sideZone.add(cptWall, 0, 5);
        sideZone.add(nbWall, 0, 6);
        //sideZone.add(currentPlayer, 0, 5);
        sideZone.add(btnAbandon, 0, 10);
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

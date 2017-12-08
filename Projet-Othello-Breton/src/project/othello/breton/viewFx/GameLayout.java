package project.othello.breton.viewFx;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import static javafx.scene.layout.VBox.setMargin;
import javafx.stage.Stage;
import project.othello.breton.model.OthelloImpl;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class GameLayout extends BorderPane {

    private final ScoresInfos scoreInfos;
    private final BoardPane board;
    private GridPane buttonsZone;
    
    private GameInfo gameInfo;

    private Button btnAbandon;
    private Button btnPass;
    private Button btnRestart;

    private final Stage primaryStage; 
    private Scene finalScene;
    private FinalLayout finalRoot;
    
    GameLayout(Stage primaryStage, OthelloImpl game, ScoresInfos scoreInfos, BoardPane board) {
        super();
        setId("gamePane");

        this.board = board;
        this.scoreInfos = scoreInfos;
        makeButtonsZone(game);
        gameInfo = new GameInfo(game);
        setMargin(gameInfo, new Insets(0, 50, 50, 0));
        
        //setMargin(scoreInfos, new Insets(200, 0, 0, 0));
        scoreInfos.setAlignment(Pos.CENTER_RIGHT);
        
        VBox boxBoardScore = new VBox();
        boxBoardScore.setSpacing(30);
        boxBoardScore.getChildren().addAll(board, scoreInfos);
        setLeft(boxBoardScore);
        
        setCenter(buttonsZone);

        setRight(gameInfo);
        this.primaryStage = primaryStage;
    }

    /**
     * Creates the side zone with the scores infos and the buttons.
     *
     * @param scoreInfos the scores infos.
     * @param btnAbandon the button abandon.
     * @return
     */
    private void makeButtonsZone(OthelloImpl game) {
        buttonsZone = new GridPane();
        setPaddingAndGap();
        makeElementsOfCenter(game);
        addElements();
    }

    private void setPaddingAndGap() {
        buttonsZone.setPadding(new Insets(225, 0, 0, 50));
        setMinWidth(200);
        buttonsZone.setHgap(10);
        buttonsZone.setVgap(15);
    }

    private void makeElementsOfCenter(OthelloImpl game) {
        makeButtons(game);
    }

    private void makeButtons(OthelloImpl game) {
        btnAbandon = makeAButton("Abandon", "button", (event) -> {
            makeAlertAbandon(game);
        });
        btnPass = makeAButton("Pass", "button", (event) -> {
            game.pass();
        });
        btnRestart = makeAButton("Restart", "button", (event) -> {
//  restart(game, scoreInfos, board);
        });
    }

    private void makeAlertAbandon(OthelloImpl game) {
        Alert alertAbandon = setAlertAbandon();
        ButtonType buttonTypeSure = new ButtonType("Yes I give up ...");
        ButtonType buttonTypeNo = new ButtonType("No !");

        alertAbandon.getButtonTypes().setAll(buttonTypeNo, buttonTypeSure);

        makeResultConfirmation(game, alertAbandon, buttonTypeSure);
    }

    private void makeResultConfirmation(OthelloImpl game, Alert alertAbandon, ButtonType buttonTypeSure) {
        Optional<ButtonType> result = alertAbandon.showAndWait();
        if (result.get() == buttonTypeSure) {
            makeFinalScene(game);
            primaryStage.setScene(finalScene);
            primaryStage.setMinWidth(1000);
            primaryStage.setMaxWidth(1000);
        } else {
            alertAbandon.close();
        }
    }

    private Alert setAlertAbandon() {
        Alert alertAbandon = new Alert(Alert.AlertType.CONFIRMATION);
        alertAbandon.setTitle("Abandon");
        alertAbandon.setHeaderText(null);
        alertAbandon.setContentText("Do you really want to give up ?");
        alertAbandon.setResizable(true);
        return alertAbandon;
    }

    private Button makeAButton(String text, String id,
            EventHandler<ActionEvent> value) {
        Button button = new Button(text);
        button.setMaxWidth(150);
        button.setMinHeight(50);
        button.setId(id);
        button.setOnAction(value);
        return button;
    }

    private void addElements() {
     //   buttonsZone.setGridLinesVisible(true); // FOR DEBUG!
        buttonsZone.add(btnPass, 0, 3);
        buttonsZone.add(btnRestart, 0, 5);
        buttonsZone.add(btnAbandon, 0, 7);
    }

    ScoresInfos getScoreInfos() {
        return scoreInfos;
    }

    BoardPane getBoard() {
        return board;
    }

    public void refresh(OthelloImpl game) {
        gameInfo.refresh(game);
        if(game.isOver()) {
            makeFinalScene(game);
            primaryStage.setScene(finalScene);
            primaryStage.setMinWidth(1000);
            primaryStage.setMaxWidth(1000);           
        }
    }
    
    private void makeFinalScene(OthelloImpl game) {
        finalRoot = new FinalLayout(game, game.getWinner());
        finalScene = makeScene(finalRoot);
    }

    private Scene makeScene(Parent root) {
        Scene scene = new Scene(root);
        scene.getStylesheets().addAll(
                this.getClass().getResource("style.css").toExternalForm());
        return scene;
    }
}

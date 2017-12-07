package project.othello.breton.viewFx;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * This class creates the layout for the start interface.
 *
 * @author Gabriel Breton - 43397
 */
public class StartLayout extends BorderPane {

    StartLayout(Stage primaryStage, Scene gameScene, GameLayout gameRoot) {
        super();
        setId("startPane");
        setSizeStage(primaryStage);

        makeGameNameBox();

        VBox pseudosBox = makePseudoBox();
        TextField tfdPseudoBlack = makeAPseudo("pseudoInput", "Black player");
        TextField tfdPseudoWhite = makeAPseudo("pseudoInput", "White player");

        pseudosBox.getChildren().addAll(tfdPseudoBlack, tfdPseudoWhite);
        setCenter(pseudosBox);

        makeButton(tfdPseudoBlack, tfdPseudoWhite, primaryStage, gameScene, gameRoot);
    }

    void setSizeStage(Stage primaryStage) {
        primaryStage.setMinWidth(1000);
        primaryStage.setMaxWidth(1000);
        primaryStage.setMinHeight(750);
        primaryStage.setMaxHeight(750);
    }

    private void makeGameNameBox() {
        VBox gameNameBox = new VBox();
        gameNameBox.setPadding(new Insets(100, 50, 50, 300));
        Label gameName = new Label("Ot.hello() ");
        gameName.setId("gameName");
        gameNameBox.getChildren().add(gameName);
        setTop(gameNameBox);
    }

    private VBox makePseudoBox() {
        VBox pseudosBox = new VBox();
        pseudosBox.setSpacing(40);
        pseudosBox.setPadding(new Insets(50, 0, 0, 350));
        return pseudosBox;
    }

    private TextField makeAPseudo(String id, String promptText) {
        TextField tfdPseudo = new TextField();
        tfdPseudo.setId(id);
        tfdPseudo.setPromptText(promptText);
        tfdPseudo.setMaxWidth(300);
        return tfdPseudo;
    }

    private void makeButton(TextField tfdPseudoBlack, TextField tfdPseudoWhite, Stage primaryStage, Scene gameScene, GameLayout gameRoot) {
        VBox buttonsBox = new VBox();
        buttonsBox.setPadding(new Insets(50, 50, 120, 425));
        Button btnPlay = new Button("Play");
        btnPlay.setMaxWidth(150);
        btnPlay.setMinHeight(50);
        btnPlay.setId("button");
        btnPlay.setOnAction(e -> {
            gameRoot.getScoreInfos().setPseudos(tfdPseudoBlack.getText(), tfdPseudoWhite.getText());//useless
            primaryStage.setScene(gameScene);
            primaryStage.setMinWidth(1500);
            primaryStage.setMaxWidth(1500);
        });
        buttonsBox.getChildren().add(btnPlay);
        setBottom(buttonsBox);
    }
}

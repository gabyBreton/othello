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
import project.othello.breton.model.OthelloImpl;

/**
 * This class creates the layout for the start interface.
 *
 * @author Gabriel Breton - 43397
 */
public class StartLayout extends BorderPane {
    
    StartLayout(Stage primaryStage, Scene gameScene, GameLayout gameRoot) {
        super();
        setId("startPane");

        makeGameNameBox();

        VBox pseudosBox = makePseudoBox();
        TextField tfdPseudoBlack = makeAPseudo("tfdPseudoB", "Black player");
        TextField tfdPseudoWhite = makeAPseudo("tfdPseudoW", "White player");

        pseudosBox.getChildren().addAll(tfdPseudoBlack,tfdPseudoWhite);
        setCenter(pseudosBox);

        makeButton(tfdPseudoBlack,tfdPseudoWhite,primaryStage,gameScene, gameRoot);
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
        btnPlay.setId("btnPlay");
        btnPlay.setOnAction(e -> {
//            game.setPseudoBlack(tfdPseudoBlack.getText());
//            game.setPseudoWhite(tfdPseudoWhite.getText());
            gameRoot.getScoreInfos().setPseudos(tfdPseudoBlack.getText(), tfdPseudoWhite.getText());
            primaryStage.setScene(gameScene);
        });
        buttonsBox.getChildren().add(btnPlay);
        setBottom(buttonsBox);
    }
}

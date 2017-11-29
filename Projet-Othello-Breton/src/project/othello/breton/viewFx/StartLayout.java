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
    
    StartLayout(OthelloImpl game, Stage primaryStage, Scene gameScene) {
        super();
        setId("startPane");

        makeGameNameBox();

        VBox pseudosBox = makePseudoBox();
        TextField tfdPseudoBlack = makePseudoBlack();
        TextField tfdPseudoWhite = makePseudoWhite();

        pseudosBox.getChildren().addAll(tfdPseudoBlack,tfdPseudoWhite);
        setCenter(pseudosBox);

        makeButton(game,tfdPseudoBlack,tfdPseudoWhite,primaryStage,gameScene);
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

    private TextField makePseudoWhite() {
        TextField tfdPseudoWhite = new TextField();
        tfdPseudoWhite.setId("tfdPseudoW");
        tfdPseudoWhite.setPromptText("White player");
        tfdPseudoWhite.setMaxWidth(300);
        return tfdPseudoWhite;
    }

    private TextField makePseudoBlack() {
        TextField tfdPseudoBlack = new TextField();
        tfdPseudoBlack.setId("tfdPseudoB");
        tfdPseudoBlack.setPromptText("Black player");
        tfdPseudoBlack.setMaxWidth(300);
        return tfdPseudoBlack;
    }

    private void makeButton(OthelloImpl game, TextField tfdPseudoBlack, TextField tfdPseudoWhite, Stage primaryStage, Scene gameScene) {
        VBox buttonsBox = new VBox();
        buttonsBox.setPadding(new Insets(50, 50, 120, 425));
        Button btnPlay = new Button("Play");
        btnPlay.setMaxWidth(150);
        btnPlay.setMinHeight(50);
        btnPlay.setId("btnPlay");
        btnPlay.setOnAction(e -> {
            game.setPseudoBlack(tfdPseudoBlack.getText());
            game.setPseudoWhite(tfdPseudoWhite.getText());
            primaryStage.setScene(gameScene);
        });
        buttonsBox.getChildren().add(btnPlay);
        setBottom(buttonsBox);
    }
}

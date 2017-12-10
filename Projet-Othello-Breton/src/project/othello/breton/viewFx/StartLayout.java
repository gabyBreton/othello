package project.othello.breton.viewFx;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import project.othello.breton.model.ComputerBehavior;
import project.othello.breton.model.HumanBehavior;
import project.othello.breton.model.OthelloImpl;

/**
 * This class creates and sets the layout for the start interface.
 *
 * @author Gabriel Breton - 43397
 */
class StartLayout extends BorderPane {

    private RadioButton humanBlack;
    private RadioButton humanWhite;
    private RadioButton computerWhite;
    private final BooleanProperty firstTime;    
    private TextFieldLimited tfdPseudoBlack;
    private TextFieldLimited tfdPseudoWhite;
    
    /**
     * Creates the start interface, that will contains the name of the game,
     * the box to enter the pseudos, and the start button.
     * 
     * @param game the current session of Othello.
     * @param primaryStage the primary stage that will contains the game scene.
     * @param gameScene the game scene.
     * @param gameRoot the game interface.
     */
    StartLayout(OthelloImpl game, Stage primaryStage, Scene gameScene, 
                GameLayout gameRoot) {
        super();
        setId("startPane");
        firstTime = new SimpleBooleanProperty(true);
        
        makeGameNameBox();
        makePseudoZone();
        makeBtnPlay(game, primaryStage, gameScene, gameRoot);
    }   
    
    /**
     * Creates the box where is displayed the name of the game.
     */
    private void makeGameNameBox() {
        VBox gameNameBox = new VBox();
        gameNameBox.setPadding(new Insets(100, 50, 50, 300));
        
        Label gameName = new Label("Ot.hello() ");
        gameName.setId("gameName");
        
        gameNameBox.getChildren().add(gameName);
        setTop(gameNameBox);
    }
    
    /**
     * Creates the zone that will contains the two input textfield to enter
     * the pseudos of the players.
     */
    private void makePseudoZone() {
        VBox pseudosBox = makePseudosBox();

        tfdPseudoBlack = makeAPseudoTfd("pseudoInput", "Black player");
        tfdPseudoWhite = makeAPseudoTfd("pseudoInput", "White player");
        
        makeRBtnChoiceAdversary();
        GridPane paneChoiceAdversary = makePaneChoiceAdversary();
        
        pseudosBox.getChildren().addAll(tfdPseudoBlack, humanBlack, 
                                        tfdPseudoWhite, paneChoiceAdversary);
        setFocusedPropertyTfd(pseudosBox);
        setCenter(pseudosBox);
    } 

    /**
     * Creates the buttons for the choice of the adversary.
     */
    private void makeRBtnChoiceAdversary() {
        makeHumanBlack();
        makeChoiceAdversaryWhite();
    } 

    /**
     * Creates the buttons for the choice of the adversary of the white player.
     */
    private void makeChoiceAdversaryWhite() {
        ToggleGroup groupChoiceAdversary = new ToggleGroup();
        makeHumanWhite(groupChoiceAdversary);
        makeComputerWhite(groupChoiceAdversary);
    } 

    /**
     * Creates the button for the white player to choose the computer.
     * 
     * @param groupChoiceAdversary the toggle group for the computer button.
     */
    private void makeComputerWhite(ToggleGroup groupChoiceAdversary) {
        computerWhite = new RadioButton("Computer");
        computerWhite.setId("rbButton");
        computerWhite.setToggleGroup(groupChoiceAdversary);
    } 

    /**
     * Creates the button for the white player to choose the human.
     * 
     * @param groupChoiceAdversary the toggle group for the human white button.
     */
    private void makeHumanWhite(ToggleGroup groupChoiceAdversary) {
        humanWhite = new RadioButton("Human");
        humanWhite.setId("rbButton");
        humanWhite.setSelected(true);
        humanWhite.setToggleGroup(groupChoiceAdversary);
    }

    /**
     * Creates the human black radio button.
     */
    private void makeHumanBlack() {
        humanBlack = new RadioButton("Human");
        humanBlack.setId("rbButton");
        humanBlack.setSelected(true);
        humanBlack.setPadding(new Insets(0, 0, 0, 80));
        
        ToggleGroup groupBlackChoice = new ToggleGroup();
        humanBlack.setToggleGroup(groupBlackChoice);
    }

    /**
     * Creates the grid pane to place the two white radio buttons for the
     * choice of the adversary between computer and human.
     * 
     * @return the new grid pane.
     */
    private GridPane makePaneChoiceAdversary() {
        GridPane paneChoiceAdversary = new GridPane();
        paneChoiceAdversary.setHgap(20);
        paneChoiceAdversary.add(humanWhite, 0, 0);
        paneChoiceAdversary.add(computerWhite, 1, 0);
        return paneChoiceAdversary;
    }

    /**
     * Set the focused property of the text field for the pseudo of the black
     * player to have no focus when scene charge so the prompt text is visible.
     * 
     * @param pseudosBox the pseudo box that contains the text field for the
     * pseudo of the black player.
     */
    private void setFocusedPropertyTfd(VBox pseudosBox) {
        tfdPseudoBlack.focusedProperty().addListener((observable,  oldValue,
                                                      newValue) -> {
            if(newValue && firstTime.get()){
                pseudosBox.requestFocus(); // Delegate the focus to container
                firstTime.setValue(false); // Variable value changed for future
                                            // references
            }
        });
    } 
    
    /**
     * Creates and set the spacing and padding of the box for the pseudos 
     * inputs.
     * 
     * @return the new pseudo box.
     */
    private VBox makePseudosBox() {
        VBox pseudosBox = new VBox();
        pseudosBox.setSpacing(40);
        pseudosBox.setPadding(new Insets(10, 0, 0, 350));
      
        return pseudosBox;
    }

    /**
     * Creates a text field where to enter the pseudo of a player. The text 
     * field have a limitation on the length of the input set on 10.
     * 
     * @param id the id of this text field.
     * @param promptText the prompt text of this text field.
     * @return the new pseudo text field.
     */
    private TextFieldLimited makeAPseudoTfd(String id, String promptText) {
        TextFieldLimited tfdPseudo = new TextFieldLimited();
        tfdPseudo.setId(id);
        tfdPseudo.setPromptText(promptText);
        tfdPseudo.setMaxWidth(300);
      
        return tfdPseudo;
    }

    /**
     * Creates the button play.
     * 
     * @param game the current session of Othello.
     * @param primaryStage the primary stage that will contains the game scene.
     * @param gameScene the game scene.
     * @param gameRoot the game interface.
     */
    private void makeBtnPlay(OthelloImpl game, Stage primaryStage, 
                             Scene gameScene, GameLayout gameRoot) {
        
        VBox buttonsBox = new VBox();
        buttonsBox.setPadding(new Insets(50, 50, 120, 425));
        
        Button btnPlay = makeAButton("Play", "button", 150, 50, (event) -> {
            makeActionPlay(game, primaryStage, gameScene, gameRoot); 
        });
        
        buttonsBox.getChildren().add(btnPlay);
        setBottom(buttonsBox);
    }

    /**
     * Creates a button.
     * 
     * @param text the text displayed on the button.
     * @param id the id of the button, for the css styling.
     * @param value the action activated when clicked.
     * @return the new button.
     */
    private Button makeAButton(String text, String id, int maxWidth,
                               int minHeight, 
                               EventHandler<ActionEvent> value) {
        Button button = new Button(text);
        button.setMaxWidth(maxWidth);
        button.setMinHeight(minHeight);
        button.setId(id);
        button.setOnAction(value);
       
        return button;
    }
    
    /**
     * Creates the action for when the button play is clicked. This action 
     * launch the game scene.
     * 
     * @param game the current session of Othello.
     * @param primaryStage the primary stage that will contains the game scene.
     * @param gameScene the game scene.
     * @param gameRoot the game interface. 
     */
    private void makeActionPlay(OthelloImpl game, Stage primaryStage, 
                                Scene gameScene, GameLayout gameRoot) {
    
        if ((tfdPseudoBlack.getLength() == 0) 
             || (tfdPseudoWhite.getLength() == 0)) {
            makeAlertPseudo();
        } else {
            makePlayers(game);
            gameRoot.getScoreInfos().setPseudos(tfdPseudoBlack.getText(),
                    tfdPseudoWhite.getText());
            setSizesNewScene(primaryStage, gameScene);
        }

    }

    private void makePlayers(OthelloImpl game) {
        if (humanWhite.isSelected()) {
            game.makePlayers(new HumanBehavior(game), tfdPseudoBlack.getText(),
                             tfdPseudoWhite.getText());
        } else {
            game.makePlayers(new ComputerBehavior(game), tfdPseudoBlack.getText(),
                             tfdPseudoWhite.getText());
        }
    }

    /**
     * Creates a warning dialog box, if at least one pseudo is not entered.
     */
    private void makeAlertPseudo() {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Pseudos Warning");
        alert.setHeaderText(null);
        alert.setContentText("Pseudos should contains at least one "
                             + "character");
        alert.showAndWait();
    }

    /**
     * Sets the sizes of the new scene.
     * 
     * @param primaryStage the primary stage that will contains the game scene.
     * @param gameScene the game scene.
     */
    private void setSizesNewScene(Stage primaryStage, Scene gameScene) {
        primaryStage.setScene(gameScene);
        primaryStage.setMinWidth(1300);
        primaryStage.setMaxWidth(1300);
        primaryStage.setMinHeight(820);
        primaryStage.setMaxHeight(820);
    }
}
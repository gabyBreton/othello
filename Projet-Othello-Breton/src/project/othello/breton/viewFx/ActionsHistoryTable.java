package project.othello.breton.viewFx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.othello.breton.model.Action;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.othello.breton.model.OthelloImpl;

/**
 * This classes is used to create an table view for the history of the actions 
 * of the game.
 * 
 * @author Gabriel Breton - 43397
 */
class ActionsHistoryTable extends TableView {

    private final ObservableList<Action> actionsHistory;
    private int actionId;

    private TableColumn<Action, Integer> idColumn;
    private TableColumn<Action, String> pseudoColumn;
    private TableColumn<Action, String> actionColumn;
    private TableColumn<Action, String> positionColumn;
    private TableColumn<Action, Integer> takingColumn;

    /**
     * Creates a new history table view.
     * 
     * @param game the current session of Othello.
     */
    ActionsHistoryTable(OthelloImpl game) {
        super();
        makeColumns();
        actionsHistory = FXCollections.observableArrayList();
        actionsHistory.add(new Action(actionId, " ", "   New game", " ", 0));        
        setItems(actionsHistory);
        getColumns().addAll(idColumn, pseudoColumn, actionColumn);
        getColumns().addAll(positionColumn, takingColumn);
        setMinWidth(366);
    }

    /**
     * Makes the columns of the history table view.
     */
    private void makeColumns() {
        idColumn = new TableColumn<>("ID");
        setTableColumn(idColumn, 10, "id");

        pseudoColumn = new TableColumn<>("Pseudo");
        setTableColumn(pseudoColumn, 95, "pseudo");
        
        actionColumn = new TableColumn<>("Action");
        setTableColumn(actionColumn, 100, "action");
        
        positionColumn = new TableColumn<>("Position");
        setTableColumn(positionColumn, 30, "position");
        
        takingColumn = new TableColumn<>("Taking");
        setTableColumn(takingColumn, 20, "taking");
    }

    /**
     * Set a column of the table.
     * 
     * @param <T> the type of the content of the column.
     * @param column the column.
     * @param minWidth the minimal size of the column.
     * @param property the property of the column.
     */
    private <T> void setTableColumn(TableColumn<Action, T> column, int minWidth, 
                                    String property) {
        column.setMinWidth(minWidth);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
    }
    
    /**
     * To refresh the tableview.
     * 
     * @param game the current session of Othello.
     */
    void refresh(OthelloImpl game) {
        actionsHistory.add(game.getAction());
    }
}

package project.othello.breton.viewFx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class ActionsHistoryTable extends TableView {

    private final ObservableList<Action> actions;
    private TableColumn<Action, Integer> idColumn;
    private TableColumn<Action, String> pseudoColumn;
    private TableColumn<Action, String> actionColumn;
    private TableColumn<Action, String> positionColumn;
    private TableColumn<Action, Integer> takingColumn;

    public ActionsHistoryTable() {
        super();
        actions = FXCollections.observableArrayList();
        makeColumns();
        setItems(actions);
        getColumns().addAll(idColumn, pseudoColumn, actionColumn);
        getColumns().addAll(positionColumn, takingColumn);
    }

    private void makeColumns() {
        idColumn = new TableColumn<>("ID");
        setTableColumn(idColumn, 10, "id");

        pseudoColumn = new TableColumn<>("Pseudo");
        setTableColumn(pseudoColumn, 100, "pseudo");
        
        actionColumn = new TableColumn<>("Action");
        setTableColumn(actionColumn, 200, "action");
        
        positionColumn = new TableColumn<>("Position");
        setTableColumn(positionColumn, 50, "position");
        
        takingColumn = new TableColumn<>("Taking");
        setTableColumn(takingColumn, 10, "taking");
    }

    private <T> void setTableColumn(TableColumn<Action, T> column, int minWidth, 
                                    String property) {
        column.setMinWidth(minWidth);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
    }
    
    void addAction(Action action) {
        actions.add(action);
    }
    
}

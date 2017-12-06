package project.othello.breton.viewFx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import project.othello.breton.model.Action;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import project.othello.breton.model.OthelloImpl;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class ActionsHistoryTable extends TableView {

    private final ObservableList<Action> actionsHistory;
    private int actionId;

    private TableColumn<Action, Integer> idColumn;
    private TableColumn<Action, String> colorColumn;
    private TableColumn<Action, String> actionColumn;
    private TableColumn<Action, String> positionColumn;
    private TableColumn<Action, Integer> takingColumn;

    public ActionsHistoryTable(OthelloImpl game) {
        super();
        makeColumns();
        actionsHistory = FXCollections.observableArrayList();
        actionsHistory.add(new Action(actionId, " ", "New game", " ", 0));        
        setItems(actionsHistory);
        getColumns().addAll(idColumn, colorColumn, actionColumn);
        getColumns().addAll(positionColumn, takingColumn);
     //   setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    private void makeColumns() {
        idColumn = new TableColumn<>("ID");
        setTableColumn(idColumn, 10, "id");

        colorColumn = new TableColumn<>("Color");
        setTableColumn(colorColumn, 100, "color");
        
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
    
    public void refresh(OthelloImpl game) {
        actionsHistory.add(game.getAction());
    }
}

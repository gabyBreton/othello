package projet.othello.breton.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is the facade of Othello.
 *
 * @author Gabriel Breton - 43397
 */
public class OthelloImpl implements Othello {

    private final Board board;
    private final Players player1;
    private final Players player2;

    public OthelloImpl(int rows, int columns) {
        board = new Board(rows, columns);
        player1 = new Players(Color.BLACK);
        player2 = new Players(Color.WHITE);
    }

    @Override
    public boolean isOver() {
        return board.isFull();
    }

    @Override
    public List<Players> getPlayers() {
        List<Players> listPlayers = new ArrayList<>();

        listPlayers.add(player1);
        listPlayers.add(player2);

        return listPlayers;
    }

    @Override
    public Players getCurrentPlayer() {
        return null;
    }

    @Override
    public int getHeight() {
        return board.getRows();
    }

    @Override
    public int getWidht() {
        return board.getColumns();
    }

    @Override
    public Color getColor(int x, int y) {
        return board.getColor(x, y);
    }

    @Override
    public void play(int x, int y) {
        
    }
}

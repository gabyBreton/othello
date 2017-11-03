package projet.othello.breton.model;

import project.othello.breton.model.Players;
import project.othello.breton.model.PlayerColor;
import project.othello.breton.model.OthelloImpl;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class OthelloImplTest {

    /**
     * Test of isOver method, of class OthelloImpl.
     */
    @Test
    public void testIsOverFull() {
        OthelloImpl game = new OthelloImpl(2, 2);
        assertTrue(game.isOver());
    }

    /**
     * Test of isOver method, of class OthelloImpl.
     */
    @Test
    public void testIsOverNotFull() {
        OthelloImpl game = new OthelloImpl(4, 4);
        assertFalse(game.isOver());
    }

    /**
     * Test of getPlayers method, of class OthelloImpl.
     */
    @Test
    public void testGetPlayers() {
        OthelloImpl game = new OthelloImpl(4, 4);
        Players playerBlack = game.getPlayers().get(0);
        Players playerWhite = game.getPlayers().get(1);

        assertTrue(playerBlack.getColor() == PlayerColor.BLACK
                   && playerWhite.getColor() == PlayerColor.WHITE);
    }

    /**
     * Test of getCurrentColor method, of class OthelloImpl, just after
     * initialisation.
     */
    @Test
    public void testGetCurrentColorInitial() {
        OthelloImpl game = new OthelloImpl(4, 4);
        PlayerColor currentColor = game.getCurrentColor();
        assertEquals(PlayerColor.BLACK, currentColor);
    }

    /**
     * Test of getCurrentColor method, of class OthelloImpl, after a change of
     * the current color by adding a pawn on the board.
     */
    @Test
    public void testGetCurrentColorAfterChange() {
        OthelloImpl game = new OthelloImpl(4, 4);
        game.play(1, 0);
        PlayerColor currentColor = game.getCurrentColor();
        assertEquals(PlayerColor.WHITE, currentColor);
    }

    /**
     * Test of getHeight method, of class OthelloImpl.
     */
    @Test
    public void testGetHeightEquals() {
        OthelloImpl game = new OthelloImpl(4, 6);
        assertEquals(4, game.getHeight());
    }

    /**
     * Test of getHeight method, of class OthelloImpl.
     */
    @Test
    public void testGetHeightNotEquals() {
        OthelloImpl game = new OthelloImpl(4, 6);
        assertNotEquals(6, game.getHeight());
    }

    /**
     * Test of getWidht method, of class OthelloImpl.
     */
    @Test
    public void testGetWidhtEquals() {
        OthelloImpl game = new OthelloImpl(4, 6);
        assertEquals(6, game.getWidht());
    }

    /**
     * Test of getWidht method, of class OthelloImpl.
     */
    @Test
    public void testGetWidhtNotEquals() {
        OthelloImpl game = new OthelloImpl(4, 6);
        assertNotEquals(4, game.getWidht());
    }

    /**
     * Test of getColor method, of class OthelloImpl, to get the color of the
     * first white pawn, placed at the start.
     */
    @Test
    public void testGetColorFirstWhite() {
        OthelloImpl game = new OthelloImpl(4, 4);
        assertEquals(PlayerColor.WHITE, game.getColor(1, 1));
    }

    /**
     * Test of getColor method, of class OthelloImpl, to get the color of the
     * first black pawn, placed at the start.
     */
    @Test
    public void testGetColorFirstBlack() {
        OthelloImpl game = new OthelloImpl(4, 4);
        assertEquals(PlayerColor.BLACK,game.getColor(1, 2));
    }

    /**
     * Test of getColor method, of class OthelloImpl, to get the color of the
     * second black pawn, placed at the start.
     */
    @Test
    public void testGetColorSecondBlack() {
        OthelloImpl game = new OthelloImpl(4, 4);
        assertEquals(PlayerColor.BLACK, game.getColor(2, 1));
    }

    /**
     * Test of getColor method, of class OthelloImpl, to get the color of the
     * second white pawn, placed at the start.
     */
    @Test
    public void testGetColorSecondWhite() {
        OthelloImpl game = new OthelloImpl(4, 4);
        assertEquals(PlayerColor.WHITE, game.getColor(2, 2));
    }

    /**
     * Test of getColor method, of class OthelloImpl, to get the color of a cell
     * where a pawn can be placed.
     */
    @Test
    public void testGetColorPossibleMove() {
        OthelloImpl game = new OthelloImpl(4, 4);
        game.setPossiblePositions();
        assertEquals(PlayerColor.GREY, game.getColor(1, 0));
    }

    /**
     * Test of getColor method, of class OthelloImpl, to get the color of an
     * empty place.
     */
    @Test
    public void testGetColorEmptyPlace() {
        OthelloImpl game = new OthelloImpl(4, 4);
        game.setPossiblePositions();
        assertEquals(null, game.getColor(0, 0));
    }

    /**
     * Test of setPossiblePositions method, of class OthelloImpl. A possible
     * positions is represented on the board by the color Grey.
     */
    @Test
    public void testSetPossiblePositions() {
        OthelloImpl game = new OthelloImpl(4, 4);
        game.setPossiblePositions();
        assertEquals(PlayerColor.GREY, game.getColor(1, 0));
    }

    /**
     * Test of cleanLastPlayerPossibilities method, of class OthelloImpl.
     */
    @Test
    public void testCleanLastPlayerPossibilities() {
        OthelloImpl game = new OthelloImpl(4, 4);
        game.setPossiblePositions();
        game.cleanLastPlayerPossibilities();
        assertEquals(null, game.getColor(1, 0));
    }

    /**
     * Test of play method, of class OthelloImpl. Test to place a pawn on an 
     * empty cell, but not a possible position.
     */
    @Test
    public void testPlayEmptyCellNotOk() {
        OthelloImpl game = new OthelloImpl(6, 6);
        game.setPossiblePositions();
        game.play(0,0);
        assertEquals(null, game.getColor(0, 0));
    }

    /**
     * Test of play method, of class OthelloImpl. Test to place a pawn on a 
     * possible position.
     */
    @Test
    public void testPlayPossibleMove() {
        OthelloImpl game = new OthelloImpl(6, 6);
        game.setPossiblePositions();        
        game.play(2, 1);
        assertEquals(PlayerColor.BLACK, game.getColor(2, 1));
    }

    /**
     * Test of play method, of class OthelloImpl. Place a pawn on a possible 
     * position and verifies if the adjacent pawn have been returned.
     */
    @Test
    public void testPlayPossibleMoveFlipOk() {
        OthelloImpl game = new OthelloImpl(6, 6);
        game.setPossiblePositions();
        game.play(2, 1);
        assertEquals(PlayerColor.BLACK, game.getColor(2, 2)); 
    }
 
    /**
     * Test of play method, of class OthelloImpl. Test to place a pawn on an
     * already taked place.
     */
    @Test
    public void testPlayTakedPlace() {
        OthelloImpl game = new OthelloImpl(6, 6);
        game.setPossiblePositions();
        game.play(2, 2);
        assertEquals(PlayerColor.WHITE, game.getColor(2, 2));
    }

    /**
     * Test of play method, of class OthelloImpl. Test to place a pawn out of 
     * the board out the minimal border.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPlayOutOfBoardMin() {
        OthelloImpl game = new OthelloImpl(6, 6);
        game.play(-1, -1);
    }

    /**
     * Test of play method, of class OthelloImpl.Test to place a pawn out of 
     * the board out the maximal border.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPlayOutOfBoardMax() {
        OthelloImpl game = new OthelloImpl(6, 6);
        game.play(6, 6);
    }

    /**
     * Test of play method, of class OthelloImpl. Test to place a pawn on the 
     * minimal inside limit border of the board, on a possible position.
     */
    @Test
    public void testPlayLimitOfBoardMinOkMove() {
        OthelloImpl game = new OthelloImpl(4, 4);
        game.play(1, 0);
        assertEquals(PlayerColor.BLACK, game.getColor(1, 0));
    }

    /**
     * Test of play method, of class OthelloImpl. Test to place a pawn on the 
     * maximal inside limit border of the board, on a possible position.
     */
    @Test
    public void testPlayLimitOfBoardMaxOkMove() {
        OthelloImpl game = new OthelloImpl(4, 4);
        game.play(3, 2);
        assertEquals(PlayerColor.BLACK, game.getColor(3, 2));
    }

}

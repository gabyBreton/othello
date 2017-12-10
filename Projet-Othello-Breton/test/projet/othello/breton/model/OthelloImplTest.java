package projet.othello.breton.model;

import project.othello.breton.model.OthelloImpl;
import org.junit.Test;
import static org.junit.Assert.*;
import project.othello.breton.model.ComputerBehavior;
import project.othello.breton.model.GameColor;
import project.othello.breton.model.HumanBehavior;

/**
 * This class provides some test of the implementation of the facade of Othello.
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
     * Test of makePlayers method, of class OthelloImpl, for the pseudo of 
     * black player setting.
     */
    @Test
    public void testMakePlayersPseudoBlack() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.makePlayers(new ComputerBehavior(game), "pseudoB", "pseudoW");
        assertTrue("pseudoB".equals(game.getCurrentPlayer().getPseudo()));
    }

    /**
     * Test of makePlayers method, of class OthelloImpl, for the pseudo of
     * white player setting.
     */
    @Test
    public void testMakePlayersPseudoWhite() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.makePlayers(new HumanBehavior(game), "pseudoB", "pseudoW");
        game.changeCurrentPlayer();
        assertTrue("pseudoW".equals(game.getCurrentPlayer().getPseudo()));
    }

    /**
     * Test of makePlayers method, of class OthelloImpl, to see if the game 
     * strategy is well set when it is a ComputerBehavior, by extension this 
     * test also the method Players.getStrategy().
     */
    @Test
    public void testMakePlayersGetGameStrategyComputer() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.makePlayers(new ComputerBehavior(game), "pseudoB", "pseudoW");
        game.changeCurrentPlayer();
        assertTrue(game.getCurrentPlayer()
                       .getGameStrategy() instanceof ComputerBehavior);
    }

    /**
     * Test of makePlayers method, of class OthelloImpl, to see if the game 
     * strategy is well set when it is a HumanBehavior, by extension this 
     * test also the method Players.getStrategy().
     */
    @Test
    public void testMakePlayersGetGameStrategyHuman() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.makePlayers(new HumanBehavior(game), "pseudoB", "pseudoW");
        game.changeCurrentPlayer();
        assertTrue(game.getCurrentPlayer()
                       .getGameStrategy() instanceof HumanBehavior);
    }
    
    /**
     * Test of getScoreBlack method at the start of the game.
     */
    @Test
    public void testGetScoreBlackInitial() {
        OthelloImpl game = new OthelloImpl(8, 8);
        assertTrue(game.getScoreBlack() == 2);
    }

    /**
     * Test of getScoreWhite method at the start of the game.
     */
    @Test
    public void testGetScoreWhiteInitial() {
        OthelloImpl game = new OthelloImpl(8, 8);
        assertTrue(game.getScoreWhite() == 2);
    }

    /**
     * Test of getScoreBlack method after the black place a pawn and takes one
     * pawn from white.
     */
    @Test
    public void testGetScoreBlackAfterPlay() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.play(2, 3);
        assertTrue(game.getScoreBlack() == 4);
    }

    /**
     * Test of getScoreWhite method after the black place a pawn and takes one
     * pawn from white.
     */
    @Test
    public void testGetScoreWhiteAfterPlay() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.play(2, 3);
        assertTrue(game.getScoreWhite() == 1);
    }
    
    /**
     * Test of getCurrentColor method, of class OthelloImpl, just after
     * initialisation.
     */
    @Test
    public void testGetCurrentColorInitial() {
        OthelloImpl game = new OthelloImpl(4, 4);
        GameColor currentColor = game.getCurrentColor();
        assertEquals(GameColor.BLACK, currentColor);
    }

    /**
     * Test of getCurrentColor method, of class OthelloImpl, after a change of
     * the current color by adding a pawn on the board.
     */
    @Test
    public void testGetCurrentColorAfterChange() {
        OthelloImpl game = new OthelloImpl(4, 4);
        game.play(1, 0);
        GameColor currentColor = game.getCurrentColor();
        assertEquals(GameColor.WHITE, currentColor);
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
        assertNotEquals(8, game.getHeight());
    }

    /**
     * Test of getWidth method, of class OthelloImpl.
     */
    @Test
    public void testGetWidthEquals() {
        OthelloImpl game = new OthelloImpl(4, 6);
        assertEquals(6, game.getWidth());
    }

    /**
     * Test of getWidth method, of class OthelloImpl.
     */
    @Test
    public void testGetWidthNotEquals() {
        OthelloImpl game = new OthelloImpl(4, 6);
        assertNotEquals(8, game.getWidth());
    }

    /**
     * Test of getColor method, of class OthelloImpl, to get the color of the
     * first white pawn, placed at the start.
     */
    @Test
    public void testGetColorFirstWhite() {
        OthelloImpl game = new OthelloImpl(4, 4);
        assertEquals(GameColor.WHITE, game.getColor(1, 1));
    }

    /**
     * Test of getColor method, of class OthelloImpl, to get the color of the
     * first black pawn, placed at the start.
     */
    @Test
    public void testGetColorFirstBlack() {
        OthelloImpl game = new OthelloImpl(4, 4);
        assertEquals(GameColor.BLACK,game.getColor(1, 2));
    }

    /**
     * Test of getColor method, of class OthelloImpl, to get the color of the
     * second black pawn, placed at the start.
     */
    @Test
    public void testGetColorSecondBlack() {
        OthelloImpl game = new OthelloImpl(4, 4);
        assertEquals(GameColor.BLACK, game.getColor(2, 1));
    }

    /**
     * Test of getColor method, of class OthelloImpl, to get the color of the
     * second white pawn, placed at the start.
     */
    @Test
    public void testGetColorSecondWhite() {
        OthelloImpl game = new OthelloImpl(4, 4);
        assertEquals(GameColor.WHITE, game.getColor(2, 2));
    }

    /**
     * Test of getColor method, of class OthelloImpl, to get the color of a cell
     * where a pawn can be placed.
     */
    @Test
    public void testGetColorPossibleMove() {
        OthelloImpl game = new OthelloImpl(4, 4);
        game.setPossiblePositions();
        assertEquals(GameColor.GREY, game.getColor(1, 0));
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
     * Test of the method wall, of class OthelloImpl, to see if a wall if 
     * effectively placed on the right upper corner.
     */
    @Test 
    public void testWallCornerUpRight() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.wall(0, 7);
        assertEquals(game.getColor(0, 7), GameColor.RED);
    }

    /**
     * Test of the method wall, of class OthelloImpl, to see if a wall if 
     * effectively placed on the right down corner.
     */
    @Test 
    public void testWallCornerDownRight() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.wall(7, 7);
        assertEquals(game.getColor(7, 7), GameColor.RED);
    }

    /**
     * Test of the method wall, of class OthelloImpl, to see if a wall if 
     * effectively placed on the left down corner.
     */
    @Test 
    public void testWallCornerDownLeft() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.wall(7, 0);
        assertEquals(game.getColor(7, 0), GameColor.RED);
    }
    
    /**
     * Test of the method wall, of class OthelloImpl, to see if a wall if 
     * effectively placed on the left up corner.
     */
    @Test 
    public void testWallCornerUpLeft() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.wall(0, 0);
        assertEquals(game.getColor(0, 0), GameColor.RED);
    }

    /**
     * Test of the method wall, of class OthelloImpl, to see if a wall is not
     * placed on an occupied cell.
     */
    @Test 
    public void testWallOccupiedCell() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.wall(4, 4);
        assertNotEquals(game.getColor(4, 4), GameColor.RED);
    }
    
        /**
     * Test of play method, of class OthelloImpl. Test to place a wall out of 
     * the board out the minimal border.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testWallOutOfBoardMin() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.wall(-1, -1);
    }

    /**
     * Test of play method, of class OthelloImpl.Test to place a wall out of 
     * the board out the maximal border at the right down corner.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testWallOutOfBoardMaxRightCornerDown() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.wall(8, 8);
    }

    /**
     * Test of play method, of class OthelloImpl.Test to place a wall out of 
     * the board out the maximal border at the right up corner.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testWallOutOfBoardMaxRightCornerUp() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.wall(0, 8);
    }
    /**
     * Test of play method, of class OthelloImpl. Test to place a wall out of 
     * the board out the maximal border at the left down corner.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testWallOutOfBoardMaxLeftCornerUp() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.wall(8, 0);
    }
    
    /**
     * Test of the method getWinner, of class OthelloImpl, to see if there is 
     * effectively equality when players have the same score.
     */
    @Test 
    public void testGetWinnerEquality() {
        OthelloImpl game = new OthelloImpl(8, 8);
        assertEquals(game.getWinner(), "Equality !");
    }

    /**
     * Test of the method getWinner, of class OthelloImpl, to see if we black
     * player is recognized as the winner.
     */
    @Test 
    public void testGetWinnerBlackWins() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.makePlayers(new HumanBehavior(game), "pseudoBlack", "pseudoWhite");
        game.play(2, 3);
        assertEquals(game.getWinner(), "pseudoBlack");
    }

    /**
     * Test of the method getWinner, of class OthelloImpl, to see if we black
     * player is recognized as the winner.
     */
    @Test 
    public void testGetWinnerWhiteWins() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.makePlayers(new HumanBehavior(game), "pseudoBlack", "pseudoWhite");
        game.wall(0, 0); //Black turn
        game.play(2, 4); //White turn
        assertEquals(game.getWinner(), "pseudoWhite");
    }
    
    /**
     * Test of setPossiblePositions method, of class OthelloImpl. A possible
     * positions is represented on the board by the color Grey.
     */
    @Test
    public void testSetPossiblePositions() {
        OthelloImpl game = new OthelloImpl(4, 4);
        game.setPossiblePositions();
        assertEquals(GameColor.GREY, game.getColor(1, 0));
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
        assertEquals(GameColor.BLACK, game.getColor(2, 1));
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
        assertEquals(GameColor.BLACK, game.getColor(2, 2)); 
    }
 
    /**
     * Test of play method, of class OthelloImpl. Test to place a pawn on an
     * already taken place.
     */
    @Test
    public void testPlayTakedPlace() {
        OthelloImpl game = new OthelloImpl(6, 6);
        game.setPossiblePositions();
        game.play(2, 2);
        assertEquals(GameColor.WHITE, game.getColor(2, 2));
    }

    /**
     * Test of play method, of class OthelloImpl. Test to place a pawn out of 
     * the board out the minimal border.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPlayOutOfBoardMin() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.play(-1, -1);
    }

    /**
     * Test of play method, of class OthelloImpl.Test to place a pawn out of 
     * the board out the maximal border at the right down corner.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPlayOutOfBoardMaxRightCornerDown() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.play(8, 8);
    }

    /**
     * Test of play method, of class OthelloImpl.Test to place a pawn out of 
     * the board out the maximal border at the right up corner.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPlayOutOfBoardMaxRightCornerUp() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.play(0, 8);
    }
    /**
     * Test of play method, of class OthelloImpl.Test to place a pawn out of 
     * the board out the maximal border at the left down corner.
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void testPlayOutOfBoardMaxLeftCornerUp() {
        OthelloImpl game = new OthelloImpl(8, 8);
        game.play(8, 0);
    }

    /**
     * Test of play method, of class OthelloImpl. Test to place a pawn on the 
     * minimal inside limit border of the board, on a possible position.
     */
    @Test
    public void testPlayLimitOfBoardMinOk() {
        OthelloImpl game = new OthelloImpl(4, 4);
        game.play(1, 0);
        assertEquals(GameColor.BLACK, game.getColor(1, 0));
    }

    /**
     * Test of play method, of class OthelloImpl. Test to place a pawn on the 
     * maximal inside limit border of the board, on a possible position.
     */
    @Test
    public void testPlayLimitOfBoardMaxOkMove() {
        OthelloImpl game = new OthelloImpl(4, 4);
        game.play(3, 2);
        assertEquals(GameColor.BLACK, game.getColor(3, 2));
    }

}

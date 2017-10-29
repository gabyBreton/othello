package projet.othello.breton.model;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gabriel Breton - 43397
 */
public class OthelloImplTest {
    
    public OthelloImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isOver method, of class OthelloImpl.
     */
    @Test
    public void testIsOverFull() {
        OthelloImpl game = new OthelloImpl(2, 2);
        assertTrue(game.isOver() == true);
    }

    
    /**
     * Test of getPlayers method, of class OthelloImpl.
     */
    @Test
    public void testGetPlayers() {
        System.out.println("getPlayers");
        OthelloImpl instance = null;
        List<Players> expResult = null;
        List<Players> result = instance.getPlayers();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentColor method, of class OthelloImpl.
     */
    @Test
    public void testGetCurrentColor() {
        System.out.println("getCurrentColor");
        OthelloImpl instance = null;
        Color expResult = null;
        Color result = instance.getCurrentColor();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHeight method, of class OthelloImpl.
     */
    @Test
    public void testGetHeight() {
        System.out.println("getHeight");
        OthelloImpl instance = null;
        int expResult = 0;
        int result = instance.getHeight();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWidht method, of class OthelloImpl.
     */
    @Test
    public void testGetWidht() {
        System.out.println("getWidht");
        OthelloImpl instance = null;
        int expResult = 0;
        int result = instance.getWidht();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getColor method, of class OthelloImpl.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        int x = 0;
        int y = 0;
        OthelloImpl instance = null;
        Color expResult = null;
        Color result = instance.getColor(x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanLastPlayerPossibilities method, of class OthelloImpl.
     */
    @Test
    public void testCleanLastPlayerPossibilities() {
        System.out.println("cleanLastPlayerPossibilities");
        OthelloImpl instance = null;
        instance.cleanLastPlayerPossibilities();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPossiblePositions method, of class OthelloImpl.
     */
    @Test
    public void testSetPossiblePositions() {
        System.out.println("setPossiblePositions");
        OthelloImpl instance = null;
        instance.setPossiblePositions();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of play method, of class OthelloImpl.
     */
    @Test
    public void testPlay() {
        System.out.println("play");
        int x = 0;
        int y = 0;
        OthelloImpl instance = null;
        instance.play(x, y);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

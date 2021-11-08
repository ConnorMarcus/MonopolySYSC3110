import static org.junit.Assert.*;

/**
 * MonopolyModel Test class
 * @author Vahid Foroughi
 */
public class MonopolyModelTest {

    MonopolyModel model;

    @org.junit.Before
    public void setUp() {
        model = new MonopolyModel(2);
    }

    /**
     * Test that the model's board is correct
     */
    @org.junit.Test
    public void testGetBoard(){
        //Test the amount of properties of the board
        assertEquals(40, model.getBoard().getNumProperties());
        //Test the first property of the board
        assertEquals("GO", model.getBoard().getProperty(0).getName());
        //Test the last property of the board
        assertEquals("Boardwalk", model.getBoard().getProperty(39).getName());
    }

    /**
     * Test initial turn of the game
     */
    @org.junit.Test
    public void testGetTurn() {
        assertEquals(0, model.getTurn());
    }

    /**
     * Tests that an exception is thrown when a null observer is passed.
     *
     */
    @org.junit.Test(expected = IllegalArgumentException.class)
    public void testAddMonopolyObserver(){
        model.addMonopolyObserver(null);
    }


    /**
     * Test to check that turn is being taken and player is moving
     * through the board.
     */
    @org.junit.Test
    public void testTakeTurn() {
        //Test that a player is moved when they take their turn
        int initialPosition = model.getPlayerList().get(0).getPosition();
        model.takeTurn();
        int newPosition = model.getPlayerList().get(0).getPosition();
        assertTrue(newPosition > initialPosition);
        initialPosition = newPosition;
        model.takeTurn();
        newPosition = model.getPlayerList().get(0).getPosition();
        assertTrue(newPosition > initialPosition);

        //Test to check that player is removed when they are bankrupted
        model.getPlayerList().get(0).setBankrupt(true);
        model.takeTurn();
        assertEquals(1, model.getPlayerList().size());
    }

    /**
     * Test to check that turn is being passed between the players
     */
    @org.junit.Test
    public void testPassTurn(){
        int currTurn =  model.getTurn();
        assertEquals(0, currTurn);
        model.passTurn();
        int passedTurn = model.getTurn();
        assertEquals(1, passedTurn);
        model.passTurn();
        int passBack = model.getTurn();
        assertEquals(0, passBack);
    }

    /**
     * Test to ensure that the player list contains the correct
     * amount of players chosen in the model.
     */
    @org.junit.Test
    public void testGetPlayerList(){
        assertEquals(2, model.getPlayerList().size());
        assertEquals("1", model.getPlayerList().get(0).getIdentifier());
        assertEquals("2", model.getPlayerList().get(1).getIdentifier());
    }
}
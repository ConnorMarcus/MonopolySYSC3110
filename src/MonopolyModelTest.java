import static org.junit.Assert.*;

/**
 * MonopolyModel Test class
 * @author Vahid Foroughi
 */
public class MonopolyModelTest {

    MonopolyModel model;

    @org.junit.Before
    public void setUp() throws Exception {
        model = new MonopolyModel(2);
    }


    @org.junit.After
    public void tearDown() throws Exception {
    }

    /**
     * Test that the model's board is correct
     */
    @org.junit.Test
    public void testGetBoard(){
        MonopolyBoard testBoard = new MonopolyBoard();
        //Test boards have the same amount of properties
        assertEquals(testBoard.getNumProperties(), model.getBoard().getNumProperties());
        //Test boards have same first property
        assertEquals(testBoard.getProperty(0).getName(), model.getBoard().getProperty(0).getName());
        //Test boards have same last property
        assertEquals(testBoard.getProperty(39).getName(), model.getBoard().getProperty(39).getName());
    }

    /**
     * Test initial turn of the
     */
    @org.junit.Test
    public void testGetTurn() {
        assertEquals(0, model.getTurn());
    }

    /**
     *
     *
     *
     *
     *
     *
     *
     */
    @org.junit.Test
    public void testAddMonopolyObserver(){
        //model.addMonopolyObserver(null);
    }


    /**
     * Test to check that turn is being taken and player is moving
     * through the board.
     */
    @org.junit.Test
    public void testTakeTurn(){
        // This will trigger a JOptionPane to show up, informing the user whether they want to
        // buy the property, or that the property they landed on is a free space for milestone 2.
        model.takeTurn();
        int newPosition = model.getPlayerList().get(0).getPosition();
        assertTrue(newPosition > 0);

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
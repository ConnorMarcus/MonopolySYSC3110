import static org.junit.Assert.*;

public class MonopolyModelTest {

    MonopolyModel model;
    MonopolyView view;

    @org.junit.Before
    public void setUp() throws Exception {
        //model = new MonopolyModel(2);
       //view = new MonopolyView("Monopoly Test");
    }


    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testGetBoard(){
        MonopolyBoard testBoard = new MonopolyBoard();
        //Test boards have same amount of properties
        assertEquals(testBoard.getNumProperties(), model.getBoard().getNumProperties());
        //Test boards have same first property
        assertEquals(testBoard.getProperty(0).getName(), model.getBoard().getProperty(0).getName());
        //Test boards have same last property
        assertEquals(testBoard.getProperty(39).getName(), model.getBoard().getProperty(39).getName());
    }

    /*
    @org.junit.Test
    public void testAddMonopolyObserver(){
        assertEquals(0, model.addMonopolyObserver(null););
    }

     */

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
    }

    /**
     * Test to check that turn is being passed between the players
     */
    @org.junit.Test
    public void testPassTurn(){
        model = new MonopolyModel(2);
        int currTurn =  model.getTurn();
        assertEquals(0, currTurn);
        model.passTurn();
        int passedTurn = model.getTurn();
        assertEquals(1, passedTurn);
        model.passTurn();
        int passBack = model.getTurn();
        assertEquals(0, passBack);
    }
}
import static org.junit.Assert.*;

public class MonopolyModelTest {

    MonopolyModel model;
    MonopolyView view;

    @org.junit.Before
    public void setUp() throws Exception {
        model = new MonopolyModel(2);
       //view = new MonopolyView("Monopoly Test");
    }


    @org.junit.After
    public void tearDown() throws Exception {
    }

    @org.junit.Test
    public void testGetBoard(){
        MonopolyBoard testBoard = new MonopolyBoard();
        //Test boards have same number of properties
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

}
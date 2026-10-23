import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class CellTest {

    @Test
    void cellStartsWithoutBomb() {
        Cell cell = new Cell();

        assertTrue(!cell.isBomb());
    }

    @Test
    void cellCanGetBomb() {
        Cell cell = new Cell();
        cell.setBombValue(true);

        assertTrue(cell.isBomb());
    }

    @Test
    void cellStartsWithNoOwner() {
        Cell cell = new Cell();
        
        assertTrue(!cell.isOccupied());
    }

    @Test
    void cellCanGetOwner() {
        Cell cell = new Cell();
        Player player = new Player("Chris");

        cell.setOwner(player);

        assertTrue(cell.isOccupied());
        assertTrue(cell.owner() == player);

    }
}

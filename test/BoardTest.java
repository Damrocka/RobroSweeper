import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void emptyBoardStartsWithoutBombs() {
        Board board = new Board();

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                assertFalse(board.cell(row, col).isBomb());
            }
        }
    }

    @Test
    void zeroBombChanceCreatesNoBombs() {
        Board board = new Board(0.0f);

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                assertFalse(board.cell(row, col).isBomb());
            }
        }
    }

    @Test
    void fullBombChanceFillsBoardWithBombs() {
        Board board = new Board(1.0f);

        for (int row = 0; row < board.getRows(); row++) {
            for (int col = 0; col < board.getCols(); col++) {
                assertTrue(board.cell(row, col).isBomb());
            }
        }
    }

    @Test
    void emptyColumnHasLandingRowAtBottom() {
        Board board = new Board();

        assertEquals(5, board.findLandingRow(0));
    }

    @Test
    void bombCountExcludesCenterCell() {
        Board board = new Board();

        board.cell(2, 3).setBombValue(true);

        assertEquals(0, board.findBombCount(2, 3));
    }

    @Test
    void bombCountIncludesAdjacentBombs() {
        Board board = new Board();

        board.cell(1, 1).setBombValue(true);
        board.cell(1, 2).setBombValue(true);
        board.cell(2, 2).setBombValue(true);

        assertEquals(3, board.findBombCount(2, 1));
    }

    @Test
    void bombCountWorksAtTopLeftCorner() {
        Board board = new Board();

        board.cell(0, 1).setBombValue(true);
        board.cell(1, 0).setBombValue(true);
        board.cell(1, 1).setBombValue(true);

        assertEquals(3, board.findBombCount(0, 0));
    }
}

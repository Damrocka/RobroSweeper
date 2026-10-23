import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import org.junit.jupiter.api.Test;

class GameTest {

    @Test
    void newPlayerCanBeFoundByName() {
        Game game = new Game();

        game.newPlayer("Alice");

        assertNotNull(game.getPlayer("Alice"));
        assertEquals("Alice", game.getPlayer("Alice").name());
        assertEquals(1, game.players().length);
    }

    @Test
    void duplicatePlayerNamesAreRejected() {
        Game game = new Game();

        game.newPlayer("Alice");
        game.newPlayer("Alice");

        assertEquals(1, game.players().length);
    }

    @Test
    void nonexistentPlayerReturnsNull() {
        Game game = new Game();

        assertNull(game.getPlayer("Nobody"));
    }

    @Test
    void initializationSetsFirstPlayerAndCreatesBoard() {
        Game game = new Game();
        Player first = new Player("Alice");
        Player second = new Player("Bob");

        game.init(first, second, 0.0f);

        assertNotNull(game.getBoard());
        assertSame(first, game.getCurrentPlayer());
    }

    @Test
    void correctGuessGivesPieceToCurrentPlayer() {
        Game game = new Game();
        Player first = new Player("Alice");
        Player second = new Player("Bob");

        game.init(first, second, 0.0f);

        MoveResult result = game.placePiece(0, false);

        assertNotNull(result);
        assertSame(first, game.getBoard().cell(5, 0).owner());
        assertSame(second, game.getCurrentPlayer());
    }

    @Test
    void incorrectGuessGivesPieceToOpponent() {
        Game game = new Game();
        Player first = new Player("Alice");
        Player second = new Player("Bob");

        game.init(first, second, 0.0f);

        MoveResult result = game.placePiece(0, true);

        assertNotNull(result);
        assertSame(second, game.getBoard().cell(5, 0).owner());
        assertSame(second, game.getCurrentPlayer());
    }

    @Test
    void placingPieceInFullColumnReturnsNull() {
        Game game = new Game();
        Player first = new Player("Alice");
        Player second = new Player("Bob");

        game.init(first, second, 0.0f);

        for (int i = 0; i < game.getBoard().getRows(); i++) {
            assertNotNull(game.placePiece(0, false));
        }

        assertNull(game.placePiece(0, false));
    }

    @Test
    void fourHorizontalPiecesTriggerWin() {
        Game game = new Game();
        Player first = new Player("Alice");
        Player second = new Player("Bob");

        game.init(first, second, 0.0f);

        Board board = game.getBoard();

        // Prepare three connected pieces for the first player.
        for (int col = 0; col < 3; col++) {
            board.cell(5, col).setOwner(first);
        }

        // The first player places the fourth piece.
        MoveResult result = game.placePiece(3, false);

        assertNotNull(result);
        assertSame(first, board.cell(5, 3).owner());

        // The game returns early when a win is detected,
        // before switching the current player.
        assertSame(first, game.getCurrentPlayer());
    }

}
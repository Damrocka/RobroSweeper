import java.awt.Color;
import java.util.*;

public class Game {
    
    private final ArrayList<Player> players = new ArrayList<>();
    private Board board;
    private Player redPlayer;
    private Player bluePlayer;
    private Player currentPlayer;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void newPlayer(String name) {

        for (Player player : players) {
            if (player.name().equals(name)) {
                System.out.println("That name is already in use.");
                return;
            }
        }
        players.add(new Player(name));

    }

    public Player[] players() {
        return players.toArray(Player[]::new);
    }

    public Player getPlayer(String name) {
        for (Player player : players) {
            if (player.name().equals(name)) {
                return player;
            }
        }
        return null;
    }
    
    public void init(Player first, Player second) {
        board = new Board();

        redPlayer = first;
        redPlayer.setColor(Color.RED);

        bluePlayer = second;
        bluePlayer.setColor(Color.BLUE);

        game();
    }

    public void init(Player first, Player second, Float bombDensity) {
        board = new Board(bombDensity);

        redPlayer = first;
        redPlayer.setColor(Color.RED);

        bluePlayer = second;
        bluePlayer.setColor(Color.BLUE);

        game();
    }

    public Board getBoard() {
        return board;
    }

    private void game() {

        System.out.println("%s is the red player".formatted(redPlayer.name()));
        System.out.println("%s is the blue player".formatted(bluePlayer.name()));

        currentPlayer = redPlayer;
        // implement the rest 

    }

    public MoveResult placePiece(int col, boolean bombGuess) {

        int row = board.findLandingRow(col);
        Player otherPlayer;
        
        if (row == -1) {
            
            return null;

        }

        if (currentPlayer == redPlayer) {
            otherPlayer = bluePlayer;
        } else {
            otherPlayer = redPlayer;
        }

        System.out.println(otherPlayer.name());

        if (bombGuess == board.cell(row, col).isBomb()) {
            board.cell(row, col).setOwner(currentPlayer);
        } else {
            board.cell(row, col).setOwner(otherPlayer);
        }

        Player victoriousPlayer = checkWinCondition(row, col);
        if (victoriousPlayer != null) {
            
            System.out.println("you won");
            // add win logic here, victoriousPlayer is the Player object that won
            return new MoveResult(bombGuess == board.cell(row, col).isBomb(),victoriousPlayer);
        }

        currentPlayer = otherPlayer;
        return new MoveResult(bombGuess == board.cell(row, col).isBomb(), null);
    }
    
    private Player checkWinCondition(int row, int col) {

        // returns the player who won according to the tile
        // takes coords of a tile to make it more efficient
        Player player;
        if (board.cell(row, col).owner() == null) {
            return null;
        } else {
            player = board.cell(row, col).owner();
        }

        int[][] directions = {
            {0, 1},
            {1, 0},
            {1, 1},
            {1, -1}
        };

        for (int[] direction : directions) {

            int streak = 0;
            int scanRow = row - (3 * direction[0]);
            int scanCol = col - (3 * direction[1]);

            for (int i = 0; i < 7; i++) {

                if (scanRow < 0 || scanRow >= board.getRows() || scanCol < 0 || scanCol >= board.getCols()) {
                    streak = 0;
                } else if (board.cell(scanRow, scanCol).owner() == player) {
                    streak++;
                } else {
                    streak = 0;
                }

                if (streak == 4) {
                    return player;
                }

                scanRow += direction[0];
                scanCol += direction[1];

            }

        }

        return null;

    }
}
    

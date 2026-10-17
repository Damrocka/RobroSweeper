import java.util.*;

public class Game {
    
    private final ArrayList<Player> players = new ArrayList<>();
    private final Board board = new Board(0.25f);
    private int currentPlayerIndex;
    public void newPlayer(String name, String color) {

        for (Player player : players) {
            if (player.name().equals(name)) {
                System.out.println("That name is already in use.");
                return;
            }
        }

        if (!color.equals("red") && !color.equals("blue") && !color.equals("green")) {
            System.out.println("That color is unavailable");
            return;
        }
        players.add(new Player(name, color));

    }
    
    public Player[] players() {
        return players.toArray(Player[]::new);
    }
    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }
    public Board getBoard() {

        return this.board;
    }
    
    public int placePiece(int col) {
            int row = board.findLandingRow(col);
            
            if (row!= -1 ) {
                board.cell(row, col).setOwner(getCurrentPlayer());
                switchPlayer();
            }
            return row;
        }

    public void switchPlayer() { //temporary for testing GUI color change
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
    }
    public void print() {
        board.print();
    }
}
    

import java.util.*;

public class Game {
    
    private final ArrayList<Player> players = new ArrayList<>();
    private final Board board = new Board();

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
    //push test
    public void print() {
        board.print();
    }
}
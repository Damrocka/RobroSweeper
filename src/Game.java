public class Game {
    
    private Board board;
    private Player redPlayer;
    private Player bluePlayer;

    public void print() {
        board.print();
    }

    public void init(Player first, Player second) {
        board = new Board(0.25f);
        redPlayer = first;
        bluePlayer = second;
        game();
    }

    public void init(Player first, Player second, Float bombDensity) {
        board = new Board(bombDensity);
        redPlayer = first;
        bluePlayer = second;
        game();
    }

    private void game() {

        System.out.println("%s is the red player".formatted(redPlayer.name()));
        System.out.println("%s is the blue player".formatted(bluePlayer.name()));

        // implement the rest

    }

}
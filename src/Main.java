import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {

        Game game = new Game(); 
        game.newPlayer("leon");
        game.newPlayer("chris");
        game.init(game.getPlayer("leon"), game.getPlayer("chris"), 0.3f);
                 
        SwingUtilities.invokeLater(() -> {

            GameWindow myWindow = new GameWindow();
            BoardPanel panel = new BoardPanel(game);

            myWindow.add(panel);
            //myWindow.add(myLabel);

            myWindow.pack();
            myWindow.setLocationRelativeTo(null);
            myWindow.setVisible(true);

        });

    }
}
 
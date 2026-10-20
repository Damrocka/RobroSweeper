import javax.swing.SwingUtilities;

public class Test {
    public static void main(String[] args) {

        Game game = new Game();  
                 
        SwingUtilities.invokeLater(() -> {

            Window myWindow = new Window();
            BoardPanel panel = new BoardPanel(game);

            myWindow.add(panel);
            //myWindow.add(myLabel);

            myWindow.pack();
            myWindow.setLocationRelativeTo(null);
            myWindow.setVisible(true);

        });

        // maybe later add a way to make custom players and sow a list inn the gui
        game.newPlayer("Leon");
        game.newPlayer("chris"); 

        Player first = game.getPlayer("Chris");
        Player second = game.getPlayer("Leon");

        game.init(first, second, 0.25f);
    }
}
 
import javax.swing.SwingUtilities;

public class Test {
    public static void main(String[] args) {

        Game testgame = new Game();
        Board myBoard = testgame.getBoard();
        
        testgame.newPlayer("leon", "red");
        testgame.newPlayer("chris", "blue");   
                 
        SwingUtilities.invokeLater(() -> {
            Window myWindow = new Window();
            BoardPanel panel = new BoardPanel(testgame);
            myWindow.add(panel);
            //myWindow.add(myLabel);
            myWindow.pack();
            myWindow.setLocationRelativeTo(null);
            myWindow.setVisible(true);
        });
    }
}

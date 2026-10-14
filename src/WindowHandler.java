import javax.swing.*;



public class WindowHandler {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Window myWindow = new Window();
            Board board = new Board();
            BoardPanel panel = new BoardPanel(board);
            myWindow.add(panel);
            //myWindow.add(myLabel);
            myWindow.pack();
            myWindow.setLocationRelativeTo(null);
            myWindow.setVisible(true);
         
            
            
        });

    }
    
}
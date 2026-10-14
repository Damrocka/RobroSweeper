import javax.swing.SwingUtilities;

public class Test {
    public static void main(String[] args) {
        int res;
        Board myBoard = new Board();
        Player player = new Player("one","blue");
        for (int i = 0; i < myBoard.getRows() + 2; i++) {
            res = myBoard.findLandingRow(3);
            System.out.println(res);
            if (res != -1) {
                myBoard.cell(myBoard.getRows() - 1 - i, 3).setOwner(player);
            }
            
            //TEST
        }
        SwingUtilities.invokeLater(() -> {
            Window myWindow = new Window();
            BoardPanel panel = new BoardPanel(myBoard);
            myWindow.add(panel);
            //myWindow.add(myLabel);
            myWindow.pack();
            myWindow.setLocationRelativeTo(null);
            myWindow.setVisible(true);
        });
    }
}

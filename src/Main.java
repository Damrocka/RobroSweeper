import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
            JLabel redPlayerLabel = createPlayerLabel(
                game.getPlayer("leon").name(), Color.RED
            );
            JLabel bluePlayerLabel = createPlayerLabel(
                game.getPlayer("chris").name(), Color.BLUE
            );

            JPanel gamePanel = new JPanel(new BorderLayout());
            gamePanel.add(redPlayerLabel, BorderLayout.WEST);
            gamePanel.add(bluePlayerLabel, BorderLayout.EAST);
            gamePanel.add(panel, BorderLayout.CENTER);
            myWindow.add(gamePanel);
            gamePanel.setBorder(
                BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.BLACK, 3),
                    BorderFactory.createEmptyBorder(50, 50, 50, 50)
                )
            );
            myWindow.pack();
            myWindow.setLocationRelativeTo(null);
            myWindow.setVisible(true);

        });
    }

    private static JLabel createPlayerLabel(String name, Color color) {
        JLabel label = new JLabel(name);
        label.setPreferredSize(new Dimension(100, 80));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setForeground(color);
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setBorder(BorderFactory.createLineBorder(color, 2));
        return label;
    }
}
 
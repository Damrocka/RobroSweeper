import java.awt.Color;
import javax.swing.JFrame;

public class GameWindow extends JFrame {
    
    GameWindow() {

        this.setTitle("RobroSweeper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setBackground(Color.black);
        
    }
}

import java.awt.Color;
import javax.swing.JFrame;

public class Window extends JFrame {
    
    Window() {

        this.setTitle("RobroSweeper");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(420, 420);
        this.setBackground(Color.black);
        
    }
}

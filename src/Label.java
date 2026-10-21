
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JLabel;

public class Label extends JLabel {
    Label() {
        this.setPreferredSize(new Dimension(120, 0));
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
        this.setBackground(Color.CYAN);
        this.setOpaque(true);        
    
    }
}

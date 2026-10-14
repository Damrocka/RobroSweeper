
import java.awt.Color;
import javax.swing.JLabel;

public class Label extends JLabel {
    Label() {
        this.setText("Omnomomnom");
        //this.setHorizontalTextPosition(JLabel.CENTER); // We dont have icons at the moment
        //this.setVerticalTextPosition(JLabel.TOP); //We dont have icons at the moment
        this.setHorizontalAlignment(CENTER);
        this.setVerticalAlignment(CENTER);
        this.setBackground(Color.CYAN);
        this.setOpaque(true);        
    
    }
}

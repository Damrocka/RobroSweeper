import java.awt.*;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {
    private final Board board;
    private static final int CELL_SIZE = 80;
    private static final int PADDING = CELL_SIZE / 8;
    public BoardPanel(Board board) {
        this.board = board;
        int rows = board.getRows();
        int cols = board.getCols();
        this.setBackground(Color.white);
        Dimension dim = new Dimension(cols * CELL_SIZE, rows * CELL_SIZE);
        this.setPreferredSize(dim);
    }   
    public Color StringToColor(String color) {
        Color color1;
        switch (color) {
            case "red":
                color1 = Color.red;
                break;
            case "yellow":
                color1 = Color.yellow;
                break;
            case "blue":
                color1 = Color.blue;
                break;
            default:
                color1 = Color.gray;
        }
        return color1;
    }
   @Override 
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(3)); 
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //makes the circles smoooooooothhhh
        int rows = board.getRows(); //amount of rows
        int cols = board.getCols(); //amount of cols
        g2.setColor(Color.green);
        for (int row = 0 ; row  < rows; row++){
            for (int col = 0; col < cols; col++) {
                if (board.cell(row, col).isOccupied()) {
                    g2.setColor(StringToColor(board.cell(row,col).owner().color()));
                    g2.drawRect(col  * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    g2.setColor(Color.ORANGE);
                    g2.fillOval((col  * CELL_SIZE) + PADDING, (row * CELL_SIZE) + PADDING,  CELL_SIZE - 2 * PADDING, CELL_SIZE - 2 * PADDING);
                }   else {

                    g2.setColor(Color.gray);
                    g2.drawRect(col  * CELL_SIZE, row * CELL_SIZE, CELL_SIZE, CELL_SIZE);
                    g2.setColor(Color.ORANGE);
                    g2.fillOval((col  * CELL_SIZE) + PADDING, (row * CELL_SIZE) + PADDING,  CELL_SIZE - 2 * PADDING, CELL_SIZE - 2 * PADDING);
                }        
                g2.setColor(Color.MAGENTA);
                
            }
        }
        g2.setStroke(oldStroke);
        
        
    } 
}

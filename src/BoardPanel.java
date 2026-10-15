import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private final Board board;
    private MouseListener m;
    private static final int CELL_SIZE = 80;
    private static final int PADDING = CELL_SIZE / 8;
    /**
     * 
     * 
     * 
     */

    public BoardPanel(Board board) {
        this.board = board;
        BoardClickListener meow = new BoardClickListener();  //change from meow to something meaningful like 
        this.addMouseListener(meow);
        int rows = board.getRows();
        int cols = board.getCols();
        this.setBackground(Color.white);
        Dimension dim = new Dimension(cols * CELL_SIZE, rows * CELL_SIZE);
        this.setPreferredSize(dim);
    }   

    private Color stringToColor(String color) {
        Color color1;
        switch (color) {
            case "red":
                color1 = Color.red;
                break;
            case "yellow":
                color1 = Color.green;
                break;
            case "blue":
                color1 = Color.blue;
                break;
            default:
                color1 = Color.gray;
        }
        return color1;
    }

    private class BoardClickListener extends MouseAdapter {
        Player player = new Player("leon", "red");
        int mx;
        int my;
        int colnum;
        int rownum;
        @Override 
        public void mousePressed(MouseEvent m){

            mx = m.getX();
            System.out.println(" Test my:  " + (my));
            colnum = mx / CELL_SIZE;
            System.out.println(" Test col:  " + (colnum + 1));
            rownum = onColumnClicked(colnum);
            System.out.println((rownum + 1) + " " + (colnum + 1));

            if (!board.cell(rownum, colnum ).isOccupied()) {
                board.cell(rownum, colnum).setOwner(player);
                repaint();
            } else {
                
            }
            
        }
        private int onColumnClicked(int col) {
            int row;
            row = board.findLandingRow(col);
            return row;
        }
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

                Cell cell = board.cell(row, col);
                int x = col  * CELL_SIZE;
                int y = row * CELL_SIZE;
                g2.setColor(Color.lightGray);
                g2.fillRect(x + PADDING, y + PADDING,  CELL_SIZE - 2 * PADDING, CELL_SIZE - 2 * PADDING); 

                if (cell.isOccupied()) {

                    g2.setColor(stringToColor(cell.owner().color()));
                    int bombNum;

                    if (cell.isBomb()) {
                        g.drawString("X", x + CELL_SIZE / 2, y + CELL_SIZE / 2);
            
                    } else { 

                        bombNum = board.findBombCount(row,col);
                        g.drawString(String.valueOf(bombNum), x + CELL_SIZE / 2, y + CELL_SIZE / 2);
                        //set up setFont, getFontMetrics(), stringWidth,getAscent, 
                           
                    }
                
                } 
            
            // extract and create a method drawNumber
                
            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                

            }
                
        }    //add glyph   
        g2.setStroke(oldStroke);  
    }
        
}    
        
   
     

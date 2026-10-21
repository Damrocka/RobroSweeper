import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private final Game game;
    private final Board board;
    private static final int CELL_SIZE = 80;
    private static final int PADDING = CELL_SIZE / 8;

    public BoardPanel(Game game) {

        this.game = game;
        this.board = game.getBoard();

        this.addMouseListener(new BoardClickListener());
        this.setBackground(Color.white);

        int rows = board.getRows();
        int cols = board.getCols();
        Dimension dim = new Dimension(cols * CELL_SIZE, rows * CELL_SIZE);

        this.setPreferredSize(dim);
    }   

    private class BoardClickListener extends MouseAdapter {
        
        @Override 
        public void mousePressed(MouseEvent e) {

            int mouseX = e.getX();            
            int colnum;
        
            colnum = mouseX / CELL_SIZE;  
            if (!(colnum >= 0 && colnum < board.getCols())) {
                return;
            } 

            // do the game logic here!

            //game.placePiece(colnum);
            repaint();
            
        }
    }

    private void drawBomb(Graphics2D g2, int centerX, int centerY) {

        drawCenteredText(g2, "X", centerX, centerY);
        //g2.drawString("X", x + CELL_SIZE / 2, y + CELL_SIZE / 2);

    }

    private void drawNumber(Graphics2D g2, int centerX, int centerY, int n) {
   
        int bombCount = n;    
        drawCenteredText(g2, String.valueOf(bombCount), centerX, centerY);
        //g2.drawString(String.valueOf(bombNum), centerX + CELL_SIZE / 2, centerY + CELL_SIZE / 2);

    }

    private void drawCenteredText(Graphics2D g2, String text, int centerX, int centerY) {

        int width;
        int ascent;
        
        FontMetrics fm = g2.getFontMetrics();
        
        width = fm.stringWidth(text);
        ascent = fm.getAscent();
        g2.drawString(text, centerX - (width / 2), centerY + (ascent / 2));

    }   

    @Override 
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int rows = board.getRows(); //amount of rows
        int cols = board.getCols(); //amount of cols

        int size = CELL_SIZE / 2;
        g2.setFont(new Font("SansSerif", Font.BOLD, size));

        Stroke oldStroke = g2.getStroke();

        g2.setStroke(new BasicStroke(3)); 
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //makes the circles smoooooooothhhh
        g2.setColor(Color.green);
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                Cell cell = board.cell(row, col);
                int x = col  * CELL_SIZE;
                int y = row * CELL_SIZE;

                g2.setColor(Color.lightGray);
                g2.fillRect(x + PADDING, y + PADDING,  CELL_SIZE - 2 * PADDING, CELL_SIZE - 2 * PADDING); 

                g2.setColor(Color.BLACK);
                g2.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                
                if (cell.isOccupied()) {
                    int centerX = x + CELL_SIZE / 2;
                    int centerY = y + CELL_SIZE / 2;
                    int n;
                    g2.setColor(cell.owner().color());
                    
                    if (cell.isBomb()) {
                        
                        drawBomb(g2, centerX, centerY);
                    } else { 
                        n = board.findBombCount(row, col);
                        drawNumber(g2, centerX, centerY, n);
                        //set up setFont, getFontMetrics(), stringWidth,getAscent, 
                    } 
                }        
            }
        }
        g2.setStroke(oldStroke);  
    }
}
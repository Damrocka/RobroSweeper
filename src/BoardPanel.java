import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
public class BoardPanel extends JPanel {

    
    private final Game game;
    private final Board board;
    private static final int CELL_SIZE = 80;
    private static final int PADDING = CELL_SIZE / 8;
    
    /**
     * 
     * 
     * 
     */

    public BoardPanel(Game game) {
        this.game = game;
        this.board = game.getBoard();
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
            case "green":
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
    private void  drawBomb (Graphics2D g2, int centerX, int centerY) {

                drawCenteredText(g2, "X", centerX, centerY);
                //g2.drawString("X", x + CELL_SIZE / 2, y + CELL_SIZE / 2);
        
        }
    private void  drawNumber (Graphics2D g2, int centerX, int centerY, int n) {

                
                int bombNum;    
                bombNum = n;
                drawCenteredText(g2 , String.valueOf(bombNum) , centerX, centerY);
                //g2.drawString(String.valueOf(bombNum), centerX + CELL_SIZE / 2, centerY + CELL_SIZE / 2);
        
        }
    private void drawCenteredText(Graphics2D g2, String text, int cx, int cy) {

        int width;
        int ascent;
       
        
        FontMetrics fm = g2.getFontMetrics();
        
        width = fm.stringWidth(text);
        ascent = fm.getAscent();
        g2.drawString(text, cx - (width / 2), cy + (ascent/ 2) );

    }   

    private class BoardClickListener extends MouseAdapter {
          
        @Override 
        public void mousePressed(MouseEvent e){
            int mx;
            mx = e.getX();            
            int colnum;
          
            colnum = mx / CELL_SIZE;  
            if (!(colnum >= 0 && colnum < board.getCols())){
                return;
            } 

            game.placePiece(colnum);
            repaint();
            
        }
    
    }

    @Override 
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int size = CELL_SIZE / 2;
        g2.setFont(new Font("SansSerif", Font.BOLD,size));
        Stroke oldStroke = g2.getStroke();
        g2.setStroke(new BasicStroke(3)); 
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON); //makes the circles smoooooooothhhh
        int rows = board.getRows(); //amount of rows
        int cols = board.getCols(); //amount of cols
        g2.setColor(Color.green);
        
        for (int row = 0 ; row  < rows; row++){
            for (int col = 0; col < cols; col++) {
                int x = col  * CELL_SIZE;
                int y = row * CELL_SIZE;
                Cell cell = board.cell(row, col);
                g2.setColor(Color.lightGray);
                g2.fillRect(x + PADDING, y + PADDING,  CELL_SIZE - 2 * PADDING, CELL_SIZE - 2 * PADDING); 
                g2.setColor(Color.BLACK);
                g2.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                
                if (cell.isOccupied()) {
                        int centerX = x + CELL_SIZE / 2;
                        int centerY = y + CELL_SIZE / 2;
                        int n;
                        g2.setColor(stringToColor(cell.owner().color()));
                        
                        if (cell.isBomb()) {
                            
                            drawBomb(g2, centerX, centerY);
                        } else { 
                            n = board.findBombCount(row, col);
                            drawNumber(g2,centerX, centerY, n);
                        //set up setFont, getFontMetrics(), stringWidth,getAscent, 

                    } 
                }        
            }
        }
        g2.setStroke(oldStroke);  
    }
}    
        
   
     

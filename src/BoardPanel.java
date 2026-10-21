import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BoardPanel extends JPanel {

    private final Game game;
    private final Board board;
    private MoveResult moveResult;
    private boolean gameOver = false;
    private final BufferedImage redBombImage;
    private final BufferedImage blueBombImage;
    private static final int CELL_SIZE = 80;
    private static final int PADDING = CELL_SIZE / 8;


    public BoardPanel(Game game) {

        this.game = game;
        this.board = game.getBoard();
        try {
            redBombImage = ImageIO.read(getClass().getResource("/img/redbomb.png"));
            blueBombImage = ImageIO.read(getClass().getResource("/img/bluebomb.png"));
        } catch (IOException | NullPointerException exception) {
            throw new IllegalStateException("Could not load bomb images", exception);

        }
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
            if (gameOver) {
                return;
            }
            if (!(colnum >= 0 && colnum < board.getCols())) {
                return;
            } 

            if (board.findLandingRow(colnum) == -1) {
                return;
            }
        
            if (e.getButton() == MouseEvent.BUTTON1) {
                
                moveResult = game.placePiece(colnum, false);
        
            } else if (e.getButton() == MouseEvent.BUTTON3) {
                
                moveResult = game.placePiece(colnum, true);
                
            } else {
                return;
            }

            if (moveResult == null) {
                return;
            }

            if (!moveResult.correct()) {
                showExplosion();
            }


            if (moveResult.winner() != null) {
                gameOver = true;
                showWinner(moveResult.winner());
            }
            
            repaint();   
        }
    }    
            
    private void drawBomb(Graphics2D g2, int centerX, int centerY, Player owner) {
        
        int imageSize = CELL_SIZE - 2 * PADDING;
        int imageX = centerX - imageSize / 2;
        int imageY = centerY - imageSize / 2;
        if (owner.color().equals(Color.red)) {
            g2.drawImage(redBombImage, imageX, imageY, imageSize, imageSize, null);
        } else {
            g2.drawImage(blueBombImage, imageX, imageY, imageSize, imageSize, null);
        }
    } 

    private void showWinner(Player winner) {
        String[] winnerGifs = {
            "/img/hampterConfettiWin.gif",
            "/img/lifegoodhampter.gif",
            "/img/fishspinconfetti.gif"
        };
        int randomIndex = ThreadLocalRandom.current().nextInt(winnerGifs.length);
        String selectedGif = winnerGifs[randomIndex];
        URL gifUrl = getClass().getResource(selectedGif);
        
        if (gifUrl == null) {
            throw new IllegalStateException(
                "Could not load winner jif: " + selectedGif
            );
        } 

        ImageIcon gifIcon = new ImageIcon(gifUrl);
        JLabel gifLabel = new JLabel(gifIcon);
        JLabel winnerLabel = new JLabel(winner.name() + " wins!" + JLabel.CENTER);

        JPanel content = new JPanel(new BorderLayout());
        content.add(winnerLabel, BorderLayout.NORTH);
        content.add(gifLabel, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(this, content, "Game over", JOptionPane.PLAIN_MESSAGE);
    }

    private void showExplosion() {
        URL gifUrl = getClass().getResource("/img/explosionMeme.gif");

        if (gifUrl == null) {
            throw new IllegalStateException(
                "Could not load jif"
            );
        
        } 
        ImageIcon gifIcon = new ImageIcon(gifUrl);
        JLabel gifLabel = new JLabel(gifIcon);

        JOptionPane.showMessageDialog(this, gifLabel, "Wrong guess", JOptionPane.PLAIN_MESSAGE);
        
    }

    private void drawNumber(Graphics2D g2, int centerX, int centerY, int bombCount) {
        drawCenteredText(g2, String.valueOf(bombCount), centerX, centerY);        
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
                int centerX = x + CELL_SIZE / 2;
                int centerY = y + CELL_SIZE / 2;

                g2.setColor(Color.lightGray);
                g2.fillRect(x + PADDING, y + PADDING,  CELL_SIZE - 2 * PADDING, CELL_SIZE - 2 * PADDING); 
                g2.setColor(Color.BLACK);
                g2.drawRect(x, y, CELL_SIZE, CELL_SIZE);
                
                if (cell.isOccupied()) {
                    int n;
                    g2.setColor(cell.owner().color());
                    n = board.findBombCount(row, col);
                    if (cell.isBomb()) {

                        drawBomb(g2, centerX, centerY, cell.owner());

                    } else { 
                        
                        drawNumber(g2, centerX, centerY, n);
                        
                    } 
                }        
            }
        }
        g2.setStroke(oldStroke);  
    }
}
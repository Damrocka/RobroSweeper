public class Board {

    private static final int ROWS = 6;
    private static final int COLS = 7;
    // i know im so funny

    private final Cell[][] cells;

    // make a board with no bombs
    public Board() {

        cells = new Cell[ROWS][COLS];

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                cells[row][col] = new Cell();

            }
        }
    }

    // make a board with bombs, bomb density is a value from 0.0 to 1.0 with 0.0 for no bombs, 1.0 for 100% bombs
    public Board(float bombDensity) {
        
        cells = new Cell[ROWS][COLS];

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {

                cells[row][col] = new Cell();
                if (Math.random() < bombDensity) {
                    cells[row][col].setBombValue(true);
                }

            }
        }
    }

    public Cell cell(int row, int col) {
        return cells[row][col];
    }

    public int getRows() {
        return ROWS;
    }

    public int getCols() {
        return COLS;
    }

    public int findLandingRow(int col) {
        for (int i = ROWS - 1; i >= 0; i--) { // THE LOOP RUNS BACKWORDS
            if (!cells[i][col].isOccupied()) {
                return i; // returns the next free space in a column
            }   
        }
        return -1; //-1 means the column is full
    }


    public void print() {
        
        String firstLine = "╔";
        for (int col = 0; col < COLS; col++) {
            firstLine += "═══";

            if (col < COLS - 1) {
                firstLine += "╦";
            }
        }
        firstLine += "╗";

        String midLine = "╠";
        for (int col = 0; col < COLS; col++) {
            midLine += "═══";

            if (col < COLS - 1) {
                midLine += "╬";
            }
        }
        midLine += "╣"; 

        String lastLine = "╚";
        for (int col = 0; col < COLS; col++) {
            lastLine += "═══";

            if (col < COLS - 1) {
                lastLine += "╩";
            }
        }
        lastLine += "╝";


        System.out.println(firstLine);
        for (int row = 0; row < ROWS; row++) {

            String valueRow = "║";
            for (int col = 0; col < COLS; col++) {

                Cell cell = cells[row][col];
                String character = " ";

                // to color the tile
                if (true) { // cell.owner() != null
                    
                    if (cell.isBomb()) {
                        character = "X";
                    } else {
                        int bombCount = findBombCount(row, col);
                        if (bombCount != 0) {
                            character = "" + bombCount;
                        }
                    }

                    String colorCode = "\u001B[34m"; //cells[row][col].owner().colorCode();
                    String colorEnd = "\u001B[0m";

                    character = colorCode + character + colorEnd;

                }

                valueRow += " %s ║".formatted(character);
            }

            System.out.println(valueRow);

            if (row < ROWS - 1) {
                System.out.println(midLine);
            }

        }
        System.out.println(lastLine);

    }

    public int findBombCount(int row, int col) {

        int bombCount = 0;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {

                boolean oob = (i < 0 || i >= ROWS) || (j < 0 || j >= COLS);
                boolean center = (i == row && j == col);

                if ((!oob && !center) && cells[i][j].isBomb()) {
                    bombCount++;
                }

            }
        }

        return bombCount;

    }
}
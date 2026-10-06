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
                valueRow += " %s ║".formatted(
                    cells[row][col].owner() == null
                        ? " "
                        : cells[row][col].owner().color()
                );
            }

            System.out.println(valueRow);

            if (row < ROWS - 1) {
                System.out.println(midLine);
            }

        }
        System.out.println(lastLine);


    }
}
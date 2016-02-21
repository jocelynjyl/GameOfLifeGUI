package GameModel;

/* Enumeration for the 9 Cells ("NeighborCell") around a given Cell, that determines its state in the next generation
 */

public enum NeighborCell {
    TOP_LEFT, TOP_CENTER, TOP_RIGHT, RIGHT, LEFT, BOTTOM_LEFT, BOTTOM_CENTER, BOTTOM_RIGHT;

    // EFFECTS: returns the state of the NeighborCell at specified position
    public static boolean getNeighborCellState(NeighborCell neighbor, Cell [][] board, int rows, int columns) {
        boolean state = false;
        switch (neighbor) {
            case TOP_LEFT:
                if (!(rows == 0 || columns == 0)) {
                    state = board [rows-1][columns-1].getCurrentState();
                }

                break;
            case TOP_CENTER:
                if (rows != 0) {
                    state = board [rows-1][columns].getCurrentState();
                }
                break;
            case TOP_RIGHT:
                if (!(rows == 0 || columns == board[0].length-1)) {
                    state = board [rows-1][columns+1].getCurrentState();
                }
                break;
            case LEFT:
                if (columns != 0) {
                    state = board [rows][columns-1].getCurrentState();
                }
                break;
            case RIGHT:
                if (columns != board[0].length-1) {
                    state = board [rows][columns+1].getCurrentState();
                }
                break;
            case BOTTOM_LEFT:
                if (!(rows == board.length-1 || columns == 0)) {
                    state = board [rows+1][columns-1].getCurrentState();;
                }
                break;
            case BOTTOM_CENTER:
                if (rows != board.length-1) {
                    state = board [rows+1][columns].getCurrentState();;
                }
                break;
            case BOTTOM_RIGHT:
                if (!(rows == board.length-1 || columns == board[0].length-1)) {
                    state = board [rows+1][columns+1].getCurrentState();
                }
                break;
        }
        return state;
    }


}

package GameModel;

/*  2D array of Cell objects that represent the game board
    invariant: length of each row must be identical, and length of each column must be identical
 */

import javax.swing.*;

public class Grid extends JPanel {
	private Cell [][] grid;
    private int rows;
    private int columns;
	private int generation = 0;
    private int liveCells = 0;
    private Cell[][] next;

    // Constructor: initializes a new Grid with specified columns and rows.
    //EFFECTS: creates a new Cell in each position in the Grid
    public Grid (int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
		grid = new Cell [rows][columns];
		for (int i = 0; i < rows; i ++)
		{
			for (int j = 0; j < columns ; j++)
			{
				grid[i][j] = new Cell();
			}
		}
		
	}

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public boolean getCellState (int row, int column) {
        return grid[row][column].getCurrentState();
    }

    // MODIFIES: this
    // EFFECTS: sets Cell at given row and column to be alive.
    public void setInitial (int row, int column) {
        grid[row][column].setLive();
	}

    //MODIFIES: this
    //EFFECTS: checks every Cell in the Grid and sets them to the next state as outlined by Game of Life rules.
    //         Add 1 to the generation counter.
	public void nextGeneration () {
        next = new Cell[getRows()][getColumns()];
        for (int i = 0; i< this.rows; i++) {
            for (int j = 0; j< this.columns; j++){
                next[i][j] = getNextState(grid, i, j);
                liveCells =0;
            }
        }
        grid = next;
		generation++;
	}
	
	//EFFECTS: prints every Cell in the Grid to the console
	public void printGame () {
        System.out.println ("Generation: "+generation);
		for (int numRow =0; numRow < rows; numRow++)
		{
			for (int numColumn = 0; numColumn < columns; numColumn++)
			{
				grid[numRow][numColumn].printCell();
			}
			System.out.println ();
		}
		
	}

    /* EFFECTS: Returns the state of a Cell at given row and column in the next generation based on the following:
                If a live Cell is surrounded by less than 2 live Cells, it dies from underpopulation.
                If a live Cell is surrounded by more than 3 live Cells, it dies from overpopulation.
                If a live Cell is surrounded by 2 or 3 live Cells, it survives.
                If a dead Cell is surrounded by 3 live Cells, it becomes alive from reproduction.
    */
    public Cell getNextState(Cell[][] board, int row, int column) {
        countSurroundingCells(board, row, column);
        Cell nextCell = new Cell();

        if ((liveCells <2 || liveCells >3) && board[row][column].getCurrentState())
        {
            nextCell.setDead();
        }
        else if ((liveCells == 2 || liveCells == 3) && board [row][column].getCurrentState())
        {
            nextCell.setLive();
        }
        else if (liveCells ==3 && !board[row][column].getCurrentState())
        {
             nextCell.setLive();
        }

        return nextCell;

    }

    // EFFECTS: returns the number of live Cells adjoining a Cell at given row and column.
    private int countSurroundingCells(Cell[][] board, int row, int column) {
        for (NeighborCell neighbor: NeighborCell.values()){
            if (NeighborCell.getNeighborCellState(neighbor, board, row, column)){
                liveCells++;
            }
        }
        return liveCells;
    }
}
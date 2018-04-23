import java.util.ArrayList;

/**
 * Class that implements Depth First Search on a generated maze
 * 
 * @author Rui Yang, Gurdev Sihra
 *
 */
public class DepthFirstSearch {

	private int dfsCounter; // Counter for DFS
	private Cells[][] mazeGrid; // Double array of maze
	CellColor black = CellColor.BLACK; // CellColor enum for black
	CellColor white = CellColor.WHITE; // CellColor enum for white
	CellColor grey = CellColor.GREY; // CellColor enum for grey
	private int DIM; // Dimensions of the maze

	/**
	 * Constructor that initializes the dimensions of the maze, the maze double
	 * array, and the dfsCounter
	 * 
	 * @param m
	 */
	public DepthFirstSearch(Maze m) {
		mazeGrid = m.getGrid();
		dfsCounter = -1;
		DIM = m.getDIM();
	}

	/**
	 * Method that carries out DFS
	 */
	public void dfs() {
		initialize(0, 0);
	}

	/**
	 * Method that sets all the cells color/visibility to white
	 * 
	 * @param startRow
	 *            - starting row of the maze
	 * @param startCol
	 *            - starting column of the maze
	 */
	public void initialize(int startRow, int startCol) {
		setAllWhite();
		dfsSolution(mazeGrid[startRow][startCol]);
	}

	/**
	 * Method that recursively updates the current cell and find its enighbors
	 * 
	 * @param cell
	 *            - visited cell (current)
	 */
	public void dfsSolution(Cells cell) {

		// Current cell is grey because its being visited
		cell.setColor(grey);

		// DFS counter assigned to cell
		counter(cell);

		// Cells array created to hold valid neighbors of the current cell
		Cells[] neighbors = validNeighbors(cell);

		// Base case, if neighbors == null then the program has reached the ending point
		if (neighbors == null) {

			// Printing the maze allow the program to display the correct solution
			printSolvedMaze();
		} else {

			// For normal cells, the maze goes through each neighbor of the cell
			for (Cells c : neighbors) {
				if (c != null) {
					if (c.getColor() == white) {

						// Recursive call for the current cell's valid neighbor
						dfsSolution(c);
					}

				}
			}
		}

		// Cell has been visited and turns black
		cell.setColor(black);
	}

	/**
	 * Method that sets all the cells to white because they have not been visited
	 */
	private void setAllWhite() {
		for (int row = 0; row < mazeGrid.length; row++) {
			for (int col = 0; col < mazeGrid[row].length; col++) {
				mazeGrid[row][col].setColor(white);
			}
		}
	}

	/**
	 * Method that keeps track of the DFS counter for each cell
	 * 
	 * @param c-
	 *            cell to be assigned a DFS value
	 */
	private void counter(Cells c) {
		if (dfsCounter == 9) {
			dfsCounter = 0;
			c.setDFS(0);
		} else {
			dfsCounter++;
			c.setDFS(dfsCounter);
		}
	}

	/**
	 * Method that returns a list of valid neighbors on the south, east, west and
	 * north side of the current cell by checking if they're white and there's no
	 * wall between the two cells
	 * 
	 * @param c
	 *            - current cell
	 * @return list of valid neighbor cells
	 */
	private Cells[] validNeighbors(Cells c) {
		Cells[] neighbors = new Cells[4];

		if (!c.getSouth()) {

			// Finds base case for program when the current cell is at the last row and
			// there's an opening in the south
			if (c.getX() == (DIM - 1)) {
				return null;
			}
			if (mazeGrid[c.getX() + 1][c.getY()].getColor() == white) {
				neighbors[0] = (mazeGrid[c.getX() + 1][c.getY()]);
			}
		}
		if (!c.getEast()) {
			if (mazeGrid[c.getX()][c.getY() + 1].getColor() == white) {
				neighbors[1] = (mazeGrid[c.getX()][c.getY() + 1]);
			}
		}

		if (!c.getWest()) {
			if (mazeGrid[c.getX()][c.getY() - 1].getColor() == white) {
				neighbors[2] = (mazeGrid[c.getX()][c.getY() - 1]);
			}
		}
		if (!c.getNorth() && (c.getX() != 0 && c.getY() != 0)) {
			if (mazeGrid[c.getX() - 1][c.getY()].getColor() == white) {
				neighbors[3] = (mazeGrid[c.getX() - 1][c.getY()]);
			}
		}
		return neighbors;
	}

	/**
	 * Method that prints the maze solved through DFS
	 */
	public void printSolvedMaze() {
		System.out.println();
		System.out.println();
		System.out.println();

		for (int i = 0; i < DIM; i++) {
			for (int j = 0; j < DIM; j++) {
				if (mazeGrid[i][j].getNorth() == true) {
					System.out.print("+-");
				} else
					System.out.print("+ ");
			}
			System.out.println("+");
			for (int j = 0; j < DIM; j++) {
				if (mazeGrid[i][j].getWest() == true) {
					if (mazeGrid[i][j].dfs() > -1) {
						System.out.print("|" + mazeGrid[i][j].dfs() + "");
					} else {
						System.out.print("| ");
					}
				}
				if (mazeGrid[i][j].getWest() == false) {
					if (mazeGrid[i][j].dfs() > -1) {
						System.out.print(" " + mazeGrid[i][j].dfs());
					} else {
						System.out.print("  ");
					}
				}
			}
			System.out.println("|");
		}
		for (int j = 0; j < DIM - 1; j++) {
			if (mazeGrid[DIM - 1][j].getSouth() == true) {
				System.out.print("+-");
			} else
				System.out.print("+ ");
		}
		System.out.print("+ +");
	}
}

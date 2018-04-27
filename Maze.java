package sjsu.yang.cs146.project2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * The Maze class holds together a set amount of cells specified by the user. It
 * generates and prints maze's based on the dimensions.
 *
 * @author Rui Yang, Gurdev Sihra
 * @version sjsu-cs-146-project2
 * @since 04/02/2018
 */
public class Maze {
    private Cells[][] grid; // 2d array which is the maze
    private ArrayList<Cells> neighborList; // An ArrayList that contains the cell's valid neighbors
    private int X; // Row in grid
    private int Y; // Column in grid
    private int DIM; // Dimensions for the Maze
    private Random random;

    /**
     * Constructor that generates a 2d array(grid) with cells and sets the position
     * of each cell when a new Maze is made
     *
     * @param dimension
     *            - dimension of the maze
     */
    public Maze(int dimension, int randomSeed) {
        random = new java.util.Random(randomSeed);
        DIM = dimension;
        if (DIM == 4 || DIM == 5 || DIM == 6 || DIM == 7 || DIM == 8) {
            this.grid = new Cells[DIM][DIM];
            initializeCells();
            setCellsCordinates(grid);
            generateMaze();
        } else {
            System.out.println("Must use a dimension from 4 - 8");
        }

    }

    /**
     * Getter method for Maze's dimensions
     *
     * @return Maze dimension
     */
    public int getDIM() {
        return DIM;
    }

    /**
     * Getter method for the Maze's double array implementation
     *
     * @return double array of the Maze
     */
    public Cells[][] getGrid() {
        return grid;
    }

    /**
     * Method that generates a grid with cell objects
     */
    public void initializeCells() {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                this.grid[i][j] = new Cells();
            }
        }
    }

    /**
     * Method used to set where to start and where to exit. starts at position(0,0)
     * in the grid ends at position(dim - 1, dim - 1) in the grid
     */
    public void setStartEnd() {
        grid[0][0].setNorth(false);
        grid[DIM - 1][DIM - 1].setSouth(false);
    }

    /**
     * Method that generates a maze
     */
    public void generateMaze() {

        X = random.nextInt(grid.length);
        Y = random.nextInt(grid.length);

        // A new CellStack (LIFO) to hold a list of cell locations
        Stack<Cells> cellsStack = new Stack<>(); //

        // The total number of cells in the DIM by DIM grid
        int TotalCells = DIM * DIM;

        // Starting cell
        Cells CurrentCell = grid[X][Y];
        int VisitedCells = 1;
        ArrayList<Cells> neighborCellList;

        // Loops through the neighbors of the CurrentCell with all the walls intact
        while (VisitedCells < TotalCells) {
            neighborCellList = findNeighborCells(CurrentCell);
            if (neighborCellList.size() >= 1) {

                // Choosing a random cell to knock a wall
                int r1 = random.nextInt(neighborCellList.size());
                Cells tempCell = neighborCellList.get(r1);
                removeWalls(CurrentCell, tempCell);

                // Pushing the CurentCell location onto the stack
                cellsStack.push(CurrentCell);
                CurrentCell = tempCell;
                VisitedCells++;
            } else {

                // Popping the most recent cell off the stack
                CurrentCell = cellsStack.pop();
            }

        }
        printMaze();
    }

    /**
     * method that sets each cell with it respective x and y position in the grid.
     *
     * @param a
     *            - new coordinates
     */
    public void setCellsCordinates(Cells[][] a) {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                a[i][j].setX(i);
                a[i][j].setY(j);
            }
        }
    }

    /**
     * Method to find all the neighbor cells with all walls intact
     *
     * @param a
     *            - current cell
     * @return a list of neighbors of the current cell
     */
    public ArrayList<Cells> findNeighborCells(Cells a) {
        neighborList = new ArrayList<>();
        Cells temp;

        // top
        if (a.getY() - 1 >= 0 && grid[a.getX()][a.getY() - 1].checkWalls() == true) {
            temp = grid[a.getX()][a.getY() - 1];
            neighborList.add(temp);
        }
        // bottom
        if (a.getY() + 1 < DIM && grid[a.getX()][a.getY() + 1].checkWalls() == true) {
            temp = grid[a.getX()][a.getY() + 1];
            neighborList.add(temp);
        }
        // left
        if (a.getX() - 1 >= 0 && grid[a.getX() - 1][a.getY()].checkWalls() == true) {
            temp = grid[a.getX() - 1][a.getY()];

            neighborList.add(temp);
        }
        // right
        if (a.getX() + 1 < DIM && grid[a.getX() + 1][a.getY()].checkWalls() == true) {
            temp = grid[a.getX() + 1][a.getY()];
            neighborList.add(temp);
        }

        return neighborList;
    }

    /**
     * Method that removes the wall between the current cell and its selected
     * neighbor
     *
     * @param current
     *            - current cell
     * @param neighbor
     *            - neighbor of the current cell
     */
    public void removeWalls(Cells current, Cells neighbor) {

        // neighbor is the top neighbor cell of the current
        if (current.getX() - 1 >= 0 && current.getX() - 1 == neighbor.getX()) {
            current.setNorth(false);
            neighbor.setSouth(false);
        }
        // neighbor is the bottom neighbor cell of the current
        else if (current.getX() + 1 < DIM && current.getX() + 1 == neighbor.getX()) {
            current.setSouth(false);
            neighbor.setNorth(false);
        }
        // neighbor is the left neighbor cell of the current
        else if (current.getY() - 1 >= 0 && current.getY() - 1 == neighbor.getY()) {
            current.setWest(false);
            neighbor.setEast(false);
        }
        // neighbor is the right neighbor cell of the current
        else if (current.getY() + 1 < DIM && current.getY() + 1 == neighbor.getY()) {
            current.setEast(false);
            neighbor.setWest(false);
        }
    }

    /**
     * Method that prints the maze
     */
    public void printMaze() {
        System.out.println();
        setStartEnd();
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (grid[i][j].getNorth() == true) {
                    System.out.print("+---");

                } else
                    System.out.print("+   ");
            }
            System.out.println("+");
            for (int j = 0; j < DIM; j++) {
                if (grid[i][j].getWest() == true) {
                    System.out.print("|   ");
                }
                if (grid[i][j].getWest() == false) {
                    System.out.print("    ");
                }
            }
            System.out.println("|");
        }
        for (int j = 0; j < DIM - 1; j++) {
            if (grid[DIM - 1][j].getSouth() == true) {
                System.out.print("+---");
            } else
                System.out.print("+   ");
        }
        System.out.print("+   +");
    }

}
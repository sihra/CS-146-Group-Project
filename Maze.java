package sjsu.yang.cs146.project2;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

/**
 * The HelloWorld program implements an application that
 * simply displays "Hello World!" to the standard output.
 *
 * @author Rui Yang, Gurdev Sihra
 * @version sjsu-cs-146-project2
 * @since 04/02/2018
 */
public class Maze {
    private Cells[][] grid;
    private ArrayList<Cells> neighborList;
    private int value;
    private int X;
    private int Y;
    private int DIM = 4;

    public Maze() {
        this.grid = new Cells[DIM][DIM];
        initializeCells();
        setCellsCordinates(grid);
        generateMaze();
    }

    public Cells[][] getGrid() {
        return grid;
    }

    public void initializeCells() {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                this.grid[i][j] = new Cells();
            }
        }
    }

    public void setStartEnd() {
        grid[0][0].setNorth(false);
        grid[3][3].setSouth(false);
//        grid[0][0].setEast(false);
//        grid[0][1].setWest(false);
//        grid[1][1].setNorth(false);
//        grid[2][1].setNorth(false);
    }

    public void generateMaze() {
//        create a CellStack (LIFO) to hold a list of cell locations
//        set TotalCells= number of cells in grid
//        choose the starting cell and call it CurrentCell
//        set VisitedCells = 1

//
//        while VisitedCells < TotalCells
//              find all neighbors of CurrentCell with all walls intact
//              if one or more found choose one at random
//                       1). knock down the wall between it and CurrentCell
//                       2). push CurrentCell location on the CellStack
//                       3). make the new cell CurrentCell
//                       4). add 1 to VisitedCells
//              else
//              pop the most recent cell entry off the CellStack make it CurrentCell
        Random random = new Random();

        X = random.nextInt(grid.length);
        Y = random.nextInt(grid.length);


        Stack<Cells> cellsStack = new Stack<>(); //
        int TotalCells = DIM * DIM;
        Cells CurrentCell = grid[X][Y];
        int VisitedCells = 1;
        ArrayList<Cells> neighborCellList;

        while (VisitedCells < TotalCells) {
            neighborCellList = findNeighborCells(CurrentCell);
            if (neighborCellList.size() >= 1) {


                int r1 = random.nextInt(neighborCellList.size());
                Cells tempCell = neighborCellList.get(r1);
                removeWalls(CurrentCell, tempCell);
                cellsStack.push(CurrentCell);
                System.out.print("(" + CurrentCell.getX() + "," + CurrentCell.getY() + ")" + " ");
                CurrentCell = tempCell;
                VisitedCells++;
            } else {
                CurrentCell = cellsStack.pop();
            }

        }

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
            if (grid[3][j].getSouth() == true) {
                System.out.print("+---");
            } else
                System.out.print("+   ");

        }
        System.out.print("+   +");


    }

    public void setCellsCordinates(Cells[][] a) {
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                a[i][j].setX(i);
                a[i][j].setY(j);
            }
        }
    }


    public ArrayList<Cells> findNeighborCells(Cells a) {

        neighborList = new ArrayList<>();
        Cells temp;

        //top
        if (a.getY() - 1 >= 0 && grid[a.getX()][a.getY() - 1].checkWalls() == true) {
            temp = grid[a.getX()][a.getY() - 1];
            neighborList.add(temp);
        }
        //bottom
        if (a.getY() + 1 < DIM && grid[a.getX()][a.getY() + 1].checkWalls() == true) {
            temp = grid[a.getX()][a.getY() + 1];
            neighborList.add(temp);
        }
        //left
        if (a.getX() - 1 >= 0 && grid[a.getX() - 1][a.getY()].checkWalls() == true) {
            temp = grid[a.getX() - 1][a.getY()];

            neighborList.add(temp);
        }
        //right
        if (a.getX() + 1 < DIM && grid[a.getX() + 1][a.getY()].checkWalls() == true) {
            temp = grid[a.getX() + 1][a.getY()];
            neighborList.add(temp);
        }

        return neighborList;
    }

    public void removeWalls(Cells current, Cells neighbor) {

        // neighbor is the top neighbor cell of the current
        if (current.getX() - 1 >= 0 && current.getX() - 1 == neighbor.getX()) {
            current.setNorth(false);
            neighbor.setSouth(false);
        }
        //neighbor is the bottom neighbor cell of the current
        else if (current.getX() + 1 < 4 && current.getX() + 1 == neighbor.getX()) {
            current.setSouth(false);
            neighbor.setNorth(false);
        }
        //neighbor is the left neighbor cell of the current
        else if (current.getY() - 1 >= 0 && current.getY() - 1 == neighbor.getY()) {
            current.setWest(false);
            neighbor.setEast(false);
        }
        //neighbor is the right neighbor cell of the current
        else if (current.getY() + 1 < 4 && current.getY() + 1 == neighbor.getY()) {
            current.setEast(false);
            neighbor.setWest(false);
        }
    }


}
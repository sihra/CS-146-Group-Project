package sjsu.yang.cs146.project2;

import java.util.ArrayList;

/**
 * Class that implements Breadth First Search on a generated maze
 *
 * @author Rui Yang, Gurdev Sihra
 */
public class BreadthFirstSearch {

    public int bfsCounter; // Counter for BFS
    private Node root; // The start of a Linked list used as a queue
    private Cells[][] mazeGrid; // Double array of maze
    CellColor black = CellColor.BLACK; // CellColor enum for black
    CellColor white = CellColor.WHITE; // CellColor enum for white
    CellColor grey = CellColor.GREY; // CellColor enum for grey
    private int DIM; // Dimensions of the maze

    /**
     * Constructor that initializes the dimensions of the maze, the maze double
     * array, and the bfsCounter
     *
     * @param maze
     */
    public BreadthFirstSearch(Maze maze) {
        mazeGrid = maze.getGrid();
        bfsCounter = -1;
        DIM = maze.getDIM();
        mazeGrid[0][0].parent = null;
    }

    /**
     * Method that carries out BFS
     */
    public void bfs() {
        bfsSolution(0, 0);
        solveTheBSFPath();
        printBFSPath();

    }

    /**
     * Method that implements the BFS algorithm on the maze
     *
     * @param startRow
     *            - starting row of the cell
     * @param startCol
     *            - starting column of the cell
     */
    private void bfsSolution(int startRow, int startCol) {

        // All cells are set to not visited/ white
        setAllWhite();

        // Find cell specified in parameter
        Cells cell = mazeGrid[startRow][startCol];

        // Cell enqueued in Linked List
        enqueue(cell);

        // Cell's visibility set to being visited
        cell.setColor(grey);

        // Loops through cell's neighbors
        while (root != null) {

            // Dequeue cell from Linked List
            cell = dequeue();

            // The cell is assigned a BFS value
            counter(cell);
            ArrayList<Cells> neighbors = validNeighbors(cell);

            // Base Vase - if there are no more neighbors, the maze is printed
            if (neighbors == null) {
                printSolvedMaze();
            } else {

                // Loops through neighbors and enqueues them/visits them
                for (int i = 0; i < neighbors.size(); i++) {
                    Cells currentCell = neighbors.get(i);
                    currentCell.setColor(grey);
                    enqueue(currentCell);
                    currentCell.parent = cell;
                }
            }
            // Cell has been visited and is set to black
            cell.setColor(black);
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
    private ArrayList<Cells> validNeighbors(Cells c) {
        ArrayList<Cells> neighbors = new ArrayList<Cells>();
        if (!c.getEast()) {
            if (mazeGrid[c.getX()][c.getY() + 1].getColor() == white) {
                neighbors.add(mazeGrid[c.getX()][c.getY() + 1]);
            }
        }

        // Finds base case for program when the current cell is at the last row and
        // there's an opening in the south
        if (!c.getSouth()) {
            if (c.getX() == (DIM - 1)) {
                return null;
            }
            if (mazeGrid[c.getX() + 1][c.getY()].getColor() == white) {
                neighbors.add(mazeGrid[c.getX() + 1][c.getY()]);
            }
        }
        if (!c.getWest()) {
            if (mazeGrid[c.getX()][c.getY() - 1].getColor() == white) {
                neighbors.add(mazeGrid[c.getX()][c.getY() - 1]);
            }
        }
        if (!c.getNorth() && c.getX() != 0) {
            if (mazeGrid[c.getX() - 1][c.getY()].getColor() == white) {
                neighbors.add(mazeGrid[c.getX() - 1][c.getY()]);
            }
        }
        return neighbors;
    }

    /**
     * Method that places a cell in the LinkedList queue and monitors the root
     *
     * @param c
     *            - Cell to be queued
     */
    private void enqueue(Cells c) {
        Node node = new Node(c);
        if (root == null) {
            root = node;
        } else {
            Node current = root;
            while (current.hasNext()) {
                current = current.next;
            }
            current.next = node;
        }
    }

    /**
     * Method that removes the root in the LinkedList because the queue is FIFO
     *
     * @return - cell dequeued
     */
    private Cells dequeue() {
        Node dequeue = root;
        root = root.next;
        return dequeue.data;
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
     * Method that keeps track of the BFS counter for each cell
     *
     * @param c-
     *            cell to be assigned a BFS value
     */
    private void counter(Cells c) {

        if (bfsCounter == 9) {
            bfsCounter = 0;
            c.setBFS(0);
        } else {
            bfsCounter++;
            c.setBFS(bfsCounter);
        }
    }

    /**
     * Getter method for the maze solved with BFS
     * @return solved maze
     */
    public Cells[][] getSolvedBFS(){
        return mazeGrid;
    }

    /**
     * Method that prints the maze solved through DFS
     */
    public void printSolvedMaze() {
        System.out.println();
        System.out.println();
        System.out.println("BFS:");
        mazeGrid[0][0].setNorth(false);
        mazeGrid[DIM - 1][DIM - 1].setSouth(false);

        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (mazeGrid[i][j].getNorth() == true) {
                    System.out.print("+ - ");
                } else
                    System.out.print("+   ");
            }
            System.out.println("+");
            for (int j = 0; j < DIM; j++) {
                if (mazeGrid[i][j].getWest() == true) {
                    if (mazeGrid[i][j].bfs() > -1) {
                        System.out.print("| " + mazeGrid[i][j].bfs() + " ");
                    } else {
                        System.out.print("|   ");
                    }
                } else if (mazeGrid[i][j].getWest() == false) {
                    if (mazeGrid[i][j].bfs() > -1) {
                        System.out.print("  " + mazeGrid[i][j].bfs() + " ");
                    } else {
                        System.out.print("    ");
                    }
                }
            }
            System.out.println("|");
        }
        for (int j = 0; j < DIM - 1; j++) {
            if (mazeGrid[DIM - 1][j].getSouth() == true) {
                System.out.print("+ - ");
            } else
                System.out.print("+   ");
        }
        System.out.print("+   +");
    }

    /**
     * Method that finds the shortest path for BFS
     */
    public void solveTheBSFPath() {
        mazeGrid[0][0].setHasPathChild(true);
        Cells end = mazeGrid[DIM - 1][DIM - 1];
        Cells current = end;

        while (current.getParent() != null) {
            current.setHasPathChild(true);
            current = current.getParent();
        }

    }

    /**
     * Method that prints out the shortest BFS path
     */
    public void printBFSPath() {
        System.out.println();
        System.out.println();
        System.out.println("BFS Path:");
        for (int i = 0; i < DIM; i++) {
            for (int j = 0; j < DIM; j++) {
                if (mazeGrid[i][j].getNorth() == true) {
                    System.out.print("+ - ");
                } else
                    System.out.print("+   ");
            }
            System.out.println("+");
            for (int j = 0; j < DIM; j++) {
                if (mazeGrid[i][j].getWest() == true) {
                    if (mazeGrid[i][j].getHasPathChild() != null && mazeGrid[i][j].getHasPathChild() != false) {
                        System.out.print("| " + "#" + " ");
                    } else {
                        System.out.print("|   ");
                    }
                } else if (mazeGrid[i][j].getWest() == false) {
                    if (mazeGrid[i][j].getHasPathChild() != null && mazeGrid[i][j].getHasPathChild() != false) {
                        System.out.print("  " + "#" + " ");
                    } else {
                        System.out.print("    ");
                    }
                }
            }
            System.out.println("|");
        }
        for (int j = 0; j < DIM - 1; j++) {
            if (mazeGrid[DIM - 1][j].getSouth() == true) {
                System.out.print("+ - ");
            } else
                System.out.print("+   ");
        }
        System.out.print("+   +");
    }

    /**
     * Node class that keeps track of the cells in the maze
     *
     * @author Rui Yang, Gurdev Sihra
     */
    class Node {
        private Node next = null; // Next node in linked list
        private Cells data; // Data contained in node

        /**
         * Node constructor that takes in a Cell object
         *
         * @param data
         *            - Cell object
         */
        public Node(Cells data) {
            this.data = data;
        }

        /**
         * Method that returns this node's next node in the linked list
         *
         * @param n
         */
        public void next(Node n) {
            this.next = n;
        }

        /**
         * Method that return's the node's data
         *
         * @return a Cell object
         */
        public Cells data() {
            return data;
        }

        /**
         * Method that sets the root for the Node
         *
         * @param n
         *            - node to be set to root
         */
        public void root(Node n) {
            root = n;
        }

        /**
         * Method that ensures there's a next node after the node
         *
         * @return - boolean on whether there's a next node or not
         */
        public boolean hasNext() {
            if (this.next != null) {
                return true;
            }
            return false;
        }

    }

}

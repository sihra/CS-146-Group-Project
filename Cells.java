package sjsu.yang.cs146.project2;

import javafx.scene.control.Cell;

import java.util.ArrayList;

/**
 * The Cells class describes how each cell looks in data
 *  --
 * |  |    each cell has north, south, west, east walls
 *  --
 *
 * @author Rui Yang, Gurdev Sihra
 * @version sjsu-cs-146-project2
 * @since 04/02/2018
 */
public class Cells {
    private Boolean north, south, west, east;// walls
    private int x; // (rows) a variable to track which position is currently at in the grid
    private int y; // (columns)a variable to track which position is currently at in the grid
    private CellColor color; // enum variable holding the color/visibility of each cell
    private int bfsValue = -1; // The counter for Breadth First Search
    private int dfsValue = -1; // the counter for Depth First Search
    Cells parent; // Parent of the cell
    Boolean hasPathChild; // Checks if there is a child
    /**
     * Constructor for Cells declaring "walls" on the north, south, west, and east sides
     */
    public Cells() {
        north = true;
        south = true;
        west = true;
        east = true;
    }

    /**
     * Method that returns the Depth First Search count number
     * @return DFS count value
     */
    public int dfs() {
        return this.dfsValue;
    }


    /**
     * Getter method for the cell's parent
     * @return parent of the cell
     */
    public Cells getParent() {
        return parent;
    }

    /**
     * Setter method for the cell's parent
     * @param parent - parent of the cell
     */
    public void setParent(Cells parent) {
        this.parent = parent;
    }

    /**
     * Checks to see if the path has a child
     * @return true if there is a child, false if not
     */
    public Boolean getHasPathChild() {
        return hasPathChild;
    }

    /**
     * Setter method for the child of the path
     * @param hasPathChild - child to be set
     */
    public void setHasPathChild(Boolean hasPathChild) {
        this.hasPathChild = hasPathChild;
    }

    /**
     * Setter method that sets the cell's DFS count number
     * @param count - DFS count value
     */
    public void setDFS(int count) {
        dfsValue = count;
    }

    /**
     * Method that returns the Breadth First Search count number
     * @return BFS count value
     */
    public int bfs() {
        return this.bfsValue;
    }

    /**
     * Setter method that sets the cell's BFS count number
     * @param count - BFS count value
     */
    public void setBFS(int count) {
        bfsValue = count;
    }

    /**
     * Setter method that set's the cell's visibility/color
     * @param enumColor - color to be set to
     */
    public void setColor(CellColor enumColor) {
        color = enumColor;
    }

    /**
     * Getter method that return's the cell's color/visibility
     * @return CellColor enum
     */
    public CellColor getColor() {
        return color;
    }

    /**
     * Getter method for the cell's position in the maze
     * @return - row in the maze
     */
    public int getX() {
        return x;
    }

    /**
     * Setter method for the cell's position in the maze
     * @param x - index of row
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Getter method for the cell's position in the maze
     * @return - column in the maze
     */
    public int getY() {
        return y;
    }

    /**
     * Setter method for the cell's position in the maze
     * @param x - index of column
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Method that gives or takes away a west wall to the cell
     * @param west- if true, then gives a wall, if false, then takes away a wall
     */
    public void setWest(Boolean west) {
        this.west = west;
    }

    /**
     * Method that gives or takes away a east wall to the cell
     * @param west- if true, then gives a wall, if false, then takes away a wall
     */
    public void setEast(Boolean east) {
        this.east = east;
    }

    /**
     * Method that gives or takes away a north wall to the cell
     * @param west- if true, then gives a wall, if false, then takes away a wall
     */
    public void setNorth(Boolean north) {
        this.north = north;
    }

    /**
     * Method that gives or takes away a south wall to the cell
     * @param west- if true, then gives a wall, if false, then takes away a wall
     */
    public void setSouth(Boolean south) {
        this.south = south;
    }

    /**
     * Method that tells users if there is a north wall
     * @return - true if there is a wall, false if there is not a wall
     */
    public Boolean getNorth() {
        return north;
    }

    /**
     * Method that tells users if there is a south wall
     * @return - true if there is a wall, false if there is not a wall
     */
    public Boolean getSouth() {
        return south;
    }

    /**
     * Method that tells users if there is a west wall
     * @return - true if there is a wall, false if there is not a wall
     */
    public Boolean getWest() {
        return west;
    }

    /**
     * Method that tells users if there is a east wall
     * @return - true if there is a wall, false if there is not a wall
     */
    public Boolean getEast() {
        return east;
    }

    /**
     * Method that checks if a cell has all walls intact
     * @return true if a cell has all walls, false if the cell does not
     */
    public boolean checkWalls() {
        if (north == true && west == true && east == true && south == true) {
            return true;
        } else {
            return false;
        }
    }
}

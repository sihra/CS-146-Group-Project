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
    Cells parent;
    Boolean hasPathChild;
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


    public Cells getParent() {
        return parent;
    }

    public void setParent(Cells parent) {
        this.parent = parent;
    }

    public Boolean getHasPathChild() {
        return hasPathChild;
    }

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

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWest(Boolean west) {
        this.west = west;
    }

    public void setEast(Boolean east) {
        this.east = east;
    }

    public void setNorth(Boolean north) {
        this.north = north;
    }

    public void setSouth(Boolean south) {
        this.south = south;
    }

    public Boolean getNorth() {
        return north;
    }

    public Boolean getSouth() {
        return south;
    }

    public Boolean getWest() {
        return west;
    }

    public Boolean getEast() {
        return east;
    }
    // a method to check if each cell has all walls intact
    public boolean checkWalls() {
        if (north == true && west == true && east == true && south == true) {
            return true;
        } else {
            return false;
        }
    }
}

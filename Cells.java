package sjsu.yang.cs146.project2;

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
    private Boolean north, south, west, east;
    private int x;
    private int y;

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

    public Cells() {
        north = true;
        south = true;
        west = true;
        east = true;
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

    public boolean checkWalls() {
        if (north == true && west == true && east == true && south == true) {
            return true;
        } else {
            return false;
        }
    }
}

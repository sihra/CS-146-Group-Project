package sjsu.yang.cs146.project2;


import org.junit.Test;
import static org.junit.Assert.*;

/**
 * JUnit test methods for the Cell Maze, BreathFirstSearch, and DepthFirstSearch classes
 * @author Rui Yang, Gurdev Sihra
 *
 */
public class MazeTest {

    /**
     * JUnit tester method for a maze with a dimension of 4, the correct values of BFS and DFS counters,
     *  and the color of a cell
     */
    @Test
    public void testMaze(){
        System.out.println();
        System.out.println("Testing Maze with Dimension 4");
        System.out.println();
        Maze maze = new Maze(4, 0);
        org.junit.Assert.assertTrue( maze.getDIM() ==  4);
        Cells[][] testWhite = maze.getGrid();
        for(int row = 0; row < testWhite.length; row++) {
            for(int col = 0; col < testWhite[row].length; col++) {
                assertTrue( testWhite[row][col].getX() ==  row);
                assertEquals(testWhite[row][col].getY(), col);
            }
        }
        testWhite[0][0].setBFS(-8);
        assertTrue( testWhite[0][0].bfs() ==  -8);
        testWhite[0][0].setBFS(-1);
        assertTrue( testWhite[0][0].bfs() ==  -1);
        assertTrue( testWhite[0][0].dfs() ==  -1);
        CellColor white = CellColor.WHITE;
        testWhite[0][0].setColor(white);
        assertEquals(testWhite[0][0].getColor(), white);
        System.out.println();
    }

    /**
     * JUnit tester method for a BFS solution on a maze of dimension 4
     */
    @Test
    public void testBFS() {
        System.out.println();
        System.out.println("Testing BFS with Dimension 4");
        System.out.println();
        Maze maze = new Maze(4, 6);
        int[][] answer ={
                {0,1,2,3},
                {-1,-1,4,5},
                {-1,-1,6,7},
                {-1,-1,-1,8}  };
        BreadthFirstSearch bfs = new BreadthFirstSearch(maze);
        bfs.bfs();
        Cells[][] output = bfs.getSolvedBFS();
        for(int row = 0; row < output.length; row++) {
            for(int col = 0; col < output[row].length; col++) {
                assertTrue( output[row][col].bfs() ==  answer[row][col]);
            }
        }
        System.out.println();

    }

    /**
     * JUnit tester method for a BFS solution on a maze of dimension 6
     */
    @Test
    public void testBFS1(){
        System.out.println();
        System.out.println("Testing BFS with Dimension 6");
        System.out.println();
        Maze maze1 = new Maze(6, 6);
        Boolean[][] answer1 ={
                {true,true,true,true,null,null},
                {null,null,null,true,true,null},
                {null,null,null,true,true,null},
                {null,null,null,true,true,true},
                {null,null,null,null,null,true},
                {null,null,null,null,null,true}};
        BreadthFirstSearch bfs1 = new BreadthFirstSearch(maze1);
        bfs1.bfs();
        Cells[][] output1 = bfs1.getSolvedBFS();
        for(int row = 0; row < output1.length; row++) {
            for(int col = 0; col < output1[row].length; col++) {
                assertTrue( output1[row][col].getHasPathChild() ==  answer1[row][col]);
            }
        }
        System.out.println();
    }

    /**
     * JUnit tester method for a BFS solution on a maze of dimension 8
     */
    @Test
    public void testBFS2(){
        System.out.println();
        System.out.println("Testing BFS with Dimension 8");
        System.out.println();
        Maze maze1 = new Maze(8, 6);
        Boolean[][] answer1 ={
                {true,null,null,null,null,null,null,null},
                {true,null,null,null,null,null,null,null},
                {true,null,null,null,null,null,null,null},
                {true,null,null,null,null,null,null,null},
                {true,null,null,null,null,null,null,null},
                {true,null,null,null,null,null,null,null},
                {true,true,true,true,null,null,null,null},
                {true,true,null,true,true,true,true,true}};
        BreadthFirstSearch bfs1 = new BreadthFirstSearch(maze1);
        bfs1.bfs();
        Cells[][] output1 = bfs1.getSolvedBFS();
        for(int row = 0; row < output1.length; row++) {
            for(int col = 0; col < output1[row].length; col++) {
                assertTrue( output1[row][col].getHasPathChild() ==  answer1[row][col]);
            }
        }
        System.out.println();
    }

    /**
     * JUnit tester method for a DFS solution on a maze of dimension 4
     */
    @Test
    public void testDFS() {
        System.out.println();
        System.out.println("Testing DFS with Dimension 4");
        System.out.println();
        Maze maze = new Maze(4, 6);
        Boolean[][] answer ={
                {true,true,true,null},
                {null,null,true,true},
                {null,null,null,true},
                {null,null,null,true}  };
        DepthFirstSearch dfs = new DepthFirstSearch(maze);
        dfs.dfs();
        Cells[][] output = dfs.getSolvedDFS();
        for(int row = 0; row < output.length; row++) {
            for(int col = 0; col < output[row].length; col++) {
                assertTrue( output[row][col].getHasPathChild() ==  answer[row][col]);
            }
        }
        System.out.println();
    }

    /**
     * JUnit tester method for a DFS solution on a maze of dimension 6
     */
    @Test
    public void testDFS1() {
        System.out.println();
        System.out.println("Testing DFS with Dimension 6");
        System.out.println();
        Maze maze1 = new Maze(6, 6);
        Boolean[][] answer1 ={
                {true,true,true,true,null,null},
                {null,null,null,true,true,null},
                {null,null,null,true,true,null},
                {null,null,null,true,true,true},
                {null,null,null,null,null,true},
                {null,null,null,null,null,true}};
        DepthFirstSearch dfs1 = new DepthFirstSearch(maze1);
        dfs1.dfs();
        Cells[][] output1 = dfs1.getSolvedDFS();
        for(int row = 0; row < output1.length; row++) {
            for(int col = 0; col < output1[row].length; col++) {
                assertTrue( output1[row][col].getHasPathChild() ==  answer1[row][col]);
            }
        }
        System.out.println();
    }

    /**
     * JUnit tester method for a DFS solution on a maze of dimension 8
     */
    @Test
    public void testDFS2() {
        System.out.println();
        System.out.println("Testing DFS with Dimension 8");
        System.out.println();
        Maze maze1 = new Maze(8, 6);
        Boolean[][] answer1 ={
                {true,null,null,null,null,null,null,null},
                {true,null,null,null,null,null,null,null},
                {true,null,null,null,null,null,null,null},
                {true,null,null,null,null,null,null,null},
                {true,null,null,null,null,null,null,null},
                {true,null,null,null,null,null,null,null},
                {true,true,true,true,null,null,null,null},
                {true,true,null,true,true,true,true,true}};
        DepthFirstSearch dfs1 = new DepthFirstSearch(maze1);
        dfs1.dfs();
        Cells[][] output1 = dfs1.getSolvedDFS();
        for(int row = 0; row < output1.length; row++) {
            for(int col = 0; col < output1[row].length; col++) {
                assertTrue( output1[row][col].getHasPathChild() ==  answer1[row][col]);
            }
        }
        System.out.println();
    }
}
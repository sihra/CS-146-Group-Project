package sjsu.yang.cs146.project2;


public class Tester {
    public static void main(String[] args){
        Maze reyMaze = new Maze(4);
        BreadthFirstSearch bfs = new BreadthFirstSearch(reyMaze);
        DepthFirstSearch dfs = new DepthFirstSearch(reyMaze);
        bfs.bfs();
        dfs.dfs();


    }
}

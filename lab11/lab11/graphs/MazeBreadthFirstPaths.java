package lab11.graphs;
import edu.princeton.cs.algs4.Queue;


/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int s;
    private int t;
    private boolean targetFound = false;


    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        maze =m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // TODO: Your code here. Don't forget to update distTo, edgeTo, and marked, as well as call announce()
        marked[s]=true;

        Queue<Integer> q = new Queue<>();
        q.enqueue(s);
        while(!q.isEmpty()){
            int source = q.dequeue();
            for(int neighbour: maze.adj(source)){
                if(!marked[neighbour]){
                    marked[neighbour]=true;
                    q.enqueue(neighbour);
                    edgeTo[neighbour]=source;
                    distTo[neighbour]=distTo[source]+1;
                    announce();

                    if(neighbour==t){
                        targetFound=true;
                    }

                    if(targetFound) return;
                }
            }
        }
    }


    @Override
    public void solve() {
         bfs();
    }
}


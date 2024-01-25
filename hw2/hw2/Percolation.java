package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;

import static org.junit.Assert.*;

public class Percolation {
    private boolean[] site; //default false, all not open
    private int size;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF backwash;
    private int virtualTop;
    private int virtualBottom;
    private int openSites;

    public Percolation(int N) {  // create N-by-N grid, with all sites initially blocked
        if(N<=0){
            throw new java.lang.IllegalArgumentException();
        }
        site = new boolean[N*N+1];
        size = N;
        virtualBottom = N*N+1;
        virtualTop=N*N;
        uf = new WeightedQuickUnionUF(N*N+2);
        backwash = new WeightedQuickUnionUF(N*N+1);
        openSites=0;

        //Connect virtual top
        for(int i =0;i<N;i++){
            backwash.union(i,virtualTop);
        }

        //Connect virtual bottom
        for(int j = N*(N-1);j<N*N;j++){
            uf.union(virtualBottom,j);
        }
    }

    public void open(int row, int col){
        validateIndex(row,col);
        int currentSite = xyTo1D(row,col);
        if(!site[currentSite]){
            site[currentSite]=true;
            connectToOpenNeighbors(row,col);
            openSites++;
        }
    }// open the site (row, col) if it is not open already

    public boolean isOpen(int row, int col){ // is the site (row, col) open?
        validateIndex(row,col);
        int currentSite = xyTo1D(row,col);
        return site[currentSite];
    }
    public boolean isFull(int row, int col){
        validateIndex(row,col);
        int currentState = xyTo1D(row,col);
        return isOpen(row,col) && backwash.connected(virtualTop,currentState);
    }// is the site (row, col) full?

    public int numberOfOpenSites(){
        return openSites;
    }           // number of open sites
    public boolean percolates(){
        return uf.connected(virtualTop,virtualBottom);
    }              // does the system percolate?
//    public static void main(String[] args)  // use for unit testing (not required)

    /* Helper Functions */


    private int xyTo1D(int r, int c){ //translate 2-d array to integer
        return r*size+c;
    }
    private void validateIndex(int i, int j){ //check if given indices are within bound
        if(i<0||j<0||i>size-1||j>size-1){
            throw new java.lang.IndexOutOfBoundsException();
        }
    }
    private void connectToOpenNeighbors(int r, int c){
        validateIndex(r,c);
        int currentSite = xyTo1D(r,c);
        int[] neighbors = getNeighbors(r,c);
        for(int neighbor: neighbors){
            if(neighbor!=-1 && site[neighbor]){
                uf.union(currentSite,neighbor);
                backwash.union(currentSite,neighbor);
                if(backwash.connected(virtualTop,neighbor)){
                    uf.union(virtualTop,currentSite);
                    backwash.union(virtualTop,currentSite);
                }
            }
        }

    }
    private int[] getNeighbors(int i, int j){
        int[] neighbors = {-1,-1,-1,-1};
        validateIndex(i,j);

        if(i==0){
            neighbors[0]=xyTo1D(i,j);
        }
        if(i>0){
            neighbors[0]=xyTo1D(i-1,j);
        }
        if(i!=this.size-1){
            neighbors[1]=xyTo1D(i+1,j);
        }
        if(j>0){
            neighbors[2]=xyTo1D(i,j-1);
        }
        if(j!=this.size-1){
            neighbors[3]=xyTo1D(i,j+1);
        }
        return neighbors;
    }
    @Test
    public static void test(){
        Percolation p = new Percolation(3);
        assertFalse(p.isOpen(0,0));
        p.open(0,0);
        assertTrue(p.isOpen(0,0));
        assertTrue(p.isFull(0,0));
        assertEquals(1,p.numberOfOpenSites());
        assertFalse(p.isOpen(1,0));
        p.open(1,0);
        assertTrue(p.isOpen(1,0));
        assertTrue(p.isFull(1,0));
        p.open(2,0);
        assertTrue(p.percolates());
    }


    public static void main(String[] args) {
        test();
    }

}

/*
Percolation class.

Created by: Aashir Gajjar
Created on: 6/28/2014
*/
public class Percolation {
   
   private boolean[][] grid;
   private final int max_size;
   private WeightedQuickUnionUF wquf;
   private WeightedQuickUnionUF wquf_fill;
   private int openFlag=0;
   /*class methods:
   public Percolation(int N)// create N-by-N grid, with all sites blocked and initailize the weighted quick UF array   
   public void open(int i , int j) //open site (row i, column j) if it is not already
   public boolean isOpen(int i, int j)    // is site (row i, column j) open?
   public boolean isFull(int i, int j)   // is site (row i, column j) full?
   public boolean percolates()            // does the system percolate?
   */
   
   
   public Percolation(int N) {
       if(N <= 0 )throw new IllegalArgumentException("N out of bounds");
     //make a matrix grid with all sites as closed.
     //represent closed sites as 0 and open as 1
     max_size=N;
     grid=new boolean[max_size+1][max_size+1];
     for(int i=1;i<=max_size;i++){
         for(int j=1;j<=max_size;j++){
             grid[i][j]=false;
         } 
     }  
     //make a weighted quick union find obj and connect the virtual sites with
     //and bottom rows
     wquf=new WeightedQuickUnionUF(max_size*max_size+2);
     wquf_fill=new WeightedQuickUnionUF(max_size*max_size+2);
     for(int i=1;i<max_size+1;i++){
         wquf.union(0,i);
         wquf_fill.union(0,i);
         wquf.union((max_size*max_size)+1,(max_size*max_size)+1-i);
     }
   }
    
   
   public boolean isOpen(int i, int j){
       if (i <= 0 || i > max_size) throw new IndexOutOfBoundsException("row index i out of bounds");
       if (j <= 0 || j > max_size) throw new IndexOutOfBoundsException("row index j out of bounds");
       //change the indexing from 1:N to 0:N-1.
       //System.out.println("Checking to open: "+ i+","+j);
       return grid[i][j];      
   }
   
   public boolean isFull(int i, int j){
       //change the indexing from 1:N to 0:N-1.
      
      if (i <= 0 || i > max_size) throw new IndexOutOfBoundsException("row index i out of bounds");
      if (j <= 0 || j > max_size) throw new IndexOutOfBoundsException("row index j out of bounds");
      i--;
      j--;
      if(isOpen(i+1,j+1)){ 
          return(wquf_fill.connected(0,(i*max_size)+j+1 ));
      }
      else{
          return false;
      }
   }

    
    public void open(int i, int j){
        //System.out.println("Opening:" + i +","+j);
        openFlag = 1;
        if (i <= 0 || i > max_size) throw new IndexOutOfBoundsException("row index i out of bounds");
        if (j <= 0 || j > max_size) throw new IndexOutOfBoundsException("row index j out of bounds");
        //change the indexing from 1:N to 0:N-1.
        i--;
        j--;
       
        if(!isOpen(i+1,j+1)){
            //System.out.print("\nOpening " + i + j);
            grid[i+1][j+1]=true; //i,j is the index of the grid
            
            //connect the site that is just opened
            int idx=i*max_size+j+1; //getting the index of wquf array
            if(j-1>=0&&grid[i+1][j]==true){
                wquf.union(idx,idx-1);
                wquf_fill.union(idx,idx-1);
            }
            if(j+1<max_size && grid[i+1][j+2]==true){
                wquf.union(idx,idx+1);
                wquf_fill.union(idx,idx+1);
            }
            if(i-1>=0 && grid[i][j+1]==true){
                wquf.union(idx,idx-max_size);
                wquf_fill.union(idx,idx-max_size);
            }
            if(i+1<max_size && grid[i+2][j+1]==true){
                wquf.union(idx,idx+max_size);
                wquf_fill.union(idx,idx+max_size);
            }
        }
        else{
            //print that the grid is not open at (i,j)
            //System.out.println("The site is already open");
        }   
    }

    public boolean percolates() {
        if(openFlag == 0){return false;}
        else{
            return wquf.connected(0,max_size*max_size+1);
        }
    }
     
     
   


}
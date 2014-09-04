// Writing the java percolation function
//
// Throw a java.lang.IndexOutOfBoundsException if either i or j is outside this range. The constructor should take time proportional to N^2; all methods should take constant time plus a constant number of calls to the union-find methods union(), find(), connected(), and count().
public class Percolation {
    Boolean [][] a;
     int N_size;
    WeightedQuickUnionUF w; 
    WeightedQuickUnionUF w2;
  /* public static void main(String[] args)
   {
    Percolation P= new Percolation(5);
    
    System.out.println("DONE RUNNING");
   }
   */
   
   public Percolation(int N)              // create N-by-N grid, with all sites blocked
   {
       N_size = N;
      w = new WeightedQuickUnionUF(N_size*N_size+2);
      w2 = new WeightedQuickUnionUF(N_size*N_size+2);
    a = new Boolean[N+1][N+1];
    for(int i =1;i<=N;i++)
        for(int j=1; j<=N;j++)
        a[i][j] = false;
    
    for(int i=1;i<=N;i++)
    {
     w.union(0,i);
      w2.union(0,i);
     w.union(N_size*N_size+1, N_size*N_size+1-i);
    }
    // connecting the first N elements to the virtual top and last N elements to the virtual bottom
    // virtual top is the 0th elements and virtual bottom is the N^2+1th element
   }
  public void open(int i, int j) // open site (row i, column j) if it is not already
  {
   if (i <= 1 || i > N_size+1) throw new java.lang.IndexOutOfBoundsException("row index i out of bounds");
   if (j <= 1 || j > N_size+1) throw new java.lang.IndexOutOfBoundsException("row index j out of bounds");
      //if((i<=0 || i>N_size) || (j<=0 || j>N_size));
      
     a[i][j] = true;
      
      // top
      if(i-1>0 && j>0 && isOpen(i-1,j))
      { w.union(xy21d(i,j),xy21d(i-1,j));
        w2.union(xy21d(i,j),xy21d(i-1,j));   
      }
//left
      if(i>0 && j-1>0 && isOpen(i,j-1))
      { w.union(xy21d(i,j),xy21d(i,j-1));
        w2.union(xy21d(i,j),xy21d(i,j-1));   
      }
      //right
      if(i>0 && j+1<=N_size && isOpen(i,j+1))
      { w.union(xy21d(i,j),xy21d(i,j+1));
         w2.union(xy21d(i,j),xy21d(i,j+1));
      }
      //bottom
      if(i+1<=N_size && j>0 && isOpen(i+1,j))
      { w.union(xy21d(i,j),xy21d(i+1,j));
          w2.union(xy21d(i,j),xy21d(i+1,j));
      }
      
  } 

  public boolean isOpen(int i, int j)   // is site (row i, column j) open?
  {
      
   if (i <= 1 || i > N_size+1) throw new java.lang.IndexOutOfBoundsException("row index i out of bounds");
   if (j <= 1 || j > N_size+1) throw new java.lang.IndexOutOfBoundsException("row index j out of bounds");
   //   System.out.println("ISopen"+i+" "+j);
      return a[i][j];
  }
  
   public boolean isFull(int i, int j)   // is site (row i, column j) full?
   {
      
   if (i <= 1 || i > N_size+1) throw new java.lang.IndexOutOfBoundsException("row index i out of bounds");
   if (j <= 1 || j > N_size+1) throw new java.lang.IndexOutOfBoundsException("row index j out of bounds");
       
       return (w.connected(xy21d(i,j),0) && isOpen(i,j) && w2.connected(xy21d(i,j),0));
       
   }
   public boolean percolates()            // does the system percolate?
   {
       return w.connected(0,N_size*N_size+1);
   }
  private int xy21d(int i, int j)
  {
      
   if (i <= 1 || i > N_size+1) throw new java.lang.IndexOutOfBoundsException("row index i out of bounds");
   if (j <= 1 || j > N_size+1) throw new java.lang.IndexOutOfBoundsException("row index j out of bounds");
      return ((i-1)*N_size+j);   
  }
   
}




//The constructor should throw a java.lang.IllegalArgumentException if either N ² 0 or T ² 0.


public class PercolationStats {
    private double[] per_threshold;
    private int count= 0;
    private int my_T = 0;;
    private int my_N = 0;
   public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
   {
    if (N <= 0) throw new java.lang.IllegalArgumentException("N out of bounds");
       if (T <= 0) throw new java.lang.IllegalArgumentException("T out of bounds");

       my_T = T;
       my_N = N;
       per_threshold = new double[T];
       for(int i=0;i<T;i++)
       {
           int tem_i, temp_j;
           Percolation per = new Percolation(N);
           while(!(per.percolates()))
           {
            
               temp_i = (StdRandom.uniform(1,N+1));
               temp_j = (StdRandom.uniform(1,N+1));
               
               if(!per.isOpen(temp_i,temp_j))
               {
                  // System.out.println(temp_i+"   "+ temp_j);
                   count = count+1;
                   per.open(temp_i,temp_j);
               }
          //     System.out.println(count);
           }
          // system has percolated!
        //   System.out.println("DONE WITH  "+i);
       //    System.out.println(count);
           per_threshold[i] = count;
           count = 0;
           
       }
       
      
   }
   
   public double mean()                     // sample mean of percolation threshold
   {
       double mean_value = 0;
       int sum = 0;
       for(int i=0;i<my_T;i++)
           mean_value = mean_value + per_threshold[i];
       return mean_value/(my_T*my_N*my_N);
       
   }
   public double stddev()                   // sample standard deviation of percolation threshold
   {
       double mean_value = mean();
       double stddev = 0;
       
       for(int i=0;i<my_T;i++)
       {
           stddev = stddev + (per_threshold[i]/(my_N*my_N) - mean_value)*(per_threshold[i]/(my_N*my_N) - mean_value);
       }
       return Math.sqrt(((stddev)/(my_T-1)));
   }
   
   public double confidenceLo()             // returns lower bound of the 95% confidence interval
   {
       
       double mean_value = mean();
       double stddev = stddev();
       
       return (mean_value - (1.96*stddev()/(Math.sqrt(my_T))));
   }
   public double confidenceHi()             // returns upper bound of the 95% confidence interval
   {
       double mean_value = mean();
       double stddev = stddev();
       
       return (mean_value - (1.96*stddev()/(Math.sqrt(my_T))));
   }
  
   
   public static void main(String[] args)
   { 
       int N = StdIn.readInt();
       int T = StdIn.readInt();
       PercolationStats p = new PercolationStats(N,T);
       System.out.println(" MEAN  = "+p.mean());
       System.out.println(" STDDEV  = "+p.stddev());
 System.out.println(" 95 % confidence  = "+p.confidenceLo()+ "  ,  "+ p.confidenceHi());
   }
 
}
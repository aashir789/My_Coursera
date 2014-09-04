public class PercolationStats{
    /*
    public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
    public double mean()                     // sample mean of percolation threshold
    public double stddev()                   // sample standard deviation of percolation threshold
    public double confidenceLo()             // returns lower bound of the 95% confidence interval
    public double confidenceHi()             // returns upper bound of the 95% confidence interval
    public static void main(String[] args)   // test client, described below
    */
    private double[] percolationThreshold;
    private int openSites;
    private int max_size;
    private int noOfSimulations;
    
    public PercolationStats(int N, int T){
        if(N <= 0 )throw new IllegalArgumentException("N out of bounds");
        if (T <= 0 ) throw new IllegalArgumentException("T out of bounds");
        max_size=N;
        noOfSimulations=T;
        
        
        //initailize the no of fractions as the no of simulations.
        percolationThreshold=new double[noOfSimulations];
        
        //keep opening sites till the grid percolates
        for(int k=0;k<T;k++){
            Percolation perc=new Percolation(max_size);
            openSites=0;
            while(!perc.percolates()){
                int i=StdRandom.uniform(1,max_size+1);
                int j=StdRandom.uniform(1,max_size+1);
                
                if(!perc.isOpen(i,j)){
                    perc.open(i,j);
                    openSites++;
                }
            }
            
            percolationThreshold[k]=(double)openSites /(max_size*max_size);
        }
    }
    
    
    public double mean(){
        double sum=0;
        for(int k = 0; k<noOfSimulations ; k++){
            sum=percolationThreshold[k]+sum;
        }
        return (double)sum/noOfSimulations;
    }
    
    public double stddev(){
        double sum=0;
        for(int k=0;k<noOfSimulations;k++){
            sum = sum +((percolationThreshold[k]-mean())*(percolationThreshold[k]-mean()));
                     }
        return (double)sum/(noOfSimulations-1);
        }
    
    public double confidenceLo(){
        return (mean()-((1.96*stddev())/(double)Math.sqrt(noOfSimulations)));
    }
    
    
    public double confidenceHi(){
        return (mean()+((1.96*stddev())/(double)Math.sqrt(noOfSimulations)));
    }

    public static void main(String[] args){
        int N=StdIn.readInt();
        int T=StdIn.readInt();
        PercolationStats perc=new PercolationStats(N,T);
        System.out.println("mean:"+perc.mean());
        System.out.println("stddev"+perc.stddev());
        System.out.println("Hi:"+perc.confidenceHi());
        System.out.println("Lo:"+perc.confidenceLo());
        
        
        
    
    }
    
}


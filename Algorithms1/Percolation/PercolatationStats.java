public class PercolationStats {
    /*
    public PercolationStats(int N, int T)    // perform T independent computational experiments on an N-by-N grid
    public double mean()                     // sample mean of percolation threshold
    public double stddev()                   // sample standard deviation of percolation threshold
    public double confidenceLo()             // returns lower bound of the 95% confidence interval
    public double confidenceHi()             // returns upper bound of the 95% confidence interval
    public static void main(String[] args)   // test client, described below
    */
    private int[] percolationThreshold;
    private int openSites;
    private int max_size;
    private int noOfSimulations;
    
    public PercolationStats(int N, int T){
        max_size=N;
        noOfSimulations=T;
        Percolation perc=new Percolation(max_size);
        
        //initailize the no of fractions as the no of simulations.
        percolationThreshold=new int[noOfSimulations];
        
        //keep opening sites till the grid percolates
        for(int k=0;k<T;k++){
            while(!perc.percolates()){
                int i=Std.Random.random(1,max_size);
                int j=Std.Random.random(1,max_size);
                perc.open(i,j);
            }
            openSites=0;
            for(int i=1;i<=max_size;i++){
                for(int j=1;j<=max_size;j++){
                    if(perc.isOpen(i,j)){openSites++;}
                }
            }
            percolationThreshold[k]=openSites/(max_size*max_size);
        }
    }
    
    
    public double mean(){
        double sum=0;
        for(int k=0;k<noOfSimulations;k++){
            sum=percolationThreshold[k]+sum;
        }
        return sum/noOfSimulations;
    }
    
    public double stddev(){
        double sum=0;
        for(int k=0;k<noOfSimulations;k++){
            sum = sum +((percolationThreshold[k]-mean())*(percolationThreshold[k]-mean()));
                     }
        return sum/(noOfSimulations-1);
        }
    
    public double confidenceLo(){
        return (mean()-(1.96*stddev()/math.sqrt(noOfSimulations)));
    }
    
    
    public double confidenceHi(){
        return (mean()+(1.96*stddev()/Math.sqrt(noOfSimulations)));
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


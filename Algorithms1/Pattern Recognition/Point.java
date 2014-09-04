import java.util.Comparator;


public class Point implements Comparable<Point> {
   /*
   public final Comparator<Point> SLOPE_ORDER;     // compare points by slope to this point
   public Point(int x, int y)                      // construct the point (x, y)
   public void draw()                              // draw this point
   public void drawTo(Point that)                  // draw the line segment from this point to that point
   public String toString()                        // string representation
   public int compareTo(Point that)                // is this point lexicographically smaller than that point?
   public double slopeTo(Point that)               // the slope between this point and that point
   */
    
    //Private properties
    
  private final int a;
  private final int b;
    

    //public methods

    public Point(int x, int y){
        this.a=x;
        this.b=y;
    }    
    
    public void draw(){

        StdDraw.point(this.a,this.b);
    }
    
    public void drawTo(Point that){
        StdDraw.line(this.a,this.b,that.a,that.b);
    }
    
    public double slopeTo(Point that){
        double slope;
        if (this.b==that.b && this.a==that.a){
            slope=Double.NEGATIVE_INFINITY;
        }
        else if(this.a==that.a){
            slope=Double.POSITIVE_INFINITY;
        }
        else{
            double numerator=this.b-that.b;
            double denominator=this.a-that.a;
            slope=numerator/denominator;
            if(slope==-0.0){slope=0.0;}  
        }
        return slope;
    }
    
    public int compareTo(Point that){
        int yDiff=this.b-that.b;
        int xDiff=this.a-that.a;
        
        if(yDiff==0){
            return xDiff;
        }
        else{
            return yDiff;
        }
    }
    
    public String toString(){
        return "("+a+","+b+")";
    }
    
    public final Comparator<Point> SLOPE_ORDER =new slopeOrder();

    // private methods

    private class slopeOrder implements Comparator<Point>{

      public int compare(Point Point1,Point Point2){
          //StdOut.println("The current point is: "+Point.this.toString());
          //StdOut.println("The comparing points are "+Point1.toString()+" and "+Point2.toString());
          //StdOut.println("Slopes are "+slopeTo(Point1)+" and "+slopeTo(Point2));

          double slopeDifference=slopeTo(Point1)-slopeTo(Point2);
          if (slopeDifference>0){slopeDifference=Math.ceil(slopeDifference);}
          else {slopeDifference=Math.floor(slopeDifference);}
          
          //StdOut.println("slopeDifference is "+slopeDifference);

          
          return (int)slopeDifference;
      }
    }    
}


     

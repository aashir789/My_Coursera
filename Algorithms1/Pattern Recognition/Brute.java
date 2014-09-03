import java.util.Comparator;
import java.util.Arrays;

public class Brute{

    private Point[] sortPoints(Point[] oldPoints){

        return oldPoints;

    }

    public static void main(String[] args){
        
        // rescale coordinates and turn on animation mode
        
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger
        
        // read in the input
        String filename = args[0];
        In in = new In(filename);
        
        int numOfPoints = in.readInt();   //first input is the number of points
        
        // initialize necessary variables after reading input

        Point[] points=new Point[numOfPoints];
        
        
        for (int i = 0; i < numOfPoints; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }


        // start comparing all the points with respect to the slope

        for (int i = 0; i < numOfPoints-3; i++) {
            //StdOut.println("i is "+i);
            for (int j = i+1; j < numOfPoints-2; j++) {
                //StdOut.println("j is "+j);
                for (int k = j+1; k<numOfPoints-1; k++) {
                    //StdOut.println("k is "+k);
                    if (points[i].SLOPE_ORDER.compare(points[j] , points[k]) != 0){
                        continue;
                    }
                    for (int l = k+1; l < numOfPoints; l++) {
                        //StdOut.println("l is "+l);
                        
                        //if(!(i==j || i==k || i==l || j==k || j==l || k==l)){

                           if (points[i].SLOPE_ORDER.compare(points[j] , points[k]) == 0 &&
                                points[i].SLOPE_ORDER.compare(points[k] , points[l]) == 0) {

                                // sort points here
                                Point[] newPoints = new Point[4];
                                newPoints[0]=points[i];
                                newPoints[1]=points[j];
                                newPoints[2]=points[k];
                                newPoints[3]=points[l];
                                
                                Arrays.sort(newPoints);
                                
                                
                                // print points on screens
                                StdOut.print(newPoints[0].toString()+" -> "+newPoints[1].toString()+" -> ");
                                StdOut.println(newPoints[2].toString()+" -> "+newPoints[3].toString());

                                // draw the line segment
                                newPoints[0].drawTo(newPoints[3]);
                            }
                        //}
                    }
                }     
            }    
        }

        //display to screen all at once
        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }
}
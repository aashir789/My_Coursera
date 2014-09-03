import java.util.Comparator;
import java.util.Arrays;

public class newFast{

	public static void main(String[] args) {

		//////// Intialize and read from file///////////////////////////////
		StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01);  // make the points a bit larger
        
        // read input from file
        String filename = args[0];
        In in = new In(filename);
        
        int numOfPoints = in.readInt();   //first input is the number of points
        
        // read the points in a loop
        Point[] points=new Point[numOfPoints];
        for (int i = 0; i < numOfPoints; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }

        Arrays.sort(points);

        for(Point p:points) {
        	StdOut.println(p.toString());
        }

        /////////////////////////////////////////////////////////////////////////



        /////////// Search for 4 or more collinear points and print them///////////
        for(int i = 0; i<numOfPoints-1;i++) {
        	Arrays.sort(points,points[i+1], points[numOfPoints-1],points[i].SLOPE_COMPARATOR);
        	
        	// initialize loop parameters
        	int j = i+1;
            double slope;
        	double prevSlope=Double.NEGATIVE_INFINITY;
        	int count =0;
        	

        	//  while looping till the end of the newpoints array
        	while (j<numOfPoints) {
        		slope = points[i].slopeTo(newPoints[j]);
        		j++;

        		
        		if(slope == prevSlope) {
        			//StdOut.println("Slope equal");
        			count++;
        		}
				
        		else{
        			count=0;
        		}

        		StdOut.println("count: " +count+" j :"+j+ " Slope is : "+slope);
        		StdOut.println("numOfPoints : "+numOfPoints+"limit: " +(numOfPoints-(i+1)));

				// when the slopes of adjacent points are unequal
        		
        		//if ((slope != prevSlope || j == subArraySize) ) {
        			//StdOut.println("Slope unequal or array ended");
        			
        			if (count == 2) {

        				// draw the line segment from i to j-1
        				StdOut.println("j-2 :"+(j-2)+"point is :" +newPoints[j-2]);
        				points[i].drawTo(newPoints[j-1]);

        				// print the last "count" no of points 
        				StdOut.print(points[i].toString());
        				while(count >=0) {
        					StdOut.print(" -> "+ newPoints[j-1-count].toString());
        					count--;
        				}

        				
        				StdOut.println();
        				
        			//assert count==0;
        			count = 0;
        			}
        		//}
        		// when the slopes of adjacent points are equal
        		
        		
        		prevSlope=slope;
        		

        	}
        }
        StdDraw.show(0);
	}
}
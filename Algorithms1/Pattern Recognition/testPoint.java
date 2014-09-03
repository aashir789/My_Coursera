public class testPoint{
	
	public static void main(String[] args) {

      // rescale coordinates and turn on animation mode
      /*
      StdDraw.setXscale(0, 32768);
      StdDraw.setYscale(0, 32768);
      */
      Point[] points=new Point[4];
      points[0]=new Point(340,150);
      points[1]=new Point(392,150);
      points[2]=new Point(204,150);
      points[3]=new Point(200,5000);
      
      /*
      points[4]=new Point(500,10000);
      points[5]=new Point(1000,10000);
      
      points[6]=new Point(10000,7500);
      points[7]=new Point(10000,6000);
      
      points[8]=new Point(5000,5000);
      */


      //print the points before sorting
      /*
      StdOut.println("Before Sorting:");
      for(Point p:points){
        StdOut.println(p.toString());
      }
      */

      //draw the points 
      /*
      StdDraw.setPenRadius(0.01); 
      for(Point p:points){
        p.draw();
      }
      */
      
      //check compare method
      int res=points[0].SLOPE_ORDER.compare(points[1],points[2]);
 	  StdOut.println(res);

 	  /*
      StdOut.println("After Sorting wrt "+points[3].toString()+":");  
      

      //print the points after sorting
      
      for(Point p:points){
        StdOut.println(p.toString());
      }
		*/

    
   
	}

}
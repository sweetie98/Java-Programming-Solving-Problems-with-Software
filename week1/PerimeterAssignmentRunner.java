
import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int count =0;
        for(Point p:s.getPoints())
        {
            count++;
        }
        return count;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double len=(double)getPerimeter(s)/(double)(getNumPoints(s)-1);
        return len;
    }

    public double getLargestSide(Shape s) {
        double x=0.0;
        Point prevPt = s.getLastPoint();
          for (Point currPt : s.getPoints()) {
            double currDist = prevPt.distance(currPt);
                if(currDist>x)
            {
                x=currDist;
            }
            prevPt = currPt;
          }        
        return x;
    }

    public double getLargestX(Shape s) {
        double x=0.0;
        for (Point pt : s.getPoints()) {
            if(pt.getX()>x)
            {
                x=pt.getX();
            }       
        }        
        return x;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
          double ans =0.0;
          DirectoryResource dr = new DirectoryResource();
          for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double peri = getPerimeter(s);
            if(peri>ans)
                 ans=peri;
          }

          return ans;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
          File temp = null;
        double ans =0.0;    
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
        double peri = getPerimeter(s);
           if(peri>ans)
        {
            ans=peri;
            temp =f;
        }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        int points = getNumPoints (s);
        double len = getAverageLength(s);
        double largeside = getLargestSide(s);
        double largex = getLargestX(s); 
        System.out.println("perimeter = " + length);
        System.out.println("number_of_points = " + points);
        System.out.println("average_length= " + len);
        System.out.println("largest_side= " + largeside);
        System.out.println("largest_x_value= " + largex);



    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
	double largest = getLargestPerimeterMultipleFiles();
        System.out.println("Largest perimeter is: " + largest);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
      System.out.println("File with largest perimeter" + getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}

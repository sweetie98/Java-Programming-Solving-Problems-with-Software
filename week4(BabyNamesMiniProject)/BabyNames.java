
/**
 * Dataset: http://www.dukelearntoprogram.com/course2/data/us_babynames.zip
 * 
 * @author (Sweety) 
 * @version (7/27/2020)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class BabyNames {
    /*
    * This method returns the total number of births males and females in a file
    */
    public void totalBirths(FileResource f) {
        int totalBirths = 0;
        int totalGirls = 0;
        int totalBoys = 0;
        int totalBoysNames =0;
        int totalGirlsNames =0;
        for(CSVRecord rec : f.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            String gender = rec.get(1);
            totalBirths += numBorn;
            if(gender.equals("M")) {
                totalBoys += numBorn;
                totalBoysNames++;
            } else {
                totalGirls += numBorn;
                totalGirlsNames++;
            }
        }

        System.out.println("Total Births: " + totalBirths);
        System.out.println("Number of Boys: " + totalBoys);
        System.out.println("Number of Girls: " + totalGirls);
        System.out.println("Total Number of Names: " + (totalBoysNames+totalGirlsNames));
        System.out.println("Number of Boys Names: " + totalBoysNames);
        System.out.println("Number of Girls Names: " + totalGirlsNames);
    }

    /*
    * This method returns the rank of the name in the file for the given gender, 
    * where rank 1 is the name with the largest number of births. 
    * If the name is not in the file, then -1 is returned.
    */
    public int getRank(int year, String name, String gender) {
        int Count=0;
       // FileResource f = new FileResource("us_babynames_small/testing/yob" + year + "short.csv");
        FileResource f = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        for(CSVRecord rec : f.getCSVParser(false)) {
            String currentName = rec.get(0);
            String currentGender = rec.get(1);
             if(currentGender.equals(gender))
             {
                 Count++;
                 if(currentName.equals(name)) {
                    return Count;
                 }
             }
        }
        return -1;
    }

    /*
    * This method returns the name of the person in the file at this rank, 
    * for the given gender, where rank 1 is the name with the largest number of births. 
    * If the rank does not exist in the file, then “NO NAME” is returned.
    */
    public String getName(int year, int rank, String gender) {
        String name = "";
        int Count=0;
        FileResource f = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        for(CSVRecord rec : f.getCSVParser(false)) {
            
             String currentGender = rec.get(1);
             if(currentGender.equals(gender))
             {
                 Count++;
                 if(rank == Count) {
                    return rec.get(0);
                 }
             }
        }
        return "NO NAME";
    }

    /*
    * This method determines what name would have been named if they were born 
    * in a different year, based on the same popularity.
    */
    public void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name,gender);
        if(rank!=-1)
        {
            String newName =getName(newYear,rank,gender);
            System.out.println(name + " born in " + year + " would be " + newName + " if she was born in " + newYear);
        }
        else
            System.out.println(name + " born in " + year + " would be " + "NOName" + " if she was born in " + newYear);
    }

    
    /*
    * This method selects a range of files to process and returns an integer, 
    * the year with the highest rank for the name and gender. 
    * If the name and gender are not in any of the selected files, it should return -1.
    */
    public int yearOfHighestRank(String name, String gender) {
        long highestRank = Long.MAX_VALUE;
        String fileName = "";
        int year=0;
        int highestYear=-1;
        DirectoryResource dr = new DirectoryResource();
        // Iterate through all files
        for(File f : dr.selectedFiles()) {
            //FileResource fr = new FileResource(f);
            fileName = f.getName();
            year = Integer.parseInt(fileName.replaceAll("[^\\d]", ""));
            long rank=getRank(year,name,gender);
                if(rank!=-1 && rank<highestRank)
                {
                     highestRank=rank;
                     highestYear=year;
                }
            
        }
            return highestYear;
        }
    
    /*
    * This method returns the average rank of a name in multiple files
    */
    public double getAverageRank(String name, String gender) {
        String fileName ="";
        double avgRank = 0.0;
        int year=0;
        int totalRank=0;
        int total = 0;
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()) {
            //FileResource fr = new FileResource(f);
            fileName = f.getName();
            year = Integer.parseInt(fileName.replaceAll("[^\\d]", ""));
            int rank=getRank(year,name,gender);
            if(rank!=-1)
                totalRank+=rank;   
            total++;
        }
        avgRank = totalRank / (double)total;
        return avgRank;
    }
    
  /*
    * This method returns the total births of the same gender that are ranked higher
  * than the parameter name
    */
   
    public int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int totalBirth = 0;
        int Count=0;
        int rank = getRank(year, name, gender);
        FileResource f = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        for(CSVRecord rec : f.getCSVParser(false)) {
            
             String currentGender = rec.get(1);
             if(currentGender.equals(gender))
             {
                 Count++;
                 if(rank == Count) {
                    return totalBirth;
                 }
                  totalBirth+=Integer.parseInt(rec.get(2));
             }
        }
        return -1;
    }

  /*
    * This method is used for testing other methods
    */
    public void testTotalBirth(int year) {
        FileResource f = new FileResource("us_babynames/us_babynames_by_year/yob" + year + ".csv");
        totalBirths(f);

    }
}
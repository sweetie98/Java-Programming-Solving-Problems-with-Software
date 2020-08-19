
/**
 * Dataset : http://www.dukelearntoprogram.com/course2/data/nc_weather.zip
 * 
 * @author (Sweety) 
 * @version (7/28/2020)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class WeatherInfo {
    /*
    * This method returns the CSVRecord with the coldest temperature in the file and thus 
    * all the information about the coldest temperature, such as the hour of the coldest temperature.
    */
    public CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestTemp = null;
        for(CSVRecord currRow : parser) {
            if(coldestTemp == null) {
                coldestTemp = currRow;
            } 
            else {
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(coldestTemp.get("TemperatureF"));
                if(currTemp < lowestTemp) {
                            coldestTemp = currRow;
                }
            }
        }
        return coldestTemp;
    }
    
    /*This method tests the coldestHourInFile method and print out information about that coldest temperature, 
     * such as the time of its occurrence. 
     */
    public void testColdestHourInFile() {
        FileResource f = new FileResource();
        CSVParser parser = f.getCSVParser();

        CSVRecord lowestTemp = coldestHourInFile(parser);
        System.out.println(lowestTemp.get("TemperatureF") + ": " + lowestTemp.get("DateUTC"));
    }

    /*This method returns a string that is the name of the file 
     * from selected files that has the coldest temperature.
     */
    public String fileWithColdestTemperature() {
        File file = null;
        CSVRecord coldestTemp = null;

        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRow = coldestHourInFile(parser);
            
            if(coldestTemp == null) {
                coldestTemp = currRow;
                file = f;
            } 
            else {
                double currTemp = Double.parseDouble(currRow.get("TemperatureF"));
                double lowestTemp = Double.parseDouble(coldestTemp.get("TemperatureF"));
                if(currTemp < lowestTemp && currTemp > -50) {
                    coldestTemp = currRow;
                    file= f;
                }
            }
        }
        return file.getName();
    }
    
    /* This method tests the fileWithcoldestTemperature method.
     * 
     */
    public void testFileWithColdestTemperature() {
        String fileName= fileWithColdestTemperature();
        String year = fileName.substring(8,12);
        FileResource fr = new FileResource("nc_weather/"+year+"/"+fileName);
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestTemp = coldestHourInFile(parser);
        System.out.println("File: "+ fileName+",Coldest Temperature: " + lowestTemp.get("TemperatureF")+",Time: " + lowestTemp.get("DateUTC"));
    }

    /* This method returns the CSVRecord that has the lowest humidity. 
     * If there is a tie, then return the first such record that was found.
     */
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestHumdity = null;
        int currHumd;
        int lowestHumd;
        for(CSVRecord currRow : parser) {
            if(lowestHumdity == null) {
                lowestHumdity = currRow;
            } 

            else {
                if(!currRow.get("Humidity").equals("N/A") && !lowestHumdity.get("Humidity").equals("N/A")) {
                    currHumd = Integer.parseInt(currRow.get("Humidity"));
                    lowestHumd = Integer.parseInt(lowestHumdity.get("Humidity"));
                    
                    if(currHumd < lowestHumd) {
                        lowestHumdity = currRow;
                    }
                }
            }
        }
        return lowestHumdity;
    }

    /* This method tests lowestHumidityInFile method*/
    public void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord lowestHumdity = lowestHumidityInFile(parser);
        System.out.println(lowestHumdity.get("Humidity") + " at " + lowestHumdity.get("DateUTC"));
    }
    
    /* This method returns a CSVRecord that has the lowest humidity over all the files.
     * If there is a tie, then return the first such record that was found.
    */
    public CSVRecord lowestHumidityInManyFiles() {
        CSVRecord lowestHumdity = null;
        int currHumd;
        int lowestHumd;

        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currRow = lowestHumidityInFile(parser);
            
            if(lowestHumdity == null) {
                lowestHumdity = currRow;
            } 
            else {
                int currTemp = Integer.parseInt(currRow.get("Humidity"));
                int lowestTemp = Integer.parseInt(lowestHumdity.get("Humidity"));
                if(currTemp < lowestTemp) {
                    lowestHumdity = currRow;
                }

                else {
                    if(currRow.get("Humidity") != "N/A" && lowestHumdity.get("Humidity") != "N/A") {
                        currHumd = Integer.parseInt(currRow.get("Humidity"));
                        lowestHumd = Integer.parseInt(lowestHumdity.get("Humidity"));
                        
                        if(currHumd < lowestHumd) {
                            lowestHumdity = currRow;
                        }
                    }
                }
            }
        }
        return lowestHumdity;
    }

    /* This method tests lowestHumidityInManyFiles method*/
    public void testLowestHumidityInManyFiles() {
        CSVRecord record = lowestHumidityInManyFiles();
        System.out.println(record.get("Humidity") + " at " + record.get("DateUTC"));
    }

    /* This method returns a double that represents the average temperature in the file.*/
    public double averageTemperatureInFile(CSVParser parser) {
        double num = 0.0;
        double sum = 0.0;

        for(CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            sum += temp;
            num++;
        }

        double average = sum / num;
        return average;
    }

     /* This method tests averageTemperatureInFile method*/
    public void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureInFile(parser);

        System.out.println("average temperature is " + avg);
    }

    /* This method returns a double that represents the average temperature 
     * of only those temperatures when the humidity was greater than or equal to value. */
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
        double num = 0.0;
        double sum = 0.0;

        for(CSVRecord record : parser) {
            double temp = Double.parseDouble(record.get("TemperatureF"));
            int humidity = Integer.parseInt(record.get("Humidity"));
            if(humidity >= value) {
                sum += temp;
                num++;
            }
        }

        double average = sum / num;
        return average;
    }

    /* This method tests averageTemperatureWithHighHumidityInFile method*/
    public void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avg = averageTemperatureWithHighHumidityInFile(parser, 80);

        if(!Double.isNaN(avg)) {
            System.out.println("average temperature is " + avg);
        } else {
            System.out.println("No Temperature was found");
        }
    }
}

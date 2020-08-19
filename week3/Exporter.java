
/**
 * Dataset: http://www.dukelearntoprogram.com/course2/data/exports.zip
 * 
 * @author (sweety) 
 * @version (7/28/2020)
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class Exporter {
       /*
    * This method returns a string of information about 
    * the country or returns “NOT FOUND” if there is no 
    * information about the country.
    */
    public String countryInfo(CSVParser parser, String country) {
        for(CSVRecord rec : parser) {
            String currCountry = rec.get("Country");
            if(currCountry.equals(country)) {
                String exports = rec.get("Exports");
                String value = rec.get("Value (dollars)");
                return( currCountry + ": " + exports + ": " + value);
            
            }
        }

        return "NOT FOUND";
        }
        
    /*
    * This method prints the names of all the countries that 
    * have both exportItem1 and exportItem2 as export items.
    */
    public void listExportersTwoProducts(CSVParser parser, String exportItem1, String exportItem2) {
        for(CSVRecord rec : parser) {
            String exports = rec.get("Exports");
            if(exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(rec.get("Country"));
            }
        }
    }

    /*
    * This method returns the number of countries 
    * that export exportItem.
    */
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int numExporters = 0;
        for(CSVRecord rec : parser) {
            String exports = rec.get("Exports");
            if(exports.contains(exportItem)) {
                numExporters++;
            }
        }
        return numExporters;
    }

    /*
    * This method prints the names of countries and their
    * Value amount for all countries whose Value (dollars) 
    * string is longer than the amount string.
    */
    public void bigExporters(CSVParser parser, String amount) {
        for(CSVRecord rec : parser) {
            String value = rec.get("Value (dollars)");
            String country = rec.get("Country");

            if(value.length() > amount.length()) {
                System.out.println(country + ": " + value);
            }
        }
    }
    
    /* This method tests all the above methods */
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        bigExporters(parser, "$999,999,999,999");
        //String info = countryInfo(parser, "Germany");
        //System.out.println(info);
        
        //listExportersTwoProducts(parser, "gold", "diamonds");
        //int numOfCountries = numberOfExporters(parser, "gold");
        //System.out.println(numOfCountries);

        
    }
}


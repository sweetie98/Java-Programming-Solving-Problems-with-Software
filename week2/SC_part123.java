
/**
 * Write a description of SC_part123 here.
 * 
 * @author (Sweety) 
 * @version (7/26/2020)
 */
import edu.duke.*;
public class SC_part123 {
    public int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while(currIndex != -1) {
            int diff = currIndex - startIndex;
            if(diff % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }

        return -1;
    }

    public void testFindStopCodon() {
            String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";
                
                int dex = findStopCodon(dna, 0,"TAA");
                System.out.println(dex);
        
                dex = findStopCodon(dna, 9,"TAA");
                System.out.println(dex);
        
                dex = findStopCodon(dna, 1,"TAA");
                System.out.println(dex);
        
                dex = findStopCodon(dna, 0,"TAG");
                System.out.println(dex);

    }

    public String findGene(String dna,int where) {
        int startIndex = dna.indexOf("ATG",where);
        if(startIndex == -1) {
            return "";
        }

        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        int minIndex = 0;
        if(taaIndex == -1 || (tagIndex != -1 && tagIndex < taaIndex)) {
            minIndex = tagIndex;
        } else {
            minIndex = taaIndex;
        }

        if(minIndex == -1 || (tgaIndex != -1 && tgaIndex < minIndex)) {
            minIndex = tgaIndex;
        }

        if(minIndex == -1) {
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);
    }

    public void testFindGene() {
        String one = "ATFxxxyyyzzzTAAxxxTAGxxx";
        String two = "xxxATGxxxyyyxxTAGxTAAxxx";
        String three = "xyyATGxxxyyyuuuTGAxxxTAGxxx";
        String four = "xyyATGxxxyyxxxyuuuTGAxxxTAGxxx";

        System.out.println("Gene is: " + one + " " + findGene(one,0));
        System.out.println("Gene is: " + two + " " + findGene(two,0));
        System.out.println("Gene is: " + three + " " + findGene(three,0));
        System.out.println("Gene is: " + four + " " + findGene(four,0));
    }
    
    
    public StorageResource getAllGenes(String dna){
        StorageResource sr = new StorageResource();
        int startIndex =0;
        while (true) {
            String currentGene = findGene(dna,startIndex);
            if (currentGene.isEmpty()) {
                break;
            } else {
                //System.out.println(currentGene);
                sr.add(currentGene);
                startIndex = dna.indexOf(currentGene,startIndex)+currentGene.length();

            }

        }
        return sr;
    } 
    

    public double cgRatio(String dna) {
        int count =0;
        int len = dna.length();

        for(int i = 0; i < len; i++) {
            if(dna.charAt(i) == 'C' || dna.charAt(i) == 'G') {
                count++;
            }
        }

        double ratio = count / (double)len;
        return ratio;
    }
    
    public int countCTG(String dna) {
        int startIndex = 0;
        int count = 0;
        int index = dna.indexOf("CTG", startIndex);
        
        while(true) {
            if(index == -1 ) {
                break;
            }
            
            count++;
            index = dna.indexOf("CTG", index+3);
        }
        return count;
    }
    
    public void processGenes() {
        FileResource f = new FileResource();
        StorageResource sr = getAllGenes(f.asString());
        int len=0;
        for(String s : sr.data()) {
            if(s.length() > len) {
                len = s.length();
            }
        }
        System.out.println(len);
    }
    
	
    public static void main() {
        Part1 test = new Part1();
        test.testFindGene();
    }
}

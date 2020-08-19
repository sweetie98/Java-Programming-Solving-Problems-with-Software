
/**
 * Write a description of Part3 here.
 * 
 * @author (Sweety) 
 * @version (7/26/2020)
 */
import edu.duke.*;
public class Part3 {
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
     public String findOneGene(String dna,int where) {
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

    public void printAllGenes(String dna) {
	     int startIndex =0;
		while (true) {
			String currentGene = findOneGene(dna,startIndex);
			if (currentGene.isEmpty()) {
				break;
			} 
			else {
				System.out.println(currentGene);
				startIndex = dna.indexOf(currentGene,startIndex)+currentGene.length();

			}

		}
} 
    
    public int countGenes(String dna)
    {
             int startIndex =0;
             int count =0;
		while (true) {
			String currentGene = findOneGene(dna,startIndex);
			if (currentGene.isEmpty()) {
				break;
			} 
			else {
				
				count++;
				startIndex = dna.indexOf(currentGene,startIndex)+currentGene.length();

			}

		}  
	return count;
    }
   
    public void testCountGenes()
    {
        int ans1= countGenes("ATGTAAGATGCCCTAGT");
        System.out.println("Example1 ans: "+ans1);
    }

}


/**
 * Write a description of part1 here.
 * 
 * @author (Sweety) 
 * @version (7/26/2020)
 */
public class Part1 {
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

	public void printAllGenes(String dna) {
	    int startIndex =0;
		while (true) {
			String currentGene = findGene(dna,startIndex);
			if (currentGene.isEmpty()) {
				break;
			} else {
				System.out.println(currentGene);
				startIndex = dna.indexOf(currentGene,startIndex)+currentGene.length();

			}

		}
	} 

	public static void main() {
        Part1 test = new Part1();
        test.testFindGene();
    }
}
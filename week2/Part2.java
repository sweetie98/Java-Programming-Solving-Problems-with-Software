
/**
 * Write a description of Part2 here.
 * 
 * @author (Sweety) 
 * @version (7/26/2020)
 */
public class Part2 {
    public int howMany(String stringa, String stringb)
    {
        int startIndex= stringb.indexOf(stringa);
        int count=0;
        while(startIndex!= -1)
        {
            count++;
            startIndex =stringb.indexOf(stringa,startIndex+stringa.length());
        }
        return count;
    } 
    public void  testHowMany()
    {
        int ans1= howMany("GAA","ATGAACGAATTGAATC");
        System.out.println("example1 ans: "+ans1);
        int ans2 = howMany("AA","ATAAAA");
        System.out.println("example2 ans: "+ans2);
    }
}

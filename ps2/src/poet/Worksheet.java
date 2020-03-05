package poet;
import java.util.*;
import java.text.BreakIterator;

public class Worksheet {

    public static void main(String[] args) {
        
//        String s = "all the sheep flew";
//        
//        String[] split = s.split("\\W+");
//        System.out.println(split);
//        for (String w : split) {
//            System.out.println(w);
//        }
        
        
        
        String text = "Mary had a little lamb.";
        
        List<String> words = new ArrayList<String>();
        BreakIterator breakIterator = BreakIterator.getWordInstance();
        breakIterator.setText(text);
        int lastIndex = breakIterator.first();
        while (BreakIterator.DONE != lastIndex) {
            int firstIndex = lastIndex;
            lastIndex = breakIterator.next();
            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
                words.add(text.substring(firstIndex, lastIndex));
            }
        }
        System.out.println(words);
        System.out.println("done");
        
//        List<String> words = new ArrayList<>();
//        
//        BreakIterator breakit = BreakIterator.getWordInstance();
//        breakit.setText(text);
//        
//        
//        int lastIndex = breakit.first();
//        while (BreakIterator.DONE != lastIndex) {
//            int firstIndex = lastIndex;
//            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
//                words.add(text.substring(firstIndex, lastIndex));
//            }
//        }
//        System.out.println(text);
//        System.out.println(words);

//      
//      
//      File testFile = new File("test/poet/TestAffinityGraphMultipleWeights");
//      Scanner scan = new Scanner(testFile);
//
//      
//      String s = "";
//      while (scan.hasNextLine()) {
//          
//          
//          s = s.concat(scan.nextLine()) + "\n";
//          
////          System.out.println(scan.nextLine());
////          System.out.println("YOOOO");
//      }        
//      scan.close();
//      
////      System.out.println(s);
//      
//      FileWriter writer = new FileWriter("C:\\Users\\alexk\\Desktop\\6.005_Software_Construction\\TestText.txt");
//      
//      writer.write(s);
//      
//      writer.close();
//      
        
    }

}

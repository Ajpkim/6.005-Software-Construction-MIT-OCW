package poet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Worksheet {

    public static void main(String[] args) throws IOException {

        
        String text = "Mary had a little lamb. \nand here is the second line.";
                     + "and here is a new line";
        
        System.out.println("text: " + text);
        System.out.println("");
        
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
        
        
        
        
//        
//        File file = new File("test/poet/TestAffinityGraphMultipleWeights");
//        GraphPoet graph = new GraphPoet(file);
//        System.out.println(graph);
//        
        
        
//        
//        
//        File testFile = new File("test/poet/TestAffinityGraphMultipleWeights");
//        Scanner scan = new Scanner(testFile);
//
//        
//        String s = "";
//        while (scan.hasNextLine()) {
//            
//            
//            s = s.concat(scan.nextLine()) + "\n";
//            
////            System.out.println(scan.nextLine());
////            System.out.println("YOOOO");
//        }        
//        scan.close();
//        
////        System.out.println(s);
//        
//        FileWriter writer = new FileWriter("C:\\Users\\alexk\\Desktop\\6.005_Software_Construction\\TestText.txt");
//        
//        writer.write(s);
//        
//        writer.close();
//        

    }

}

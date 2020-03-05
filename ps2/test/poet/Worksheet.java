package poet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Worksheet {

    public static void main(String[] args) throws IOException {
        
        File file = new File("test/poet/TestAffinityGraphMultipleWeights");
        GraphPoet graph = new GraphPoet(file);
        System.out.println(graph);
        
        
        
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

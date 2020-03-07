package poet;
import java.util.*;

import graph.ConcreteEdgesGraph;
import graph.Graph;
import poet.GraphPoet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;


public class Worksheet {

    public static void main(String[] args) throws IOException {
 
        
//      String text = "the cow jumped over the moon";
//      String testPoem = "" + text;
//      
//      
//      BreakIterator breakIterator = BreakIterator.getWordInstance();
//      breakIterator.setText(text);
//      
//      int targetEnd = 0;
//      
//      targetEnd = breakIterator.next() == BreakIterator.DONE ? text.length() : breakIterator.next();
//      System.out.println(targetEnd);
        
        
///////////////////////////// TESTING POEM() ///////////////////////////// 
        
      File corpus = new File("test/poet/JumpingCow.txt");
      
      GraphPoet graphPoet = new GraphPoet(corpus);
      
      String input = "the cow jumped over the moon.";
      String poem = graphPoet.poem(input);
      
      System.out.println("original input: " + input);
      System.out.println("resulting poem: " + poem);
            
      
      
      // so i know that FindBridgeWord is being called correctly in the correct location
      // for some reason it's not finding the correct bridge words always
      
  
//      List<String> verticesList = new ArrayList<>();  // create list to iterate through
//      verticesList.addAll(graphPoet.graph.vertices());
//      
//      for (String v : verticesList) {
//          System.out.println(v);
//          System.out.println("sources: " + graphPoet.graph.sources(v));
//          System.out.println("targets: " + graphPoet.graph.targets(v));
//          System.out.println("");
//      }
      
      // Hmmm, maps actually look good.
      // ----> wasn't controlling for case of source and target words when looking
      // for bridges
      
      
        
        
        
        
        
        
//////////////////////////////////////////////////////////////////////////////////

//        String s = "0123456789";
//        List<String> s = Arrays.asList("0", "1", "2", "3", "4");
//        for (int i = 0; i < s.size(); i++) {
//            System.out.println(s.get(i));
//        }

        
        
        
        
        
//        String text = "the cow jumped over the moon";
//        String testPoem = "" + text;
//        
//        
//        BreakIterator breakIterator = BreakIterator.getWordInstance();
//        breakIterator.setText(text);
//        
//        int indexChange = 0;
//        int sourceStart = breakIterator.first();
//        int sourceEnd = breakIterator.next();
//        int targetStart = breakIterator.next(); 
//        int targetEnd = breakIterator.next();
////        
//        String source;
//        String target;
//        
//        source = text.substring(sourceStart, sourceEnd);
//        target = text.substring(targetStart, targetEnd);
//        
//        System.out.println("source: " + source);
//        System.out.println("target: " + target);
//        
//        System.out.println("sourceStart: " + sourceStart + ": " + text.charAt(sourceStart));
//        System.out.println("sourceEnd: " + sourceEnd + ": " + text.charAt(sourceEnd));
//        System.out.println("targetStart: " + targetStart+ ": " + text.charAt(targetStart));
//        System.out.println("targetEnd: " + targetEnd + ": " + text.charAt(targetEnd));
//        System.out.println("\n---\n");
//        
//        //  going to try adding a bridge word now to testPoem
//        System.out.println("original input: " + text);
//        System.out.println("adding 'amazing' after 1st word...");
//        System.out.println("");
//        
//        String bridgeWord = "amazing";
//        
//        // first substring index will always be 0 
//        testPoem = testPoem.substring(0, targetStart) + bridgeWord + " " + testPoem.substring(targetStart + indexChange);
//
//        // adjust indexChange AFTER adjusting poem String
//        
//        indexChange += bridgeWord.length() + 1;
//        
//        System.out.println("testPoem: " + testPoem);
//        
//        System.out.println("");
//        System.out.println("now, adding 'barely' after 'cow'...");
//        
//        
//        // after each source:target analysis the source indices must be set to the existing target indices... targets become sources
//        sourceStart = targetStart;
//        sourceEnd = targetEnd;
//        
//        targetStart = breakIterator.next(); 
//        targetEnd = breakIterator.next();
//        
//        System.out.println("");        
//        
//        source = text.substring(sourceStart, sourceEnd);
//        target = text.substring(targetStart, targetEnd);
//        
//        System.out.println("source: " + source);
//        System.out.println("target: " + target);
//        
//        System.out.println("sourceStart: " + sourceStart + ": " + text.charAt(sourceStart));
//        System.out.println("sourceEnd: " + sourceEnd + ": " + text.charAt(sourceEnd));
//        System.out.println("targetStart: " + targetStart+ ": " + text.charAt(targetStart));
//        System.out.println("targetEnd: " + targetEnd + ": " + text.charAt(targetEnd));
//        
//        System.out.println("\n---\n");
//        
//        
//        bridgeWord = "barely";
//                
//        
//        testPoem = testPoem.substring(0, targetStart + indexChange) + bridgeWord + " " + testPoem.substring(targetStart + indexChange);
//
////        System.out.println(targetStart+indexChange);
//        // why does indexChange need to be +1?
//        // ---> because each bridge words ADDS an extra space that wasn't there before
//        
//        
//        
//        indexChange += bridgeWord.length();
//        
//        System.out.println("testPoem: " + testPoem);
//        
        
        
        
//////////////////////////////////////////////////////////////////////////////////        
        
//        String w = text.substring(sourceStart, sourceEnd);
//        System.out.println("[" + w + "]");
//        
        
        
//        while(sourceEnd != breakIterator.DONE) {
//            source = input.substring(sourceStart, sourceEnd);
//        
        
                
        
    }
}
        
        
        
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        
//        File corpus = new File("test/poet/TestMultipleBridgeWordsCompetingWeights.txt");
//        
////        GraphPoet graphPoet = new GraphPoet(corpus);
//        
//        Graph<String> graph = new ConcreteEdgesGraph();
//        
//        String text = GraphPoet.GetTextFromFile(corpus);
//        
//        System.out.println(text);
//        
//        List<String> words = GraphPoet.GetWordsFromString(text);
//        
//        System.out.println(words);
//        System.out.println("--------");
//        System.out.println("");
//        
//        GraphPoet.BuildAffinityGraph(words, graph);
//        System.out.println(graph);
        
        
//       /// ////////////////////////////////////////////////////////
//
//      FileReader fileReader = new FileReader("test/poet/TestMultipleBridgeWordsCompetingWeights.txt");
//      
//      String text = "";
//      
//      BufferedReader bufferedReader = new BufferedReader(fileReader);
//      
//      String line;
//      while((line = bufferedReader.readLine()) != null) {
//          text = text + line + "\n";
//      }
//      
//      
//      System.out.println(text);
//      System.out.println("-----------------------");
//      
////      
//      List<String> words = new ArrayList<String>();
//      BreakIterator breakIterator = BreakIterator.getWordInstance();
//      breakIterator.setText(text);
//      int lastIndex = breakIterator.first();      
//      
//      while (BreakIterator.DONE != lastIndex) {
//          int firstIndex = lastIndex;
//          lastIndex = breakIterator.next();
//          System.out.println("firstIndex: " + firstIndex + "   lastIndex: " + lastIndex);
//          if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
//              words.add(text.substring(firstIndex, lastIndex));
//          }
//      }
//      System.out.println(words);
//    }
//}
      
   ////////////////////////////////////////////////   
//      
//      System.out.println("words: " + words);
//      System.out.println("---------------");
//
//      Graph<String> graph = new ConcreteEdgesGraph<>();
//      
//      String source;
//      String target;
//      int weight;
//          
//      for (int i = 1; i < words.size(); i++) {            
//          source = words.get(i - 1);
//          target = words.get(i);
//          
//          weight = graph.targets(source).get(target) == null ? 1 : graph.targets(source).get(target) + 1;
//          
//          System.out.println("weight is: " + weight);
//          
//          graph.set(source, target, weight);
//          
//      }
//          
//      List<String> verticesList = new ArrayList<>();
//      verticesList.addAll(graph.vertices());
//      System.out.println(verticesList);
//
//      for (String v : verticesList) {
//          System.out.println(v + ": ");
//          System.out.println("sources: " + graph.sources(v));
//          System.out.println("targets: " + graph.targets(v));
//          System.out.println("");
//          }
//      }
          
          
          
          // commented for TS
//          weight = graph.targets(source).get(target) == null ? 0 : graph.targets(source).get(target) + 1;
          
//          if (graph.targets(source).get(target) != null) {
//              weight = graph.targets(source).get(target) + 1;
//          }
//          else {
//              weight = 0;
//          }
//          
//
//          if (!graph.targets(source).keySet().contains(target)) {
//              weight = 1;
//          }
//          else {
//              weight = graph.targets(source).get(target) + 1;
//          }
//          
//          System.out.println(weight);
//          System.out.println("");
//          System.out.println(graph.targets(source).get(target));
//          System.out.println("");
//          
//          weight = 1;
//          System.out.println(graph.set(source, target, weight));
          
      
   
      
      
      
      
      
      
      
      
      
//      NOT WORKING BC ADDING 0 WEIGHT DOESNT ADD EDGE
    
    // entries not getting added --> edges not being properly tracked.... vertices being added incorrectly
       
//        
//        public void BuildAffinityGraph(List<String> words, Graph<String> graph) {
//            
//            String source;
//            String target;
//            int weight;
//              
//            for (int i = 1; i < words.size(); i++) {            
//                source = words.get(i - 1);
//                target = words.get(i);
//                weight = graph.targets(source).get(target) == null ? 0 : graph.targets(source).get(target) + 1;
//                
//                graph.set(source, target, weight);
//            }     
//        }
//
//        
//        
//        /**
//         * Parses data from file into a string
//         * 
//         * @param file is file path to be read from 
//         * @return String of data read from file 
//         */
//                
//        public static String GetTextFromFile(String file) {
//            
//            BufferedReader bufferedReader = null;
//            
//            try {
//                FileReader fileReader = new FileReader(file);
//                bufferedReader = new BufferedReader(fileReader);
//            }
//            catch (FileNotFoundException fnfex) {
//                System.out.println(fnfex.getMessage() + "the file was not found");
//                System.exit(0);
//            }
//            
//            String text = "";  
//            try {                        
//                String line;
//                while((line = bufferedReader.readLine()) != null) {
//                    text = text + line + "\n";    
//                }
//            }
//            catch (IOException ioe) {
//                System.out.println(ioe.getMessage() + "error reading file");
//            }
//            
//            return text;
//        }
//        
//        
//        
//        /**
//         * Create list of all the words that appear in text. Words that appear 
//         * multiple times in text will have multiple entries
//         * 
//         * @param text string to be parsed
//         * @return list<String> of every word in text 
//         */
//        public List<String> GetWordsFromString(String text){
//            
//            List<String> words = new ArrayList<String>();
//            BreakIterator breakIterator = BreakIterator.getWordInstance();
//            breakIterator.setText(text);
//            int lastIndex = breakIterator.first();
//            
//            // Building word list
//            while (BreakIterator.DONE != lastIndex) {
//                int firstIndex = lastIndex;
//                lastIndex = breakIterator.next();
//                if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
//                    words.add(text.substring(firstIndex, lastIndex).toLowerCase());
//                }
//            }
//            return words;
//        }
    
        
        
        
        
        
        
        
        
//        Map<String, Integer> map = new HashMap<>();
//        map.put("abc", 3);
//        
//        System.out.println(map.get("def") == null ? "not a val" : "val exists");
//        
        
        
//        String s = "all the sheep flew";
//        
//        String[] split = s.split("\\W+");
//        System.out.println(split);
//        for (String w : split) {
//            System.out.println(w);
//        }
        
        
//       #####################
//        String text = "Mary had a!! little lamb. \nand here is the second line.";
//        System.out.println("text: " + text);
//        System.out.println("");
//        ######################
//        
//        FileReader fileReader = new FileReader("test/poet/TestMultipleBridgeWordsCompetingWeights.txt");
//        
//        String text = "";
//        
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//        
//        String line;
//        while((line = bufferedReader.readLine()) != null) {
//            text = text + line + "\n";
//        }
//        
//        
//        System.out.println(text);
//        System.out.println("-----------------------");
//        
////        
//        List<String> words = new ArrayList<String>();
//        BreakIterator breakIterator = BreakIterator.getWordInstance();
//        breakIterator.setText(text);
//        int lastIndex = breakIterator.first();
//        
//        System.out.println(lastIndex);
//        
//        while (BreakIterator.DONE != lastIndex) {
//            int firstIndex = lastIndex;
//            lastIndex = breakIterator.next();
//            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
//                words.add(text.substring(firstIndex, lastIndex));
//            }
//        }
//        
//        System.out.println("words: " + words);
//        System.out.println("done");
//        
        
        /////////////////////////////////////////
//        
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
        
//    }




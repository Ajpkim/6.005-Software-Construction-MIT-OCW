/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import graph.Graph;

/**
 * A graph-based poetry generator.
 * 
 * <p>GraphPoet is initialized with a corpus of text, which it uses to derive a
 * word affinity graph.
 * Vertices in the graph are words. Words are defined as non-empty
 * case-insensitive strings of non-space non-newline characters. They are
 * delimited in the corpus by spaces, newlines, or the ends of the file.
 * Edges in the graph count adjacencies: the number of times "w1" is followed by
 * "w2" in the corpus is the weight of the edge from w1 to w2.
 * 
 * <p>For example, given this corpus:
 * <pre>    Hello, HELLO, hello, goodbye!    </pre>
 * <p>the graph would contain two edges:
 * <ul><li> ("hello,") -> ("hello,")   with weight 2
 *     <li> ("hello,") -> ("goodbye!") with weight 1 </ul>
 * <p>where the vertices represent case-insensitive {@code "hello,"} and
 * {@code "goodbye!"}.
 * 
 * <p>Given an input string, GraphPoet generates a poem by attempting to
 * insert a bridge word between every adjacent pair of words in the input.
 * The bridge word between input words "w1" and "w2" will be some "b" such that
 * w1 -> b -> w2 is a two-edge-long path with maximum-weight weight among all
 * the two-edge-long paths from w1 to w2 in the affinity graph.
 * If there are no such paths, no bridge word is inserted.
 * In the output poem, input words retain their original case, while bridge
 * words are lower case. The whitespace between every word in the poem is a
 * single space.
 * 
 * <p>For example, given this corpus:
 * <pre>    This is a test of the Mugar Omni Theater sound system.    </pre>
 * <p>on this input:
 * <pre>    Test the system.    </pre>
 * <p>the output poem would be:
 * <pre>    Test of the system.    </pre>
 * 
 * <p>PS2 instructions: this is a required ADT class, and you MUST NOT weaken
 * the required specifications. However, you MAY strengthen the specifications
 * and you MAY add additional methods.
 * You MUST use Graph in your rep, but otherwise the implementation of this
 * class is up to you.
 */
public class GraphPoet {
    
//    private final Graph<String> graph = Graph.empty();
    Graph<String> graph = Graph.empty();
    
    // Abstraction function:
    //      - Represents an affinity graph of words in which the edges weight between w1 and w2 equals 
    //      the numbers of times that w2 appears directly after w1 in the corpus text
    
    // Representation invariant:
    //      - All weights in the graph are > 0
    //      - All vertices are unique 
    //      
    
    // Safety from rep exposure:
    //   TODO
    
    /**
     * Create a new poet with the graph from corpus (as described above).
     * 
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        
        String text = GetTextFromFile(corpus);
        List<String> words = GetWordsFromString(text);
        BuildAffinityGraph(words, graph);               
    }
    
    
    /**
     *  Modifies the graph by building an affinity graph with the given list of words.
     *  Implements a weighted directed graph. 
     *  
     * @param words: list<String> of words (parsed from corpus file)
     * @param graph
     */
    public static void BuildAffinityGraph(List<String> words, Graph<String> graph) {
        
        String source;
        String target;
        int weight;
            
        for (int i = 1; i < words.size(); i++) {            
            source = words.get(i - 1);
            target = words.get(i);
            weight = graph.targets(source).get(target) == null ? 1 : graph.targets(source).get(target) + 1; 
            
            graph.set(source, target, weight);
        }     
    }
    
    
    /**
     * Parses data from file into a string
     * 
     * @param file is file path to be read from 
     * @return String of data read from file 
     */
    public static String GetTextFromFile(File file) {
        
        BufferedReader bufferedReader = null;
        
        try {
            FileReader fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        }
        catch (FileNotFoundException fnfex) {
            System.out.println(fnfex.getMessage() + "the file was not found");
            System.exit(0);
        }
        
        String text = "";  
        try {                        
            String line;
            while((line = bufferedReader.readLine()) != null) {
                text = text + line + "\n";    
            }
        }
        catch (IOException ioe) {
            System.out.println(ioe.getMessage() + "error reading file");
        }
        
        return text;
    }
    
    
    /**
     * Create list of all the words that appear in text. Words that appear 
     * multiple times in text will have multiple entries
     * 
     * @param text string to be parsed
     * @return list<String> of every word in text 
     */
    public static List<String> GetWordsFromString(String text){
        
        List<String> words = new ArrayList<String>();
        BreakIterator breakIterator = BreakIterator.getWordInstance();
        breakIterator.setText(text);
        int lastIndex = breakIterator.first();
        int firstIndex;
        
        // Building word list
        while (BreakIterator.DONE != lastIndex) {
            firstIndex = lastIndex;
            lastIndex = breakIterator.next();
            if (lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
                words.add(text.substring(firstIndex, lastIndex).toLowerCase());
            }
        }
        return words;
    }
    
    
    // TODO checkRep
    
    /**
     * Generate a poem.
     * 
     * @param input string from which to create the poem
     * @return poem (as described above)
     */
    public String poem(String input) {
        
        // for every input source/target pair I need to check if there is a vertex with source as source 
        // and target as target and if yes then insert the option that maximizes 2 edge length.
        
        
        // how to iterate through the input string? 
        // could use a break iterator to set source and target...
        // issue with moving to list is that I need to preserve the punctuation of original input string
        // another issue is manipulating the string while iterating through it.
        
        
        // break iterator to identify each source/target pair 
        // BreakIterator does have a last() method that could be helpful.
        
        // So maybe I iterate through input with break iterator identifying possible bridge word opportunities,
        // track the index of next break after target... don't need to track since next will automove the iterator
        // variables to track locations will be
        //      - sourceStart
        //      - sourceEnd
        //      - targetStart
        //      - targetEnd
        //      --> these will allow me to pull out the words from string for analysis
        // then I will insert bridge word into string copy... and track how far up I've moved the index?
        // if I keep a running tally of how much I've moved the index then I should be able to insert bridge
        // words at the correct locations. 

        // BreakIterator.next() does point to the end of word A and beginning of adjacent word B
        
        BreakIterator breakIterator = BreakIterator.getWordInstance();
        breakIterator.setText(input);
        
        String poem = "" + input;
        
        int indexShift = 0;
        int sourceStart = breakIterator.first();
        int sourceEnd = breakIterator.next();
        int targetStart = breakIterator.next();
        int targetEnd = breakIterator.next();      

        String source;
        String target;
        String bridgeWord;        
        
        while(targetEnd < input.length()) {
            
            source = input.substring(sourceStart, sourceEnd).toLowerCase();
            target = input.substring(targetStart, targetEnd).toLowerCase();

            bridgeWord = FindBridgeWord(source, target);
            
            if (bridgeWord != "") { 
            
                poem = poem.substring(0, targetStart + indexShift) 
                        + bridgeWord + " " + poem.substring(targetStart + indexShift);
            
                indexShift += bridgeWord.length() + 1;
            }
            
            sourceStart = targetStart;
            sourceEnd = targetEnd;
            targetStart = breakIterator.next();
            
            targetEnd = breakIterator.next();
            if (targetEnd == BreakIterator.DONE) {
                targetEnd = input.length();
            }            
        }
        return poem;
    }

    
    public String FindBridgeWord(String source, String target) {
        
        String bridgeWord = "";
        
        if (!graph.vertices().contains(source) || !graph.vertices().contains(target)) {
            return bridgeWord;
        }

        List<String> verticesList = new ArrayList<>();
        verticesList.addAll(graph.vertices());
        
        String word;
        int best = 0;
        int w1;
        int w2;
        
        for (int i = 0; i < verticesList.size(); i++) {
            
            word = verticesList.get(i);
            
            if (graph.sources(word).containsKey(source)) {
                w1 = graph.sources(word).get(source);
            
                if (graph.targets(word).containsKey(target)) {
                    w2 = graph.targets(word).get(target);
                
                    if (best < w1 + w2) {
                        best = w1 + w2;                        
                        bridgeWord = word;
                    }                
                } 
            }
        }
        return bridgeWord;
    }
    
    // TODO toString()
    
}

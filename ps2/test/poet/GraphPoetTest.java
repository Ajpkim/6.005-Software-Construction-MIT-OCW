/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package poet;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
    
    // Testing strategy given input string...
    //      - no bridge words
    //      - single bridge word
    //      - multiple bridge words all with single weight
    //      - multiple bridge words with different weights (make sure highest weight implemented)
    
                     
    String testInput = "My favorite color is blue, it is so pretty.";
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    @Test
    public void testPoetNoBridgeWords() throws IOException {
        File corpusText = new File("test/poet/TestNoBridgeWords.txt");
        GraphPoet graph = new GraphPoet(corpusText);
        String poem = graph.poem(testInput);
        
        assertEquals(testInput, poem);
        }
    
    @Test public void testPoetSingeBridgeWord() throws IOException {
        File corpusText = new File("test/poet/TestSingleBridgeWord.txt");
        GraphPoet graph = new GraphPoet(corpusText);
        String poem = graph.poem(testInput);
        
        String expected = "My favorite color is deep blue, it is so pretty.";
        
        assertEquals(expected, poem);
    }
    
    
    @Test
    public void testGraphPoetMultipleBridgeWordsSingleWeight() throws IOException {
        File corpusText = new File("test/poet/TestMultipleBridgeWordsSameSingleWeights.txt");
        GraphPoet graph = new GraphPoet(corpusText);
        String poem = graph.poem(testInput);
        String expected = "My absolute favorite color is blue, it constantly is so amazingly pretty.";
        
        assertEquals(expected, poem);
        }
    
    
    @Test
    public void testGraphPoetMultipleBridgeWordsCompetingWeights() throws IOException {
        File corpusText = new File("test/poet/TestMultipleBridgeWordsCompetingWeights.txt");
        GraphPoet graph = new GraphPoet(corpusText);
        String poem = graph.poem(testInput);
        String expected = "My absolute favorite color is blue, it constantly is so tantalizingly pretty.";
        
        assertEquals(expected, poem);
        }
    
    
    
}

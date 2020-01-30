/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class FilterTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     * 
     * writtenBy:
     * Partition input list into the following conditions:
     * a) empty list
     * b) list with single author match 
     * c) multiple tweets by single author in same order
     * d) confirm case insensitivity
     * 
     * 
     * inTimespan:
     * Partition input list into following conditions:
     * a) empty list
     * b) list with no tweets in timespan
     * c) list with multiple elements and a single tweet in timespan
     * d) list with multiple tweets in timespan and correct order
     * 
     * containing:
     * Partition input list into following conditions:
     * - empty list
     * - multiple tweets with no matching words
     * - multiple tweets with single matching word
     * - multiple tweets with multiple matches (ordered correctly)
     * 
     * 
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T11:01:00Z");
    private static final Instant d4 = Instant.parse("2016-03-17T11:00:00Z");
    private static final Instant d5 = Instant.parse("2017-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "alex", "Live free or die. talk @NHGov_1776", d3);
    private static final Tweet tweet4 = new Tweet(4, "alex", "Andrew Yang for prez! yes@ayang 3@ayang ayang@gmail.com", d4);
    private static final Tweet tweet5 = new Tweet(5, "James", "Stay hydrated with @gatorade for top performance", d5);
    private static final Tweet tweet6 = new Tweet(5, "james", "@ai-3 Got milk?", d5);
    private static final Tweet tweet7 = new Tweet(5, "jamesss", "hello @ai-3 you are the @answer for all @bball questions", d5);

    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // TESTING WRITTENBY
    
    @Test   // covers emptyList condition
    public void testWrittenByEmptyList() {
        List<Tweet> emptyList = Arrays.asList();        
        List<Tweet> tweets = Filter.writtenBy(emptyList, "username");
        
        assertEquals("expected empty list", emptyList, tweets);
    }
    
    @Test   // covers singleton list
    public void testWrittenByMultipleTweetsSingleResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "alyssa");
        
        assertEquals("expected singleton list", 1, writtenBy.size());
        assertTrue("expected list to contain tweet", writtenBy.contains(tweet1));
    }
    
    @Test // covers multiple tweets by single author
    public void testWrittenByMultipleTweetsSingleAuthor() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2, tweet3, tweet4), "alex");
        
        assertEquals("expected list with 2 elements", 2, writtenBy.size());
        assertEquals("expected same order", 0, writtenBy.indexOf(tweet3));
    }
    
    @Test // covers case insensitivity
    public void testWrittenByCaseInsensitivity() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet7, tweet6, tweet5), "jaMes");
        assertEquals("expected list with 2 elements", 2, writtenBy.size());
    }
    
    // TESTING INTIMESPAN
    
    
    @Test // covers empty list
    public void testInTimespanEmptyList() {
        Instant testStart = Instant.parse("2016-02-17T09:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T12:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(), new Timespan(testStart, testEnd));
        assertEquals("expected empty list", 0, inTimespan.size());
    }
    
    @Test   // covers list with no tweets in timespan
    public void testInTimespanNoMatches() {
        Instant testStart = Instant.parse("2010-02-17T10:00:00Z");
        Instant testEnd = Instant.parse("2010-02-17T12:00:00Z");
       
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2),
                                               new Timespan(testStart, testEnd));
        assertEquals("expected empty list", 0, inTimespan.size());
    }
    
    @Test   // covers list with multiple tweets and single tweet in timespan
    public void testInTimespanSingleTweet() {
        Instant testStart = Instant.parse("2015-02-17T19:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T10:00:01Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2, tweet3), 
                                                       new Timespan(testStart, testEnd));
        
        assertEquals("expected list with single element", 1, inTimespan.size());
    }
    
    @Test   // covers list with multiple tweets and multiple tweets in timespan
    public void testInTimespanMultipleTweetsMultipleResults() {
        Instant testStart = Instant.parse("2016-02-17T09:00:00Z");
        Instant testEnd = Instant.parse("2016-02-17T12:00:00Z");
        
        List<Tweet> inTimespan = Filter.inTimespan(Arrays.asList(tweet1, tweet2), new Timespan(testStart, testEnd));
        
        assertFalse("expected non-empty list", inTimespan.isEmpty());
        assertTrue("expected list to contain tweets", inTimespan.containsAll(Arrays.asList(tweet1, tweet2)));
        assertEquals("expected same order", 0, inTimespan.indexOf(tweet1));
    }
    
    // TESTING CONTAINING
    
    @Test   // covers empty list
    public void testContainingEmptyList() {
        List <Tweet> containing = Filter.containing(Arrays.asList(), Arrays.asList("Hello"));
        
        assertTrue("expected empty list", containing.isEmpty());
    }
    
    @Test   // covers list with no matching words
    public void testContainingNoMatches() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2, tweet3), Arrays.asList("BBAll"));
        
        assertTrue("expected empty list", containing.isEmpty());
    }
    
    @Test   // covers multiple tweets with single match
    public void testContainingSingleMatch() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2, tweet3), Arrays.asList("minutes"));
        
        assertEquals("expected singleton list", 1, containing.size());
    }
    
    @Test   // covers multiple word matches
    public void testContaining() {
        List<Tweet> containing = Filter.containing(Arrays.asList(tweet1, tweet2, tweet3), Arrays.asList("talk"));
        
        assertFalse("expected non-empty list", containing.isEmpty());
        assertTrue("expected list to contain tweets", containing.containsAll(Arrays.asList(tweet1, tweet2, tweet3)));
        assertEquals("expected same order", 2, containing.indexOf(tweet3));
    }

    /*
     * Warning: all the tests you write here must be runnable against any Filter
     * class that follows the spec. It will be run against several staff
     * implementations of Filter, which will be done by overwriting
     * (temporarily) your version of Filter with the staff's version.
     * DO NOT strengthen the spec of Filter or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Filter, because that means you're testing a stronger
     * spec than Filter says. If you need such helper methods, define them in a
     * different class. If you only need them in this test class, then keep them
     * in this test class.
     */

}

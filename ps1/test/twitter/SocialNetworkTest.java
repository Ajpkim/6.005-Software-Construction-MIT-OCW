/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class SocialNetworkTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     * 
     * guessFollowsGraph:
     * Partition tests into the following conditions:
     * - empty graph
     * - empty set for single key
     * - Single author with multiple follows
     * - multiple authors with varying mentions each (1 - 4)
     * 
     * influencers:
     * Partition tests into the following conditions:
     * - empty set
     * - single influencer
     * - multiple influencers 
     * -  
     */
    
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T11:01:00Z");
    private static final Instant d4 = Instant.parse("2016-03-17T11:00:00Z");
    private static final Instant d5 = Instant.parse("2017-02-17T11:00:00Z");
    
    private static final Tweet tweet1 = new Tweet(1, "Alex", "@JHarden ball is life?", d1);
    private static final Tweet tweet2 = new Tweet(2, "alex", "ROY is @JAMorant", d2);
    private static final Tweet tweet3 = new Tweet(3, "alEX", "@jamorant or @Zion ?", d3);
    private static final Tweet tweet4 = new Tweet(4, "alex", "@MJ or @LBJ", d4);
    private static final Tweet tweet5 = new Tweet(5, "James", "yoooooo", d5);
    private static final Tweet tweet6 = new Tweet(5, "james", "nba reddit or nba twitter?", d5);
    private static final Tweet tweet7 = new Tweet(5, "jamES", "GLeague is where it's happening.", d5);
    private static final Tweet tweet8 = new Tweet(5, "rover", "@jamorant is a beast", d5);
    private static final Tweet tweet9 = new Tweet(5, "ROVER", "can't stop @jharden", d5);
    private static final Tweet tweet10 = new Tweet(5, "Mike", "@jharden for MVP", d5);
    private static final Tweet tweet11 = new Tweet(5, "Mikee", "@jHarden @vince for MVP", d5);
    private static final Tweet tweet12 = new Tweet(5, "Mikeee", "@jhaRDen @vince @pg MVP", d5);
    private static final Tweet tweet13 = new Tweet(5, "Mikeeee", "@jharden @vInce @pg @ luka for MVP", d5);
    private static final Tweet tweet14 = new Tweet(5, "Mikeeeeee", "@jharden @VInce @pg @luka @Dirk for MVP", d5);


    
    
    
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test   // covers empty graph
    public void testGuessFollowsGraphEmpty() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(new ArrayList<>());
        
        assertTrue("expected empty graph", followsGraph.isEmpty());
    }
    
    @Test   // covers empty set with single author
    public void testGuessFollowsGraphEmptySet() {
        List<Tweet> tweets = new ArrayList<Tweet>(Arrays.asList(tweet5, tweet6, tweet7));
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        
        assertTrue("expected single key, [james]", followsGraph.containsKey("james"));
        assertEquals("expected empty set for james", 0, followsGraph.get("james").size());
    }
    
    
    @Test  // covers single author with multiple follows
    public void testGuessFollowsGraphSingleAuthor() {
        List<Tweet> tweets = new ArrayList<Tweet>(Arrays.asList(tweet1, tweet2, tweet3, tweet4));
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        
        assertEquals("Expected single key", 1, followsGraph.keySet().size());
        assertEquals("expected 6 follows", 6, followsGraph.get("alex").size());
        
    }
    
    
    @Test   // covers multiple authors who make multiple mentions
    public void testGuessFollowsGraphTwoAuthors() {
        List<Tweet> tweets = new ArrayList<Tweet>(Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet8, tweet9));
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(tweets);
        
        assertEquals("expected two keys", 2, followsGraph.keySet().size());
        assertEquals("expected alex to have 6 follows", 6, followsGraph.get("alex").size());
        assertEquals("expected rover to have 2 follows", 2, followsGraph.get("rover").size());
    }
    
    
    
    @Test  // covers empty list
    public void testInfluencersEmpty() {
        Map<String, Set<String>> followsGraph = new HashMap<>();
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected empty list", influencers.isEmpty());
    }
    
    @Test // covers singleton list
    public void testInfluencersSingleton() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertTrue("expected singleton list", influencers.size() == 1);
        assertEquals("expected single key to be @jharden", "jharden", influencers.get(0));
    }

    @Test  // covers multiple influencers with same followers
    public void testInfluencersMultiple() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet1, tweet2, tweet3, tweet8, tweet9, tweet10));        
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertEquals("expected list of 3 elements", 3, influencers.size());
        assertEquals("expected @jharden to be index 0", "jharden", influencers.get(0));
        assertEquals("expected @jamorant to be index 1", "jamorant", influencers.get(1));
        assertEquals("expected @jamorant to be index 2", "zion", influencers.get(2));       
    }
    
    @Test   // covers multiple influencers with diff follower counts
    public void testInfluencersMultipleVaryingFollowers() {
        Map<String, Set<String>> followsGraph = SocialNetwork.guessFollowsGraph(Arrays.asList(tweet10, tweet11, tweet12,tweet13,tweet14));
        List<String> influencers = SocialNetwork.influencers(followsGraph);
        
        assertEquals("expected jharden to be [0]", "jharden", influencers.get(0));
        assertEquals("expected vince to be [1]", "vince", influencers.get(1));
        assertEquals("expected pg to be [2]", "pg", influencers.get(2));
        
    }
    
    
    /*
     * Warning: all the tests you write here must be runnable against any
     * SocialNetwork class that follows the spec. It will be run against several
     * staff implementations of SocialNetwork, which will be done by overwriting
     * (temporarily) your version of SocialNetwork with the staff's version.
     * DO NOT strengthen the spec of SocialNetwork or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in SocialNetwork, because that means you're testing a
     * stronger spec than SocialNetwork says. If you need such helper methods,
     * define them in a different class. If you only need them in this test
     * class, then keep them in this test class.
     */

}

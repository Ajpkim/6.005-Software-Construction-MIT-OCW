/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.Test;

public class ExtractTest {

    /*
     * TODO: your testing strategies for these methods should go here.
     * See the ic03-testing exercise for examples of what a testing strategy comment looks like.
     * Make sure you have partitions.
     * 
     * Testing Strategy
     * 
     * getTimeSpan:   
     * Partition input list of tweets for getTimeSpan into 4 cases:
     * a) list of two tweets with different time
     * b) list of two tweets with same time
     * c) shuffled list of 5 tweets 
     * d) empty list
     * e) list with 1 tweet
     * 
     * getMentiondedUsers: 
     * Partition input lists into following cases:
     * a) empty list
     * b) tweet list with no mentions
     * c) tweet list with mention at beginning, end, and in middle of tweets
     * d) tweet with several invalid usernames
     * e) multiple users in single tweet
     * f) single user in multiple tweets
     * 
     */
    
    private static final Instant d1 = Instant.parse("2016-02-17T10:00:00Z");
    private static final Instant d2 = Instant.parse("2016-02-17T11:00:00Z");
    private static final Instant d3 = Instant.parse("2016-02-17T11:01:00Z");
    private static final Instant d4 = Instant.parse("2016-03-17T11:00:00Z");
    private static final Instant d5 = Instant.parse("2017-02-17T11:00:00Z");
    
    
    private static final Tweet tweet1 = new Tweet(1, "alyssa", "is it reasonable to talk about rivest so much?", d1);
    private static final Tweet tweet2 = new Tweet(2, "bbitdiddle", "rivest talk in 30 minutes #hype", d2);
    private static final Tweet tweet3 = new Tweet(3, "tester1", "Live free or die @NHGov_1776", d3);
    private static final Tweet tweet4 = new Tweet(4, "tester2", "Andrew Yang for prez! yes@ayang 3@ayang ayang@gmail.com", d4);
    private static final Tweet tweet5 = new Tweet(5, "tester3", "Stay hydrated with @gatorade for top performance", d5);
    private static final Tweet tweet6 = new Tweet(5, "tester3", "@ai-3 Got milk?", d5);
    private static final Tweet tweet7 = new Tweet(5, "tester4", "hello @ai-3 you are the @answer for all @bball questions", d5);

    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    
    // covers list of 2 tweets with different timestamps
    @Test
    public void testGetTimespanTwoTweets() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet1, tweet2));
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d2, timespan.getEnd());
        
    }
    
    // covers two tweets from same time
    @Test
    public void testGetTimespanTwoTweetsSameTime() {
        Timespan timespan = Extract.getTimespan(Arrays.asList(tweet5, tweet6));
        
        assertEquals("expected start", d5, timespan.getStart());
        assertEquals("expected end", d5, timespan.getEnd());
    }
    
    // covers list of 5 tweets that are shuffled 
    @Test 
    public void testGetTimespaceFiveTweets() {
        List<Tweet> tweetlist = Arrays.asList(tweet1, tweet2, tweet3, 
                                             tweet4, tweet5);
        Collections.shuffle(tweetlist);
        Timespan timespan = Extract.getTimespan(tweetlist);
        
        assertEquals("expected start", d1, timespan.getStart());
        assertEquals("expected end", d5, timespan.getEnd());
    }
    
    // covers empty list
    @Test
    public void testGetTimestampEmptyList() {
        List<Tweet> emptyList = Arrays.asList();
        Timespan timespan = Extract.getTimespan(emptyList);
        
        assertNotNull("Shouldn't be null", timespan);
        Instant start = timespan.getStart();
        Instant end = timespan.getEnd();
        assertEquals("expected start time and end timestamps to be the same", end, start);    
    }
    
    // covers list with 1 tweet
    @Test
    public void testGetTimespanSingleTweet() {
        List<Tweet> singleTweet = Arrays.asList(tweet1);
        Timespan timespan = Extract.getTimespan(singleTweet);
        
        Instant start = timespan.getStart();
        Instant end = timespan.getEnd();
        
        assertEquals("expected start time and end time to be the same", start, end);
    }
    
    
    
    // getMentionedUsers:
    
    // covers empty list
    @Test
    public void testGetMentionedUsersEmpty() {
        List<Tweet> emptyList = Arrays.asList();
        Set<String> mentionedUsers = Extract.getMentionedUsers(emptyList);
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    // covers list with no mentions
    @Test
    public void testGetMentionedUsersNoMention() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2));
        
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }

    // covers tweet list with mention at beginning, end, and in middle
    @Test
    public void testGetMentionedUsersWithDiffMentions() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet3, tweet5, tweet6));
        
        assertTrue("expected set with 3 elements", (mentionedUsers.size() == 3));
    }
    
    // covers tweet list with no valid mentions and several invalid mentions
    @Test
    public void testGetMentionedUsersEmail() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet1, tweet2, tweet4));
        assertTrue("expected empty set", mentionedUsers.isEmpty());
    }
    
    // covers single tweet with multiple mentioned users
    @Test
    public void testGetMentionedUsersMultipleInTweet() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet7));
        assertTrue("expected set with 3 elements", mentionedUsers.size() == 3);
    }

    // covers list with multiple mentions of same username
    @Test
    public void testGetMentionedUsersSameUserMulitpleMentions() {
        Set<String> mentionedUsers = Extract.getMentionedUsers(Arrays.asList(tweet6, tweet7));
        assertTrue("expected set with 3 elements", mentionedUsers.size() == 3);
    }
    
    
    /*
     * Warning: all the tests you write here must be runnable against any
     * Extract class that follows the spec. It will be run against several staff
     * implementations of Extract, which will be done by overwriting
     * (temporarily) your version of Extract with the staff's version.
     * DO NOT strengthen the spec of Extract or its methods.
     * 
     * In particular, your test cases must not call helper methods of your own
     * that you have put in Extract, because that means you're testing a
     * stronger spec than Extract says. If you need such helper methods, define
     * them in a different class. If you only need them in this test class, then
     * keep them in this test class.
     */

}

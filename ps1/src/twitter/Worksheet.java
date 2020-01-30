package twitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

public class Worksheet {
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


    
    public static void testWrittenByMultipleTweetsSingleResult() {
        List<Tweet> writtenBy = Filter.writtenBy(Arrays.asList(tweet1, tweet2), "alyssa");
        
        
        System.out.println(writtenBy.size());
        System.out.println(tweet1.getAuthor());
//        System.out.println(writtenBy);
//        System.out.println("hello");
//        assertEquals("expected singleton list", 1, writtenBy.size());
//        assertTrue("expected list to contain tweet", writtenBy.contains(tweet1));
    }
    public static void main(String[] args) {
//        List<String> emptyList = Arrays.asList();
//        System.out.println(emptyList.size());
        
        testWrittenByMultipleTweetsSingleResult();
        
               
        
//        \b@[a-zA-Z0-9_-]+\b
    }
    
}



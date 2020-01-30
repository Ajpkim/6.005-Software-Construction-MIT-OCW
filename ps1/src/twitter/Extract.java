/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     *         every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        
        // for empty list return Timespan with 0 duration
        if (tweets.isEmpty()) {
            return new Timespan(Instant.parse("2016-02-17T10:00:00Z"),
                                Instant.parse("2016-02-17T10:00:00Z"));
        }
        // Finding the earliest and latest tweetTimeStamps in list
        Instant start = Instant.MAX;
        Instant end = Instant.MIN;
        
        for (Tweet tweet : tweets) {
            Instant tweetTimeStamp = tweet.getTimestamp();
            if (start.isAfter(tweetTimeStamp)){
                start = tweetTimeStamp;
            }
            if (end.isBefore(tweetTimeStamp)) {
                end = tweetTimeStamp;               
            }
        }
        return new Timespan(start, end);
    }

    /**
     * Get usernames mentioned in a list of tweets.
     * 
     * @param tweets
     *            list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     *         A username-mention is "@" followed by a Twitter username (as
     *         defined by Tweet.getAuthor()'s spec).
     *         The username-mention cannot be immediately preceded or followed by any
     *         character valid in a Twitter username.
     *         For this reason, an email address like bitdiddle@mit.edu does NOT 
     *         contain a mention of the username mit.
     *         Twitter usernames are case-insensitive, and the returned set may
     *         include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> mentionedUsers = new HashSet<>();
        
        for (Tweet tweet : tweets) {   
            Set<String> tweetMentions = getMentionedUsersFromText(tweet.getText());
            mentionedUsers.addAll(tweetMentions);
            }
        return mentionedUsers;  
        }
    
    /*
     *  get all the users mentioned in a tweet
     *  
     * @param String (text from tweet)
     *              
     * @return Set of all the valid user mentions appearing in text
     */
    private static Set<String> getMentionedUsersFromText(String text) {
        Set<String> tweetMentions = new HashSet<>();
        
        for (int i = 0; i < text.length(); i++) {
            
            // begin search after finding '@'
            if (text.charAt(i) == '@') {

                // check if anything invalid is preceding '@' 
                if (!(i==0)) {            
                    char previousChar = text.charAt(i - 1);
                    if (!(previousChar == ' ')) {
                        break;
                    }
                }

                String possibleMention = "";
                int j = i + 1; // start possibleMention AFTER the '@' sign
                // construct the possible mention then check username validity

                while ((!(text.charAt(j) == ' ')))  {
                possibleMention = possibleMention + text.charAt(j);
                    ++j;
                    if (j >= text.length()) {  // Having trouble adding this clause to while loop??
                        break;
                    }
                }   
                // add valid mentions to set
                if (checkUsernameValidity(possibleMention)) {
                    tweetMentions.add(possibleMention);
                } 
            }
        }
        return tweetMentions;
    }

    /*
     * returns true if input is valid twitter username, false otherwise
     * 
     * @param string without whitespace
     * @return boolean
     * 
     */
    private static boolean checkUsernameValidity(String possibleMention) {
        
        String regex = "[a-zA-Z0-9_-]+";
        
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(possibleMention);
        
        return matcher.matches();
    }

}

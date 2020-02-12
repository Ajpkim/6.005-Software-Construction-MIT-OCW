/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package twitter;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.Comparator;

/**
 * SocialNetwork provides methods that operate on a social network.
 * 
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * 
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     * 
     * @param tweets
     *            a list of tweets providing the evidence, not modified by this
     *            method.
     * @return a social network (as defined above) in which Ernie follows Bert
     *         if and only if there is evidence for it in the given list of
     *         tweets.
     *         One kind of evidence that Ernie follows Bert is if Ernie
     *         @-mentions Bert in a tweet. This must be implemented. Other kinds
     *         of evidence may be used at the implementor's discretion.
     *         All the Twitter usernames in the returned social network must be
     *         either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
        
        Map<String, Set<String>> followsGraph = new HashMap<String, Set<String>>();
        Set<String> authors = new HashSet<String>();
        
        for (Tweet tweet : tweets) {            
            authors.add(tweet.getAuthor().toLowerCase());
        }
        
        for (String author : authors) {
            Set<String> mentions = new HashSet<String>();
            mentions.addAll(Extract.getMentionedUsers(Filter.writtenBy(tweets, author)));
            
            // additional evidence of influence (problem 4)
            // if A follows B, and B mentions C, then A is influenced by C
            // returns influencer graph
            
            Set<String> influencers = new HashSet<String>();
            influencers.addAll(mentions);
            
            for (String influencer : mentions) {
                influencers.addAll(Extract.getMentionedUsers(Filter.writtenBy(tweets, influencer)));
            }
            
            followsGraph.put(author, influencers);
        }
        return followsGraph;
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     * 
     * @param followsGraph
     *            a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     *         descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        
        /* 
         * - create new map that maps username to number of followers
         * - for each key in followsGraph, add all the new influencers to map and then increment once 
         * - return keys of influencers map in descending order of follower count
         */
        Map<String, Integer> influencersGraph = new HashMap<String, Integer>();

        for (Set<String> mentions : followsGraph.values()) {
            for (String influencer : mentions) {
                String influencerLower = influencer.toLowerCase();
                if (!influencersGraph.containsKey(influencerLower)){
                    influencersGraph.put(influencerLower, 1);
                } else {
                    influencersGraph.replace(influencerLower, (influencersGraph.get(influencerLower) + 1));
                }
            }
        }
        
        List<Entry<String, Integer>> entriesList = new ArrayList<>();
        entriesList.addAll(influencersGraph.entrySet());
        
        Collections.sort(entriesList, (o1, o2) -> {
            return o1.getValue() < o2.getValue() ? 1:-1;
        });
        
        List<String> influencers = new ArrayList<>();
        for (Entry<String, Integer> entry : entriesList) {
            influencers.add(entry.getKey());
        }
        
        return influencers;
    }
}

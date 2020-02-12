package twitter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

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
        
//        testWrittenByMultipleTweetsSingleResult();
        
//        Set<String> names = new HashSet<>(Arrays.asList("James", "Alex"));
////        if (names.contains("James")) {
////            System.out.println("name contained");
////        }
//        
////        Map<String, Set<String>> titles = new HashMap<String, Set<String>>("AK", names);
//        Map<String, Set<String>> titles = new HashMap<String, Set<String>>();
//        titles.put("AK", names);
//        Set<String> initials = new HashSet<String>();
//        initials.add("AK");
//        
//        System.out.println(titles);
//        System.out.println(initials);
//        System.out.println("titles keyset =" + titles.keySet());
//        System.out.println("---------");
////        System.out.println((titles.keySet() == initials) ? "contained" : "not contained");
////        System.out.println((titles.keySet().contains("AK")) ? "contained" : "not contained");        
////        System.out.println((2+3==5) ? "contained" : "not contained");
//        System.out.println(titles.get("AK"));
        
//        Map<Character, Set<Integer>> testMap = new HashMap<Character, Set<Integer>>();
//        String letters = "abcdefg";
//        int i = 0;
//        for(int l = 0; l < letters.length(); l++) {
//            Set<Integer> num = new HashSet<Integer>();
//            num.add(i++);
//            testMap.put(letters.charAt(l), num);
//        }
////        System.out.println(testMap);
//        Set<Integer> newNum = new HashSet<Integer>();
//        newNum.add(33);
//        testMap.replace('c', newNum);
////        for(Set<Integer> nums : testMap.values()) {
////            System.out.println(nums);
////        }
//        
////        System.out.println(testMap);
////        System.out.println("-------");
//        System.out.println(testMap.entrySet());
//        System.out.println("-------");
////        for (Map.Entry e : testMap.entrySet()) {
////            System.out.println(e.getValue());
////        }
////        
//        System.out.println(testMap.values());
//        System.out.println("-------");
//        System.out.println("");
//        System.out.println();
//        
        
//        testMap.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(System.out::println);
        
        
        
        
       
        
        
//        // testing Collections.sort method
//        List<Integer> nums = new ArrayList<>();
//        for (int i = 10; i>0; i--) {
//            nums.add(i);
//        }
//        System.out.println(nums);
//        
//        System.out.println("after sorting:");
//        Collections.sort(nums);
//        System.out.println(nums);

        
       
        
        
//////////////////        COMPARATORS           ////////////////
//
//         List<Integer> nums = new ArrayList<>();
//        for (int i = 0; i<10; i++) {
//            nums.add((int)(Math.random() * 1000));
//        }
//        System.out.println("original numbers: " + nums);
//        // using SEPARATE CLASS FILE
//        Collections.sort(nums, new ComparatorPractice());  // creating comparator object from ComparatorPractice class file
//        System.out.println("sorted numbers using comp: " + nums);
//        
//        //// using an ANONYMOUS CLASS
//        Comparator<Integer> anonComp = new Comparator<Integer>(){
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                if (o1%10 > o2%10) {
//                    return 1;
//                } if (o1%10 == o2%10) {
//                    return 0;
//                } return -1;
//            }
//        };
//        Collections.sort(nums, anonComp);
//        System.out.println("sorted numbers using comp: " + nums);
//        
//        // using LAMBDA EXPRESSION
//        Comparator<Integer> lambdaComp = (o1, o2) -> {
//            if (o1 > o2) {
//                return 1;
//            } if (o1 == o2) {
//                return 0;
//            } return -1;
//        };
//        Collections.sort(nums, lambdaComp);
//        System.out.println("sorted numbers ascending: " + nums); 
//        
//        
//        // SUPER CONCISE lambda expression
//        Comparator<Integer> conciseComp = (o1, o2) -> {
//            return o1>o2?1:-1;
//        };
//        Collections.sort(nums, conciseComp);
//        System.out.println("sorted numbers ascending: " + nums); 
//        
//        
//        // ONE LINER
//        Collections.sort(nums,  (o1, o2) -> {
//            return o1>o2?1:-1;
//        });
//        System.out.println("sorted numbers ascending: " + nums);
        
        ////////////////////////////////////////////////////
        
        
//         Creating testMap
      Map<Character, Integer> testMap = new HashMap<Character, Integer>();
      String letters = "abcdef";
      for (int i = 0; i<letters.length(); i++) {
          testMap.put(letters.charAt(i), ((int)(Math.random() * 100)));
      }
//      System.out.println("original map: "+ testMap);
//      System.out.println("------");        
//      System.out.println("");
        
      List<Entry<Character, Integer>> entryList = new ArrayList<>();
      entryList.addAll(testMap.entrySet());
      
      
      //// extracting different info from Entry ... reveals I just need to sort the entry set
//      System.out.println(entryList);
//      System.out.println(entryList.get(0));
//      System.out.println(entryList.get(0).getKey());
//      System.out.println(entryList.get(0).getValue());
      ////
      
      
//      //// SUCCESS WITH ANON COMPARATOR FOR MAP.ENTRY
//      Comparator<Map.Entry<Character, Integer>> entryComp = new Comparator<Map.Entry<Character, Integer>>() {
//
//        @Override
//        public int compare(Entry<Character, Integer> o1, Entry<Character, Integer> o2) {
//            if (o1.getValue() > o2.getValue()) {
//                return 1;
//            } return -1;          
//        }
//      };
//      System.out.println("Original order: " + entryList);
//      
//      Collections.sort(entryList, entryComp);
//      System.out.println("sorted order: " + entryList);
//        
      
//      //// LAMBDA EXPRESSION COMP FOR MAP.ENTRY
    System.out.println("Original order: " + entryList);
      
      Collections.sort(entryList, (o1, o2) -> {
          return o1.getValue()>o2.getValue()?1:-1;
      });
      
      System.out.println(entryList);      

////////////////////////    SUCCESS!      /////////////////////
        
        
        
        
        // http://www.java2novice.com/java-interview-programs/sort-a-map-by-value/
//        Set<Entry<Character, Integer>> set = testMap.entrySet();
//        List<Entry<Character, Integer>> list = new ArrayList<Entry<Character, Integer>>(set);
//        Collections.sort( list, new Comparator<Map.Entry<String, Integer>>()
//        {
//            public int compare( Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2 )
//            {
//                return (o2.getValue()).compareTo( o1.getValue() );
//            }
//        } );
//        for(Map.Entry<Character, Integer> entry:list){
//            System.out.println(entry.getKey()+" ==== "+entry.getValue());
//        }
    }
}
    
      
            
    
        
        
        
        
        
//        from SO...
//        LinkedHashMap<Integer, String> sortedMap = 
//                map.entrySet().stream().
//                sorted(Entry.comparingByValue()).
//                collect(Collectors.toMap(Entry::getKey, Entry::getValue,
//                                         (e1, e2) -> e1, LinkedHashMap::new));
        

        
//        \b@[a-zA-Z0-9_-]+\b

    



package graph;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.*;

public class Worksheet {
    
    public static void main(String[] args) {        
        

            Graph<String> graph = new ConcreteVerticesGraph();
            
            assertEquals(0, graph.set("v1", "v2", 0));
            assertEquals(0, graph.targets("v1").size());
            assertEquals(Collections.emptySet(), graph.targets("v1").keySet());  // also works
        
        
        
        
//        Graph<String> graph = new ConcreteVerticesGraph();
//        graph.add("v1");
//        graph.set("v1", "v2", 10);
//        graph.set("v1", "v3", 11);
//        graph.set("v1", "v4", 12);
//        graph.set("v1", "v5", 13);
//        
//        System.out.println("---");
//        System.out.println(graph.sources("v3"));
//        System.out.println("---");
//        
//        
//        graph.remove("v1");
//        System.out.println("---");
//
//        System.out.println(graph.sources("v3"));        
//        System.out.println("---");
        
        
//        
//        System.out.println("current vertices: " + graph.vertices());
//        System.out.println("current v2 sources:" + graph.\sources("v2").keySet());
//        
//        
//        System.out.println("-----");
//        
//        System.out.println("removing non-
//        existent vertex... " + graph.remove("fakeVertex"));
//        System.out.println("-----");
//        
//        System.out.println("removing v1 vertex... " + graph.remove("v1"));
//        System.out.println("-----");
//        
//        System.out.println("updated vertices: " + graph.vertices());
//        System.out.println("updated v2 sources:" + graph.sources("v2").keySet());        
//        

        ///
        
//        assertTrue(graph.remove("v1"));
//        assertEquals(0, graph.vertices().size());
//        
        
        
        
        //
//      List<Integer> testList = new ArrayList<>();
//      for(int i = 0; i<5; i++) {
//          testList.add(i);
//      }
//      
//      
//      System.out.println(testList);
//      testList.remove(3);
//      System.out.println(testList);
//        
        
        
        
        
        
        
        
//        
//        Set<String> s = new HashSet<>();
//        System.out.println(s.equals(Collections.emptySet()));  // WORKS
//        System.out.println(s == Collections.emptySet());  // DOESNT WORK
        
//        
//        List<String> c = Arrays.asList("a", "b", "c", "d", "e");
//        List<Integer> n = Arrays.asList(0, 1, 2, 3, 4);
//        Map<String, Integer> testMap = new HashMap<>();
////        
//        for (int i = 0; i < 5; i++) {
//            testMap.put(c.get(i), n.get(i));
//        }
//        
//        Set<String> letterSet = new HashSet<>(testMap.keySet());
//        
//        System.out.println(letterSet);
//        System.out.println("-----");
//        
//        letterSet.add("ZZZ");
//        
//        System.out.println("testMap keys: " + testMap.keySet());
//        System.out.println("letterSet: " + letterSet);
        
        
        
        
        
        
        
        
//        System.out.println(testMap);
//        System.out.println("---");
//        
//        String testMapString = testMap.toString();
//        System.out.println(testMapString);
        
        
//        String s = "a, b, c,";
//        s = s.replaceAll(",$", "");
//        System.out.println(s);
        
        
        
//        String t = "Vertices: \nv1";
//        System.out.println(t);
        
        
//        String s = "abc";
//        
//        s = s + "\n" + "def";
//        System.out.println(s);
//        
//        final int a = 3;
//        List<Integer> nums = Arrays.asList(1,2,3);
//        final List<Integer> numList = new ArrayList<>(nums);
//        
//        System.out.println(numList);
//        
//        numList.add(4);
//        
//        System.out.println(numList);
        
//        a += 1;
               
        
        
//        int a = 5;
//        
//        assert a > 10;
//        
//        System.out.println("Done");
        
//        System.out.println(checkingNumChange);
//        System.out.println("-----");
//        
//        int a = checkingNumChange;
//        
//        a += 3;
//        
//        System.out.println(a);
//        System.out.println("-----");
//        
//        System.out.println(checkingNumChange);
//        
//        System.out.println("-----");
//        System.out.println("-----");
//        
        
//        Collection<Integer> testNums = new HashSet<>();
//        testNums.add(3);
//        System.out.println(testNums);
//        
//        System.out.println((int)testNums.toArray()[0] == 3);
//        
//        Map<String, Integer> testMap = new HashMap<>();
//        System.out.println(Collections.emptySet() == testMap.keySet());
//        
//        System.out.println(testMap.keySet().size() == 0);
//        
//        
//
//        Map<String, Integer> testMap = new HashMap<>();
//        
//        System.out.println(testMap == Collections.EMPTY_MAP);
//      
        
        
//        List<String> sources = Arrays.asList("v1", "v3","v4", "v5");
//        System.out.println(sources);
//        
        
//        assertEquals(testMap.keySet() == Collections.emptySet());
        
        
//        testMap.put("v1", 10);
//        int value = (int)testMap.values().toArray()[0];
//        System.out.println(value);
//        
        
//        final int num = 3;
//        
//        System.out.println(num);
//        
//        int a = num;
//        
//        System.out.println(a);
//        
//        a += 1;
//        System.out.println(a);
//        System.out.println("----");
//        System.out.println(num);
//        
//        int b = a + 3;
//        
//        System.out.println(b);
//        System.out.println(num);
        
        
    }

}

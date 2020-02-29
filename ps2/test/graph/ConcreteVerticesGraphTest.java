/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    /*
     * Testing ConcreteVerticesGraph...
     */
    
    // Testing strategy for ConcreteVerticesGraph.toString()
    //   - empty graph, graph with 1 vertex, graph with multiple vertices and edges
    
    // TODO tests for ConcreteVerticesGraph.toString()
    
    /*
     * Testing Vertex...
     */
    
    // Testing strategy for Vertex:
    //      - no sources or targets
    //      - only sources, only targets
    //      - circular edges (one edge where v1=source, v2=target, another w/ v2=source, v1=target)
    //      - multiple sources and targets
    //
    // Methods to test:
    //      - checkRep()
    //      - getSourcesLabels()
    //      - getTargetsLabels()
    //      - addSource()
    //      - addTarget()
    //      - toString()
    
    
    // Testing checkRep()...
    
    @Test(expected=AssertionError.class)
    public void testCheckRepDuringVertexModifcation() {
        Vertex<String> v1 = new Vertex<String>("v1");
        Vertex<String> v2 = new Vertex<String>("v2");
        
        v1.addTarget("v2", -3); // should trigger assertion
    }
 
    @Test(expected=AssertionError.class)
    public void testCheckRepConstructor() {        
        Map<String, Integer> testMap = new HashMap<>();
        testMap.put("v3", 5);
        testMap.put("v2", -1);
        
        Vertex<String> v1 = new Vertex<String>("v1", testMap, null);  // should trigger assertion   
    }
    
    
    // covers no sources or targets
    @Test
    public void testLonelyVertex() {
        Vertex<String> v1 = new Vertex<String>("v1");
        
        assertEquals(Collections.emptySet(), v1.getSourcesLabels());
    }
    
    // covers vertex with sources only...
    @Test
    public void testVertexSourcesOnly() {
        Vertex<String> v3 = new Vertex<String>("v3");

        v3.addSource("v1", 10);
        v3.addSource("v2", 15);
        
        assertEquals(2, v3.getSourcesLabels().size());
    }
    
    // covers vertex with targets only
    @Test
    public void testVertexTargetsOnly() {
        Vertex<String> v1 = new Vertex<String>("v1");
        v1.addTarget("v2", 10);
        v1.addTarget("v3", 15);
        
        assertEquals(2,  v1.getTargetsLabels().size());
        }
    
    // covers vertex with targets and sources
    @Test
    public void testVertexTargetsAndSources() {
        Vertex<String> v1 = new Vertex<String>("v1");
        v1.addTarget("v2", 10);
        v1.addTarget("v3", 15);
        v1.addSource("v4", 20);
        v1.addSource("v5", 25);
        
        assertEquals(2,  v1.getTargetsLabels().size());
        assertEquals(2,  v1.getSourcesLabels().size());
        }
    
    // covers circular edges
    @Test
    public void testVertexCircularEdges() {
        Vertex<String> v1 = new Vertex<String>("v1");
        v1.addTarget("v2", 10);
        v1.addSource("v2", 20);
        
        assertEquals(v1.getSourcesLabels(), v1.getTargetsLabels());
        assertEquals(10, (int)v1.getTargetsMap().values().toArray()[0]);
        assertEquals(20, (int)v1.getSourcesMap().values().toArray()[0]);
        }
    
    // covers toString()
    @Test
    public void testVertexToString() {
        Vertex<String> v1 = new Vertex<String>("v1");
        v1.addTarget("v2", 10);
        v1.addSource("v3", 20);
        
        String stringRep = "label: v1" + "\n"
                         + "sources: x ---> v1" + "\n"
                         + v1.getSourcesMap().toString() + "\n"
                         + "targets: v1 ---> x" + "\n"
                         + v1.getTargetsMap().toString();
        
        assertEquals(stringRep, v1.toString());
    }
    
    
    
    
    
    
    
    
    
}

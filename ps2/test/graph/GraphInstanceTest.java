/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    /* Testing strategy for each instance method of Graph:
     * 
     * empty():
     * 
     * add():
     *      - empty graph, non-empty graph
     *      - vertices already in graph, new vertices
     * 
     * set():
     *      - existing sources and targets, non-existing sources and targets (add them to graph)
     *      - add new edge, update existing edge, remove existing edge (weight == 0)
     *      - add new edge with 0 weight (does not modify graph)
     * 
     * remove():
     *      - empty graph, non-empty graph
     *      - vertex exists in graph, vertex doesn't exist in graph
     *      - vertex part of single edge, multiple edges, no edges
     * 
     * vertices():
     *      - vertices = 0, 1, n>1
     *       
     * sources():
     *      - vertex with sources = 0, 1, n>1
     *      
     * targets():
     *      - vertex with targets = 0, 1, n>1
     * 
     *    
     */
    
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    @Test
    public void testInitialVerticesEmpty() {
        // TODO you may use, change, or remove this test
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // TODO other tests for instance methods of Graph
    
    
    // testing add()
    
    @Test
    public void testAddEmptyGraphNewVertices() {
        Graph<String> graph = emptyInstance();
        
        assertTrue(graph.add("v1"));
        assertEquals(1, graph.vertices().size());
    }
    
    @Test
    public void testAddNonEmptyGraphNewVertices() {
        Graph<String> graph = emptyInstance();
        graph.add("v1");
        
        assertTrue(graph.add("v2"));
        assertEquals(2, graph.vertices().size());
    }
    
    @Test
    public void testAddExistingVertices() {
        Graph<String> graph = emptyInstance();
        graph.add("v1");
        
        assertFalse(graph.add("v1"));
        assertEquals(1, graph.vertices().size());
    }
    
    
    // Testing set()
    
    @Test
    public void testSetNewEdgeExistingVertices() {
        Graph<String> graph = emptyInstance();
        graph.add("v1");
        graph.add("v2");
        
        assertEquals(0, graph.set("v1", "v2", 10));
        assertEquals(2, graph.vertices().size());
        assertEquals(1, graph.targets("v1").size());
        assertTrue(graph.targets("v1").keySet().contains("v2"));
        assertEquals(10, (int)graph.targets("v1").values().toArray()[0]);
    }
    
    @Test 
    public void testSetNewEdgeNonExistingVertices() {
        Graph<String> graph = emptyInstance();
        
        assertEquals(0, graph.set("v1", "v2", 10));
        assertEquals(2, graph.vertices().size());
        assertEquals(1, graph.targets("v1").size());
        assertTrue(graph.targets("v1").keySet().contains("v2"));
        assertEquals(10, (int)graph.targets("v1").values().toArray()[0]);
    }
    
    @Test
    public void testSetUpdateEdge() {
        Graph<String> graph = emptyInstance();
        
        graph.set("v1", "v2", 10);
        assertEquals(10, graph.set("v1", "v2", 11));     
        assertEquals(2, graph.vertices().size());
        assertEquals(11, (int)graph.targets("v1").values().toArray()[0]);
    }
    
    @Test
    public void testSetRemoveEdge(){
        Graph<String> graph = emptyInstance();

        graph.set("v1", "v2", 10);
        assertEquals(10, graph.set("v1", "v2", 0));
        assertEquals(0, graph.targets("v1").size());
    }
    
    @Test  // does not add vertices
    public void testSetAdd0WeightEdge() {
        Graph<String> graph = emptyInstance();
        
        assertEquals(0, graph.set("v1", "v2", 0));
        assertEquals(0, graph.targets("v1").size());
        assertEquals(Collections.emptySet(), graph.targets("v1").keySet());  // also works
    }
    
    // testing remove()
    
    @Test
    public void testRemoveEmptyGraph() {
        Graph<String> graph = emptyInstance();
        
        assertFalse(graph.remove("v1"));
    }

        @Test
    public void testRemoveExistingVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("v1");
        
        assertTrue(graph.remove("v1"));
        assertEquals(0, graph.vertices().size());
    }
    
    @Test
    public void testRemoveNonExistingVertexNoEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("v1");
        
        assertFalse(graph.remove("v2"));
        assertEquals(1, graph.vertices().size());
    }
    
    @Test
    public void testRemoveVertexSingleEdge() {
        Graph<String> graph = emptyInstance();
        graph.set("v1", "v2", 10);
        
        assertTrue(graph.remove("v2"));
        assertEquals(1, graph.vertices().size());
        assertEquals("expected empty map", 0, graph.targets("v1").size()); 
    }
    
    @Test
    public void testRemoveVertexMultipleEdges() {
        Graph<String> graph = emptyInstance();
        graph.set("v1", "v2", 10);
        graph.set("v1", "v3", 11);
        graph.set("v4", "v1", 12);
        
        assertTrue(graph.remove("v1"));
        assertEquals(0, graph.sources("v2").size());
        assertEquals(0, graph.sources("v3").size());
        assertEquals(0, graph.targets("v4").size());
    }
    
    // testing vertices()
    
    @Test
    public void testVerticesZero() {
        Graph<String> graph = emptyInstance();        
        
        assertEquals(0, graph.vertices().size());
    }
    
    @Test
    public void testVerticesSingle() {
        Graph<String> graph = emptyInstance();
        graph.add("v1");
        
        assertEquals(1, graph.vertices().size());
    }
    @Test
    public void testVerticesMultiple() {
        Graph<String> graph = emptyInstance();
        graph.add("v1");
        graph.add("v2");
        
        assertEquals(2, graph.vertices().size());
    }

    // testing sources()
    
    @Test
    public void testSourcesZeroSources() {
        Graph<String> graph = emptyInstance();
        graph.add("v1");
        
        assertEquals(0, graph.sources("v1").size()); 
        }

    @Test
    public void testSourcesSingleSource() {
        Graph<String> graph = emptyInstance();
        graph.set("v1", "v2", 10);
        graph.set("v3", "v1", 11);
        
        assertEquals(1, graph.sources("v1").size());
        assertTrue(graph.sources("v1").containsKey("v3"));
        assertEquals(11, (int)graph.sources("v1").values().toArray()[0]);
    }
    
    @Test
    public void testSourcesMultipleSources() {
        Graph<String> graph = emptyInstance();
        graph.set("v1", "v2", 10);
        graph.set("v3", "v2", 11);
        graph.set("v4", "v2", 12);
        graph.set("v5", "v2", 13);
        
        List<String> sources = Arrays.asList("v1", "v3","v4", "v5");
        List<Integer> weights = Arrays.asList(10, 11, 12, 13);
       
        for (int i = 0; i < 4; i++) {
            assertTrue(graph.sources("v2").containsKey(sources.get(i)));
            assertEquals(weights.get(i), graph.sources("v2").get(sources.get(i)));
        }
    }
    
    // testing targets()
    
    @Test
    public void testTargetsZeroTargets() {
        Graph<String> graph = emptyInstance();
        graph.set("v1", "v2", 10);
        
        assertEquals(0, graph.targets("v2").size());
    }
    
    @Test
    public void testTargetsSingleTarget() {
        Graph<String> graph = emptyInstance();
        graph.set("v1", "v2", 10);
        graph.set("v3", "v1", 11);
        
        assertEquals(1, graph.targets("v1").size());
        assertTrue(graph.targets("v1").containsKey("v2"));
        assertEquals(10, (int)graph.targets("v1").values().toArray()[0]);
    }
    
    @Test
    public void testTargetsMultipleTargets() {
        Graph<String> graph = emptyInstance();
        graph.set("v1", "v2", 10);
        graph.set("v1", "v3", 11);
        graph.set("v1", "v4", 12);
        graph.set("v1", "v5", 13);
        
        List<String> targets = Arrays.asList("v2", "v3","v4", "v5");
        List<Integer> weights = Arrays.asList(10, 11, 12, 13);
       
        for (int i = 0; i < 4; i++) {
            assertTrue(graph.targets("v1").containsKey(targets.get(i)));
            assertEquals(weights.get(i), graph.targets("v1").get(targets.get(i)));
        }
        
        
    }
    
}

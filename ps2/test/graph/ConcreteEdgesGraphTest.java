/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    /*
     * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
     */
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    
    /*
     * Testing ConcreteEdgesGraph...
     */
    
    // Testing strategy for ConcreteEdgesGraph.toString()
    //   - empty graph, vertices no edges, 1 edge, multiple edges
    
    // TODO tests for ConcreteEdgesGraph.toString()
    
    @Test
    public void testToStringEmptyGraph() {
        Graph<String> graph = emptyInstance();
        
        assertEquals("Vertices: " + "\n" + "Edges:", graph.toString());
    }
    
    @Test
    public void testToStringVerticesNoEdges() {
        Graph<String> graph = emptyInstance();
        graph.add("v1");
        graph.add("v2");
        
        assertEquals("Vertices: v1, v2" + "\n" + "Edges:", graph.toString());
    }
    
    @Test
    public void testToStringSingleEdge() {
        Graph<String> graph = emptyInstance();
        graph.set("v1", "v2", 10);
        Edge e = new Edge("v1", "v2", 10);
        
        assertEquals("Vertices: v1, v2" + "\n" + "Edges:" + "\n" + e.toString(), graph.toString());
    }
    
    
    /*
     * Testing Edge...
     */
    
    // Testing strategy for Edge
    //      - create new edge object, modify existing edge
    //      - checkRep()
    
    // TODO tests for operations of Edge
    
    @Test
    public void testEdgeCreation() {
        Edge e = new Edge("v1", "v2", 10);
        
        assertEquals("v1", e.getSource());
        assertEquals("v2", e.getTarget());
        assertEquals(10, e.getWeight());
        assertEquals("v1 -> v2: 10", e.toString());   
    }
    
    @Test
    public void testEdgeModify() {
        Edge e = new Edge("v1", "v2", 10);
        e = e.setWeight(20);
        
        assertEquals(20, e.getWeight());
        assertEquals("v1", e.getSource());
        assertEquals("v2", e.getTarget());
        assertEquals("v1 -> v2: 20", e.toString());           
    }
    
    @Test(expected=AssertionError.class)
    public void testEdgeCheckRep() {
        Edge e = new Edge("v1", "v2", -10);
    }
    
    @Test
    public void testEdgeCheckRepNoAssertion() {
        Edge e = new Edge("v1", "v2", 10);
    }
}

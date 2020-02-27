/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph implements Graph<String> {
    
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
    // Abstraction function:
    // represents a mutable weighted directed graph with labeled vertices
    //
    // Representation invariant:
    // - all edges have weight > 0
    // - all vertices involved in edges are contained in vertices set
    // - each vertex has an unique label
    // 
    // Safety from rep exposure:
    //   - All fields are private and final 
    
    // TODO constructor
    
    public ConcreteEdgesGraph() {
    }
      
    // TODO checkRep
    private void checkRep() {
        
        for (Edge e : edges) {
            assert e.getWeight() > 0;
            assert vertices.contains(e.getSource());
            assert vertices.contains(e.getTarget());
        }
        
        // check for duplicate vertices
        for (int i = 0; i < vertices.size(); i++) {
            for (int j = i + 1; j < vertices.size() - 1; j++) {
                    assert (!vertices.toArray()[i].equals(vertices.toArray()[j]));
                }
            }
        // check for...
        
        
        }
    
        
       

    
    
   
   
    @Override public boolean add(String vertex) {
        return vertices.add(vertex);
        }    
    
    @Override public int set(String source, String target, int weight) {
        
        this.add(source);
        this.add(target);
        
        // need to check if edge already exists
        for (Edge e: edges) {
            if(e.getSource().equals(source) && e.getTarget().equals(target)) {
                int previousWeight = e.getWeight();
                edges.remove(e);
         
                if (weight > 0) {
                edges.add(e.setWeight(weight));                
                }
                
                this.checkRep();
                return previousWeight;
            }
        }
        // no existing edge in graph...
        if (weight > 0) {
            edges.add(new Edge(source, target, weight));
        }
        this.checkRep();
        return 0;
    }
    
    @Override public boolean remove(String vertex) {
        if (!vertices.contains(vertex)) {
            return false;
        }
        
        List<Edge> edgesToRemove= new ArrayList<>();
        for(Edge e : edges) {
            if (e.getSource().equals(vertex) || e.getTarget().equals(vertex)) {
                edgesToRemove.add(e);
            }
        }
        edges.removeAll(edgesToRemove);
        vertices.remove(vertex);
        
        this.checkRep();
        return true;
        
    }
    
    @Override public Set<String> vertices() {
        return this.vertices;
    }
    
    @Override public Map<String, Integer> sources(String target) {
        Map<String, Integer> sourcesMap = new HashMap<>();
        
        for(Edge e : edges) {
            if(e.getTarget().equals(target)) {
                sourcesMap.put(e.getSource(), e.getWeight());
            }
        }
        return sourcesMap;
    }
    
    @Override public Map<String, Integer> targets(String source) {
        Map<String, Integer> targetsMap = new HashMap<>();
        
        for(Edge e : edges) {
            if(e.getSource().equals(source)) {
                targetsMap.put(e.getTarget(), e.getWeight());
            }
        }
        return targetsMap;
    }
    
    // TODO toString()
    /**
     * @return a string representation of the graph that includes the list of vertices and all the edges"
     */
    
    @Override public String toString() {
        String stringRep = "Vertices: ";
        for(String v : vertices) {
            stringRep = (stringRep + v + ", ");
        }
        stringRep = stringRep.replaceAll(", $", "");
        stringRep = stringRep + "\n" + "Edges:";
        
        for(Edge e : edges) {
            stringRep = stringRep + "\n" + e.toString();
        }
        return stringRep;
    }
}

/**
 * Immutable data type representing an edge in a mutable directed weighted graph with labeled vertices. 
 * An edge consists of source vertex, target vertex, and positive weight connecting the vertices.
 * This class is internal to the rep of ConcreteEdgesGraph. 
 * 
 * <p>PS2 instructions: the specification and implementation of this class is
 * up to you.
 */
class Edge {
    
    // TODO fields
    
    private final String source; 
    private final String target;
    private final int weight;
    
    // Abstraction function:
    //      represents an edge in a mutable weighted directed graph with labeled vertices
    //
    // Representation invariant:
    //      - weight > 0
    //      - source and target are vertices in the graph
    //   
    // Safety from rep exposure:
    //      - all fields are private, final, and immutable types
    
    
    // TODO constructor
   
    
    public Edge(String source, String target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        checkRep();
    }
    
    // TODO checkRep
    private void checkRep() {
        assert this.weight > 0;
    }
    
    // TODO methods
    
    
    /**
     * @return String source of edge
     */
    public String getSource() {
        return this.source;
    }
    
    /**
     * @return String target of edge
     */
    public String getTarget() {
        return this.target;
    }
    
    /**
     * @return int weight of edge
     */
    public int getWeight() {
        return this.weight;
    }
    
    /**
     * modify the weight of an existing edge with new edge object
     * @param weight positive weight of edge
     * @return new edge object with updated weight (maintain immutability contract)
     */
    public Edge setWeight(int weight) {
        return new Edge(this.source, this.target, weight);
    }
    
    
    /**
     * @return String representation of edge that includes source, target, weight
     */
    // TODO toString()
    @Override public String toString() {
        return (this.source + " -> " + this.target + ": " + this.weight);
    }
    
}

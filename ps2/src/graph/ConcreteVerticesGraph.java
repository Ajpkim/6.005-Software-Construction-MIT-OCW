/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package graph;

import java.util.ArrayList;
import java.util.Arrays;
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
public class ConcreteVerticesGraph<L> implements Graph<L> {
    
    private final List<Vertex<L>> vertices = new ArrayList<>();
    
    // Abstraction function:
    //   - represent a mutable weighted directed graph
    
    // Representation invariant:
    //      - all weights are > 0
    //      - no duplicate vertices
    
    // Safety from rep exposure:
    //   all methods that return objects return defensively copied versions of data
    
    
    // constructor
    public ConcreteVerticesGraph() {
    }
    
    // TODO checkRep
    public void checkRep() {
        
        // check for duplicate vertices (by vertex labels)
        for (int i = 0; i < vertices.size(); i++){
            for (int j = i + 1; j < vertices.size() - 1; j++) {
                assert(! vertices.get(i).getLabel().equals(vertices.get(j).getLabel()));
            }
        }
        // check for illegal weights (weight < 1)
        for (Vertex<L> v : vertices) {
            for (int weight : v.getTargetsMap().values()) {
                assert (weight > 0);
            }
        }
    }
    
    
    @Override public boolean add(L vertex) {
        if (! this.vertices().contains(vertex)) {
            this.vertices.add(new Vertex<L>(vertex));
            return true;
        }
        return false;
    }
    
    
    /**
     * helper function to get vertex object in graph.
     * Requires that label matches the label of an existing vertex in the graphs
     * @param L label
     * @return Vertex object with given label
     */
    public Vertex<L> getVertex(L label){
        // check that vertex exists
        assert(this.vertices().contains(label));

        for(Vertex<L> v : vertices) {
            if (v.getLabel().equals(label)){
                return v;
            }
        }
        return new Vertex<>(label); // never actually used...
    }
   
    
    @Override public int set(L source, L target, int weight) {
        
        this.add(source);
        this.add(target);
        int previousWeight = 0;
                                  
        Vertex<L> sourceVertex = this.getVertex(source);
        Vertex<L> targetVertex = this.getVertex(target);
            
        // check if edge already exists...        
        if (! sourceVertex.getTargetsLabels().contains(target)) {
            
            // edge doesn't already exist
            if (weight > 0) {  // add the edge                 
                sourceVertex.addTarget(target, weight);
                targetVertex.addSource(source, weight);
            }
                this.checkRep(); 
            return previousWeight; // returns 0 whether we added or not (indicating edge didn't exist prior)
        }        
         
        // edge exists and needs to be updated...
        previousWeight = sourceVertex.getTargetsMap().get(target);
        
        // updating existing edge representations...
        sourceVertex.removeTarget(target);
        targetVertex.removeSource(source);
        
        if (weight > 0) {
            sourceVertex.addTarget(target, weight);
            targetVertex.addSource(source, weight);
        }
        this.checkRep(); 
        return previousWeight;
    }

    
    @Override public boolean remove(L vertex) {
        // vertex not in graph...
        if (! vertices().contains(vertex)) {
            return false;
        }
        for (Vertex<L> v : vertices) {
            v.removeSource(vertex);
            v.removeTarget(vertex);
        }
        vertices.remove(this.getVertex(vertex));       
        return true;   
    }

    
    @Override public Set<L> vertices() {
        Set<L> vertexLabels = new HashSet<>();
        
        for (Vertex<L> v : vertices) {
            vertexLabels.add(v.getLabel()); 
        }
        return vertexLabels;
    }
    
    @Override public Map<L, Integer> sources(L target) {
        assert(this.vertices().contains(target));
        
        Map<L, Integer> sourcesMap = new HashMap<>();
        for (Vertex<L> v : vertices) {
            if (v.getLabel().equals(target)) {
                sourcesMap = v.getSourcesMap();
            }
        }
        return sourcesMap;
    }
    
    @Override public Map<L, Integer> targets(L source) {
        assert(this.vertices().contains(source));
        
        Map<L, Integer> targetsMap = new HashMap<>();
        for (Vertex<L> v : vertices) {
            if (v.getLabel().equals(source)) {
                targetsMap = v.getTargetsMap();
            }
        }
        return targetsMap;
    }
    
    @Override public String toString() {
        String stringRep = "Vertices:" + "\n";
        
        for (Vertex<L> v : vertices) {
            stringRep = stringRep + v.getLabel() + "\n";
        }
   return stringRep;
    }   
}

/**
 * Mutable data type representing an vertex in a mutable weighted directed graph. 
 * 
 * A vertex consists of a label, sources map, and target map. 
 * The keys in the sources map are all the vertices that are directed at the vertex, 
 * and the values are the weights. The keys in the targets map are all the vertices
 * that the given vertex is directed at, and the values are the weights
 * 
 * This class is internal to the rep of ConcreteVerticesGraph.
 */
class Vertex<L> {
       
    private final L label;
    private final Map<L, Integer> sources;
    private final Map<L, Integer> targets;
    
    // Abstraction function:
    //      - represents an uniquely labeled vertex in a mutable weighted directed graph
    //
    // Representation invariant:
    //      - all values in the sources and targets map are > 0   
    //
    // Safety from rep exposure:
    //      - all fields are private final 
    //      - return defensive copies of mutable type objects
    
    
    // constructor with only label parameter
    public Vertex(L label) {
        this.label = label;
        this.sources = new HashMap<L, Integer>();
        this.targets = new HashMap<L, Integer>();
    }
    
    // constructor for building Vertex objects more efficiently if data is at hand, otherwise pass null if missing sources or targets map
    public Vertex (L label, Map<L, Integer> sources, Map<L, Integer> targets) {
        this.label = label;
        this.sources = sources != null ? sources : new HashMap<L, Integer>();
        this.targets = targets != null ? targets : new HashMap<L, Integer>();
        
        this.checkRep();
    }
      
    // TODO checkRep
    public void checkRep() {
//        List<Integer> weights = new ArrayList<>();
        for (Integer val : this.sources.values()) {
            assert val > 0;
        }
        
        for (Integer val : this.targets.values()) {
            assert val > 0;
        }
    }

    
    /**
     * @return L label
     */
    public L getLabel() {        
        return this.label;
    }
    
    /**
     * @param vertex object to be compared to this vertex
     * @return true if edges have the same label, sourcesMap, targetsMap, and false otherwise
     */
    public boolean isSameVertex(Vertex<L> comparisonVertex) {
        if (! this.getLabel().equals(comparisonVertex.getLabel())) {
            return false;
        }
        if(! this.sources.equals(comparisonVertex.sources)) {
            return false;
        }
        if (! this.getSourcesMap().equals(comparisonVertex.getSourcesMap())){
            return false;
        }
        if (! this.getSourcesMap().equals(comparisonVertex.getTargetsMap())) {
            return false;
        }
        return true;
    }
    
    /**
     * @return defensively copied set of all source vertices
     */
    public Set<L> getSourcesLabels(){
        this.checkRep();
        return new HashSet<L>(this.sources.keySet());
    }
    

    /**
     * @return defensively copied map of sources and weights
     */
    public Map<L, Integer> getSourcesMap(){
        return new HashMap<L, Integer>(this.sources);
    }
    
    /**
     * @return defensively copied set of all target vertices
     */
    public Set<L> getTargetsLabels(){
        this.checkRep();
        return new HashSet<L>(this.targets.keySet());
    }
    
    /**
     * @return defensively copied map of targets and weights
     */
    public Map<L, Integer> getTargetsMap(){
        return new HashMap<L, Integer>(this.targets);
    }
    
    /**
     * @param L source vertex label
     * @param weight of edge
     * @return true if source not already a key in this.sources, false otherwise (and vertex is not modified)
     */
    public boolean addSource(L source, int weight) {
        if (this.sources.containsKey(source)) {
            return false;
        }
        this.sources.put(source, weight);
        this.checkRep();
        
        return true;
    }
    
    /**
     * @param L target vertex label
     * @param weight of edge
     * @return true if target not already a key in this.sources, false otherwise (and vertex is not modified)
     */
    public boolean addTarget(L target, int weight) {
        if (this.targets.containsKey(target)) {
            return false;
        }
        this.targets.put(target, weight);
        this.checkRep();
        
        return true;
    }
    
    
    /**
     * removes the given vertex from this vertex's sources map
     * @param L label of source vertex to remove
     */
    public boolean removeSource(L source) {
        if (this.getSourcesLabels().contains(source)) {
            this.sources.remove(source);
            return true;
        }
        return false;
    }
    
    /**
     * removes the given vertex from this vertex's targets map
     * @param L label of target vertex to remove
     */
    public boolean removeTarget(L target) {
        if (this.getTargetsLabels().contains(target)) {
            this.targets.remove(target);
            return true;
        }
        return false;
    }
    
    // TODO toString()
    
    /**
     * @return String representation of vertex that includes sources and weights, targets and weights
     */
    public String toString() {
    String stringRep;
    stringRep = "label: " + this.getLabel() + "\n" + 
                 "sources: x ---> " + this.getLabel() + "\n";
    stringRep = stringRep + this.sources.toString() + "\n";
    stringRep = stringRep + "targets: " + this.getLabel() + " ---> x" + "\n";
    stringRep = stringRep + this.targets.toString();
            
    return stringRep;
    }
}

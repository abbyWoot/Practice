import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
 
public class Graph<V,E> extends AbstractGraph<V, E> {
    protected final Map<V, Map<V, EdgeInfo<E>>> adjacencyMap;   // [source vertex] -> [[destination vertex] --> [edge info]]
    protected final Map<V, VertexInfo<V>> vertexInfo;           // [vertex] -> [vertex info]
    protected final List<E> edgeList;  
 
    public Graph() {
        this.adjacencyMap = new HashMap<V, Map<V, EdgeInfo<E>>>();
        this.vertexInfo = new HashMap<V, VertexInfo<V>>();
        this.edgeList = new ArrayList<E>();
    }
 
    //pre:Accepts a vertex to be added. If the vertex is null a NullPointerException will be thrown
    //post: the vertex v is added to the graph
    public void addVertex(V v) {
        if(v == null){
            throw new NullPointerException();
        }else if(!adjacencyMap.containsKey(v)){
            adjacencyMap.put(v, null);
            VertexInfo<V> x = new VertexInfo<V>(v);
            vertexInfo.put(v, x);
        }
    }
 
    //post: returns true is the value passed is a vertex in the graph and false otherwise
    public boolean containsVertex(V v) {
        return adjacencyMap.containsKey(v);
    }
 
    //pre: the passed value must be non null and be contained in the graph
    //post: returns the neighbors of a valid key
    public Collection<V> neighbors(V v) {
        if(v == null){
            throw new NullPointerException();
        }else if(!containsVertex(v)){
            throw new IllegalArgumentException();
        }
        return adjacencyMap.get(v).keySet();
    }
 
 
    //pre:both vertexes passed must be contained in the graph
    //post: an edge of weight 1 will be added between the vertexes
    public void addEdge(V v1, V v2, E e) {
        if (v1 == null || v2 == null){
            throw new NullPointerException();
        }else if(!containsVertex(v1) || !containsVertex(v2)){
            throw new IllegalArgumentException();
        }
        EdgeInfo<E> e1 = new EdgeInfo<E>(e);
        adjacencyMap.get(v1).put(v2, e1);
        adjacencyMap.get(v2).put(v1, e1);
        edgeList.add(e);
 
    }
 
    //pre:both vertexes passed must be contained in the graph
    //post: an edge of the specified will be added between the vertexes
    public void addEdge(V v1, V v2, E e, int weight) {
        if (v1 == null || v2 == null){
            throw new NullPointerException();
        }else if(!containsVertex(v1) || !containsVertex(v2)){
            throw new IllegalArgumentException();
        }
        EdgeInfo<E> e1 = new EdgeInfo<E>(e, weight);
        adjacencyMap.get(v1).put(v2, e1);
        adjacencyMap.get(v2).put(v1, e1);
        edgeList.add(e);
 
    }
 
 
    //post: returns true if the vertexes are connected by an edge.
    public boolean containsEdge(V v1, V v2) {
        if(!adjacencyMap.containsKey(v1)){
            return false;
        }
        return adjacencyMap.get(v1).containsKey(v2);
    }
 
    //pre:both vertexes passed must be contained in the graph
    //post: returns the E value of the edge between the 2 vertexes or null if no edge exists
    public E edge(V v1, V v2) {
        if(v1 == null || v2 == null){
            throw new NullPointerException();
        }else if(!containsVertex(v1) || !containsVertex(v2)){
            throw new IllegalArgumentException();
        }
        else if(!adjacencyMap.get(v1).containsKey(v2)){
            return null;
        }else{
            return adjacencyMap.get(v1).get(v2).e;
        }
    }
 
    //pre:both vertexes passed must be contained in the graph and have an edge between them
    //post: returns the weight of the edge between the 2 vertexes
    public int edgeWeight(V v1, V v2) {
        if(v1 == null || v2 == null){
            throw new NullPointerException();
        }else if((!containsVertex(v1) || !containsVertex(v2)) || !adjacencyMap.get(v1).containsKey(v2)){
            throw new IllegalArgumentException();
        }else{
            return adjacencyMap.get(v1).get(v2).weight;
        }
    }
 
 
 
}

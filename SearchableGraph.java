import java.util.*;
 
 
 
public class SearchableGraph<V, E> extends Graph<V,E> implements ISearchableGraph<V, E>   {
 
 
 
    //pre: v1 and v2 must be non null and belong to the graph. If either v1 or v2 is null a null
    //pointer exception will be thrown. If either v1 or v2 does not belong to the graph a illegal argument 
    //exception will be thrown
    //post: a list of the vertexes belonging to the minimum weight path will be returned.
 
    public List<V> minimumWeightPath(V v1, V v2) {
        if(v1 == null || v2 == null){
            throw new NullPointerException();
        }
        if(!adjacencyMap.containsKey(v1) || !adjacencyMap.containsKey(v2) ){
            throw new IllegalArgumentException();
        }
        if(v1.equals(v2)){ //if v1 == v2  a path is already found and no other work needs to be done
            List<V> path = new LinkedList<V>();
            path.add(v2);
            return path;
        }
        List<V> list = new LinkedList<V>();
        for(V v : adjacencyMap.keySet()){
            vertexInfo.get(v).distance = 10000; //set to "infinity"
            vertexInfo.get(v).visited = false; //set to false just in case this value has not been reset
            list.add(v);
        }
        vertexInfo.get(v1).distance = 0;
        boolean found = false;
        while(!list.isEmpty()){
            V v = findShortest(list);
            for(V n : adjacencyMap.get(v).keySet()){
                if(!vertexInfo.get(n).visited){
                    int dist = vertexInfo.get(v).distance + adjacencyMap.get(v).get(n).weight;
                    if(dist < vertexInfo.get(n).distance){
                        vertexInfo.get(n).distance = dist;
                        vertexInfo.get(n).previous = v;
                        if(n.equals(v2)){
                            found = true;
                        }
                    }
 
                }
            }
        }
        if(found){ // if found then reconstruct path
            V v = v2;
            List<V> path = new LinkedList<V>();
            while(!v.equals(v1)){
                path.add(0, v);
                v = vertexInfo.get(v).previous;
            }
            path.add(0, v1);
            return path;
        }else{
            return null;
        }
    }
 
    //pre:at least one vertex in the list must be not visited
    //post: will return the non visited vertex with the shortest distance and marks 
    //that vertex as visited
    private V findShortest(List<V> list){
            list.toString();
            //find unvisited v 
            V temp = null;
            for(V v : list){
                if(vertexInfo.get(v).visited == false){
                    temp = v;
                }
                else {
                    break;
                }
            }
            for(V v : list){
                VertexInfo<V> info = vertexInfo.get(v);
 
                if(info.distance < vertexInfo.get(temp).distance && info.visited == false){
                    temp = v;
                }
            }
            list.remove(temp);
            return temp;
    }
 
    //pre: v1 and v2 must be non null and belong to the graph. If either v1 or v2 is null a null
    //pointer exception will be thrown. If either v1 or v2 does not belong to the graph a illegal argument 
    //exception will be thrown
    //post: true will be returned if a path between v1 and v2 exists and false will be return otherwise
    public boolean reachable(V v1, V v2) {
        if(v1 == null || v2 == null){
            throw new NullPointerException();
        }
        if(!adjacencyMap.containsKey(v1) || !adjacencyMap.containsKey(v2) ){
            throw new IllegalArgumentException();
        }
        return shortestPath(v1, v2) != null;
    }
 
 
 
    //pre: v1 and v2 must be non null and belong to the graph. If either v1 or v2 is null a null
    //pointer exception will be thrown. If either v1 or v2 does not belong to the graph a illegal argument 
    //exception will be thrown
    //post: a list of the vertexes belonging to the shortest path will be returned.
    public List<V> shortestPath(V v1, V v2) {
        if(v1 == null || v2 == null){
            throw new NullPointerException();
        }
        if(!adjacencyMap.containsKey(v1) || !adjacencyMap.containsKey(v2) ){
            throw new IllegalArgumentException();
        }
        List<V> list = new LinkedList<V>();
        for(V k : adjacencyMap.keySet()){ //reset vertexInfo
            vertexInfo.get(k).visited = false;
             vertexInfo.get(k).previous = null;
        }
        list.add(v1);
        vertexInfo.get(v1).visited = true;
        while(!list.isEmpty()){
            V v = list.remove(0);
            if(v.equals(v2)){ //path found
                List<V> path = new LinkedList<V>();
                path.add(v2);
                while(v != v1){
                    v = vertexInfo.get(v).previous;
                    path.add(0,v);
                }
                return path;
            }else{
                for(V k : adjacencyMap.get(v).keySet() ){
                    if(!vertexInfo.get(k).visited){
                     vertexInfo.get(k).visited = true;
                     vertexInfo.get(k).previous = v;
                     list.add(k);
                    }
                }
            }
        } 
        return null;
    }
 
 
 
}

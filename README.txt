This is an old assignment from my data structures and algorithms course. Please see Setp 2 of the description for details on what the final product actually does.
This assignment shows my understaning of data structures and classes. 



Honestly, in step 0 I made a few mistakes that I should not have. If you look at Graph.java in the function addEdge, I did not check that the edge weight was greater
than 0. This could cause series problems when traversing the graph. This is a problem with implementing error checking. Since working on this project
I have become much better at error catching and writing tests to catch problems this would have created.

In  public void addEdge(V v1, V v2, E e, int weight) I did not check if the edge already existed. This would create another edge between the 2 vertices. 
This would cause traversing the graph to break. Yet again this was caused by my lack of error checking. Before I started work at my current job I was very bad at 
error checking. Now, much of my job involves finding ways people could possibly break our code and preventing them from ever doing so.
I have become much better at error checking.

In SearchableGraph.java I had some redundant code, and there were a few places where my boolean logic could have been simplified. Neither of these caused problems,
but they were bad practice. Boolean logic is something that I have improved upon greatly. It was just a matter of practice to see how everything should fit together.


Feel free to play around with the Kevin Bacon game or contact me if you have any questions about the code. I wrote KevinBacon.java, Graph.java, and SearchableGraph.java.
I hope that this gives you some idea of my coding style.



Step 0 – Graph Implementation:
In this step of the assignment, you will complete a graph implementation. For this step, you are given 
supporting files IGraph.java, AbstractGraph.java, VertexInfo.java, and EdgeInfo.java. You will write 
a class named Graph (in file Graph.java) that extends the instructor-provided AbstractGraph class. 
AbstractGraph partially implements the IGraph interface. Your goal will be to add methods to the graph to 
complete the implementation of the IGraph interface found below. Details of the behavior of each method you 
are to implement can be found in the "Methods to Implement" section below.
public interface IGraph<V, E> {
 // vertex-related methods
 public void addVertex(V v);
 public boolean containsVertex(V v);
 public Collection<V> neighbors(V v);
 public Collection<V> vertices();
 // edge-related methods
 public void addEdge(V v1, V v2, E e);
 public void addEdge(V v1, V v2, E e, int weight);
 public boolean containsEdge(V v1, V v2);
 public E edge(V v1, V v2);
 public Collection<E> edges();
 public int edgeWeight(V v1, V v2);
}
The graph representation that you will be using for your implementation is the "adjacency map". The adjacency 
map is a double mapping that connects pairs of vertices to their associated edges. This is represented by the 
data structure adjacencyMap of type Map<V, Map<V, EdgeInfo<E>>> in the AbstractGraph class. Each key 
in the adjacencyMap data structure is a vertex v
0 in the graph. The value that v
0 is mapped to in adjacencyMap
is a second Map that has as its keys all vertices that v
0 is connected to in the graph. In this second Map, each 
vertex v
m (that v
0 is connected to) maps to information about the edge that is connecting v
0 to v
m. So, to find out 
if there is an edge between v
1 and v
2
we could call adjacencyMap.get(v1).containsKey(v2) and to get the 
information about the edge between v
1 and v
2 we could call adjacencyMap.get(v1).get(v2). The benefit of 
this representation is that your graph will have constant (O(1)) expected runtime for common operations such as 
adding/retrieving vertices and edges, or getting collections of vertices and neighbors.
Two additional data structures can be found in the AbstractGraph class: vertexInfo (of type Map<V, 
VertexInfo<V>>) and edgeList (of type List<E>). vertexInfo contains a mapping from vertices to 
VertexInfo objects. The VertexInfo object keeps additional information about vertices that are helpful for 
different graph algorithms. edgeList is a collection of all edges in the graph. All of this data could be kept in 
the adjacencyMap data structure, but these additional data structures allow for code clarity, ease of use, and 
efficient support for common operations performed on the graph.
In addition to these data structures, the AbstractGraph class provides a no-argument constructor that constructs 
an empty undirected graph by initializing the declared data structures, methods that can be used in your Graph
class to check that the graph is in a valid state and parameters passed to methods are valid (checkForNull, 
checkVertex, checkVertices), and implementation of the following methods:
public Collection<E> edges() returns a read-only collection of the graph's edges
public String toString() returns a String representation of the graph
public Collection<V> vertices() returns a read-only collection of the graph's vertices
protected void clearVertexInfo() resets all distance/previous/visited data from all the VertexInfo objects in 
this graph (useful for Step 1)Methods to Implement:
The methods you must implement to complete the IGraph interface are listed below in detail. 
• public void addVertex(V v)
Adds a vertex of generic type V to the graph. If there is already a vertex in the graph with this 
information, no change should be made to the graph. If the vertex passed is null, you should throw a 
NullPointerException.
• public boolean containsVertex(V v)
Returns true if there exists a vertex in the graph with the given vertex v; otherwise, return false. 
• public Collection<V> neighbors(V v)
Returns a collection containing all vertices that are connected to the given vertex v by an edge. If the 
vertex passed is null, you should throw a NullPointerException. If the vertex passed is not a part 
of the graph, you should throw an IllegalArgumentException.
• public void addEdge(V v1, V v2, E e)
Adds an undirected edge to the graph between the two vertices v1 and v2. e is of generic type E and 
represents the information to store in the edge. The edge should by given a default weight of 1. If an 
edge already exists between the vertices, it should be replaced with the given information. If any of 
the arguments are null, you should throw a NullPointerException. If either of the vertices is not a 
part of the graph, you should throw an IllegalArgumentException.
• public void addEdge(V v1, V v2, E e, int weight)
Adds an undirected edge to the graph between the two vertices v1 and v2. e is of generic type E and 
represents the information to store in the edge. The edge should have the given weight. If an edge 
already exists between the vertices, it should be replaced with the given information. If any of the 
arguments are null, you should throw a NullPointerException. If either of the vertices is not a part 
of the graph or if the edge weight is negative, you should throw an IllegalArgumentException.
• public boolean containsEdge(V v1, V v2) 
Returns true if there exists an edge between the two vertices v1 and v2; return false otherwise.
• public E edge(V v1, V v2);
Returns the edge that connects v1 to v2. If v1 and v2 are legal vertices but there is no edge between 
them, you should return null. If either of the vertices passed is null, you should throw a 
NullPointerException. If either of the vertices passed is not a part of the graph, you should throw 
an IllegalArgumentException.
• public int edgeWeight(V v1, V v2);
Returns the weight of the edge that connects v1 and v2. If either of the vertices is null, you should 
throw a NullPointerException. If v1 and v2 are legal vertices but there is no edge between them or 
if either of the vertices is not a part of the graph, you should throw an IllegalArgumentException.


Step 1 - Graph Search Implementation:
For this step, you will write a class named SearchableGraph (in file SearchableGraph.java) that extends 
your Graph class from Step 0 and implements the ISearchableGraph interface. Your goal is to add path 
searching methods to the graph. 
Methods to Implement:
The methods you must implement to complete the ISearchableGraph interface are listed below in detail. Each 
of these methods should not modify the state of the map's vertices or edges.
• public List<V> minimumWeightPath(V v1, V v2)
Returns the path in this graph, with the lowest total path weight, that leads from the given starting 
vertex v1 to the given ending vertex v2. Use Dijkstra's algorithm to find the path. The minimum 
weight path from a vertex v1 to itself should be a one-element list containing only v1. This method 
should be O(|V|2). If v2 is not reachable from v1, the method returns null. If either of the vertices 
passed is null, you should throw a NullPointerException. If either of the vertices is not a part of 
the graph, you should throw an IllegalArgumentException.
• public List<V> shortestPath(V v1, V v2)
Returns the path in this graph, with the least number of vertices, that leads from the given starting 
vertex v1 to the given ending vertex v2. Use the breadth-first algorithm to find the path. The shortest 
path from a vertex v1 to itself should be a one-element list containing only v1. This method should be 
O(|V| + |E|). If v2 is not reachable from v1, the method returns null. If either of the vertices passed is 
null, you should throw a NullPointerException. If either of the vertices is not a part of the graph, 
you should throw an IllegalArgumentException.
• public boolean reachable(V v1, V v2)
Returns whether there is any path in this graph that leads from the given starting vertex v1 to the given 
ending vertex v2. Any vertex can reach itself. This method should be O(|V| + |E|). If either of the 
vertices passed is null, you should throw a NullPointerException. If either of the vertices passed 
is not a part of the graph, you should throw an IllegalArgumentException.
Graph Searching Algorithms:
Use the following pseudo-code for breadth-first search and Dijkstra's algorithm to help you implement the graph search 
algorithms.
BFS(v1, v2):
for each vertex v:
 v's visited := false.
v's previous := none.
List := {v1}.
mark v1 as visited.
while List is not empty:
v := List.removeFirst().
if v is v2:
path is found.
reconstruct path from v2 back to v1,
following previous pointers.
else, for each unvisited neighbor n of v:
mark n as visited.
n's previous := v.
List.addLast(n).
path is not found. 
Dijkstra(v1, v2):
 for each vertex v:
v's distance := infinity.
v's previous := none.
v1's distance := 0.
List := {all vertices}.
while List is not empty:
v := remove List vertex with minimum distance.
 mark v as visited.
for each unvisited neighbor n of v:
dist := v's distance + edge (v, n)'s weight.
if dist is smaller than n's distance:
n's distance := dist.
n's previous := v.
 if path is found
reconstruct path from v2 back to v1, following
 previous pointers. 
 
 
 
 Step 2 – Kevin Bacon Game:
In this step of the assignment, you will use your graph and search algorithm implementation to solve the Kevin 
Bacon problem. For this step, you will be given the supporting files KevinBacon.java and movies.txt. You
will add your code to and turn in KevinBacon.java. If you are using Eclipse, movies.txt should be saved in 
your main project directory.
The given KevinBacon.java file builds a SearchableGraph from movies.txt, a file of actors and movies. 
You should add to this file by printing an introductory message to the user, and then prompt them for an actor's 
name. You should then search the graph for the shortest path between the actor and Kevin Bacon, and print the 
information about the path returned. Here's an example log of execution; your output should match exactly:
Welcome to the Six Degrees of Kevin Bacon.
If you tell me an actor's name, I'll connect them to Kevin Bacon through
the movies they've appeared in. I bet your actor has a Kevin Bacon number
of less than six!
Actor's name (or ALL for everyone)? Brad Pitt
Path from Brad Pitt to Kevin Bacon:
Brad Pitt was in Ocean's Eleven (2001) with Julia Roberts
Julia Roberts was in Flatliners (1990) with Kevin Bacon
Brad Pitt's Bacon number is 2
When the user types "ALL", your program should print the paths between every actor and Kevin Bacon. If the 
user types the name of an actor that is not found in the graph, you program should print "No such actor."
"There are two types of actors: those who say they want to be famous and those who are liars." -- Kevin Bacon
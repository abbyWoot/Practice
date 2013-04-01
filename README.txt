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
should be O(|V|
2
). If v2 is not reachable from v1, the method returns null. If either of the vertices 
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
 previous pointers. Step 2 – Kevin Bacon Game:
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
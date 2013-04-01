import java.util.*;
import java.io.*;
 
public class KevinBacon {
    public static void main(String[] args) throws FileNotFoundException {
        ISearchableGraph<String, String> graph = buildGraph();
       //System.out.println(graph);
        printMessage();
        Scanner console = new Scanner(System.in);
        String actor = console.nextLine();
        if(actor.equals("ALL")){
            for(String s : graph.vertices()){
                getPath(graph, s);
            }
        }else if(graph.containsVertex(actor)){
            getPath(graph, actor);
        }else{
            System.out.println("No such actor.");
        }
    }
 
    public static void printMessage(){
        System.out.println("Welcome to the Six Degrees of Kevin Bacon.");
        System.out.println("If you tell me an actor's name, I'll connect them to Kevin Bacon through");
        System.out.println("the movies they've appeared in.  I bet your actor has a Kevin Bacon number");
        System.out.println("of less than six!");
        System.out.println();
        System.out.print("Actor's name (or ALL for everyone)? ");
 
    }
 
    public static void getPath(ISearchableGraph<String, String> graph, String actor){
        System.out.println();
        System.out.println("Path from " + actor + " to Kevin Bacon:");
        if(graph.reachable(actor, "Kevin Bacon")){
            List<String> path = graph.shortestPath(actor, "Kevin Bacon");
            int num = path.size() -1;
            String s = path.remove(0);
            while(!path.isEmpty()){
                String s2 = path.remove(0);
                String movie = graph.edge(s, s2);
                System.out.println(s + " was in " + movie + " with " + s2);
                s = s2;
            }   
            System.out.println(actor + "'s Bacon number is " + num );
        }else{
            System.out.println("No path found.");
        }
 
    }
    public static ISearchableGraph<String, String> buildGraph() throws FileNotFoundException {
        Scanner input = new Scanner(new File("movies.txt"));
        ISearchableGraph<String, String> graph = new SearchableGraph<String, String>();
 
        while (input.hasNextLine()) {
            Scanner line = new Scanner(input.nextLine()).useDelimiter(";");
 
            String movie = line.next();
 
            // get all of the actors in the movie
            List<String> actors = new ArrayList<String>();
            while (line.hasNext()) {
                String actor = line.next();
                graph.addVertex(actor);
                actors.add(actor);
            }
 
            // connect all of the actors
            for (int i = 0; i < actors.size(); i++) {
                for (int j = 1; j < actors.size(); j++) {
                    graph.addEdge(actors.get(i), actors.get(j), movie);
                }
            }
 
        }
 
        return graph;
    }
}



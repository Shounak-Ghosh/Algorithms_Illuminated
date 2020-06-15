package Course2.Week1;// Java Program to demonstrate adjacency list
// representation of graphs
import java.util.LinkedList;
// A user defined class to represent an undirected graph.
// A graph is an array of adjacency lists.
// Size of array will be V (number of vertices
// in graph)
public class UndirectedGraph
{
    private int V; //number of vertices
    private LinkedList<Integer>[] adjListArray;

    // constructor
    public UndirectedGraph(int V)
    {
        this.V = V;

        // define the size of array as
        // number of vertices
        adjListArray = new LinkedList[V];

        // Create a new list for each vertex
        // such that adjacent nodes can be stored
        for(int i = 0; i < V ; i++){
            adjListArray[i] = new LinkedList<>();
        }
    }


    // Adds an edge to an undirected graph
    private static void addEdge(UndirectedGraph graph, int src, int dest)
    {
        // Add an edge from src to dest.
        graph.adjListArray[src].add(dest);

        // Since graph is undirected, add an edge from dest
        // to src also
        graph.adjListArray[dest].add(src);
    }

    // A utility function to print the adjacency list
    // representation of graph
    private static void printGraph(UndirectedGraph graph)
    {
        for(int v = 0; v < graph.V; v++)
        {
            System.out.println("Adjacency list of vertex "+ v);
            System.out.print("head");
            for(Integer pCrawl: graph.adjListArray[v]){
                System.out.print(" -> "+pCrawl);
            }
            System.out.println("\n");
        }
    }

    // Driver program to test above functions
    public static void main(String args[])
    {

        int V = 5;
        UndirectedGraph graph = new UndirectedGraph(V);
        addEdge(graph, 0, 1);
        addEdge(graph, 0, 4);
        addEdge(graph, 1, 2);
        addEdge(graph, 1, 3);
        addEdge(graph, 1, 4);
        addEdge(graph, 2, 3);
        addEdge(graph, 3, 4);


        // print the adjacency list representation of
        // the above graph
        printGraph(graph);
    }
}


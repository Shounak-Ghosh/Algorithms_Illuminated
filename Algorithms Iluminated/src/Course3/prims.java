package Course3;// Java program for Prim's MST for
// adjacency list representation of graph
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class prims
{

    // for the Graph
    class node1 {

        // Stores destination vertex in adjacency list
        int dest;

        // Stores weight of a vertex in the adjacency list
        int weight;

        // Constructor
        node1(int a, int b)
        {
            dest = a;
            weight = b;
        }

        public String toString()
        {
            return dest + " " + weight;
        }
    }
    static class Graph
    {

        // Number of vertices in the graph
        int V;

        // List of adjacent nodes of a given vertex
        LinkedList<node1>[] adj;

        // Constructor
        Graph(int e)
        {
            V = e;
            adj = new LinkedList[V];
            for (int o = 0; o < V; o++)
                adj[o] = new LinkedList<>();
        }
    }

    // class to represent a node in PriorityQueue
    // Stores a vertex and its corresponding
    // key value
    class node
    {
        int vertex;
        int key;
    }

    // Comparator class created for PriorityQueue
    // returns 1 if node0.key > node1.key
    // returns 0 if node0.key < node1.key and
    // returns -1 otherwise
    class comparator implements Comparator<node>
    {

        @Override
        public int compare(node node0, node node1)
        {
            return node0.key - node1.key;
        }
    }

    int getWeight(Graph g, int src, int dest)
    {
        if(src > 0)
        {
            Iterator<node1> itr = g.adj[src].iterator();
            node1 current = null;
            while (itr.hasNext())
            {
                current = itr.next();
                if (current.dest == dest)
                {
                    return current.weight;
                }
            }

        }
        if(g.adj[src].getLast().dest == dest)
        {
            return g.adj[src].getLast().weight;
        }
        return -1;
    }

    public void printGraph(Graph g)
    {
        for(LinkedList<node1> ls : g.adj)
        {
            System.out.println(ls);
        }
    }

    // method to add an edge
    // between two vertices
    void addEdge(Graph graph, int src, int dest, int weight)
    {

        node1 node0 = new node1(dest, weight);
        node1 node = new node1(src, weight);
        graph.adj[src].addLast(node0);
        graph.adj[dest].addLast(node);
    }

    // method used to find the mst
    // method used to find the mst
    void prims_mst(Graph graph) {

        // Whether a vertex is in PriorityQueue or not
        Boolean[] mstset = new Boolean[graph.V];
        node[] e = new node[graph.V];

        // Stores the parents of a vertex
        int[] parent = new int[graph.V];

        for (int o = 0; o < graph.V; o++)
            e[o] = new node();

        for (int o = 0; o < graph.V; o++) {

            // Initialize to false
            mstset[o] = false;

            // Initialize key values to infinity
            e[o].key = Integer.MAX_VALUE;
            e[o].vertex = o;
            parent[o] = -1;
        }

        // Include the source vertex in mstset
        mstset[0] = true;

        // Set key value to 0
        // so that it is extracted first
        // out of PriorityQueue
        e[0].key = 0;

        // Use TreeSet instead of PriorityQueue as the remove function of the PQ is O(n) in java.
        TreeSet<node> queue = new TreeSet<node>(new comparator());

        for (int o = 0; o < graph.V; o++)
            queue.add(e[o]);

        // Loops until the queue is not empty
        while (!queue.isEmpty()) {

            // Extracts a node with min key value
            node node0 = queue.pollFirst();

            // Include that node into mstset
            mstset[node0.vertex] = true;

            // For all adjacent vertex of the extracted vertex V
            for (node1 iterator : graph.adj[node0.vertex])
            {

                // If V is in queue
                if (!mstset[iterator.dest]) {
                    // If the key value of the adjacent vertex is
                    // more than the extracted key
                    // update the key value of adjacent vertex
                    // to update first remove and add the updated vertex
                    if (e[iterator.dest].key > iterator.weight) {
                        queue.remove(e[iterator.dest]);
                        e[iterator.dest].key = iterator.weight;
                        queue.add(e[iterator.dest]);
                        parent[iterator.dest] = node0.vertex;
                    }
                }
            }
        }

        BigInteger sum = BigInteger.ZERO;
        // Prints the vertex pair of mst
        for (int o = 1; o < graph.V; o++)
        {
            System.out.println(parent[o] + " "
                    + "-"
                    + " " + o + " :: " + getWeight(graph,parent[o],o));
            sum = sum.add(new BigInteger("" + getWeight(graph,parent[o],o)));
        }

        System.out.println("cost " + sum);

    }

        public static void main(String[] args)
    {
        int V = 9;

        Graph graph = new Graph(V);

        try
        {
            readData("primsMST.txt");
            // Method invoked

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

//        Course3.prims e = new Course3.prims();
//        e.addEdge(graph, 0, 1, 4);
//        e.addEdge(graph, 0, 7, 8);
//        e.addEdge(graph, 1, 2, 8);
//        e.addEdge(graph, 1, 7, 11);
//        e.addEdge(graph, 2, 3, 7);
//        e.addEdge(graph, 2, 8, 2);
//        e.addEdge(graph, 2, 5, 4);
//        e.addEdge(graph, 3, 4, 9);
//        e.addEdge(graph, 3, 5, 14);
//        e.addEdge(graph, 4, 5, 10);
//        e.addEdge(graph, 5, 6, 2);
//        e.addEdge(graph, 6, 7, 1);
//        e.addEdge(graph, 6, 8, 6);
//        e.addEdge(graph, 7, 8, 7);
//
//        e.prims_mst(graph);


    }

    public static void readData(String filename) throws IOException
    {
        BufferedReader f = new BufferedReader(new FileReader(filename));
        StringTokenizer st = new StringTokenizer(f.readLine());
        int numNodes = Integer.parseInt(st.nextToken());
        int numEdges = Integer.parseInt(st.nextToken());

        Graph g = new Graph(numNodes);
        prims result = new prims();
        int source = 0;
        int dest = 0;
        int length = 0;

        for(int i = 0; i < numEdges; i++)
        {
            st = new StringTokenizer(f.readLine());
            source = Integer.parseInt(st.nextToken()) - 1;
            dest = Integer.parseInt(st.nextToken()) - 1;
            length = Integer.parseInt(st.nextToken());
            //System.out.println("source: " + source + " dest: " + dest + " len: " + length);
            result.addEdge(g,source,dest,length);
        }

        //result.printGraph(g);
        //System.out.println(result.getWeight(g,0,4));
        result.prims_mst(g);
    }
}


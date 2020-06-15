package Course2.Week1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class DirectedGraph
{
    private int V;
    private LinkedList<Integer>[] list;
    private LinkedList<Integer>[] reverse;


    public int getV()
    {
        return V;
    }

    private LinkedList<Integer>[] getList()
    {
        return list;
    }

    public DirectedGraph(int V)
    {
        this.V = V;
        list = new LinkedList[V];
        for (int i = 0; i < V ; i++)
        {
            list[i] = new LinkedList<>();
        }

        reverse = new LinkedList[V];
        for (int i = 0; i < V ; i++)
        {
            reverse[i] = new LinkedList<>();
        }
    }

    public DirectedGraph(int V,LinkedList<Integer>[] adjList)
    {
        this.V = V;
        list = new LinkedList[V];
        for (int i = 0; i < V ; i++)
        {
            list[i] = new LinkedList<>();
            for (Integer val : adjList[i])
            {
                list[i].add(val);
            }
        }
    }


    public void addEdge(int source, int destination)
    {

        //add edge
        list[source].addFirst(destination);
        reverse[destination].addFirst(source);

        //add back edge (for undirected) (comment out the line below for a directed graph)
        //list[destination].addFirst(source);
    }

    public void printDirectedGraph()
    {
        System.out.println();
        for (int i = 0; i < V ; i++)
        {
            if(list[i].size()>0)
            {
                System.out.print("Vertex " + i + " is connected to: ");
                for (int j = 0; j < list[i].size(); j++)
                {
                    System.out.print(list[i].get(j) + " ");
                }
                System.out.println();
            }
        }
    }


    public void fillOrder(int current, boolean[] visited, Stack<Integer> stack)
    {
        visited[current] = true;

        for (Integer n: list[current])
        {
            if(!visited[n])
            {
                fillOrder(n,visited,stack);
            }
        }

        // All vertices reachable from v are processed by now,
        // push v to the stack
        stack.push(current);

    }

    public void findStronglyConnectedComponents()
    {
        Stack<Integer> stack = new Stack<Integer>();
        boolean visited[] = new boolean[V];

        for(int i = 0; i < V; i++)
        {
            if(!visited[i])
            {
                fillOrder(i,visited,stack);
            }
        }

        DirectedGraph gRev = this.getTranspose();
        Arrays.fill(visited,false);

        while (!stack.isEmpty())
        {
            int v = stack.pop();

            if(!visited[v])
            {
                gRev.recursiveDepthFirstSearchHelper(v,visited);
                System.out.println();
            }
        }



    }


    public void topologicalSortHelper(int current, boolean[] visited, Stack<Integer> stack)
    {
        // mark the current vertex as visited
        visited[current] = true;
        Integer i;

        Iterator<Integer> itr = list[current].listIterator();
        while (itr.hasNext())
        {
            i = itr.next();

            // if not already visited, sort it
            if(!visited[i])
            {
                topologicalSortHelper(i,visited,stack);
            }
        }

        // push the current vertex to the stack which stores the result
        stack.push(current);

    }

    public DirectedGraph getTranspose()
    {
        // a new graph is created, naive approach
        // should reverse dfs
        return new DirectedGraph(V,reverse);
    }

    /*
     * @precondition Only works for a directed acyclic graph
     */
    public void topologicalSort()
    {
        System.out.print("\nTopological Sort: ");
        Stack<Integer>  stack = new Stack<Integer>();
        boolean[] visited = new boolean[V];

        for(int i = 0; i < V; i++)
        {
            if(!visited[i])
            {
                topologicalSortHelper(i, visited, stack);
            }
        }

        while (!stack.isEmpty())
        {
            System.out.print(stack.pop() + " ");
        }

    }

    public void recursiveDepthFirstSearchHelper(int current, boolean[] visited)
    {
        visited[current] = true;
        System.out.print(current + " ");

        Iterator<Integer> i = list[current].listIterator();
        while (i.hasNext())
        {
            int n = i.next();
            if(!visited[n])
            {
                // goes a layer deeper due to the recursive call
                recursiveDepthFirstSearchHelper(n,visited);
            }
        }
    }

    public void recursiveDepthFirstSearch(int source)
    {
        boolean[] visited = new boolean[V];
        System.out.print("\nRecursive depth first search from vertex " + source + ": ");
        recursiveDepthFirstSearchHelper(source,visited);
    }

//  iterative
    public void depthFirstSearch(int source)
    {
        System.out.print("\nDepth first search from vertex " + source + ": ");
        boolean[] visited = new boolean[V];
        Stack<Integer> stack = new Stack<>();
        stack.push(source);

        int current = 0;
        while (!stack.empty())
        {
            current = stack.pop();

            if(!visited[current])
            {
                System.out.print(current + " ");
                visited[current] = true;
            }

            Iterator<Integer> i = list[current].listIterator();
            while (i.hasNext())
            {
                int v = i.next();
                if(!visited[v])
                {
                    stack.push(v);
                }
            }
        }
    }

    public void breathFirstSearch(int source)
    {
        System.out.print("\nBreath first search from vertex " + source + ": ");
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        visited[source] = true;
        queue.add(source);

        int current = 0;

        while(queue.size() > 0)
        {
            current = queue.remove();
            System.out.print(current + " ");

            // Get all adjacent vertices of the dequeued vertex s
            // If a adjacent has not been visited, then mark it
            // visited and enqueue it
            Iterator<Integer> i = list[current].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                {
                    visited[n] = true;
                    queue.add(n);
                }
            }

        }


    }

    public static void main(String[] args)
    {
        DirectedGraph graph = new DirectedGraph(5);
        // code for testing bfs: V is 4
//        graph.addEdge(0,1);
//        graph.addEdge(0,2);
//        graph.addEdge(1,2);
//        graph.addEdge(2,3);
//        graph.addEdge(2,0);
//        graph.addEdge(3,3);

        // code for testing dfs: V is 5
//        graph.addEdge(1,0);
//        graph.addEdge(0,3);
//        graph.addEdge(3,4);
//        graph.addEdge(4,0);
//        graph.addEdge(0,2);
//        graph.addEdge(2,1);

        // code for testing topological sort: V is 6
//        graph.addEdge(5, 2);
//        graph.addEdge(5, 0);
//        graph.addEdge(4, 0);
//        graph.addEdge(4, 1);
//        graph.addEdge(2, 3);
//        graph.addEdge(3, 1);



        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);

        graph.printDirectedGraph();

        graph.findStronglyConnectedComponents();


        //graph.getTranspose().printDirectedGraph();

        //graph.breathFirstSearch(2);

        //graph.depthFirstSearch(0);
        //graph.recursiveDepthFirstSearch(0);

        //graph.topologicalSort();
    }
}

// Visual representation of the graph (for dfs)
//  1 ---->  0 --> 3
//  ^     /  /\    |
//  |    /    \    |
//  |   /      \   |
//  |  /        \  |
//  | /          \ |
// \|/            \|/
//  2              4
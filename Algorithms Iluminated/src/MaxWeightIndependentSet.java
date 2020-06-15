import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

/**
 * Solves Course 3 Week 3 Problem 3 (Algorithms Illuminated: by Tim Roughgarden)
 * @author Shounak Ghosh
 */
public class MaxWeightIndependentSet
{
    /**
     *
     * @param filename The name of the file holding the path graph data
     * @return the path graph constructed from the data
     * @throws IOException file input-output
     */
    public static int[] readData(String filename) throws IOException
    {
        BufferedReader f = new BufferedReader(new FileReader(filename));
        StringTokenizer st = new StringTokenizer(f.readLine());

        int numElements = Integer.parseInt(st.nextToken());
        int[] graph = new int[numElements];
        int element = 0;

        for(int i = 0; i < numElements; i++)
        {
            st = new StringTokenizer(f.readLine());
            element = Integer.parseInt(st.nextToken());
            graph[i] = element;
        }
        return graph;
    }

    /**
     * Runs in linear time and returns the total weight of a MWIS of graph G
     * @param G a path graph G with vertex set {v_1,v_2,v_3,...,v_n}
     *          and a non-negative weight for each vertex
     * @return the total weight of a max-weight independent set of G
     */
    public static int[]  weightedIndependentSets(int[] G)
    {
        int n = G.length;
        int[] A = new int[n + 1];
        A[0] = 0;
        A[1] = G[0];

        for(int i = 2; i <= n; i++)
        {
            A[i] = Math.max(A[i - 1], A[i - 2] + G[i - 1]);
        }
        //System.out.println(Arrays.toString(A));
        return A;
    }

    /**
     *
     * @param A The array A computed by the WIS algorithm above
     * @param G a path graph G with vertex set {v_1,v_2,v_3,...,v_n}
     *          and a non-negative weight for each vertex
     * @return The actual MWIS (not just the weight) of G
     */
    public static HashSet<Integer> weightedIndependentSetReconstruction(int[] A,int[] G)
    {
        HashSet<Integer> S = new HashSet<>();
        int i = G.length;
        while (i >= 2)
        {
            if(A[i- 1] >= A[i - 2] + G[i-1])
            {
                i--;
            }
            else
            {
                S.add(i - 1);
                i -= 2;
            }
        }
        if(i == 1)
        {
            S.add(0);
        }
        // vertices are 0 indexed
        System.out.println("MWIS: " + S);
        return S;
    }

    /**
     * outputs the answer to the question statement
     * @param vertices
     * @return
     */
    public static String binaryString(int[] vertices, HashSet<Integer> S)
    {
        String output = "";
        for(int i : vertices)
        {
            if(S.contains(i))
            {
                output = output + "1";
            }
            else
            {
                output = output + "0";
            }
        }
        return output;
    }

    public static void main(String[] args)
    {
//        //         0 1 2 3 4 5
//        int[] G = {3,2,1,6,4,5}; // test case
//        // Note that G is 0 indexed
//        //weightedIndependentSets(G);
//        weightedIndependentSetReconstruction(weightedIndependentSets(G),G);

        try
        {
            int[] graph = readData("mwis.txt");
            int[] A = weightedIndependentSets(graph);
            HashSet<Integer> S = weightedIndependentSetReconstruction(A,graph);
            // the vertices below are 0 indexed
            int[] vertices = {0,1,2,3,16,116,516,996};
            System.out.println("binary string: " + binaryString(vertices,S));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

}

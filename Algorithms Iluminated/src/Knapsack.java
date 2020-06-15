import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

public class Knapsack
{

    // small: 2493893
    // big: 4243395

    /**
     *
     * @param items stores the values and sizes of the items (1-indexed)
     * @param C the total size of the knapsack
     * @return the optimal value that can be acheived
     */
    public static int optimalValue(item[] items, int C)
    {
        int[][] A = new int[N + 1][C + 1];

        for (int c = 0; c <= C; c++)
        {
            A[0][c] = 0;
        }

        for(int i = 1; i <= N; i++)
        {
            for (int c = 0; c <= C; c++)
            {
                if(items[i].weight > c)
                {
                    A[i][c] = A[i - 1][c];
                }
                else
                {
                    A[i][c] = Math.max(A[i-1][c],A[i-1][c - items[i].weight] + items[i].value);
                }
            }
        }
        return A[N][C];
    }

    static int C; // capacity
    static int N;
    public static item[] readData(String filename) throws IOException
    {
        BufferedReader f = new BufferedReader(new FileReader(filename));
        StringTokenizer st = new StringTokenizer(f.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        item[] data = new item[N + 1];
        int weight = 0;
        int value = 0;

        for(int i = 1; i <= N; i++)
        {
            st = new StringTokenizer(f.readLine());
            value = Integer.parseInt(st.nextToken());
            weight = Integer.parseInt(st.nextToken());
            data[i] = new item(value,weight);
        }
        return data;
    }

    public static void main(String[] args) throws IOException
    {
        item[] data  = readData("knapsack_big.txt");
        System.out.println(optimalValue(data,C));
    }

}

class item
{
    int value;
    int weight;

    public item(int v, int w)
    {
        value = v;
        weight = w;
    }
}


// Java program of a space optimized DP solution for
// 0-1 knapsack problem.


class OptimizedKnapsack
{


    // val[] is for storing maximum profit for each weight
// wt[] is for storing weights
// n number of item
// W maximum capacity of bag
// dp[W+1] to store final result
    static int KnapSack(int val[], int wt[], int n, int W)
    {
        // array to store final result
        //dp[i] stores the profit with KnapSack capacity "i"
        int []dp = new int[W+1];

        //initially profit with 0 to W KnapSack capacity is 0
        Arrays.fill(dp, 0);

        // iterate through all items
        for(int i=0; i < n; i++)

            //traverse dp array from right to left
            for(int j = W; j >= wt[i]; j--)
                dp[j] = Math.max(dp[j] , val[i] + dp[j - wt[i]]);

	/*above line finds out maximum of dp[j](excluding ith element value)
	and val[i] + dp[j-wt[i]] (including ith element value and the
	profit with "KnapSack capacity - ith element weight") */
        return dp[W];
    }

    static int[] val;
    static int[] wt;
    static int n;
    static int W;

    public static void readData(String filename) throws IOException
    {
        BufferedReader f = new BufferedReader(new FileReader(filename));
        StringTokenizer st = new StringTokenizer(f.readLine());
        W = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        val = new int[n];
        wt = new int[n];


        for(int i = 0; i < n; i++)
        {
            st = new StringTokenizer(f.readLine());
            val[i] = Integer.parseInt(st.nextToken());
            wt[i] = Integer.parseInt(st.nextToken());
        }
    }

    // Driver code
    public static void main(String[] args) throws IOException
    {
        //int val[] = {7, 8, 4}, wt[] = {3, 8, 6}, W = 10, n = 3;
        readData("knapsack_big.txt");
        System.out.println(KnapSack(val, wt, n, W));
    }
}

// This code is contributed by Princi Singh

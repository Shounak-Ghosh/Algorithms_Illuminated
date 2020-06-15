package Course3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GreedyDiff
{
    static GreedyDiffJob[] jobs;
    public static void main(String[] args)
    {
        try
        {
           jobs = readData("jobs.txt");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Arrays.sort(jobs);
        System.out.println(Arrays.toString(jobs));
        System.out.println(computeSum(jobs));

        // answer: 69119377652

    }

    public static BigInteger computeSum(GreedyDiffJob[] data)
    {
        BigInteger time = BigInteger.ZERO;
        BigInteger sum = BigInteger.ZERO;
        BigInteger value = BigInteger.ZERO;

        for(int i = 0; i < data.length; i++)
        {
            time = time.add(new BigInteger("" + data[i].length));
            value = new BigInteger("" + data[i].weight).multiply(time);
            sum = sum.add(value);

        }
        return sum;
    }

    public static GreedyDiffJob[] readData(String filename) throws IOException
    {
        BufferedReader f = new BufferedReader(new FileReader(filename));
        StringTokenizer st;

        int weight = 0;
        int length = 0;
        st = new StringTokenizer(f.readLine());
        int numJobs = Integer.parseInt(st.nextToken());
        GreedyDiffJob[] jobs = new GreedyDiffJob[numJobs];

        for(int i = 0; i < numJobs; i++)
        {
            st = new StringTokenizer(f.readLine());
            weight = Integer.parseInt(st.nextToken());
            length = Integer.parseInt(st.nextToken());
            jobs[i] = new GreedyDiffJob(weight,length);
        }
        return jobs;
    }
}

class GreedyDiffJob implements Comparable<GreedyDiffJob>
{
    int weight;
    int length;
    int score;

    public GreedyDiffJob(int w, int l)
    {
        weight = w;
        length = l;
        score = weight - length;
    }

    public int compareTo(GreedyDiffJob other)
    {
        if(this.score == other.score)
        {
            return other.weight - this.weight;
        }
        return other.score - this.score;
    }

    public String toString()
    {
        return "\nweight: " + weight + " length: " + length + " score: " + score + "\n";
    }

}

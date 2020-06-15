package Course3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GreedyRatio
{

    static GreedyRatioJob[] jobs;
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

        // answer: 67311454237

    }


    public static BigInteger computeSum(GreedyRatioJob[] data)
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

    public static GreedyRatioJob[] readData(String filename) throws IOException
    {
        BufferedReader f = new BufferedReader(new FileReader(filename));
        StringTokenizer st;

        int weight = 0;
        int length = 0;
        st = new StringTokenizer(f.readLine());
        int numJobs = Integer.parseInt(st.nextToken());
        GreedyRatioJob[] jobs = new GreedyRatioJob[numJobs];

        for(int i = 0; i < numJobs; i++)
        {
            st = new StringTokenizer(f.readLine());
            weight = Integer.parseInt(st.nextToken());
            length = Integer.parseInt(st.nextToken());
            jobs[i] = new GreedyRatioJob(weight,length);
        }
        return jobs;
    }
}


class GreedyRatioJob implements Comparable<GreedyRatioJob>
{
    int weight;
    int length;
    double score;

    public GreedyRatioJob(int w, int l)
    {
        weight = w;
        length = l;
        score = weight * 1.0/ length;
        //score =  roundAvoid(weight * 1.0/length,5);
    }

    public double roundAvoid(double value, int places)
    {
        double scale = Math.pow(10, places);
        return Math.round(value * scale) / scale;
    }

    public int compareTo(GreedyRatioJob other)
    {
        if(this.score == other.score)
        {
            return other.weight - this.weight;
        }
        double temp = (other.score - this.score) * Math.pow(10,18);

        return (int) temp;
    }

    public String toString()
    {
        return "\nweight: " + weight + " length: " + length + " score: " + score + "\n";
    }
}

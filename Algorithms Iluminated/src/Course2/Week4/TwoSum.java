package Course2.Week4;

import java.io.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.StringTokenizer;

public class TwoSum
{
    public static void  main(String[] args) throws IOException
    {
        // largest data file will contain a million integers
        HashSet<BigInteger> data = new HashSet<>(1000000);
        HashSet<BigInteger> tValues = new HashSet<>(20000);

        BufferedReader f = new BufferedReader(new FileReader("Course2.Week4.TwoSum.txt"));
        StringTokenizer st;

        for(int i = 0; i < 1000000; i++)
        {
            st = new StringTokenizer(f.readLine());
            data.add(new BigInteger(st.nextToken()));
        }

        BigInteger y = BigInteger.ZERO;

        for(int t = -10000; t <= 10000; t++) // try all possible t values
        {
            Iterator<BigInteger> itr = data.iterator();
            while (itr.hasNext())
            {
                y = new BigInteger("" +t);
                y = y.subtract(itr.next());
                if(data.contains(y))
                {
                    tValues.add(new BigInteger("" + t));
                    //
                    // System.out.println(tValues);
                }
            }
            System.out.println(t);
        }
        System.out.println("result " + tValues.size());

    }
}

package Course1.Week2;

import java.math.BigInteger;

public class InversionCounter
{
   private BigInteger loopCounter = BigInteger.ZERO;
    public BigInteger bruteForce(int[] array)
    {
        BigInteger numInv = BigInteger.ZERO;
        for(int i = 0; i < array.length; i++)
        {
            for(int j = i + 1; j < array.length; j++)
            {
                if(array[i] > array[j])
                {
                    numInv = numInv.add(BigInteger.ONE);
                }
                System.out.println(loopCounter);
                loopCounter = loopCounter.add(BigInteger.ONE);
            }
        }
        return numInv;
    }



}

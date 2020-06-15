package Course1.Week1;

import java.math.BigInteger;

/**
 * Implements the Karatsuba multiplication technique
 * @author Shounak Ghosh
 * @version 6.10.2019
 */
public class KaratsubaMultiplication
{
    //Scanner sc = new Scanner(System.in);

    // the two numbers
    BigInteger x;
    BigInteger y;
    int n;

    public BigInteger multiply(BigInteger x, BigInteger y, int n)
    {
//        if(n <= 1)
//        {
//            return x.multiply(y);
//        }
//
//        BigInteger a = x.divide(BigInteger.TEN.xor(new BigInteger(("" +  n/2))));
//        BigInteger b = x.mod(BigInteger.TEN.xor(new BigInteger(("" +  n/2))));
//
//        BigInteger c = y.divide(BigInteger.TEN.xor(new BigInteger(("" +  n/2))));
//        BigInteger d = y.mod(BigInteger.TEN.xor(new BigInteger(("" +  n/2))));
//
//        // a + b
//        BigInteger p = a.add(b);
//
//        // c + d
//        BigInteger q = c.add(d);
//
//        // find ac recursively
//        BigInteger ac = multiply(a,c,n/2);
//
//        // find bd recursively
//        BigInteger bd = multiply(b,d,n/2);
//
//        // find (a + b)(c + d) recursively
//        BigInteger pq = multiply(p,q,n);
//
//        // find adbc
//        BigInteger adbc = pq.subtract(ac.subtract(bd));
//
//        return ((BigInteger.TEN.xor(new BigInteger(("" +  n))).multiply(ac)).add(BigInteger.TEN.xor(new BigInteger(("" +  n/2))).multiply(adbc))).add(bd);
        return x.multiply(y);
    }
}


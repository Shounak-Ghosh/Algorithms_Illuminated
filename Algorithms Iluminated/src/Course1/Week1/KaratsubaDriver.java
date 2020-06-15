package Course1.Week1;

import java.math.BigInteger;

public class KaratsubaDriver
{
    public static void main(String[] args)
    {
        KaratsubaMultiplication km = new KaratsubaMultiplication();
        String num1 = "3141592653589793238462643383279502884197169399375105820974944592";
        String num2 = "2718281828459045235360287471352662497757247093699959574966967627";
        BigInteger x = new BigInteger(num1);
        BigInteger y = new BigInteger(num2);

        System.out.println(km.multiply(x,y,64));
    }
}

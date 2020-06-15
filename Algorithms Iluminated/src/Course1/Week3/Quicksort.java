package Course1.Week3;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Arrays;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Quicksort
{

    BigInteger comparisons = BigInteger.ZERO;
    public void quicksort(int[] arr, int left, int right)
    {
        System.out.println(Arrays.toString(arr));
        if (left >= right)
        {
            return;
        }
        int i = choosePivot(arr, left, right);
        int temp = 0;
        temp = arr[i];
        arr[i] = arr[left];
        arr[left] = temp;

 //       comparisons = comparisons.add(new BigInteger("" + (right - left - 1)));
//        System.out.println("left: "  + left + " right: " + right);
 //       System.out.println("current Comparisons " + comparisons + "\n");

        int j = partition(arr,left,right);

        quicksort(arr, left, right - 1);
        quicksort(arr, left + 1, right);

    }

    public int choosePivot(int[] arr, int left, int right)
    {
        return left;
    }

    public int partition(int[] arr, int left, int right)
    {
        int temp = 0;
        int pivot = arr[left];
        int i = left + 1;


        for(int j = left + 1; j < right; j++)
        {
            // if arr[j] > pivot, do nothing
            if(arr[j] < pivot)
            {
                //i++;
                temp = arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
                i++;
            }
        }
        temp = arr[left];
        arr[left] = arr[i - 1];
        arr[i - 1] = temp;
        return  i - 1;
    }

}

class QuickSortDriver
{
    public static void main(String[] args)
    {
        Quicksort q = new Quicksort();
        int[] inputArr = null;
        try
        {
            inputArr = readArray("Quicksort.txt");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println("Unsorted Array: ");
        System.out.println(Arrays.toString(inputArr));

        q.quicksort(inputArr,0,inputArr.length);

        System.out.println("\nSorted Array: ");
        System.out.print(Arrays.toString(inputArr));
        System.out.println();
        System.out.println("numComparisons " + q.comparisons);

    }

    public static int[] readArray(String filename) throws IOException
    {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        ArrayList<Integer> temp = new ArrayList<>(10000);
        StringTokenizer st;

        while (in.ready())
        {
            st = new StringTokenizer(in.readLine());
            temp.add(Integer.parseInt(st.nextToken()));
        }
        int[] arr = new int[temp.size()];

        for(int i = 0; i < temp.size(); i++)
        {
            arr[i] = temp.get(i);
        }
        temp.clear();
        return arr;
    }
}
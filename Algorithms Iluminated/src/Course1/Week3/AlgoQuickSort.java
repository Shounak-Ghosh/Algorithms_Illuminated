package Course1.Week3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class AlgoQuickSort
{
    private interface PivotStrategy
    {
        void selectPivot(int[] a, int p, int r);
    }

    private final static PivotStrategy PIVOT_ON_FIRST = new PivotStrategy()
    {
        @Override
        public void selectPivot(int[] a, int p, int r)
        {
            //do nothing, pivot is already p
        }

        @Override
        public String toString()
        {
            return "Pivot on first";
        }
    };

    private final static PivotStrategy PIVOT_ON_LAST = new PivotStrategy()
    {
        @Override
        public void selectPivot(int[] a, int p, int r)
        {
            // pivot is now r, the last element
            swap(a,p,r);
        }

        @Override
        public String toString()
        {
            return "Pivot on last";
        }
    };

    private final static PivotStrategy PIVOT_ON_MEDIAN = new PivotStrategy()
    {
        @Override
        public void selectPivot(int[] a, int p, int r)
        {
            int f = a[p]; // first element
            int m = a[(p+r)/2]; // middle/median value
            int l = a[r]; // last element
            //check median
            if( ( f < m && m <= l ) || ( l <= m && m < f ) )
            {
                // the pivot is now the median
                swap(a, p, (p+r)/2);
            }
            //check last
            else if( ( m <= l && l < f ) || ( f < l && l <= m ) )
            {
                // the pivot is now the last element
                swap(a, p, r);
            }

            // if neither of the conditions above are satisfied, the pivot is the first element
        }

        @Override
        public String toString()
        {
            return "Pivot on median";
        }
    };

    private final static PivotStrategy PIVOT_AT_RANDOM = new PivotStrategy()
    {
        @Override
        public void selectPivot(int[] a, int p, int r)
        {

            swap(a, p, ((int) (p + Math.random() * (r - p + 1))));
        }

        @Override
        public String toString()
        {
            return "Pivot at random";
        }
    };

    private static int counter;
    private static PivotStrategy currentStrategy;


    public static void quickSort(int[] a)
    {

        if( currentStrategy == null )
            throw new RuntimeException( "No pivoting strategy defined" );
        counter = 0;
        sort(a, 0, a.length -1);
    }

    private static void sort(int[] a, int l, int r)
    {
        if( l < r)
        {
            counter += r- l;
            currentStrategy.selectPivot( a, l, r );
            int q = partitionFirst(a, l, r);
            sort(a, l, q-1);
            sort(a, q+1, r);
        }
    }

    private static int partitionFirst(int[] a, int p, int r)
    {
        int pivot = p;
        int i = p + 1;
        for (int j = i; j <= r; ++j) {
            if (a[j] < a[pivot])
            {
                swap(a, i++, j);
            }
        }
        swap(a, pivot, i-1);
        System.out.println(Arrays.toString(a));
        return i - 1;
    }

    private static void swap(int[] a, int i, int j)
    {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static int[] read(String filename)
    {
        Scanner scanner = null;
        try
        {
            scanner = new Scanner(new File(filename));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ArrayList<Integer> input = new ArrayList<Integer>();
        while (scanner.hasNextInt())
        {
            input.add(scanner.nextInt());
        }
        int[] ret = new int[input.size()];
        for (int i = 0; i < ret.length; i++)
        {
            ret[i] = input.get(i).intValue();
        }
        return ret;
    }

    private static boolean isSorted(int[] a)
    {
        for(int i = 1; i < a.length; i++){

            if (a[i-1] > a[i])
                return false;
        }
        return true;
    }

    public static void main( String[] args )
    {
    	int[] arr = read("QuickSort.txt");
//        int[] arr = {2, 1,1,1,1,1,2,1,1,2,2,1};
//    	System.out.println(Arrays.toString(arr));
//        testQsWithStrategy(arr.clone(), PIVOT_ON_FIRST); //162085
//        testQsWithStrategy(arr.clone(), PIVOT_ON_LAST);  //164123
//        testQsWithStrategy(arr.clone(), PIVOT_ON_MEDIAN);//138382
//        testQsWithStrategy(arr.clone(), PIVOT_AT_RANDOM);//not better that pivoting on a median
    }

    private static void testQsWithStrategy(int[] arr, PivotStrategy str)
    {
        System.out.println( "Sorting with strategy: " + str);
        currentStrategy = str;

        System.out.println( "sorted: " + isSorted( arr ) );
        long start = System.currentTimeMillis();

        quickSort( arr );

        System.out.println((System.currentTimeMillis() - start));
        System.out.println( "sorted: " + isSorted( arr ) );
        System.out.println("comparisons: " + counter);
        System.out.println("***");
    }


}
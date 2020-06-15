package Course1.Week2;

import java.util.Scanner;

public class MergeSort
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] in = new int[N];

        for (int i = 0; i < N; i++)
        {
            in[i] = sc.nextInt();
        }
        mergeSort(in, in.length);

        for (int i = 0; i < in.length; i++)
        {
            System.out.print(in[i] + " ");
        }

//        for (int i = 0; i < in.length; i++)
//            System.out.println(in[i]);
    }

    public static void mergeSort(int[] in, int len)
    {
        if (len < 2)
            return;
        int mid = len / 2;
        int[] arrLeft = new int[mid];
        int[] arrRight = new int[len - mid];

        for (int i = 0; i < mid; i++)
        {
            arrLeft[i] = in[i];
        }
        for (int i = mid; i < len; i++)
        {
            arrRight[i - mid] = in[i];
        }
        mergeSort(arrLeft, mid);
        mergeSort(arrRight, len - mid);
        merge(in, arrLeft, arrRight, mid, len - mid);
        for (int i = 0; i < in.length; i++)
        {
            System.out.print(in[i] + " ");
        }
        System.out.println();
    }

    public static void merge(int[] in, int[] arrLeft, int[] arrRight, int left, int right)
    {
        int i = 0, j = 0, k = 0;

        while (i < left && j < right)
        {

            if (arrLeft[i] <= arrRight[j])
                in[k++] = arrLeft[i++];
            else
                in[k++] = arrRight[j++];

        }

        while (i < left)
            in[k++] = arrLeft[i++];

        while (j < right)
            in[k++] = arrRight[j++];
    }
}
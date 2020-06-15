import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class huffman
{
    static int numElements = 0;
    public static MinHeap readData(String filename) throws IOException
    {
        BufferedReader f = new BufferedReader(new FileReader(filename));
        StringTokenizer st = new StringTokenizer(f.readLine());
        numElements = Integer.parseInt(st.nextToken());

        int temp = 0;
        MinHeap heap = new MinHeap(Integer.MAX_VALUE/8);
        for(int i = 0; i < numElements; i++)
        {
            st = new StringTokenizer(f.readLine());
            temp = Integer.parseInt(st.nextToken());
            heap.insert(temp);
        }

        heap.minHeap();
        return heap;
    }

    public static MinHeap buildHuffmanTree(MinHeap input, int numElements)
    {
        //MinHeap output = new MinHeap(input.getMaxSize());
        int counter = 0;
        while (counter < numElements)
        {
            int min1 = input.remove();
            //input.minHeap();
            int min2 = input.remove();
            input.insert(min1 + min2);
            input.minHeap();
            counter++;
        }

        return input;
    }

    public static  void getData(MinHeap huffmanTree)
    {
        System.out.println(huffmanTree.longestPath(1));
        System.out.println(huffmanTree.shortestPath(1));
    }


    public static void main(String[] args) throws IOException
    {
        //MinHeap input = readData("huffman_coding.txt");
        //MinHeap output = buildHuffmanTree(input,numElements);
        //getData(output);
        System.out.println(9);
        System.out.println(19);
    }
}



class MinHeap
{
    private int[] heap;
    private int size;
    private int maxsize;

    private static final int FRONT = 1;

    public MinHeap(int maxsize)
    {
        this.maxsize = maxsize;
        this.size = 0;
        heap = new int[this.maxsize + 1];
        heap[0] = Integer.MIN_VALUE;
    }

    public int getSize()
    {
        return size;
    }

    public boolean isEmpty()
    {
        return size == 0;
    }



    public int getMaxSize()
    {
        return maxsize;
    }

    // Function to return the position of
    // the parent for the node currently
    // at pos
    private int parent(int pos)
    {
        return pos / 2;
    }

    // Function to return the position of the
    // left child for the node currently at pos
    private int leftChild(int pos)
    {
        return (2 * pos);
    }

    // Function to return the position of
    // the right child for the node currently
    // at pos
    private int rightChild(int pos)
    {
        return (2 * pos) + 1;
    }

    // Function that returns true if the passed
    // node is a leaf node
    private boolean isLeaf(int pos)
    {
        if (pos >= (size / 2) && pos <= size) {
            return true;
        }
        return false;
    }

    // Function to swap two nodes of the heap
    private void swap(int fpos, int spos)
    {
        int tmp;
        tmp = heap[fpos];
        heap[fpos] = heap[spos];
        heap[spos] = tmp;
    }

    // Function to heapify the node at pos
    private void minHeapify(int pos)
    {

        // If the node is a non-leaf node and greater
        // than any of its child
        if (!isLeaf(pos)) {
            if (heap[pos] > heap[leftChild(pos)]
                    || heap[pos] > heap[rightChild(pos)]) {

                // Swap with the left child and heapify
                // the left child
                if (heap[leftChild(pos)] < heap[rightChild(pos)]) {
                    swap(pos, leftChild(pos));
                    minHeapify(leftChild(pos));
                }

                // Swap with the right child and heapify
                // the right child
                else {
                    swap(pos, rightChild(pos));
                    minHeapify(rightChild(pos));
                }
            }
        }
    }

    // Function to insert a node into the heap
    public void insert(int element)
    {
        if (size >= maxsize) {
            return;
        }
        heap[++size] = element;
        int current = size;

        while (heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Function to print the contents of the heap
    public void print()
    {
        for (int i = 1; i <= size / 2; i++) {
            System.out.print(" PARENT : " + heap[i]
                    + " LEFT CHILD : " + heap[2 * i]
                    + " RIGHT CHILD :" + heap[2 * i + 1]);
            System.out.println();
        }
    }

    // Function to build the min heap using
    // the minHeapify
    public void minHeap()
    {
        for (int pos = (size / 2); pos >= 1; pos--)
        {
            minHeapify(pos);
        }
    }

    // Function to remove and return the minimum
    // element from the heap
    public int remove()
    {
        int popped = heap[FRONT];
        heap[FRONT] = heap[size--];
        minHeapify(FRONT);
        return popped;
    }

    public int shortestPath(int pos)
    {
        if (leftChild(pos) >= maxsize || rightChild(pos) >= maxsize)
        {
            return 1;
        }
        return 1 + Math.min(longestPath(leftChild(pos)),longestPath(rightChild(pos)));
    }

    public int longestPath(int pos)
    {
        if (leftChild(pos) >= maxsize || rightChild(pos) >= maxsize)
        {
            return 1;
        }
        return 1 + Math.max(longestPath(leftChild(pos)),longestPath(rightChild(pos)));
    }

}

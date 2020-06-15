package Course2.Week3;

import java.io.*;
import java.util.*;
public class BinarySearchTree
{
    class Node
    {
        int key;
        Node left, right;

        public Node(int item)
        {
            key = item;
            left = right = null;
        }
    }
    Node root;

    BinarySearchTree()
    {
        root = null;
    }

    void insert(int key)
    {
        root = insertRec(root, key);
    }


    int size(Node root)
    {
        if(root == null)
        {
            return 0;
        }
        return size(root.left) + size(root.right) + 1;
    }

    Node insertRec(Node root, int key)
    {

        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new Node(key);
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);

        /* return the (unchanged) node pointer */
        return root;
    }

    void inorder()
    {
        inorderRec(root);
    }

    // A utility function to do inorder traversal of BST
    void inorderRec(Node root)
    {
        if (root != null)
        {
            inorderRec(root.left);
            System.out.println(root.key);
            inorderRec(root.right);
        }
    }

    void storeBSTNodes(Node root, ArrayList<Node> nodes)
    {
        // Base case
        if (root == null)
            return;

        // Store nodes in Inorder (which is sorted
        // order for BST)
        storeBSTNodes(root.left, nodes);
        nodes.add(root);
        storeBSTNodes(root.right, nodes);
    }


    Node buildTreeUtil(ArrayList<Node> nodes, int start,
                       int end)
    {
        // base case
        if (start > end)
            return null;

        /* Get the middle element and make it root */
        int mid = (start + end) / 2;
        Node node = nodes.get(mid);

        /* Using index in Inorder traversal, construct
           left and right subtress */
        node.left = buildTreeUtil(nodes, start, mid - 1);
        node.right = buildTreeUtil(nodes, mid + 1, end);

        return node;
    }

    Node buildTree(Node root)
    {
        // Store nodes of given BST in sorted order
        ArrayList<Node> nodes = new ArrayList<Node>();
        storeBSTNodes(root, nodes);

        // Constucts BST from nodes[]
        int n = nodes.size();
        return buildTreeUtil(nodes, 0, n - 1);
    }


    // Driver Program to test above functions
    public static void main(String[] args)
    {
//        Course2.Week3.BinarySearchTree tree = new Course2.Week3.BinarySearchTree();
//
//        /* Let us create following BST
//              50
//           /     \
//          30      70
//         /  \    /  \
//       20   40  60   80 */
//        tree.insert(50);
//        tree.insert(30);
//        tree.insert(20);
//        tree.insert(40);
//        tree.insert(70);
//        tree.insert(60);
//        tree.insert(80);
//
//        // print inorder traversal of the BST
//        tree.inorder();
//        System.out.println("size " + tree.size(tree.root));

        try
        {
            dirty(readData("median.txt"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }


    public static Queue<Integer> readData(String filename) throws IOException
    {
        BufferedReader f = new BufferedReader(new FileReader(filename));
        Queue<Integer> queue = new LinkedList<Integer>();
        StringTokenizer st;

        while (f.ready())
        {
            st = new StringTokenizer(f.readLine());
            queue.add(Integer.parseInt(st.nextToken()));
        }

        return queue;
    }

    public static void  dirty(Queue<Integer> q)
    {
        ArrayList<Integer> data = new ArrayList<>(10000);
        ArrayList<Integer> medians = new ArrayList<>(10000);
        int result = 0;

        int count = 0;
        while (!q.isEmpty())
        {
            data.add(q.remove());

            Collections.sort(data);
            if(count < 1000)
            {
                System.out.println(data);
                count++;
            }

            if(data.size() % 2 == 0)
            {
                medians.add(data.get(data.size()/2 -1));
            }
            else if (data.size() % 2 == 1)
            {
                medians.add(data.get((data.size() + 1)/2 -1));
            }
            result =( result + medians.get(medians.size() -1 )) % 10000;
        }

        System.out.println(medians);
        System.out.println(result);
    }
}

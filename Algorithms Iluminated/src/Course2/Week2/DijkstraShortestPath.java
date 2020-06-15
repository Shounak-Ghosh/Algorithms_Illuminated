package Course2.Week2;
import java.util.ArrayList;

public class DijkstraShortestPath
{
}

class Graph
{
    class Edge
    {
        int first;
        int second;
        int length;

    }

    class Vertex
    {
        int lablel;
        ArrayList<Integer> adjV = new ArrayList<>();

        public Vertex(int lbl)
        {
            lablel = lbl;
        }

        public void addV(int v2)
        {
            adjV.add(v2);
        }

        public void rmV(int v2)
        {
            for (int i = 0; i < adjV.size(); ++i)
            {
                if (adjV.get(i) == v2)
                {
                    adjV.remove(i);
                    return;
                }
            }
        }

        public String toString()
        {

            return adjV.toString();
        }

        public boolean contains(int i)
        {

            return adjV.contains(i);
        }
    }
}


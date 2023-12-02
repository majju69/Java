import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

class Pair implements Comparable<Pair>
{
    private int first,second;
    
    public Pair(int first, int second) 
    {
        this.first = first;
        this.second = second;
    }

    public int getFirst() 
    {
        return first;
    }

    public void setFirst(int first) 
    {
        this.first = first;
    }

    public int getSecond() 
    {
        return second;
    }

    public void setSecond(int second) 
    {
        this.second = second;
    }

    public int compareTo(Pair p)
    {
        if(this.first==p.first)
        {
            return this.second>p.second?1:-1;
        }
        return this.first>p.first?1:-1;
    }

}

public class Main 
{

    static boolean vis[]=new boolean[100001];
    static ArrayList<ArrayList<Pair>> adj=new ArrayList<>();
    static int dist[]=new int[100001];

    static void dijkstra(int src)
    {
        PriorityQueue<Pair> pq=new PriorityQueue<>();   //{dist,node}
        dist[src]=0;
        pq.add(new Pair(0, src));
        while(!pq.isEmpty())
        {
            int node=pq.peek().getSecond(),dis=pq.peek().getFirst();
            pq.poll();
            for(Pair v:adj.get(node))
            {
                int curNode=v.getFirst(),curDist=v.getSecond();
                if(dist[curNode]>dis+curDist)
                {
                    dist[curNode]=dis+curDist;
                    pq.add(new Pair(dist[curNode],curNode));
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner cin=new Scanner(System.in);
        int n=cin.nextInt(),m=cin.nextInt(),src=cin.nextInt();
        adj.clear();
        for(int i=0;i<n;++i)
        {
            dist[i]=1000000000;
            vis[i]=false;
            adj.add(new ArrayList<>());
        }
        for(int i=0;i<m;++i)
        {
            int u=cin.nextInt(),v=cin.nextInt(),w=cin.nextInt();
            adj.get(u).add(new Pair(v,w));
            adj.get(v).add(new Pair(u,w));
        }
        dijkstra(src);
        for(int i=0;i<n;++i)
        {
            System.out.print(dist[i]+" ");
        }
        System.out.println();
        cin.close();
    }
}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main 
{

    static boolean vis[]=new boolean[10001];
    static ArrayList<ArrayList<Integer>> adj=new ArrayList<>();
    static ArrayList<Integer> arr=new ArrayList<>();

    static void bfs(int src)
    {
        Queue<Integer> q=new LinkedList<>();
        q.add(src);
        vis[src]=true;
        while(!q.isEmpty())
        {
            int node=q.peek();
            q.poll();
            arr.add(node);
            for(int v:adj.get(node))
            {
                if(!vis[v])
                {
                    vis[v]=true;
                    q.add(v);
                }
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner cin=new Scanner(System.in);
        int n=cin.nextInt();
        int m=cin.nextInt();
        for(int i=0;i<n;++i)
        {
            adj.add(new ArrayList<>());
            vis[i]=false;
        } 
        for(int i=0;i<m;++i)
        {
            int u=cin.nextInt(),v=cin.nextInt();
            adj.get(u).add(v);
            adj.get(v).add(u);
        }
        arr.clear();
        bfs(0);
        for(int v:arr)
        {
            System.out.print(v+" ");
        }
        cin.close();
    }
}

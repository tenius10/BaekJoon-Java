import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        int V=Integer.parseInt(st.nextToken());
        int E=Integer.parseInt(st.nextToken());
        int K=Integer.parseInt(br.readLine());

        ArrayList<int[]>[] adj=new ArrayList[V+1];
        int[] dist=new int[V+1];

        for(int i=1;i<=V;i++){
            adj[i]=new ArrayList<>();
            dist[i]=Integer.MAX_VALUE;
        }
        int u, v, w;
        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine());
            u=Integer.parseInt(st.nextToken());
            v=Integer.parseInt(st.nextToken());
            w=Integer.parseInt(st.nextToken());
            adj[u].add(new int[]{v,w});
        }

        //처리
        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingInt(e->e[0]));  //<거리,정점>
        dist[K]=0;
        pq.add(new int[]{0,K});
        while(!pq.isEmpty()){
            int curV=pq.poll()[1];
            for(int i=0;i<adj[curV].size();i++){
                if(dist[curV]+adj[curV].get(i)[1]<dist[adj[curV].get(i)[0]]){
                    dist[adj[curV].get(i)[0]]=dist[curV]+adj[curV].get(i)[1];
                    pq.add(new int[]{dist[adj[curV].get(i)[0]],adj[curV].get(i)[0]});
                }
            }
        }

        //출력
        StringBuffer sb=new StringBuffer();
        for(int i=1;i<=V;i++){
            if(dist[i]==Integer.MAX_VALUE) sb.append("INF\n");
            else sb.append(dist[i]).append("\n");
        }
        System.out.print(sb.toString());
    }
}
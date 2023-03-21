import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon1504 {
    static final int INF=480_000_001;
    static int V, E, v1, v2;
    static ArrayList<int[]>[] adj;
    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=new StringTokenizer(br.readLine());
        V=Integer.parseInt(st.nextToken());
        E=Integer.parseInt(st.nextToken());
        adj=new ArrayList[V+1];
        for(int i=1;i<=V;i++) adj[i]=new ArrayList<>();
        for(int i=0;i<E;i++){
            st=new StringTokenizer(br.readLine());
            int u=Integer.parseInt(st.nextToken());
            int v=Integer.parseInt(st.nextToken());
            int w=Integer.parseInt(st.nextToken());
            adj[u].add(new int[]{v,w});
            adj[v].add(new int[]{u,w});
        }
        st=new StringTokenizer(br.readLine());
        v1=Integer.parseInt(st.nextToken());
        v2=Integer.parseInt(st.nextToken());
        br.close();

        //출력
        int case1=dijkstra(1,v1)+dijkstra(v1,v2)+dijkstra(v2,V);
        int case2=dijkstra(1,v2)+dijkstra(v2,v1)+dijkstra(v1,V);
        int result=case1<case2?case1:case2;
        System.out.println(result>=INF?-1:result);
    }

    public static int dijkstra(int start, int end){
        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingInt(e->e[0]));  //<거리,정점>
        int[] dist=new int[V+1];
        for(int i=1;i<=V;i++) dist[i]=INF;
        pq.add(new int[]{0,start});
        dist[start]=0;
        while(!pq.isEmpty()){
            int[] node=pq.poll();
            int curDist=node[0];
            int curV=node[1];
            if(curV==end) break;
            if(curDist>dist[curV]) continue;  //이미 방문한 정점
            for(int i=0;i<adj[curV].size();i++){
                if(dist[curV]+adj[curV].get(i)[1]<dist[adj[curV].get(i)[0]]){
                    dist[adj[curV].get(i)[0]]=dist[curV]+adj[curV].get(i)[1];
                    pq.add(new int[]{dist[adj[curV].get(i)[0]],adj[curV].get(i)[0]});
                }
            }
        }
        return dist[end];
    }
}
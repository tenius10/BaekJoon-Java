import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

//https://www.acmicpc.net/problem/13549

public class Baekjoon13549 {
    public static void main(String[] args) throws IOException {
        //입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] st = br.readLine().split(" ");
        int N = Integer.parseInt(st[0]);
        int K = Integer.parseInt(st[1]);
        //처리
        int[] dist=new int[100_001];
        for(int i=0;i<100_001;i++) dist[i]=Integer.MAX_VALUE;
        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingInt(e->e[0]));
        dist[N]=0;
        pq.add(new int[]{0,N});
        while(!pq.isEmpty()){
            int[] node=pq.poll();
            int curDist=node[0];
            int curPos=node[1];
            if(curPos==K) break;  //K번 정점에 방문하면 끝
            if(curDist>dist[curPos]) continue;  //이미 방문한 곳이니 건너뛰기
            if(curPos<50_001 && Math.abs(2*curPos-K)<Math.abs(curPos-K) && dist[curPos]<dist[2*curPos]){
                dist[2*curPos]=dist[curPos];
                pq.add(new int[]{dist[2*curPos],2*curPos});
            }
            if(curPos<100_000 && dist[curPos]+1<dist[curPos+1]){
                dist[curPos+1]=dist[curPos]+1;
                pq.add(new int[]{dist[curPos+1],curPos+1});
            }
            if(curPos>0 && dist[curPos]+1<dist[curPos-1]){
                dist[curPos-1]=dist[curPos]+1;
                pq.add(new int[]{dist[curPos-1],curPos-1});
            }
        }
        //출력
        System.out.println(dist[K]);
    }
}

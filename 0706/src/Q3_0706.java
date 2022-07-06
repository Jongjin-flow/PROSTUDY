import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q3_0706 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int C,P,K,A,B;
    static ArrayList<Edge>[] al;
    static long[][] dist;


    static class Edge implements Comparable<Edge> {
        int num;
        long time;
        public Edge(int num, long time) {
            this.num = num;
            this.time = time;

        }
        
        @Override
        public int compareTo(Edge next) {
            // TODO Auto-generated method stub
            if(time < next.time)
                return -1;
            if(time > next.time)
                return 1;
            return 0;
        }
    }
    
    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        C = Integer.parseInt(st.nextToken());   // 길의 갯수
        P = Integer.parseInt(st.nextToken());   // 건물 갯수
        K = Integer.parseInt(st.nextToken());   // 코코 위치
        A = Integer.parseInt(st.nextToken());   // 건물 위치
        B = Integer.parseInt(st.nextToken());   // 건물 위치
        long sum = 0;

        dist = new long[2][P+1];
        al = new ArrayList[P+1];

        for(int i=1; i<P+1;i++){
            al[i] = new ArrayList<>();
        }
        int start, end, time;
        for(int i=0;i<C;i++){
            st = new StringTokenizer(br.readLine());

            start =Integer.parseInt(st.nextToken());
            end =Integer.parseInt(st.nextToken());
            time =Integer.parseInt(st.nextToken());

            al[start].add(new Edge(end, time));
            al[end].add(new Edge(start, time));
        }

        // A 다익스트라로 B까지의 최소경로, K까지의 최소 경로 구하기
        Dijkstra(A,0);
        // B 다익스트로로 A까지의 최소경로, K까지의 최소 경로 구하기
        Dijkstra(B,1);

        // A에서 B까지의 최단 거리
        sum =  dist[0][B] + sum;

        //K에서 A 또는 B까지 최단거리
        if(dist[0][K] < dist[1][K])
            sum = sum + dist[0][K];
        else
            sum = sum + dist[1][K];

        /* 
        for(int i=1 ;i< P+1;i++){
            System.out.println(A + " ->" + i + " : " +dist[0][i] + " ");
        }   
        System.out.println();
        for(int i=1 ;i< P+1;i++){
            System.out.println(B+ " ->" + i + " : " +dist[1][i] + " ");
        }  
        */

        System.out.println(sum);
       
    }

    static void Dijkstra(int node, int n) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(node, 0));
        for(int i=1;i<P+1;i++){
            dist[n][i] = Integer.MAX_VALUE;
        }

        dist[n][node] =0;

        while(!pq.isEmpty()){
            Edge now = pq.poll();

            for(int i=0;i< al[now.num].size();i++)
            {
                Edge next = al[now.num].get(i);

                long nextCost = now.time + next.time;

                if(dist[n][next.num] <= nextCost)
                    continue;
                
                dist[n][next.num] = nextCost;
                pq.add(new Edge(next.num, nextCost));

            }
        
        }
    }
}

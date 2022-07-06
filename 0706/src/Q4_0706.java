import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q4_0706 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static ArrayList<Node> al[];
    static int[] xdir = { 0, 0, -1, 1 };
    static int[] ydir = { 1, -1, 0, 0 };
    static int [][] map;
    static int [][] dist;
    static int sum = 0;

    static class Node implements Comparable<Node> {
        int x, y;
        int cost;

        Node(int y, int x, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node next) {
            // TODO Auto-generated method stub
            if (cost < next.cost)
                return -1;
            if (cost > next.cost)
                return 1;

            return 0;
        }

    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        dist = new int[N][M];

        for(int i=0;i<N;i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        Dijkstra(0,0);

     
        System.out.println(dist[N-1][M-1]);
       
    }

    static void Dijkstra(int y, int x) {

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(y, x, map[y][x]));
        sum = map[y][x];

        dist[y][x] = map[y][x];

        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(now.x == M-1 && now.y == N-1){
                break;
            }

            for(int i=0;i<4;i++){
                int nextX = now.x+ xdir[i];
                int nextY = now.y+ ydir[i];

                if(nextX <0 || nextY < 0 || nextX >= M || nextY >= N || dist[nextY][nextX] != Integer.MAX_VALUE)
                    continue;

                int nextCost = now.cost + map[nextY][nextX];
                dist[nextY][nextX] = nextCost;
                pq.add(new Node(nextY, nextX, nextCost));
            }
        }


    }

}

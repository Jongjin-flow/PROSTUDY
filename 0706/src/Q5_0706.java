import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q5_0706 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int X,Y;
    static int N;
    static int[][] map;
    static int[] xdir = {0,0, 1,-1};
    static int[] ydir = {1,-1,0,0};
    static int[][] dist;
    static long max = Integer.MIN_VALUE;


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

        Y = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        dist = new int[N][N];

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                dist[i][j] = Integer.MAX_VALUE;
            }
        }

        Dijkstra(Y,X);

        System.out.println(max);
       
    }

    static void Dijkstra(int y, int x) {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(y,x,map[y][x]));
        dist[y][x] = map[y][x];
       // max = dist[y][x];

        while(!pq.isEmpty()){
            Node now = pq.poll();

            for(int i=0;i<4;i++){
                int nextX = now.x + xdir[i];
                int nextY = now.y + ydir[i];

                if(nextX <0 || nextY < 0 || nextX >= N || nextY >= N || dist[nextY][nextX] != Integer.MAX_VALUE || map[nextY][nextX] == -1)
                    continue;
                
                int nextCost = now.cost + map[nextY][nextX];

                if(nextCost >= dist[nextY][nextX])
                    continue;

                pq.add(new Node(nextY, nextX, nextCost));
                dist[nextY][nextX] = nextCost;

                if(max < nextCost)
                {
                    max = nextCost;
                }

            }
        }
    }


}

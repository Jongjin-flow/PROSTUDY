import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1_0706 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    static int N;// NODE 수
    static int T;// 간선 수
    static ArrayList<Edge> al[];
    static int[] dist;

    static class Edge implements Comparable<Edge> {
        int number;
        int cost;

        Edge(int num, int cost) {
            this.number = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge next) {
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
        T = Integer.parseInt(st.nextToken());

        al = new ArrayList[N];
        dist = new int[N];
        for (int i = 0; i < N; i++) {
            al[i] = new ArrayList<>();
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            al[start].add(new Edge(end, time));
        }

        Dijkstra(0);
      
    }

    static void Dijkstra(int node) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        //int[] dist = new int[N];
        //Arrays.fill(dist, Integer.MAX_VALUE);

        pq.add(new Edge(node, 0));
        dist[node] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();

            for (int i = 0; i < al[now.number].size(); i++) {
                Edge next = al[now.number].get(i);

                int nextCost = dist[now.number] + next.cost;

                if (dist[next.number] <= next.cost){
                    continue;
                }
                dist[next.number] = nextCost;
                pq.add(new Edge(next.number, nextCost));
            }
        }

        if (dist[N-1] == Integer.MAX_VALUE) {
            System.out.println("impossible");
        } else
            System.out.println(dist[N-1]);

    }
}

/*
 import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Edge implements Comparable <Edge>{
        int num; // 갈려고 하는 노드의 번호
        int cost; // 갈려고 하는 노드까지의 간선의 값

        Edge(int num, int cost){
            this.num = num;
            this.cost = cost; 
        }

        // PriorityQueue에서 cost가 작은순으로 나올수 있도록 처리 
        @Override
        public int compareTo(Edge next) {
            if(cost < next.cost)
                return -1;
            if(cost > next.cost)
                return 1;
            return 0;
        }
    }

    static ArrayList<Edge>al[]; 

    static void dijkstra(int node) {
        // 1. PQ 세팅 
        PriorityQueue<Edge>pq = new PriorityQueue<>();
        // 시작 노드는 삽입 -> 시작까지의 cost = 0 
        pq.add(new Edge(node, 0));

        // 2. dist 세팅
        int[] dist = new int[n]; 
        // dist는 항상 "최소값"을 저장을 하고 있을것이기 때문에 -> 최대값으로 세팅 
        for(int i = 0; i < n; i++) 
            dist[i] = Integer.MAX_VALUE;
        dist[node] = 0;

        // dijkstra
        while(!pq.isEmpty()) {
            // 맨 위의 Edge를 가져옵니다
            Edge now = pq.poll(); 
            // 여기서부터 갈 수 있는 간선들을 확인 
            for(int i = 0; i < al[now.num].size(); i++) {
                // 다음에 갈수 있는 노드/간선을 가져옴
                Edge next = al[now.num].get(i); 
                // 다음 노드까지의 총 비용 = 
                // 내가 지금 있는 노드까지 오기 위해 사용한 비용 + 다음 노드까지 가기 위한 비용 
                int ncost = dist[now.num] + next.cost;
                // ncost가 정말 최소인가?
                // 다음 노드까지 가기 위한 총 비용 < 여태까지 기록된 다음 노드까지의 총 비용보다 작으면 갱신
                if(dist[next.num] <= ncost)
                    continue;
                // 최소 비용이 갱신되면 갱신
                dist[next.num] = ncost;
                // cost를 넣을때 여태까지 누적된 비용으로 새로 삽입 
                pq.add(new Edge(next.num, ncost));
            }
        }
        // 마지막으로 출력 -> n-1번이 못가는 지역 -> integer.max-value가 저장되어있을것.
        if(dist[n-1] == Integer.MAX_VALUE)
            System.out.println("impossible");
        else
            System.out.println(dist[n-1]);
    }

    static int n, t; 

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        // arraylist init
        al = new ArrayList[n]; 
        for(int i = 0; i <n; i++)
            al[i] = new ArrayList<>();

        // 간선 입력 
        for(int i = 0; i < t; i++) {
            int from, to, cost;
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            //단방향 연결 
            al[from].add(new Edge(to, cost)); 
        }

        dijkstra(0); 

    }
}
 */
import java.io.*;
import java.util.*;

public class Q6_0706 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Edge implements Comparable <Edge> {
        int num;
        int cost; 

        Edge(int num, int cost) {
            this.num = num;
            this.cost = cost; 
        }

        @Override
        public int compareTo(Edge next) {
            if(cost < next.cost)
                return -1;
            if(cost > next.cost)
                return 1;
            return 0; 
        }
    }

    static int test;
    static int n, m;
    static ArrayList<Edge>al[]; 
    static int[] dist1;
    static int[] distN; 

    static void dijkstra(int node, int[] dist) {
        // 1. pq 설정
        PriorityQueue<Edge>pq = new PriorityQueue<>();
        pq.add(new Edge(node, 0));

        // 2. dist설정
        for(int i = 0; i <= n; i++)
            dist[i] = Integer.MAX_VALUE;
        dist[node] = 0; 

        // 3. dijkstra
        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            for(int i = 0; i < al[now.num].size(); i++) {
                Edge next = al[now.num].get(i);
                // 거리 갱신
                int ncost = dist[now.num] + next.cost;
                if(dist[next.num] <= ncost)
                    continue;
                dist[next.num] = ncost;
                pq.add(new Edge(next.num, ncost));
            }
        }
    }

    // dist = dist[N] 
    static int psearch(int value, int[] dist) {
        // left right를 설정
        // distN 기준 0~n-3
        int left = 0;
        int right = n - 3; 

        int ans = 0; 
        // psearch 
        while(left <= right) {
            int mid = (left + right) / 2; 
            // 가능성 
            // distN[mid] >= value 
            if(dist[mid] >= value) {
                //정답일 가능성이 있다!
                ans = mid;
                right = mid -1; 
            }
            // value가 너무 작으니까 더 큰 값들을 확인해보자!
            else {
                left = mid + 1; 
            }
        }
        return ans; 
    }


    public static void main(String[] args) throws IOException {
        test = Integer.parseInt(br.readLine());
        for(int t = 1; t <= test; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            // arraylist init
            al = new ArrayList[n+1];
            for(int i = 0; i <= n; i++)
                al[i] = new ArrayList<>();
            // edge input
            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                // 양방향 연결
                al[from].add(new Edge(to, cost));
                al[to].add(new Edge(from , cost));
            }

            // 힌트 1
            // dijkstra
            // 1->모든 노드의 최단거리 dist1
            dist1 = new int[n+1];
            // N->몯느 노드의 최단거리 distN
            distN = new int[n+1];

            // dijkstra 2번 --> 시간복잡도=2 x O(ElogE)
            dijkstra(1, dist1);
            dijkstra(n, distN); 

            // 힌트 2 
            // 1->n, n->1 모두 알고잇는데도 불구하고, 모든 도로를 놓아볼수 X (100,000^2) 
            // 가능성이 있는 노드만 연결 
            // 1 -> A -> B -> N + 1 < dist1[N]; 
            // 1 -> A라는 노드까지의 거리를 A
            // N -> B라는 노드까지의 거리를 B
            // A + B + 1 < dist1[N];
            // B < dist1[N] - A - 1; 
            // distN[B] < dist1[N] - dist1[A] - 1; 
            // dist1[N] - dist1[A] - 1 = value
            // distN[B] < value 
            // ^ parametric search로 빠르게 찾을 것


            // 처리를 몇개 해줘야 함
            // 1. 1에 둘수 없죠
            // 2. N에 둘수 없죠
            // 3. 0은 저희가 사용하지 않습니다
            // --> 얘네들을 걸러주기 위해서 --> 맨 오른쪽으로 push 
            distN[0] = Integer.MAX_VALUE;
            distN[1] = Integer.MAX_VALUE;
            distN[n] = Integer.MAX_VALUE;

            // distN 정렬
            Arrays.sort(distN);

            int ans = 0;
            // 모든 1 -> A => 모든 A도시를 기준으로 value가 맞는 값들을 찾아볼것
            // 2번 도시부터 (1번에는 도로 못놓음), n번에도 못놓음 
            for(int i = 2; i < n; i++) {
                // distN[B] < dist1[N] - dist1[A] - 1; 
                // distN[B] < value 
                int value = dist1[n] - dist1[i] - 1;
                // distN[B] < dist1[N] - dist1[A] - 1 을 충족하는 B도시의 개수를 빠르게 찾아서 
                // ans에 누적 
                ans += psearch(value, distN);
            }
            System.out.println("#" + t + " " + ans);    
        }
    }
}
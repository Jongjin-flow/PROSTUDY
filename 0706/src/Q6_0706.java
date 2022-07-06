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

public class Q6_0706 {
    /* 신규 도로 */
    /*
     * 문제 이해
     * 어떤 도시 A <-> 어떤 도시 B를 연결하는 도로 -> 그 비용이 1
     * - 1-> N까지의 거리가 더 짧아질 수 있는 도로는 총 몇개 놓을 수 있는가?
     * 
     * - 1번 N번에는 도로를 놓을 수 없다.
     * 
     * # 간단하게 모든 지점의 쌍에 도로를 하나씩 놓아보고 -> dijkstra를 돌리면 되겠다.
     * 
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int T, N, M;
    static ArrayList<Edge> al[];
    static int[][] dist;
    static int[] todist;
    static int cnt;

    static class Edge implements Comparable<Edge> {
        int number;
        int cost;

        Edge(int number, int cost) {
            this.number = number;
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

        T = Integer.parseInt(st.nextToken());

        int start, end, c;

        for (int j = 0; j < T; j++) {

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            al = new ArrayList[N + 1];
            dist = new int[3][N + 1];
            todist = new int[N+1];
            cnt =0;

            for (int i = 1; i < N + 1; i++) {
                al[i] = new ArrayList<>();
                dist[0][i] = Integer.MAX_VALUE;
                dist[1][i] = Integer.MAX_VALUE;
                dist[2][i] = Integer.MAX_VALUE;
            }

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                start = Integer.parseInt(st.nextToken());
                end = Integer.parseInt(st.nextToken());
                c = Integer.parseInt(st.nextToken());

                al[start].add(new Edge(end, c));
                al[end].add(new Edge(start, c));
            }



            Dijkstra(1,0);

            Dijkstra(N,1);

            /*
            for (int i = 1; i < N + 1; i++)
                System.out.println("1 에서 " + i + "까지 시간" + " : " + dist[0][i]);
        
            for (int i = 1; i < N + 1; i++)
                System.out.println("10 에서 " + i + "까지 시간" + " : " + dist[1][i]);
            */



             // 이렇게 구하면 N이 최대값인 경우 터진다.
             /*
            for(int i=2;i<N;i++){
                for(int k=2;k<N;k++){
                    if(i == k)
                        continue;

                    if(dist[0][N] > dist[0][i] + dist[1][k] + 1)
                    {
                        al[i].add(new Edge(k, 1));
                        al[k].add(new Edge(i, 1));

                        Dijkstra(1,2);

                        if(dist[2][N] < dist[0][N])
                            cnt ++;
                        
                        Arrays.fill(dist[2], Integer.MAX_VALUE);
                    }



                }
            }
            */
            dist[1][0] = Integer.MAX_VALUE;
            dist[1][1] = Integer.MAX_VALUE;
            dist[1][N] = Integer.MAX_VALUE;
            Arrays.sort(dist[1]);
            //PS를 해야한다.
            
            int ans = 0;
            // 모든 1 -> A => 모든 A도시를 기준으로 value가 맞는 값들을 찾아볼것
            // 2번 도시부터 (1번에는 도로 못놓음), n번에도 못놓음 
            for(int i = 2; i < N; i++) {
                // distN[B] < dist1[N] - dist1[A] - 1; 
                // distN[B] < value 
                int value = dist[1][N] - dist[1][i] - 1;
                // distN[B] < dist1[N] - dist1[A] - 1 을 충족하는 B도시의 개수를 빠르게 찾아서 
                // ans에 누적 
                ans += psearch(value, dist[1]);
            }

            System.out.println("#"+ (j+1) + " " + ans);
        }

    }

    // dist = dist[N] 
    static int psearch(int value, int[] dist) {
        // left right를 설정
        // distN 기준 0~n-3
        int left = 0;
        int right = N - 3; 

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


    static void Dijkstra(int node, int k) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(node, 0));
        dist[k][node] = 0;

        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            for (int i = 0; i < al[now.number].size(); i++) {
                Edge next = al[now.number].get(i);

                int nextCost = dist[k][now.number] + next.cost;

                if (nextCost >= dist[k][next.number])
                    continue;

                dist[k][next.number] = nextCost;
                pq.add(new Edge(next.number, nextCost));
            }

        }

    }


}

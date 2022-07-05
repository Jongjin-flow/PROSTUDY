import java.io.*;
import java.util.*; 

public class Q3_0705 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n, k, m;
    // 인접 리스트
    static ArrayList<Integer>al[]; 

    static int bfs(int node) {
        // 1. queue
        Queue<Integer>q = new LinkedList<>();
        q.add(node); 

        // 2. visited
        int[]visited = new int[n+m+1];
        visited[node] = 1;

        // bfs
        while(!q.isEmpty()) {
            int now = q.poll();
            // now로 부터 갈수있는 노드 확인
            for(int i = 0; i < al[now].size(); i++) {
                int next = al[now].get(i);
                // 방문 체크
                if(visited[next] > 0)
                    continue;
                // ** 여태까지의 문제와 다른것 
                // 내가 N번 노드까지 도착하기 위해서 몇개를 거쳐왔는가?
                // visited를 다르게 쓸수 있는 방법 
                // --> 여태까지 몇개의 노드를 거쳐왔는가를 기록하는데에 사용 가능
                visited[next] = visited[now] + 1;
                q.add(next); 
            }
        }
        // visited[N] -> N번 노드까지 총 몇개의 노드를 거쳐왔는가? 
        return visited[n]; 
    }

    public static void main(String[] args) throws IOException {
         st = new StringTokenizer(br.readLine());
         n = Integer.parseInt(st.nextToken());
         k = Integer.parseInt(st.nextToken());
         m = Integer.parseInt(st.nextToken());

         // arraylist init 
         // 노드의 개수(1부터 시작) + 하이퍼 튜브도 하나의 노드로 인식 
         al = new ArrayList[n + m + 1];
         for(int i = 0; i <= n + m; i++)
             al[i] = new ArrayList<>();

         // 하이퍼튜브 input 
         for(int i = 1; i <= m; i++) {
             st = new StringTokenizer(br.readLine());
             // 하이퍼 튜브의 번호 작업
             // 노드 : 1~N의 번호를 사용 
             // 하이퍼 튜브들 : N+@ 넘어가는 번호들을 사용
             int ht = n + i; 
             for(int j = 0; j < k; j++) {
                 // from -> to 연결 인접리스트
                 // 양방향 연결
                 int to = Integer.parseInt(st.nextToken());
                 al[ht].add(to);
                 al[to].add(ht);
             }
         }
         // 1 -> n번노드까지
         int ans = bfs(1);
         if(ans == 0)
             System.out.println(-1);
         // 노드 -> 튜브 ->노드 ->튜브 ->....->노드
         // 최종 목적지 = 항상 노드개수 = 튜브의 개수 + 1
         // 지금까지 거쳐온 (경로 + 1) / 2 -> 방문한 노드의 개수 
         else
             System.out.println((ans+1) / 2);

    }
}
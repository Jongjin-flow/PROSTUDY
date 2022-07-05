import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q1_0705 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;

    static int[][] map;
    static int[] NODE;
    // Bfs + DFS = 그래프 알고리즘
    /*
     * 깊이 우선 -> 들어갈 수 있을 때까지 들어간다.
     * 특징 :  재귀 + 백트래킹
     * 
     * 완전탐색 -> 모든 노드를 순서에 관계없이 한번씩 방문하는 탐색
     * /// 팁 -> 모든 노드를 들려봐야 하는 탐색 : DFS
     * 
     * 
     * BFS > 너무 우선 -> 층 순으로 방문한다. >  시작 노드로부터 인접한 노드를 우선으로 방문 > Queue > 가장 가까운 최단 경로를 찾는 탐색
     */

     static void bfs(int node) throws IOException{
        Queue<Integer> q =new LinkedList<>();
        q.add(node);
        int[] visited = new int[N];
        visited[node] =1;

        while(!q.isEmpty()){
            int now = q.poll();
            bw.write(NODE[now] + " ");

            for(int i=0;i<N;i++){
                if(map[now][i] == 0)
                    continue;
                
                if(visited[i]==1)
                    continue;
                
                visited[i] =1;
                q.add(i);
            }
        }
     }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        NODE = new int[N];
        map = new int[N][N];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            NODE[i] = Integer.parseInt(st.nextToken());
        }
        

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

       bfs(0);

       bw.flush();
       bw.close();
    }
}

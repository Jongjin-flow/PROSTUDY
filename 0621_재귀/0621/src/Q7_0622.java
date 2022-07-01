import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
Backtracking!

*/
public class Q7_0622 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,K;
    // 인접 행렬
    static int[][] matrix;
    static int [] visited;

    public static void main(String[] args) throws IOException {
        // from -> to 연결
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());

        matrix = new int[N+1][N+1];
        visited = new int[N+1];
        for(int i=0;i<K;i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            matrix[y][x] = 1;

        }

        preorder(start);
        System.out.println();
        Arrays.fill(visited, 0);
        postorder(start);

    }

    private static void preorder(int start) {

        System.out.print(start + " ");
        visited[start] = 1;
        for(int i = N ; i > 0 ;i--){

            if(matrix[start][i] == 0 || visited[i] == 1)
                continue;


            preorder(i);
        }

    
    }


    private static void postorder(int start) {

       //재귀 구성
       // 모든 방문할 수 있는 노드 개수
       visited[start] = 1;
        for(int i = N ; i>0 ;i--){

            if(matrix[start][i] == 0 || visited[i] == 1)
                continue;
            postorder(i);
        }

        System.out.print(start + " ");

    }
}

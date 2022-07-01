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

import org.w3c.dom.Node;

/*
Backtracking!

*/
public class Q9_0622 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int Node, start, end;
    // 인접 행렬
    static int[][] matrix;
    static int [] visited;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // from -> to 연결
        st = new StringTokenizer(br.readLine());
        Node = Integer.parseInt(st.nextToken());
        matrix = new int[Node][Node];

        for(int i=0;i<Node;i++){
            st = new StringTokenizer(br.readLine());

            for (int j=0; j<Node ; j++)
            {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        visited = new int[Node];

        int cost = 0;
        preorder(start, end, cost);

        System.out.println(min + " \n" + max);

    }

    private static void preorder(int start, int end, int cost) {

        visited[start] = 1;

        if(start == end)
        {
            if(cost <= min){
                min = cost;
            }

            if(cost >= max){
                max = cost;
            }

            visited[start] = 0;
            return;
        }

        for(int i = 0 ; i < Node ;i++){

            if(visited[i] == 1)
                continue;

            if(matrix[start][i] != 0 && visited[i] == 0)
            {
                int temp = cost + matrix[start][i];
                preorder(i,end, temp);
            }
               
        }
        visited[start] = 0;

    }

}

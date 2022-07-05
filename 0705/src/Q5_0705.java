import java.io.*;
import java.util.*;

public class Q5_0705 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int K;
    static int map[][];
    static int cnt= Integer.MIN_VALUE;
    static int[] xdir= { 1, 1, -1, -1 };
    static int[] ydir = { -1, 1, 1, -1 };
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                sol(i,j);
        }

        System.out.println(cnt);

    }

    static void sol(int i, int j) {
        int sum = 0;
        for(int t=1 ;t<=K;t++){
            for(int l=0;l<4;l++){
                int nx = j + xdir[l]* t;
                int ny = i + ydir[l]* t;
    
                if(nx < 0 || ny < 0|| nx >= N || ny >= N)
                    continue;
    
                sum = sum + map[ny][nx];
            }
        }
        if(cnt < sum)
        {
            cnt = sum;
        }
    }
}
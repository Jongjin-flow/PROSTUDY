import java.io.*;
import java.util.*;

public class Flood_fill {
    /*
     * 원천으로부터 쫙 퍼지면서 채워지는 것
     * -> 2차원 또는 (N)차원 배열 내에서 BFS -> 게임 유형
     * 1.floodfiㅣㅣ -> 모든 노드까지의 방문
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int K;
    static int map[][];
    static int cnt= Integer.MIN_VALUE;
    static int[] xdir= { 0,0,1,-1};
    static int[] ydir = { 1,-1,0,0 };
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        map = new int[5][5];

        bfs(y, x);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++)
                System.out.print(map[i][j] + " ");

            System.out.println();   
         }

    }

    static class Node{
        int x;
        int y;

        public Node(int y,int x){
            this.y = y;
            this.x = x;
        }
    }

    static void bfs(int y, int x){
        Queue <Node> q = new LinkedList<>();

        q.add(new Node(y, x));

        map[y][x] = 1;
        
        while(!q.isEmpty()){
            Node now = q.poll();

            for(int i=0;i<4;i++){
                int nx = now.x + xdir[i];
                int ny = now.y + ydir[i];

                if(nx<0 || ny < 0|| nx >=5 || ny >=5 || map[ny][nx] != 0){
                    continue;
                }

                map[ny][nx] = map[now.y][now.x]+1;
                q.add(new Node(ny, nx));
                
            }
        }
    }
  
}
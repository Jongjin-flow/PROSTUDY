import java.io.*;
import java.util.*;

public class Q7_0705 {
    /*
     * 원천으로부터 쫙 퍼지면서 채워지는 것
     * -> 2차원 또는 (N)차원 배열 내에서 BFS -> 게임 유형
     * 1.floodfiㅣㅣ -> 모든 노드까지의 방문
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int height, width;
    static int map[][];
    static Queue <flower> q = new LinkedList<>();

    static int[] xdir= { 0,0,1,-1};
    static int[] ydir = { 1,-1,0,0 };
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());

        map = new int[height][width];
        st = new StringTokenizer(br.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        q.add(new flower(y, x));
        map[y][x] = 1;
        st = new StringTokenizer(br.readLine());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        q.add(new flower(y, x));
        map[y][x] = 1;

        bfs();

        System.out.println(max);

    }

    static class flower{
        int x;
        int y;

        public flower(int y,int x){
            this.y = y;
            this.x = x;
        }
    }

    static void bfs(){
        while(!q.isEmpty()){
            flower now = q.poll();

            for(int i=0;i<4;i++){
                int nx = now.x + xdir[i];
                int ny = now.y + ydir[i];

                if(nx<0 || ny < 0|| nx >=width || ny >=height || map[ny][nx] != 0){
                    continue;
                }

                map[ny][nx] = map[now.y][now.x]+1;
                if(max < map[ny][nx])
                    {
                        max = map[ny][nx];
                    }
                q.add(new flower(ny, nx));
                
            }
        }
    }
  
}
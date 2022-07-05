import java.io.*;
import java.util.*;

public class Q8_0705 {
    /*
     * 원천으로부터 쫙 퍼지면서 채워지는 것
     * -> 2차원 또는 (N)차원 배열 내에서 BFS -> 게임 유형
     * 1.floodfiㅣㅣ -> 모든 노드까지의 방문
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static StringTokenizer st;

    static int W, H;
    static int map[][];
    static int[] xdir = { 0, 0, -1, 1 };
    static int[] ydir = { 1, -1, 0, 0 };
    static int zoombiCnt = 0;
    static int max_time = 0;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());


        map = new int[H][W];

        for (int i = 0; i < H; i++) {
            String str = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j) - '0';

                if (map[i][j] == 1) {
                    zoombiCnt++;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());


        bfs(y-1,x-1);

        System.out.println(max_time+2);
        System.out.println(zoombiCnt);
    }

    static void bfs(int y, int x) {
        Queue <zoombi> q = new LinkedList<>();
        zoombi now1 = new zoombi(y, x, 1);
        max_time = now1.time;
        q.add(now1);
        if(map[y][x] == 1)
            zoombiCnt--;

        map[now1.y][now1.x] = 2;

        while(!q.isEmpty()){
            zoombi now = q.poll();

            if(zoombiCnt == 0){
                break;
            }

            for(int i =0;i<4;i++){
                int nx = now.x + xdir[i];
                int ny = now.y + ydir[i];

                if(nx < 0 || ny < 0 || nx >= W || ny >= H || map[ny][nx]==0 || map[ny][nx]!=1)
                    continue;

                if(map[ny][nx] == 1){
                    zoombi temp = new zoombi(ny, nx, now.time+1);
                    q.add(temp);
                    zoombiCnt--;
                    map[ny][nx] = 2;

                    if(max_time < temp.time)
                    {
                        max_time = temp.time;
                    }
                }
            }
            
        }

    }


    static class zoombi{
        int x;
        int y;
        int time;

        public zoombi(int y,int x, int time){
            this.y = y;
            this.x = x;
            this.time = time;
        }
    }

}
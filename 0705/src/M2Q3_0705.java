import java.io.*;
import java.util.*; 

public class M2Q3_0705 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static char map[][];
  

    public static void main(String[] args) throws IOException {
         st = new StringTokenizer(br.readLine());
        
         map = new char[4][5];
         

         Arrays.fill(map[0], '_');
         Arrays.fill(map[1], '_');
         Arrays.fill(map[2], '_');
         Arrays.fill(map[3], '_');

         int y = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());

        int [] xdir = {0,1,1,1,0,-1,-1,-1};
        int [] ydir = {-1,-1,0,1,1,1,0,-1};

        for(int i=0;i<8;i++){
            int nx = x + xdir[i];
            int ny = y + ydir[i];

            if(nx < 0 || ny < 0 || nx > 4 || ny > 3)
                continue;

            
            map[ny][nx] = '#';
        }
         
        st = new StringTokenizer(br.readLine());

        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());

        for(int i=0;i<8;i++){
            int nx = x + xdir[i];
            int ny = y + ydir[i];

            if(nx < 0 || ny < 0|| nx > 4 || ny > 3)
                continue;

            
            map[ny][nx] = '#';
        }

        for(int i=0;i<4;i++){
            for(int j=0;j<5;j++)
            {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
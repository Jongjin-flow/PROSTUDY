import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q5_0621 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static Boolean[][] map;
    static PriorityQueue <Bomb> pq;
    static ArrayList<Integer> arrlist;

    static int N;
    static Bomb bums;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());
        N =  Integer.parseInt(st.nextToken());

        map = new Boolean[N][N];
        arrlist =new ArrayList<>();

        pq = new PriorityQueue<>();

        for(int i=0; i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                int number = Integer.parseInt(st.nextToken());
                map[i][j] = true;
                pq.add(new Bomb(j, i, number)); 
            }
        }


        bw.write(sol() + "초\n");
        bw.flush();
        bw.close();
        
    }



    private static int sol() {
        int min =0;

        int temp_x[] = {1,0,-1,0};
        int temp_y[] = {0,-1,0,1};

        while(pq.size() != 0){
            Bomb cur = pq.poll();
            min++;

            if(map[cur.y][cur.x] == true){
                map[cur.y][cur.x] = false;
                arrlist.add(1);

                for(int i =0; i< 4;i++){
                    int tempx = cur.x + temp_x[i];
                    int tempy = cur.y + temp_y[i];
                
                    if(tempx < 0 || tempx >= N || tempy < 0 || tempy >= N)
                    {
                        continue;
                    }
                    else{
                        if (map[tempy][tempx] == true){
                            map[tempy][tempx] = false;
                            arrlist.add(1);
                            //total --;
                        }
                    }
                }

            }

            if(arrlist.size() == N*N)
                break;

        }
        return min;
        
    }



    static public class Bomb implements Comparable <Bomb>{
        int x,y;
        int number;
        boolean isPossible;
        ArrayList <Bomb> list;

        public Bomb(int x,int y, int number){
            this.x = x;
            this.y = y;
            this.number = number;
        }

        @Override
        public int compareTo(Bomb next) {
            // TODO Auto-generated method stub

            if(number < next.number)
                return -1;
            if (number > next.number)
                return 1;

            return 0;
        }
    }

}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Q5 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int H, W;
    static int map[];
    static int[] list;
    static int bresult = 0;
    static int nomal_result = 0;
    static int dat[], list_dat[];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        dat = new int[100001];
        list_dat = new int[100001];

        map = new int[H*W];
        int cnt = 0;
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0;j<W;j++){
                map[cnt] = Integer.parseInt(st.nextToken());
                dat[map[cnt]] ++;
                cnt++;
            }
        }

        st = new StringTokenizer(br.readLine());
        int bH = Integer.parseInt(st.nextToken());
        int bW = Integer.parseInt(st.nextToken());
        list = new int[bH*bW];
        cnt = 0;

        for (int i = 0; i < bH; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<bW;j++){
                int temp = Integer.parseInt(st.nextToken());
                if(list_dat[temp] == 0)
                {
                    list_dat[temp]= 1;
                    list[cnt] = temp;
                    cnt++;
                }          
            }
        }

        //list=Arrays.stream(list).distinct().toArray();

        /*
        for(int i=0;i<H*W;i++){
            for(int j = 0; j < list.length; j++){
                if(map[i] == list[j]){
                    bresult++;
                    break;
                }
                
            }
        }
        */
       
 

        dat();

        bw.write(bresult + "\n");
        bw.write(H*W - bresult + "\n");
        bw.flush();
        bw.close();
    }

    private static void dat() {
        // DAT (Direct Address Table)
        // 그냥 배열
        // index에 어떠한 "의미를" 부여한다.
        for(int i =0; i<list.length;i++){
            bresult = bresult + dat[list[i]];
        }
    }
}

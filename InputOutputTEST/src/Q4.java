import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q4 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int H,W,N;
    static char[][] map;
    static char[] find;
    static String proStr ="";
    static String findStr;
    
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input();
        //find();
        proFind();

        bw.flush();
        bw.close();
    }

    //Pro 라면?
    //2차원을 1차열로 풀어서 해결하자
    private static void proFind() throws IOException {
        for(int i = 0; i <= proStr.length() - N;i++)
        {
            if(proStr.charAt(i) == findStr.charAt(0)){
                boolean find = true;
                for(int j =0;j<findStr.length(); j++)
                {
                    if(proStr.charAt(i+j) != findStr.charAt(j)){
                        find = false;
                        break;
                    }
                }

                if(find == true){
                    bw.write("("+(i/W) + "," +(i%W)+ ")" +"\n");
                }
            }


        }
    }

    private static void find() {
        int cnt = 0;
        int x, y;
        for(int i=0; i<H;i++)
        {
            for(int j=0; j<W;j++)
            {
                if(map[i][j] == find[0])
                {
                    findStr(i,j);
                }
            }
        }
    }

    // 직관적인 방법.
    private static void findStr(int i, int j) {

        for(int k = 0; k<find.length; k++)
        {
            if(j== W-1){
               i = i+1;
               j=0;
            }
            
            if(map[i][j]==find[k])
            {

            }

        }
    }


    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        //map = new char[H][W];

        /* for(int i=0;i<H;i++){
            String str = br.readLine();
            for(int j=0; j<W;j++){
                map[i][j] = str.charAt(j);
            }
        } */

        for(int i=0;i<H;i++){
            String str = br.readLine();
            proStr = proStr + str;
        }


        st = new StringTokenizer(br.readLine());
        N= Integer.parseInt(st.nextToken());
        findStr = br.readLine();

        /*
        find = new char[findStr.length()];

        for(int i=0;i<findStr.length();i++)
        {
            find[i] = findStr.charAt(i);
        }
        */
    }
}

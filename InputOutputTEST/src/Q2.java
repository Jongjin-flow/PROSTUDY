import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.util.StringTokenizer;

public class Q2 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int H, W;
    static int map[][];
    static int Line[];
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input();
        output();
        
    }

    private static void output() throws IOException {
        
        for(int i=0;i<H;i++){
            bw.write(Line[i]+ "\n");
        }

        bw.flush();
        bw.close();
    }

    static void input() throws IOException
    {
        st = new StringTokenizer(br.readLine());
        
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new int[H][W];
        Line = new int[H];

        for(int i = 0; i<H; i++){
            String str = br.readLine();
            Line[i] = 0;

            for(int j=0; j<W; j++){
                map[i][j] = str.charAt(j)- 48;
                //System.out.println("map[i][j] : "+map[i][j]);
                if(map[i][j] == 1)
                {
                    Line[i]++;
                }
            }
        }
        
    }
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q6 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int dat[];

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str = br.readLine();
        int[] result = new int[str.length()];
        dat = new int[100];

        for(int i=0; i<str.length();i++)
        {
            if(dat[str.charAt(i)] == 0)
             {
                 result[i] = str.charAt(i);
                 dat[str.charAt(i)] = 1;
             }
        }
     
        for(int i= 65; i<92; i++){
            if(dat[i] == 1){
                bw.write((char)(i)+"");
            }            
        }

        bw.write("\n");
        bw.flush();
        bw.close();
    }
}

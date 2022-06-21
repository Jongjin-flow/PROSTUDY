import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q8 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int dat[];
    static int N, T, k;
    public static void main(String[] args) throws IOException {
        
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
 
        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for(k = 0; k<T;k++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            dat = new int[N+1];
            StringTokenizer height = new StringTokenizer(br.readLine());
            StringTokenizer cardnumber = new StringTokenizer(br.readLine());

            for(int i =0; i<N;i++){
                int H = Integer.parseInt(height.nextToken());
                int num = Integer.parseInt(cardnumber.nextToken());
                dat[num] = H;
            }
            
            boolean check = true;
            for(int i=2; i<N+1;i++){
                if(dat[i] > dat[i-1]){
                    check = false;
                }
            }

            if(check == false){
                bw.write("NO" + "\n");
            }
            else{
                bw.write("YES"+ "\n");
            }
            
        }
        bw.flush();
        bw.close();

    }

}
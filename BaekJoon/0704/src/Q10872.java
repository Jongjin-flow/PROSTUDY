import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q10872 {
    static int n;
    static int sol=1;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        if(n == 0)
        {
            sol = 1;
        }
        else{
            // 시작
            fac(1);
        }

        bw.write(sol + "\n");
        bw.flush();
        bw.close();
    }

     static void fac(int next) {

        sol = sol * next;
        if(next == n){
            return;
        }
        fac(next+1);
    }
    
}

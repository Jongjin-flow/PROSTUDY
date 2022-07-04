import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q10870 {
    static int n;
    static int sol=0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

   
        sol = fibo(n);
        
        bw.write(sol + "\n");
        bw.flush();
        bw.close();
    }

     static int fibo(int next) {
        
        if(next == 0)
            return 0;
        else if(next == 1){
            return 1;
        }
        else{
            return fibo(next-1)+fibo(next-2);
        }
    }
    
}

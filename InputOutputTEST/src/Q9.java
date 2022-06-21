import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q9 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static int [] Button;

    static int T, N,M,K;
    static int result = 0;
    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for(int i = 0; i<T; i++){ 
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());74-
            Button = new int[N];

            st = new StringTokenizer(br.readLine());
            for(int j =0; j<N;j++)
            {
                Button[j] = Integer.parseInt(st.nextToken());

            }

            for(int j =0; j<N;j++)
            {
                find(Button[j]);
            }

            bw.write(result+"\n");

        }

        bw.flush();
        bw.close();
        
    }
    private static void find(int button) {
        int click = 0;
        int sum = 0;

        LinkedList <Integer> q = new LinkedList<>();
        q.add(button);

        while (q.size() != 0)
        {
            int temp = q.poll();
            click++;

            sum = sum + temp;

            if(sum > M){
                sum = sum%M;
            }
            if(sum < 0){
                sum =  sum + M+1;
            }

            if(click == 4 && sum == K)
            {
                result ++;
            }

            if (click < 4){
                for(int i=0;i<N;i++){
                    q.add(Button[i]);
                }
            }

        }

    }
}

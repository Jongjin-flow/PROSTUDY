import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q2750 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue <Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        int n = Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if(num == 0){
                if(pq.size() == 0)
                    bw.write(0 + "\n");
                else{
                    int sol = pq.poll() ;
                    bw.write(sol + "\n");
                }
            }else{
                pq.add(num);
            }
                
        }

        bw.flush();
        bw.close();
    }

}

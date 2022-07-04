import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q11286 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue <Number> pq = new PriorityQueue<>();
        
        int n = Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            if(num == 0){
                if(pq.size() == 0)
                    bw.write(0 + "\n");
                else{
                    Number sol = pq.poll() ;
                    bw.write(sol.number + "\n");
                }
            }else{
                pq.add(new Number(num));
            }
                
        }

        bw.flush();
        bw.close();
    }

    static class Number implements Comparable<Number>{
        int number;

        public Number(int number){
            this.number = number;
        }

        @Override
        public int compareTo(Number next) {
            // TODO Auto-generated method stub
            if(Math.abs(number) < Math.abs(next.number))
                return -1;
            if(Math.abs(number) > Math.abs(next.number))
                return 1;   
            if(number < next.number)
                return -1;
            if(number > next.number)
                return 1;

            return 0;
        }
        
    }
}

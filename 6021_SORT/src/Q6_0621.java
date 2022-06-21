import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q6_0621 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static PriorityQueue <College> pq;
    static PriorityQueue <College> pq2;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        pq2 = new PriorityQueue<>();

        pq.add(new College(500));

        for(int i=0;i< N;i++){
            st = new StringTokenizer(br.readLine());
            int score1 = Integer.parseInt(st.nextToken());
            int score2 = Integer.parseInt(st.nextToken());
        
            
            pq.add(new College(score1));
            pq.add(new College(score2));
            pq2.addAll(pq);

            int mid = pq.size()/2 +1;

            College temp = null;
            for(int j=0;j<mid;j++){
                temp = pq.poll();
            }

            bw.write(temp.score+ "\n");

            pq.clear();
            pq.addAll(pq2);
            pq2.clear();
          
        }

        bw.flush();
        bw.close();

    }


    static public class College implements Comparable<College>{
        int score;

        public College(int score){
            this.score = score;
        }

        @Override
        public int compareTo(College next) {
            // TODO Auto-generated method stub
            if(score < next.score)
            {
                 return -1;
            }
            if(score > next.score)
            {
                return 1;
            }
            return 0;
        }
    }
}

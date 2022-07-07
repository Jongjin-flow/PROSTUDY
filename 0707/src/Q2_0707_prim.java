import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q2_0707_prim {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;

    static double []x;
    static double []y;
    static double [][]cost;

    //노드를 선택하기 때문에 노드별로 Arraylist 생성 필요
    static ArrayList<Node> al[];

    static class Node implements Comparable<Node>{
        int num; // 현재 노드
        double cost; // 현재 노드까지 오기전 cost 값

        Node(int num, double cost){
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Q2_0707_prim.Node o) {
            if(cost < o.cost)
                return -1;
            if(cost > o.cost)
                return 1;
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        x = new double[N];
        y = new double[N];
        cost = new double[N][N];

        al = new ArrayList[N];
        
        for(int i=0;i<N;i++)
        {
            al[i] = new ArrayList<>();
        }

        double tempX, tempY;
        for(int i=0;i<N;i++)
        {
            st = new StringTokenizer(br.readLine());

            tempX = Double.parseDouble(st.nextToken());
            tempY = Double.parseDouble(st.nextToken());

            x[i] = tempX;
            y[i] = tempY;
        
        }

        for(int i=0;i<N;i++)
        {
            for(int j=0;j<N;j++)
            {
                double value = Math.sqrt(Math.pow(x[i]-x[j],2) + Math.pow(y[i]-y[j],2));

                cost[i][j] = value;
            }
        }

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(i != j){
                    al[i].add(new Node(j, cost[i][j]));
                }
            }
        }

        prim();
        
    }

    static void prim() {
        // pq 세팅
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));

        int [] visited = new int[N];
        double min =0;
        while(!pq.isEmpty()){
            Node now = pq.poll();

            if(visited[now.num] == 1)
                continue;
            
            visited[now.num] =1;
            min = min + now.cost;

            for(int i=0;i<al[now.num].size();i++){
                Node next = al[now.num].get(i);

                if(visited[next.num] == 1)
                    continue;
                
                pq.add(next);
            }
        }

        System.out.println(Math.round(min*100)/100.0);
    }
}

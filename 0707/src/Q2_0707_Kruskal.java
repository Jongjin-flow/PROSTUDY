import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q2_0707_Kruskal {
    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N;

    static double []x;
    static double []y;
    static double [][]cost;

    static ArrayList<Edge> al = new ArrayList<>();
    static int [] parent;

    static class Edge implements Comparable<Edge>{
        int A;
        int B;
        double cost;

        Edge(int A, int B, double cost){
            this.A = A;
            this.B = B;
            this.cost = cost;
        }

        @Override
        public int compareTo(Q2_0707_Kruskal.Edge o) {
            // TODO Auto-generated method stub
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
        parent = new int[N];

        double tempX, tempY;
        for(int i=0;i<N;i++)
        {
            st = new StringTokenizer(br.readLine());

            tempX = Double.parseDouble(st.nextToken());
            tempY = Double.parseDouble(st.nextToken());

            x[i] = tempX;
            y[i] = tempY;
        
            parent[i] = i;
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
            for(int j=0;j<N;j++)
            {
                if(i<=j)
                 continue;
                al.add(new Edge(i, j, cost[i][j]));
            }
        }

        Kruskal();

    }

    static void Kruskal() {
        // 정렬이 먼저!
        Collections.sort(al);

        double min = 0;

        //가중치가 적은 엣지부터 선택해서 mst 만들기 시작
        for(int i=0;i<al.size();i++){
            Edge now = al.get(i);

            if(find(now.A) == find(now.B))
                continue;
            
            //합치자!
            min = min + now.cost;
            union(now.A,now.B);
        }
        
        System.out.println(Math.round(min*100)/100.0);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(pa == pb)
            return;
        
        parent[pb] = pa;
    }

    static int find(int a) {
        if(a == parent[a])
            return a;


        return parent[a] = find(parent[a]);
    }
}

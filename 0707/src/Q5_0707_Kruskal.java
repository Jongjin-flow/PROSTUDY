import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q5_0707_Kruskal {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int T,N,M;
    //static long [][] number;
    static int [] enter;
    static int [] parent;
    static ArrayList<Node> al;
    static double min ;

    static class Node{
        int x;
        int y;
        int num;
        long parent;

        Node(int num,int x, int y, int parent){
            this.x = x;
            this.y = y;
            this.num =num;
            this.parent= parent;
        }
    }

    static class Edge implements Comparable <Edge>{
        int a;
        int b;
        double cost;

        Edge(int a, int b, double cost)
        {
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            if(cost <o.cost)
                return -1;
            if(cost > o.cost) 
                return 1;

            return 0;
        }
    }

    static ArrayList<Edge> eal;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        
        T = Integer.parseInt(st.nextToken());

        for(int t =0; t<T;t++){
            st = new StringTokenizer(br.readLine());
            min = 0;
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            al = new ArrayList<>();

            //number = new long[100001][100001];
            enter = new int[N+1];
            parent = new int[N+2];
            int x,y;


            for(int i=1; i<N+1; i++){
                st = new StringTokenizer(br.readLine());

                y = Integer.parseInt(st.nextToken());
                x = Integer.parseInt(st.nextToken());
                //number[y][x] = i;
                parent[i] = i;
                // ????????? ?????????
                al.add(new Node(i, x, y, i));

            }

            for(int i=0;i<M;i++){
                st = new StringTokenizer(br.readLine());
                int tempEnter =Integer.parseInt(st.nextToken());

                // ????????? ?????? ????????? ??????- parent ?????? -> ????????? mst??? ???????????? ????????? ?????????.
                parent[tempEnter] = N+1;
            }

            eal = new ArrayList<>();

            for(int i=0;i<al.size();i++){
                Node a = al.get(i);
                for(int j=0;j<al.size();j++)
                {   // ?????? ???????????? ?????? ????????? ??????.
                    if(j>i)
                    {
                        Node b = al.get(j);
                        double value = Math.pow(b.x-a.x, 2) + Math.pow(b.y-a.y,2);
                        eal.add(new Edge(a.num,b.num, value));

                    }
                     
                }
            }
            Kruskal();

            System.out.println("#"+ (t+1) + " " +Math.round(min));
        }
    }

    static void Kruskal() {
        Collections.sort(eal);
        

        for(int i=0 ; i<eal.size();i++){
            Edge now = eal.get(i);

            // ????????? ????????? ???????????? ???????????? ????????????
            if(find(now.a) == find(now.b))
                continue;

            min = min + now.cost;
            union(now.a, now.b);


        }
       
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if(pa == pb)
            return;
        
        
        parent[pb] = pa;

    }

    static int find(int a) {
        //????????? parent??? ?????? ????????? ??????
        if(a == parent[a])
            return a;

        // ???????????? a??? parent??? ????????? ??????
        return parent[a] = find(parent[a]);
    }
}

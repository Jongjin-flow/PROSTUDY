import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

import javax.imageio.plugins.tiff.TIFFDirectory;

public class Q3_0707_Kruskal {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static String [] coll;
    static int [] parent;
    static ArrayList<Edge> al= new ArrayList<>();

    static class Edge implements Comparable<Edge>{
        int a;
        int b;
        int cost;

        Edge(int a, int b, int cost){
            this.a =a;
            this.b = b;
            this.cost =cost;
        }

        @Override
        public int compareTo(Q3_0707_Kruskal.Edge o) {
            
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
        M = Integer.parseInt(st.nextToken());

        al = new ArrayList<>();

        coll = new String[N+1];
        parent = new int[N+1];

       st = new StringTokenizer(br.readLine());

        for(int i=1;i<N+1;i++){

            coll[i] =  st.nextToken();
            parent[i] = i;
        }

        int a; int b; int cost;
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());

            al.add(new Edge(a, b, cost));
        }

        Kruskal();

    }


    static void Kruskal() {
        Collections.sort(al);
        int ans = 0;
        int cnt = 0;
        for(int i=0;i<al.size();i++){
            Edge now = al.get(i);

            if(coll[now.a].equals(coll[now.b]))
                continue;
            
            if(find(now.a) == find(now.b))
                continue;

            ans = ans + now.cost;
            cnt ++;

            union(now.a, now.b);
            
        } 

        // MST의 특징 :  간선의 갯수는 NODE의 갯수보다 1 작다!
        if(cnt != N-1)
            System.out.println(-1);
        else
            System.out.println(ans);
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

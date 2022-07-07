import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q1_0707_Kruskal {

    
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;
    static int V,E;

    // 크루스칼은 간선을 선택
    static class Edge implements Comparable<Edge>{ 
        int a;
        int b;
        int cost;

        Edge(int a, int b, int cost){
            this.a = a;
            this.b = b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Q1_0707_Kruskal.Edge o) {
            if(cost < o.cost)
                return -1;
            if(cost > o.cost)  
                return 1;

            return 0;
        }
    }

    //양방향 간선을 넣기 때문에, 간선마다 생성필요 없고, Edge 배열을 만든다.
    static ArrayList <Edge> al = new ArrayList<>();
    // 대신 parent 배열을 만든다. 유니온 - 파인드를 하기 위해.
    static int[] parent;

    public static void main(String[] args) throws IOException {
        
        st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        parent = new int[V+1];
        for(int i=1;i<V+1;i++){
            parent[i] = i;
            // 일단 서로 연결되지 않았다고 설정
        }




        // 이제 간선을 양방향으로 연결
        int start; 
        int end; 
        int cost;

        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());

            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
            cost = Integer.parseInt(st.nextToken());
            al.add(new Edge(start, end, cost));
        }


        Kruskal();
    }

    static void Kruskal() {
        // 일단 list의 sort 정렬을 하고, 가중치가 작은 간선부터 이어나간다.

        Collections.sort(al);
        int min =0;
        //for문으로 작은순서대로 선택한 간선의 a,b의 부모 노드가 같니? -> 서로 연결되어있니?
        for(int i=0; i<al.size(); i++){
            Edge now =  al.get(i);

            if(find(now.a) == find(now.b))
                continue;
            
            // 연결이 안되어있으면 연결하자.
            min = min + now.cost;
            union(now.a, now.b);
                
        }

        System.out.println(min);
    }

    static void union(int a, int b) {
        // 각 노드의 부모 찾기
        int pa = find(a);
        int pb = find(b);

        // 조상이 같으면 끝.
        if(pa == pb)
            return;

        // pb를 pa로 합침.
        parent[pb] = pa;
    }

    static int find(int node) {

        if(node == parent[node])
        {
            return node;
        }
        return parent[node] = find(parent[node]);
    }
}

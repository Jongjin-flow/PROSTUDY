import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.AlgorithmConstraints;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;

public class Kruskal {
    /*
     * MST (Minimun  Spanning Tree - 최소 신장 트리)
     * 
     * 신장트리란? 그래프의 노드를 모두 연결은 하지만 "사이클"이 존재하지 않는 특수 형태의 트리 -> 항상 양방향 그래프에서만 만들 수 있다.
     * -> A노드 -> B노드까지 가는 경로는 항상 1개가 존재
     * -> 간선의 갯수는 노드의 갯수 - 1
     * 
     * 최소 신장 트리란?
     * 그래프에서 생성된 신장 트리 중 "가장 적은 비용"을 필요로 하는 것 ** 가중치 + 양방향 그래프
     * -> 어떻게? -> 두 개의 알고리즘
     * 1. 크루스칼 알고리즘 - 간선 위주로 선택, Union Find + Sort
     *    1) 모든 간선을 저장
     *      Edge{
     *             int a, int b, int cost;
     *          }
     *    2) 작은 가중치를 가진 간선부터 빼온다.
     *       - sort -> 작은 가중치를 가진 간선부터 선택이 가능
     * 
     *    3) 간선을 하나씩 빼와서 사이클이 생기지 않는 한, 해당 간선을 선택
     *        -> MST의 일원으로 포함
     *        -> 빼온 간선의 A와 B 노드의 union - find 작업
     * 
     * 
     * 2. 프림 알고리즘 - 노드 위주로 선택 + Priority Queue
     */
     static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     static StringTokenizer st;


     // 크루스칼과 다익스트라랑 엣지 구현 차이 비교.
     static class Edge implements Comparable <Edge>
     {
        // A와 B 사이의 간선
        int a;
        int b;
        int cost;

        Edge(int a, int b, int cost){
            this.a =a;
            this.b =b;
            this.cost = cost;
        }

        @Override
        public int compareTo(Kruskal.Edge o) {
            // TODO Auto-generated method stub
            if(cost < o.cost)
                return -1;
            if(cost > o.cost)
                return 1;
            return 0;
        }
     }

     static int find(int node){
        // 지금의 노드가 가리키는게 부모와 같다.
        if(node == parent[node]){
            return node;
        }

        return parent[node] = find(parent[node]);
     }

     static void union(int a, int b){
        //조상 확인
        int pa = find(a);
        int pb = find(b);
        //조상이 같으면 -> 이미 연결되어있다.
        if(pa == pb)
            return;
        
            //pa의 산하 아래 pb 흡수
        parent[pb] = pa;
     }

     static void Kruskal(){
        int ans =0;
        // 작은 cost를 가진 간선부터 빼오기 위해 먼저 sort
        Collections.sort(al);
        // 하나씩 빼오면서 MST의 일원이 될 수 있다면 cost 누적
        // 이 간선을 연결하던 두 노드를 결합(union)
        for(int i=0 ; i<al.size();i++){
            Edge now = al.get(i);

            // 두개가 이미 연결되었는가? = 두개를 연결하면 사이클이 발생하는가?
            if(find(now.a) == find(now.b)){
                System.out.println("SKIP : " + now.a + " - " + now.b);
                continue;
            }
            // 두개를 연결-> mst의 일원이 되고->최소신장트를 만들기 위해 사용한 비용 누적
            ans += now.cost;
            System.out.println(now.a + "에서 " +  now.b + " 로 연결 비용 : " + now.cost);

            union(now.a, now.b);
        }

        System.out.println("총 비용 :" + ans);

     }

     static ArrayList<Edge> al = new ArrayList<>();
     static int [] parent;
    public static void main(String[] args) throws Exception {

        parent = new int[5];

        for(int i=0;i<5;i++){
            parent[i] = i;
        }

        // 이미 엣지가 양방향이라는 가정에 하기와 같이 넣음
        al.add(new Edge(0,1,10));
        al.add(new Edge(1,2,20));
        al.add(new Edge(1,4,30));
        al.add(new Edge(0,4,40));
        al.add(new Edge(2,4,50));
        al.add(new Edge(0,3,60));
        al.add(new Edge(2,3,70));

        Kruskal();

    }
}

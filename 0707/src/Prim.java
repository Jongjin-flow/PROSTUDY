import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prim {
    /*
     * MST (Minimun Spanning Tree - 최소 신장 트리)
     * 
     * 신장트리란? 그래프의 노드를 모두 연결은 하지만 "사이클"이 존재하지 않는 특수 형태의 트리 -> 항상 양방향 그래프에서만 만들 수 있다.
     * -> A노드 -> B노드까지 가는 경로는 항상 1개가 존재
     * -> 간선의 갯수는 노드의 갯수 - 1
     * 
     * 최소 신장 트리란?
     * 그래프에서 생성된 신장 트리 중 "가장 적은 비용"을 필요로 하는 것 ** 가중치 + 양방향 그래프
     * -> 어떻게? -> 두 개의 알고리즘
     * 1. 크루스칼 알고리즘 - 간선 위주로 선택, Union Find + Sort
     * 1) 모든 간선을 저장
     * Edge{
     * int a, int b, int cost;
     * }
     * 2) 작은 가중치를 가진 간선부터 빼온다.
     * - sort -> 작은 가중치를 가진 간선부터 선택이 가능
     * 
     * 3) 간선을 하나씩 빼와서 사이클이 생기지 않는 한, 해당 간선을 선택
     * -> MST의 일원으로 포함
     * -> 빼온 간선의 A와 B 노드의 union - find 작업
     * 
     * 크루스칼의 시간 복잡도 : 유니온 파인드의 시간복잡도는 O(a(N)) + Sort 알고리즘 = O(ElongE)
     * -> O(ElogE)
     * 
     * => 일반적인 경우 크루스칼을 많이 사용
     * 
     * 2. 프림 알고리즘 - 노드 위주로 선택 + Priority Queue
     * 1) 모든 노드를 저장.
     * class Node{
     * int num;
     * int cost
     * }
     * 
     * al[from].add(new Node(to,cost));
     * al[to].add(new Node(from,cost));
     * 
     * 2) 처음 노드를 설정 -> 아무 곳에서 시작해도 결과물은 똑같다 -> pq -> 첫 시작 노드(0,1)에서 시작
     * 
     * 3) 인접한 노드들을 PQ에 넣는다.
     * 가장 작은거 뺴고, mst의 일원이니? 일원이 아니네 -> 확정 -> 인접한 노드 pq에 추가 , 이미 일원이네 -> 제외하고 다음 노드
     * 뺸다.
     * 
     * 프림의 시간 복잡도 : PQ = O(logN) ---> O(logE)
     * 노드 번 만큼 반복 ->> O(VlogE)
     * 
     * => 간선의 갯수가 엄청 많을 때 사용하는게 좋고
     */

    static class Node implements Comparable<Node> {
        int num;
        int cost;

        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Prim.Node o) {
            // TODO Auto-generated method stub
            if (cost < o.cost)
                return -1;
            if (cost > o.cost)
                return 1;

            return 0;
        }

    }

    static ArrayList<Node> al[];

    static void prim() {
        // 1. PQ setting
        PriorityQueue<Node> pq = new PriorityQueue<>();
        // 그냥 아무거나 일단 시작점으로 잡고 넣어주고 시작
        pq.add(new Node(1, 0));
        int ans = 0;
        int[] visited = new int[5];
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            // MST의 멤버인가?
            if (visited[now.num] == 1) {
                // 이미 mst의 멤버라면 pass
                continue;
            }

            // mst의 일원으로 받아줘야함
            visited[now.num] = 1;
            System.out.println("선택된 node : " + now.num + ", 비용 : " + now.cost);
            ans += now.cost;
            // 인접한 노드들을 다시 pq에 넣어줘야함
            for (int i = 0; i < al[now.num].size(); i++) {
                Node next = al[now.num].get(i);

                if (visited[next.num] == 1)
                    continue;

                pq.add(next);
            }
        }

        System.out.println(ans);
    }

    public static void main(String[] args) {

        al = new ArrayList[5];
        for (int i = 0; i < 5; i++) {
            al[i] = new ArrayList<>();
        }

        // 양방향 연결
        al[0].add(new Node(1, 10));
        al[1].add(new Node(0, 10));
        al[0].add(new Node(4, 40));
        al[4].add(new Node(0, 40));
        al[0].add(new Node(3, 60));
        al[3].add(new Node(0, 60));
        al[1].add(new Node(2, 20));
        al[2].add(new Node(1, 20));
        al[1].add(new Node(4, 30));
        al[4].add(new Node(1, 30));
        al[2].add(new Node(4, 50));
        al[4].add(new Node(2, 50));
        al[2].add(new Node(3, 70));
        al[3].add(new Node(2, 70));

        prim();
    }
}

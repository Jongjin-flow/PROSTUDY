import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q1_0707_prim {
    //PQ를 써야한다.
    //NODE 선택.
    //양방향으로 list를 넣어줘야한다.

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st ;
    
    static int V, E;

    static class Node implements Comparable<Node>{
        int num;    // 노드의 번호
        int cost;   // 이전 노드까지 연결될 때까지의 비용

        Node(int num, int cost){
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Q1_0707_prim.Node o) {
            if(cost <o.cost)
                return -1;
            if(cost > o.cost)
                return 1;
            
            return 0;
        }
    }

    static ArrayList<Node> al[];

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        // 리스트를 노드의 갯수만큼 생성
        al = new ArrayList[V+1];
        
        for(int i=1;i<V+1;i++){
            al[i] = new ArrayList<>();
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


            al[start].add(new Node(end, cost));
            al[end].add(new Node(start, cost));

        }

        prim();



    }

    static void prim() {
        // 1. PQ를 생성하고, 첫번쨰 노드를 넣자.
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));

        int [] visited = new int[V+1];
        int min = 0;

        while(!pq.isEmpty())
        {
            Node now = pq.poll();
            
            if (visited[now.num] == 1)
                continue;
            // mst에 합치기
            visited[now.num] = 1;
            min = min + now.cost;

            // 인접한 노드 pq에 넣기
            for(int i=0;i<al[now.num].size();i++){
                Node next = al[now.num].get(i);

                if(visited[next.num] == 1)
                    continue;
                
                pq.add(next);
            }
        }

        System.out.println(min);
    }
    
}

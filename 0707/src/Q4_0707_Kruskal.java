import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q4_0707_Kruskal {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int T, Y, X;
    static char[][] map;
    static int[][][] costmap;
    static int[] Ax;
    static int[] Ay;
    static int sx, sy;

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
        public int compareTo(Edge o) {
            
            if(cost < o.cost)
                return -1;
            if(cost > o.cost)
                return 1;

            return 0;
        }
    }

    static class Node {
        int x;
        int y;
        int cost;

        Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    static int num [][];
    static int[] parent;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            X = Integer.parseInt(st.nextToken());
            Y = Integer.parseInt(st.nextToken());

            map = new char[Y][X];
            Ax = new int[101];
            Ay = new int[101];
            // 노드 넘버링
            num = new int [Y][X];
            parent = new int[101];
            sx = 0;
            sy = 0;
            int k = 0;

            for (int i = 0; i < Y; i++) {

                String str = br.readLine();
                map[i] = str.toCharArray();

                for (int j = 0; j < X; j++) {
                    if (map[i][j] == 'A' || map[i][j] == 'S') {
                        Ax[k] = j;
                        Ay[k] = i;
                        num[i][j] = k;
                        parent[k] =k;
                        k++;
                        
                    }

                    if (map[i][j] == 'S') {
                        sx = j;
                        sy = i;
                    }
                }
            }

            costmap = new int[k][Y][X];

            al = new ArrayList<>();
            // flood fill
            for (int i = 0; i < k; i++) {
                flood_fill(Ax[i], Ay[i], costmap[i]);
            }
            /*
            for(int i=0;i<k;i++)
            {
                for(int j=0;j<Y;j++){
                    for(int l=0;l<X;l++){
                       System.out.print(costmap[i][j][l]);
                    }
                    System.out.println();
                }
                System.out.println();
                System.out.println();
            }
            for (int i = 0; i < k; i++)
                System.out.println(costmap[i][sy][sx]);

            */
            /*  
            for (int i = 0; i < al.size(); i++) {
                System.out.println(al.get(i).cost);
            }
            */
            Kruskal(); 


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
                //System.out.println("SKIP : " + now.a + " - " + now.b);
                continue;
            }
            // 두개를 연결-> mst의 일원이 되고->최소신장트를 만들기 위해 사용한 비용 누적
            ans += now.cost;
            //System.out.println(now.a + "에서 " +  now.b + " 로 연결 비용 : " + now.cost);

            union(now.a, now.b);
        }

        System.out.println(ans);

     }


    static int[] xdir = { 0, 0, -1, 1 };
    static int[] ydir = { 1 ,-1, 0, 0 };

    static void flood_fill(int x, int y, int[][] costmap) {
        Queue <Node> q = new LinkedList<>();
        q.add(new Node(x,y,0));
   
        while(!q.isEmpty())
        {
            Node now = q.poll();
            int nowX =now.x;
            int nowY = now.y;

             // 다른 노드를 찾았다 하면 arraylist에 넣어주기
             if(num[nowY][nowX] > 0){
                al.add(new Edge(num[y][x], num[nowY][nowX], costmap[nowY][nowX]));
            }
        
            for (int i = 0; i < 4; i++) {
                int nextX = nowX + xdir[i];
                int nextY = nowY + ydir[i];

                if (nextX < 0 || nextY < 0 || nextX >= X || nextY >= Y || map[nextY][nextX] == '#' || costmap[nextY][nextX] != 0)
                    continue;


                costmap[nextY][nextX] = now.cost + 1;
                q.add(new Node(nextX, nextY, costmap[nextY][nextX]));

            }
        }

    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q4_0721 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int T;
    static int N, Q;
    static int[] fac;
    static int[] tree;
    static int [] ans;
    static ArrayList<Query> Qlist;
    static ArrayList<Node> Node_list;

    static class Query implements Comparable<Query> {
        int start;
        int end;
        int value;
        int index;

        public Query(int start, int end, int value, int index) {
            this.start = start; // 시작점
            this.end = end; // 끝점
            this.value = value; // 기준값
            this.index = index; // 쿼리 index
        }

        @Override
        public int compareTo(Query next) {
            if (value < next.value)
                return 1;
            if (value > next.value)
                return -1;
            return 0;
        }
    }

    static class Node implements Comparable<Node> {
        int value;  // node 값
        int index; // node의 처음 위치

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(Node next) {
            if (value < next.value)
                return 1;
            if (value > next.value)
                return -1;
            return 0;
        }

    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        T = Integer.parseInt(br.readLine());

        for(int t=1;t<=T;t++){
            st = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());

            Qlist = new ArrayList<>();
            Node_list = new ArrayList<>();
            ans = new int[Q];

            fac = new int[N+1];
            tree = new int [N * 4];

            st = new StringTokenizer(br.readLine());

            for(int i=1;i<=N; i++){
                fac[i] = Integer.parseInt(st.nextToken());
                Node_list.add(new Node(fac[i], i));
            }

            //제품 정보를 다 받은 후, 제품 값이 큰 경우부터 쿼리를 진행하기 위해 오름차순으로 정렬.
            Collections.sort(Node_list);

            for(int i=0;i<Q;i++){

                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int value = Integer.parseInt(st.nextToken());

                Qlist.add(new Query(start, end, value, i));
            }

            //쿼리를 다 받은 후, 쿼리 value 기준으로 오름차순으로 정렬 후 높은 값부터 update 해가면서 쿼리 처리.
            // 어차피 정렬 후 첫번째 값보다 높은 애들을 update 하면, 두번쨰 세번째 값보다 높은 값이니까.
            Collections.sort(Qlist);

            int idx=0;
            for(int i=0;i<Qlist.size();i++)
            {   
                Query q = Qlist.get(i);
                
                for(int k =idx; k< Node_list.size();k++){
                    Node temp = Node_list.get(k);

                    if(temp.value > q.value){
                        //원래 노드의 값을 update 해줘야함.
                        update(1,N,1,temp.index,1);
                        idx++;
                    }
                    else
                        break;
                }
                 

                int a = Query(1,N,1,q.start,q.end);
                ans[q.index] = a;
            }

            System.out.print("#"+t +" ");
            for(int i=0;i<Qlist.size();i++){
                System.out.print(ans[i] + " ");
            }
            System.out.println();
        }

    }

    static int update(int start, int end, int node, int index, int value) {
 
        if(index > end || index < start){
            return tree[node];
        }
        if (index == start && index == end) {
            return tree[node] = value;

        }

        int mid = (start + end) / 2;
        int leftval = update(start, mid, node * 2, index, value);
        int rightval = update(mid + 1, end, node * 2 + 1, index, value);

        return tree[node] = merge(leftval, rightval);
    
    }

    static int Query(int start, int end, int node, int left, int right) {

        if (left > end || right < start)
            return 0;

        if (left <= start && right >= end) {
            return tree[node];

        }

        int mid = (start + end) / 2;
        int leftval = Query(start, mid, node * 2, left, right);
        int rightval = Query(mid + 1, end, node * 2 + 1, left, right);

        return merge(leftval, rightval);
    }

    private static int merge(int left, int right) {
        return left + right;
    }
}

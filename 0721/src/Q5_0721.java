import java.io.*;
import java.util.*; 

public class Q5_0721 {

    static class Node implements Comparable <Node> {
        int x, y;
        Node(int x, int y){
            this.x = x;
            this.y = y;
        }
        // 정렬 기준 : 왼쪽 x부터 찾아볼수 있도록 -> x 오름차순
        @Override
        public int compareTo(Node next) {
            if(x < next.x)
                return -1;
            if(x > next.x)
                return 1;
            return 0; 
        }
    }

    static int merge(int left, int right) {
        // MAX Tree
        return Math.max(left, right);
    }

    // init
    static int init(int start, int end, int node) {
        // leaf node까지 도달
        if(start == end) {
            return tree[node] = arr[start].y;
        }
        int mid = (start + end) / 2;
        int leftval = init(start, mid, node *2);
        int rightval = init(mid+1, end, node * 2 + 1);
        return tree[node] = merge(leftval, rightval); 
    }

    //query
    static int query(int start, int end, int node, int left, int right) {

        // MAX TREE
        if (left > end || right < start)
            // 완전히 벗어나는 구간 -> 영향을 주지 않는 값 
            return -1000000001;

        if (left <= start && right >= end)
            return tree[node];

        int mid = (start + end) / 2;
        int leftval = query(start, mid, node * 2, left, right);
        int rightval = query(mid + 1, end, node * 2 + 1, left, right);
        return merge(leftval, rightval);
    }

    static int psearch(int start, int end, int target, int maxb) {

        // 중간부터 짤라보며 탐색 
        int mid = (start + end) / 2; 

        // 종료 조건 ->
        if(start > end)
            return maxb;  

        // 지금 이 점의 x값은 -> 내가 찾고자 하는 구간 내에 포함이 된다!
        if(arr[mid].x <= target) {
            // 가능성을 찾았다!
            maxb = mid;
            // 더 가볼수 있을까? 
            return psearch(mid+1, end, target, maxb); 
        }
        else {
            // 더 작은 구간을 찾아보자
            return psearch(start, mid-1, target, maxb); 
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int n ,k; 
    static Node[] arr; 
    static int[] tree; 

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        // n개만큼의 점들의 좌표 입력
        // arr init 
        arr = new Node[n+1]; 
        tree = new int[n*4]; 

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            // 1번점 ... 2번점... 
            arr[i + 1] = new Node(x, y); 
        }

        // solve 
        // 1. 모든 점을 하나씩 기준으로 둘러볼껀데 -> X는 작은거부터 찾아보기 위해 정렬
        // 0번에 그냥 엄청 큰거 
        // arr[0] = new Node(Integer.MAX, MAX) --> 그리고 마지막 노드까지는 탐색하지 않도록
        Arrays.sort(arr, 1, n+1);

        // segment tree init -> MAX TREE
        init(1, n, 1); 

        // 답을 저장할 변수
        int ans = Integer.MIN_VALUE;

        // 모든 점 한번씩 기준으로 탐색
        for(int i = 1; i <= n; i++) {
            // 지금 기준의 점 
            Node now = arr[i]; 

            // 찾을 구간 
            // |Xa - Xb| <= K (a.x < b.x)
            // Xa <= K + Xb
            // 이 식을 충족하는 구간의 최대 Y값을 찾아서 반환하고, 
            // 현재 점의 (now.y)합이 최대가 된다면 -> 갱신 
            int target = now.x + k; 
            // 가장 멀리 갈 수 있는 점을 찾아봄
            // psearch(시작, 끝, 충족값, 반환값 -> 만약 못찾으면 -1을 return); 
            int maxb = psearch(i, n, target, -1);

            // 만약 target 구간 내로 갈 수 있는 가장 먼 점이 존재한다면
            if(maxb != -1) {
                // query 때려봄 
                // 지금 내 점 뒷점 (내 점은 포함되서는 안됨
                // temp = i+1~maxb 구간 내의 "가장 큰 Y값" (max tree)
                int temp = query(1, n, 1, i+1, maxb);
                // 기준점의 Y + 지금 찾은 구간 내의 최대 Y값 > ans 
                if(temp + now.y > ans) {
                    // 갱신
                    ans = temp + now.y;
                }
            }
        }
        System.out.println(ans);
    }
}
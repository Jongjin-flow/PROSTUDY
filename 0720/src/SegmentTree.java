public class SegmentTree {
    static int[] arr = {0,1,3,5,7,9,2,4,6,8,10};

    static int[] tree;
    public static void main(String[] args) {
        
        // tree init
        int n =10;

        // 만약 배열 요소를 입력 받아야한다면 -> tree의 leaf node의 순서와 동일하게 쓸 수 있도록 1번부터 입력 받으면 편함
        // init ->> arr = new int[n+1];
        // 트리의 노드 개수 -> leaf 노드의 개수 * 4
        tree = new int [n * 4];

        // init(배열 오소의 시작, 배열 요소의 끝(포함), 트리의 노드 번호)
        init(1,n,1); // 1번부터 n번까지 노드를 1번 노드부터 만들어간다.

        //query (배열 요소의 시작, 배열 요소의 끝, 트리 노드번호, 쿼리 시작, 쿼리 끝)
        int ans = query(1, n, 1, 1, 10); 
        System.out.println(ans);
        
    }
    static int init(int start, int end, int node) {
        // 종료 조건 -> 단일 노드가 = leaf node
        if(start == end){
            // 지금 node의 위치에 배열에서의 현재 값 저장
             return tree[node] = arr[start];
        }
        // 재귀 구성 -> 분할 정복 -> 왼쪽과 오른쪽으로 내려갈 것
        int mid =  (start+end) /2;

        // 왼쪽 = 배열은 절반이 되면서 트리 번호는 *2
        int leftval = init(start, mid, node*2); //1
        // 오른쪽 = 배열은 절반이 되면서 트리 번호는 *2 +1
        int rightval = init(mid+1, end, node*2 + 1); // 3

        return tree[node] = merge(leftval,rightval);

    }
    static int merge(int leftval, int rightval) {
        // MAX TREE -> 둘중 큰 값을 올려주면 된다.
        return Math.max(leftval, rightval);

        // min Tree
        //return Math.min(leftval, rightval);
        // Sum Tree
    }   //return leftval + rightval;

     // query -> log(N)
     static int query(int start, int end, int node, int left, int right) {

        // return 조건 
        // 1. 구간을 완전히 벗어난다
        if(left > end || right < start)
            // 정답에 영향을 주지 않는 값을 return
            // ** 주의 : 상황에 따라서 어떤 값이 영향을 안주는가? 
            return Integer.MIN_VALUE; 

        // 2. 포함되는 구간이다 
        if(left <= start && right >= end)
            return tree[node]; 

        // 3. 겹치는 구간이다 --> 분할정복 
        int mid = (start + end) / 2;
        int leftval = query(start, mid, node * 2, left, right); 
        int rightval = query(mid+1, end, node * 2+1, left, right); 
        // ** 주의 : query에서는 tree에 기록은 X
        return merge(leftval, rightval); 
    }
}

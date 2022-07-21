import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q3_0721 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int [] tree;
    static int cnt = 0;
    public static void main(String[] args) throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());

        int treeSize = N * 4;
        tree = new int[treeSize];



        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(st.nextToken());

            cnt+= sumQuery(1,N,1,num+1,N);


            update(1, N, 1, num, 1);
        }

        System.out.println(cnt);

    }
    static int sumQuery(int start, int end, int node, int left, int right) {
        if(left > end || right < start)
            return 0;

        if (left <= start && right >= end)
            return tree[node];
        
        int mid = (start + end) /2;

        int leftval = sumQuery( start, mid, node*2,  left, right);
        int rightval = sumQuery(mid+1,  end, node*2 +1 ,  left,  right) ;
        return merge(leftval,rightval);

    }
    static int merge(int leftval, int rightval) {
            return leftval + rightval;
    }

    static int update(int start, int end, int node, int idx, int val) {

        if (idx < start || idx > end)
            return tree[node];

        if (start == end)
            return tree[node] = val;

        int mid = (start + end) / 2;
        int leftval = update(start, mid, node * 2, idx, val);
        int rightval = update(mid + 1, end, node * 2 + 1, idx, val);
        return tree[node] = merge(leftval, rightval);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import javax.xml.crypto.Data;

public class Q2_0721 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n; // dvd의 개수
    static int m; // query의 개수
    static int[] tree;
    static int test;
    static int[] movies;
    static int[] DAT;

    static int merge(int left, int right) {
        return left + right;
    }

    static int init(int start, int end, int node) {

        if (start == end) {
            return tree[node] = movies[start];
        }
        int mid = (start + end) / 2;
        int leftval = init(start, mid, node * 2);
        int rightval = init(mid + 1, end, node * 2 + 1);
        return tree[node] = merge(leftval, rightval);
    }

    // segtree의 query
    static int query(int start, int end, int node, int left, int right) {

        if (left > end || right < start)
            return 0;

        if (left <= start && right >= end)
            return tree[node];

        int mid = (start + end) / 2;
        int leftval = query(start, mid, node * 2, left, right);
        int rightval = query(mid + 1, end, node * 2 + 1, left, right);
        return merge(leftval, rightval);
    }

    // update
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

    public static void main(String[] args) throws IOException {
        test = Integer.parseInt(br.readLine());
        for (int t = 1; t <= test; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            // tree init
            // leaf node의 개수 n개의 dvd + m개의 공백 공간 = n + m;
            int nodecnt = n + m;
            tree = new int[nodecnt * 4];
            movies = new int[nodecnt + 1];
            DAT = new int[nodecnt + 1];

            for (int i = 1; i <= n; i++) {
                // dvd의 실제 위치 = i번 dvd는 +m -> i+m번
                // 1 = tree init
                movies[i + m] = 1;
                // i번 dvd가 실제로 tree에서 어디에 있는지 기록 - DAT
                DAT[i] = i + m;
            }

            // sum tree init
            init(1, nodecnt, 1);

            // query 입력받고 처리
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                // 지금 query로 처리할 dvd 번호
                int dvd = Integer.parseInt(st.nextToken());

                // 실제 dvd 위치 -> DAt에 기록된 지금 dvd의 번호
                int dvdnum = DAT[dvd];

                // 현재 dvd 앞에 몇개가 있는지
                int pos = query(1, nodecnt, 1, 1, dvdnum);
                // pos -> 현재 dvd가 몇번째 위치에 있는지
                System.out.print(pos + " ");

                // update -> 현재 위치 0
                update(1, nodecnt, 1, dvdnum, 0);

                // update -> 이 dvd를 앞으로 이동
                update(1, nodecnt, 1, m - i, 1);

                // dvd의 위치도 DAT에 업데이트
                DAT[dvd] = m - i;
            }
            System.out.println();
        }
    }
}
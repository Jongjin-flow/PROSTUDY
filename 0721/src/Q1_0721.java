import java.io.*;
import java.util.*;

public class Q1_0721 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n; // query의 개수 
    static int cnt = 0;
    static int[] tree;

    static int merge(int left, int right) {
        return left + right; 
    }

    // segtree의 query
    static int query(int start, int end, int node, int left, int right) {

        if(left > end || right < start)
            return 0; 

        if(left <= start && right >= end)
            return tree[node]; 

        int mid = (start + end) / 2;
        int leftval = query(start, mid, node * 2, left, right);
        int rightval = query(mid+1, end, node * 2 + 1, left, right);
        return merge(leftval, rightval); 
    }

    // update 
    static int update(int start, int end, int node, int idx, int val) {

        if(idx < start || idx > end)
            return tree[node];

        if(start == end)
            return tree[node] = val; 

        int mid = (start + end) / 2;
        int leftval = update(start, mid, node * 2, idx, val);
        int rightval = update(mid+1, end, node * 2 + 1, idx, val);
        return tree[node] = merge(leftval, rightval); 
    }   

    public static void main(String[] args) throws IOException {
        // n input
        n = Integer.parseInt(br.readLine()); 

        // 총 학생의 수
        int total = 1000000; 

        // tree init
        tree = new int [total * 4]; 

        // query input
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());

            if(cmd == 1) {
                /*
                // 순위에 맞춰 어차피 + 1을 한 순위를 출력해야 함
                int rank = query(1, total, 1, 1, num-1);
                System.out.println(rank + 1);
                */ 

                // num번이 대회 참가! --> update 
                // num번을 참가했다! 라는 의미로 1로 업데이트
                update(1, total, 1, num, 1);

                // num번의 순위 출력
                int rank = query(1, total, 1, 1, num);
                System.out.println(rank + "위");

                // 한명이 더 대회에 참가한다!
                cnt++; 
            }

            if(cmd == 2) {
                // num이 대회 참여를 취소한다
                // num이 참가 - > segtree 1 --> update --> 0
                update(1, total, 1, num, 0);

                // 한명이 대회에서 나갔다!
                cnt--; 

                System.out.println(cnt + "명");
            }
        }
    }
}
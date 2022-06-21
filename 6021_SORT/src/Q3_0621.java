import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q3_0621 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static Tree tree[];
    static int dat[];
    static boolean check;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int t = Integer.parseInt(st.nextToken());
        // 그룹화 index = 통나무 번호, value는 그룹
        dat = new int[n+1];

        tree = new Tree[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            tree[i] = new Tree(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()), i+1);
        }

        Arrays.sort(tree);

        Tree cur = tree[0];
        int g = 1;
        dat[cur.number] = g;

         // 그룹화
         for (int i = 1; i < n; i++) {
            Tree next= tree[i];

            if(next.x1 <= cur.x2)
            {
                // --------
                //    -------
                // ------
                //   ---
                
                //지금 선택한 통나무의 끝점 > 이전 통나무의 끝점보다 멀리 간다면
                if(next.x2 > cur.x2){
                    cur = next;
                }
            }
            else{
                // 새로운 group
                g++;
                // 비교할 통나무 갱신
                cur = next;
            }

            dat[next.number] = g;
           
        }

        /*
        // 그룹화
        for (int i = 1; i < n; i++) {

            if (tree[i].x1 <= tree[i - 1].x2) {
                tree[i].number = tree[i - 1].number;

                tree[i].x1 = tree[i-1].x1;

                if(tree[i].x2  <=  tree[i-1].x2){
                    tree[i].x2 = tree[i-1].x2;
                }

            }
        }
        */

        for (int k = 0; k < t; k++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            if (dat[start] == dat[end]) {
                bw.write(1 + "\n");
            } else {
                bw.write(0 + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    static public class Tree implements Comparable<Tree> {
        int x1;
        int x2;
        int y;
        int number;


        public Tree(int x1, int x2, int y, int number) {
            this.x1 = x1;
            this.x2 = x2;
            this.y = y;
            this.number = number;
        }

        @Override
        public int compareTo(Tree o) {
            // TODO Auto-generated method stub
            // start 기준 가장 작은 것부터
            if (x1 < o.x1) {
                return -1;
            }
            if (x1 > o.x1) {
                return 1;
            }
            if (x2 < o.x2)
                return -1;
            if (x2 > o.x2)
                return 1;    

            return 0;
        }
    }
}

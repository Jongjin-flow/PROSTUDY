import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Q2_0705 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M, K, P;
    static ArrayList<NODE> node = new ArrayList<>();
    static ArrayList<Integer> al[];
    static ArrayList<Integer> sol = new ArrayList<>();

    static void bfs(NODE node1) throws IOException {

        Queue<NODE> q = new LinkedList<>();
        q.add(node1);
        int[] visited = new int[N];
        visited[node1.number] = 1;

        while (!q.isEmpty()) {
            NODE now = q.poll();

            if (now.number != (P - 1))
                now.gas++;

            if(now.gas > K){
                break;
            }

            if (now.gas == K) {
                sol.add(now.number);
                continue;
            }

            for (int i = 0; i < now.go.size(); i++) {
                int num = now.go.get(i);
                if (visited[num - 1] == 1)
                    continue;

                visited[num - 1] = 1;
                NODE temp = node.get(num - 1);
                temp.gas = now.gas;
                q.add(temp);
            }
        }
    }

    public static void main(String[] args) throws Exception {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // NODE
        M = Integer.parseInt(st.nextToken()); // 선
        K = Integer.parseInt(st.nextToken()); // 남은 기름통
        P = Integer.parseInt(st.nextToken()); // 출발지

        for (int i = 0; i < N; i++) {
            node.add(new NODE(i));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            node.get(start - 1).go.add(end);
        }

        bfs(node.get(P - 1));

        if (sol.size() == 0) {
            bw.write(-1 + "\n");
        } else {
            Collections.sort(sol);
            for (int i = 0; i < sol.size(); i++) {
                bw.write(sol.get(i) + 1 + "\n");
            }
        }
        bw.flush();
        bw.close();
    }

    static class NODE {
        int number;
        int gas;
        ArrayList<Integer> go = new ArrayList<>();

        public NODE(int number) {
            this.number = number;
            this.gas = 0;
        }
    }
}

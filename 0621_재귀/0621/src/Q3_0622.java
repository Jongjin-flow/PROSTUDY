import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.StringTokenizer;

/*
Backtracking!

*/
public class Q3_0622 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N, M;
    static int path[] = new int[6];

    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        if (M == 1)
            func(0);
        if (M == 2)
            func2(0);
        if (M == 3)
            func3(0);

    }

    static int[] DAT = new int[7];

    private static void func3(int index) {
        // 기저 조건
        if (index == N) {
            // path 출력
            for (int i = 0; i < N; i++) {
                System.out.print(path[i] + " ");
            }
            System.out.println();
            return;
        }

        // 재귀 수행
        for (int i = 1; i <= 6; i++) {
            // 3. 가지치기
            // 이미 이전에 사용된 눈금이라면 pass
            // 사용된 눈금 기록 -> dat
            // index : 눈금 번호, value는 사용(1), 사용되지 않음(0)

            if (DAT[i] == 1)
                continue;

            DAT[i] = 1;
            path[index] = i;
            func3(index + 1);

            path[index] = 0;
            DAT[i] = 0;

        }
    }

    private static void func2(int index) {
        // 기저 조건
        if (index == N) {
            // path 출력
            for (int i = 0; i < N; i++) {
                System.out.print(path[i] + " ");
            }
            System.out.println();
            return;
        }

        // 재귀 수행
        for (int i = 1; i <= 6; i++) {
            if (index > 0)
                if (i < path[index - 1])
                    continue;

            path[index] = i;
            func2(index + 1);

            path[index] = 0;
        }

    }

    // 모든 수;
    private static void func(int index) {
        // 기저 조건
        if (index == N) {
            // path 출력
            for (int i = 0; i < N; i++) {
                System.out.print(path[i] + " ");
            }
            System.out.println();
            return;
        }

        // wornl 수행
        for (int i = 1; i <= 6; i++) {
            path[index] = i;
            func(index + 1);

            path[index] = 0;
        }

    }
}

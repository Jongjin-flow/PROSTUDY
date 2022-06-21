import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q3 {

    static int W;
    static int H;
    static int map[][];
    static int result;

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        input();
        cal();
        output();
    }
    /*
     * 1. 모든 위치에서부터 사각형을 만들어 볼겁니다. (중첩 for)
     * 2. 그 시작위치로부터 만들 수 있는 모든 사각형의 크기를 설정
     * 3. 그 구간만큼의 합이 최대치인 값을 찾는 문제
     ** 주의 : 사각형 내에 0이 포함되면 안됩니다.
     * 
     * // 시작 위치
     * for(행)
     * for(열)
     * for(만들수있는 y크기)
     * for(만들수 있는 x 크기)
     * for(시작위치 -> y크기만큼)
     * for(시작위치 -> x 크기만큼)
     * 사각형의 합 -> 최대치 갱신
     ** 주의사항 : 0이 포함되면 안된다. \
     */

    private static void cal() {
        // 모든위치에서 다각형 만들어본다.(중첩 for)
        // 시작 위치로부터 만들 수 있는 사각형의 크기 설정

    }

    private static void output() throws IOException {
        bw.write(result + "\n");
    }

    private static void input() throws IOException {
        st = new StringTokenizer(br.readLine());

        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
    }
}

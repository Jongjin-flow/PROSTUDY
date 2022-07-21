import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;

public class Q2_0720 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n; 
    static int[][] MAP; 

    static boolean isCompressed(int pixel, int y, int x, int size) {
        for(int i = y; i < y+ size; i++) {
            for(int j = x; j < x+size; j++) {
                // 지금 접근하고 있는 픽셀 값이 기준 픽셀값과 다르다면 :
                if(MAP[i][j] != pixel)
                    // 추가 압축이  필요 
                    return false; 
            }
        }
        return true; 
    }

    // 분할정복 
    static void dc(int y, int x, int size) {

        // 기준 위치의 픽셀값 (0 또는 1)
        int cur = MAP[y][x]; 

        // 해당 size x size의 구간 단일 노드로 구성되어있는가?
        if(isCompressed(cur, y, x, size)) {
            // 기준 픽셀 출력
            System.out.print(cur);
        }
        else {
            // 분할 + 정복 
            // 분할 들어갈때 괄호 
            System.out.print("(");

            // 다음 구간( 분할정복된 부분)의 크기 = size의 절반 
            int half = size / 2; 

            // 왼쪽 위
            // 기준 y, x위치는 같고, 크기만 달라짐
            dc(y, x, half);

            // 오른쪽 위
            dc(y, x + half, half);

            // 왼쪽 아래
            dc(y+ half, x, half);

            // 오른쪽 아래 
            dc(y+ half, x+half, half);

            // 정복하고 나올때 괄호 
            System.out.print(")");
        }
    }

    public static void main(String[] args) throws IOException {
        // n input
        n = Integer.parseInt(br.readLine());

        // MAP init
        MAP = new int[n][n]; 

        // MAP input
        for(int i = 0; i < n; i++) {
            String inp = br.readLine();
            for(int j = 0; j < n; j++) {
                MAP[i][j] = inp.charAt(j) -'0'; 
            }
        }

        // D&C (Divide & Conquer)
        // dc(y의 기준좌표, x의 기준좌표, 내가 지금 체크해야 할 크기)
        dc(0, 0, n);
    }
}
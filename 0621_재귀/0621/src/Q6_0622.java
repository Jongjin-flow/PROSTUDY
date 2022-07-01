import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
Backtracking!

*/
public class Q6_0622 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int[] members = { 1004, 1680, 9941, 3367, 3261, 2976, 4889, 1234, 6461, 7329, 5518 };

    // 인접 행렬
    static int[][] matrix = new int[11][11];

    public static void main(String[] args) throws IOException {
        // from -> to 연결
        // 인접행렬 하드코딩
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[1][3] = 1;
        matrix[1][4] = 1;
        matrix[2][5] = 1;
        matrix[2][6] = 1;
        matrix[4][7] = 1;
        matrix[4][8] = 1;
        matrix[5][9] = 1;
        matrix[5][10] = 1;

        st = new StringTokenizer(br.readLine());
        int number = Integer.parseInt(st.nextToken());
        boolean isMember = false;
        for (int i = 0; i < members.length; i++) {

            if (members[i] == number) {
                isMember = true;
                int boss = -1;
                // 상사 찾기
                for (int j = 0; j < 11; j++) {
                    if (matrix[j][i] == 1) {
                        System.out.println(members[j]);
                        boss = j;
                    }
                }

                if (boss == -1) {
                    System.out.println("no boss");

                }

                // 동료찾기
                int com = -1;
                if (boss != -1) {
                    for (int j = 0; j < 11; j++) {
                        if (matrix[boss][j] == 1 && j != i) {
                            System.out.println(members[j]);
                            com = 1;
                        }
                    }
                }

                if (com == -1) {
                    System.out.println("no company");
                }

                // 부하찾기
                int junior = -1;
                for (int j = 0; j < 11; j++) {
                    if (matrix[i][j] == 1) {
                        System.out.print(members[j] + " ");
                        junior = 1;
                    }
                }

                if (junior == -1) {
                    System.out.println("no junior");
                    break;
                }

                break;
            }
        }

        if (isMember == false)

        {
            System.out.println("no person");
        }

    }
}

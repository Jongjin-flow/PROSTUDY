import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2_0704 {
    /*
    3
    4
    1bacDBTAab12D
    3
    734734734
    2
    10101010111

    #1 PASS
    #2 PASS
    #3 FAIL
    */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int T, M;
    static int[] arr;
    static int[] dat;

    public static void main(String[] args) throws Exception {
        // 5개의 구간의 합을 출력하는 sliding window code

        // 1. 공통 구간 세팅
        // 공통 구간 : 시작 -> 구간 크기 -1

        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());

            String str = br.readLine();

            arr = new int[str.length()];
            dat = new int[100001];

            for (int i = 0; i < arr.length; i++) {
                arr[i] = str.charAt(i);
            }

            // 공통 구간 설정

            int start = 0;
            int end = M - 1;
            int sum = 0;
            boolean isCheck = false;

            for (int i = start; i < end; i++) {
                dat[arr[i]]++;

                if (dat[arr[i]] > 1) {
                    isCheck = false;
                    break;
                }

                isCheck = true;
            }

            // 수행 및 포인터 이동
            while (end < arr.length && isCheck == true) {
                dat[arr[end]] ++;



                if(dat[arr[end]] > 1){
                    isCheck = false;
                    break;
                }

                dat[arr[start]] --;
                end++;
                start++;

            }

            if (isCheck == true) {
                bw.write("#" + (t + 1) + " PASS\n");
            } else {
                bw.write("#" + (t + 1) + " FAIL\n");

            }
        }

        bw.flush();
        bw.close();
    }
}

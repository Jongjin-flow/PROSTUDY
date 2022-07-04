import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q3_0704 {
     /*
    2
    7 2
    65 66 81 86 65 71 82
    8 2
    65 65 81 66 65 65 69 69

    #1 YES
    #2 NO
    */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int T, N, R;
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
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            arr = new int[N*2];
            dat = new int[201];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int num = Integer.parseInt(st.nextToken());
                arr[i] = num;
                arr[N+i]= num;
            }

            // 공통 구간 설정

            int start = 0;
            int end = R*2;

            boolean isCheck = false;

            for (int i = start; i < end; i++) {
               dat[arr[i]] ++;
                
               if(dat[arr[i]] > 2)
               {
                isCheck = false;
               }

                isCheck = true;
            }

            // 수행 및 포인터 이동
            while (end < N*2 && isCheck == true) {
                dat[arr[end]]++;

                if (dat[arr[end]] > 2) {
                    isCheck = false;
                    break;
                }

                dat[arr[start]]--;
                end++;
                start++;

            }

            if (isCheck == true) {
                bw.write("#" + (t + 1) + " YES\n");
            } else {
                bw.write("#" + (t + 1) + " NO\n");

            }
        }

        bw.flush();
        bw.close();
    }
}

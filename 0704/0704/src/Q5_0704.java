import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q5_0704 {
    /*
     * 문제 이해 :
     * -> 2개의 솔루션을 섞어 0에 가장 가까운 조합을 찾는 것
     * -> 추가 조건 : 여러가지 조합이 있을 경우, 가장 작고 큰 것 위주로 출력
     * 
     * 1. 가장 큰 거 + 가장 작은거 더 해보면서 -> 상황에 따라 값을 바꿔 넣어본다.
     * 2. 합 < target -> 더 큰 값을 더해보고
     * 합 > target -> 더 작은 값을 더해보고
     * 5
     * -2 4 -99 -1 98
     * # -99 98
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, cnt = 0;
    static long M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }
        Arrays.sort(arr);

        int Target = 0;
        int solsum = Integer.MAX_VALUE;
        // 포인터 설정.

        int left = 0;
        int right = N - 1;

        int diff = Integer.MAX_VALUE;
        int solmax = 0;
        int solmin = 0;
   
        while (right != left) {

            int sum = arr[left] + arr[right];

            if(sum == 0)
            {
                System.out.println(arr[left] + " "+ arr[right]);
                return;
            }
            // 0이라는 답이 안나올 경우 0에서 가장 가까운 합을 만들던 두개의 솔루션을 출력

            if(Math.abs(sum) < diff){
                // 갱신
                diff = Math.abs(sum);
                solmax = arr[right];
                solmin = arr[left];
            }

            if(sum > 0)
            {
                right--;
            }
            else if (sum < 0){
                left++;
            }
        }

        bw.write(solmin + " " + solmax + "\n");
        bw.flush();
        bw.close();
    }
}

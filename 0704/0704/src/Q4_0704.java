import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q4_0704 {
    /*
    투 포인터
    -> [배열] 내에서 두 개의 포인터를 활용하여 저희가 찾고자 하는 무언가 target를 찾는 알고리즘
    -> sliding window -> pointer의 동작이 항상 균일


    일반적인 two pointer -> 조건에 따라 각각의 포인터를 따로 동작
    ex)
    1 2 3 4 5 1 9 10 1 9 1

    sub-array(연속된 배열안의 작은 배열)에서 합이 10이 되는 구간은 몇개인가?

    투포인터를 활용하지 않는다면?
    for(int i=0; i< n;i++){  // 구간의 크기
        for (int j0;j<n;j++){ // 시작 위치
            for(int k = j; k<j+i ; k++){  // 시작~크기까지 subarray
                //합이 몇이냐?
            }
        }
    }
    => O(n^3) 

    투 포인터를 사용한다면?
    (L)
    *
    1 2 3 4 5 1 9 10 1 9 1
    *
    (R)

    sum = 0
    target =10

    # 포인터의 이동 : 가능성에 의해 방향이 정해진다.

    (L)
    *
    1 2 3 4 5 1 9 10 1 9 1
          *
         (R)
    sum = 1 +2 +3+6 =10 

    sum < target
    1. right를 더하면서 이동하면서 target을 찾는다.
    2. target에 부응하는 구간을 찾으면 -> 모든 탐색을 종료할 수 있는 가능성의 방향  -> left 또는 right 포인터가 n개의 모든 요소를 확인해야 종료 (while right < n) -> 이 떄에도 right 포인터를 이동 (summ == target)
    3. sum > target
       -> left 포인터 가동 -> 맨 앞의 값부터 빼보기 시작
       -> 찾으면 다시 right 포인터 이동

     * 4 2
     * 1 1 1 1
     * 
     * #3
     * 
     * 10 5
     * 1 2 3 4 2 5 3 1 1 2
     * 
     * #3
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N,cnt =0;
    static long M;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Long.parseLong(st.nextToken());

        arr = new int[N+1];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = num;
        }
        arr[N] = 0;

        // 포인터 설정.

        int left= 0;
        int right = 0;
        int sum = 0;
        
        while (right < N+1) {
           if(sum < M)
           {
                sum =  sum + arr[right];
                right ++;
           }
           else if (sum > M){
                sum = sum - arr[left];
                left ++;
           }
           else{
                cnt++;
                sum = sum + arr[right];
                right++;
           }

        }

        bw.write(cnt+"\n");
        bw.flush();
        bw.close();
    }
}

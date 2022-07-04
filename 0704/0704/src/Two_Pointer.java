import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Two_Pointer {
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
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N;
    static long M;

    // 마지막 구간까지 확인하기 위해 배열의 크기를 N+1로 설정
    static int[] arr = {1,2,3,4,6,1,9,10,9,1,0};

    public static void main(String[] args) throws Exception {
        //StringTokenizer st = new StringTokenizer(br.readLine());

        // 1. 포인터 설정
        // two pointer에서는 포인터의 위치가 둘 다 왼쪽부터 시작할거란 보장 X
        int left = 0;
        int right = 0;

        // input으로 받을텐데 target 설정
        int target =10;
        int sum =0;

        // sliding windwo처럼 이동
        // 상황에 따라서 종료 조건에 신경을 좀더 신경 써야함

        while(right <= 10){
            // 가능성에 따라 포인터 이동 방향 설정
            // 1. sum > target
            if(sum > target)
            {
                sum =  sum - arr[left];
                left ++;
            }

            // 2. sum < target
            else if(sum < target)
            {
                sum =  sum + arr[right];
                right ++;
            }

            // 3. sum ==  target

            else
            {
                for(int i= left; i<right ;i++){
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
                sum = sum + arr[right];
                right++;
            }
        }

       

       
        //bw.write("#" + (t + 1) + " NO\n");

        

        //bw.flush();
        //bw.close();
    }
}

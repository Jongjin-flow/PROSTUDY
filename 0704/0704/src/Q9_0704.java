import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q9_0704 {
    /*
    psearch
    -> 지금 이 점수로 t개의 팀을 만들 수 있는가?

    headsum이 가장 작을 때 :  teamcnt == n -> 모두가 각자의 팀 - > headsum  가장 작게 -> 200
    headsum이 가장 높을 때 :  tamcnt == 1 -> 모두가 하나의 팀 - >  모든 능력치를 더한다. -> 880


    */

    static int arr[];

   
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, T;
    
    static void psearch(int num){
        // 1. 구간 설정
        int left = minabil;
        int right = maxabil;
        int ans = 0;


        // 탐색
        while(left <= right)
        {
            // 1.BS 항상 "중간"만을 확인
            int mid = (left + right) / 2;
            // 이 점수로 t개의 팀을 만들 수 있는가?
            // mid 기준으로 팀을 만들어봄
            // teamcnt : mid의 점수로 만들 수 있는 최소한의 팀 개수
            // teamcnt == t? 어느 방향으로 가야하는가?
            int teamcnt = maketeam(mid);

            // 방향성/ 가능성 설절
            // 너무 많은 팀이 만들어졌다! -> 기준 점수가 너무 낮다 >  기준 점수를 높여봄
            if(teamcnt > t){
                left = mid +1;
            }

            // 너무 적은 팀이 만들어졌다. -> 여기서 가능성을 확인
            // -> 기준 점수 너무 높다 -> 기준 점수 낮취봄
            else{
                right = mid -1;

                // 여기서 가능성 확인 -> 정답을 갱신
                
            }

            //방향 설정
            // mid >  target
            if(arr[mid] > num){
                right =  mid-1;
            }
            // mid < target
            else if(num > arr[mid])
            {
                left = mid +1;
            }
            // mid = target
            else{
                return;
            }
        }

    }
    // 주어진 abil로 최소 몇개의 팀을 만들 수 있는가?
    private static int maketeam(int mid) {
        int teamcnt = Integer.MAX_VALUE;

        for(int i =0;i<n; i++){
            int sum = 0; // 하나의 팀의 능력치의 합
            int cnt = 1; // 현재까지 만들어진 팀의 수
            // i = 시작 위치 학생
            // 무조건 n명의 학생을 다 둘러봐야함.
            for(int j =i;j<i +N; j++){
                // 계속해서 하나의 학생을 abil 내에 현재 팀에 포함 시킬 수 있으면 넣어봄
                if(sum + arr[j%N] < mid){
                    sum += arr[j%N];
                }else{
                    sum = arr[j%N];
                    cnt ++;
                }

            }

            if (cnt < teamcnt) 
        {
            teamcnt = cnt;
        }       
        return teamcnt;
    }

        return 0;
    }
    static int maxabil =0;
    static int minabil = -1;
    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        for(int i = 0;i<N;i++)
        {
            arr[i] = Integer.parseInt(st.nextToken());
            // 최대값 =  t의 개수가 1개 (모두가 한팀)
            maxabil += arr[i];
            // 최소값
            if(arr[i] > minabil){
                minabil = arr[i];
            }
        }
       
        for(int k=0;k<N;k++){
            psearch(k);
        }
    }
}

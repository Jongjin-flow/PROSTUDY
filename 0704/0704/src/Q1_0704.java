import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q1_0704 {
    /*
    2
    10 5
    2 5 2 6 5 -3 9 4 2 -7 
    5 2
    -1 -2 -3 -4 -5

    #1 3 7 21
    #2 0 1 -3
    */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int T;
    static int W, N;
    static long sum =0;
    static int [] arr;
    static long MAX = Long.MIN_VALUE;
    static int Max_Start = 0;
    static int Max_End = 0;

    public static void main(String[] args) throws Exception {
        // 5개의 구간의 합을 출력하는 sliding window code

        // 1. 공통 구간 세팅
        // 공통 구간 : 시작 -> 구간 크기 -1
        
        StringTokenizer st = new StringTokenizer(br.readLine());

        T = Integer.parseInt(st.nextToken());

        


        for(int i=0;i<T;i++)
        {
            st = new StringTokenizer(br.readLine());
            
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            MAX = Long.MIN_VALUE;
            Max_Start = 0;
            Max_End = 0;
            sum = 0;

            int start = 0;
            int end = W -1;

            arr = new int [N];
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                arr[j] = Integer.parseInt(st.nextToken());
            }


            // 공통 구간 설정
            for (int k = start;k < end; k++) {
                sum += arr[k];
            }

            // 수행
            while(end < N)
            {
                sum = sum + arr[end];
            
                if(sum > MAX)
                {
                    MAX = sum;
                    Max_End = end;
                    Max_Start = start;
                }

                
                // 포인터 이동, 공통구간 세팅
                sum =  sum - arr[start];
                start = start +1;
                end = end +1;
            }

            bw.write("#"+(i+1) + " " +Max_Start+ " " + Max_End + " " + MAX + "\n");
        }
        bw.flush();
        bw.close();
    }
}

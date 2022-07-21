import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q1_0720 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] arr;
    static int N, K;
    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<K;i++)
        {
            int find = Integer.parseInt(st.nextToken());

            //BS(시작,끝, 찾는값)
            bs(0,N,find);
        }
        
    }
    static int bs(int start, int end, int find) {

        // 2. 기저조건 -> 만약 start와 end 구간이 겹치면 (안만나면)
        if(start >= end)
        {
            System.out.print("X"); // 못찾았다
            return -1;
        }

        // 1. 재귀 구성
        // BS : 1. 정렬 필수/ mid를 활용해서 체크
        int mid = (start + end) / 2;

        if(arr[mid] > find)
        {
            return bs(start, mid, find);
        }
        else if(arr[mid] < find){
            return bs(mid+1, end, find);
        }
        else{
            System.out.print("O"); // 찾았다
            return mid;
        }
    }
}

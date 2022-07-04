import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q8_0704 {
    /*
    숫자맞추기 게임
    이진탐색은 필수로 정렬된 형태여야한다.!!!!!!!
    */

    static String str;

    static void psearch(){
        // 1. 구간 설정
        int left = 0;
        int right = str.length() -1;
        int ans = -1;

        // 탐색
        while(left <= right)
        {
            // 1.BS 항상 "중간"만을 확인
            int mid = (left + right) / 2;

            //방향 설정
            // mid >  target
            if(str.charAt(mid) == '#'){
                ans = mid;

                left = mid + 1;
            }
            else{
                right = mid -1; 
            }
        }

        if(ans == -1){
            System.out.println("0%");
        }else{
            System.out.println((ans+1) * 100 / str.length() + "%");
        }
    }
   
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    
    static void BinarySearch(int num){
        // 1. 구간 설정
        int left = 0;
        int right = n-1;

        // 탐색
        while(left <= right)
        {
            // 1.BS 항상 "중간"만을 확인
            int mid = (left + right) / 2;

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
                System.out.print("O");
                return;
            }
        }

        //끝까지 찾아봤는데 없다.
        System.out.print("X");
    }

    static int arr[];

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
    
        //st = new StringTokenizer(br.readLine());


        for(int i = 0;i<n;i++)
        {

            str = br.readLine();
            psearch();
          
        }
       
        //bw.write("#" + (t + 1) + " NO\n");

        

        //bw.flush();
        //bw.close();
    }
}

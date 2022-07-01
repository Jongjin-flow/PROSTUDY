import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
Backtracking!

*/
public class Q4_0622 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int N;
    static int map[][];
    static int path[] = new int[6];
    static int cnt = 0;
    static int[] datx;
    static int[] daty;

    /*
    행 = banch
    열 = level
    */
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        datx = new int[N];
        daty = new int[N];

    
        func(0);
    
        System.out.println(cnt);
    }

    // 모든 수;
    private static void func(int row) {
        // 기저 조건
        // 모든 행에 캐슬을 하나씩 놓을 수 있다면, 찾음
        if(row == N-1)
        {
            cnt++;
            return;
        }

        // 수행 조건
        for(int i=0; i< N ; i++){
            // 다음 행으로 넘어라가!
            // 기록 -> 어떤 열에 두었는가? - >DAT
            //index : 열번호 , value : 두었는가? (1,0)

            if(daty[i] == 1){
                continue;
            }

            daty[i] = 1;
            func(row+1);
            daty[i] = 0;
        }
        
    }
}

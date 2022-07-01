import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.Buffer;
import java.util.StringTokenizer;

public class Q2_0622 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int cnt = 1;
    static int branch, level;
    public static void main(String[] args) throws Exception {
        st = new StringTokenizer(br.readLine());

        branch = Integer.parseInt(st.nextToken());
        level = Integer.parseInt(st.nextToken());

        func(0);

        System.out.println(cnt);
    }
    private static void func(int index) {
        // 기저 조건
        if(index == level){
            return;
        }
        // 수행
        for(int i =0; i< branch;i++){
            cnt ++; 
            func(index+1);
        }
        // 수행 후

    }
}

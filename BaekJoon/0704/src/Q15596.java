import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q15596 {
    public static void main(String[] args) throws Exception {
    }
    static long sum(int[] a) {
        long sum = 0;

        for(int i=0;i<a.length ;i++){
            sum = sum + a[i];
        }
        return sum;
    }


}

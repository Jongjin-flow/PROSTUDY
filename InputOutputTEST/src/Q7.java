import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q7 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static String dat[];
    static int dat_enter[];

    static int n;

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        dat = new String[10000];
        dat_enter = new int [10000];

        st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());

            int number = Integer.parseInt(st.nextToken());

            if (type == 1) {
                String name = st.nextToken();
                new_member(number, name);
            } else {
                EnterOrExit(number);
            }

        }
        bw.flush();
        bw.close();
    }

    private static void EnterOrExit(int number) throws IOException {
        if (number < 1000 || number > 9999) {
            bw.write(number + " " + "ERROR\n");
            return;
        }

        if(dat_enter[number] == 0 && dat[number] != null){
            dat_enter[number] = 1;
            bw.write(number + " " + dat[number] + " ENTER\n");
            return;
        }
        else if (dat_enter[number] == 1 && dat[number] != null){
            dat_enter[number] = 0;
            bw.write(number + " " + dat[number] + " EXIT\n");
            return;
        }else{
            bw.write(number + " " + "ERROR\n");
            return;

        }
    }

    private static void new_member(int number, String name) throws IOException {

        if (number < 1000 || number > 9999) {
            bw.write(number + " " + "ERROR\n");
            return;
        }

        if (dat[number] == null) {
            dat[number] = name;
            bw.write(number + " " + "OK\n");
            return;
        } else if (dat[number] != null) {
            bw.write(number + " " + "ERROR\n");
            return;
        }

    }
}

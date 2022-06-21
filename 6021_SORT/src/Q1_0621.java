import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q1_0621 {
    static BufferedReader br;
    static BufferedWriter bw;
    static Str str[];
    static int n;
    static Integer arr[];
    public static void main(String[] args) throws IOException{
        br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer number = new StringTokenizer(br.readLine());
        n = Integer.parseInt(number.nextToken());
        arr = new Integer[n];
        char chr[] = new char[n];

        StringTokenizer row1 = new StringTokenizer(br.readLine());
        StringTokenizer row2 = new StringTokenizer(br.readLine());
        str = new Str[n];
        for(int i =0; i<n; i++){
            arr[i] = Integer.parseInt(row1.nextToken());
            chr[i] = row2.nextToken().charAt(0);
            str[i] = new Str(arr[i], chr[i]);
        }

        type1_sort();
        Arrays.sort(arr, Collections.reverseOrder());
        type2_sort();
        Arrays.sort(str);
        type3_sort();
    }


    static class Str implements Comparable <Str>{
        int a;
        char b;

        public Str(int a, char b){
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Str o) {
            // TODO Auto-generated method stub
            if (a % 2 == 0 && o.a % 2 == 1)
            {
                return -1;
            }
            if (a % 2 == 1 && o.a % 2 == 0)
            {
                return 1;
            }
            if (a > o.a)
            {
                return 1;
            }
            if (a < o.a)
            {
                return -1;
            }
            if (b > o.b)
            {
                return 1;
            }
            if (b < o.b)
            {
                return -1;
            }

            return 0;
        }
    }
    
    public static void type1_sort(){
        Arrays.sort(arr);
        for(int i=0; i<n; i++){
            System.out.print(arr[i]);

            if(i<n-1){
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    public static void type2_sort(){
        for(int i=0; i<n; i++){
            System.out.print(arr[i]);

            if(i<n-1){
                System.out.print(" ");
            }
        }
        
        System.out.println();
    }

    public static void type3_sort(){

        for(int i=0; i<n; i++){
            System.out.print(str[i].a);

            if(i<n-1){
                System.out.print(" ");
            }
        }
        System.out.println();
        for(int i=0; i<n; i++){
            System.out.print(str[i].b);

            if(i<n-1){
                System.out.print(" ");
            }
        }

    }

}

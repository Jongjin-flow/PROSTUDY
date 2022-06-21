import java.io.*;
import java.util.StringTokenizer;

public class Q1 {
    static StringTokenizer st;
    static BufferedWriter bw;
    static BufferedReader br;
    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int type = Integer.parseInt(st.nextToken());

        if (type == 1){
            type_1();
        }else if (type == 2)
        {
            type_2();
        }
        else{
            type_3();
        }
    

        br.close();
        bw.close();
    }


    static void type_1() throws IOException{
        st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int hap = 0;
        long gob = 1;
        st = new StringTokenizer(br.readLine());

        for(int i=0;i<n; i++){
            int temp = Integer.parseInt(st.nextToken());
            hap = hap + temp;
            gob = gob * temp;
        }

        bw.write(hap + " " + gob +"\n");
        
    }

    static void type_2() throws IOException{
        st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        String max = "";
        String min = "";

        for(int i=0; i<n;i++)
        {
            String temp = br.readLine();

            if(max.length()<1){
                max =temp;
            }

            if(min.length()<1){
                min = temp;
            }

            if(max.length() < temp.length())
            {
                max = temp;
            }

            if(min.length() > temp.length())
            {
                min = temp;
            }
        }


        bw.write(max + "\n");
        bw.write(min + "\n");

    }
    static void type_3() throws IOException{
        st = new StringTokenizer(br.readLine());
        
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int map [][] = new int[y][x];
        int min = -2134567890;
        int cnt = 0;
        for(int i = 0 ; i<y; i++)
        {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<x;j++){
                map[i][j] = Integer.parseInt(st.nextToken());

                if(min == -2134567890){
                    min = map[i][j];
                    cnt ++;
                }
                else{
                    if(map[i][j] <= min){
                        if(map[i][j] != min)
                        {
                            min = map[i][j];
                            cnt = 1;
                        }
                        else if(map[i][j] == min){
                            cnt++;
                        }
                    }
                }
            }
        }

        bw.write(min + "\n");
        bw.write(cnt + "개\n");
    }

}
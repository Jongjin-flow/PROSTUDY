import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q6_0704 {
    /*
     * 문제 이해 :
     * -> 2개의 솔루션을 섞어 0에 가장 가까운 조합을 찾는 것
     * -> 추가 조건 : 여러가지 조합이 있을 경우, 가장 작고 큰 것 위주로 출력
     * 
     * 1. 가장 큰 거 + 가장 작은거 더 해보면서 -> 상황에 따라 값을 바꿔 넣어본다.
     * 2. 합 < target -> 더 큰 값을 더해보고
     * 합 > target -> 더 작은 값을 더해보고
     * 5
     * -2 4 -99 -1 98
     * # -99 98
     */
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static int N, M;
    static long[] arr;
    static long[] finalArr;

    public static void main(String[] args) throws Exception {
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new long[M];
        finalArr = new long[N*M];
        int k = 0;
        for(int i =1;i<N+1 ;i++){
            st = new StringTokenizer(br.readLine());

            for(int j = 0;j<M;j++){
                arr[k] =  Integer.parseInt(st.nextToken());
                k++;
            }

        }



    
        bw.flush();
        bw.close();
    }


    static class Student implements Comparable<Student>{
        int classNumber;
        int score;

        public Student(int classNumber, int score){
            this.classNumber = classNumber;
            this.score = score;
        }

        @Override
        public int compareTo(Student next) {
            // TODO Auto-generated method stub
            if(score < next.score)
                return -1;
            if(score > next.score)
                return 1;
                
            return 0;
        }



    }
}

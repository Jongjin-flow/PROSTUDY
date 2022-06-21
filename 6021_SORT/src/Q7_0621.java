import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Q7_0621 {
    /*
    Ugly Number

    문제 이해 단계
    1. 최대 10,000 쿼리
    2. 1500개의 최대 쿼리
    3. ugly number 란 1부터 시작해서 2,3,5의 배수


    1차원적인 접근
    int n;
    while(n%2==0) n/=2;
    while(n%3==0) n/=2;
    while(n%5==0) n/=2;

    if(n==1)-> ugly number

    --- 이 방법은 너무 오래 걸린다. => ugly number를 쿼리마다 계산하면 안되겠구나!
    ->> DAT를 사용해야한다. N번쨰 ugly number는 무엇인지 기록하고 있어야한다.


    1.5 효율적인 K개의 Ugly number를 미리 구해두기 위해서는 미리 limit을 알고 있으며 된다.
    -> 들어오는 쿼리의 max 값을 참고해서 거기까지 구해서 저장
    다음 ugly number 가 될 수 있는 후보?

    -> 맨위에 있는것 x2,x3,x5

    1. pq 맨위에것을 빼고 (now)
    2. now *2, now*3,now*5 < 삽입
    3. 중복되는 값이 빠져나올 경우 cnt X


    dat
    index : 순서
    value : n번째 ugly number
    
    중복 체크는 맨 마지막에 뺴고 기록된 숫자 -> 현재까지 가장 큰 ugly number
    -> 당연히 다음에 나오는 ugly number > 지금 기록한 ugly number
    */

    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;
    static long dat[];
    static PriorityQueue <Long> pq;

    static int N;
    static PriorityQueue <Integer> Query;
    static int[] qu;
    static int MAX;

    public static void main(String[] args) throws IOException {
    
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        pq = new PriorityQueue<>();

        dat = new long[15001];
        dat[0] = 0;
        qu = new int[N];

        Query = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0;i<N;i++)
        {
            int temp= Integer.parseInt(st.nextToken());
           Query.add(temp);
            qu[i] = temp;
        }
        MAX = Query.poll();

        makeUgly(1);

        for(int i = 0;i<N;i++)
        {
          bw.write(dat[qu[i]]+"");

          if(i < N)
          {
              bw.write(" ");
          }
        }
        bw.write("\n");
        bw.flush();
        bw.close();

    }

    private static int makeUgly(int start) {
        pq.add((long)1);
        int index = 1;
        while(pq.size() != 0){
            long a = pq.poll();

            if(dat[index -1] == a && a != 1)
            {
                continue;
            }

            dat[index]  = a;
            index ++;

            if(index == MAX+1)
            {
                break;
            }

            pq.add(a*2);
            pq.add(a*3);
            pq.add(a*5);

        }
        

        return 0;
 
    }

}

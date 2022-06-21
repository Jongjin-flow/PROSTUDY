import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q2_0621 {
    /*
    Greedy (탐욕법)
    주어진 어떠한 상황에서 가증 이득이 되는 선택을 하면서 문제를 해결하는 방법.
    ex) 동전 거스름돈

    그리드는 정답을 보장할 수 없다.

    => 그리드를 보완하는 방법
    1. 선택한 기준을 바꿔본다.

    2. 알고리즘을 추가한다 -> greedy +  DP

    구현 방법
    1. 기준 선택 -> 어떤 기준으로 이 문제를 해결하기위해 접근할 것인가? ** 내가 지금 선택한 기준이 중간에 바뀌어서는 안됨.
    2. 검증 -> 내가 선택한 기준이 '항상' 최적해에 도잘하는가? ** 왠만한 케이스에서는 수학적인 검증이 가능. -> 반려 케이스들을 생각해본다.
    3. 구현
    */


    /*
    문제 설계를 해보자.
    1시와 가장 가까우면서 가장 시간이 짧은거 먼저 놓고
    9시에 끝나면서 가장 짥은 시간인 회의 넣고
    그담에 시간에 겹치지 않는 시간 넣고 
    반복

    1. 가장 먼저 시작하는 회의 x
    2. 늦게 시작하는 회의 x
    3. 먼저 끝나는 회의 o   -> 먼저 끝나는 순으로 정렬 -> 하나씩 선택함. - > 당연히 맨 첫번째 회의는 들어간다! -> 내가 지금 들어간 회의의 끝나는 시간 vs 다음 들어갈 수 있는 회의의 시작 시간을 비교. -> 들어갈 수 있으면 하나의 회의 추가하고 반복

;;
    4. 늦게 끝나는 회의 x
    5. 짧은 회의 x
    6. 긴 회의 x
    */

    static BufferedReader br;
    static BufferedWriter bw;
    static meetting me[];
    static int cnt=0;
    static ArrayList <meetting> mtable;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        me = new meetting[n];
        mtable = new ArrayList<>();

        for(int i =0; i<n;i++){
            StringTokenizer hour = new StringTokenizer(br.readLine());

            me[i] = new meetting(Integer.parseInt(hour.nextToken()), Integer.parseInt(hour.nextToken()));
        }

        Arrays.sort(me);
        /*
        for(int i=0; i<n;i++){

           System.out.println(me[i].start + " " + me[i].end);
        }
        */

        mtable.add(me[0]);

        for(int i=1; i<n;i++){
            meetting temp = mtable.get(mtable.size()-1);

            if(me[i].start >= temp.end){
                //System.out.println(me[i].start + " " + me[i].end);

                mtable.add(me[i]);
            }
        }


        bw.write(mtable.size()+ "\n");
        bw.flush();
        bw.close();
    }

    static public class meetting implements Comparable <meetting>{
        int start;
        int end;

        public meetting(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(meetting o) {
            // TODO Auto-generated method stub

            if(end > o.end)
            {
                return 1;
            }
            if(end < o.end){
                return -1;
            }
            return 0;
        }


    }
}

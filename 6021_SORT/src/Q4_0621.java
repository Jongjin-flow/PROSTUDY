import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

import java.util.StringTokenizer;

public class Q4_0621 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;


    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        // pq 
        // 자바에서는 defualt는 작은거

        // MAX HEAP
        PriorityQueue <Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        // 커스텀 가능
        PriorityQueue <People> people = new PriorityQueue<>();


        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());

            String type = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            if(type.equals( "push")){
                pq.add(num);
            }
            else if(type.equals("pop")){
                for(int j=0;j<num;j++){
                    int a = pq.poll();
                    System.out.print(a);
                    if(j<num){
                        System.out.print(" ");
                    }
                }
                System.out.println();
            }
            else if(type.equals("add")){
                int a = pq.poll();
                pq.add(a+num);
            }

        }


        people.add(new People(12, 160));
        people.add(new People(13, 170));
        people.add(new People(14, 180));
        people.add(new People(11, 130));

        for(int i = 0; i<4;i++){
            People p = people.poll();
            System.out.println(p.age);
        }
    }

    static class People implements Comparable <People>{
        int age;
        int height;

        People(int age, int height){
            this.age = age;
            this.height = height;
        }

        @Override
        public int compareTo(People o) {
            // TODO Auto-generated method stub
            // 올바른 형태 : -1
            if(age > o.age)
            {
                return -1;
            }
            if(age < o.age)
            {
                return 1;
            }
            if(height > o.height)
            {
                return -1;
            }
            if(height < o.height)
            {
                return 1;
            }
            return 0;
        }
    }
}

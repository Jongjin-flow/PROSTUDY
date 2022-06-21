import java.util.Arrays;

public class Sort {
    public static void main(String[] args) throws Exception {
        //1. 일반적인 정렬
        // Arrays.sort(arr); - 오름차순
        // Arrays.sort(arr,Collections.reverseOrder()); -- 내림차순

        //2. custom 배열!
        People []a = new People[4];
        a[1] = new People(1, 120);
        a[2] = new People(2, 150);
        a[3] = new People(3, 170);
        a[0] = new People(3, 180);

        Arrays.sort(a);

        for(int i=0; i<4;i++)
            System.out.println(a[i].age + " , " + a[i].height);

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

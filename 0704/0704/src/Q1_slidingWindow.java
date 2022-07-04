public class Q1_slidingWindow {
    /*
     * TWO Pointer + Sliding Window
     * Two Pointer 안에 Sliding Window가 포함
     * 
     * Two Pointer : 2개의 포인터를 활용하는 알고리즘
     * 배열에서 우리가 원하고자 하는 것을 찾기 위한 알고리즘.
     * 배열 내의 좀 더 작은 배열 -> Sub-areay
     * ex) 배열 내에서 연속된 값으 합이 10이 되는 구간은 몇개인가?
     * -> 틀이 없음 -> 두 개의 포인터를 어떻게 이동시켜야 하는가?
     * 
     * => Sliding Window -> 항상 동일한 틀 -> 동일한 구간만틈 지속적으로 이동 -> 구현이 굉장히 쉬움.
     * 창문의 구간만큼 이동한다?!
     * 
     * 구현 방법
     * 1. 공통 구간
     * 
     * 2. sliding window
     * -> 구간 완성 => 공통구간에 다음 마지막 값을 더해준다.
     * -> 수행
     * -> 구간 이동 => 시작 위치를 뺴주고, 시작 포인터 이동 -> 끝 포인터 이동
     */

    static int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

    public static void main(String[] args) throws Exception {
        // 5개의 구간의 합을 출력하는 sliding window code

        // 1. 공통 구간 세팅
        // 공통 구간 : 시작 -> 구간 크기 -1
        int start = 0; // 시작 포인터
        int end = 4; // 끝 포인터

        int sum = 0;

        for (int i = start; i < end; i++) {
            sum += arr[i];
        }

        // 2. sliding window
        // 창문을 다 열 때까지 진행

        while (end < 10) {
            // 1. 구간 완성
            // 마지막에 추가하지 않았던 포인터의 값을 축
            // -> 여기까지 해서 하나의 구간 완성
            sum = sum + arr[end];

            // 2. 수행
            System.out.println(sum);

            // 3. 포인터 이동
            // 다시 공통 구간을 만들기 위해 -> 맨 앞의 값을 합에서 제외
            sum = sum - arr[start];

            start = start + 1;
            end = end + 1;
        }

    }
}

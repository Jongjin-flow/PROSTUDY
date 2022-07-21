import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q3_0720 {
    /* MERGE SORT */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N;
    static int arr[];

    static void mergeSort(int start, int end) {
        // 종료 조건 -> 언제 돌아가는가?
        // 단일 노드까지 쪼개졌으면 -> 더 쪼갤 것이 없으니 -> 돌아가라
        if (start >= end)
            return;

        // 재귀 구성
        int mid = (start + end) / 2;

        // 왼쪽 절반 정렬 -> 분할
        // --> mid 포함!
        mergeSort(start, mid);

        // 오른쪽 절반 정렬 -> 분할
        // --> end 포함!
        mergeSort(mid + 1, end);

        // 정복
        // start~end 구간이 정렬하고 합치면서 올라감
        merge(start, end);

        printArr(start, end);
    }

    static void merge(int start, int end) {
        int mid = (start + end) / 2; // 중간지점
        int left = start; // 왼쪽 절반의 시작 포인터 = 시작과 같음
        int right = mid + 1; // 오른쪽 절반의 시작 포인터

        // 임시 배열
        int[] temp = new int[N];
        // temp에서 기록과 arr(원본)위치를 저장하고 있을 index값
        int index = start;

        while (left <= mid && right <= end) {
            // left 요소와 right 요소 비교
            if (arr[right] < arr[left]) {
                temp[index++] = arr[right++];
            } else {
                temp[index++] = arr[left++];
            }
        }

        // left 또는 right가 끝까지 이동했다.
        // 남아있는 요소 넣어야한다.

        if (left > mid) {
            for (int i = right; i <= end; i++) {
                temp[index++] = arr[i];
            }
        } else {
            for (int i = left; i <= mid; i++) {
                temp[index++] = arr[i];
            }
        }

        // 원본 덮어씌우기
        for (int i = start; i <= end; i++) {
            arr[i] = temp[i];
        }

    }

    static void printArr(int start, int end) {
        for (int i = start; i <= end; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        mergeSort(0, N - 1);

    }
}

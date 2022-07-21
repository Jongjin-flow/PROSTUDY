public class Tree {
      // 트리를 구성할 때, 0번 노드를 제외하고 1번이 root
      static int[] tree = {0,1,2,3,4,5,6,7};
      public static void main(String[] args) {
          
          // 루트 노드부터 탐방
          dc(1);
  
      }
      static void dc(int node) {
          // 종료 조건
          // 끝까지 내려가서 return해서 올라와라
          if(node *2 > 7)
          {
              // 아래 더 노드가 없으니까 끝
              return;
          }
          // 들어갈때 코드
          System.out.println(tree[node] + " ");
          System.out.println("left : " + tree[node*2]);
          System.out.println("right : " + tree[node*2+1]);
  
          // 재귀 구성
          // 왼쪽 노드 탐방
          dc(node*2);
  
          // 오른쪽 노드 탐방
          dc(node*2 +1);
  
          // 나올때 코드
      }
}

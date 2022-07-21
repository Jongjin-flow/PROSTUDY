import java.io.*;
import java.util.*;

// 같은 이진 트리 거리

public class Q4_0720 {
	
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    
    static int k; 
    static int nodecnt; 
    static int[] tree; 
    static int ans = 0; 
    
    static int dc(int node) {
    	
    	// 방문한 노드의 값을 일단 누적
    	ans += tree[node]; 
    	
    	// 종료 조건
    	// 지금 노드 -> 왼쪽 아래로 내려가볼려 했는데, 총 노드 개수를 넘어간다 
    	// --> 지금 이 노드는 leaf node다!
    	if(node * 2 > nodecnt) { 
    		// 현재 노드의 값을 return 
    		return tree[node];
    	}
    	
    	// 재귀 구성 -> 설계 1번
    	// 왼쪽으로 분할 
    	int left = dc(node * 2);
    	// 오른쪽으로 분할
    	int right = dc(node * 2 + 1);
    	
    	//==========================
    	// left와 right, 두개의 비교 대상의 노드

    	// 설계 2번 -> left와 right를 통일된 값으로 변하기 위해 필요한 "차이" ans에 누적 
    	ans += Math.abs(left-right); 
    	
    	// 설계 3번 -> left와 right가 어떤 값으로 통일을 헀는지 -> 상위 노드에 알려주고
    	// 이를 계속해서 반복해서 root까지 올라갈것.
    	// 아래에서 통일하기로 한 값 = left, right 둘 중 더 "큰 값" -> 저희는 증가밖에 안됨.
    	return tree[node] + Math.max(left, right);
    }

    public static void main(String[] args) throws IOException {
    	
    	// height 입력 
    	k = Integer.parseInt(br.readLine());
    	
    	// height을 가지고 PBT의 총 노드 개수 ?? 
    	nodecnt = (int) Math.pow(2, k+1) - 1;
    	
    	// tree init <-- 트리의 구성 root = 1번부터 구성
    	tree = new int[nodecnt + 1]; 
    	
    	// input --> 2번 node부터 입력받기
    	st = new StringTokenizer(br.readLine());
    	for(int i = 2; i <= nodecnt; i++)
    		tree[i] = Integer.parseInt(st.nextToken());
    	
    	// root에서부터 내려가면서 트리의 분할정복 
    	dc(1);
    	System.out.println(ans);
    }
}}
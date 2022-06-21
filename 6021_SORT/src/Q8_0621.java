import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Q8_0621 {
    static BufferedReader br;
    static BufferedWriter bw;
    static StringTokenizer st;

    static PriorityQueue<Bomb> pq;
    static PriorityQueue<Bomb> pq1;

    static int N, M;
    static int[][] map;

    public static void main(String[] args) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[1000][1000];

        pq = new PriorityQueue<>();
        pq1 = new PriorityQueue<>();

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());

            int type = Integer.parseInt(st.nextToken());

            if (type == 0) // 터트리자
            {
                bw.write(remove() + "\n");

            } else { // 설치
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                add(type, y, x);
            }

        }

        bw.flush();
        bw.close();

    }

    private static void add(int type, int y, int x) {

        // 0 아무것도 없다.
        if (map[y][x] == 0) {
            pq.add(new Bomb(type, x, y));
            map[y][x] = 1;
        }
        // 1 유효한 폭탄이 있다.
        else if (map[y][x] == 1) {
            for (int i = 0; i < pq.size(); i++) {
                Bomb re = pq.poll();

                if (re.x == x && re.y == y) {
                    pq.addAll(pq1);
                    pq1.clear();
                    break;
                }
                pq1.add(re);
            }
        }
        // 2 무효한 폭탄이 있다.
        else if (map[y][x] == 2) {
        }
        // 3 터진 곳은 이미 설치해도 무효폭탄이 된다.
        else if (map[y][x] == 3) {
            // 설치 안한다. 어차피 무표
        }

    }

    private static int remove() {
        int number = 0;

        Bomb re = pq.poll();

        if(re == null){
            return -1;
        }

        int x = re.x;
        int y = re.y;

        // 1 유효한 폭탄이다 터트리자
        if (map[y][x] == 1) {
            map[y][x] = 3;

            if (y - 1 >= 0 && x < 1000) {
                if (map[y - 1][x] == 1) {
                    map[y - 1][x] = 2; // 무효한 폭탄으로 변경
                    for (int i = 0; i < pq.size(); i++) {
                        Bomb re1 = pq.poll();
        
                        if (re1.x == x && re1.y == y-1) {
                            pq.addAll(pq1);
                            pq1.clear();
                        }
                        pq1.add(re1);
                    }
                }
            }
            if (y < 1000 && x - 1 >= 0) {
                if (map[y][x - 1] == 1) {
                    map[y][x - 1] = 2; // 무효한 폭탄으로 변경

                    for (int i = 0; i < pq.size(); i++) {
                        Bomb re1 = pq.poll();
        
                        if (re1.x == x-1 && re1.y == y) {
                            pq.addAll(pq1);
                            pq1.clear();
                            break;
                        }
                        pq1.add(re1);
                    }
                }
            }
            if (y + 1 < 1000 && x >= 0) {
                if (map[y + 1][x] == 1) {
                    map[y + 1][x] = 2; // 무효한 폭탄으로 변경

                    for (int i = 0; i < pq.size(); i++) {
                        Bomb re1 = pq.poll();
        
                        if (re1.x == x && re1.y == y+1) {
                            pq.addAll(pq1);
                            pq1.clear();
                            break;
                        }
                        pq1.add(re1);
                    }
                }
            }
            if (y >= 0 && x + 1 < 1000) {
                if (map[y][x + 1] == 1) {
                    map[y][x + 1] = 2; // 무효한 폭탄으로 변경

                    for (int i = 0; i < pq.size(); i++) {
                        Bomb re1 = pq.poll();
        
                        if (re1.x == x+1 && re1.y == y) {
                            pq.addAll(pq1);
                            pq1.clear();
                            break;
                        }
                        pq1.add(re1);
                    }
                }
            }


            //// 빈공간이면, 터진 공간으로 변경

            if (y - 1 >= 0 && x < 1000) {
                if (map[y - 1][x] == 0) {
                    map[y - 1][x] = 3; // 터진곳
                }
            }
            if (y < 1000 && x - 1 >= 0) {
                if (map[y][x - 1] == 0) {
                    map[y][x - 1] = 3; // 터진곳
                }
            }
            if (y + 1 < 1000 && x >= 0) {
                if (map[y + 1][x] == 0) {
                    map[y + 1][x] = 3; // 터진곳
                }
            }
            if (y >= 0 && x + 1 < 1000) {
                if (map[y][x + 1] == 0) {
                    map[y][x + 1] = 3; // 터진곳
                }
            }

            number = re.number;
        }
        // 무효한 폭탄.
        else if (map[y][x] == 2) {
            number = -1;
        }
        // 터진 곳
        else if (map[y][x] == 3) {
            number = -1;
        }

        return number;
    }

    static class Bomb implements Comparable<Bomb> {
        int number;
        int x;
        int y;

        public Bomb(int number, int x, int y) {
            this.number = number;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Bomb next) {
            // TODO Auto-generated method stub
            if (number < next.number)
                return -1;
            if (number > next.number)
                return 1;

            return 0;
        }
    }
}

package BOJ;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17471_게리맨더링_prac {

    static int N;
    static int[] person;
    static boolean[] selected;
    static boolean[][] connect;
    static int minRes = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        person = new int[N];
        selected = new boolean[N];
        connect = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            person[i] = sc.nextInt();
        }

        // 인접한 구역 표시
        for (int i = 0; i < N; i++) {
            int count = sc.nextInt();
            for (int j = 0; j < count; j++) {
                int num = sc.nextInt() - 1;
                connect[i][num] = true;
            }
        }

        // 비트마스킹으로 구역 나누기
        for (int i = 1; i < (1 << N - 1) ; i++) {
            ArrayList<Integer> A = new ArrayList<>();
            ArrayList<Integer> B = new ArrayList<>();

            for (int j = 0; j < N; j++) {
                if((i & (1 << j)) > 0) {
                    A.add(j);
                } else {
                    B.add(j);
                }
            }

//            System.out.println("A : " + A);
//            System.out.println("B : " + B);

            connectCheck(A, B);
        }

        if (minRes == Integer.MAX_VALUE)
            System.out.println("-1");
        else
            System.out.println(minRes);
    }

    public static void connectCheck(ArrayList<Integer> A, ArrayList<Integer> B) {
        boolean isAConnected = true;
        boolean isBConnected = true;

        if (A.size() > 1) { // 부분집합으로 이루어진 구역의 개수가 1보다 크면, 연결되어있는지 확인
            isAConnected = BFS(A); // BFS로 연결 여부 체크해서 Boolean값 리턴
        }

        if (B.size() > 1) {
            isBConnected = BFS(B);
        }

        if (isAConnected && isBConnected) { // 두 구역 모두 연결되어 있을때만, 두 선거구의 인구 차이의 최솟값 저장.

            int aSum = 0;
            int bSum = 0;

            for (int i = 0; i < A.size(); i++) {
                aSum += person[A.get(i)];
            }

            for (int i = 0; i < B.size(); i++) {
                bSum += person[B.get(i)];
            }

            minRes = Math.min(minRes, Math.abs(aSum - bSum));
        }
    }

    // BFS로 인접한 구역을 체크하고, 인구수를 모두 합해 리턴하기.
    public static boolean BFS(ArrayList<Integer> area) {

        boolean flag = true;
        boolean[] checked = new boolean[N];
        Queue<Integer> q = new LinkedList<>();

        int start = area.get(0); // area의 맨 처음 시작점 저장
        q.add(start);
        checked[start] = true;

        while(!q.isEmpty()) {
            int next = q.poll();

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < area.size(); j++) { // 그룹에 속하는 구역만 확인
                    if(connect[next][i] && !checked[i] && i == area.get(j)) { // 방문하지 않은 곳이고 && 연결된 곳이고 && i값이 구역에 속하는지
                        q.add(i);
                        checked[i] = true;
                    }
                }
            }
        }

        for (int i = 0; i < area.size(); i++) { // 그룹을 돌면서 확인
            if (!checked[area.get(i)]) { // 연결 안된것이 있다면
                flag = false; // 연결 여부 false 갱신
                break; // for문 빠져나옴
            }
        }

        return flag; // true면 구역이 모두 연결되어있다는 뜻!
    }
}

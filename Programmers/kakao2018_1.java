import java.util.Arrays;

class Solution {
    public static int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        Fail[] tmpAns = new Fail[N];
        double[] noClear = new double[N];

        for (int stage: stages){
            if (stage > N) continue;
            noClear[stage - 1]++;
        }

        double[] player = new double[N];
        for (int i = 0; i < N; i++) {
            if (i == 0) player[0] = stages.length;
            else player[i] = player[i - 1] - noClear[i - 1];
        }

        for (int i = 0; i < N; i++) {
            double percentage;
            if (noClear[i] == 0) percentage = 0.0;
            else percentage = noClear[i] / player[i];
            tmpAns[i] = new Fail(i + 1, percentage);
        }

        Arrays.sort(tmpAns);

        for (int i = 0; i < N; i++) {
            answer[i] = tmpAns[i].idx;
        }

        return answer;
    }
    
    static class Fail implements Comparable<Fail>{
        int idx;
        double percent;

        public Fail(int idx, double percent) {
            this.idx = idx;
            this.percent = percent;
        }

        @Override
        public int compareTo(Fail o) {
            if (this.percent == o.percent) {
                return Integer.compare(this.idx, o.idx);
            } else {
                return -Double.compare(this.percent, o.percent);
            }
        }
    }
}

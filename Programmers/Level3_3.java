class Solution {
    static int result = Integer.MAX_VALUE;
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        boolean[] visited = new boolean[words.length];
        boolean flag = false;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) flag = true;
        }

        if (!flag) return 0;
        else answer = dfs(begin, target, words, visited, 0);
        return answer;
    }
    
    private static int dfs(String begin, String target, String[] words, boolean[] visited, int count) {
        if (result < count) return result;
        if (begin.equals(target)) {
            if (result > count) result = count;
            return result;
        }

        for (int i = 0; i < words.length; i++) {
            if (!visited[i] && inRange(begin, words[i])) {
                visited[i] = true;
                dfs(words[i], target, words, visited, count + 1);
                visited[i] = false;
            }
        }
        return result;
    }
    
    private static boolean inRange(String begin, String target) {
        int cnt = 0;
        for (int i = 0; i < begin.length(); i++) {
            if (begin.charAt(i) != target.charAt(i)) cnt++;
        }

        return cnt == 1 ? true : false;
    }
}

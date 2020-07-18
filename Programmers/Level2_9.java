import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = skill_trees.length;


        for (int i = 0; i < skill_trees.length; i++) {
            Queue<Character> q = new LinkedList<>();
            for (int x = 0; x < skill.length(); x++) {
                q.add(skill.charAt(x));
            }

            int len = skill_trees[i].length();
            for (int j = 0; j < len; j++) {
                char nextSkill = skill_trees[i].charAt(j);
                if (q.isEmpty()) break;
                if (q.peek() == nextSkill) {
                    q.poll();
                    continue;
                }
                if (q.contains(nextSkill)) {
                    answer--;
                    break;
                }
            }
        }
        return answer;
    }
}

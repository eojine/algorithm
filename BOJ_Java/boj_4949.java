import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_4949_균형잡힌세상 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            String s = br.readLine();
            Stack<Character> st = new Stack<>();
            boolean flag = true;
            if (s.equals(".")) break;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if(c == '[' || c == '(') {
                    st.push(c);
                } else if (c == ')') {
                    if (!st.isEmpty() && st.peek() == '(') {
                        st.pop();
                    } else {
                        System.out.println("no");
                        flag = false;
                        break;
                    }
                } else if (c == ']') {
                    if (!st.isEmpty() && st.peek() == '[') {
                        st.pop();
                    } else {
                        System.out.println("no");
                        flag = false;
                        break;
                    }
                }
            }

            if (!flag) continue;

            if (!st.isEmpty()) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}

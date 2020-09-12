import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = Integer.parseInt(br.readLine());
        HashSet<String> hs = new HashSet<>();

        for (int i = 0; i < cnt; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String name = st.nextToken();
            String state = st.nextToken();

            switch (state) {
                case "enter":
                    hs.add(name);
                    break;
                case "leave":
                    hs.remove(name);
                    break;
            }

        }

        ArrayList<String> s = new ArrayList<>(hs);
        s.sort(Comparator.reverseOrder());
        for (String r : s) {
            System.out.println(r);
        }
    }
}

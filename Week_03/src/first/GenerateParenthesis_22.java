package first;

import java.util.ArrayList;
import java.util.List;

public class GenerateParenthesis_22 {

    public static void main(String[] args) {
        GenerateParenthesis_22 generateParenthesis22 = new GenerateParenthesis_22();
        generateParenthesis22.generateParenthesis(3).forEach(System.out::println);
    }

    public List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        generate(list, n, "", 0, 0);
        return list;
    }

    public void generate(List<String> list, int n, String s, int left, int right) {
        if (left == n && right == n) {
            list.add(s);
            return ;
        }
        if (left < n) {
            generate(list, n, s + "(", left + 1, right);
        }
        if (right < left) {
            generate(list, n, s + ")", left, right + 1);
        }
    }
}

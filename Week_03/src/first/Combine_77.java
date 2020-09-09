package first;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Combine_77 {

    public static void main(String[] args) {
        Combine_77 combine_77 = new Combine_77();
        combine_77.combine(4, 2).forEach(list -> {
            list.forEach(num -> System.out.print(num + " "));
            System.out.println();
        });
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> deque = new ArrayDeque<>(k);
        dfs2(result, deque, n, k, 1);
        return result;
    }

    public void dfs(List<List<Integer>> result, Deque<Integer> deque, int n, int k, int begin) {
        if (k == 0) {
            result.add(new ArrayList<>(deque));
            return;
        }

        // 1、这里相当于本层有几个子节点可选，就循环几次，因为每一次都是选了路径走，所以递归后需要revert state
        // 这样是为了清除下一层递归完的状态修改，即路径选择（因为deque是动态的数据结构，也可以进入下一层的时候复制一份数据，就不用清除）
        // 2、这里的循环就相当于N叉，当N=2的时候相当于就是二叉树，不用循环写就是先递归左子节点，再递归右子节点
        for (int i = begin; i <= n - k + 1; i++) {
            deque.offer(i);
            dfs(result, deque, n, k - 1, i + 1);
            deque.removeLast();
        }
    }

    public void dfs2(List<List<Integer>> result, Deque<Integer> deque, int n, int k, int begin) {
        if (k == 0) {
            result.add(new ArrayList<>(deque));
            return;
        }

        if (begin > n - k + 1) {
            return;
        }

        // 每个数字，只有选或者不选2个可能，相当于2叉树
        // 这里先不选，k剩余可选的数量所以没有减少，直接传递到下一层，但是下一层的可选数字起始begin是变化的
        dfs(result, deque, n, k, begin + 1);

        // 这里选择了当前的数值
        deque.offer(begin);
        dfs(result, deque, n, k - 1, begin + 1);
        // 因为选择了数字，需要做清理
        deque.removeLast();
    }
}

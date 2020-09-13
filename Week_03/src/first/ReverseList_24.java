package first;

import java.util.Stack;

public class ReverseList_24 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        ReverseList_24 reverseList_24 = new ReverseList_24();
        ListNode newHead = reverseList_24.reverseList3(node1);
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        ListNode newHead = stack.peek();
        temp = stack.pop();
        while (!stack.isEmpty()) {
            temp.next = stack.pop();
            temp = temp.next;
            temp.next = null;
        }
        return newHead;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return null;
        }
        return dfs(head, null);
    }

    public ListNode dfs(ListNode curr, ListNode last) {
        if (curr.next == null) {
            curr.next = last;
            return curr;
        }
        ListNode head = dfs(curr.next, curr);
        curr.next = last;
        return head;
    }

    public ListNode reverseList3(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
  }
}

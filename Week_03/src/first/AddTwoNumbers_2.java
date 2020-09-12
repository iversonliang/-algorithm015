package first;

import java.util.List;

public class AddTwoNumbers_2 {

    public static void main(String[] args) {
        AddTwoNumbers_2 addTwoNumbers2 = new AddTwoNumbers_2();
        ListNode l1 = addTwoNumbers2.genHead(5);
        ListNode l2 = addTwoNumbers2.genHead(5);

        ListNode result = addTwoNumbers2.addTwoNumbers(l1, l2);
        while (result != null) {
            System.out.print(result.val);
            result = result.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        int carry = 0;
        ListNode head = null;
        ListNode temp = new ListNode(0);
        while ((p1!= null || p2 != null)) {
            int val1 = p1 != null ? p1.val : 0;
            int val2 = p2 != null ? p2.val : 0;
            int result = val1 + val2 + carry;
            carry = result >= 10 ? 1 : 0;
            temp.next = carry == 1 ? new ListNode(result % 10) : new ListNode(result);
            temp = temp.next;
            if (head == null) {
                head = temp;
            }

            if (p1 != null) {
                p1 = p1.next;
            }
            if (p2 != null) {
                p2 = p2.next;
            }
        }
        if (carry == 1) {
            temp.next = new ListNode(1);
        }
        return head;
    }

    public ListNode genHead(long num) {
        ListNode head = new ListNode((int) num % 10);
        ListNode temp = head;
        while (true) {
            if ((num /= 10) == 0) {
                break;
            }
            long value = num % 10;
            temp.next = new ListNode((int)value);
            temp = temp.next;
        }
        return head;
    }

    public long getListNum(ListNode head) {
        long num = 0;
        int i = 0;
        while (head != null) {
            if (i > 0) {
                num += Math.pow(10, i) * head.val;
            } else {
                num += head.val;
            }
            head = head.next;
            i++;
        }
        return num;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

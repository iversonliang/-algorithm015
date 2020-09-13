package first;

public class KeverseKGroup_25 {

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

        KeverseKGroup_25 keverseKGroup25 = new KeverseKGroup_25();
        ListNode newHead = keverseKGroup25.reverseKGroup2(node1,2);
        while (newHead != null) {
            System.out.print(newHead.val + " ");
            newHead = newHead.next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) {
            return null;
        }

        ListNode pre = null;
        ListNode curr = head;
        ListNode result = null;
        ListNode oldGroupTail = head;
        ListNode nextGroupHead = null;
        while (head != null) {
            int i = 1;
            for (; i <= k; i++) {
                if (head == null) {
                    break;
                }
                head = head.next;
            }
            if (i <= k) {
                oldGroupTail.next = nextGroupHead;
                break;
            }

            nextGroupHead = head;
            ListNode currGroupTail = curr;
            for (i = 1; i <= k && curr != null; i++) {
                ListNode next = curr.next;
                curr.next = pre;
                pre = curr;
                curr = next;
            }

            // pre是当前的group的尾部，反转后就是头部，上个group的尾部指向本group的头部
            oldGroupTail.next = pre;
            oldGroupTail = currGroupTail;
            if (nextGroupHead == null) {
                currGroupTail.next = null;
            }
            if (result == null) {
                result = pre;
            }
        }

        return result;
    }

    public ListNode reverseKGroup2(ListNode head, int k) {
        // 1. 如果本组不满足k个数，直接返回本组头结点
        ListNode nextGroupStart = head;
        int i = 1;
        for (; i <= k; i++) {
            if (nextGroupStart == null) {
                return head;
            }
            nextGroupStart = nextGroupStart.next;
        }

        // 2. 反转链表元素，得到本组新的头结点
        ListNode newHead = reverse(head, k);
        // 3. 获取下一组的头结点
        ListNode nextHead = reverseKGroup2(nextGroupStart, k);
        // 4. 本组新的尾结点，指向下一组新的头结点
        head.next = nextHead;
        // 5. 返回本组新的头结点
        return newHead;
    }

    public ListNode reverse(ListNode curr, int n) {
        if (n == 1) {
            return curr;
        }
        ListNode pre = null;
        ListNode next = null;
        for (int i = 1; i <= n; i++) {
            next = curr.next;
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

package first;

public class SwapPairs_24 {

    public static void main(String[] args) {
        ListNode listNode4 = new ListNode(4);
        ListNode listNode3 = new ListNode(3, listNode4);
        ListNode listNode2 = new ListNode(2, listNode3);
        ListNode listNode1 = new ListNode(1, listNode2);
        SwapPairs_24 swapPairs24 = new SwapPairs_24();
        ListNode head = swapPairs24.swapPairs(listNode1);
        ListNode temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public ListNode swapPairs(ListNode head) {
        ListNode newHead = head;
        ListNode temp = head;
        ListNode last = new ListNode();
        while (true) {
            if (temp == null || temp.next == null) {
                break;
            }
            ListNode next = temp.next;
            ListNode nextHead = next.next;
            last.next = next;
            next.next = temp;
            temp.next = nextHead;
            last = temp;
            temp = nextHead;
            if (newHead == head) {
                newHead = next;
            }
        }

        return newHead;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}


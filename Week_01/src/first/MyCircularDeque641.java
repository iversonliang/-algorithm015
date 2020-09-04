package first;

public class MyCircularDeque641 {
    public static void main(String[] args) {
        MyCircularDeque3 deque = new MyCircularDeque3(4);
        System.out.println(deque.insertFront(9));
        System.out.println(deque.deleteLast());
        System.out.println(deque.getRear());
    }

}


class MyCircularDeque {

    int[] ring;
    int first = 0;
    int rear = 0; // 数组最后一个元素的下一位，即新元素的插入位置

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque(int k) {
        // 需要浪费一个元素的位置来判断是否满，避免和空的情况相同无法判断
        ring = new int[k + 1];
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        // 取模运算的结果，只能是[0, ring.leng - 1]，不用担心数组越界的问题，可避免if判断
        first = (first - 1 + ring.length) % ring.length;
        // 新元素放在rear位置上
        ring[first] = value;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        ring[rear] = value;
        rear = (rear + 1) % ring.length;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        first = (first + 1) % ring.length;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        rear = (rear - 1 + ring.length) % ring.length;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return ring[first];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return ring[(rear - 1 + ring.length) % ring.length];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return first == rear;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return first == (rear + 1) % ring.length;
    }
}


// ------------------------------------------
// 用size记录当前队列的元素个数，这样的话不用浪费一个数组元素，但也会额外引入一个size做缓存，但我觉得是值得的，代码更优雅
// first表示新元素要在队头插入的下标，last表示新元素在队尾插入的下标
// 如果插入队头，则进行赋值，然后计算新的first，插入队尾同理
// 这种写法代码很简洁，但是要注意最初的时候first和last不能是同一个下标，不然先插入队头，再插入队尾时，插入队头的数据会被覆盖
class MyCircularDeque2 {

    int[] ring;
    int first = 0;
    int last = 1;
    int size = 0;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque2(int k) {
        ring = new int[k];
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        ring[first] = value;
        first = getPre(first);
        size++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        ring[last] = value;
        last = getNext(last);
        size++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        first = getNext(first);
        size--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        last = getPre(last);
        size--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return isEmpty() ? -1 : ring[getNext(first)];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return isEmpty() ? -1 : ring[getPre(last)];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == ring.length;
    }

    private int getPre(int index) {
        return (index - 1 + ring.length) % ring.length;
    }

    private int getNext(int index) {
        return (index + 1) % ring.length;
    }
}



// ---------------------------------------------
// 使用链表代码稍微多一点，但是不用考虑循环的问题，操作队头队尾都是常规的节点操作
class MyCircularDeque3 {
    private Node head;
    private Node tail;
    int size = 0;
    int capacity;

    /** Initialize your data structure here. Set the size of the deque to be k. */
    public MyCircularDeque3(int k) {
        capacity = k;
    }

    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        Node node = new Node(value);
        if (isEmpty()) {
            head = tail = node;
        } else {
            node.next = head;
            head.pre = node;
            head = node;

        }
        size++;
        return true;
    }

    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        Node node = new Node(value);
        if (isEmpty()) {
            head = tail = node;
        } else {
            tail.next = node;
            node.pre = tail;
            tail = node;
        }
        size++;
        return true;
    }

    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        if (size == 1) {
            head = tail = null;
        } else {
            Node next = head.next;
            head.next = null;
            next.pre = null;
            head = next;
        }
        size--;
        return true;
    }

    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        if (size == 1) {
            head = tail = null;
        } else {
            Node pre = tail.pre;
            pre.next = null;
            tail.pre = null;
            tail = pre;
        }
        size--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        return isEmpty() ? -1 : head.value;
    }

    /** Get the last item from the deque. */
    public int getRear() {
        return isEmpty() ? -1 : tail.value;
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == capacity;
    }

    private class Node {
        int value;
        Node next;
        Node pre;
        public Node(int value) {
            this.value = value;
        }
    }
}
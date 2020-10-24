package first;

import sun.misc.LRUCache;

import java.util.HashMap;
import java.util.Map;

/**
 * @create: 2020-10-24 15:00
 */
public class LRUCache_146 {

    int capacity;
    private Map<Integer, Node> map;
    private DoubleList list;

    public static void main(String[] args) {
        LRUCache_146 cache = new LRUCache_146( 2 /* 缓存容量 */ );

        cache.get(2);
        cache.put(2, 6);
        cache.get(1);
        cache.put(1, 5);
        cache.put(1, 2);
        cache.get(1);
        cache.get(2);

//        cache.put(1, 1);
//        cache.put(2, 2);
//        System.out.println(cache.get(1));       // 返回  1
//        cache.put(3, 3);    // 该操作会使得关键字 2 作废
//        System.out.println(cache.get(2));       // 返回 -1 (未找到)
//        cache.put(4, 4);    // 该操作会使得关键字 1 作废
//        System.out.println(cache.get(1));       // 返回 -1 (未找到)
//        System.out.println(cache.get(3));       // 返回  3
//        System.out.println(cache.get(4));       // 返回  4
    }

    public LRUCache_146(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>(capacity);
        list = new DoubleList();
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        list.use(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;
            list.use(node);
            return;
        }
        if (map.size() == capacity) {
            Node tail = list.removeLast();
            map.remove(tail.key);
        }
        Node node = map.computeIfAbsent(key, k -> new Node(key, value));
        list.insert(node);
    }

    private static class DoubleList {
        Node head = null;
        Node tail = null;

        public void insert(Node node) {
            if (head == null && tail == null) {
                head = tail = node;
                return;
            }
            node.next = head;
            node.prev = null;
            head.prev = node;
            head = node;
        }

        public void use(Node node) {
            if (node == head) {
                return;
            } else if (node == tail) {
                removeLast();
            } else  {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            insert(node);
        }

        public Node removeLast() {
            Node old = tail;
            if (head == tail) {
                head = tail = null;
            } else {
                tail.prev.next = null;
                tail = tail.prev;
            }
            return old;
        }
    }

    private static class Node {
        private int key;
        private int value;
        Node next = null;
        Node prev = null;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
}

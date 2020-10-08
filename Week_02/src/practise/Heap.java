package practise;

public class Heap {

    private int[] element;
    private int count = 0;

    public static void main(String[] args) {
        Heap heap = new Heap(20);
        heap.insert(33);
        heap.insert(27);
        heap.insert(21);
        heap.insert(55);
        heap.insert(13);
        heap.insert(15);
        heap.insert(19);
        heap.insert(5);
        heap.insert(6);
        heap.insert(7);
        heap.insert(8);
        heap.insert(1);
        heap.insert(2);
        heap.insert(12);
        heap.print();

        heap.deleteMax();
        heap.print();
    }

    public Heap(int capacity) {
        element = new int[capacity + 1];
    }

    public void print() {
        for (int i = 1; i <= count; i++) {
            System.out.print(element[i] + " ");
        }
        System.out.println();
    }

    public boolean insert(int value) {
        if (count == element.length - 1) {
            return false;
        }
        element[++count] = value;
        int i = count;
        while (i / 2 > 0 && element[i] > element[i / 2]) {
            element[i] = element[i / 2];
            i /= 2;
        }
        element[i] = value;
        return true;
    }

    public boolean deleteMax() {
        if (count == 0) {
            return false;
        }
        element[1] = element[count--];
        int temp = element[1];
        int i = 1;
        while (i * 2 < element.length) {
            int left = element[i * 2];
            int right = element[i * 2 + 1];
            if (element[i] > left && element[i] > right) {
                break;
            }
            element[i] = left > right ? element[i * 2] : element[i * 2 + 1];
            i = left > right ? i * 2 : i * 2 + 1;
        }
        element[i] = temp;
        return true;
    }

    private void swap(int index1, int index2) {
        int temp = element[index2];
        element[index2] = element[index1];
        element[index1] = temp;
    }

}

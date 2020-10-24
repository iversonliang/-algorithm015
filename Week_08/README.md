#排序算法模板

###BubbleSort
```$xslt
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i ; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
```

###InsertionSort
```
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int temp = arr[i+1];
            for (int j = i; j >= 0; j--) {
                if (temp > arr[j]) {
                    break;
                }
                arr[j+1] = arr[j];
                if (j == 0 || temp > arr[j-1]) {
                    arr[j] = temp;
                    break;
                }
            }
        }
    }
```

###SelectionSort
```
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    min = j;
                }
            }
            if (i == min) {
                continue;
            }
            int temp = arr[min];
            arr[min] = arr[i];
            arr[i] = temp;
        }
    }
```

###QuickSort
```
    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(arr, left, right);
        // 中轴左边的都比它小，右边的都比它大，说明位置已经确定，只需递归排序中轴两边的元素
        quickSort(arr, left, pivot - 1);
        quickSort(arr, pivot + 1, right);
    }

    public static int partition(int[] arr, int left, int right) {
        int count = left;
        int pivot = right;
        for (int i = left; i < right; i++) {
            // 如果当前的元素 < 中轴元素，那么就将当前元素交换到count的位置
            // 这里先不用管当前count位置的元素大小，即使当前count位置的元素比中轴元素小，
            // 最终也会被交换回新的中轴左边，因此遍历会将所有小于中轴的元素都交换到左边，
            // 最终的效果就是元素[0, count]区间都是小于中轴的元素，count++就是新的中轴位置
            if (arr[i] < arr[pivot]) {
                int temp = arr[i];
                arr[i] = arr[count];
                arr[count] = temp;
                count++;
            }
        }
        // 将中轴元素交换到count的位置上
        int temp = arr[pivot];
        arr[pivot] = arr[count];
        arr[count] = temp;
        return count;
    }
```

###MergeSort
```
    public static void sort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (right <= left) {
            return;
        }
        int mid = (left + right) >> 1;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] tempArr = new int[right - left + 1];
        int i = left;     // 左边数组的指针
        int j = mid + 1;  // 右边数组的指针
        int k = 0;        // 临时数组的指针

        // 合并，两边都还没遍历完
        while (i <= mid && j <= right) {
            tempArr[k++] = arr[i] < arr[j] ? arr[i++] : arr[j++];
        }
        // 只剩下其中一边数组还没遍历完
        while (i <= mid) {
            tempArr[k++] = arr[i++];
        }
        while (j <= right) {
            tempArr[k++] = arr[j++];
        }

        // 将临时数组复制回原数组的[left, right]区间
        for (int n = 0; n < tempArr.length; n++) {
            arr[left + n] = tempArr[n];
        }
    }
```

###HeapSort
```
    public static void sort(int[] arr) {
        buildHeap(arr);
        for (int i = arr.length - 1; i >=0; i--) {
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            heapifyDown(arr, i, 0);
        }
    }

    public static void heapifyDown(int[] arr, int limit, int i) {
        int value = arr[i];
        int tempIdx = i;
        for (int child = 2 * i + 1; child < limit; child = 2 * child + 1) {
            if (child + 1 < limit && arr[child] < arr[child + 1]) {
                child++;
            }
            if (arr[child] > value) {
                arr[tempIdx] = arr[child];
                tempIdx = child;
            }
        }
        arr[tempIdx] = value;
    }

    public static void buildHeap(int[] arr) {
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, arr.length, i);
        }
    }
```
package edu.neu.coe.info6205.sort.counting;

import java.util.Arrays;

public class DPStest {
    public void dualQuickSort(int[] A, int left, int right) {
        if (left >= right) {
            return;
        }
        if (A[left] > A[right]) {
            swap(A, left, right);
        }
        int less = left;
        int great = right;
        int pivot1 = A[left];
        int pivot2 = A[right];
        while (A[++less] < pivot1) ;
        while (A[--great] > pivot2) ;

        /*
         * Partitioning:
         *
         *   left part           center part                   right part
         * +--------------------------------------------------------------+
         * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
         * +--------------------------------------------------------------+
         *               ^                          ^       ^
         *               |                          |       |
         *              less                        k     great
         *
         * Invariants:
         *
         *              all in (left, less)   < pivot1
         *    pivot1 <= all in [less, k)     <= pivot2
         *              all in (great, right) > pivot2
         *
         * Pointer k is the first index of ?-part.
         */
        outer:
        for (int k = less - 1; ++k <= great; ) {
            int ak = A[k];
            if (ak < pivot1) { // ak 小于 p1
                swap(A, k, less); // 交换
                less++;
            } else if (ak > pivot2) { // ak > p2
                while (A[great] > pivot2) { // 找到不满足条件的位置
                    if (great-- == k) {
                        System.out.println("outer");
                        break outer;
                    }
                }
                if (A[great] < pivot1) { // a[great] <= pivot1，

                    A[k] = A[less];  // less放到 k的位置,  k 位置的元素数保存在 ak中
                    A[less] = A[great]; // great 放到less的位置
                    ++less;  // 更新 less
                } else { // pivot1 <= a[great] <= pivot2
                    A[k] = A[great];
                }
                /*
                 * Here and below we use "a[i] = b; i--;" instead
                 * of "a[i--] = b;" due to performance issue.
                 */
                A[great] = ak; // ak 放到 great位置
                --great;
            } // 其他情况就是中间位置，不用考虑
        }

        System.out.println("left :" + left + " less " + less + " great" + great + " right " + right);
//        System.out.println(Arrays.toString(A));

        dualQuickSort(A, left, less - 1);
        dualQuickSort(A, less, great);
        dualQuickSort(A, great + 1, right);
    }

    public void swap(int[] A, int i, int j) {
        int t = A[i];
        A[i] = A[j];
        A[j] = t;
    }

    public static void main(String[] args) {
        int[] A = new int[]{13, 3, 65, 97, 76, 10, 35, 71, 5, 7, 3, 27, 49};
        System.out.println(Arrays.toString(A));
        DPStest dualQuickSort = new DPStest();
        int l = 0;
        int r = A.length - 1;
        dualQuickSort.dualQuickSort(A, l, r);
        System.out.println(Arrays.toString(A));
    }

}

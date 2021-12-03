package edu.neu.coe.info6205.sort.counting;

import java.util.Arrays;

public class DualPivotQuickSort {
    private static int charAsciiVal(String str, int charPosition) {
        if (charPosition >= str.length()) {
            return 0;
        }
        return str.charAt(charPosition);
    }

    public boolean compareStr(String strA, String strB) {
        for (int i = 0; i < Math.min(strA.length(), strB.length()); i++) {
            if (charAsciiVal(strA, i) < charAsciiVal(strB, i)){
                return true;
            } else if (charAsciiVal(strA, i) > charAsciiVal(strB, i)) {
                return false;
            }
        }

        if (strA.length() < strB.length()) {
            return true;
        }
        return false;
    }

    public void swap(String[] a, int left, int right){
        String temp;
        temp = a[left];
        a[left] = a[right];
        a[right] = temp;
    }

    public void dualQuickSort(String[] names, String[] A, int left, int right) {
        if (left >= right) {
            return;
        }
        if (compareStr(A[right], A[left])) {
            swap(A, left, right);
            swap(names, left, right);
        }
        int less = left;
        int great = right;
        String pivot1 = A[left];
        String pivot2 = A[right];
        while (compareStr(A[++less], pivot1)) ;
        while (compareStr(pivot2, A[--great])) ;

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
            String ak = A[k];
            String nk = names[k];

            if (compareStr(ak, pivot1)) { // ak 小于 p1
                swap(A, k, less); // 交换
                swap(names, k, less);
                less++;
            } else if (compareStr(pivot2, ak)) { // ak > p2
                while (compareStr(pivot2, A[great])) { // 找到不满足条件的位置
                    if (great-- == k) {
//                        System.out.println("outer");
                        break outer;
                    }
                }
                if (compareStr(A[great], pivot1)) { // a[great] <= pivot1，

                    A[k] = A[less];  // less放到 k的位置,  k 位置的元素数保存在 ak中
                    names[k] = names[less];
                    A[less] = A[great]; // great 放到less的位置
                    names[less] = names[great];
                    ++less;  // 更新 less
                } else { // pivot1 <= a[great] <= pivot2
                    A[k] = A[great];
                    names[k] = names[great];
                }
                /*
                 * Here and below we use "a[i] = b; i--;" instead
                 * of "a[i--] = b;" due to performance issue.
                 */
                A[great] = ak; // ak 放到 great位置
                names[great] = nk;
                --great;
            } // 其他情况就是中间位置，不用考虑
        }

//        System.out.println("left :" + left + " less " + less + " great" + great + " right " + right);
//        System.out.println(Arrays.toString(A));

        dualQuickSort(names, A, left, less - 1);
        dualQuickSort(names, A, less, great);
        dualQuickSort(names, A, great + 1, right);
    }

    public static void main(String[] args) {
        String filePath = "/Users/chenpeng/Documents/GitHub/INFO6205-Final-ZCL/shuffledChinese.txt";
        String[] ChineseWords = ReadTxt.readTxtFile(filePath,999998);
        String[] pinyin = Pinyin.getPinYinWithTone(ChineseWords);

        DualPivotQuickSort dq = new DualPivotQuickSort();
        dq.dualQuickSort(ChineseWords, pinyin, 0, pinyin.length - 1);

        for (int i = 0; i <= 100; i++) {
            System.out.println(ChineseWords[i]);
            System.out.println(pinyin[i]);
        }

//        System.out.println(charAsciiVal("asd",0));
    }

}

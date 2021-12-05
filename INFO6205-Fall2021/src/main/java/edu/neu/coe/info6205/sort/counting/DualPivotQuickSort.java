package edu.neu.coe.info6205.sort.counting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

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

    public void dualQuickSort(String[] A, int left, int right) {
        if (left >= right) {
            return;
        }
        if (compareStr(A[right], A[left])) {
            swap(A, left, right);
//            swap(names, left, right);
        }
        int less = left;
        int great = right;
        String pivot1 = A[left];
        String pivot2 = A[right];
        while (compareStr(A[++less], pivot1)) ;
        while (compareStr(pivot2, A[--great])) ;

        outer:
        for (int k = less - 1; ++k <= great; ) {
            String ak = A[k];
//            String nk = names[k];

            if (compareStr(ak, pivot1)) { //
                swap(A, k, less); //
//                swap(names, k, less);
                less++;
            } else if (compareStr(pivot2, ak)) { // ak > p2
                while (compareStr(pivot2, A[great])) { //
                    if (great-- == k) {
//                        System.out.println("outer");
                        break outer;
                    }
                }
                if (compareStr(A[great], pivot1)) { // a[great] <= pivot1，

                    A[k] = A[less];  //
//                    names[k] = names[less];
                    A[less] = A[great]; //
//                    names[less] = names[great];
                    ++less;  //
                } else { // pivot1 <= a[great] <= pivot2
                    A[k] = A[great];
//                    names[k] = names[great];
                }
                /*
                 * Here and below we use "a[i] = b; i--;" instead
                 * of "a[i--] = b;" due to performance issue.
                 */
                A[great] = ak;
//                names[great] = nk;
                --great;
            }
        }

//        System.out.println("left :" + left + " less " + less + " great" + great + " right " + right);
//        System.out.println(Arrays.toString(A));

        dualQuickSort(A, left, less - 1);
        dualQuickSort(A, less, great);
        dualQuickSort(A, great + 1, right);
    }

    public void DualPivotQuickSortTest(int n) {
        String filePath = "/Users/chenpeng/Documents/GitHub/INFO6205-Final-ZCL/shuffledChinese.txt";
        String[] ChineseWords = ReadTxt.readTxtFile(filePath,n);
        Pinyin PinYin = new Pinyin();
        String[] pinyin = PinYin.getPinYinWithTone(ChineseWords);

        DualPivotQuickSort dq = new DualPivotQuickSort();
        dq.dualQuickSort(pinyin, 0, pinyin.length - 1);

        Map<String, ArrayList> map = PinYin.getMap();
        String[] result = new String[pinyin.length];
        int index = 0;

        for(String pinyinword : pinyin){

            if(map.containsKey(pinyinword)){
                ArrayList<String> newList = map.get(pinyinword);

                for(String str: newList){
                    result[index++] = str;
                }

                map.remove(pinyinword);
            }

        }

//        for (int i = 0; i <= 100; i++) {
//            System.out.println(result[i]);
//            System.out.println(pinyin[i]);
//        }

        WriteTxt wt = new WriteTxt();
        wt.writeTxt("DualPivotQuickSorted.txt", result, 1000);
    }

    public static void main(String[] args) {
        DualPivotQuickSort test = new DualPivotQuickSort();
        test.DualPivotQuickSortTest(999998);
    }
}
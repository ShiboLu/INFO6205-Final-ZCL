package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.sort.counting.huskySort.sort.huskySort.PureHuskySort;
import edu.neu.coe.info6205.sort.counting.huskySort.sort.huskySortUtils.HuskyCoderFactory;

import java.util.ArrayList;
import java.util.Map;

public class MSDStringSort {
    private static int R = 256;
    private static String[] aux;
//    private static String[] auxName;

    public static int charAt(String s, int d) {
        if (d < s.length()) {
            return s.charAt(d);
        } else {
            return -1;
        }
    }

    public static void sort(String[] a) {
        int N = a.length;
        aux = new String[N];
        sort(a, 0, N - 1, 0);
    }

    public static void sort(String[] a, int lo, int hi, int d) {
        if (lo >= hi) {
            return;
        }
        int[] count = new int[R + 2];
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d) + 2]++;
        }
        for (int r = 0; r < R + 1; r++) {
            count[r + 1] += count[r];
        }
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d) + 1]++] = a[i];
//            auxName[count[charAt(a[i],d)+1]++]=names[i];
        }
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i - lo];
//            names[i]=auxName[i - lo];
        }
        for (int r = 0; r < R; r++) {
            sort(a, lo + count[r], lo + count[r + 1] - 1, d + 1);
        }
    }

    public void MSDStringSortTest(int n) {
        String filePath = "/Users/chenpeng/Documents/GitHub/INFO6205-Final-ZCL/shuffledChinese.txt";
        String[] ChineseWords = ReadTxt.readTxtFile(filePath, n);
        Pinyin PinYin = new Pinyin();
        String[] pinyin = PinYin.getPinYinWithTone(ChineseWords);

        MSDStringSort.sort(pinyin);

        Map<String, ArrayList> map = PinYin.getMap();
        String[] result = new String[pinyin.length];
        int index = 0;

        for (String pinyinword : pinyin) {

            if (map.containsKey(pinyinword)) {
                ArrayList<String> newList = map.get(pinyinword);

                for (String str : newList) {
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
        wt.writeTxt("MSDStringSorted.txt", result, 1000);
    }

    public static void main(String[] args) {
        MSDStringSort test = new MSDStringSort();
        test.MSDStringSortTest(999998);
    }
}

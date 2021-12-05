/*
  (c) Copyright 2018, 2019 Phasmid Software
 */
package edu.neu.coe.info6205.sort.finalProject;

import edu.neu.coe.info6205.sort.BaseHelper;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.SortWithHelper;
import edu.neu.coe.info6205.util.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Sorter which delegates to Timsort via Arrays.sort.
 *
 * @param <X>
 */
public class TimSort<X extends Comparable<X>> extends SortWithHelper<X> {

    /**
     * Constructor for TimSort
     *
     * @param helper an explicit instance of Helper to be used.
     */
    public TimSort(Helper<X> helper) {
        super(helper);
    }

    /**
     * Constructor for TimSort
     *
     * @param N      the number elements we expect to sort.
     * @param config the configuration.
     */
    public TimSort(int N, Config config) {
        super(DESCRIPTION, N, config);
    }

    public TimSort() throws IOException {
        this(new BaseHelper<>(DESCRIPTION, Config.load(TimSort.class)));
    }

    public void sort(X[] xs, int from, int to) {
        Arrays.sort(xs, from, to);
    }

    public static final String DESCRIPTION = "Timsort";

    public void TimSortTest(int n) throws IOException {
        String filePath = "/Users/chenpeng/Documents/GitHub/INFO6205-Final-ZCL/shuffledChinese.txt";
        String[] ChineseWords = ReadTxt.readTxtFile(filePath,n);
        Pinyin PinYin = new Pinyin();
        String[] pinyin = PinYin.getPinYinWithTone(ChineseWords);

        TimSort ts = new TimSort();
        ts.sort(pinyin, 0, pinyin.length);

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
        wt.writeTxt("TimSorted.txt", result, 1000);
    }

    public static void main(String[] args) throws IOException {
        TimSort test = new TimSort();
        test.TimSortTest(999998);
    }
}
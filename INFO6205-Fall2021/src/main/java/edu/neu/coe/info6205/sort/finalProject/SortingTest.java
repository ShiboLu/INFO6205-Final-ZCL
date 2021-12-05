/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.finalProject;

import edu.neu.coe.info6205.sort.finalProject.huskySort.sort.huskySortUtils.HuskyCoderFactory;
//import edu.neu.coe.info6205.sort.finalProject.TimSort;
import edu.neu.coe.info6205.sort.finalProject.huskySort.sort.huskySort.PureHuskySort;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.*;

@SuppressWarnings("ALL")
public class SortingTest {

	final List<Integer> list = new ArrayList<>();
	
    @Test
    public void testDualPivotQuickSort() {
        Benchmark<Integer> bm = new Benchmark_Timer<>(
                "testArray", null,
                num -> {
                    //System.out.println(list);
//                    System.out.println("run!"+num);
                    DualPivotQuickSort test = new DualPivotQuickSort();
                    test.DualPivotQuickSortTest(num);
                },
                null);

        Integer[] lengths = {250000, 500000, 999998, 999998 * 2, 999998 * 4};
        for(int length : lengths) {
//            System.out.println(new Date());
            System.out.println("DualPivotQuickSort:");
            double x = bm.run(length, 10); // length, times
            System.out.println("testSortArray time with length " + length + ": " + x);
//            System.out.println(new Date());
        }
    }

    @Test
    public void testLSDStringSort() {
        Benchmark<Integer> bm = new Benchmark_Timer<>(
                "testArray", null,
                num -> {
                    //System.out.println(list);
//                    System.out.println("run!"+num);
                    LSDStringSort test = new LSDStringSort();
                    test.LSDStringSortTest(num);
                },
                null);

        Integer[] lengths = {250000, 500000, 999998, 999998 * 2, 999998 * 4};
        for(int length : lengths) {
//            System.out.println(new Date());
            System.out.println("LSDStringSort:");
            double x = bm.run(length, 10); // length, times
            System.out.println("testSortArray time with length " + length + ": " + x);
//            System.out.println(new Date());
        }
    }

    @Test
    public void testMSDStringSort() {
        Benchmark<Integer> bm = new Benchmark_Timer<>(
                "testArray", null,
                num -> {
                    //System.out.println(list);
//                    System.out.println("run!"+num);
                    MSDStringSort test = new MSDStringSort();
                    test.MSDStringSortTest(num);
                },
                null);

        Integer[] lengths = {250000, 500000, 999998, 999998 * 2, 999998 * 4};
        for(int length : lengths) {
//            System.out.println(new Date());
            System.out.println("MSDStringSort:");
            double x = bm.run(length, 10); // length, times
            System.out.println("testSortArray time with length " + length + ": " + x);
//            System.out.println(new Date());
        }
    }

    @Test
    public void testTimSort() {
        Benchmark<Integer> bm = new Benchmark_Timer<>(
                "testArray", null,
                num -> {
                    //System.out.println(list);
//                    System.out.println("run!"+num);
                    TimSort test = null;
                    try {
                        test = new TimSort();
                        test.TimSortTest(num);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                },
                null);

        Integer[] lengths = {250000, 500000, 999998, 999998 * 2, 999998 * 4};
        for(int length : lengths) {
//            System.out.println(new Date());
            System.out.println("TimSort:");
            double x = bm.run(length, 10); // length, times
            System.out.println("testSortArray time with length " + length + ": " + x);
//            System.out.println(new Date());
        }
    }

    @Test
    public void testHuskySort() {
        Benchmark<Integer> bm = new Benchmark_Timer<>(
                "testArray", null,
                num -> {
                    //System.out.println(list);
//                    System.out.println("run!"+num);
                    PureHuskySort test = new PureHuskySort<>(HuskyCoderFactory.unicodeCoder, false, false);
                    test.PureHuskySortTest(num);
                },
                null);

        Integer[] lengths = {250000, 500000, 999998, 999998 * 2, 999998 * 4};
        for(int length : lengths) {
//            System.out.println(new Date());
            System.out.println("HuskySort:");
            double x = bm.run(length, 10); // length, times
            System.out.println("testSortArray time with length " + length + ": " + x);
//            System.out.println(new Date());
        }
    }
    
}
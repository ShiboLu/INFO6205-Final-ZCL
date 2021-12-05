/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.sort.counting;

import edu.neu.coe.info6205.sort.*;
import edu.neu.coe.info6205.sort.linearithmic.Partition;
import edu.neu.coe.info6205.sort.linearithmic.Partitioner;
//import edu.neu.coe.info6205.sort.linearithmic.QuickSort;
import edu.neu.coe.info6205.sort.counting.DualPivotQuickSort;
import edu.neu.coe.info6205.util.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static edu.neu.coe.info6205.util.Utilities.round;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ALL")
public class QuickSortDualPivotTest {

    @Test
    public void testSort() throws Exception {
        String[] xs = new String[4];
        xs[0] = "hello";
        xs[1] = "world";
        xs[2] = "java";
        xs[3] = "sort";
        DualPivotQuickSort s = new DualPivotQuickSort();
        s.dualQuickSort(xs, 0, xs.length - 1);
        assertEquals("hello", xs[0]);
        assertEquals("java", xs[1]);
        assertEquals("sort", xs[2]);
        assertEquals("world", xs[3]);
    }

    @Test
    public void testNumberMixedSort() throws Exception {
        String[] xs = new String[4];
        xs[0] = "chen2peng2";
        xs[1] = "cao2xin1wei3";
        xs[2] = "lu2shi4bo2";
        xs[3] = "zhang1feng1yi1";
        DualPivotQuickSort s = new DualPivotQuickSort();
        s.dualQuickSort(xs, 0, xs.length - 1);
        assertEquals("cao2xin1wei3", xs[0]);
        assertEquals("chen2peng2", xs[1]);
        assertEquals("lu2shi4bo2", xs[2]);
        assertEquals("zhang1feng1yi1", xs[3]);
    }

    @Test
    public void testRepeatedSort() throws Exception {
        String[] xs = new String[4];
        xs[0] = "chen2peng2";
        xs[1] = "cao2xin1wei3";
        xs[2] = "cao2xin1wei3";
        xs[3] = "chen2peng4";
        DualPivotQuickSort s = new DualPivotQuickSort();
        s.dualQuickSort(xs, 0, xs.length - 1);
        assertEquals("cao2xin1wei3", xs[0]);
        assertEquals("cao2xin1wei3", xs[1]);
        assertEquals("chen2peng2", xs[2]);
        assertEquals("chen2peng4", xs[3]);
    }





    final static LazyLogger logger = new LazyLogger(DualPivotQuickSort.class);

    @BeforeClass
    public static void beforeClass() throws IOException {
        config = Config.load();
    }

    private static Config config;
}
package com.cjhx.poc;

import org.junit.jupiter.api.Test;

/**
 * 冒泡排序算法测试类
 * 包含各种排序场景的测试用例
 */
import static org.junit.jupiter.api.Assertions.*;

public class BubbleSortTest {
    /**
     * 测试基本排序功能 - 对乱序数组进行排序
     */
    @Test
    public void testSort() {
        int[] input = {5, 3, 8, 1, 2};
        int[] expected = {1, 2, 3, 5, 8};
        
        BubbleSort.sort(input);
        assertArrayEquals(expected, input);
    }

    /**
     * 测试空数组情况 - 空数组排序后仍应为空数组
     */
    @Test
    public void testEmptyArray() {
        int[] input = {};
        int[] expected = {};
        
        BubbleSort.sort(input);
        assertArrayEquals(expected, input);
    }

    /**
     * 测试已排序数组 - 已排序数组不应被改变
     */
    @Test
    public void testAlreadySorted() {
        int[] input = {1, 2, 3, 4, 5};
        int[] expected = {1, 2, 3, 4, 5};
        
        BubbleSort.sort(input);
        assertArrayEquals(expected, input);
    }
}

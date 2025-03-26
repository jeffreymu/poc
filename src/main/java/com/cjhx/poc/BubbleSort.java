package com.cjhx.poc;

/**
 * 冒泡排序算法实现
 */
public class BubbleSort {
    /**
     * 使用冒泡排序算法对整数数组进行升序排序
     * @param array 待排序的整数数组
     * @throws IllegalArgumentException 如果输入数组为null
     * @timeComplexity O(n^2) 最坏和平均情况
     * @spaceComplexity O(1) 原地排序
     */
    public static void sort(int[] array) {
        if (array == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (array[j] > array[j+1]) {
                    // 交换array[j]和array[j+1]
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
}

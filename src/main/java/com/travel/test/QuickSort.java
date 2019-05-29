/***********************************************
 * File Name: QuickSort
 * Author: caoguobin
 * mail: caoguobin@live.com
 * Created Time: 29 05 2019 9:22
 ***********************************************/

package com.travel.test;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] array = {6, 1, 2, 7, 9, 3, 4, 51, 0, 8};
        sort(array, 0, array.length - 1);
        System.out.println(Arrays.toString(array));
    }

    private static void sort(int[] array, int left, int right) {
        if (left>right){
            return;
        }
        int i = left;
        int j = right;
        int temp = array[left];
        while (i < j) {
            while (j > i && array[j] >= temp) {
                j--;
            }
            while (j > i && array[i] <= temp) {
                i++;
            }
            if (i < j) {
                array[i] = array[i] + array[j];
                array[j] = array[i] - array[j];
                array[i] = array[i] - array[j];
            }
        }
        array[left] = array[i];
        array[i] = temp;

        sort(array, left, i - 1);
        sort(array, j + 1, right);
    }
}

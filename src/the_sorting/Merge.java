package the_sorting;

import java.util.Arrays;

public class Merge {

    public static void main(String[] args) {
        int[] array = {38, 29, 14, 35, 22, 61, 35, 59, 36, 2, -1, -12};
//        int[] aa = MergeSort(array, 0, array.length-1);
//        for (int a : aa){
//            System.out.println(a);
//        }

        sortMergel(array);
        for (int a: array){
            System.out.println(a);
        }
    }


    /**
     * 归并排序的迭代实现
     * @param array
     * @param low
     * @param high
     * @return
     */
    public static int[] MergeSort(int[] array, int low, int high){
        int mid = (low + high) / 2;
        if (low < high){
            MergeSort(array, low, mid);
            MergeSort(array, mid + 1, high);
            merge(array, low, mid, high);
        }
        return array;
    }


    public static void merge(int[] array, int low, int mid, int high){
        int[] temp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        //把较小的数先移到新数组中
        while (i <= mid && j<= high){
            if (array[i] < array[j]){
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[j++];
            }
        }
        //把左边剩余的数移入数组
        while (i <= mid){
            temp[k++] = array[i++];
        }
        //把右边剩余的移如数组
        while (j <= high){
            temp[k++] = array[j++];
        }
        //把新数组中的数覆盖nums数组
        for (int x = 0; x < temp.length; x++){
            array[x+low] = temp[x];
        }
    }

    /**
     * 归并排序非递归实现（迭代）
     * @param array
     */
    public static void sortMergel(int[] array){
        //初始化排序数组长度
        int len = 1;
        while (len < array.length){
            for (int i = 0; i < array.length; i += len * 2){
                sortHelper(array, i, len);
            }
            len *= 2;
        }
    }


    public static void sortHelper(int[] array, int start, int len){
        int[] temp = new int[len * 2];
        int i = start;
        int j = start + len;
        int k = 0;
        while(i < start + len && (j < start + len + len && j < array.length)) {
            temp[k++] = array[i] < array[j]? array[i++] : array[j++];
        }
        while(i < start + len && i < array.length) {  // 注意：这里i也可能超出长度
            temp[k++] = array[i++];
        }
        while(j < start + len + len && j < array.length) {
            temp[k++] = array[j++];
        }
        int right = start + len + len;
        int index = 0;
        while(start < array.length && start < right) {
            array[start++] = temp[index++];
        }
    }


}

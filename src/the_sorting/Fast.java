package the_sorting;

/**
 * 快速排序
 */
public class Fast {

    public static void main(String[] args) {
        int[] array = {38, 29, 14, 35, 22, 61, 35, 59, 36, 2, -1, -12};
        QuickSort(array, 0, array.length-1);
        for (int i = 0; i < array.length; i++){
            System.out.println(array[i]);
        }
    }


    public static void QuickSort(int[] array, int start, int end) {
        int left, right, temp, tap;
        if (start > end){
            return;
        }

        left = start;
        right = end;
        //temp就是基准位
        temp = array[start];

        while (left < right){
            //先看右边，依次往左递减
            while (temp <= array[right] && left < right){
                right--;
            }
            //再看左边，依次往右递增
            while (temp >= array[left] && left < right){
                left++;
            }

            //如果满足条件则交换
            if(left < right){
                tap = array[right];
                array[right] = array[left];
                array[left] = tap;
            }
        }
        //最后将基准与left和right相等位置的数字交换
        array[start] = array[left];
        array[left] = temp;
        QuickSort(array, start, right - 1);
        QuickSort(array, left + 1, end);
    }

}

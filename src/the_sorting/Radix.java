package the_sorting;

import java.util.ArrayList;

/**
 * 基数排序
 */
public class Radix {

    public static void main(String[] args) {
//        int[] array = {38, 29, 14, 35, 22, 61, 35, 59, 36, 2, -1, -12};
        int[] array = {21,56,88,195,354,1,35,12,6,7, 15, 23};
//        RadixSort(array, 3);
        RadixSort(array);
        for (int a : array){
            System.out.println(a);
        }
    }



    public static void radixSort(int[] array, int max){
        int[] count = new int[array.length];

        int[] bucket = new int[array.length];

        for (int k = 1; k <= max; k++){

            for (int i = 0; i < array.length; i++){
                count[i] = 0;
            }

            for (int i = 0; i < array.length; i++){
                count[getFigure(array[i], k)]++;
            }

            for (int i = 1; i < array.length; i++){
                count[i] = count[i] + count[i -1];
            }

            for (int i = array.length -1; i >= 0; i--){
                int j = getFigure(array[i], k);
                bucket[count[j] - 1] = array[i];
                count[j]--;
            }

            for (int i = 0, j = 0; j < array.length; i++, j++){
                array[i] = bucket[j];
            }
        }
    }


    private static int getFigure(int i, int k){
        int[] a = {1, 10, 100};
        return (i / a[k-1]) % 10;
    }


    /**
     * 基数排序
     * @param array
     * @return
     */
    public static int[] RadixSort(int[] array) {
        if (array == null || array.length < 2)
            return array;
        // 1.先算出最大数的位数；
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            max = Math.max(max, array[i]);
        }
        int maxDigit = 0;
        while (max != 0) {
            max /= 10;
            maxDigit++;
        }
        int mod = 10, div = 1;
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < 10; i++)
            bucketList.add(new ArrayList<Integer>());
        for (int i = 0; i < maxDigit; i++, mod *= 10, div *= 10) {
            for (int j = 0; j < array.length; j++) {
                int num = (array[j] % mod) / div;
                bucketList.get(num).add(array[j]);
            }
            int index = 0;
            for (int j = 0; j < bucketList.size(); j++) {
                for (int k = 0; k < bucketList.get(j).size(); k++)
                    array[index++] = bucketList.get(j).get(k);
                bucketList.get(j).clear();
            }
        }
        return array;
    }

}

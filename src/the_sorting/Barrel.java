package the_sorting;

import java.util.ArrayList;

/**
 * 桶排序
 */
public class Barrel {


    public static void main(String[] args) {
        int[] array = {38, 29, 14, 35, 22, 61, 35, 59, 36, 2};
        ArrayList<Integer> lists = new ArrayList<>();
        for (int i : array){
            lists.add(i);
        }
        ArrayList<Integer> a = BucketSort(lists, 10);
        for (int b: a){
            System.out.println(b);
        }
//        bucketSort(array);
    }


    public static void bucketSort(int[] array){
        //分桶，这里采用映射函数f(x) = x / 10;
        //输入数据为0~99之间的数字
        int bucketCount = 10;
        int[][] bucket = new int[bucketCount][array.length];
        for (int i = 0; i < array.length; i++){
            //这里使用f(x)
            int quotient = array[i] / 10;
            for (int j = 0; j < array.length; j++){
                if (bucket[quotient][j] == 0){
                    bucket[quotient][j] = array[i];
                    break;
                }
            }
        }
        //小桶排序
        for (int i = 0; i < bucket.length; i++){
            for (int j = 0; j < bucket[i].length; i++){
                if (bucket[i][j] == 0) {
                    break;
                }
                int value = bucket[i][j];
                int position = j;
                while (position > 0 && bucket[i][position - 1] > value){
                    bucket[i][position] = bucket[i][position -1];
                    position --;
                }
                bucket[i][position] = value;
            }
        }
        //输出
        for (int i = 0, index = 0; i < bucket.length; i++){
            for (int j = 0; j < bucket[i].length; j++){
                if (bucket[i][j] != 0){
                    array[index] = bucket[i][j];
                    index++;
                } else {
                    break;
                }
            }
        }

        for (int a : array){
            System.out.println(a);
        }
    }


    /**
     * 桶排序
     *
     * @param array
     * @param bucketSize
     * @return
     */
    public static ArrayList<Integer> BucketSort(ArrayList<Integer> array, int bucketSize) {
        if (array == null || array.size() < 2)
            return array;
        int max = array.get(0), min = array.get(0);
        // 找到最大值最小值
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i) > max)
                max = array.get(i);
            if (array.get(i) < min)
                min = array.get(i);
        }
        int bucketCount = (max - min) / bucketSize + 1;
        ArrayList<ArrayList<Integer>> bucketArr = new ArrayList<>(bucketCount);
        ArrayList<Integer> resultArr = new ArrayList<>();
        for (int i = 0; i < bucketCount; i++) {
            bucketArr.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < array.size(); i++) {
            bucketArr.get((array.get(i) - min) / bucketSize).add(array.get(i));
        }
        for (int i = 0; i < bucketCount; i++) {
            if (bucketSize == 1) { // 如果带排序数组中有重复数字时  感谢 @见风任然是风 朋友指出错误
                for (int j = 0; j < bucketArr.get(i).size(); j++)
                    resultArr.add(bucketArr.get(i).get(j));
            } else {
                if (bucketCount == 1)
                    bucketSize--;
                ArrayList<Integer> temp = BucketSort(bucketArr.get(i), bucketSize);
                for (int j = 0; j < temp.size(); j++)
                    resultArr.add(temp.get(j));
            }
        }
        return resultArr;
    }
}

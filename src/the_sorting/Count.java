package the_sorting;

/**
 * 8.
 * 计数排序
 */
public class Count {


    public static void main(String[] args) {
        int[] array = {38, 29, 14, 35, 22, 61, 35, 59, 36, 2, -1, -10, -12};
        int[] aa = countSort(array);
        for (int a: aa){
            System.out.println(a);
        }
    }



    public static int[] countSort(int[] array){
        //求取最大值最小值，计算中间数组的长度，中间数组是用来记录原始数据中每个值出现的频率
        int max = array[0];
        int min = array[0];
        for (int i : array){
            if (i > max){
                max = i;
            }
            if (i < min){
                min = i;
            }
        }

        //通过最大值最小值确定中间数组的长度
        int[] len = new int[max - min + 1];

        //循环遍历旧数组计数排序， 就是统计原始数组出现的频率到中间数组中
        for (int i: array){
            //数的位数
            len[i - min] += 1;
        }

        //遍历输出
        //创建最终数组，就是返回的数组，和原始数组长度相等，但是排序完成
        int[] result = new int[array.length];
        //记录最终数组的下标
        int index = 0;

        //先循环每一个元素，在计数排序器的下标中
        for (int i = 0; i < len.length; i++){
            //循环出现的次数  len[i]这个数出现的频路
            for (int j = 0; j < len[i]; j++){
                //以为原来减少了min现在加上min, 值就变成了原来的值
                result[index++] = i + min;
            }
        }
        return result;

    }

}

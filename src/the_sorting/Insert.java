package the_sorting;

/**
 * 插入排序
 */
public class Insert {

    public static void main(String[] args) {
        int[] array = {38, 29, 14, 35, 22, 61, 35, 59, 36, 2, -1, -12};
        setInsertionSort(array, array.length);
    }



    public static void setInsertionSort(int[] array, int length){
        //外层向右的index，即作为比较对象的数据的index
        for (int index = 1; index < length; index++){
            //用来比较的数据
            int temp = array[index];
            int leftindex = index - 1;
            //当比到最左边或者遇到比temp小的数据时，结束循环
            while (leftindex >= 0 && array[leftindex] > temp){
                array[leftindex + 1] = array[leftindex];
                leftindex--;
            }
            //把temp放到空位上
            array[leftindex + 1] = temp;
        }

        for (int a: array){
            System.out.println(a);
        }
    }

}

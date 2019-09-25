package the_sorting;

/**
 * 希尔排序
 */
public class Hill {

    public static void main(String[] args) {
        int[] array = {38, 29, 14, 35, 22, 61, 35, 59, 36, 2, -1, -12};
        shellsort(array);
    }


    public static void shellsort(int[] array){
        int len = array.length;
        int temp, gap = len / 2;
        while (gap > 0){
            for (int i = gap; i < len; i++){
                temp = array[i];
                int perIndex = i - gap;
                while (perIndex >= 0 && array[perIndex] > temp){
                    array[perIndex + gap] = array[perIndex];
                    perIndex -= gap;
                }
                array[perIndex + gap] = temp;
            }
            gap /= 2;
        }

        for (int a: array){
            System.out.println(a);
        }
    }

}

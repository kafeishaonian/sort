package the_sorting;

/**
 * 选择排序
 */
public class Choose {

    public static void main(String[] args) {
        int[] array = {38, 29, 14, 35, 22, 61, 35, 59, 36, 2, -1, -12};
        setSelect(array);
    }



    public static void setSelect(int[] array){
        for (int i = 0; i < array.length - 1; i++){
            int minIndex = i;
            for (int j = minIndex + 1; j < array.length; j++){
                if (array[j] < array[minIndex]){
                    minIndex = j;
                }
            }
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }

        for (int a : array){
            System.out.println(a);
        }
    }





}

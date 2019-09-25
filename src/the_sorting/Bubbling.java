package the_sorting;

/**
 * 冒泡排序
 */
public class Bubbling {


    public static void main(String[] args) {
        int[] array = {38, 29, 14, 35, 22, 61, 35, 59, 36, 2, -1, -12};
        setBubbling(array);
    }


    public static void setBubbling(int[] array){
        //排序轮数
        for (int i = 0; i < array.length; i++){
            //比较次数
            for (int j = 0; j < array.length - 1 - i; j++){
                if (array[j] > array[j + 1]){
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }

        for (int a: array){
            System.out.println(a);
        }
    }

}

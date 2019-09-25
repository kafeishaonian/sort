package the_sorting;

/**
 * 堆排序
 */
public class Heap {


    public static void main(String[] args) {
        int[] array = {38, 29, 14, 35, 22, 61, 35, 59, 36, 2, -1, -12};
        heapSort(array);
        for (int a: array){
            System.out.println(a);
        }
    }

    public static void heapSort(int[] array){
        //构造初始化对，从第一个非叶子节点开始调整，左右孩子节点中较大的交换到父节点中
        for (int i = (array.length) / 2 -1; i >= 0; i--){
            heapAdjust(array, array.length, i);
        }
        //排序，将最大的节点放在堆尾，然后从根节点重新调整
        for (int i = array.length - 1; i >=1; i--){
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapAdjust(array, i, 0);
        }
    }

    private static void heapAdjust(int[] array, int len, int i){
        int k = i, temp = array[i], index = 2 * k + 1;
        while (index < len){
            if (index + 1 < len){
                if (array[index] < array[index + 1]){
                    index = index + 1;
                }
            }
            if (array[index] > temp){
                array[k] = array[index];
                k = index;
                index = 2 * k + 1;
            } else {
                break;
            }
        }
        array[k] = temp;
    }




}

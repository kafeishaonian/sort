package data_structure;

/**
 * 有序数组
 */
public class OrderArray {

    private int[] intArray;
    //数组元素个数
    private int length = 0;

    /**
     * 构造方法，传入数组最大长度
     * @param max
     */
    public OrderArray(int max){
        intArray = new int[max];
    }

    /**
     * 使用二分查找法定位某个元素，如果存在返回其下标，不存在则分会-1
     * @param target
     * @return
     */
    public int find(int target){
        //搜索段最小元素的下标
        int lowerBound = 0;
        //搜索段最大元素的下标
        int upperBound = length -1;
        //当前检测元素的下标
        int curln;

        //如果数组为空，直接返回-1
        if (upperBound < 0){
            return -1;
        }

        while (true){
            curln = (lowerBound + upperBound) / 2;

            if (target == intArray[curln]){
                return curln;
            //在当前下标与搜索段的最小小标重合时，代表搜索段中只包含一个或两个元素
            } else if (curln == lowerBound){
                //既然走到该分支，证明上一个if分之不满足，即目标元素不等于低位元素
                if (target == intArray[upperBound]){ //等于高位元素，返回
                    return upperBound;
                } else {//高位元素也不等于目标元素，证明数组中没有该元素，搜索结束
                    return -1;
                }
            } else {//搜索段中的元素至少有三个，且当前元素不等于目标元素的下标
                if (intArray[curln] < target){
                    //如果当前元素小于目标元素，则将下一个搜索段的最小下标置为当前元素的下标
                    lowerBound = curln;
                } else {
                    //如果当前元素大于目标元素，则将下一个控制段的最大小标置为当前元素的下标
                    upperBound = curln;
                }
            }
        }
    }

    /**
     * 插入
     * @param elem
     */
    public void insert(int elem){
        int location = 0;

        //判断应插入未知的下标
        for (; location < length; location++){
            if (intArray[location] > elem){
                break;
            }
        }

        //将插入下标之后的所有元素后移一位
        for (int i = length; i > location; i--){
            intArray[i] = intArray[i-1];
        }

        //插入元素
        intArray[location] = elem;
        length++;
    }


    /**
     * 删除某个指定的元素值，删除成功返回true,失败返回false
     * @param target
     * @return
     */
    public boolean delete(int target){
        int index = -1;
        if ((index = find(target)) != -1){
            for (int i = index; i < length-1; i++){
                //删除元素之后的所有元素前移一位
                intArray[i] = intArray[i + 1];
            }
            length--;
            return true;
        } else {
            return false;
        }
    }


    /**
     * 列出所有元素
     */
    public void display(){
        for (int i = 0; i < length; i++){
            System.out.println(intArray[i] + "\t");
        }
        System.out.println();
    }
}

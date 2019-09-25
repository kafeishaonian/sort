package data_structure;

/**
 * 无序数组
 * 优点： 插入快，如果知道下标，可以很快的存取
 * 缺点：查找慢，删除慢，大小固定
 */
public class Array {
    private String[] strArray;
    private int lenght = 0;//数组元素个数

    /**
     * 构造方法，传入数组最大长度
     * @param max
     */
    public Array(int max){
        strArray = new String[max];
    }

    /**
     * 检测数组是否包含某个元素，如果存在返回其下标，不存在则返回-1
     * @param target
     * @return
     */
    public int contains(String target){
        int index = -1;
        for (int i = 0; i < lenght; i++){
            if (strArray[i].equals(target)){
                index = i;
                break;
            }
        }
        return index;
    }


    /**
     * 插入
     * @param elem
     */
    public void insert(String elem){
        strArray[lenght] = elem;
        lenght++;
    }


    /**
     * 删除某个指定的元素值，删除成功则返回true，否则返回false
     * @param target
     * @return
     */
    public boolean detele(String target){
        int index = -1;
        if ((index = contains(target)) != -1){
            for (int i = index; i < lenght-1; i++){
                //删除元素滞后所有的元素前移一位
                strArray[i] = strArray[i+1];
            }
            lenght--;
            return true;
        } else {
            return false;
        }
    }


    /**
     * 列出所有元素
     */
    public void display(){
        for (int i = 0; i < lenght; i++){
            System.out.println(strArray[i] + "\t");
        }
    }

}

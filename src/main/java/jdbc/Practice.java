package jdbc;

import java.util.ArrayList;
import java.util.List;

public class Practice {
    public static void main(String[] args) {
        int [] arr ={1,2,3,4,5};
       List<Integer> res = convert(arr);
        System.out.println(res);

    }

    public static List<Integer> convert(int[] arr ){
        List<Integer> list = new ArrayList<>();
        for(int a:arr){
           list.add(a);
        }
        return list;
    }
}

package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoopDemo {

    public static void main(String[] args) {

        List<Integer> intList = Arrays.asList(0, 1, 2, 3, 4, 5);

        System.out.println("Начальный массив:");
        System.out.println(intList);

        List<Integer> result = new ArrayList<>();

        System.out.println("Созданный пустой результат:");
        System.out.println(result);

        for(Integer i : intList) {
            if (i % 3 == 0) {
                System.out.println(i + " подходит");
                result.add(i);
            } else {
                System.out.println(i + " не подходит");
            }
        }

        System.out.println("Итоговый результат");
        System.out.println(result);

        List<Integer> result2;

        result2 = intList.stream().filter(i -> i % 3 == 0).toList();

        System.out.println("Итоговый результат 2");
        System.out.println(result2);

    }


}



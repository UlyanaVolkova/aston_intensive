package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> myList = new ArrayList<Integer>();
        List<Integer> list = new ArrayList<Integer>();

        list.add(8);
        list.add(45);
        list.add(100);
        list.add(111);

        myList.add(10);
        myList.add(25);
        myList.add(63);
        myList.add(2);
        myList.add(22);
        myList.addAll(list);

        System.out.println(myList);

        System.out.println(myList.get(5));

        myList.remove(5);

        System.out.println(myList);

        myList.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        System.out.println(myList);

        System.out.println(myList.isEmpty());

        myList.clear();

        System.out.println(myList.isEmpty());
        }
    }
package storage;

import java.util.*;

public class MainCustomHashSet {
    public static void main(String[] args) {

        CustomHashSet<String> customHashSet = new CustomHashSet<>();
        customHashSet.put("one");
        customHashSet.put("two");
        customHashSet.put("three");
        customHashSet.put("four");
        customHashSet.put("five");
        customHashSet.put("six");


        Iterator<String> iterator = customHashSet.iterator();
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());
        System.out.println(iterator.next());

        if (customHashSet.contains("one")) {
            System.out.println("yes");
        } else System.out.println("no");

        customHashSet.delete("one");

        if (customHashSet.contains("one")) {
            System.out.println("yes");
        } else System.out.println("no");


        for (String string : customHashSet) {
            System.out.println(string);
        }
    }
}

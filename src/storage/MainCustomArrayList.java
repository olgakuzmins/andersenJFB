package storage;

public class MainCustomArrayList {
    public static void main(String[] args) {

        CustomArrayList<String> stringCustomArrayList = new CustomArrayList<>();

        //adding one element
        stringCustomArrayList.add("one");
        stringCustomArrayList.add("two");
        stringCustomArrayList.add("three");
        stringCustomArrayList.add("seven");
        stringCustomArrayList.add("not four", 3);
        stringCustomArrayList.put("four", 3);

        //adding array
        String [] numbers = {"four", "five", "six"};
        stringCustomArrayList.add(numbers);
        stringCustomArrayList.add(numbers, 3);
        String [] minusNumbers = {"-four", "-five", "-six"};
        stringCustomArrayList.put(minusNumbers, 6);

        //deleting
        stringCustomArrayList.deleteByIndex(0);

        for (int i = 0; i < stringCustomArrayList.size(); i++) {
            System.out.println(stringCustomArrayList.getByIndex(i));
        }
    }
}

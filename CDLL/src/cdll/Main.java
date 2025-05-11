package cdll;

public class Main {
    public static void main(String[] args) {
        CDLinkedList<Integer> myCDLL = new CDLinkedList<>();
        //System.out.println(myCDLL.size()); // works!
        myCDLL.addFirst(1);
        myCDLL.addFirst(2);
        System.out.println(myCDLL.get(0));

    }
}
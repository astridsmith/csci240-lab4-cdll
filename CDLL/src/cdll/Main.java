package cdll;

public class Main {
    public static void main(String[] args) {
        CDLinkedList<Character> myCDLL = new CDLinkedList<>();

        // list empty, uncomment for error:
        //myCDLL.get(0);

        //System.out.println(myCDLL.size()); // works!
        myCDLL.addFirst('a');
        myCDLL.addLast('b');
        myCDLL.addLast('c');
        myCDLL.insertAt(1, 'b'); // will be a, b, b, c
        System.out.println(myCDLL.get(2)); // second b
        System.out.println("Prev: " + Character.toString(myCDLL.getPrev(0))); // circle to last
        System.out.println("Next: " + Character.toString(myCDLL.getNext(3))); // circle to first
        System.out.println(myCDLL.toString()); // print all!

    }
}
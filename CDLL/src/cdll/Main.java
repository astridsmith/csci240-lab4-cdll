package cdll;

public class Main {
    public static void main(String[] args) {
        CDLinkedList<Character> myCDLL = new CDLinkedList<>();
        //System.out.println(myCDLL.size()); // works!
        myCDLL.addFirst('a');
        myCDLL.addLast('b');
        myCDLL.addLast('c');
        myCDLL.insertAt(1, 'b');
        System.out.println(myCDLL.get(2));
        System.out.println("Prev: " + Character.toString(myCDLL.getPrev(0)));
        System.out.println("Next: " + Character.toString(myCDLL.getPrev(3)));
        System.out.println(myCDLL.toString());

    }
}
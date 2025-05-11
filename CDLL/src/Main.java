import org.w3c.dom.Node;

public class Main {
    public static void main(String[] args) {
        CDLinkedList myList = new CDLinkedList();
        myList.addFirst("a");
        myList.addLast("c");
        myList.insertAt(1, "b");


        // what's the next one?
        System.out.println(myList.toString());

    }
}
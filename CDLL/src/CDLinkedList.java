public class CDLinkedList<E> {
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        public Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<E> head;
    private int size;

    public CDLinkedList() {
        head = null;
        size = 0;
    }

    public void addFirst(E data) {
        if (isEmpty()){
            // create new node and assign next and prev pointers to itself
            Node node = new Node(data);
            node.prev = node;
            node.next = node;
            head = node;
        } else {
                Node node = new Node(data);
                node.prev = head.prev;
                node.next = head;
                head = node;
        }
    }

    public void addLast(E data) {
        //create new node
        Node<E> node = new Node<>(data);
        if (isEmpty()){
            //  ...and assign next and prev pointers to itself
            node.next = node;
            node.prev = node;
            head = node;
        } else {
            node.next = head.prev;
            node.prev = head;
            head.prev.next = node;
            head.prev = node;
        }
        size++;
    }

    public void removeFirst() {
        // new head should be head.next;
        if(head == null){throw new RuntimeException("Empty CircularDoublyLinkedList, can't remove.");} else if(size == 1){head = null;} else {
            Node<E> newHead = head.next;
            newHead.prev = head.prev;
            head.prev.next = newHead;
            head = newHead;
        }
        size --;
    }

    public void removeLast() {
        if (head.prev == null) {
            throw new RuntimeException("Empty CircularDoublyLinkedList, can't remove.");
        } else if (size == 1) {
            head = null;
        } else {
            Node<E> tail = head.prev;
            Node<E> newTail = tail.prev;
            head.prev = newTail;
            head.prev = newTail;
        }
        size --;
    }
    public void insertAt(int index, E data) {
//        Node current = head;
//        for (int i = 0; i < (this.size() - index); i++){
//            current = current.next;
//            if (index == i){
//                Node node = new Node(data);
//                node.prev = get(i - 1);
//                node.next = get(i + 1);
//            }
//        }
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else {
            Node<E> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            Node<E> newNode = new Node<>(data);
            newNode.prev = current.prev;
            newNode.next = current;
            current.prev.next = newNode;
            current.prev = newNode;
            size++;
        }
    }

    public Node<E> get(int index) {
        Node<E> current = head;
        if (index == 0){
            System.out.println("DEBUG Returning current!");
            return current;
        }
        for (int i = 0; i < index; i++) {
            current = current.next;
            if (index == i){
                return current;
            }
        }
        return current;
    }
    public int size() {
//        if (head == null){
//            return 0;
//        } else if (head.next == head) {
//            return 1;
//        } else {
//            int count = 1;
//            Node currentTraverseNode = head;
//            boolean firstRun = true;
//            while (true){
//                if (firstRun){
//                    currentTraverseNode = currentTraverseNode.next;
//                } else {
//                    if (currentTraverseNode != head){
//                        count++;
//                        currentTraverseNode = currentTraverseNode.next;
//                    } else {
//                        break;
//                    }
//                }
//            }
//            return count;
//        }
        return size;
    }

    public boolean isEmpty() {
        if (this.size == 0){
            return true;
        } else {
            return false;
        }
    }

    public void clear() {
        for (int i = 0; i < this.size(); i++){
            removeLast();
        }
    }
    @Override
    public String toString() {
        if (isEmpty()) {
            throw new RuntimeException("Can't convert to string - empty CDLinkedList");
        }
        String toPrint = "[";
        for (int i = 0; i < size(); i++) {
            toPrint = toPrint + (get((i)).data);
            if (i < (size())) { // comma adder
                toPrint = toPrint + ", ";
            }
        }
        toPrint = toPrint + get(size()).data;
        toPrint = toPrint + "]";
        return toPrint;
    }
}

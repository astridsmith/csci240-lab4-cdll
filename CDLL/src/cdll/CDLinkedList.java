package cdll;

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
    public E DEBUGgetHead(){
        return this.head.data;
    }

    private Node<E> head;
    private int size;

    public CDLinkedList() {
        head = null;
        size = 0;
    }

    public void addFirst(E data) {
        if (isEmpty()){
            // create first node in linked list
            Node<E> node = new Node<>(data);
            // make circular
            node.next = node;
            node.prev = node;

            this.head = node;
        } else {
            // get tail for pointer updating
            Node<E> tail = this.head.prev;

            // create this node
            Node<E> node = new Node<>(data);
            // attach to head
            node.next = this.head;
            node.prev = this.head.prev; // attach to end of list, since circular

            this.head.prev = node; // old head attach to new head (prev pointer)

            // make head
            this.head = node;

            //update tail
            tail.next = node;
        }
        size++;
    }

    public void addLast(E data) {
        if (isEmpty()){
            // create first node in linked list
            Node<E> node = new Node<>(data);
            // make circular
            node.next = node;
            node.prev = node;

            this.head = node;
        } else {
            // get tail
            Node<E> tail = this.head.prev;

            // create this node
            Node<E> node = new Node<>(data);

            // assign location
            node.prev = tail;
            node.next = this.head;

            // update old tail and head
            tail.next = node;
            this.head.prev = node;
        }
        size++;
    }

    public E removeLast() {
        if (isEmpty()){throw new RuntimeException("[ERROR] Empty list, can't remove!");} else if (size == 1) { E toReturn = this.head.data; head = null; size--; return toReturn;} else {
            // keep track of tail node
            Node<E> tail = this.head.prev;
            Node<E> newTail = tail.prev;
            // delete
            E toReturn = tail.data;
            tail = null;

            // attach new tail to head
            newTail.next = head;
            head.prev = newTail;
            // finish!
            size--;
            return toReturn;
        }
    }

    public E removeFirst() {
        if (isEmpty()){throw new RuntimeException("[ERROR] Empty list, can't remove!");} else if (size == 1) { E toReturn = this.head.data; head = null; size--; return toReturn;} else {
            // keep track of nodes to keep and join
            Node<E> tail = this.head.prev;
            Node<E> newHead = this.head.next;
            // delete existing head (this method, in essence, is removeHead lol)
            E toReturn = this.head.data; // idk why we need to return this (it's not a pop method) but added this to satisfy instructions 🤷️ also made line 89 really long, hope you have word wrap on!
            this.head = null;
            // redefine
            this.head = newHead;
            newHead.prev = tail;
            tail.next = newHead;
            // finish!
            size--;
            return toReturn;
        }
    }

    public void insertAt(int index, E data) {
        if (index > size()){throw new RuntimeException("Requested insertion index " + index + " is out of bounds for CDLL size of " + size());} else if (index == size()){
            // use addLast instead, it's simpler:
            addLast(data);
        } else if (index == 0) {
            addFirst(data);
        } else {
            Node<E> currentNode = this.head;

            for (int i = 0; i < index; i++){ // advance to the correct node and set vars
                //prevNode = currentNode.prev;
                //if (i == index){break;}
                currentNode = currentNode.next;
            }

            Node<E> prevNode = currentNode.prev; // just to initialize, should NOT stay as head at point of read operation!

            Node<E> toInsert = new Node<>(data); // insert this BEFORE the insertion index
            toInsert.prev = prevNode;
            toInsert.next = currentNode;

            prevNode.next = toInsert;
            currentNode.prev = toInsert;
            size ++;
        }
    }

    public E get(int index) {
        if (index > (size() - 1)){ throw new RuntimeException("Requested lookup index " + index + " is out of bounds for CDLL size of " + size());} else {
            Node<E> currentNode = head;
            for (int i = 0; i <= index; i++){
                if (i == index){
                    return currentNode.data;
                } else {
                    currentNode = currentNode.next;
                }
            }
            System.out.println("[ERROR] No data found at location specified!");
            return null;
        }
    }

    private Node<E> getNodeObjFromIndex(int index) {
        // internal copy of get method for using with nodes directly (used for delete methods and circular demo)
        if (index > (size() - 1)){ throw new RuntimeException("[Internal ERROR] Requested object lookup index " + index + " is out of bounds for CDLL size of " + size());} else {
            Node<E> currentNode = head;
            for (int i = 0; i <= index; i++) {
                if (i == index) {
                    return currentNode;
                } else {
                    currentNode = currentNode.next;
                }
            }
            return null;
        }
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        if (this.size == 0){return true;} else {return false;}
    }

    public void clear() {
        if (size() == 0){System.out.println("CDLL Empty, nothing to clear!");}
        Node currentNode = getNodeObjFromIndex(0);
        Node nextNode = getNodeObjFromIndex(0);
        for (int i = 0; i < (size() - 1); i++){
            if (i == 0){} else {
                currentNode = getNodeObjFromIndex(i);
                nextNode = getNodeObjFromIndex((i + 1));
            }
            currentNode.data = null;
            currentNode.prev = null;
            nextNode = currentNode.next;
            currentNode = nextNode;
        }
        size = 0;
        head = null;
    }

    public E getNext(int index){ return getNodeObjFromIndex(index).next.data;}
    public E getPrev(int index){ return getNodeObjFromIndex(index).prev.data;}


    @Override
    public String toString() {
        if (isEmpty()) {return "[]";} // empty array-like string representation

        String toReturn = "[" + head.data;
        Node<E> current = head.next;

        while (current != null && current != head) {  // loop until circled back to head
            toReturn = toReturn + ", " + current.data;
            current = current.next;
        }
        System.out.println("Finished while");
        toReturn = toReturn + "]";
        return toReturn;
    }
}
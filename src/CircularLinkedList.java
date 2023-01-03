import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/**
 * {@inheritDoc}
 */
public class CircularLinkedList<E> implements CircularLinkedListInterface<E>, Iterable<E>{


    /** First node in list */
    private Node<E> front;
    /** Size of list */
    private int size;
    /** Last node in list */
    private Node<E> end;


    /**
     * Constructs an empty circular linked list
     */
    public CircularLinkedList() {
        front = null;
        size = 0;
    }

    /**
     * Checks the node at the given position;
     * throws an exception if negative or greater than list size
     * @param position      location where node is at in list;
     *                      (must not be negative or greater than size)
     */
    private void checkPosition(int position) {
        if (position < 0 || position >= size) {
            throw new IllegalArgumentException("position must be between 0 and list size of " + size + " (exclusive)");
        }
    }

    /**
     * Returns a reference to the node at the given position
     * @param position      Position of node to check for
     * @return              Reference of node at given position
     */
    private Node<E> nodeAt(int position) {
        checkPosition(position);
        Node<E> current = front;
        for (int idx = 0; idx < position; idx++) {
            current = current.next;
        }
        return current;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E get(int position) {
        checkPosition(position);
        return nodeAt(position).data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(E value) {
        Node<E> newNode = new Node<>(value);
        if (front == null) {
            front = newNode;
            end = newNode;
            newNode.next = newNode;
            size++;
        } else {
            newNode.next = front;
            end.next = newNode;
            end = newNode;
            size++;
        }
    }


    // reverses order of nodes in list
//    public void reverse() {
//        if (front == null) {
//            add new node
//        }
//        Node<E> prev = null;
//        Node<E> curr = front;
//        Node<E> next;
//        do {
//            next = curr.next;
//            curr.next = prev;
//            prev = curr;
//            curr = next;
//        } while (curr != front);
//
//        front.next = prev;
//        front = prev;
//
//    }

    /**
     * Adds new node to the front of a list
     * @param data      node to be added to the front
     */
    public void addAtFront(E data) {
        Node<E> newNode = new Node<>(data);
        newNode.next = front;
        front = newNode;
        size++;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean remove(E value) {
        if (front == null) {
            return false;
        }
        Node<E> current = front;
        if (current == end.next && current.data.equals(value)) {
            end.next = end.next.next;
            front = front.next;
            size--;
            return true;
        } else {
            while (current != end && !(current.data.equals(value))) {
                if (current.next.data.equals(value)) {
                    if (current.next == end && current.next.data.equals(value)) {
                        current.next = current.next.next;
                        end = current;
                        size--;
                        return true;
                    }
                    current.next = current.next.next;
                    size--;
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void remove(int position) {
        checkPosition(position);
        if (position == 0) {
            front = front.next;
            size--;
        } else {
            Node<E> current = nodeAt(position-1);
            current.next = current.next.next;
            size--;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<E> iterator() {
        return new CircularLinkedListIterator();
    }

    /**
     * Inner static class for storing a single node of a linked list
     * @param <T>   Generic values being stored
     */
    private static class Node<T> {
        /** Data stored in this node */
        public T data;
        /** Link to next node in the list */
        public Node<T> next;


        /**
         * Constructs a node with given data and null link
         * @param data      Generic data to be stored
         */
        public Node(T data) {
            this(data, null);
        }

        /**
         * Constructs a node with given data and given link
         * @param data      Generic data to be stored
         * @param next      Link to next node
         */
        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }


    /**
     * Cycles through circular linked list checking for elements
     */
    private class CircularLinkedListIterator implements Iterator<E>{

        /** Location of node in list */
        private Node<E> position;
        // not required for project but can implement if you want
        //private boolean removeOk;

        /**
         * Constructs an iterator for circular linked list
         */
        public CircularLinkedListIterator() {
            position = front;
        }

        /**
         * Checks if there are nodes in list
         * @return      True if there are nodes, false otherwise
         */
        @Override
        public boolean hasNext() {
            if (front == null) {
                return false;
            } else {
                return position.next != null;
            }
        }

        /**
         * Finds the next node data in list
         * @return      Next node data in iteration
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E result = position.data;
            position = position.next;
            return result;
        }

        // not required to create for project but can if you want
        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

}

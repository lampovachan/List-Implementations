package main;

/**
 * This class provides the implementation of collection close to LinkedList from java.util.collections.
 *
 * @author Svitlana Tkachuk
 */

public class LinkedListImpl implements ListInterface {
    /**
     * Pointer to first node.
     */
    protected Node start;
    /**
     * Pointer to last node.
     */
    protected Node end;
    /**
     * The current number of elements.
     */
    public int size;

    /**
     * This constructor creates an empty linked list.
     */
    public LinkedListImpl() {
        start = null;
        end = null;
        size = 0;
    }

    /**
     * This method returns the size of collection.
     * @return The number which represents the size of collection.
     */
    public int size() {
        return size;
    }

    @Override
    public void add(Object val) {
        Node oldEnd = end;
        Node nptr = new Node(val, null, oldEnd);
        end = nptr;
        if(oldEnd == null) {
            start = nptr;
        } else {
            oldEnd.setLinkNext(nptr);
        }
        size++;
    }

    @Override
    public void add(int pos, Object val) {
        Node temp = new Node(val, null, null);
        Node current = start;
        if (current != null) {
            for (int i = 0; i < pos && current.getLinkNext() != null; i++) {
                current = current.getLinkNext();
            }
        }
        temp.setLinkNext(current.getLinkNext());
        current.setLinkNext(temp);
        size++;
    }

    @Override
    public boolean removeByValue(Object o) {
        Node trav = start;
        for (int i = 0; trav != null; trav = trav.next, i++) {
            if (o.equals(trav.data)) {
                removeByIndex(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeByIndex(int pos) {
        if (pos == 1) {
            if (size == 1) {
                start = null;
                end = null;
                size = 0;
                return;
            }
            start = start.getLinkNext();
            start.setLinkPrev(null);
            size--;
            return ;
        }
        if (pos == size) {
            end = end.getLinkPrev();
            end.setLinkNext(null);
            size-- ;
        }
        Node ptr = start.getLinkNext();
        for (int i = 2; i <= size; i++) {
            if (i == pos) {
                Node p = ptr.getLinkPrev();
                Node n = ptr.getLinkNext();

                p.setLinkNext(n);
                n.setLinkPrev(p);
                size-- ;
                return;
            }
            ptr = ptr.getLinkNext();
        }
    }

    @Override
    public boolean contains (Object element) {
        Node trav = start;
        for (; trav != null; trav = trav.next) {
            if (element.equals(trav.data)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedListImpl list = new LinkedListImpl();
        list.add(1);
        list.add(2);
        System.out.println(list.size());
        list.removeByIndex(1);
        System.out.println(list);
        System.out.println(list.contains(50));
    }

    /**
     * This static class represents the node of linked list on which it is based.
     */

    private static class Node {
        protected Object data;
        protected Node next, prev;

        public Node() {
            next = null;
            prev = null;
            data = null;
        }

        public Node(Object data, Node next, Node previous) {
            this.data = data;
            this.next = next;
            this.prev = previous;
        }
        public void setLinkNext(Node next) {
            this.next = next;
        }
        public void setLinkPrev(Node previous) {
            this.prev = previous;
        }
        public Node getLinkNext() {
            return next;
        }
        public Node getLinkPrev() {
            return prev;
        }
        public void setData(int d) {
            data = d;
        }
        public Object getData() {
            return data;
        }
    }
}





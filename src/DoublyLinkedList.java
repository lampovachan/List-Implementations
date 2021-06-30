/**
 * This class provides the implementation of collection close to LinkedList from java.util.collections.
 *
 * @author Svitlana Tkachuk
 */

class Node
{
    protected Object data;
    protected Node next, prev;

    public Node() {
        next = null;
        prev = null;
        data = 0;
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

public class DoublyLinkedList
{
    protected Node start;
    protected Node end ;
    public int size;

    public DoublyLinkedList() {
        start = null;
        end = null;
        size = 0;
    }

    public boolean isEmpty()
    {
        return start == null;
    }

    public int getSize()
    {
        return size;
    }

    public void insertAtEnd(Object val)
    {
        Node nptr = new Node(val, null, null);
        if(start == null)
        {
            start = nptr;
            end = start;
        }
        else
        {
            nptr.setLinkPrev(end);
            end.setLinkNext(nptr);
            end = nptr;
        }
        size++;
    }

    public void insertAtPos(int val , int pos)
    {
        Node nptr = new Node(val, null, null);
        Node ptr = start;
        for (int i = 2; i <= size; i++)
        {
            if (i == pos)
            {
                Node tmp = ptr.getLinkNext();
                ptr.setLinkNext(nptr);
                nptr.setLinkPrev(ptr);
                nptr.setLinkNext(tmp);
                tmp.setLinkPrev(nptr);
            }
            ptr = ptr.getLinkNext();
        }
        size++ ;
    }

    public void deleteAtPos(int pos)
    {
        if (pos == 1)
        {
            if (size == 1)
            {
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
        if (pos == size)
        {
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
                size--;
                return;
            }
            ptr = ptr.getLinkNext();
        }
    }
    public boolean contains (Object element) {
        int index = 0;
        Node trav = start;
            for (; trav != null; trav = trav.next, index++) {
                if (element.equals(trav.data)) {
                    return true;
                }
            }

        return false;
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.insertAtEnd(1);
        list.insertAtEnd(2);
        System.out.println(list.getSize());
        list.deleteAtPos(1);
        System.out.println(list);
        System.out.println(list.contains(50));
    }
}



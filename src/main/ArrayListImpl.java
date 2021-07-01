package main;

/**
 * This class provides the implementation of array based collection close to ArrayList from java.util.collections.
 * When the number of elements in the array reaches its current capacity, it automatically doubles its capacity.
 *
 * @author Svitlana Tkachuk
 */
public class ArrayListImpl implements ListInterface {
    /**
     * The capacity of the array cannot get any smaller than this value.
     */
    private static final int MINIMUM_CAPACITY = 16;

    /**
     * The current number of elements in the array.
     */
    private int size;

    /**
     * The storage for the elements.
     */
    private Object[] array;

    /**
     * This default constructor creates an instance of a dynamic array with the minimum
     * capacity (16 elements).
     */
    public ArrayListImpl() {
        this(MINIMUM_CAPACITY);
    }

    /**
     * This constructor creates an instance of an array with at least the specified
     * minimum capacity. The actual capacity will be a power of two that is greater or
     * equal to the specified minimum capacity.
     *
     * @param minimumCapacity The minimum initial size of the array.
     */
    public ArrayListImpl(int minimumCapacity) {
        int actualSize = MINIMUM_CAPACITY;
        while (actualSize < minimumCapacity) actualSize <<= 1;  // make sure it is a power of two
        this.array = new Object[actualSize];
        this.size = 0;  // no elements in the array yet
    }


    /**
     * This method doubles the capacity
     * when the number of elements in the array reaches its current capacity.
     */
    private void doubleCapacity() {
        Object[] newArray = new Object[array.length << 1];  // multiply current length by 2
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    /**
     * This method returns the size of collection.
     * @return The number which represents the size of collection.
     */
    public int size() {
        return size;
    }

    @Override
    public void add(Object element) {
        add(size(), element);
    }

    @Override
    public void add(int index, Object element) {
        if (array.length == size) doubleCapacity();
        array[index] = element;
        size++;
    }

    @Override
    public void removeByIndex(int index) {
        int numMoved = size - index - 1;
        if (numMoved > 0)
            System.arraycopy(array, index+1, array, index,
                    numMoved);
        array[--size] = null;
    }

    @Override
    public boolean removeByValue(Object element) {
        if (element == null) {
            for (int index = 0; index < size; index++)
                if (array[index] == null) {
                    removeByIndex(index);
                    return true;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (element.equals(array[index])) {
                    removeByIndex(index);
                    return true;
                }
        }
        return false;
    }

    @Override
    public boolean contains(Object element) {
        for (int i = 0; i < this.array.length; i++) {
            if (element.equals(this.array[i])) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        StringBuilder symbol = new StringBuilder("[");
        for (int i = 0; i < array.length; i++) {
            if (i == array.length - 1)
                symbol.append(array[i]);
            else
                symbol.append(array[i]).append(", ");
        }
        symbol.append("]");
        return symbol.toString();
    }

    public static void main(String[] args) {
        ArrayListImpl myArray = new ArrayListImpl();
        myArray.add('A');
        myArray.add('B');
        myArray.add('C');
        System.out.println(myArray.toString());
        System.out.println(myArray.size);
        myArray.removeByIndex(2);
        System.out.println(myArray.contains(50));
    }
}


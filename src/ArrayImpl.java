/**
 * This class provides the implementation of array based collection close to ArrayList from java.util.collections.
 * When the number of elements in the array reaches its current capacity, it automatically doubles its capacity.
 *
 * @author Svitlana Tkachuk
 */
public class ArrayImpl implements Array {
    /**
     * The capacity of the array cannot get any smaller than this value.
     */
    private final static int MINIMUM_CAPACITY = 16;

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
    public ArrayImpl() {
        this(MINIMUM_CAPACITY);
    }

    /**
     * This constructor creates an instance of an array with at least the specified
     * minimum capacity. The actual capacity will be a power of two that is greater or
     * equal to the specified minimum capacity.
     *
     * @param minimumCapacity The minimum initial size of the array.
     */
    public ArrayImpl(int minimumCapacity) {
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
     * This method halves the capacity
     * when the number of elements drops below 1/4th of its current capacity.
     */
    private void halveCapacity() {
        if (array.length == MINIMUM_CAPACITY) return;  // make sure we don't shrink too much
        Object[] newArray = new Object[array.length >> 1];  // divide current length by 2
        System.arraycopy(array, 0, newArray, 0, size);
        array = newArray;
    }

    @Override
    public void add(Object element) {
        this.array[this.size] = element;
        this.size++;
    }

    @Override
    public void set(int index, Object element) {
        if (array.length == size) doubleCapacity();
        if (index < size) System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size++;
    }

    @Override
    public void removeByIndex(int index) {
        System.arraycopy(array, index + 1, array, index, --size - index);
        array[size] = null;
        if (size < array.length >>> 2) halveCapacity();  // less than 1/4th
    }

    @Override
    public void removeByValue(Object element) {

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

    public static void main(String[] args) {
        ArrayImpl myArray = new ArrayImpl(3);
        myArray.add('A');
        myArray.add('B');
        myArray.add('C');
        myArray.toString();
        myArray.set(2, 'F');
        myArray.toString();
    }
}


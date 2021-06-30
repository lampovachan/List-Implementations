/**
 * This interface defines the invariant methods that all arrays must implement.
 *
 * @author Svitlana Tkachuk
 */
public interface Array extends Container {
    /**
     * This method adds the specified element to the end.
     */
    void add(Object element);

    /**
     * This methods sets the element at the specified position.
     * @param index The specified position where we want to set element.
     * @param element The specified element we want to add to the collection.
     */
    void set(int index, Object element);

    /**
     * This method removes the element at the specified position.
     * @param index The specified position of the element we want to remove.
     */
    void remove(int index);

    /**
     * This method removes the specified element.
     * @param element The specified element we want to remove.
     */
    void remove(Object element);

    /**
     * This method returns true if the collection contains specified element,
     * or false if this collection does not contain the element.
     * @param element The specified element we want to find out in the collection.
     * @return True if the element was found, or false if there is no such element.
     */
    boolean contains(Object element);
}

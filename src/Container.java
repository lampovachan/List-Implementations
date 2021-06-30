/**
 * This interface defines the invariant methods that all collections must implement.
 *
 * @author Svitlana Tkachuk
 */
public interface Container extends Iterable<Object> {

    /**
     * This method returns a string representation of collection.
     * @return The string representation of all elements the collection contains.
     */
    String toString();
}

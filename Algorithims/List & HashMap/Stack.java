package hw2;

/**
 * Interface for a simple Stack. A stack class can be built by
 * implementing this interface.
 */
public interface Stack<T> {
    /**
     * Add an element to the top of the stack.
     *
     * @param v element to add to the stack
     */
    public void push(T v);

    /**
     * Remove and return the top element of the stack.
     *
     * @throws NullPointerException when stack is empty.
     * @return Top element of the stack.
     */
    public T pop();

    /**
     * Returns `true` if the stack is empty, `false` otherwise.
     *
     * @return `true` if stack is empty, `false` otherwise.
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in the stack.
     *
     * @return An `int` representing the number of elements in the stack.
     */
    public int size();
}

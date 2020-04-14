package hw2;

/**
 * Interface for a simple Queue. A queue class can be built by
 * implementing this interface.
 */
public interface Queue<T> {
    /**
     * Add an element to the back of the queue.
     *
     * @param v the element to add to the back of the queue.
     */
    public void push(T v);

    /**
     * Remove and return the element at the front of the queue.
     *
     * @throws NullPointerException when the queue is empty.
     * @return The front element of the queue.
     */
    public T pop();

    /**
     * Returns `true` if the queue is empty, `false` otherwise.
     *
     * @return `true` if the queu is empty, `false` otherwise.
     */
    public boolean isEmpty();

    /**
     * Returns the number of elements in the queue as an int.
     *
     * @return An int representing the number of elements in the queue.
     */
    public int size();

}

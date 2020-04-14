package hw2;

public class ListNode<T> {
    private T value;
    private ListNode<T> next;
    private ListNode<T> prev;

    public ListNode(T v) {
	value = v;
	next = null;
	prev = null;
    }

    public ListNode<T> next() { return next; }
    public void setNext(ListNode<T> n) { next = n; }

    public ListNode<T> prev() { return prev; }
    public void setPrev(ListNode<T> p) { prev = p; }

    public T value() { return value; }
    public void setValue(T v) { value = v; }

    @Override
    public String toString() {
	return String.format("Node(%s)", value.toString());
    }
}

package hw2;


public class ListStack<T> implements Stack<T> {
    private List<T> root;

    public ListStack() {
        root = new List();
    }
    public void push(T v) {
        root.insert(v);
    }
    public T pop() {
	    return root.popFront();
    }
    public boolean isEmpty() {
	    return root.isEmpty();
    }
    public int size() {
	    return root.size();
    }
}

package hw2;

public class ListQueue<T> implements Queue<T> {
    private List<T> root;

    public ListQueue() {
        root = new List();
    }
    public void push(T v) {
	    root.append(v);
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

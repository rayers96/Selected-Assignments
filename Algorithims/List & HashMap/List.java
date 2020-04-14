package hw2;

import java.lang.StringBuilder;
import java.util.function.Consumer;
import java.util.function.Predicate;


public class List<T> {
    private ListNode<T> root;
    private ListNode<T> tail;
    private int size;

    public List() {
	root = null;
	tail = null;
	size = 0;
    }

    public T find(Predicate<T> pred) {
	if (root == null) return null;
	ListNode<T> runner = root;
	while (runner != null) {
	    if (pred.test(runner.value())) {
		return runner.value();
	    }
	    runner = runner.next();
	}
	return null;
    }

    public void insert(T v) {
    	ListNode<T> x = new ListNode(v);
    	x.setNext(root);
        if (root != null) root.setPrev(x);
        root = x;
        size++;

        if (tail == null) tail = root;
    }

    public void append(T v) {
		ListNode<T> x = new ListNode(v);
		x.setPrev(tail);
		if (tail != null) tail.setNext(x);
		tail = x;
	    size++;

	    if (root == null) root = tail;
    }
    
    public void delete(T v) {
    	ListNode<T> runner = root;
    	while (runner != null) {
			if (runner.value().equals(v)) {
				ListNode<T> prev = runner.prev();
				ListNode<T> next = runner.next();
				if (prev != null) prev.setNext(next);
				if (next != null) next.setPrev(prev);
				size--;
				return;
			}
			runner = runner.next();
		}
    }

    public T popFront() throws IllegalStateException {
		if (root == null) return null;

		var v = root.value();
		root = root.next();
		size--;
		return v;
    }

    public T popBack() throws IllegalStateException {
    	if (tail == null) return null;

    	var v = tail.value();
    	tail = tail.prev();
    	size--;
    	return v;
    }

    public int size() {
    	return size;
    }

    public boolean isEmpty() {
		return size == 0;
    }

    public void forEach(Consumer<T> consumer) {
	var runner = root;
	while (runner != null) {
	    consumer.accept(runner.value());
	    runner = runner.next();
	}
    }

    @Override
    public String toString() {
	class StrConsumer implements Consumer<T> {
	    StringBuilder builder;

	    StrConsumer() {
		builder = new StringBuilder("[ ");
	    }
	    
	    public void accept(T t) {
		builder.append(t);
		builder.append(" ");
	    }

	    public String str() {
		builder.append("]");
		return builder.toString();
	    }
	}

	var strConsumer = new StrConsumer();
	forEach(strConsumer);
	return strConsumer.str();
    }
}

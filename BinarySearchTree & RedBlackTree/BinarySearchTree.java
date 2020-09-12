package hw3;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;

public class BinarySearchTree<K extends Comparable, V> {
    private BNode<K, V> root;
    private int size;

    public BinarySearchTree() {
	root = null;
	size = 0;
    }

    public static <K extends Comparable, V> BinarySearchTree fromList(List<Pair<K, V>> pairs) {
	var bst = new BinarySearchTree();
	for (Pair<K, V> p : pairs) {
	    bst.put(p.getKey(), p.getValue());
	}
	return bst;
    }

    public static <K extends Comparable, V> BinarySearchTree randomizeFromList(List<Pair<K, V>> pairs) {
	var l = new ArrayList(pairs);
	Collections.shuffle(l);
	var bst = BinarySearchTree.fromList(l);
	return bst;
    }

    /**
     * Adds the given key-value pair to the tree if the key does not already
     * exists in the tree. If the key exists, the associated value is updated.
     *
     * @param k Key being inserted.
     * @param v Value being inserted.
     */
    public void put(K k, V v) {
        BNode z = new BNode(k, v);
        BNode y = null;
        BNode x = root;
        while (x != null) {
            y = x;
            if (z.key().compareTo(x.key()) == 0) {
                x.setValue(v);
                return;
            } else if (z.key().compareTo(x.key()) < 0) {
                x = x.leftChild();
            } else {
                x = x.rightChild();
            }
        }
        z.setParent(y);
        if (y == null) {
            root = z;
        } else if (z.key().compareTo(y.key()) < 0) {
            y.setLeftChild(z);
        } else {
            y.setRightChild(z);
        }
        size++;
    }

    /**
     * Returns an optional containing the associated value for the given
     * key if it exists and is empty otherwise.
     *
     * @param k Key being fetched.
     * @return Optional containing the associated value if it exists, empty otherwise.
     */
    public Optional<V> get(K k) {
        BNode x = getNode(k);
        if (x != null) {
            return (Optional<V>)Optional.of(x.value());
        }
        return Optional.empty();
    }

    /**
     * Returns a BNode reference to the node associated with the given key.
     *
     * @param k Key being fetched.
     * @return Associated BNode.
     */
    private BNode<K, V> getNode(K k) {
        BNode x = root;
        while (x != null && k.compareTo(x.key()) != 0) {
            if (k.compareTo(x.key()) < 0) {
                x = x.leftChild();
            } else {
                x = x.rightChild();
            }
        }
        return x;
    }

    /**
     * Deletes the node with key k from the tree.
     *
     * @param k key for the element to be deleted.
     */
    public void delete(K k) {
	    BNode x = getNode(k);
	    if (x != null) {
	        if (x.leftChild() == null) {
	            transplant(x, x.rightChild());
            } else if (x.rightChild() == null) {
	            transplant(x, x.leftChild());
            } else {
	            BNode y = findMinimum(x.rightChild());
	            if (y.parent() != x) {
	                transplant(y, y.rightChild());
	                y.setRightChild(x.rightChild());
	                y.rightChild().setParent(y);
                }
	            transplant(x, y);
	            y.setLeftChild(x.leftChild());
	            y.leftChild().setParent(y);
            }
	        size--;
        }
    }

    /**
     * Replaces node u with node v.
     *
     * @param u BNode being replaced.
     * @param v BNode doing the replacing.
     */
    private void transplant(BNode<K, V> u, BNode<K, V> v) {
        if (u.parent() == null) {
            root = v;
        } else if (u == u.parent().leftChild()) {
            u.parent().setLeftChild(v);
        } else {
            u.parent().setRightChild(v);
        }
        if (v != null) {
            v.setParent(u.parent());
        }
    }

    /**
     * Returns the minimum node in the given subtree.
     *
     * @param z root of subtree being searched.
     */
    private BNode<K, V> findMinimum(BNode<K, V> z) {
	    BNode x = z;
        while (x.leftChild() != null) {
            x = x.leftChild();
        }
        return x;
    }

    /**
     * Deletes and returns the node with key k from the tree.
     *
     * @param k key corresponding to the element to be deleted and returned.
     * @return Optional possibly containing the value of the removed node.
     */
    public Optional<V> remove(K k) {
	    Optional<V> v = get(k);
	    delete(k);
	    return v;
    }

    public BNode<K, V> root() { return root; }
    public boolean isEmpty() { return size <= 0; }
    public int size() { return size; }

    public void preOrderTraversal(BiConsumer<K, V> consumer) { Tree.preOrderTraversal(root, consumer); }
    public void inOrderTraversal(BiConsumer<K, V> consumer) { Tree.inOrderTraversal(root, consumer); }
    public void postOrderTraversal(BiConsumer<K, V> consumer) { Tree.postOrderTraversal(root, consumer); }

    @Override
    public String toString() {
	return Tree.stringFromTree(root);
    }

    public int height() {
	return Tree.height(root);
    }

    
}

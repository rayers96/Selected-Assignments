package hw3;

import java.util.function.BiConsumer;
import java.util.List;
import java.util.Optional;

public class RedBlackTree<K extends Comparable, V> {
    private BNode<K, V> nil = new BNode(null, null);
    private BNode<K, V> root;
    private int size;

    public RedBlackTree() {
	root = nil;
	nil.setParent(nil);
	nil.setLeftChild(nil);
	nil.setRightChild(nil);
	size = 0;
    }

    public static <K extends Comparable, V> RedBlackTree<K, V> fromList(List<Pair<K, V>> pairs) {
	RedBlackTree<K, V> rbt = new RedBlackTree();
	for (Pair<K, V> p : pairs) {
	    rbt.put(p.getKey(), p.getValue());
	}
	return rbt;
    }

    /**
     * Returns a reference to the node associated with key `k`.
     *
     * @param k Key of node.
     * @returns BNode reference to respective node.
     */
    private BNode<K, V> getNode(K k) {
        BNode x = root;
        while (x != nil && k.compareTo(x.key()) != 0) {
            if (k.compareTo(x.key()) < 0) {
                x = x.leftChild();
            } else {
                x = x.rightChild();
            }
        }
        return x;
    }

    /**
     * Returns an Optional possibly containing the value of the respective node.
     * If the node exists, the Optional contains the node value, otherwise the
     * optional is empty.
     *
     * @param k Key of node being fetched.
     * @returns Optional of the associated value type.
     */
    public Optional<V> get(K k) {
        BNode x = getNode(k);
        if (x != nil) {
            return (Optional<V>)Optional.of(x.value());
        }
        return Optional.empty();
    }

    /**
     * Adds the key-value pair to the tree if the key does not already
     * exist, otherwise updates the value for the associated key.
     *
     * @param key Key being inserted.
     * @param value Value being inserted.
     */
    public void put(K key, V value) {
        BNode z = new BNode(key, value);
	    BNode y = nil;
	    BNode x = root;
	    while (x != nil) {
	        y = x;
	        if (z.key().compareTo(x.key()) == 0) {
	            x.setValue(value);
	            return;
            } else if (z.key().compareTo(x.key()) < 0) {
	            x = x.leftChild();
            } else {
	            x = x.rightChild();
            }
        }
	    z.setParent(y);
	    if (y == nil) {
	        root = z;
        } else if (z.key().compareTo(y.key()) < 0) {
	        y.setLeftChild(z);
        } else {
	        y.setRightChild(z);
        }
        size++;
        z.setRightChild(nil);
	    z.setLeftChild(nil);
	    z.setColor(Color.RED);
        insertFix(z);
    }

    /**
     * Handles fixup after insert.
     *
     * @param z Node to start fixup at.
     */
    private void insertFix(BNode z) {
        while (z.parent().color().equals(Color.RED)) {
            if (z.parent() == z.parent().parent().leftChild()) {
                BNode y = z.parent().parent().rightChild();
                if (y.color().equals(Color.RED)) {
                    z.parent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    z = z.parent().parent();
                } else if (z == z.parent().rightChild()) {
                    z = z.parent();
                    rotateLeft(z);
                }
                z.parent().setColor(Color.BLACK);
                z.parent().parent().setColor(Color.RED);
                rotateRight(z.parent().parent());
            } else {
                BNode y = z.parent().parent().leftChild();
                if (y.color() == Color.RED) {
                    z.parent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    z = z.parent().parent();
                } else if (z == z.parent().leftChild()) {
                    z = z.parent();
                    rotateRight(z);
                }
                z.parent().setColor(Color.BLACK);
                z.parent().parent().setColor(Color.RED);
                rotateLeft(z.parent().parent());
            }
            nil.setColor(Color.BLACK); // Reset this to ensure nil nodes are black
        }
        root.setColor(Color.BLACK);
    }

    /**
     * Rotate-left algorithm from CLRS.
     */
    private void rotateLeft(BNode<K, V> x) {
        if (x == nil) return;
        BNode y = x.rightChild();
        x.setRightChild(y.leftChild());
        if (y.leftChild() != nil) {
            y.leftChild().setParent(x);
        }
        y.setParent(x.parent());
        if (x.parent() == nil) {
            root = y;
        } else if (x == x.parent().leftChild()) {
            x.parent().setLeftChild(y);
        } else {
            x.parent().setRightChild(y);
        }
        y.setLeftChild(x);
        x.setParent(y);
    }

    /**
     * Rotate-right algorithm, similar to rotate-left.
     */
    private void rotateRight(BNode<K, V> y) {
        if (y == nil) return;
        BNode x = y.leftChild();
        y.setLeftChild(x.rightChild());
        if (x.rightChild() != nil) {
            x.rightChild().setParent(y);
        }
        x.setParent(y.parent());
        if (y.parent() == nil) {
            root = x;
        } else if (y == y.parent().rightChild()) {
            y.parent().setRightChild(x);
        } else {
            y.parent().setLeftChild(x);
        }
        x.setRightChild(y);
        y.setParent(x);
    }

    public void preOrderTraversal(BiConsumer<K, V> consumer) {
	Tree.preOrderTraversal(root, consumer);
    }

    @Override
    public String toString() {
	return Tree.stringFromTree(root);
    }

    public int height() {
	return Tree.height(root);
    }

    public boolean isEmpty() {
	return size == 0;
    }

}

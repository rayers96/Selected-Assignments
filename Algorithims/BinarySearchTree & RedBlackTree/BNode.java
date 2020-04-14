package hw3;

public class BNode<K extends Comparable, V> {
    private K key;
    private V value;
    private Color color;
    private BNode<K, V> leftChild;
    private BNode<K, V> rightChild;
    private BNode<K, V> parent;

    public BNode(K k, V v) {
	key = k;
	value = v;
	color = Color.BLACK;
	leftChild = null;
	rightChild = null;
	parent = null;
    }

    public BNode(K k, V v, BNode<K, V> n) {
	key = k;
	value = v;
	color = Color.BLACK;
	leftChild = n;
	rightChild = n;
	parent = n;
    }

    public void setLeftChild(BNode<K, V> lc) { leftChild = lc; }
    public BNode<K, V> leftChild() { return leftChild; }
    public void setRightChild(BNode<K, V> rc) { rightChild = rc; }
    public BNode<K, V> rightChild() { return rightChild; }
    public void setParent(BNode<K, V> p) { parent = p; }
    public BNode<K, V> parent() { return parent; }
    public K key() { return key; }
    public V value() { return value; }
    public void setValue(V v) { value = v; }
    
	
    public Color color() { return color; }
    public void setColor(Color c) { color = c; }
    
    @Override
    public String toString() {
	return String.format("RBNode(k: %s, v: %s)",
			     key.toString(),
			     value.toString());
    }
    
}

package hw3;

import java.util.function.BiConsumer;

public class Tree {

    // Student to implement
    public static <K extends Comparable, V> void preOrderTraversal(BNode<K, V> node, BiConsumer<K, V> consumer) {
	if (node == null) return;
	consumer.accept(node.key(), node.value());
	preOrderTraversal(node.leftChild(), consumer);
	preOrderTraversal(node.rightChild(), consumer);
    }

    public static <K extends Comparable, V> void inOrderTraversal(BNode<K, V> node, BiConsumer<K, V> consumer) {
	if (node == null) return;
	inOrderTraversal(node.leftChild(), consumer);
	consumer.accept(node.key(), node.value());
	inOrderTraversal(node.rightChild(), consumer);
    }

    public static <K extends Comparable, V> void postOrderTraversal(BNode<K, V> node, BiConsumer<K, V> consumer) {
	if (node == null) return;
	postOrderTraversal(node.leftChild(), consumer);
	postOrderTraversal(node.rightChild(), consumer);
	consumer.accept(node.key(), node.value());
    }

    public static <K extends Comparable, V> String stringFromTree(BNode<K, V> node) {
	StringBuilder builder = new StringBuilder();
	stringFromTreeHelper(node, builder, 0);
	return builder.toString();
    }

    private static <K extends Comparable, V> void stringFromTreeHelper(BNode<K, V> node, StringBuilder builder, int tabbed) {
	// Get tab offset
	StringBuilder offsetBuilder = new StringBuilder();
	for (int i = 0; i < tabbed; i++) {
	    // offset is two spaces
	    offsetBuilder.append("  ");
	}
	String offset = offsetBuilder.toString();
	builder.append(offset);

	// Add appropriate node
        if (node == null || node.key() == null || node.value() == null) {
	    builder.append("(NIL)\n");
	    return;
	}
	builder.append(String.format("(K: %s, V: %s, C: %s\n", node.key(), node.value(), node.color()));
	stringFromTreeHelper(node.leftChild(), builder, tabbed+1);
	stringFromTreeHelper(node.rightChild(), builder, tabbed+1);
	builder.append(offset + ")\n");
    }

    public static <K extends Comparable, V> int height(BNode<K, V> node) {
	if (node == null || node.value() == null || node.key() == null) return 0;
	int lHeight = height(node.leftChild());
	int rHeight = height(node.rightChild());
	return 1 + Math.max(lHeight, rHeight);
    }
    
}

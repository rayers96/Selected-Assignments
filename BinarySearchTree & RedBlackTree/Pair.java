package hw3;

public class Pair<K, V> {
    private K key;
    private V value;
    
    public Pair(K k, V v) {
	key = k;
	value = v;
    }

    public K getKey() { return key; }
    public void setKey(K k) { key = k; }
    public V getValue() { return value; }
    public void setValue(V v) { value = v; }

}

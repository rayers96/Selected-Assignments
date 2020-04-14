package hw2;

public class KVPair<T extends Comparable<? super T>, U> {
    private T key;
    private U value;

    public KVPair(T k, U v) {
	assert k instanceof Comparable;
	key = k;
	value = v;
    }

    public T key() { return key; }
    public U value() { return value; }
    public void setValue(U v) { value = v; }

    @Override
    public String toString() {
	return String.format("KVPair(key: %s, value: %s)",
			     key.toString(), value.toString());
    }
}

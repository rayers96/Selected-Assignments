package hw2;

import java.lang.StringBuilder;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class SimpleHashMap<T extends Comparable<? super T>, U> {
    private ArrayList<List<KVPair<T, U>>> map;
    private int nKeys = 0;
    private double alpha = .5;

    public SimpleHashMap() {
	// Create an empty map intially
	map = new ArrayList();
    }

    public SimpleHashMap(double a) {
	this();
	alpha = a;
    }

    /**
     * Allocates space for the map's backing array and copies
     * elements into the newly allocated space.
     */
    public void allocateCapacity(int n) {
	ArrayList<List<KVPair<T, U>>> newMap = new ArrayList(n);
	for (int i = 0; i < n; i++) {
	    newMap.add(new List());
	}
	for (int i = 0; i < map.size(); i++) {
	    if (!map.get(i).isEmpty()) {
		// Re-hash old key-value pairs
		map.get(i).forEach(new Consumer<KVPair<T, U>>() {
			public void accept(KVPair<T, U> kvPair) {
			    int k = (int)(kvPair.key().hashCode() % n);
			    newMap.get(k).insert(kvPair);
			}
		    });
	    }
	}
	map = newMap;
    }

    /**
     * Create mapping from key to value.
     */
    public void put(T key, U value) {
	if (nKeys == 0 || map.size() == 0) {
	    allocateCapacity(2);
	} else if ((double)nKeys/map.size() > alpha) {
	    allocateCapacity(map.size() * 2);
	}

	KVPair<T, U> pair = new KVPair(key, value);
	int k = (int)(pair.key().hashCode() % map.size());
	map.get(k).insert(pair);
	nKeys++;
    }

    /**
     * Return value associated with key, otherwise return null.
     */
    public U get(T key) {
	class Finder implements Predicate {
	    @Override
	    public boolean test(Object v) {
		if (v instanceof KVPair) {
		    return ((Comparable)((KVPair) v).key()).compareTo(key) == 0;
		}
		return false;
	    }
	}
	Finder f = new Finder();
	int k = (int)(key.hashCode() % map.size());
	List bucket = map.get(k);
	int size = map.get(k).size();

    for (int i = 0; i < size; i++) {
	    KVPair<T, U> current = map.get(k).popFront();
	    bucket.append(current);
	    if (f.test(current)) {
		    return current.value();
	    }
    }
	return null;
    }

    /**
     * Returns true if map contains no keys, false otherwise.
     */
    public boolean isEmpty() {
		return nKeys == 0;
    }

    /**
     * Returns the capacity of the map. The capacity is defined as the
     * number of buckets in the backing array.
     */
    public int capacity() {
		return map.size();
    }

    /**
     * Returns the number of keys currently stored in the map.
     */
    public int size() {
		return nKeys;
    }
    
    public Double averageChainSize() {
	Double sizes = 0.0;
	for (var list : map) {
	    sizes += list.size();
	}
	return sizes / map.size();
    }

    public Integer maxChainSize() {
	Integer s = 0;
	for (var list : map) {
	    if (list.size() > s) {
		s = list.size();
	    }
	}
	return s;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("[\n");
	for (int i = 0; i < map.size(); i++) {
	    builder.append(String.format("\t[%d] -> %s\n", i, map.get(i).toString()));
	}
	builder.append("]");
	return builder.toString();
    }    
}

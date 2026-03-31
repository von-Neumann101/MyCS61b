import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K ,V> {
/*我们对于方法的实现有两种选择
* 一种是每一个方法几乎都要写一个递归函数
* 一种是只写一个递归函数，返回key（如果有）对应的Node
* 前者性能更高，但是代码多
* 后者可以复用，但是性能略差（但是阶是一样的）*/
    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    Node root;
    int size;

    public BSTMap() {
        size = 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if(size == 0){//初次运行
            root = new Node(key, value);
            size = 1;
            return;
        }
        putHelper(key, value, root);
    }

    private void putHelper(K key, V value, Node position) {
        if(key.compareTo(position.key) < 0){
            if(position.left == null){
                position.left = new Node(key, value);
                size += 1;
                return;
            }
            putHelper(key, value, position.left);
        }else if(key.compareTo(position.key) > 0) {
            if (position.right == null) {
                position.right = new Node(key, value);
                size += 1;
                return;
            }
            putHelper(key, value, position.right);
        }else{
            position.value = value;
        }
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    private V getHelper(K key, Node position) {
        if(position == null)
            return null;

        if(key.compareTo(position.key) < 0)
            return getHelper(key, position.left);
        else if(key.compareTo(position.key) > 0)
            return getHelper(key, position.right);
        else
            return position.value;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return containsKeyHelper(key, root);
    }

    private boolean containsKeyHelper(K key, Node position) {
        if(position == null)
            return false;

        if(key.compareTo(position.key) < 0)
            return containsKeyHelper(key, position.left);
        else if(key.compareTo(position.key) > 0)
            return containsKeyHelper(key, position.right);
        else
            return true;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        return keySetHelper(root);
    }

    private Set<K> keySetHelper(Node position) {
        Set<K> result = new HashSet<>(size);
        if(isLeaf(position)) {
            Set<K> leafResult = new HashSet<>(1);
            leafResult.add(position.key);
            return leafResult;
        } else {
            if(position.right != null && position.left == null) {
                result.add(position.key);
                result.addAll(keySetHelper(position.right));
            }
            if(position.left != null && position.right == null) {
                result.add(position.key);
                result.addAll(keySetHelper(position.left));
            }
            if(position.left != null && position.right != null) {
                result.add(position.key);
                result.addAll(keySetHelper(position.left));
                result.addAll(keySetHelper(position.right));
            }
        }
        return result;
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private boolean isLeaf(Node node){
        return node.left == null && node.right == null;
    }
}

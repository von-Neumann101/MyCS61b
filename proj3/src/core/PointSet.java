package core;

import java.util.HashSet;
import java.util.Set;

public class PointSet {
    private final Set<Long> set = new HashSet<>();

    // ===== 编码 / 解码 =====
    private long encode(int x, int y) {
        return (((long) x) << 32) | (y & 0xffffffffL);
    }

    private int X(long key) {
        return (int) (key >> 32);
    }

    private int Y(long key) {
        return (int) key;
    }

    // ===== 基本操作 =====
    public void add(int x, int y) {
        set.add(encode(x, y));
    }

    public boolean contains(int x, int y) {
        return set.contains(encode(x, y));
    }

    public void remove(int x, int y) {
        set.remove(encode(x, y));
    }

    public int size() {
        return set.size();
    }

    public void clear() {
        set.clear();
    }

    // ===== 遍历 =====
    public interface PointConsumer {
        void accept(int x, int y);
    }

    public void forEach(PointConsumer consumer) {
        for (long key : set) {
            consumer.accept(X(key), Y(key));
        }
    }
}
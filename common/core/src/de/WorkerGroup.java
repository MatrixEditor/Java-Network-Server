package de;

import java.util.Collection;
import java.util.HashMap;

/**
 * commhawk in de.commhawk.cloud.server
 * <p>
 * Class description...
 *
 * @author Leonard,
 * @version ...
 * @date 04.11.2020
 **/
public class WorkerGroup<T extends AsyncRunner> {

    private static int state = 0;

    private transient final HashMap<Integer, T> OBJECTS = new HashMap<>();

    @SafeVarargs
    public WorkerGroup(T... objects) {
        addAll(objects);
        state = 1;
    }

    public WorkerGroup() {
        state = -1;
    }

    public T get(int i) throws NullPointerException {
        if (state > 0) return OBJECTS.get(i);
        else throw new NullPointerException();
    }

    public boolean stop(int i) {
        if (state > 0 && OBJECTS.containsKey(i)) {
            OBJECTS.remove(i);
            if (size() == 0) state = -1;
            return true;
        } else return false;
    }

    public void add(T object) {
        OBJECTS.put(size(), object);
        state = 1;
    }

    @SafeVarargs
    private void addAll(T... objects) {
        int k = 0;
        for (T obj : objects) {
            OBJECTS.put(k, obj);
            ++k;
        }
    }

    public Collection<T> workers() {
        return OBJECTS.values();
    }

    private int size() {
        return OBJECTS.size();
    }

    public String toString() {
        return "+--" + getClass().getSimpleName() + " " +
                "\n|\tGroup(size=" + size() +"), " +
                "\n|\tstate=" + state;
    }

}
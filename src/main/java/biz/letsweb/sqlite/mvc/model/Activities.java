 package biz.letsweb.sqlite.mvc.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Tomasz
 */
public class Activities <T> implements Iterable<T>{
    private final List<T> activities;

    public Activities() {
        this.activities = new ArrayList<>();
    }

    @Override
    public Iterator<T> iterator() {
        return activities.iterator();
    }

    public T get(int index) {
        return activities.get(index);
    }

    public int size() {
        return activities.size();
    }

    public boolean add(T activity) {
        return activities.add(activity);
    }

    public boolean remove(T stage) {
        return activities.remove(stage);
    }

    public List<T> getAsList() {
        return activities;
    }
    
}

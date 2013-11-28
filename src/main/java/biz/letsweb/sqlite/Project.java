package biz.letsweb.sqlite;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Project {

    private String name = "project";
    private boolean isFocused;
    private Task focusedTask;
    private Set<Task> availableTasks;

    public Project() {
        availableTasks = new HashSet<>();
        Task defaultFocusedTask = new Task();
        defaultFocusedTask.isFocused(true);
        availableTasks.add(defaultFocusedTask);
        focusedTask = defaultFocusedTask;
    }

    long getStartTimeMls() {
        return 0;
    }

    long getEndTimeMls() {
        return 0;
    }

    public String getName() {
        return name;
    }

    long calculateDuration(long startTime, long endTime) {
        return endTime - startTime;
    }

    List<Task> getAvailableTasks() {
        List<Task> tasksLocal = new ArrayList<>();
        tasksLocal.addAll(availableTasks);
        return tasksLocal;
    }

    void setAvailableTasks(Set<Task> availableTasks) {
        this.availableTasks = availableTasks;
    }

    String getDescription() {
        return "default project";
    }

    public void setName(String name) {
        this.name = name;
    }

    boolean isFocused() {
        return isFocused;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.name);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Project other = (Project) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    Task getFocusedTask() {
        return this.focusedTask;
    }

    void changeFocusedTask(Task taskToChangeTo) throws NotAgreedBehaviourException {
        for (Task t : availableTasks) {
            if (t.getName().equals(taskToChangeTo.getName())) {
                focusedTask = taskToChangeTo;
            } else {
                throw new NotAgreedBehaviourException("Cannot change to non-existing task.");
            }
        }
    }

    boolean addNewTask(Task newTask) {
        return availableTasks.add(newTask);
    }
}

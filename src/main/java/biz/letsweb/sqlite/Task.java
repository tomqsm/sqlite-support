package biz.letsweb.sqlite;

import java.util.Objects;

public class Task {

    private int id;
    private int projectId;
    private String name = "stopped";
    private boolean isFocused;
    private boolean isKeepingTime;
    private long startTime;
    private long stopTime;
    private String description;

    public Task withName(String name){
        setName(name);
        return this;
    }
    
    String getName() {
        return name;
    }

    String getDescription() {
        return "some task";
    }

    public long calculateDuration() {
        return stopTime - startTime;
    }

    boolean isFocused() {
        return this.isFocused;
    }

    public void isFocused(boolean b) {
        this.isFocused = b;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void isFocused(int b) {
        this.isFocused = b == 1 ? true : false;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public boolean isKeepingTime() {
        return this.isKeepingTime;
    }

    public void startKeepingTime() {
        this.startTime = System.currentTimeMillis();
        isKeepingTime = true;
    }

    public void stopKeepingTime() throws ApplicationException {
        if (this.isKeepingTime) {
            this.isKeepingTime = false;
            this.stopTime = System.currentTimeMillis();
        } else {
            throw new ApplicationException("Cannot stop keeping time when it hasn't been started beforehand.");
        }
    }

    long getStopTime() {
        return this.stopTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public void isKeepingTime(int aInt) {
        this.isKeepingTime = aInt == 1 ? true : false;
    }

    public void setStartTime(long aLong) {
        this.startTime = aLong;
    }

    public void setStopTime(long aLong) {
        this.stopTime = aLong;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.name);
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
        final Task other = (Task) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        String sep = "|";
        builder.append(projectId).append(sep).
                append(name).append(sep).
                append(isFocused).append(sep).
                append(isKeepingTime).append(sep).
                append(startTime).append(sep).
                append(stopTime).append(sep).
                append(description);
        return builder.toString();
    }
}

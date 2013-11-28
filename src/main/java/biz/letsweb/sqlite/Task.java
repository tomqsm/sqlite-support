package biz.letsweb.sqlite;

import java.util.Objects;

class Task {

    private boolean isFocused;
    private String name = "stopped";
    private boolean isKeepingTime;
    private long startTime;
    private long stopTime;

    String getName() {
        return name;
    }

    String getDescription() {
        return "some task";
    }

    long calculateDuration(long startTime, long endTime) {
        return endTime - startTime;
    }

    boolean isFocused() {
        return this.isFocused;
    }

    void setName(String name) {
        this.name = name;
    }

    void isFocused(boolean b) {
        this.isFocused = b;
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

    long getStartTime() {
        return this.startTime;
    }

    boolean isKeepingTime() {
        return this.isKeepingTime;
    }

    void startKeepingTime() {
        this.startTime = System.currentTimeMillis();
        isKeepingTime = true;
    }

    void stopKeepingTime() throws NotAgreedBehaviourException {
        if (this.isKeepingTime) {
            this.isKeepingTime = false;
            this.stopTime = System.currentTimeMillis();
        } else {
            throw new NotAgreedBehaviourException("Cannot stop keeping time when it hasn't been started beforehand.");
        }
    }

    long getStopTime() {
        return this.stopTime;
    }
}

package biz.letsweb.sqlite.mvc.model;

/**
 *
 * @author Tomasz
 */
public abstract class Activity {

    String name;
    String description;
    ChangeLog changeLog;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}

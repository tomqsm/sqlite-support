package biz.letsweb.sqlite.mvc.model;

/**
 *
 * @author Tomasz
 */
public abstract class Activity {

    int id;
    String name;
    String description;
    ChangeLog changeLog;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

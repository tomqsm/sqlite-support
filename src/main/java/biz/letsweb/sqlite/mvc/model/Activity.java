package biz.letsweb.sqlite.mvc.model;

import biz.letsweb.sqlite.dao.Types;

/**
 * 
 * @author Tomasz
 */
public class Activity {

  int id;
  String name;
  String description;
  String type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Activity{" + "id=" + id + ", name=" + name + ", description=" + description + ", type=" + type + ", changeLog=" + changeLog + '}';
    }

}

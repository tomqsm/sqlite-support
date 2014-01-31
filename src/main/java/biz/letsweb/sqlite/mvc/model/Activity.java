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
  Types type;
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

  public Types getType() {
    return type;
  }

  public void setType(int typeId) {
    this.type = Types.LOOKUP.get(typeId);
  }

}

package biz.letsweb.sqlite.dao;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author toks
 */
public enum Types {
  PROJECT(1), STAGE(2), STORY(3), TASK(4);
  private final int typeId;
  public static final Map<Integer, Types> LOOKUP;
  static {
    LOOKUP = new HashMap<>(4);
    for (Types t : Types.values()) {
      LOOKUP.put(t.getTypeId(), t);
    }
  }

  private Types(int typeId) {
    this.typeId = typeId;
  }

  public int getTypeId() {
    return typeId;
  }

}

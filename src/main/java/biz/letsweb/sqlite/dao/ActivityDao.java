package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.mvc.model.Activity;
import java.util.List;

/**
 * 
 * @author toks
 */
public interface ActivityDao extends Dao<Activity> {
  Activity findRecentByProjectId(int projectId);

  Activity findRecentSubActivity();

  Activity findRecentActivity();

  List<Activity> findRecentActivitiesTree();
  List<Activity> findRecents();
}

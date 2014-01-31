package biz.letsweb.sqlite.dao;

import biz.letsweb.sqlite.mvc.model.Activities;
import biz.letsweb.sqlite.mvc.model.Activity;

/**
 * 
 * @author toks
 */
public interface ActivityDao extends Dao<Activity> {
  Activity findRecentByProjectId(int projectId);

  Activity findRecentSubActivity();

  Activity findRecentActivity();

  Activities findRecentActivitiesTree();
}

package biz.letsweb.sqlite.mvc.view;

import biz.letsweb.sqlite.mvc.model.Activities;
import biz.letsweb.sqlite.mvc.model.Activity;

/**
 * 
 * @author toks
 */
public class Presenter {
  public String presentActivitiesTree(Activities<Activity> activities) {
    StringBuilder sb = new StringBuilder();
    for (int i = activities.size() - 1; i >= 0; i--) {
      sb.append(activities.get(i).getName()).append(" > ");
    }
    return sb.toString();
  }
}

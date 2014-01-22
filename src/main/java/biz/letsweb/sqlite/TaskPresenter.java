package biz.letsweb.sqlite;

import org.joda.time.Period;
import org.joda.time.format.PeriodFormatterBuilder;

public class TaskPresenter {

  public String displayDuration(Task task) {
    String result = null;
    final long duration = task.calculateDuration();
    Period period = new Period(duration);
    PeriodFormatterBuilder periodFormatter = new PeriodFormatterBuilder();
    periodFormatter.appendDays().appendSuffix(" dzień ", " dni ").appendHours()
        .appendSuffix(" godz. ").appendMinutes().appendSuffix(" min. ").appendSeconds()
        .appendSuffix(" sek. ");
    result = period.toString(periodFormatter.toFormatter());
    return result;
  }
}

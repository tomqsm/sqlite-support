package biz.letsweb.sqlite.mvc.model;

/**
 *
 * @author Tomasz
 */
public class Project extends Activity {

    private Stages stages;
    private Activities activities;

    public Project() {
        stages = new Stages();
    }

    public void setStages(Stages stages) {
        this.stages = stages;
    }

    public Stages getStages() {
        return stages;
    }

    Activities getActivities() {
        return activities;
    }
}

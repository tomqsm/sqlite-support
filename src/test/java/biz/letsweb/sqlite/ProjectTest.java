package biz.letsweb.sqlite;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

public class ProjectTest {

    private Project defaultProject;
    private Task defaultTask;

    public ProjectTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        defaultProject = new Project();
        defaultTask = new Task();
    }

    @After
    public void tearDown() {
    }
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void projectExists() throws Exception {
        assertNotNull(defaultProject);
    }

    @Test
    public void projectHasStartTimeMls() throws Exception {
        long startTime = defaultProject.getStartTimeMls();
        assertEquals(0, startTime);
    }

    @Test
    public void projectHasEndTimeMls() throws Exception {
        long endTime = defaultProject.getEndTimeMls();
        assertEquals(0, endTime);
    }

    @Test
    public void projectHasName() throws Exception {
        String projectName = defaultProject.getName();
        assertEquals("project", projectName);
    }

    @Test
    public void projectHasGotADescription() {
        String projectDescription = defaultProject.getDescription();
        assertEquals("default project", projectDescription);
    }

    @Test
    public void projectCanCalculateItsDuration() {
        long startTime = 1234567890;
        long endTime = 1234567895;
        long duration = defaultProject.calculateDuration(startTime, endTime);
        assertEquals(5, duration);
    }

    @Test
    public void projectHasGotSomeAvailableTasks() {
        Task task1 = new Task();
        task1.setName("task1");
        Task task2 = new Task();
        task2.setName("task2");
        Set<Task> exampleOfAvailableTasks = new HashSet<>(Arrays.asList(new Task[]{task1, task2}));
        defaultProject.setAvailableTasks(exampleOfAvailableTasks);
        List<Task> availableTasks = defaultProject.getAvailableTasks();
        assertNotNull(availableTasks);
        assertEquals(exampleOfAvailableTasks.size(), availableTasks.size());
    }

    @Test
    public void taskHasGotAName() {
        Task task1 = new Task();
        task1.setName("task1");
        Task task2 = new Task();
        task2.setName("task2");
        Set<Task> exampleOfAvailableTasks = new HashSet<>(Arrays.asList(new Task[]{task1, task2}));
        defaultProject.setAvailableTasks(exampleOfAvailableTasks);
        List<Task> availableTasks = defaultProject.getAvailableTasks();
        assertNotNull(availableTasks);
        assertTrue(availableTasks.get(0).getName().equals("task1") || availableTasks.get(0).getName().equals("task2"));
    }

    @Test
    public void taskHasGotADescription() {
        Task task = new Task();
        String taskDescription = task.getDescription();
        assertEquals("some task", taskDescription);
    }

    @Test
    public void taskCanSayItsDuration() {
        Task task = new Task();
        long startTime = 1234567890;
        long endTime = 1234567895;
        long taskDuration = task.calculateDuration(startTime, endTime);
        assertEquals(5, taskDuration);
    }

    @Test
    public void thereIsADataSourceHavingSomeProjects() {
        DataProvidable dataProvidable = new ProjectsProvider();
        List<Project> projects = dataProvidable.findAllProjects();
        assertNotNull(projects);
    }

    @Test
    public void projectsCanBeIdentifiedByTheirName() {
        DataProvidable dataProvidable = new ProjectsProvider();
        Project projectFound = dataProvidable.getProjectByName("default");
        assertNotNull(projectFound);
    }

    @Test
    public void oneProjectCanBeFocusedWhenCurrent() {
        boolean isFocused = defaultProject.isFocused();
        assertEquals(false, isFocused);
    }

    @Test
    public void cantBeMoreThanOneProjectFocused() {
        DataProvidable dataProvidable = new ProjectsProvider();
        Project project = dataProvidable.getFocusedProject();
        assertEquals(null, project);
    }

    @Test
    public void projectsAreEqualWhenTheirNamesAreTheSame() {
        Project project1 = new Project();
        project1.setName("same");
        Project project2 = new Project();
        project2.setName("same");
        assertEquals(true, project1.equals(project2));
    }

    @Test
    public void anewProjectCanBeAddedToCollection() {
        DataProvidable dataProvidable = new ProjectsProvider();
        Project newProject = new Project();
        boolean isAdded = dataProvidable.addNewProject(newProject);
        assertEquals(true, isAdded);
    }

    @Test
    public void noDuplicatesInProjectsCollection() {
        DataProvidable dataProvidable = new ProjectsProvider();
        Project newProject1 = new Project();
        Project newProject2 = new Project();
        boolean isAdded = dataProvidable.addNewProject(newProject1);
        assertEquals(true, isAdded);
        isAdded = dataProvidable.addNewProject(newProject2);
        assertEquals(false, isAdded);
    }

    @Test
    public void aTaskCanBeFocusedWhenRunning() {
        boolean isFocused = defaultTask.isFocused();
        assertEquals(false, isFocused);
    }

    @Test
    public void projectAlwaysHasOneFocusedTaskBreakIfProjectNotFocused() {
        Task focusedTask = defaultProject.getFocusedTask();
        assertNotNull(focusedTask);
        assertEquals("stopped", focusedTask.getName());
    }

    @Test
    public void projectCanChangeFocusedTask() throws NotAgreedBehaviourException {
        defaultProject.changeFocusedTask(defaultTask);
        assertEquals(defaultProject.getFocusedTask().getName(), defaultTask.getName());
    }

    @Test
    public void changingOfFocusedTaskCanHappenOnlyWithinAvailableTasks() throws NotAgreedBehaviourException {
        thrown.expect(NotAgreedBehaviourException.class);
        thrown.expectMessage("Cannot change to non-existing task.");
        Task taskToChangeTo = new Task();
        taskToChangeTo.setName("non existant name");
        defaultProject.changeFocusedTask(taskToChangeTo);
    }

    @Test
    public void itIsPossibleToAddTaskToProjectsAvailableTasks() {
        Task newTask = new Task();
        newTask.setName("documentation");
        boolean hasAdded = defaultProject.addNewTask(newTask);
        assertEquals(true, hasAdded);
    }

    @Test
    public void tasksAreEqualWhenTheirNamesAreSame() {
        Task task1 = new Task();
        Task task2 = new Task();
        assertEquals(task1, task2);
    }

    @Test
    public void youCannotAddDuplicatingTask() {
        final boolean hasAdded = defaultProject.addNewTask(defaultTask);
        assertEquals(false, hasAdded);
    }
    
    @Test
    public void taskHasStartTime(){
        long startTime = defaultTask.getStartTime();
        assertEquals(0, startTime);
    }
    
    @Test
    public void taskCanBeQueriedIfKeepsTime(){
        boolean isKeepingTime = defaultTask.isKeepingTime();
        assertEquals(false, isKeepingTime);
    }
    
    @Test
    public void taskCanStartKeepingTime(){
        defaultTask.startKeepingTime();
        assertEquals(true, defaultTask.isKeepingTime());
    }
    
    @Test
    public void taskCanStopKeepingTime() throws NotAgreedBehaviourException{
        defaultTask.startKeepingTime();
        assertEquals(true, defaultTask.isKeepingTime());
        defaultTask.stopKeepingTime();
        assertEquals(false, defaultTask.isKeepingTime());
    }
    
    @Test
    public void taskCanBeFocusedButNotKeepingTime(){
        defaultTask.isFocused(true);
        assertEquals(true, defaultTask.isFocused());
        assertEquals(false, defaultTask.isKeepingTime());
    }
    
    @Test
    public void whenTaskStartedKeepingTimeStartTimeIsNotDefault(){
        defaultTask.startKeepingTime();
        final long startTime = defaultTask.getStartTime();
        assertEquals(true, startTime > 0);
    }
    
    @Test
    public void whenTaskStoppedKeepingTimeStopTimeIsNotDefault() throws NotAgreedBehaviourException{
        defaultTask.startKeepingTime();
        defaultTask.stopKeepingTime();
        final long stopTime = defaultTask.getStopTime();
        assertEquals(true, stopTime > 0);
    }
    
    @Test
    public void impossibleToStopKeepingTimeIfNotStartedBeforehand() throws NotAgreedBehaviourException{
        thrown.expect(NotAgreedBehaviourException.class);
        thrown.expectMessage("Cannot stop keeping time when it hasn't been started beforehand.");
        defaultTask.stopKeepingTime();
    }
}
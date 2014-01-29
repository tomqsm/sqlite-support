DROP TABLE IF EXISTS activities_associations;
DROP TABLE IF EXISTS activities;
DROP TABLE IF EXISTS types;
DROP TABLE IF EXISTS current;

CREATE TABLE types (
id INTEGER PRIMARY KEY AUTOINCREMENT,
name VARCHAR(25)
);

CREATE TABLE activities (
id INTEGER PRIMARY KEY AUTOINCREMENT,
type_id INTEGER,
name VARCHAR(25),
FOREIGN KEY(type_id) REFERENCES types(id) ON DELETE NO ACTION
);

CREATE TABLE current (
project_id INTEGER,
FOREIGN KEY(project_id) REFERENCES activities(id)
);

INSERT INTO types VALUES (null, 'project');
INSERT INTO types VALUES (null, 'stage');
INSERT INTO types VALUES (null, 'story');
INSERT INTO types VALUES (null, 'task');

-- adding projects
INSERT INTO activities VALUES (null, 1, 'tomtom');
INSERT INTO activities VALUES (null, 1, 'letsweb');

-- adding stages
INSERT INTO activities VALUES (null, 2, 'documentation');
INSERT INTO activities VALUES (null, 2, 'implementation');
INSERT INTO activities VALUES (null, 2, 'design');
SELECT a.id FROM activities a WHERE a.type_id = (SELECT id FROM types WHERE name='stage');

-- adding stories
INSERT INTO activities VALUES (null, 3, 'story setting-up'); --6
INSERT INTO activities VALUES (null, 3, 'story fixing bug 123'); --7
INSERT INTO activities VALUES (null, 3, 'story fixing bug 456'); --8
SELECT a.id AS 'activity_id' FROM activities a WHERE a.type_id = (SELECT id FROM types WHERE name='story');

-- adding tasks
INSERT INTO activities VALUES (null, 4, 'task added try catch'); --9
INSERT INTO activities VALUES (null, 4, 'task replaced dependency'); --10
INSERT INTO activities VALUES (null, 4, 'task made a commit'); --11
SELECT a.id AS 'activity_id' FROM activities a WHERE a.type_id = (SELECT id FROM types WHERE name='task');


CREATE TABLE activities_associations (
id INTEGER PRIMARY KEY AUTOINCREMENT,
activity_id INTEGER,
sub_activity_id INTEGER,
FOREIGN KEY(activity_id) REFERENCES activities(id) ON DELETE CASCADE,
FOREIGN KEY(sub_activity_id) REFERENCES activities(id) ON DELETE CASCADE
);

-- associating project has stage
INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), (SELECT id FROM activities WHERE name='documentation'));
INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), (SELECT id FROM activities WHERE name='design'));
INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), (SELECT id FROM activities WHERE name='implementation'));
INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='letsweb'), (SELECT id FROM activities WHERE name='implementation'));

-- associating project has story
INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), 6);
INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), 7);
INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), 8);

-- associating story has task
INSERT INTO activities_associations VALUES (null, 6, 9);
INSERT INTO activities_associations VALUES (null, 6, 10);
INSERT INTO activities_associations VALUES (null, 6, 11);
INSERT INTO activities_associations VALUES (null, 7, 9);

-- associating story has stage
INSERT INTO activities_associations VALUES (null, 6, (SELECT id FROM activities WHERE name='documentation'));

-- find stages associated with tomtom
SELECT a.name FROM activities a JOIN activities_associations aa ON aa.sub_activity_id = a.id WHERE aa.sub_activity_id IN (SELECT ac.id FROM activities ac WHERE ac.type_id = (SELECT id FROM types WHERE name='stage')) AND aa.activity_id=(SELECT id FROM activities WHERE name='tomtom');

-- find stories associated with tomtom (story can belong to only one project)
SELECT a.id, a."name" FROM activities a JOIN activities_associations aa ON aa.sub_activity_id = a.id WHERE aa.sub_activity_id IN (SELECT a.id FROM activities a WHERE a.type_id = (SELECT id FROM types WHERE name='story')) AND aa.activity_id=(SELECT id FROM activities WHERE name='tomtom');

-- find tasks associated with story 'setting-up' (nr 6)
SELECT a.name FROM activities a JOIN activities_associations aa ON aa.sub_activity_id = a.id WHERE aa.sub_activity_id IN (SELECT a.id FROM activities a WHERE a.type_id IN (SELECT id FROM types WHERE name IN ('task','stage'))) AND aa.activity_id=6;

-- find tasks associated with story nr 6, last 3 of them
SELECT a.id, a.name FROM activities a JOIN activities_associations aa ON aa.sub_activity_id = a.id WHERE aa.sub_activity_id IN (SELECT a.id FROM activities a WHERE a.type_id = (SELECT id FROM types WHERE name='task')) AND aa.activity_id=6 ORDER BY a.id DESC LIMIT 3;

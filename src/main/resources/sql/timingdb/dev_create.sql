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
FOREIGN KEY(type_id) REFERENCES types(id)
);

CREATE TABLE current (
project_id INTEGER,
FOREIGN KEY(project_id) REFERENCES activities(id)
);

INSERT INTO types VALUES (null, 'project');
INSERT INTO types VALUES (null, 'stage');
INSERT INTO types VALUES (null, 'story');
INSERT INTO types VALUES (null, 'task');
--adding projects
INSERT INTO activities VALUES (null, 1, 'tomtom');
INSERT INTO activities VALUES (null, 1, 'letsweb');
--adding stages
INSERT INTO activities VALUES (null, 2, 'documentation');
INSERT INTO activities VALUES (null, 2, 'implementation');
INSERT INTO activities VALUES (null, 2, 'design');
--adding stories
INSERT INTO activities VALUES (null, 3, 'story setting-up');
INSERT INTO activities VALUES (null, 3, 'story fixing bug 123');
INSERT INTO activities VALUES (null, 3, 'story fixing bug 456');

CREATE TABLE activities_associations (
id INTEGER PRIMARY KEY AUTOINCREMENT,
activity_id INTEGER,
sub_activity_id INTEGER,
FOREIGN KEY(activity_id) REFERENCES activities(id)
FOREIGN KEY(sub_activity_id) REFERENCES activities(id)
);

-- associating stages with project
INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), (SELECT id FROM activities WHERE name='documentation'));
INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), (SELECT id FROM activities WHERE name='design'));
INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), (SELECT id FROM activities WHERE name='implementation'));
SELECT a.id FROM activities a WHERE a.type_id = (SELECT id FROM types WHERE name='stage');

-- associating stories with project
INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), (SELECT id FROM activities WHERE name='documentation'));
INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), (SELECT id FROM activities WHERE name='documentation'));

-- find stages associated with tomtom
SELECT a.name FROM activities a JOIN activities_associations aa ON aa.sub_activity_id = a.id WHERE aa.sub_activity_id IN (SELECT a.id FROM activities a WHERE a.type_id = (SELECT id FROM types WHERE name='stage'));
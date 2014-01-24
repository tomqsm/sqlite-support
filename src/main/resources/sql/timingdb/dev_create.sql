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

INSERT INTO activities VALUES (null, 1, 'tomtom');
INSERT INTO activities VALUES (null, 1, 'letsweb');
INSERT INTO activities VALUES (null, 2, 'documentation');
INSERT INTO activities VALUES (null, 2, 'implementation');
INSERT INTO activities VALUES (null, 2, 'design');

CREATE TABLE activities_associations (
id INTEGER PRIMARY KEY AUTOINCREMENT,
activity_id INTEGER,
sub_actibity_id INTEGER,
FOREIGN KEY(activity_id) REFERENCES activities(id)
FOREIGN KEY(sub_actibity_id) REFERENCES activities(id)
);

INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), (SELECT id FROM activities WHERE name='documentation'));
INSERT INTO activities_associations VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), (SELECT id FROM activities WHERE name='design'));
SELECT a.name FROM activities a WHERE a.type_id = (SELECT id FROM types WHERE name='stage');
SELECT a.name FROM activities a JOIN tactivities_associations aa ON aa.activity_id = (SELECT id FROM activities WHERE name='tomtom') WHERE aa.sub_activity_id=(SELECT id FROM activities WHERE name='tomtom');
-- INSERT INTO activities VALUES (null, (SELECT id FROM types WHERE name='stage'), 'documentation');
-- INSERT INTO activities VALUES (null, 1, 'letsweb');
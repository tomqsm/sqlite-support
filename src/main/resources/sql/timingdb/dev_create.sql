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

INSERT INTO types VALUES (null, 'project');
INSERT INTO types VALUES (null, 'stage');
INSERT INTO types VALUES (null, 'story');
INSERT INTO types VALUES (null, 'task');

INSERT INTO activities VALUES (null, 1, 'tomtom');
INSERT INTO activities VALUES (null, 1, 'letsweb');
INSERT INTO activities VALUES (null, 2, 'documentation');
INSERT INTO activities VALUES (null, 2, 'implementation');
INSERT INTO activities VALUES (null, 2, 'design');

CREATE TABLE activities_types (
id INTEGER PRIMARY KEY AUTOINCREMENT,
activity_id INTEGER,
type_id INTEGER,
FOREIGN KEY(activity_id) REFERENCES activities(id)
FOREIGN KEY(type_id) REFERENCES types(id)
);

INSERT INTO activities_types VALUES (null, (SELECT id FROM activities WHERE name='tomtom'), 2);
INSERT INTO activities_types VALUES (null, (SELECT id FROM activities WHERE name='letsweb'), 2);
-- INSERT INTO activities_types VALUES (null, 1, 3);
-- INSERT INTO activities_types VALUES (null, 1, 4);

-- SELECT a.name FROM activities a JOIN types t ON a.type_id=t.id WHERE t.id=(SELECT id FROM types WHERE name='project') AND a.name='letsweb';
-- INSERT INTO activities VALUES (null, (SELECT id FROM types WHERE name='stage'), 'documentation');
-- INSERT INTO activities VALUES (null, 1, 'letsweb');
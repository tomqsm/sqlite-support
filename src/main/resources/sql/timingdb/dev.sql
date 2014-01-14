DROP TABLE IF EXISTS activities;
DROP TABLE IF EXISTS types;

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
INSERT INTO activities VALUES (null, 2, 'documentation');

-- SELECT a.name FROM activities a JOIN types t ON a.type_id=t.id WHERE t.id=(SELECT id FROM types WHERE name='project') AND a.name='letsweb';
-- INSERT INTO activities VALUES (null, (SELECT id FROM types WHERE name='stage'), 'documentation');
-- INSERT INTO activities VALUES (null, 1, 'letsweb');
DROP TABLE IF EXISTS tasks;
DROP TABLE IF EXISTS projects;


CREATE TABLE projects (
id INTEGER PRIMARY KEY AUTOINCREMENT, 
name VARCHAR(25),
is_focused INTEGER,
start_time INTEGER,
stop_time INTEGER,
created INTEGER,
description TEXT
);
CREATE TABLE tasks (
id INTEGER PRIMARY KEY AUTOINCREMENT,
project_id INTEGER,
name VARCHAR(13),
is_focused INTEGER,
is_keeping_time INTEGER,
start_time INTEGER,
stop_time INTEGER,
description TEXT,
FOREIGN KEY(project_id) REFERENCES projects(id)
);
INSERT INTO projects VALUES (null, 'projekt1', 0, 0, 0, 0, 'projekt pierwszy');
INSERT INTO tasks VALUES (null, 1, 'requirements', 0, 1, 0, 0, 'requirements capturing');
INSERT INTO tasks VALUES (null, 1, 'analysis', 0, 1, 0, 0, 'analysis phase');
INSERT INTO tasks VALUES (null, 1, 'design', 0, 1, 0, 0, 'design phase');
INSERT INTO tasks VALUES (null, 1, 'implementation', 0, 1, 0, 0, 'implementation phase');
INSERT INTO tasks VALUES (null, 1, 'testing', 0, 1, 0, 0, 'testing phase');
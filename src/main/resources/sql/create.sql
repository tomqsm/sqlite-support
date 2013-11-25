DROP TABLE IF EXISTS works;
DROP TABLE IF EXISTS activities;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS datetest;

CREATE TABLE projects (
id INTEGER PRIMARY KEY AUTOINCREMENT, 
name VARCHAR(25),
desc TEXT
);
CREATE TABLE activities (
id INTEGER PRIMARY KEY NOT NULL, 
name VARCHAR(13),
desc TEXT
);
CREATE TABLE works (
id INTEGER PRIMARY KEY AUTOINCREMENT, 
change_time DATE, 
id_activity INTEGER, 
id_project INTEGER, 
FOREIGN KEY(id_project) REFERENCES projects(id), 
FOREIGN KEY(id_activity) REFERENCES activities(id)
);
CREATE TABLE datetest (
id INTEGER PRIMARY KEY AUTOINCREMENT, 
change_time DATE,
last_modified DATETIME
);
INSERT INTO datetest VALUES (null, "2013-11-19 00:18", datetime('now'));
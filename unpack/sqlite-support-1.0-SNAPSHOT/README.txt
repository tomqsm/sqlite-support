ARTEFACT: sqlite-support-1.0-SNAPSHOT
BUILD DATE: 27/01/2014 11:56

SQLITE notes:

sqlite> .header on
sqlite> .mode column
sqlite> select * from datetest;
id          change_time       last_modified
----------  ----------------  -------------------
1           2013-11-19 00:18  2013-11-20 00:39:15

sqlite> .schema datetest
CREATE TABLE datetest (
id INTEGER PRIMARY KEY AUTOINCREMENT,
change_time DATE,
last_modified DATETIME
);
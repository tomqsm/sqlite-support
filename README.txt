ARTEFACT: ${project.build.finalName}
BUILD DATE: ${date}

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

An activity has two hands and can bound with two other activities. Many-to-many.
activity1 -- activity2 -- activity3 -- activity4
rootTask                                leafTask

activity1   --  activity2   --  activity4
            --  activity3   --  activity5
            --  activity6

activity8   --  activity2   --  activity7   --  activity9

Go from leaftask to rootTask ?
activity9   --  activity7   --  activity2   --  activity1
                                            --  activity8

select 

It is possible to traverse the chain from leaf to trunk.

There are tasks which are cross-cutting concerns, cutting cross-wide this organisation.
They are called 'stage'.
Any leaf can have a stage e.g. pause, documentation. Stage is also a task but it is predfined.
It is also shared between all projects of the same nature. As such when a stage is set on a leaf task,
it disables the traversal downwards to trunk. In those cases the 'before-leaf' can be use as start
of traversal.
Stage cannot have a sub task, it can only be the leaf.

Stage może być leaf ale nie wiesz jaki jest projekt idąc od stage, bo stage ma kilka projektów.
Nie da się jednoznacznie wybrać super task (branch) od stage bo jest wiele asocjacji z stage jako leaf.
Ten sam leaf wisi na dwóch branch.

Jednoznaczność można osiągnąć poprzez:
1) pobranie jednego rekordu spośród wielu mających ten sam leaf np ostatniego w asocjacji.
Złe rozwiązanie, asocjacja może być wielokrotnie używana, czyli nie bedzie zawsze na końcu tabeli jako
bieżąca.
2) id asocjacji z tabeli historii, które jest ostatnie wskaże ostatnią 
jak rozpoznać po tym ostatni wpis dla projektu B w odróżnieniu od projektu A ?
-->Czyli w tabeli historii powienien być projectId albo storyId.
    Chyba lepiej poprostu super task id.
    Or maybe a reference to activities_associations for fixing the last record.
    Having the last record we can traverse back to project even if the last task
the leaf task was a stage.

FindRecentByProject - hard without a project reference in history table.



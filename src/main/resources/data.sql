


insert into teacher (name, object)
values ( 'Malahov',' Mathematics'),
       ('Gubin', 'History'),
       ('Petrov', 'Chemistry'),
       ('Sidorov','Economy'),
       ('Drakin','Philosofy');



insert into groups (number,teacher_id) values (793,1),
                                              (792,2),
                                              (791,3);
insert into student (date_of_birth, name, groups_id)
values ('4 March 1992', 'Anton',1),
       ('30 November', 'Zagir',2),
       ('20 December 1992',' Sasha',3),
       ('4 March', 'Nastya',3),
       ('25 November', 'Alex',2);

insert into rating (value, students_id, teacher_id) VALUES (5,1,1),
                                                           (4,2,2),
                                                           (3,3,3),
                                                           (3.5,4,3),
                                                           (2.5,5,2);
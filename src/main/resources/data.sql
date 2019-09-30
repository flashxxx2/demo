     insert into students (id, date_of_birth, name, groups_id, students_id)
values (3,'4 March 1992', 'Anton',1,1),
(2,'30 November', 'Zagir',2,2),
(1,'20 December 1992',' Sasha',3,3),
(4,'4 March', 'Nastya',3,4),
(5,'25 November', 'Alex',2,5);

insert into teacher (id, name, object, groups_id)
values (1, 'Malahov',' Mathematics', 3),
(2, 'Gubin', 'History',2),
(3,'Petrov', 'Chemistry',1);


insert into groups (id, number, subject) values (1,793, 'Mathematics'),
(2,792,' History'),
(3,791, 'Chemistry');


insert into rating (id, "value", students_id, teacher_id) VALUES (1,5,1,1),
(2,4,2,2),
(3,3,3,3),
(4,3.5,4,3),
(5,2.5,5,2);


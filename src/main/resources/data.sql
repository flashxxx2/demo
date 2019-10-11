
insert into groups (number, subject) values (793, 'Mathematics'),
                                                (792,' History'),
                                                (791, 'Chemistry');


insert into teacher (name, object, groups_id)
values ( 'Malahov',' Mathematics', 3),
       ('Gubin', 'History',2),
       ('Petrov', 'Chemistry',1),
        ('Sidorov','Economy',3),
        ('Drakin','Philosofy',1);

insert into students (date_of_birth, name, groups_id, students_id)
values ('4 March 1992', 'Anton',1,1),
('30 November', 'Zagir',2,2),
('20 December 1992',' Sasha',3,3),
('4 March', 'Nastya',1,4),
('25 November', 'Alex',2,5);


insert into rating (value, students_id, teacher_id) VALUES (5,1,1),
                                                           (4,2,2),
                                                           (3,3,3),
                                                           (3.5,4,3),
                                                           (2.5,5,2);


insert into subject (subject_name)
values ('History'),
       ('Mathematics'),
       ('Chemistry'),
       ('Philosofy'),
       ('Economics'),
       ('Phisics'),
       ('Ingeneria'),
       ('Biology'),
       ('Geography'),
       ('English'),
       ('Geometry'),
       ('Gym'),
       ('Literature'),
       ('Music'),
       ('French'),
       ('Botany'),
       ('Health');
insert into groups (number)
values (793),
       (792),
       (791);
insert into teacher (name, subject_id, group_id)
values ('Malahov', 1,1),
       ('Gubin', 2,2),
       ('Petrov', 3,3),
       ('Sidorov', 4,1),
       ('Drakin', 5,2);

insert into student (date_of_birth, name, group_id)
values ('4 March 1992', 'Anton', 1),
       ('30 November', 'Zagir', 2),
       ('20 December 1992', ' Sasha', 3),
       ('4 March', 'Nastya', 3),
       ('25 November', 'Alex', 2);



insert into rating (value, student_id, subject_id)
VALUES (5, 1, 1),
       (4, 2, 1),
       (3, 3, 2),
       (3, 4, 3),
       (2, 5, 2),
       (3, 1, 3),
       (4, 1, 4),
       (1, 2, 1),
       (2, 2, 4),
       (6, 3, 1),
       (5, 3, 5),
       (7, 4, 2),
       (9, 4, 9),
       (4, 5, 7),
       (8, 5, 6);

insert into group_subject (group_id, subject_id)
values (1, 2),
       (1, 4),
       (1, 3),
       (2, 2),
       (2, 3),
       (2, 5),
       (3, 6),
       (3, 7),
       (3, 2);

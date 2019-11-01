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

insert into teacher (name, subject_id)
values ('Malahov', 1),
       ('Gubin', 2),
       ('Petrov', 3),
       ('Sidorov', 4),
       ('Drakin', 5);



insert into groups (number, subject_id)
values (793, 1),
       (792, 2),
       (791, 3);
insert into student (date_of_birth, name, groups_id)
values ('4 March 1992', 'Anton', 1),
       ('30 November', 'Zagir', 2),
       ('20 December 1992', ' Sasha', 3),
       ('4 March', 'Nastya', 3),
       ('25 November', 'Alex', 2);



insert into rating (value, students_id, subject_id)
VALUES (5, 1, 1),
       (4, 2, 1),
       (3, 3, 2),
       (3, 4, 3),
       (2, 5, 2),
    (3,1,3),(4,1,4),(1,2,1),(2,2,4),(6,3,1),(5,3,5),(7,4,2),(9,4,9),(4,5,7),(8,5,6);

insert into users(id, username, password, name, surname, phone, birthday, role, activated) values
(1, 'admin1@yahoo.com', '123', 'Admin1', 'Admin1', '061-543-543', '1998-01-01', 'ADMINISTRATOR', 'true'),
(2, 'admin2@yahoo.com', '123', 'Admin2', 'Admin2', '062-531-546', '1997-02-02', 'ADMINISTRATOR', 'true'),
(3, 'manager1@yahoo.com', '123', 'Manager1', 'Manager1', '063-765-432', '1996-03-03', 'MANAGER', 'true'),
(4, 'manager2@yahoo.com', '123', 'Manager2', 'Manager2', '064-643-785', '1995-04-04', 'MANAGER', 'true'),
(5, 'viewer1@yahoo.com', '123', 'Viewer1', 'Viewer1', '065-742-923', '1994-05-05', 'VIEWER', 'true'),
(6, 'viewer2@yahoo.com', '123', 'Viewer2', 'Viewer2', '066-182-934', '1993-06-06', 'VIEWER', 'true');

insert into cinemas(id, name, address, email, phone, deleted) values
(1, 'Cinema1', 'Address1', 'cinema1@yahoo.com', '064-346-443', 'false'),
(2, 'Cinema2', 'Address2', 'cinema2@yahoo.com', '063-765-743', 'false'),
(3, 'Cinema3', 'Address3', 'cinema3@yahoo.com', '062-600-743', 'false');

insert into cinema_manager(cinema_id, manager_id) values
(1, 3),
(2, 3),
(3, 3),
(3, 4);

insert into theaters(id, name, seats, deleted, cinema_id) values
(1, 'Theater1', 50, 'false', 1),
(2, 'Theater1', 20, 'false', 2),
(3, 'Theater1', 20, 'false', 3),
(4, 'Theater2', 40, 'false', 1),
(5, 'Theater3', 10, 'false', 1),
(6, 'Theater2', 50, 'false', 2);

insert into films(id, name, description, duration, genre, deleted) values
(1, 'Ko to tamo peva', 'Opis1', 60, 'KOMEDIJA', 'false'),
(2, 'Kad porastem bicu kengur', 'Opis2', 90, 'KOMEDIJA', 'false'),
(3, 'Juzni vetar', 'Opis3', 120, 'AKCIJA', 'false'),
(4, 'Leptirica', 'Opis4', 60, 'HOROR', 'false'),
(5, 'Klopka', 'Opis5', 120, 'TRILER', 'false'),
(6, 'Oteta', 'Opis6', 120, 'TRILER', 'false'),
(7, 'Taxi', 'Opis7', 90, 'AKCIJA', 'false'),
(8, 'Terminator', 'Opis8', 120, 'NAUCNA FANTASTIKA', 'false');

insert into projections(id, time, free_seats, price, theater_id, film_id, deleted) values
--2020-09-01 theater1 cinema1
(1, '2020-09-01T10:00:00', 50, 150, 1, 1, 'false'),
(2, '2020-09-01T11:30:00', 50, 200, 1, 1, 'false'),
(3, '2020-09-01T13:00:00', 50, 250, 1, 2, 'false'),
(4, '2020-09-01T15:00:00', 50, 250, 1, 2, 'false'),
(5, '2020-09-01T17:00:00', 50, 300, 1, 2, 'false'),
(6, '2020-09-01T19:00:00', 50, 350, 1, 3, 'false'),
(7, '2020-09-01T21:30:00', 50, 400, 1, 3, 'false'),
--2020-09-01 theater2 cinema1
(8, '2020-09-01T13:30:00', 40, 200, 4, 4, 'false'),
(9, '2020-09-01T15:00:00', 40, 200, 4, 4, 'false'),
(10, '2020-09-01T16:30:00', 40, 250, 4, 4, 'false'),
(11, '2020-09-01T18:00:00', 40, 300, 4, 7, 'false'),
(12, '2020-09-01T20:00:00', 40, 350, 4, 8, 'false'),
--2020-09-01 theater3 cinema1
(13, '2020-09-01T16:30:00', 10, 250, 5, 5, 'false'),
(14, '2020-09-01T19:00:00', 10, 300, 5, 5, 'false'),
(15, '2020-09-01T21:30:00', 10, 300, 5, 4, 'false'),
--2020-09-01 theater1 cinema2
(16, '2020-09-01T14:30:00', 20, 250, 2, 4, 'false'),
(17, '2020-09-01T16:00:00', 20, 250, 2, 4, 'false'),
(18, '2020-09-01T17:30:00', 20, 300, 2, 5, 'false'),
(19, '2020-09-01T20:00:00', 20, 350, 2, 5, 'false'),
--2020-09-01 theater2 cinema2
(20, '2020-09-01T16:00:00', 50, 200, 6, 7, 'false'),
(21, '2020-09-01T18:00:00', 50, 200, 6, 8, 'false'),
(22, '2020-09-01T21:30:00', 50, 200, 6, 7, 'false'),
--2020-09-01 theater1 cinema3
(23, '2020-09-01T13:00:00', 20, 300, 3, 7, 'false'),
(24, '2020-09-01T15:00:00', 20, 350, 3, 8, 'false'),
(25, '2020-09-01T17:30:00', 20, 400, 3, 3, 'false'),
(26, '2020-09-01T20:30:00', 20, 450, 3, 3, 'false'),
--2020-09-02 theater1 cinema1
(27, '2020-09-02T10:00:00', 50, 150, 1, 1, 'false'),
(28, '2020-09-02T11:30:00', 50, 200, 1, 1, 'false'),
(29, '2020-09-02T13:00:00', 50, 250, 1, 2, 'false'),
(30, '2020-09-02T15:00:00', 50, 250, 1, 2, 'false'),
(31, '2020-09-02T17:00:00', 50, 300, 1, 2, 'false'),
(32, '2020-09-02T19:00:00', 50, 350, 1, 3, 'false'),
(33, '2020-09-02T21:30:00', 50, 400, 1, 3, 'false'),
--2020-09-02 theater2 cinema1
(34, '2020-09-02T13:30:00', 40, 200, 4, 4, 'false'),
(35, '2020-09-02T15:00:00', 40, 200, 4, 4, 'false'),
(36, '2020-09-02T16:30:00', 40, 250, 4, 4, 'false'),
(38, '2020-09-02T18:00:00', 40, 300, 4, 7, 'false'),
(39, '2020-09-02T20:00:00', 40, 350, 4, 8, 'false'),
--2020-09-02 theater3 cinema1
(40, '2020-09-02T16:30:00', 10, 250, 5, 5, 'false'),
(41, '2020-09-02T19:00:00', 10, 300, 5, 5, 'false'),
(42, '2020-09-02T21:30:00', 10, 300, 5, 4, 'false'),
--2020-09-02 theater1 cinema2
(43, '2020-09-02T14:30:00', 20, 250, 2, 4, 'false'),
(44, '2020-09-02T16:00:00', 20, 250, 2, 4, 'false'),
(45, '2020-09-02T17:30:00', 20, 300, 2, 5, 'false'),
(46, '2020-09-02T20:00:00', 20, 350, 2, 5, 'false'),
--2020-09-02 theater2 cinema2
(47, '2020-09-02T16:00:00', 50, 200, 6, 7, 'false'),
(48, '2020-09-02T18:00:00', 50, 200, 6, 8, 'false'),
(49, '2020-09-02T21:30:00', 50, 200, 6, 7, 'false'),
--2020-09-02 theater1 cinema3
(50, '2020-09-02T13:00:00', 20, 300, 3, 7, 'false'),
(51, '2020-09-02T15:00:00', 20, 350, 3, 8, 'false'),
(52, '2020-09-02T17:30:00', 20, 400, 3, 3, 'false'),
(53, '2020-09-02T20:30:00', 20, 450, 3, 3, 'false');

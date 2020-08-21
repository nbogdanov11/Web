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
(1, 'Theater1', 10, 'false', 1),
(2, 'Theater1', 20, 'false', 2),
(3, 'Theater1', 20, 'false', 3),
(4, 'Theater2', 40, 'false', 1),
(5, 'Theater3', 60, 'false', 1),
(6, 'Theater2', 50, 'false', 2);

insert into films(id, name, description, duration, genre, deleted) values
(1, 'Ko to tamo peva', 'Opis1', 80, 'KOMEDIJA', 'false'),
(2, 'Kad porastem bicu kengur', 'Opis2', 90, 'KOMEDIJA', 'false'),
(3, 'Juzni vetar', 'Opis3', 100, 'AKCIJA', 'false'),
(4, 'Leptirica', 'Opis4', 70, 'HOROR', 'false'),
(5, 'Klopka', 'Opis5', 90, 'TRILER', 'false'),
(6, 'Klopka', 'Opis6', 100, 'TRILER', 'false'),
(7, 'Taxi', 'Opis7', 110, 'AKCIJA', 'false'),
(8, 'Terminator', 'Opis8', 120, 'NAUCNA FANTASTIKA', 'false');

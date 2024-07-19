insert into users (dob, email,phone, username, password, surname, name, patronymic, med_spec_id, med_pos_id, role_id, med_org_id) VALUES
('12-08-2000', 'test@email.ru', '8-922-111-11-11', 'testUser1', '1234', 'Ivanov', 'Ivan', 'Ivanovich' , 1, 2, 1, 194),
('12-08-2000', 'test@email.ru', '8-922-112-13-14', 'testUser2', '1234', 'Petrov', 'Pyotr', 'Petrovich' , 2, 3, 2, 195),
('12-08-2000', 'test@email.ru', '8-922-151-61-71', 'testUser3', '1234', 'Hellish', 'Pier', 'Ivanovich' , 3, 4, 2, 202),
('12-08-2000', 'test@email.ru', '8-922-563-32-91', 'testUser4', '1234', 'Dostojevskaya', 'Sveta', 'Ivanovich' , 1, 15, 2, 208),
('12-08-2000', 'test@email.ru', '8-922-341-09-22', 'testUser5', '1234', 'Armada', 'Aur', 'Ivanovich' , 3, 23, 2, 128),
('12-08-2000', 'test@email.ru', '8-922-151-13-12', 'testUser6', '1234', 'Kremzin', 'Kazatot', 'Ivanovich' , 3, 26, 2, 126),
('12-08-2000', 'test@email.ru', '8-922-851-91-17', 'testUser7', '1234', 'Adovich', 'Azzory', 'Ivanovich' , 2, 30, 2, 231),
('12-08-2000', 'test@email.ru', '8-922-111-11-11', 'testUser8', '1234', 'Mozhegov', 'Ursa', 'Ivanovich' , 1, 11, 2, 251),
('12-08-2000', 'test@email.ru', '8-922-111-11-11', 'testUser9', '1234', 'Isertobi', 'Azrael', 'Ivanovich' , null, null, 2, null),
('12-08-2000', 'test@email.ru', '8-922-111-11-11', 'testUser10', '1234', 'Jotun', 'Jerry', 'Ivanovich' , null, null, 2, null),
('12-08-2000', 'test@email.ru', '8-922-111-11-11', 'testUser11', '1234', 'Smidt', 'John', 'Ivanovich' , null, null, 2, null),
('12-08-2000', 'test@email.ru', '8-922-111-11-11', 'testUser12', '1234', 'Rolu', 'Maria', 'Ivanovich' , null, null, 2, null),
('12-08-2000', 'test@email.ru', '8-922-111-11-11', 'testUser13', '1234', 'Remi', 'Soffia', 'Ivanovich' , null, null, 2, null),
('12-08-2000', 'test@email.ru', '8-922-111-11-11', 'testUser14', '1234', 'Allory', 'Sanny', 'Ivanovich' , null, null, 2, null),
('12-08-2000', 'test@email.ru', '8-922-111-11-11', 'testUser15', '1234', 'Ivanov', 'Alex', 'Ivanovich' , null, null, 2, null);

insert into employees_types (employee_id, employee_type_id) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 1),
(6, 2),
(7, 3),
(8, 4),
(9, 1),
(10, 2),
(11, 3),
(12, 4),
(13, 1),
(14, 2),
(15, 3),
(13, 2),
(3, 4),
(12, 3),
(2, 4),
(8, 1);

insert into medical_specialities (id, name) VALUES
(1, 'Butchery'),
(2, 'Surgery'),
(3, 'Urology');

insert into events (start_date, end_date, medical_speciality_id, event_type_id) values
('2024-07-08', '2024-07-16', 3, 1),
('2024-07-10', '2024-07-25', 2, 2),
('2024-07-05', '2024-08-01', 1, 3);

insert into schedule_slots (dop, start_time, end_time,  draft, event_id, event_stage_id) VALUES
('2024-07-12', '08:00:00', '09:00:00', false, 2, 4),
('2024-07-12', '09:30:00', '10:30:00', false, 2, 5);

insert into employees_without_locations (employee_id, schedule_slot_id) values
(4, 1),
(4, 2);

insert into slot_locations (location_id, schedule_slot_id) VALUES
(7, 1),
(3, 2),
(4, 2),
(5, 2),
(6, 2);

insert into employee_locations (employee_id, slot_location_id) VALUES
(3, 1),
(3, 2),
(2, 2),
(3, 3),
(6, 3),
(3, 4),
(10, 4),
(3, 5),
(14, 5);

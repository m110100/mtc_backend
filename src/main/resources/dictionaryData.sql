insert into roles (id, name) VALUES
(default, 'мед. работник'),
(default, 'специалист УЦ');
insert into event_types (id, acronym, name) VALUES
(1, 'ПА', 'Первичная аккредитация'),
(2, 'ПК', 'Повышение квалификации'),
(3, 'ДПО', 'Дополнительное образование');
insert into event_stage_categories (id, name) VALUES
(1, 'Обучение'),
(2, 'Оценочные мероприятия');
insert into event_stages (id, name, event_stage_category_id, event_type_id) VALUES
(1, 'Тестирование', 2, 1),
(2, 'Оценка практических навыков', 2, 1),
(3, 'Собеседование', 2, 1),
(4, 'Лекция', 1, 2),
(5, 'Практическое занятие', 1, 2),
(6, 'Тестирование', 2, 2),
(7, 'Лекция', 1, 3),
(8, 'Практическое занятие', 1, 3),
(9, 'Тестирование', 2, 3);
insert into event_stage_restrictions (duration, max_per_month, max_per_week, event_stage_id) VALUES
(120, 1, 1, 1),
(0, 0, 0, 2),
(0, 0, 0, 3),
(60, 10, 4, 4),
(60, 12, 4, 5),
(40, 1, 1, 6),
(90, 16, 6, 7),
(120, 18, 8, 8),
(60, 1, 1, 9);
insert into employee_types (id, name) VALUES
(1, 'Экзаменатор'),
(2, 'Инженер'),
(3, 'Специалист УЦ'),
(4, 'Куратор');
insert into location_types (id, name) VALUES
(1, 'Компьютерный зал'),
(2, 'Аудитория-станция'),
(3, 'Лекционное помещение');
insert into locations (id, available_seats, number, location_type_id) VALUES
(default, 40, 101, 1),
(default, 40, 102, 1),
(default, 40, 103, 2),
(default, 40, 104, 2),
(default, 40, 201, 2),
(default, 40, 202, 2),
(default, 40, 203, 3),
(default, 40, 204, 3);
insert into event_stage_location_restrictions (id, location_number, event_stage_id, location_type_id) values
(1, 1, 1, 1),
(2, 4, 2, 2),
(3, 1, 3, 1),
(4, 1, 3, 3),
(5, 1, 4, 3),
(6, 4, 5, 2),
(7, 1, 6, 1),
(8, 1, 7, 3),
(9, 4, 8, 2),
(10, 1, 9, 1);
insert into location_employee_type_restrictions (employee_number, employee_type_id, location_restriction_id) VALUES
(1, 1, 1),
(1, 2, 1),
(1, 1, 2),
(1, 2, 2),
(2, 3, 3),
(2, 3, 4),
(1, 3, 5),
(1, 2, 6),
(1, 3, 6),
(1, 1, 7),
(1, 2, 7),
(1, 3, 8),
(1, 2, 9),
(1, 3, 9),
(1, 1, 10),
(1, 2, 10);

-- Создание таблиц
CREATE TABLE `films` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(255) NOT NULL,
  `duration` INT NOT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `sessions` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `show_date` DATE NOT NULL,
  `show_time` TIME NOT NULL,
  `film_id` BIGINT NOT NULL,
  `price` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`film_id`) REFERENCES `films`(`id`)
);

CREATE TABLE `tickets` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `session_id` BIGINT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`session_id`) REFERENCES `sessions`(`id`)
);

-- Наполнение таблиц данными
INSERT INTO `films` (title, duration) VALUES
('Фильм 1', '60'),
('Фильм 2', '90'),
('Фильм 3', '60'),
('Фильм 4', '90'),
('Фильм 5', '120');

INSERT INTO `sessions` (show_date, show_time, film_id, price) VALUES
('2021-02-11', '9:00', 1, 200),
('2021-02-11', '10:15', 3, 250),
('2021-02-11', '11:30', 2, 300),
('2021-02-11', '13:30', 5, 400),
('2021-02-11', '15:00', 4, 350),
('2021-02-11', '17:00', 5, 450),
('2021-02-11', '19:40', 3, 450),
('2021-02-11', '20:50', 1, 450),
('2021-02-11', '22:00', 5, 500);

INSERT INTO `tickets` (session_id) VALUES
(1),
(1),
(1),
(2),
(2),
(2),
(3),
(3),
(4),
(4),
(4),
(5),
(6),
(6),
(7),
(7),
(8),
(9),
(9),
(9);

/* Задача 1
 Определение наложения фильмов
*/
SELECT f1.title as 'фильм 1', s1.show_time as 'время начала', f1.duration as 'длительность', f2.title as 'фильм 2', s2.show_time as 'время начала', f2.duration as 'длительность'
FROM sessions as s1 INNER JOIN films as f1 ON s1.film_id = f1.id
INNER JOIN sessions as s2 ON s2.id = s1.id + 1
INNER JOIN films as f2 ON f2.id = s2.film_id
WHERE TIMEDIFF(s2.show_time, (s1.show_time + INTERVAL f1.duration MINUTE)) < '00:00:00'
ORDER BY s1.show_time;

/* Задача 2
 Определение перерывов между фильмами более 30 минут
*/
SELECT f1.title as 'фильм 1', s1.show_time as 'время начала', f1.duration as 'длительность', s2.show_time as 'время начала второго фильма',
TIMEDIFF(s2.show_time, (s1.show_time + INTERVAL f1.duration MINUTE)) as 'длительность перерыва'
FROM sessions as s1 INNER JOIN films as f1 ON s1.film_id = f1.id
INNER JOIN sessions as s2 ON s2.id = s1.id + 1
INNER JOIN films as f2 ON f2.id = s2.film_id
WHERE TIMEDIFF(s2.show_time, (s1.show_time + INTERVAL f1.duration MINUTE)) >= '00:30:00'
ORDER BY TIMEDIFF(s2.show_time, (s1.show_time + INTERVAL f1.duration MINUTE)) DESC;

/* Задача 3
 Список фильмов с поситетелями, сборами и итогом. НЕ СДЕЛАЛ СРЕДНЕЕ КОЛИЧЕСТВО ПОСЕТИТЕЛЕЙ ЗА СЕАНС
*/
SELECT IF(GROUPING(f.title), 'Итого', f.title) as 'фильм', count(t.id) as 'всего зрителей', sum(s.price) as 'сборы'
FROM sessions s INNER JOIN films f ON s.film_id = f.id INNER JOIN tickets t ON s.id = t.session_id
GROUP BY f.title WITH ROLLUP;

/* Задача 4
 До конца не понятно условияе. Сделал вывод посетителей и сборы по фильмам в период с 9 до 15 часов...
*/
SELECT s.show_time as 'начало фильма', count(t.id) as 'посетители', sum(s.price) as 'сборы' FROM sessions s INNER JOIN tickets t ON s.id = t.session_id
GROUP BY s.show_time
HAVING s.show_time BETWEEN '09:00:00' AND '15:00:00'
ORDER BY s.show_time;
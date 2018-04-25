DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM dishes;
DELETE FROM votes;
DELETE FROM menu_dish_link;


ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('Admin', 'admin@gmail.com', 'admin'),
  ('User', 'user@yandex.ru', 'user');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (id, name, description) VALUES
  (10001, 'Ginza', 'Address: Aptekarskiy Ave., 16, St. Petersburg 197022, Russia.'),
  (10002, 'Mansarda', 'Address: Pochtamtskaya St., 3-5, St. Petersburg 190000, Russia.'),
  (10003, 'Terrassa', 'Address: Kazanskaya St., 3A, St. Petersburg 191186, Russia');

INSERT INTO menus (id, restaurant_id, date) VALUES
  (1001, 10001, '2018-05-01 00:00:00'),
  (1002, 10002, '2018-05-01 00:00:00'),
  (1003, 10001, '2018-05-02 00:00:00'),
  (1004, 10003, '2018-05-02 00:00:00');

INSERT INTO dishes (id, name, price) VALUES
  (101, 'Burger',     200),
  (102, 'Nuggets',    50),
  (103, 'Sandwich',   120),
  (104, 'Salad',      150),
  (105, 'Coffee',     160),
  (106, 'Tea',        110),
  (107, 'Cola',       130),
  (108, 'Chocolate',  170),
  (109, 'Cheesecake', 180),
  (110, 'Ice cream',  160);

INSERT INTO menu_dish_link (menu_id, dish_id) VALUES
  (1001, 101),(1001, 104),(1001, 105),(1001, 108),
  (1002, 102),(1002, 104),(1002, 106),(1002, 109),
  (1003, 103),(1003, 104),(1003, 107),(1003, 110),
  (1004, 102),(1004, 104),(1004, 107),(1004, 108);

INSERT INTO votes (vote_time, user_id, restaurant_id) VALUES
  ('2018-05-01 10:00:00', 100000, 1001),
  ('2018-05-01 10:00:00', 100001, 1002);
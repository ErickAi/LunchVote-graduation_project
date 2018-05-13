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
  ('ROLE_ADMIN', 100000),
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (id, name, description) VALUES
  (10000, 'Ginza', 'Address: Aptekarskiy Ave., 16, St. Petersburg 197022, Russia.'),
  (10001, 'Mansarda', 'Address: Pochtamtskaya St., 3-5, St. Petersburg 190000, Russia.'),
  (10002, 'Terrassa', 'Address: Kazanskaya St., 3A, St. Petersburg 191186, Russia');

INSERT INTO menus (id, restaurant_id, date) VALUES
  (1000, 10000, '2000-01-01 00:00:00'),
  (1001, 10001, '2000-01-02 00:00:00'),
  (1002, 10000, '3000-01-01 00:00:00'),
  (1003, 10001, '3000-01-01 00:00:00'),
  (1004, 10002, '3000-01-02 00:00:00');

INSERT INTO dishes (id, name, price) VALUES
  (100, 'Burger', 200),
  (101, 'Nuggets', 50),
  (102, 'Sandwich', 120),
  (103, 'Salad', 150),
  (104, 'Coffee', 160),
  (105, 'Tea', 109),
  (106, 'Cola', 130),
  (107, 'Chocolate', 170),
  (108, 'Cheesecake', 180),
  (109, 'Ice cream', 160);

INSERT INTO menu_dish_link (menu_id, dish_id) VALUES
  (1000, 100), (1000, 103), (1000, 104), (1000, 107),
  (1001, 101), (1001, 103), (1001, 105), (1001, 108),
  (1002, 102), (1002, 103), (1002, 106), (1002, 109),
  (1003, 101), (1003, 103), (1003, 106), (1003, 108),
  (1004, 100), (1004, 103), (1004, 105), (1004, 109);
INSERT INTO votes (user_id, menu_id, date) VALUES
  (100001, 1000, '2000-01-01 00:00:00'),
  (100001, 1002, '3000-01-01 00:00:00');
DELETE FROM users;
DELETE FROM user_roles;
DELETE FROM restaurants;
DELETE FROM dishes;
DELETE FROM votes;


ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('Admin', 'admin@gmail.com', '{noop}admin'),
  ('User', 'user@yandex.ru', '{noop}user');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_ADMIN', 100000),
  ('ROLE_USER', 100000),
  ('ROLE_USER', 100001);

INSERT INTO restaurants (id, name) VALUES
  (10000, 'Mansarda'),
  (10001, 'Terrassa'),
  (10002, 'Restaurant Closed');

INSERT INTO menus (id, restaurant_id, menu_date) VALUES
  (1000, 10000, 'yesterday'),     --past vote exist
  (1001, 10000, CURRENT_DATE),    --current vote exist
  (1002, 10001, CURRENT_DATE),    --current for update
  (1003, 10000, 'tomorrow'),      --future not voted
  (1004, 10001, 'tomorrow'),      --future not voted

  (1005, 10000, '2018-12-31 00:00:00');

/*
  (1000, 10000, CURRENT_DATE -'1' DAY),   --past vote exist
  (1001, 10000, CURRENT_DATE),            --current vote exist
  (1002, 10001, CURRENT_DATE),            --current for update
  (1003, 10000, CURRENT_DATE +'1' DAY),   --future not voted
  (1004, 10001, CURRENT_DATE +'1' DAY),   --future not voted

  (1005, 10000, '2018-12-31 00:00:00');
*/

INSERT INTO dishes (id, name, price, menu_id) VALUES
  (100, 'Burger',   200, 1000), --past not voted
  (101, 'Coffee',   160, 1000),
  (102, 'Ice cream',160, 1000),

  (103, 'Nuggets',  160, 1001), --current vote exist
  (104, 'Tea',      110, 1001),
  (105,'Cheesecake',180, 1001),
  (106, 'Burger',   200, 1002), --current not voted
  (107, 'Coffee',   160, 1002),
  (108, 'Ice cream',160, 1002),

  (109, 'Sandwich', 120, 1003), --future not voted
  (110, 'Cola',     130, 1003),
  (111, 'Chocolate',170, 1003),
  (112, 'Nuggets',  160, 1004), --future for update
  (113, 'Tea',      110, 1004),
  (114,'Cheesecake',180, 1004);
;
INSERT INTO votes (user_id, menu_id, vote_date) VALUES
  (100001, 1001, CURRENT_DATE);
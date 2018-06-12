DROP TABLE votes        IF EXISTS;
DROP TABLE dishes       IF EXISTS;
DROP TABLE menus        IF EXISTS;
DROP TABLE restaurants  IF EXISTS;
DROP TABLE user_roles   IF EXISTS;
DROP TABLE users        IF EXISTS;

DROP SEQUENCE global_seq IF EXISTS;

CREATE SEQUENCE GLOBAL_SEQ AS INTEGER START WITH 100000;

CREATE TABLE users
(
  id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name            VARCHAR(255)            NOT NULL,
  email           VARCHAR(255)            NOT NULL,
  password        VARCHAR(255)            NOT NULL,
  registered      TIMESTAMP DEFAULT now() NOT NULL,
  enabled         BOOLEAN DEFAULT TRUE    NOT NULL
);
CREATE UNIQUE INDEX users_unique_email_idx ON USERS (email);

CREATE TABLE user_roles
(
  user_id         INTEGER NOT NULL,
  role            VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES USERS (id) ON DELETE CASCADE
);

CREATE TABLE restaurants (
  id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name            VARCHAR(255)            NOT NULL
);
CREATE UNIQUE INDEX unique_restaurant ON restaurants (name);

CREATE TABLE menus (
  id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  restaurant_id   INTEGER                 NOT NULL,
  menu_date       TIMESTAMP DEFAULT now() NOT NULL ,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX unique_menu ON menus (menu_date, restaurant_id);

CREATE TABLE dishes (
  id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  name            VARCHAR(255)            NOT NULL,
  price           INTEGER                 NOT NULL,
  menu_id         INTEGER                 NOT NULL
);
CREATE UNIQUE INDEX unique_dish ON dishes (name, menu_id);

CREATE TABLE votes (
  id              INTEGER GENERATED BY DEFAULT AS SEQUENCE GLOBAL_SEQ PRIMARY KEY,
  user_id         INTEGER            NOT NULL,
  menu_id         INTEGER            NOT NULL,
  vote_date       TIMESTAMP DEFAULT now(),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
  FOREIGN KEY (menu_id) REFERENCES menus (id) ON DELETE CASCADE
);
CREATE UNIQUE INDEX unique_vote ON votes (user_id, vote_date);
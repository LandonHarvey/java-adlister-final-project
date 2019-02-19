DROP DATABASE adlister_db;

CREATE DATABASE adlister_db;

DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS ad_categories;
DROP TABLE IF EXISTS comments;

USE adlister_db;

CREATE TABLE users (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    username VARCHAR(240)  UNIQUE NOT NULL,
    email VARCHAR(240) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ads (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    user_id INT UNSIGNED NOT NULL,
    title VARCHAR(240) NOT NULL,
    description TEXT NOT NULL,
    created TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE categories (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    category_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE ad_categories (
  ad_id INT UNSIGNED NOT NULL,
  categories_id INT UNSIGNED NOT NULL,
  FOREIGN KEY (ad_id) REFERENCES ads(id)
    ON DELETE CASCADE,
  FOREIGN KEY (categories_id) REFERENCES  categories(id)
);

CREATE TABLE vote_ad (
  ad_id INT UNSIGNED NOT NULL,
  user_id INT UNSIGNED NOT NULL,
  direction ENUM('up', 'down') NOT NULL,
  vote TIMESTAMP NOT NULL,
  FOREIGN KEY (ad_id) REFERENCES ads(id)
    ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE vote_comment (
  comment_id INT UNSIGNED NOT NULL,
  user_id INT UNSIGNED NOT NULL,
  direction ENUM('up', 'down') NOT NULL,
  vote TIMESTAMP NOT NULL,
  FOREIGN KEY (comment_id) REFERENCES comments(id)
    ON DELETE CASCADE,
  FOREIGN KEY (user_id) REFERENCES users(id)
        ON DELETE CASCADE
);

CREATE TABLE profile_pic (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT UNSIGNED NOT NULL,
  fileHandler VARCHAR(50) NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id)
    ON DELETE CASCADE
);

CREATE TABLE comments (
  id INT UNSIGNED NOT NULL AUTO_INCREMENT,
  user_id INT UNSIGNED NOT NULL,
  ad_id INT UNSIGNED NOT NULL,
  parent_comment_id int UNSIGNED NULL DEFAULT NULL,
  comment TEXT NOT NULL,
  posted TIMESTAMP NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (user_id) REFERENCES users(id)
    ON DELETE CASCADE,
  FOREIGN KEY (ad_id) REFERENCES ads(id)
    ON DELETE CASCADE,
  FOREIGN KEY (parent_comment_id) REFERENCES comments (id)
    ON DELETE CASCADE
);
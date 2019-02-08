USE adlister_db;

DROP DATABASE adlister_db;

CREATE DATABASE adlister_db;


DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS users;

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
  FOREIGN KEY (ad_id) REFERENCES ads(id),
  FOREIGN KEY (categories_id) REFERENCES  categories(id)
    ON DELETE CASCADE
);

INSERT INTO categories (category_name)
VALUES ('computer'), ('house'), ('car');

INSERT INTO ad_categories (ad_id, categories_id)
VALUES (6,1), (6,2);

SELECT * FROM ads WHERE id = '1';

SELECT * FROM ads JOIN users AS u ON u.id = ads.user_id  WHERE ads.id = 1;

SELECT * FROM ads WHERE user_id = 2;

SELECT category_name AS category FROM categories c JOIN ad_categories ac ON c.id = ac.categories_id WHERE ad_id = 6;



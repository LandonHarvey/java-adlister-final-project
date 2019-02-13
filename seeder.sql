USE adlister_db;

INSERT INTO ads (user_id, title, description, created)
VALUES (1,'AdminTest','Supman this is a cool test', NOW());

INSERT INTO categories (category_name)
VALUES ('computer'), ('house'), ('car');

INSERT INTO ad_categories (ad_id, categories_id)
VALUES (1,1), (1,2);

INSERT INTO vote_ad (ad_id, user_id, direction, vote)
VALUES (3,1,'up', (SELECT NOW()));

SELECT * FROM vote_ad WHERE ad_id = 3 AND direction = 'down';

SELECT * FROM ads WHERE id = '1';

SELECT * FROM ads JOIN users AS u ON u.id = ads.user_id  WHERE ads.id = 1;

SELECT * FROM ads WHERE user_id = 2;

SELECT category_name AS category FROM categories c JOIN ad_categories ac ON c.id = ac.categories_id WHERE ad_id = 6;

SELECT * FROM ad_categories ac JOIN ads a ON ac.ad_id = a.id JOIN ad_categories b on a.id = b.ad_id WHERE ac.categories_id = 1 and b.categories_id = 2;

SELECT * FROM ad_categories ac JOIN ads a ON ac.ad_id = a.id JOIN ad_categories b on a.id = b.ad_id JOIN ad_categories c on a.id = c.ad_id WHERE ac.categories_id = 1 AND b.categories_id = 2 AND c.categories_id = 3;

SELECT * FROM ad_categories ac JOIN ads a ON ac.ad_id = a.id JOIN ad_categories b on a.id = b.ad_id JOIN ad_categories c on a.id = c.ad_id WHERE AND c.categories_id = 3
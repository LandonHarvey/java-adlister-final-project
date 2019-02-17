USE adlister_db;

INSERT INTO ads (user_id, title, description, created)
VALUES (1,'AdminTest','Supman this is a cool test', NOW());

INSERT INTO categories (category_name)
VALUES ('computer'), ('house'), ('car');

INSERT INTO ad_categories (ad_id, categories_id)
VALUES (1,1), (1,2);

INSERT INTO vote_ad (ad_id, user_id, direction, vote)
VALUES (3,1,'up', (SELECT NOW()));

INSERT INTO comments (user_id, ad_id, comment)
VALUES (1,3,'This post is amazin!!');

INSERT INTO comments (user_id, ad_id, parent_comment_id, comment)
VALUES (1,3,1,'Hello Dad');

INSERT INTO comments (user_id, ad_id, parent_comment_id, comment)
VALUES (1,3,2,'Sup WINNER');

INSERT INTO comments (user_id, ad_id, parent_comment_id, comment)
VALUES (2,3,3,'Its jerry the 3rd nest boys');

SELECT * FROM comments JOIN users u on comments.user_id = u.id  WHERE ad_id = 3 and parent_comment_id is null;

SELECT * FROM vote_ad WHERE ad_id = 3 AND direction = 'down';

SELECT * FROM ads WHERE id = '1';

SELECT * FROM ads JOIN users AS u ON u.id = ads.user_id  WHERE ads.id = 1;

SELECT * FROM ads WHERE user_id = 2;

SELECT category_name AS category FROM categories c JOIN ad_categories ac ON c.id = ac.categories_id WHERE ad_id = 6;

SELECT * FROM ad_categories ac JOIN ads a ON ac.ad_id = a.id JOIN ad_categories b on a.id = b.ad_id WHERE ac.categories_id = 1 and b.categories_id = 2;

SELECT * FROM ad_categories ac JOIN ads a ON ac.ad_id = a.id JOIN ad_categories b on a.id = b.ad_id JOIN ad_categories c on a.id = c.ad_id WHERE ac.categories_id = 1 AND b.categories_id = 2 AND c.categories_id = 3;

SELECT * FROM vote_ad v JOIN ads ON ads.id = v.ad_id where v.user_id = 1 and v.direction = 'up' and v.vote >= NOW() - INTERVAL 1 DAY;

SELECT * FROM vote_ad v JOIN ads ON ads.id = v.ad_id where v.direction = 'up' and ads.user_id = 1 and not v.user_id = 1;

SELECT * FROM profile_pic WHERE user_id = 1;

SELECT * FROM ad_categories ac JOIN ads a ON ac.ad_id = a.id JOIN ad_categories b on a.id = b.ad_id JOIN ad_categories c on a.id = c.ad_id WHERE AND c.categories_id = 3

SELECT * FROM profile_pic WHERE user_id = 7;

SELECT * FROM comments JOIN users u on comments.user_id = u.id
JOIN comments c2 ON comments.id = c2.parent_comment_id
ORDER BY comments.id;

SELECT * FROM comments JOIN users u on comments.user_id = u.id
JOIN comments c2 ON comments.id = c2.parent_comment_id ORDER BY comments.id;

-- WHERE comments.ad_id = 3 and comments.parent_comment_id is null

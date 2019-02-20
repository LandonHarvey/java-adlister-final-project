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

INSERT INTO admins (level, user_id, password)
VALUES (3,1,'$2a$12$aJIfWgo5ieSBFDLwYRpUBOnZpoCSCQFNiR53denCFhJKdMCIe5C7a');

SELECT * FROM admins WHERE id = 7;
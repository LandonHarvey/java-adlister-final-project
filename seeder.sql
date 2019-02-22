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

INSERT INTO admins (level, jedimaster, user_id, password)
VALUES (1,1,3,'$2a$12$aJIfWgo5ieSBFDLwYRpUBOnZpoCSCQFNiR53denCFhJKdMCIe5C7a');

INSERT INTO offense (offense_name)
VALUES ('Spam'), ('Targeted Harassment'), ('Threatens violence or physical harm'), ('Rude, Vulgar'), ('Copyright'),('Pornography'),
('Sexual or suggestive content'), ('Threatens self-harm or suicide'), ('Personal or confidential information');

INSERT INTO report (user_id, offense, user_reported_id, ad_id, description)
VALUES (1, 1, 3, 6,'KEEPS SPAMMING!');

INSERT INTO report (user_id, offense, user_reported_id , ad_id, description)
VALUES (1,1,3,14,'Mean');

INSERT INTO report(user_id, offense, user_reported_id, comment_id, description)
VALUES (1,1,3,35,'Mean Comment')

INSERT INTO report (user_id, offense, user_reported_id, comment_id, description)
VALUES (1, 1, 1, 1,'KEEPS SPAMMING COMMENTS!');

SELECT u.username AS 'jedimaster', u2.username, a.level AS 'level' FROM admins AS a
JOIN users u on a.jedimaster = u.id
JOIN users u2 on a.user_id = u2.id;


SELECT r.id, u.username,u.id as 'u1id', o.offense_name, r.description, u2.username as 'offender', u2.id as 'u2id', a.title, r.created
FROM report AS r JOIN users u on r.user_id = u.id
JOIN users u2 on r.user_reported_id= u2.id JOIN ads a on r.ad_id = a.id
JOIN offense o on r.offense = o.id WHERE r.id = 3;

SELECT a.id, u.username, o.offense_name, r.description, u2.username as 'offender', c.comment as 'title', r.created FROM report AS r
JOIN users u on r.user_id = u.id
JOIN users u2 on r.user_reported_id= u2.id
JOIN comments c on r.comment_id = c.id
JOIN ads as a on c.ad_id = a.id
JOIN offense o on r.offense = o.id
WHERE r.id = 2

SELECT * FROM admins WHERE user_id = 7;

DELETE FROM report where id = 4;

SELECT r.id, u.username, o.offense_name, r.description, u2.username as 'offender', a.title, r.created FROM report AS r
JOIN users u on r.user_id = u.id
JOIN ads a on r.ad_id = a.id
JOIN offense o on r.offense = o.id
JOIN users u2 on a.id= u2.id
WHERE r.id = ?;

SELECT r.id, u.username, o.offense_name, r.description, u2.username as 'offender', a.title, r.created FROM report AS r
JOIN users u on r.user_id = u.id
JOIN ads a on r.ad_id = a.id
JOIN offense o on r.offense = o.id
JOIN users u2 on a.user_id = u2.id
WHERE ad_id IS NOT NULL;

SELECT * FROM users;
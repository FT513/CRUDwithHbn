CREATE TABLE labels (
id INTEGER PRIMARY KEY,
name CHARACTER VARYING(255)
);

CREATE TABLE posts (
id INTEGER PRIMARY KEY,
content CHARACTER VARYING(255),
created DATETIME DEFAULT NOW(),
updated TIMESTAMP,
post_status CHARACTER VARYING(30)
);

CREATE TABLE post_labels(
post_id INTEGER,
labels_id INTEGER,
UNIQUE(post_id, labels_id),
FOREIGN KEY(post_id) REFERENCES posts(id),
FOREIGN KEY(labels_id) REFERENCES labels(id)
 );

CREATE TABLE writers(
id INTEGER PRIMARY KEY,
first_name CHARACTER VARYING(255),
last_name CHARACTER VARYING(255)
);

CREATE TABLE writer_posts(
writer_id INTEGER,
posts_id INTEGER,
UNIQUE(writer_id, posts_id),
FOREIGN KEY(writer_id) REFERENCES writers(id),
FOREIGN KEY(posts_id) REFERENCES posts(id)
);


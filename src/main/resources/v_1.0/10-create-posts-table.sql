CREATE TABLE IF NOT EXISTS chatter.posts
(
    id           UUID PRIMARY KEY,
    author	     UUID REFERENCES chatter.persons (id),
    creation_date DATE,
    message	 TEXT,
    likes	     INTEGER
);
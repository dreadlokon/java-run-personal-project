CREATE TABLE IF NOT EXISTS chatter.likes
(
    person_id UUID REFERENCES chatter.persons (id),
    post_id UUID  ,
    FOREIGN KEY (post_id) REFERENCES chatter.posts (id) ON DELETE CASCADE
);
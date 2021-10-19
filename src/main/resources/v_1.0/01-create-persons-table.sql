CREATE TABLE IF NOT EXISTS chatter.persons
(
    id        	UUID PRIMARY KEY,
    first_name  varchar(30),
    last_name 	varchar(30),
    nick_name   varchar(30),
    birth_date 	DATE,
    email     	varchar(50),
    role	    person_role,
    UNIQUE(email,nick_name)
);
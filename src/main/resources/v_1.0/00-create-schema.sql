CREATE SCHEMA IF NOT EXISTS chatter;

CREATE TYPE  chatter.person_role  AS ENUM  ('administrator', 'moderator', 'user');

CREATE EXTENSION IF NOT EXISTS "uuid-ossp";



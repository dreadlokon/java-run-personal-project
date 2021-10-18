# java-run-personal-project
+ 1st issue done 

+ 2nd issue done

+ 3d issue in progress

---
### Структура таблиц
### Создаем отдельную от внутренних таблиц схему.

>CREATE SCHEMA IF NOT EXISTS chatter;

### Создаем роли пользователей

>CREATE TYPE personRole AS ENUM ('administrator', 'moderator', 'user');  	//возможно лишнее в рамках прокта, если не подключать Spring Security  

### Основные сущности

>CREATE TABLE IF NOT EXISTS chatter.persons  
(  
 id        	UUID PRIMARY KEY,  
 first_name  varchar(30),  
 last_name 	varchar(30),  
 birth_date 	DATE,			  
 email     	varchar(50),  
 role	    personRole,  
 UNIQUE(email)  
);

>CREATE TABLE IF NOT EXISTS chatter.posts  
(  
 id           UUID PRIMARY KEY,  
 author	     UUID REFERENCES chatter.persons (id),  
 creation_date DATE,  
 message	 TEXT  
 likes	     INTEGER,		//здесь храним только количество  
);  

### M2M таблица
> CREATE TABLE IF NOT EXISTS chatter.likes  
(  
 person_id UUID REFERENCES chatter.persons (id),  
 post_id UUID  ,  
 FOREIGN KEY (post_id) REFERENCES chatter.posts (id) ON DELETE CASCADE,  
)

### Команды запуска Docker
docker run --name postgres-2 -p 32768:5432 -e POSTGRES_USER=javarun -e POSTGRES_PASSWORD=javarun -e POSTGRES_DB=javarun -d postgres
docker run --name liquitest -p 32769:5432 -e POSTGRES_USER=javarun -e POSTGRES_PASSWORD=javarun -e POSTGRES_DB=javarun -d postgres

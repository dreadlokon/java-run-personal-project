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

>CREATE TABLE IF NOT EXISTS chatter.person  
(  
 id        	UUID PRIMARY KEY,  
 firstName  varchar(30),  
 lastName 	varchar(30),  
 birthDate 	DATE,			//возможно лучше BigInteger для более простой совместимости с Java Date.class  
 email     	varchar(50),  
 role	    personRole,  
);

>CREATE TABLE IF NOT EXISTS chatter.post  
(  
 id           UUID PRIMARY KEY,  
 author	     UUID REFERENCES chatter.person (id),  
 creationDate DATE,  
 message	 TEXT  
 likes	     INTEGER,		//ранее данное поле отсутсвовало в классе модели  
);


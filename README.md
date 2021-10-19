# java-run-personal-project
+ 1st issue done 

+ 2nd issue done

+ 3d issue in progress

---
### Структура таблиц
### Создаем отдельную от внутренних таблиц схему. Создаем роли пользователей

v_1.0/00-changeset-create-schema.xml

### Основные сущности

v_1.0/01-changeset-persons-table.xml

v_1.0/10-changeset-posts-table.xml  

### M2M таблица
v_1.0/20-changeset-likes-table.xml

Настройки liquibase пока открыто лежат в pom.xml

### Команды запуска Docker
docker run --name postgres-2 -p 32768:5432 -e POSTGRES_USER=javarun -e POSTGRES_PASSWORD=javarun -e POSTGRES_DB=javarun -d postgres  
docker run --name liquitest -p 32769:5432 -e POSTGRES_USER=javarun -e POSTGRES_PASSWORD=javarun -e POSTGRES_DB=javarun -d postgres

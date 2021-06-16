DROP TABLE IF EXISTS PLANET;
DROP TABLE IF EXISTS lord;
CREATE TABLE IF NOT EXISTS PLANET (id int auto_increment, name varchar(250), lord_id int);
CREATE TABLE IF NOT EXISTS lord (id int auto_increment, name varchar(250), age int);

-- INSERT INTO lord values (1, 'name', 1);
-- INSERT INTO lord values (2, 'name', 2);
-- INSERT INTO PLANET values (1, 'name', 1);

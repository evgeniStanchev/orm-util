DROP DATABASE IF EXISTS test_orm;

CREATE DATABASE IF NOT EXISTS test_orm;

USE test_orm;

DROP TABLE IF EXISTS primitive_types_and_string;


CREATE TABLE primitives_and_string(id INTEGER NOT NULL AUTO_INCREMENT, 
byte_ TINYINT,
short_ SMALLINT, 
integer_ INTEGER,
long_ BIGINT,
boolean_ BIT,
float_ REAL,
double_ DOUBLE,
string_ VARCHAR(255),
primary key(id));

INSERT INTO primitives_and_string(byte_, short_, integer_, long_, boolean_,float_,double_,string_) VALUES
(1, 16, 250, 10202, true, 10.1011, 100282.219021, "one"),
(2, 26, 2520, 1002, false, 210.1011, 40282.218921, "two"),
(1, 11, 2501, 29302, true, 130.10121, 3282.84734, "three"),
(4, 32, 3250, 9302, false, 120.10111, 82.21745, "four"),
(2, 43, 5250, 10293022, true, 104.10311, 1082.21783, "five"),
(7, 121, 2250, 1202930211, true, 1610.10112, 1282.27383, "six"),
(5, 182, 50, 123029302, false, 120.10161, 1282.219328, "seven");

DROP TABLE IF EXISTS date_and_time_types;

CREATE TABLE date_and_time_types(id int NOT NULL AUTO_INCREMENT, 
	 date_ DATE ,
	 timestamp_ TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	 primary key(id)
);

INSERT INTO date_and_time_types(date_,timestamp_) VALUES 
("1993-06-12","1993-06-12 00:12:30"),
("1982-12-15","1982-12-15 11:22:31"),
("1988-11-01","1988-11-01 10:52:42"),
("1992-09-17","1992-09-17 02:47:03"),
("1996-07-04","1996-07-04 05:36:11"),
("1992-01-11","1992-01-11 03:18:26"),
("2020-02-13","2020-02-13 03:19:26"),
("2001-11-13","2001-11-13 04:12:17"),
("1993-11-15","1993-11-15 07:11:28");

DROP TABLE IF EXISTS big_integer_decimal_types;

CREATE TABLE big_integer_decimal_types(id int NOT NULL AUTO_INCREMENT, 
	biginteger_ bigint,
	bigdecimal_ decimal(10,2),
	primary key(id)
);

INSERT INTO big_integer_decimal_types(biginteger_, bigdecimal_) VALUES
("12155013", '1289232.51'),
("19334253", '103892.12'),
("19334013", '15842.14'),
("194354013", '178402.19'),
("193854013", '10222.41'),
("1923413", '1567.11'),
("19315633", '162.41'),
("1942013", '1132.16'),
("19301543", '12312.18');


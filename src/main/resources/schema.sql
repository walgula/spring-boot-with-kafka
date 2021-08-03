DROP TABLE IF EXISTS countries;
CREATE TABLE countries (
  id INTEGER NOT NULL PRIMARY KEY,
  city VARCHAR(100) NOT NULL,
  country VARCHAR(100) NOT NULL
);
insert into countries (id,city,country) values (1,'WARSAW','POLAND');
insert into countries (id,city,country) values (2,'BERLIN','GERMANY');
DROP TABLE IF EXISTS test.books;

CREATE TABLE test.books (
  ID INT NOT NULL AUTO_INCREMENT,
  TITLE VARCHAR(100),
  DESCRIPTION VARCHAR(255),
  AUTHOR VARCHAR(100),
  ISBN VARCHAR(20),
  PRINT_YEAR INT,
  READ_ALREADY TINYINT(1),
  PRIMARY KEY (ID));

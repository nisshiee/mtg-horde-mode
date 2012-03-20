# CardMaster schema

# --- !Ups

CREATE TABLE card_master (
   id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT
  ,card_type TINYINT NOT NULL -- 1:Normal 2:Token
  ,name VARCHAR(256) NOT NULL
  
  ,PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE card_master;

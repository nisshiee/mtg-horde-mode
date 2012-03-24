# CardMaster schema

# --- !Ups

CREATE TABLE card_master (
   id INT UNSIGNED NOT NULL AUTO_INCREMENT
  ,card_type TINYINT NOT NULL -- 1:Normal 2:Token
  ,name VARCHAR(256) NOT NULL
  
  ,PRIMARY KEY (id)
);

CREATE TABLE card_image (
   id INT UNSIGNED NOT NULL
  ,content_type VARCHAR(32) NOT NULL
  ,image_binary MEDIUMBLOB NOT NULL
  
  ,PRIMARY KEY (id)
);

# --- !Downs

DROP TABLE card_master;
DROP TABLE card_image;

CREATE DATABASE belatrix;
---USE belatrix
CREATE TABLE Log_Values 
  ( 
     id       INT(6) auto_increment PRIMARY KEY, 
     message  VARCHAR(5000) NOT NULL, 
     typemessage     VARCHAR(10) NOT NULL, 
     reg_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
  ); 
CREATE TABLE holder (
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE bank_card (
   number BIGINT NOT NULL,
   pin_code INT NOT NULL,
   holder_id INT,
   FOREIGN KEY (holder_id) REFERENCES holder(id)
);
CREATE TABLE holder (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE bank_card (
    id int NOT NULL AUTO_INCREMENT,
    pin_code INT NOT NULL,
    balance FLOAT NOT NULL DEFAULT 0.00,
    number_hash VARCHAR(255),
    number_last_digits INT,
    holder_id INT,
    FOREIGN KEY (holder_id) REFERENCES holder(id),
    PRIMARY KEY (id)
);
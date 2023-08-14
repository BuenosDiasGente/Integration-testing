CREATE TABLE persons(
     id INT PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(100),
     age INTEGER,
     can_drive BOOLEAN,
     car_id INTEGER REFERENCES car(id)
);

CREATE TABLE car(
    id INT PRIMARY KEY AUTO_INCREMENT,
    brand VARCHAR(50),
    model VARCHAR(50),
    price DECIMAL(15,6)
);
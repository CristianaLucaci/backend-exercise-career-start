CREATE TABLE dog_breeds (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE dogs (
    id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    breed_id INT,
    treats INT DEFAULT 0,
    CONSTRAINT fk_breed
        FOREIGN KEY (breed_id)
        REFERENCES dog_breeds (id)
);
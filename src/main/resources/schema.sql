DROP TABLE IF EXISTS drone;
CREATE TABLE drone
(
    serial_number    VARCHAR(100) PRIMARY KEY,
    model            VARCHAR(32) NOT NULL,
    weight_limit     NUMERIC NOT NULL,
    battery_capacity NUMERIC NOT NULL,
    state            VARCHAR(32) NOT NULL
);

DROP TABLE IF EXISTS medication;
CREATE TABLE medication
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(32) NOT NULL,
    weight NUMERIC NOT NULL,
    code   VARCHAR(32) NOT NULL,
    image  BINARY
);

DROP TABLE IF EXISTS delivery;
CREATE TABLE delivery
(
    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
    drone_id VARCHAR(100),
    medication_id BIGINT,
    start_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP,

    FOREIGN KEY (drone_id) REFERENCES drone(serial_number),
    FOREIGN KEY (medication_id) REFERENCES medication(id)
);




INSERT INTO drone(serial_number, model, weight_limit, battery_capacity, state) VALUES ('6d3db7c5-84e4-430f-82a7-1165b2ee3e5a', 'LIGHTWEIGHT', 200, 100, 'IDLE');
INSERT INTO drone(serial_number, model, weight_limit, battery_capacity, state) VALUES ('86fd130f-061b-4b1f-9f18-2e972428b8a0', 'LIGHTWEIGHT', 200, 100, 'IDLE');
INSERT INTO drone(serial_number, model, weight_limit, battery_capacity, state) VALUES ('45cea21a-2304-4983-b777-a817d61f1cdc', 'LIGHTWEIGHT', 200, 100, 'DELIVERING');
INSERT INTO drone(serial_number, model, weight_limit, battery_capacity, state) VALUES ('fdf2d501-20d8-418d-b6f2-314add1fbb96', 'LIGHTWEIGHT', 200, 20, 'IDLE');

INSERT INTO drone(serial_number, model, weight_limit, battery_capacity, state) VALUES ('f274c65b-3944-4fcc-b482-50a3ffa326f9', 'MIDDLEWEIGHT', 300, 25, 'IDLE');
INSERT INTO drone(serial_number, model, weight_limit, battery_capacity, state) VALUES ('dfefe57a-2730-4ece-b3fa-a7ebe1cae379', 'MIDDLEWEIGHT', 300, 100, 'IDLE');
INSERT INTO drone(serial_number, model, weight_limit, battery_capacity, state) VALUES ('4c443e6c-1f5f-4e8e-9580-b26165582ee7', 'MIDDLEWEIGHT', 300, 100, 'IDLE');

INSERT INTO drone(serial_number, model, weight_limit, battery_capacity, state) VALUES ('92e22868-8a9e-4bfa-97d7-7d6b7e99b4ce', 'CRUISERWEIGHT', 400, 50, 'IDLE');
INSERT INTO drone(serial_number, model, weight_limit, battery_capacity, state) VALUES ('f02787fe-3b89-405d-9628-e75a2f29c9a5', 'CRUISERWEIGHT', 400, 100, 'IDLE');

INSERT INTO drone(serial_number, model, weight_limit, battery_capacity, state) VALUES ('686c44ee-85a0-4d07-9b18-a392e630e44d', 'HEAVYWEIGHT', 500, 100, 'IDLE');


INSERT INTO medication(id, name, weight, code) VALUES (10001, 'Pills-1_100', 100, 'PILLS_1_100');
INSERT INTO medication(id, name, weight, code) VALUES (10002, 'Pills-2_100', 100, 'PILLS_2_100');

INSERT INTO delivery(id, drone_id, medication_id, start_date, end_date) VALUES (1001, '45cea21a-2304-4983-b777-a817d61f1cdc', 10001,  '1970-01-01 08:00:00', '1970-01-01 09:00:00');
INSERT INTO delivery(id, drone_id, medication_id, start_date) VALUES (1002, '45cea21a-2304-4983-b777-a817d61f1cdc', 10002,  '1970-01-01 12:00:00');

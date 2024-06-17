INSERT INTO persons (id, name, email, password, dob, active) values
(1, 'Siva', 'siva@gmail.com', 'siva', '1980-01-01', true),
(2, 'Prasad', 'prasad@gmail.com', 'prasad', '1980-01-01', true),
(3, 'Ravi', 'ravi@gmail.com', 'ravi', '1980-01-01', true),
(4, 'Suresh', 'suresh@gmail.com', 'suresh', '1980-01-01', true),
(5, 'Rajesh', 'rajesh@gmail.com', 'rajesh', '1980-01-01', true),
(6, 'Ramesh', 'ramesh@gmail.com', 'ramesh', '1980-01-01', true),
(7, 'Raj', 'raj@gmail.com', 'raj', '1980-01-01', true),
(8, 'Rahul', 'rahul@gmail.com', 'rahul', '1980-01-01', true),
(9, 'Ramu', 'ramu@gmail.com', 'ramu', '1980-01-01', true),
(10, 'Kumar', 'kumar@gmail.com', 'kumar', '1980-01-01', true),
(11, 'John', 'john@gmail.com', 'john', '1980-01-01', true),
(12, 'Peter', 'peter@gmail.com', 'peter', '1980-01-01', true);

INSERT INTO phone_numbers (owner_id, phone_number) values
(1, '9999999999'),
(1, '8888888888'),
(2, '7777777777');

alter sequence person_id_seq restart with 20;
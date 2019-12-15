DROP SCHEMA IF EXISTS swapbook CASCADE;
CREATE SCHEMA IF NOT EXISTS swapbook;

CREATE TABLE swapbook.users (
    user_id INT PRIMARY KEY,
    name VARCHAR (255) NOT NULL,
    email VARCHAR (255) UNIQUE NOT NULL,
    password VARCHAR (255) NOT NULL,
    address VARCHAR (255)
);

CREATE TABLE swapbook.opinions (
    opinion_id INT PRIMARY KEY,
    giver_id INT REFERENCES swapbook.users,
    receiver_id INT REFERENCES swapbook.users,
    text VARCHAR (500),
    mark INT NOT NULL CHECK ((mark >= 1) AND ( mark <= 5 )),
    date DATE
);

CREATE TABLE swapbook.books (
    book_id INT PRIMARY KEY,
    title VARCHAR (255) UNIQUE NOT NULL,
    author VARCHAR (255)  NOT NULL,
    photo_url VARCHAR (255) NOT NULL
);

CREATE TABLE swapbook.reviews(
    review_id INT PRIMARY KEY,
    book_id INT REFERENCES swapbook.books,
    user_id INT REFERENCES swapbook.users,
    text VARCHAR (500),
    mark INT NOT NULL CHECK ((mark >= 1) AND ( mark <= 5 )),
    date DATE
);

CREATE TABLE swapbook.specimens(
    specimen_id INT PRIMARY KEY,
    book_id INT REFERENCES swapbook.books,
    user_id INT REFERENCES swapbook.users,
    title VARCHAR (255) NOT NULL,
    condition VARCHAR(64),
    number_pages INT ,
    author VARCHAR (255) NOT NULL,
    release_date DATE,
    issue_number VARCHAR (64),
    isbn VARCHAR (64),
    loan_period INT,
    photo_url VARCHAR (255) NOT NULL
);

CREATE TABLE swapbook.loans(
    loan_id INT PRIMARY KEY,
    specimen_id INT REFERENCES swapbook.specimens,
    owner_id INT REFERENCES swapbook.users,
    loaner_id INT REFERENCES swapbook.users,
    loan_status VARCHAR(64),
    date_loan DATE NOT NULL,
    date_return DATE,
    period_days INT
);

INSERT INTO swapbook.users
VALUES
    (12, 'Dominik', 'kolodziejd@student.agh.edu.pl', 'xxx','Brzezówka 180'),
    (24, 'Marcin', 'marcin@gmail.com', 'yyy','Kraków Chuta'),
    (36, 'Szymon', 'sborowy@gmail.com', 'zzz','Kraków nie Kraków');

INSERT INTO swapbook.books
VALUES
    (51, 'Harry Potter i Komnata Tajemnic', 'J.K. Rowling', 'https://image.ceneostatic.pl/data/products/46228652/i-harry-potter-i-komnata-tajemnic.jpg');

INSERT INTO swapbook.specimens
VALUES
    (71, 51, 24, 'Harry Potter i komnata tajemnic', 'Good', 567, 'J. K. Rowling', '1997-06-26', 'wydanie drugie', 9780605928183, 14, 'https://image.ceneostatic.pl/data/products/46228652/i-harry-potter-i-komnata-tajemnic.jpg' );

INSERT INTO swapbook.reviews
VALUES
    (91, 51, 12, 'Super Książka', 4, '2019-12-15' );
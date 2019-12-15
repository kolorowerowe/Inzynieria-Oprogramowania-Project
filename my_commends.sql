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
    release_date VARCHAR (64),
    issue_number VARCHAR (64),
    isbn VARCHAR (64),
    publishing_house VARCHAR (64),
    loan_period INT,
    photo_url VARCHAR (255)
);

CREATE TABLE swapbook.loans(
    loan_id INT PRIMARY KEY,
    specimen_id INT REFERENCES swapbook.specimens,
    owner_id INT REFERENCES swapbook.users,
    loaner_id INT REFERENCES swapbook.users,
    loan_status VARCHAR(64),
    date_loan DATE NOT NULL,
    date_return DATE
);

INSERT INTO swapbook.users
VALUES
    (11, 'Dominik', 'kolodziejd@student.agh.edu.pl', 'xxx','Brzezówka 180'),
    (12, 'Marcin', 'marcin@gmail.com', 'yyy','Kraków Chuta'),
    (13, 'Szymon', 'sborowy@gmail.com', 'zzz','Kraków nie Kraków');

INSERT INTO swapbook.books
VALUES
    (51, 'Harry Potter i Komnata Tajemnic', 'J.K. Rowling', 'https://image.ceneostatic.pl/data/products/46228652/i-harry-potter-i-komnata-tajemnic.jpg'),
    (52, 'Świat według Clarksona. Jeśli mógłbym dokończyć...', 'Jeremy Clarkson', 'https://ecsmedia.pl/c/swiat-wedlug-clarksona-jesli-moglbym-dokonczyc-w-iext55525750.jpg');

INSERT INTO swapbook.specimens
VALUES
    (71, 51, 11, 'Harry Potter i komnata tajemnic', 'Good', 567, 'J. K. Rowling', '2015-08-07', 'wydanie drugie', '5342223', 'Insignis', 14, 'https://image.ceneostatic.pl/data/products/46228652/i-harry-potter-i-komnata-tajemnic.jpg' ),
    (72, 51, 12, 'Harry Potter i komnata tajemnic', 'Average', 566, 'J. K. Rowling', '2015-08-08', 'wydanie trzecie', '23452', 'Biały Kruk', 17, 'https://image.ceneostatic.pl/data/products/46228652/i-harry-potter-i-komnata-tajemnic.jpg' ),
    (73, 52, 13, 'Świat według Clarksona. Jeśli mógłbym dokończyć...', 'Average', 321, 'Jeremy Clarkson', '2015-08-09', 'pierwsze', '23543365', 'PWN', 10, 'https://ecsmedia.pl/c/swiat-wedlug-clarksona-jesli-moglbym-dokonczyc-w-iext55525750.jpg' );

INSERT INTO swapbook.reviews
VALUES
    (91, 51, 11, 'Super Książka', 4, '2019-12-15' ),
    (92, 52, 13, 'Clarkson to jednak umie pisać!', 5, '2019-12-11' );

INSERT INTO swapbook.opinions
VALUES
    (111, 11, 12, 'Marcin to spoko gość, powiedział Dominik jbc', 5, '2019-12-13'),
    (112, 13, 11, 'Z dominikiem nigdy nie było problemów, polecam, Szymon', 5, '2019-12-14');

INSERT INTO swapbook.loans
VALUES
    (131, 71, 11, 13, 'LOANED', '2019-12-05', '2019-12-30'),
    (132, 73, 13, 12, 'RETURNED', '2019-12-01', '2019-12-14');
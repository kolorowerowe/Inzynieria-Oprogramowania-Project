DROP SCHEMA IF EXISTS swapbook CASCADE;
CREATE SCHEMA IF NOT EXISTS swapbook;

CREATE TABLE swapbook.users (
    id INT PRIMARY KEY,
    name VARCHAR (255) NOT NULL,
    email VARCHAR (255) UNIQUE NOT NULL,
    password VARCHAR (255) NOT NULL,
    address VARCHAR (255),
    is_active BOOLEAN
);

CREATE TABLE swapbook.opinions (
    opinion_id INT PRIMARY KEY,
    giver_id INT REFERENCES swapbook.users(id),
    receiver_id INT REFERENCES swapbook.users(id),
    text VARCHAR (500),
    mark INT NOT NULL CHECK ((mark >= 1) AND ( mark <= 5 )),
    date DATE
);

CREATE TABLE swapbook.books (
    id INT PRIMARY KEY,
    title VARCHAR (255) UNIQUE NOT NULL,
    author VARCHAR (255)  NOT NULL,
    photo_url VARCHAR (255) NOT NULL
);

CREATE TABLE swapbook.reviews(
    review_id INT PRIMARY KEY,
    book_id INT REFERENCES swapbook.books(id),
    user_id INT REFERENCES swapbook.users(id),
    text VARCHAR (500),
    mark INT NOT NULL CHECK ((mark >= 1) AND ( mark <= 5 )),
    date DATE
);

CREATE TABLE swapbook.specimens(
    specimen_id INT PRIMARY KEY,
    book_id INT REFERENCES swapbook.books(id),
    user_id INT REFERENCES swapbook.users(id),
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
    owner_id INT REFERENCES swapbook.users(id),
    loaner_id INT REFERENCES swapbook.users(id),
    loan_status VARCHAR(64),
    date_loan DATE NOT NULL,
    period_days INT,
    date_return DATE
);

INSERT INTO swapbook.users
VALUES
    (11, 'Dominik', 'kolodziejd@student.agh.edu.pl', 'xxx','Brzezowka 180, 39-102', TRUE),
    (12, 'Marcin', 'marcinkozak005@gmail.com', 'yyy','Krakow ', TRUE),
    (13, 'Szymon', 'sborowy4@gmail.com', 'zzz','Krakow ul. Jana Pawla II 12', TRUE),
    (14, 'Bartek', 'bartek@gmail.com', 'qqq','Krakow ul. Symfoniczna 1/21', TRUE);

INSERT INTO swapbook.books
VALUES
    (51, 'Harry Potter i Komnata Tajemnic', 'J.K. Rowling', 'https://image.ceneostatic.pl/data/products/46228652/i-harry-potter-i-komnata-tajemnic.jpg'),
    (52, 'Swiat wedlug Clarksona. Jesli moglbym dokonczyc...', 'Jeremy Clarkson', 'https://ecsmedia.pl/c/swiat-wedlug-clarksona-jesli-moglbym-dokonczyc-w-iext55525750.jpg'),
    (53, 'Wprowadzenie do fizyki w grach, animacjach i symulacjach Flash', 'Dev Ramtal', 'https://static01.helion.com.pl/helion/okladki/326x466/wprofi.jpg'),
    (54, 'English 4 IT', 'Beata Blaszczyk', 'https://static01.helion.com.pl/helion/okladki/326x466/anginf.jpg'),
    (55, 'Wprowadzenie do algorytmow', 'Thomas H. Cormen, Ron Rivest', 'https://ksiegarniainternetowa.de/img/product_images_new/472/20472_01_wprowadzenie_do_algorytmow.300.jpg');

INSERT INTO swapbook.specimens
VALUES
    (71, 51, 11, 'Harry Potter i komnata tajemnic', 'Good', 567, 'J. K. Rowling', '1997-06-26', 'wydanie drugie', '9780605928183', 'Wydawnictwo Magow', 14, 'https://image.ceneostatic.pl/data/products/46228652/i-harry-potter-i-komnata-tajemnic.jpg' ),
    (72, 51, 12, 'Harry Potter i komnata tajemnic', 'Average', 566, 'J. K. Rowling', '1999-07-27', 'wydanie trzecie', '9780605928122', 'PWN', 17, 'https://image.ceneostatic.pl/data/products/46228652/i-harry-potter-i-komnata-tajemnic.jpg' ),
    (73, 52, 13, 'Swiat według Clarksona. Jesli moglbym dokonczyć...', 'Average', 321, 'Jeremy Clarkson', '2019-11-13', 'pierwsze', '33634555', 'Insignis' , 10, 'https://ecsmedia.pl/c/swiat-wedlug-clarksona-jesli-moglbym-dokonczyc-w-iext55525750.jpg' ),
    (74, 53, 11, 'Wprowadzenie do fizyki w grach, animacjach i symulacjach Flash', 'Average', 531, 'Dev Ramtal', '2019-11-13', 'pierwsze', '978-83-246-4473-5', 'Helion' , 10, 'https://static01.helion.com.pl/helion/okladki/326x466/wprofi.jpg' ),
    (75, 54, 11, 'English 4 IT', 'Average', 283, 'Beata Blaszczyk', '2017-10-11', 'pierwsze', '9788328304338', 'Helion' , 10, 'https://static01.helion.com.pl/helion/okladki/326x466/anginf.jpg' ),
    (76, 55, 13, 'Wprowadzenie do algorytmow', 'Good', 999, 'Thomas H. Cormen, Ron Rivest', '2019-11-13', 'pierwsze', '9789650603533', 'PWN' , 10, 'https://emp-scs-uat.img-osdw.pl/img-p/1/kipwn/d576082e/std/2bc-452/105536615o.jpg' );

INSERT INTO swapbook.reviews
VALUES
    (91, 51, 11, 'Super Ksiazka', 4, '2019-12-15' ),
    (92, 52, 13, 'Clarkson to jednak umie pisac!', 5, '2019-12-11' );

INSERT INTO swapbook.opinions
VALUES
    (111, 11, 12, 'Marcin to spoko gosc, powiedzial Dominik jbc', 5, '2019-12-13'),
    (112, 13, 11, 'Z dominikiem nigdy nie było problemow, polecam, Szymon', 5, '2019-12-14');

INSERT INTO swapbook.loans
VALUES
    (131, 71, 11, 13, 'LOANED', '2019-12-05', 25, '2019-12-30'),
    (132, 73, 13, 12, 'RETURNED', '2019-12-01', 13, '2019-12-14');
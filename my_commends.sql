DROP SCHEMA IF EXISTS swapbook CASCADE;
CREATE SCHEMA IF NOT EXISTS swapbook;

CREATE TABLE swapbook.users (
    id INT PRIMARY KEY,
    name VARCHAR (255) NOT NULL,
    email VARCHAR (255) UNIQUE NOT NULL,
    password VARCHAR (255) NOT NULL,
    address VARCHAR (255)
);

CREATE TABLE swapbook.opinie (
    id INT PRIMARY KEY,
    id_wystawiajacy INT REFERENCES swapbook.users,
    id_oceniany INT REFERENCES swapbook.users,
    tresc VARCHAR (500),
    ocena INT NOT NULL CHECK ((idkompozycji >= 1) AND ( idkompozycji <= 5 )),
    data DATE
);

CREATE TABLE swapbook.ksiazki (
    id INT PRIMARY KEY,
    tytul VARCHAR (255) UNIQUE NOT NULL,
    autor VARCHAR (255)  NOT NULL,
    zdjecie_url VARCHAR (255) NOT NULL --url?
);

CREATE TABLE swapbook.recenzje(
    id INT PRIMARY KEY,
    ksiazkaID INT REFERENCES swapbook.ksiazki,
    userID INT REFERENCES swapbook.users,
    tresc VARCHAR (500),
    ocena INT NOT NULL CHECK ((ocena >= 1) AND ( ocena <= 5 )),
    data DATE
);

CREATE TABLE swapbook.egzemplarze(
    id INT PRIMARY KEY,
    ksiazkaID INT REFERENCES swapbook.ksiazki,
    tytul VARCHAR (255) NOT NULL,
    stan VARCHAR(64),
    lStron INT ,
    autor VARCHAR (255) NOT NULL,
    dataWydania DATE,
    nrWydania VARCHAR (64),
    ISBN VARCHAR (64),
    czas_wypozyczenia DATE,
    zdjecie_url VARCHAR (255) NOT NULL -- url?
);

CREATE TABLE swapbook.wypozyczenia(
    id INT PRIMARY KEY,
    egzemplarzID INT REFERENCES swapbook.egzemplarze,
    wlascicielID INT REFERENCES swapbook.users,
    czytelnikID INT REFERENCES swapbook.users,
    stan VARCHAR(64),                           -- tutaj z jakiejś listy rozwijanej, 1-5 czy jak?
    data_wyp DATE NOT NULL,
    data_odbioru DATE,
    dl_wyp INT                                   -- w dniach?
)

INSERT INTO Users
VALUES (12, 'Dominik', 'kolodziejd@student.agh.edu.pl', 'xxx','Brzezówka 180','12,34,53');
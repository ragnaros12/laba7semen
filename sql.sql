CREATE table Users (
	id serial PRIMARY KEY,
	login varchar(255),
	password varchar(255)
);

CREATE table Car
(
    cool boolean NOT NULL,
    car_id serial PRIMARY KEY
);

CREATE table Coordinates
(
    x real NOT NULL,
    y integer NOT NULL,
    coor_id serial PRIMARY KEY
);


CREATE table HumanBeing
(
    id serial PRIMARY KEY,
    name VARCHAR(255),
    coordinate_id integer NOT NULL,
    car_id integer NOT NULL,
    creationdate date NOT NULL,
    realhero boolean NOT NULL,
    hastoothpick boolean NOT NULL,
    impactspeed integer NOT NULL,
    weapontype varchar(255) NOT NULL,
    mood varchar(255) NOT NULL,
    creator integer NOT NULL,
    FOREIGN KEY (car_id)
        REFERENCES Car (car_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY (coordinate_id)
        REFERENCES Coordinates (coor_id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);
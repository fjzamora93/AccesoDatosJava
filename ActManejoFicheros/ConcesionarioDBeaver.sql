USE Concesionario;

CREATE TABLE Coches (
    id INT AUTO_INCREMENT PRIMARY KEY,
    matricula VARCHAR(20) NOT NULL,
    marca VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    color VARCHAR(20) NOT NULL
);

create table Pasajeros (
	id INT AUTO_INCREMENT primary key,
	nombre VARCHAR(25) NOT NULL,
	edad INT NOT NULL,
	peso INT NOT NULL
);

create table coches_pasajeros (
	id_pasajero INT,
	id_coche INT,
	CONSTRAINT fk_pasajero FOREIGN KEY (id_pasajero) REFERENCES pasajeros(id),  
    CONSTRAINT fk_coche FOREIGN KEY (id_coche) REFERENCES coches(id)  
);


select * from coches;

select * from pasajeros;

select * from coches_pasajeros;
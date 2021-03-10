CREATE TABLE cliente (
	email varchar(100) PRIMARY KEY,
	nome varchar(50) NOT NULL,
	telefone varchar(50) NOT NULL
	);
	
CREATE TABLE utensilio (
	id serial PRIMARY KEY,
	nome varchar(50) NOT NULL,
	descricao varchar(255) NOT NULL,
	cliente_id varchar(100)
	);
	
ALTER TABLE utensilio
	ADD CONSTRAINT FK_utensilio_id
	    FOREIGN KEY (cliente_id)
	    REFERENCES cliente(email)
	    ON DELETE CASCADE ON UPDATE NO ACTION;

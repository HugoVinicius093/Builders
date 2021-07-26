DROP TABLE IF EXISTS public.clients;

CREATE TABLE IF NOT exists public.clients (
	id bigserial NOT NULL,
	cep varchar(15) NOT NULL,
	cidade varchar(50) NOT NULL,
	cpf varchar(14) NOT NULL,
	data_nascimento date NULL,
	email varchar(100) NOT NULL,
	endereco varchar(100) NOT NULL,
	estado varchar(25) NOT NULL,
	nome varchar(100) NOT NULL,
	CONSTRAINT clients_pkey PRIMARY KEY (id),
	CONSTRAINT uk7it9dgecuhaofss241235vdcn UNIQUE (cpf),
	CONSTRAINT uksrv16ica2c1csub334bxjjb59 UNIQUE (email)
);

INSERT INTO clients (cep,cidade,cpf,data_nascimento,email,endereco,estado,nome) VALUES
('19060-555','Cidade A','76203621005','1998-12-20','KMbappe@gmail.com','Rua xyz','SP','Kylian Mbappe')
,('19040-185','Cidade B','27167518034','1987-12-19','KarimBenzema@gmail.com','Rua abc','MG','Karim Benzema')
,('19060-555','Cidade C','88399806013','1991-03-21','AntonieGriezmann@gmail.com','Rua xpa','BH','Antoine Griezmann')
,('19060-643','Cidade D','95897794090','1993-03-15','PaulPogba@gmail.com','Rua kxs','RJ','Paul Pogba')
,('19060-643','Cidade E','73307749013','1986-09-30','GiroudO@gmail.com','Rua kxs','RJ','Olivier Giroud')
;
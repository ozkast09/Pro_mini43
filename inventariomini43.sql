create database inventariomini43;
use inventariomini43;

create table categoria(
id int auto_increment not null primary key,
categoria varchar(50)
);
insert into categoria (categoria) values ("granos");
insert into categoria (categoria) values ("aseo");
insert into categoria (categoria) values ("confiteria");

select * from categoria;


create table marca(
id int auto_increment not null primary key,
marca varchar(50)
);
insert into marca (marca) values ("Diana");
insert into marca (marca) values ("bombombum");
insert into marca (marca) values("FAB");
insert into marca (marca) values ("La muñeca");

select * from marca;

create table unidadMedida(
id int auto_increment not null primary key,
unidadmedida varchar(50)
);
insert into unidadMedida(unidadmedida) values ("gramos");
insert into unidadMedida(unidadmedida) values("mililitros");

select * from unidadMedida;


create table proveedor(
id3 int auto_increment not null primary key,
proveedor varchar(50)
);

insert into proveedor(proveedor) values("Alpina");
insert into proveedor(proveedor) values("Nestle");
insert into proveedor(proveedor) values("Quala");
select * from proveedor;

create table Inventario(
id int auto_increment not null primary key,
nombre varchar(50),
descripcion varchar(250),
fkcategoria int,
fkmarca int,
fkunidadmedida int,
fkproveedor int,
cantidad int,
foreign key (fkcategoria) references categoria(id) ,
foreign key (fkmarca) references marca(id),
foreign key (fkunidadMedida) references unidadMedida(id),
foreign key (fkproveedor) references proveedor(id3) 
);

select * from Inventario;

select Inventario.id,Inventario.nombre,Inventario.descripcion,categoria.categoria,marca.marca,unidadMedida.unidadmedida,proveedor.proveedor,Inventario.cantidad
from Inventario inner join categoria on Inventario.fkcategoria= categoria.id inner join marca on Inventario.fkmarca= marca.id inner join unidadMedida on Inventario.fkunidadmedida= unidadMedida.id inner join proveedor on Inventario.fkproveedor= proveedor.id3;


insert into Inventario (nombre,descripcion,fkcategoria,fkmarca,fkunidadmedida,fkproveedor,cantidad) values ("Arroz Roa","Arroz",1,1,1,3,10);

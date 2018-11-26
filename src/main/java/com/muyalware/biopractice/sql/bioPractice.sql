DROP TABLE IF EXISTS kit, administrador, alumno, profesor, material;

CREATE TABLE kit(
  id serial               primary key       not null,
  fecha_Vencimiento       TIMESTAMP,
  lista_materiales        int [],
  alumno_id               int,
  profesor_id             int
);

CREATE TABLE administrador(
  id serial               primary key       not null,
  nombre                  text              not null,
  correo                  text              not null,
  contrasena              text              not null,
  activo                  boolean           not null
);

CREATE TABLE profesor(
  id serial               primary key       not null,
  nombre                  text              not null,
  correo                  text              not null,
  contrasena              text              not null,
  num_trabajador          text              not null,
  fotografia              bytea,
  activo                  boolean           not null
);

CREATE TABLE alumno(
  id serial               primary key       not null,
  nombre                  text              not null,
  correo                  text              not null,
  contrasena              text              not null,
  num_cuenta              text              not null,
  estado                  boolean           not null,
  fotografia              bytea,
  activo                  boolean           not null
);

CREATE TABLE material(
  id serial               primary key       not null,
  nombre                  text              not null,
  descripcion             text              not null,
  unidades                int               not null,
  prestado                boolean           not null,
  categoria               text              not null,
  subCategoria            text              not null,
  fotografia              bytea
  
);


insert into Administrador (id, nombre, correo, contrasena, activo) values (1, 'Jule Clemson', 'directional@ciencias.unam.mx', 'fT1tIW', false);
insert into Administrador (id, nombre, correo, contrasena, activo) values (2, 'Norah Bew', 'intranet@ciencias.unam.mx', 'EIsNFw', false);
insert into Administrador (id, nombre, correo, contrasena, activo) values (3, 'Mellie Dwane', 'Mandatory@ciencias.unam.mx', 's0keKR', false);

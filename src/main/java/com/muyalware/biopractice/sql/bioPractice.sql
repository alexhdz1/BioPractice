DROP DATABASE IF EXISTS BioPractice;
DROP TABLE IF EXISTS kit, administrador, alumno, profesor, material;

CREATE DATABASE BioPractice;

CREATE TABLE kit(
  id serial               primary key       not null,
  fecha_Vencimiento       TIMESTAMP,
  lista_materiales        text,
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

create or replace function bytea_import(p_path text, p_result out bytea) 
                   language plpgsql as $$
declare
  l_oid oid;
  r record;
begin
  p_result := '';
  select lo_import(p_path) into l_oid;
  for r in ( select data 
             from pg_largeobject 
             where loid = l_oid 
             order by pageno ) loop
    p_result = p_result || r.data;
  end loop;
  perform lo_unlink(l_oid);
end;$$;

INSERT INTO administrador (nombre, correo, contrasena, activo) VALUES ('Jule Clemson', 'directional@ciencias.unam.mx', 'fT1tIW', false);
INSERT INTO administrador (nombre, correo, contrasena, activo) VALUES ('Norah Bew', 'intranet@ciencias.unam.mx', 'EIsNFw', false);

INSERT INTO profesor (nombre, correo, contrasena, num_trabajador, activo) VALUES ('Hanna Oktaba', 'hanna@ciencias.unam.mx', 'fT1tIW', '456789871', false);
INSERT INTO profesor (nombre, correo, contrasena, num_trabajador, activo) VALUES ('Mellie Dwane', 'Mandatory@ciencias.unam.mx', 's0keKR', '4845692367',false);

INSERT INTO alumno(nombre, correo, contrasena, num_cuenta, estado, activo) VALUES('Alexis', 'alexhdz1@ciencias.unam.mx', 'alxhdz1', '315123450', 'FALSE', 'FALSE');
INSERT INTO alumno(nombre, correo, contrasena, num_cuenta, estado, activo) VALUES('Carlos', 'carlitos@ciencias.unam.mx', 'carlos', '56788767', 'FALSE', 'FALSE');
INSERT INTO alumno(nombre, correo, contrasena, num_cuenta, estado, activo) VALUES('Gibran', 'gazmatem@ciencias.unam.mx', 'zorra', '95445678', 'FALSE', 'FALSE');
INSERT INTO alumno(nombre, correo, contrasena, num_cuenta, estado, activo) VALUES('Martin', 'martin1@ciencias.unam.mx', 'martin', '2345676565', 'FALSE', 'FALSE');
INSERT INTO alumno(nombre, correo, contrasena, num_cuenta, estado, activo) VALUES('Gerardo', 'g.soto@ciencias.unam.mx', 'yo', '2346456356', 'FALSE', 'FALSE');
INSERT INTO alumno(nombre, correo, contrasena, num_cuenta, estado, activo) VALUES('Jaime', 'henry@ciencias.unam.mx', 'pelos', '245672454', 'FALSE', 'FALSE');

INSERT INTO material(nombre, descripcion, unidades, prestado, categoria, subCategoria) VALUES ('Bata', 'Bata blanca', 14,'false', 'Batas','Blancas');
INSERT INTO material(nombre, descripcion, unidades, prestado, categoria, subCategoria) VALUES ('Microscopio', 'Microscopio Optico', 10,'true', 'Lentes','Aumento');
INSERT INTO material(nombre, descripcion, unidades, prestado, categoria, subCategoria) VALUES ('Lupa', 'Lupa pequeña', 100,'false', 'Lupa','Pequeña');
INSERT INTO material(nombre, descripcion, unidades, prestado, categoria, subCategoria) VALUES ('Bata', 'Bata gris', 17,'false', 'Batas','Grises');
INSERT INTO material(nombre, descripcion, unidades, prestado, categoria, subCategoria) VALUES ('Bata', 'Bata verde', 19,'false', 'Batas','Verdes');

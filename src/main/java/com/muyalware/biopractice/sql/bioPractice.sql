DROP TABLE IF EXISTS kit, administrador, alumno, profesor, material;

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


insert into Administrador (id, nombre, correo, contrasena, activo) values (1, 'Jule Clemson', 'directional@ciencias.unam.mx', 'fT1tIW', false);
insert into Administrador (id, nombre, correo, contrasena, activo) values (2, 'Norah Bew', 'intranet@ciencias.unam.mx', 'EIsNFw', false);
insert into Administrador (id, nombre, correo, contrasena, activo) values (3, 'Mellie Dwane', 'Mandatory@ciencias.unam.mx', 's0keKR', false);

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

INSERT INTO alumno(nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) VALUES('Alexis', 'alexhdz1@ciencias.unam.mx', 'alxhdz1', '315123450', 'FALSE', bytea_import('/Users/kyuSolesito/Downloads/Image/m.jpg'), 'FALSE');
INSERT INTO alumno(nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) VALUES('Carlos', 'carlitos@ciencias.unam.mx', 'carlos', '56788767', 'FALSE', bytea_import('/Users/kyuSolesito/Downloads/Image/n.jpg'), 'FALSE');
INSERT INTO alumno(nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) VALUES('Gibran', 'gazmatem@ciencias.unam.mx', 'zorra', '95445678', 'FALSE', bytea_import('/Users/kyuSolesito/Downloads/Image/o.jpg'), 'FALSE');
INSERT INTO alumno(nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) VALUES('Martin', 'martin1@ciencias.unam.mx', 'martin', '2345676565', 'FALSE', bytea_import('/Users/kyuSolesito/Downloads/Image/p.jpg'), 'FALSE');
INSERT INTO alumno(nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) VALUES('Gerardo', 'g.soto@ciencias.unam.mx', 'yo', '2346456356', 'FALSE', bytea_import('/Users/kyuSolesito/Downloads/Image/q.jpg'), 'FALSE');
INSERT INTO alumno(nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) VALUES('Jaime', 'henry@ciencias.unam.mx', 'pelos', '245672454', 'FALSE', bytea_import('/Users/kyuSolesito/Downloads/Image/r.jpg'), 'FALSE');
INSERT INTO alumno(nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) VALUES('Hanna', 'profa@ciencias.unam.mx', 'prof', '236345634', 'FALSE', bytea_import('/Users/kyuSolesito/Downloads/Image/s.jpg'), 'FALSE');

DROP TABLE IF EXISTS kit, administrador, alumno, profesor, material;

CREATE TABLE kit(
  id serial               primary key       not null,
  fecha_Vencimiento       TIMESTAMP,
  lista_materiales        int []
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

insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (1, 'Bridgette Gullane', 'visionary@ciencias.unam.mx', 'xOrMAkPV', 426055931, false, '#bd4f9e', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (2, 'Clementius Dundredge', 'quality-focused@ciencias.unam.mx', 'jXuaKavH', 394132025, false, '#c55c51', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (3, 'Nestor Bloor', 'migration@ciencias.unam.mx', 'aLno4sbO', 353359553, false, '#f89351', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (4, 'Marlee Shalloo', 'cross-platform@ciencias.unam.mx', 'PJkkQd7G', 381837035, true, '#0e8375', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (5, 'Chucho Michiel', 'intranet@ciencias.unam.mx', 'Ech8lcP3', 313969734, false, '#d71786', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (6, 'Sophey Snowden', 'configurable@ciencias.unam.mx', 'oFFdcdpt', 496664478, true, '#e44787', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (7, 'Scott O''Roan', 'database@ciencias.unam.mx', 'NKPu4b7B', 394798227, true, '#2f7c01', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (8, 'Abramo Bruyntjes', 're-engineered@ciencias.unam.mx', 'oNRyM6vX', 392040592, false, '#0b3f47', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (9, 'King Lackey', 'protocol@ciencias.unam.mx', 'Q8vZTJ22', 378852562, true, '#1c8bd6', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (10, 'Walsh Fitzhenry', 'encoding@ciencias.unam.mx', '5864Fn2E', 357444583, false, '#a02ba2', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (12, 'Byran Bicheno', 'object-oriented@ciencias.unam.mx', 'wtNqV0Uf', 304294993, false, '#1bc9c3', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (13, 'Winslow Bosma', 'initiative@ciencias.unam.mx', 'AktT4ubo', 353746738, false, '#845ff5', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (14, 'Billy Parncutt', 'didactic@ciencias.unam.mx', 'zUiit1wh', 305867287, true, '#80b3e4', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (15, 'Enrichetta Collington', '4th_generation@ciencias.unam.mx', 'U3uPlW2D', 354401630, false, '#e98b38', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (16, 'Nichols Simonetto', 'object-based@ciencias.unam.mx', 'OJSISD7Z', 341377187, true, '#b0f83e', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (17, 'Garland Markos', 'secured line@ciencias.unam.mx', 'OmpJXY6L', 394626991, true, '#4319d3', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (18, 'Eydie Alasdair', 'focus group@ciencias.unam.mx', 'VcUReorw', 398825760, false, '#790d0b', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (19, 'Colby Gerauld', 'multi-channelled@ciencias.unam.mx', 'SjVnPcee', 477059415, true, '#6cdd45', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (20, 'Kamillah Sine', 'synergized@ciencias.unam.mx', 'ZygmooaB', 358325102, false, '#c698ce', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (21, 'Cleavland Mardling', 'circuit@ciencias.unam.mx', 'x6zKvTnP', 326989389, false, '#fa3797', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (22, 'Auguste Stove', 'superstructure@ciencias.unam.mx', 'v5Dp4C1s', 311285235, false, '#18a045', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (23, 'Maurise Klampk', 'knowledge_user@ciencias.unam.mx', 'Ob6ll7Qr', 463118567, false, '#c196bd', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (24, 'Clemmy Deinert', 'even-keeled@ciencias.unam.mx', 'rRrSSlSo', 309085134, true, '#f8a2c9', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (25, 'Antony Chismon', 'multi-lateral@ciencias.unam.mx', 'YaFJpPLy', 484292712, false, '#267d64', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (26, 'Humbert Storck', 'archive@ciencias.unam.mx', 'AYGAyMIr', 327535495, true, '#a64c95', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (27, 'Gipsy Micheau', 'moderator@ciencias.unam.mx', 'f6Gp5iuo', 319717989, true, '#8943c4', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (28, 'Katerina Yegorovnin', 'de-engineered@ciencias.unam.mx', 'Y7qEL3rZ', 424223475, true, '#d9cd96', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (29, 'Murry Frayne', 'cross-platform@ciencias.unam.mx', 'VpdrsfmD', 405899801, true, '#56a909', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (30, 'Benson Milmith', 'high-level@ciencias.unam.mx', '4M2YudvC', 302206822, false, '#a1bfc6', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (31, 'Bridie McIlwaine', 'switchable@ciencias.unam.mx', 'Zf4wrnMw', 393769728, false, '#20869b', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (32, 'Ira Cleeves', 'multi-tasking@ciencias.unam.mx', 'HGh97wCi', 427585091, true, '#ebfc72', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (33, 'Jerrine Riley', 'zero administration@ciencias.unam.mx', 'LMY7l0iL', 337705114, true, '#d57a83', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (34, 'Chancey Cassells', 'internet solution@ciencias.unam.mx', '7NbRf464', 410841392, true, '#884138', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (35, 'Thorstein Hummerston', '5th_generation@ciencias.unam.mx', 'P4lFRJ69', 432296207, true, '#1047f0', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (36, 'Orran McMoyer', 'groupware@ciencias.unam.mx', 'SRQAdSOO', 362240489, true, '#a12acc', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (37, 'Noe Houlston', 'diverse@ciencias.unam.mx', 'GzSzkE0l', 347795123, false, '#dc02d6', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (38, 'Ody Dumbell', 'upward-trending@ciencias.unam.mx', 'EG38mbCG', 418325958, true, '#d76fd8', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (39, 'Gregorius Wadly', 'non-volatile@ciencias.unam.mx', 'FIBwSTOj', 350617749, true, '#c8bc7e', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (40, 'Jess Dugmore', 'systematic@ciencias.unam.mx', 'pr3OP9QA', 373088305, true, '#6e64eb', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (41, 'Rianon Noad', 'operative@ciencias.unam.mx', 'CrSNbWms', 493255001, true, '#f4dc4c', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (42, 'Emilio Christy', 'digitized@ciencias.unam.mx', 'RUzkN57t', 357839705, true, '#d4495b', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (43, 'Kit Pett', 'function-based@ciencias.unam.mx', 'VhebC9Pr', 437708834, false, '#2a1554', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (44, 'Gavra Issakov', 'sharable@ciencias.unam.mx', 'r9604WOE', 491338563, true, '#1b649b', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (45, 'Harley Nansom', 'decentralized@ciencias.unam.mx', 'xLVfVN3X', 358543115, true, '#c732d1', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (46, 'Jeff Barrack', 'open architecture@ciencias.unam.mx', 'l7NWI9Dr', 380929423, true, '#3853f3', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (47, 'Loraine Prozescky', 'extended@ciencias.unam.mx', 'nJRi2mj0', 499172980, false, '#f2edd7', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (48, 'Sharona Andreix', 'open system@ciencias.unam.mx', 'jUHXd94D', 310020569, false, '#57f09f', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (49, 'Eimile Bartczak', 'moderator@ciencias.unam.mx', '0LYQmCzz', 389522537, false, '#94c91c', false);
insert into alumno (id, nombre, correo, contrasena, num_cuenta, estado, fotografia, activo) values (50, 'Ingunna Cherry', 'robust@ciencias.unam.mx', '5wXdsb4U', 363742319, true, '#23a759', false);

insert into profesor (id, nombre, correo, contrasena, num_Trabajador, fotografia, activo) values (1, 'Rozele Beaushaw', 'self-enabling@ciencias.unam.mx', 'Fvlrif', '25-8472925', '#8db675', false);
insert into profesor (id, nombre, correo, contrasena, num_Trabajador, fotografia, activo) values (2, 'Enoch Mathou', 'contextually-based@ciencias.unam.mx', 'MG4yU4', '88-5188860', '#c93d12', false);
insert into profesor (id, nombre, correo, contrasena, num_Trabajador, fotografia, activo) values (3, 'Trenton Mc Menamin', 'digitized@ciencias.unam.mx', 'cAjfzr', '14-0142414', '#43f09c', false);
insert into profesor (id, nombre, correo, contrasena, num_Trabajador, fotografia, activo) values (4, 'Jaymee Lester', 'optional@ciencias.unam.mx', 'hdsyMd', '15-4236160', '#a8e823', false);
insert into profesor (id, nombre, correo, contrasena, num_Trabajador, fotografia, activo) values (5, 'Kata Sinncock', 'object-oriented@ciencias.unam.mx', 'CHlG4b', '07-5199879', '#c04cc4', false);
insert into profesor (id, nombre, correo, contrasena, num_Trabajador, fotografia, activo) values (6, 'Uri oldey', 'balanced@ciencias.unam.mx', 'oH8kNu', '58-6360209', '#6d319d', false);
insert into profesor (id, nombre, correo, contrasena, num_Trabajador, fotografia, activo) values (7, 'Cyndi Jedraszczyk', 'seamless@ciencias.unam.mx', 'IZvF4H', '33-9606318', '#ea70fd', false);
insert into profesor (id, nombre, correo, contrasena, num_Trabajador, fotografia, activo) values (8, 'Henrieta Hanmore', 'workforce@ciencias.unam.mx', 'MKuyIP', '74-7336385', '#0d1f16', false);
insert into profesor (id, nombre, correo, contrasena, num_Trabajador, fotografia, activo) values (9, 'Mozelle Letts', 'multi-state@ciencias.unam.mx', 'hU1n03', '17-6799773', '#fcd440', false);
insert into profesor (id, nombre, correo, contrasena, num_Trabajador, fotografia, activo) values (10, 'Gerry Ozelton', 'team-oriented@ciencias.unam.mx', 'peHUVi', '70-8257631', '#9a9f63', false);

insert into Administrador (id, nombre, correo, contrasena, activo) values (1, 'Jule Clemson', 'directional@ciencias.unam.mx', 'fT1tIW', false);
insert into Administrador (id, nombre, correo, contrasena, activo) values (2, 'Norah Bew', 'intranet@ciencias.unam.mx', 'EIsNFw', false);
insert into Administrador (id, nombre, correo, contrasena, activo) values (3, 'Mellie Dwane', 'Mandatory@ciencias.unam.mx', 's0keKR', false);

insert into material (id, nombre, descripcion, unidades, prestado, categoria, subcategoria, fotografia) values (1, 'Y-Solowarm', 'potassium chloride', 10,true, 'Potassium Chloride', 'Watson Pharma, Inc.', '#46e582');
insert into material (id, nombre, descripcion, unidades, prestado, categoria, subcategoria, fotografia) values (2, 'Zamit', 'Sodium Fluoride', 5, false, 'Gelato APF', 'Mycone Dental Supply Co..', '#d849ca');
insert into material (id, nombre, descripcion, unidades, prestado, categoria, subcategoria, fotografia) values (3, 'Flowdesk', 'PROMETHAZINE HYDROCHLORIDE, PHENYLEPHRINE HYDROCHLORIDE AND CODEINE PHOSPHATE', 3, true, 'PROMETHAZINE VC WITH CODEINE', 'Actavis Mid Atlantic LLC', '#265a59');
insert into material (id, nombre, descripcion, unidades, prestado, categoria, subcategoria, fotografia) values (4, 'It', 'Duloxetine hydrochloride', 10, false, 'Cymbalta', 'Bryant Ranch Prepack', '#09b1d5');
insert into material (id, nombre, descripcion, unidades, prestado, categoria, subcategoria, fotografia) values (5, 'Latlux', 'OCTINOXATE, TITANIUM DIOXIDE', 10, false, 'DiorSnow White Reveal UV Shield Loose Powder Tender Peach 003', 'Parfums Christian Dior', '#81b97e');
insert into material (id, nombre, descripcion, unidades, prestado, categoria, subcategoria, fotografia) values (6, 'Greenlam', 'Alcohol', 10, false, 'Freshorize', 'Freshorize, Ltd.', '#ea2421');
insert into material (id, nombre, descripcion, unidades, prestado, categoria, subcategoria, fotografia) values (7, 'It', 'Acetaminophen', 3, true, 'Health Mart Junior Rapid Melts', 'Mckesson', '#b2a879');
insert into material (id, nombre, descripcion, unidades, prestado, categoria, subcategoria, fotografia) values (8, 'Job', 'ALCOHOL', 1, false, 'Lemon Verbena Waterless Hand Wash', 'Mangiacotti Floral LLC', '#f2f22c');
insert into material (id, nombre, descripcion, unidades, prestado, categoria, subcategoria, fotografia) values (9, 'Veribet', 'Black Walnut', 4, false, 'Black Walnut', 'Antigen Laboratories, Inc.', '#4cd2a5');
insert into material (id, nombre, descripcion, unidades, prestado, categoria, subcategoria, fotografia) values (10, 'Tempsoft', 'zaleplon', 19, false, 'Zaleplon', 'Upsher-Smith Laboratories, Inc.', '#30f6bf');

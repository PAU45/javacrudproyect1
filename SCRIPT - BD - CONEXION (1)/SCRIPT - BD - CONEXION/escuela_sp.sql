CREATE PROCEDURE sp_ins_curso(in cod CHAR(3),in nom VARCHAR(50), in cre int(2))
insert into curso values(cod,nom,cre);

CREATE PROCEDURE sp_upd_curso(in cod CHAR(3),in nom VARCHAR(50), in cre int(2))
update curso set vchCurNombre=nom, intCurCreditos=cre where chrCurCodigo=cod;

CREATE PROCEDURE sp_del_curso(in cod CHAR(3))
delete from curso where chrCurCodigo=cod;

CREATE PROCEDURE sp_findAll_curso()
select * from curso;

CREATE PROCEDURE sp_find_curso(in cod CHAR(3))
select * from curso where chrCurCodigo=cod;

CREATE PROCEDURE sp_login(in user CHAR(10),in pass CHAR(10))
select * from administrador where chrAdmLogin=user and chrAdmPassword=pass;
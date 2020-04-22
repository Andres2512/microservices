INSERT INTO users (username,password,enabled,name,surname,email) values ('Andres','12345',true ,'Andres','Smith','andresSmith@gmail.com');
INSERT INTO users (username,password,enabled,name,surname,email) values ('Harold','12345',true,'Harold','Horta','hhorta@gmail.com');

insert into roles (description)values ('ROLE_USER');
insert into roles (description)values ('ROLE_ADMIN');

insert into user_to_role (id_user,id_role) values (1,1);

insert into user_to_role (id_user,id_role) values (2,2);

insert into user_to_role (id_user,id_role) values (2,1);
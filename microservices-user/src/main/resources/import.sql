INSERT INTO users (username,password,enabled,name,surname,email) values ('Andres','$2a$10$mVCHqwggc5967Sw4A1RPK.aTImFYYb7pKDj0P3SVENDP7Uxjw.wim',true ,'Andres','Smith','andresSmith@gmail.com');
INSERT INTO users (username,password,enabled,name,surname,email) values ('Harold','$2a$10$ebs7U4hl3X9ZUGbD4R1hIunuHvZ93ikhwTbDmEjCLW3FUbL8u4qHC',true,'Harold','Horta','hhorta@gmail.com');

insert into roles (description)values ('ROLE_USER');
insert into roles (description)values ('ROLE_ADMIN');

insert into user_to_role (id_user,id_role) values (1,1);

insert into user_to_role (id_user,id_role) values (2,2);

insert into user_to_role (id_user,id_role) values (2,1);
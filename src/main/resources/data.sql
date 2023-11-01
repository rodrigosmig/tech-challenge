insert into usuarios (id, cpf, email, nome, senha)
values (1, '01166709400', 'thomasrabelo@hotmail.com', 'Thomas Rabelo',
        '$2a$12$4a49YEH3iRcVTCvrBb4/auZBkCBhmID3skzpsWp3ypnWlPEYGMmWi');

insert into papeis (id, nome)
values (1, 'ADMIN');

insert into usuario_papeis (usuario_id, papel_id)
values (1, 1);

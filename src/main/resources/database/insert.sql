insert into usuario(id, nome, nome_social, cpf, email, telefone, pontos_troca, senha) values
(default, 'Renato Gomes', 'Renato', '016.648.658-89', 'renato@email.com', '(71)98785-9628', 50, '12345'),
(default, 'Yla Buri', 'Yla', '613.697.465-45', 'yla@email.com', '(71)98956-6122', 50, '12345');

insert into endereco(id, cep, cidade, bairro, rua, numero, id_usuario) values
(default, '46589-000', 'Camaçari', 'Monte Gordo', 'Rua da floresta', 'S/N', 1),
(default, '42658-310', 'Simões Filho', 'Bairro', 'Rua da pedras', '0697', 2);

insert into genero(id, nome) values
(default, 'Romance'),
(default, 'Ação');


insert into livro(id, titulo, autor, sinopse, detalhes, foto_livro, id_genero, id_usuario) values
(default, 'A seleção', 'Kiera Cass', 'Muitas garotas sonham em ser princesas, mas este não é o caso de America Singer...', 'Livro usado em perfeito estado', 'foto.jpeg', 1, 1),
(default, 'O ladrão de raios', 'Rick Riordan', 'A vida do adolescente Percy Jackson...', 'Livro em bom estado, com algumas marcas de uso', 'foto.jpeg', 2, 2);

insert into notificacao(id, id_livro_desejado, id_usuario_solicitante, status) values
(default, 2, 1, 'concluida'),
(default, 1, 2, 'concluida');

insert into livro_usuario(id, id_livro, id_notificacao) values
(default, 2, 1),
(default, 1, 2);

insert into troca(id, data, localidade, status) values
(default, '2020-05-20', 'Esta��o da Lapa', 'Aberta'),
(default, '2020-04-26', 'Shopping Paralela', 'Fechado');

insert into livro_troca(id, id_livro, id_troca) values
(default, 1, 2),
(default, 2, 1);


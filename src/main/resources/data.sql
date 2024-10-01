truncate tb_role cascade;
truncate tb_company cascade;
truncate tb_competence cascade;
truncate tb_student cascade;
truncate tb_student_competence_skill cascade;
truncate tb_internship_offer cascade;
truncate tb_internship_offer_necessary_skill cascade;
truncate tb_internship_offer_desirable_skill cascade;
truncate tb_Candidature cascade;


CREATE TABLE spring_session
(
    id                    VARCHAR(64)  NOT NULL,
    session_id            VARCHAR(255) NOT NULL,
    creation_time         BIGINT       NOT NULL,
    last_access_time      BIGINT       NOT NULL,
    max_inactive_interval INTEGER      NOT NULL,
    expiry_time           BIGINT       NOT NULL,
    principal_name        VARCHAR(255),
    unique (session_id),
    primary key (id)
);

alter table spring_session
    rename id to primary_id;

CREATE TABLE spring_session_attributes
(
    session_primary_id VARCHAR(64)  NOT NULL,
    attribute_name     VARCHAR(255) NOT NULL,
    attribute_bytes    BYTEA        NOT NULL,
    CONSTRAINT unique_attribute UNIQUE (session_primary_id, attribute_name)
);

-- Roles
INSERT INTO tb_role (id, name)
VALUES (1, 'ROLE_ADMIN');

INSERT INTO tb_role (id, name)
VALUES (2, 'ROLE_COORDINATOR');

INSERT INTO tb_role (id, name)
VALUES (3, 'ROLE_STUDENT');

INSERT INTO tb_role (id, name)
VALUES (4, 'ROLE_COMPANY');

-- Users
INSERT INTO tb_user (username, email, password, enabled, role_id)
VALUES ('admin', 'admin@email.com',
        '$2a$10$XhWSdZtXKkYIIMLc.2cZO.WmlFUi5oQip.n//bKT2P1fQ6AB2q36K', TRUE, 1), -- administrator
       ('Coordenador_Sistemas', 'coordenador.sistemas@gmail.com',
        '$2a$10$gnZeaOFqprG8glYWHso/sujuNhqLAbfj6fTaYjO0dH/6GsXhdnrbG', TRUE, 2), -- Coordenador123
       ('Tech_Innovators', 'Tech_Innovators@gmail.com', '$2a$10$uHIAJXUDN44JaPOKKg/c/OpoQqeJI9GfzJu3A1IK.PdAjixhxvrwK',
        TRUE, 4),                                                                 -- TechInno123
       ('NextGen_Solutions', 'NextGen_Solutions@gmail.com',
        '$2a$10$od7ajpzIz3dgac7NKcORHORCEQ6o5bnje4r1N/NmAXFfIxhmfMHkK', TRUE, 4), -- NextGen123
       ('CloudWare_gmail', 'CloudWare_gmail@gmail.com', '$2a$10$vbLDUooNrE/hbV1Dt360yeEplOduJWnxBWnIOpU0KX6FJ8aIvStNC',
        TRUE, 4),                                                                 -- Cloud123
       ('DataStream_Labs', 'DataStream_Labs@gmail.com', '$2a$10$jnXY3rWttAsJVEafzqJjj.VoHSePnYabrxnnmc7xgJs7wKXK5evXq',
        TRUE, 4),                                                                 -- DataStream123
       ('SoftTech_Corp', 'SoftTech_Corp@gmail.com', '$2a$10$VBPI8gNoE7/BPokpwkzZVucL/o3.ZSYGK8SpT0tmmH8sPddC/8oJO',
        TRUE, 4),                                                                 -- SoftTech123
       ('CyberSec_Brasil', 'CyberSec_Brasil@gmail.com', '$2a$10$SauEVVmeio4DCmQ8YkDehOZqm/8usg16DV7OMAu9RoAPoOAgutuTm',
        TRUE, 4),                                                                 -- CyberSec123
       ('AI_Innovations', 'AI_Innovations@gmail.com', '$2a$10$Kbd6i3Mv5uuDGHZwDxRmwO83eCqHlNDXEF.RIhj1CcUj5/149gjqe',
        TRUE, 4),                                                                 -- AiInno123
       ('Quantum_Solutions', 'Quantum_Solutions@gmail.com',
        '$2a$10$gpscI7xyrKIzEosUzLyq.ebqoAxAHgSuuGfWS3Beapb.8ZINW2BBe', TRUE, 4), -- Quantum123
       ('DevOps_Masters', 'DevOps_Masters@gmail.com', '$2a$10$uObvYPlV4Y6C4PbQxT4iTu1rrWRHBuW1HxA0UFXp4pLVYpkkoAgDy',
        TRUE, 4),                                                                 -- DevOps123
       ('ByteLab_gmail', 'ByteLab_gmail@gmail.com', '$2a$10$9WwnLqpGm3.35aEShcXj3Oloey6eht4J.QtIjtfeB1r.v43/O6iiC',
        TRUE, 4),                                                                 -- ByteLab123
       ('SmartGrid_Tech', 'SmartGrid_Tech@gmail.com', '$2a$10$vMnPneArlqxPFR6LMeBfa.A2FdrJ0hXlHpYEKbb69zNExbrFYWq5S',
        TRUE, 4),                                                                 -- SmartGrid123
       ('VR_World', 'VR_World@gmail.com', '$2a$10$Fg5TXShVDatdlP.G22I8xuzkfrOaEUksaXQw7Gh.JzPuvx5OCU47S', TRUE,
        4),                                                                       -- VrWorld123
       ('Blockchain_Solutions', 'Blockchain_Solutions@gmail.com',
        '$2a$10$Z9riilTL3MJHmpHxzGVVX.5lh.6AoWwy49whzY2sVmkXSiOoC7xm2', TRUE, 4), -- Blockchain123
       ('RoboTech_gmail', 'RoboTech_gmail@gmail.com', '$2a$10$1/SdmgKNxumms.8IZSf3VOkNO.nGsIwxZc/vHUm0H1HObL.8Ip0/6',
        TRUE, 4),                                                                 -- RoboTech123
       ('Innovatech_gmail', 'Innovatech_gmail@gmail.com',
        '$2a$10$OTyoe2SGoYZ2UKp9IRnXmOgWTaIR5mkYIZJUMfrUEjEzxtraKPvUO', TRUE, 4), -- Innovatech123
       ('WebDev_Solutions', 'WebDev_Solutions@gmail.com',
        '$2a$10$eogzReHN1Y30cS2iPUfze.QqhvtjPEEpnU7J5t2EZypHlmjdrHO5y', TRUE, 4), -- WebDev123
       ('AI_Robotics', 'AI_Robotics@gmail.com', '$2a$10$QNK0HTrforRMrp9nEi1Dz.zC6Vmwm1Nh3P9TCq1xcTRtxoqS39iMG', TRUE,
        4),                                                                       -- AiRob123
       ('GreenTech_gmail', 'GreenTech_gmail@gmail.com', '$2a$10$WYr2oy1GzT5bQDKFkP.V6uV/SHyW6rYXOO/jdjjSyZndeZfcWhXZm',
        TRUE, 4),                                                                 -- GreenTech123
       ('Infinity_Networks', 'Infinity_Networks@gmail.com',
        '$2a$10$fAV2mvnf.aMfLA5fFMuxAek/yIFCEHkqJQJdJsnPk6TCrpTwAWMCi', TRUE, 4), -- Infinity123
       ('CodeCraft_gmail', 'CodeCraft_gmail@gmail.com', '$2a$10$egbukOZ.CoPX/Ct.bKXEqedRP/C0W3CXqdWF6pTkRrZUJMC7U/DpW',
        TRUE, 4),                                                                 -- CodeCraft123
       ('Yuri_Souza', 'Yuri.Souza@gmail.com', '$2a$10$ObL1T37nbXgVsLuVHpW3cOqYuFU5/qBwXL5D5c/eglFJ5skLAHUDe', TRUE,
        3),                                                                       -- YuriSou123
       ('Haniel_Silva', 'Haniel.Silva@gmail.com', '$2a$10$3xiECHobPzw/SEaFFhBNeeZLUevSmU5P1yoorm1z4Gd6.hVZtYbl2', TRUE,
        3),                                                                       -- Haniel123
       ('Marcio_Silva', 'Marcio.Silva@gmail.com', '$2a$10$o9L/Ohxm5RUUVCjr7IECfOi7KC8FBUZ45rSmGSHxaFIS0VrpzJQBC', TRUE,
        3),                                                                       -- Marcio123
       ('Allan_Amancio', 'Allan.Amancio@gmail.com', '$2a$10$ghEOvPkJHOaBHtHIyoDrTezgHP7P19UH/Iah.LZosJYFJAlQbgKQy',
        TRUE, 3),                                                                 -- Allan123
       ('Matheus_Sousa', 'Matheus.Sousa@gmail.com', '$2a$10$gBoI2Cltp1DF15R6aoiRvONTz5cumHJVSZ42j6f/o6uT/DojPpFdy',
        TRUE, 3),                                                                 -- Matheus123
       ('Brian_Trajano', 'Brian.Trajano@gmail.com', '$2a$10$juul4iHtADaj6JqwOspsbueSAtoz0hKbHVGm0BvsW4TetzetxqYTm',
        TRUE, 3),                                                                 -- Brian123
       ('Ian_Mendonca', 'Ian.Mendonca@gmail.com', '$2a$10$BxoDrGW.6L2yunEnmTd79epvRB0i3zSTW8bi0B1twng1WHtmR.k2S', TRUE,
        3),                                                                       -- IanRib123
       ('George_Lima', 'George.Lima@gmail.com', '$2a$10$PVZXD1dU88TUBcr2esHF7ezTnZkDE53KfigYXMMJ8E2DvgfFhJli6', TRUE,
        3),                                                                       -- George123
       ('Marcela_Kramer', 'Marcela.Kramer@gmail.com', '$2a$10$qoL7BFszE6WNZq1zkbuqRONQ93sNyM.gKZ.4M2/xPSDBdB7QkFbmK',
        TRUE, 3),                                                                 -- Marcela123
       ('Renato_Melo', 'Renato.Melo@gmail.com', '$2a$10$hkt0ThrEWwh8sjAdtyiHr.5scllakkwScBWm3nr13YFdmqjVKAweC', TRUE,
        3),                                                                       -- Renato123
       ('Yago_Aguiar', 'Yago.Aguiar@gmail.com', '$2a$10$0rhBwfkg72K.lJp.WKRlKOShpoxOLRXQRCA04.EKn9roWkZxPY2uK', TRUE,
        3),                                                                       -- YagoCes123
       ('Amanda_Araujo', 'Amanda.Araujo@gmail.com', '$2a$10$wDurni5v8Eb6kSVbjGzunuQNi25alwcQFLgzKBPrcf7oijJnVYvhO',
        TRUE, 3),                                                                 -- Amanda123
       ('Olivia_Oliva', 'Olivia.Oliva@gmail.com', '$2a$10$/TrbszOwRYmDArwC4BRAY.qSPqO0KsCew23Kq8hPHKuA/khPzujV6', TRUE,
        3),                                                                       -- Olivia123
       ('Raqueline_Gomes', 'Raqueline.Gomes@gmail.com', '$2a$10$.5JN9xxx7dKcnZnW7gKxeu6fPC.sy1nTT84SEhaIaGC0P.AtM9giS',
        TRUE, 3),                                                                 -- Raqueline123
       ('Geraldo_Neto', 'Geraldo.Neto@gmail.com', '$2a$10$g4sffagWMog65rN0y2o7veP.wtzJCFg9ggmXA.JrdvLtw7oUyZ2dq', TRUE,
        3),                                                                       -- Geraldo123
       ('Juliana_Cavalcante', 'Juliana.Cavalcante@gmail.com',
        '$2a$10$y1nhPjr4hXsncl0pO.ocgOJH3hJgdHErHKJv7AL2VancAL3dwZ0dq', TRUE, 3), -- Juliana123
       ('Pedro_Bravim', 'Pedro.Bravim@gmail.com', '$2a$10$v1FyJ/VynX5o7hnEkmad0eKtECPQLChhN/2Zxk2/sGKeHl5mXKAES', TRUE,
        3),                                                                       -- Pedro123
       ('Juan_Farias', 'Juan.Farias@gmail.com', '$2a$10$wmwE/1bZnvv6Y2KZPhH5eOZi73qLfkOzOeI0zT5bTLEpi5Rkhl2v6', TRUE,
        3),                                                                       -- JuanFar123
       ('Joao_Dias', 'Joao.Dias@gmail.com', '$2a$10$X0ZNf5ucz9SfkMdYdF8nL.5ZSebuU6SThZKVMpOmJCPLQ3eumZF8G', TRUE,
        3),                                                                       -- JoaoDias123
       ('Alic_Andrade', 'Alic.Andrade@gmail.com', '$2a$10$AUH939ZyD0R6bPoviJHnTuld/9Dqz8YFYOW/tzYZln3tlX2nekahC', TRUE,
        3),                                                                       -- AlicAndr123
       ('Luis_Kilmer', 'Luis.Kilmer@gmail.com', '$2a$10$aWtuBwGfb7ZjVZNO.Mi1yeNsV.hdrjDSuHNYE5KzW54xtr5RrWafu', TRUE,
        3),                                                                       -- LuisKill123
       ('Joao_Kleber', 'joao.kleber@gmail.com', '$2a$10$ixAQQUfU0JVReUmKbLrFmOdOd6epBk5bTgFmoMTEwaGzg.wI9KOHe', TRUE,
        3); -- JoaoKleber123

INSERT INTO tb_coordinator (id, username, enrollment, name, course, phone_number)
VALUES (1, 'Coordenador_Sistemas', '20220040009', 'João Silva', 'Sistemas para Internet', '83992384910');


/*
Company
    String fantasyName;
    String cnpj;
    String phoneNumber;
    String personContact;
    String address;
    String principalActivity;
    String urlPage="";
*/
INSERT INTO tb_company (id, username, fantasy_name, cnpj, phone_number, person_contact, address, principal_activity,
                        url_page)
VALUES (1, 'Tech_Innovators', 'Tech Innovators', '12345678000191', '11987654321', 'Lucas Andrade',
        'Av. Paulista, 1234, São Paulo, SP', 'Desenvolvimento de software', 'www.techinnovators.com.br'),
       (2, 'NextGen_Solutions', 'NextGen Solutions', '23456789000182', '21998765432', 'Ana Costa',
        'Rua da Tecnologia, 200, Rio de Janeiro, RJ', 'Consultoria em TI', 'www.nextgensolutions.com.br'),
       (3, 'CloudWare_gmail', 'CloudWare', '34567890000173', '31912345678', 'Felipe Ramos',
        'Rua do Sol, 789, Belo Horizonte, MG', 'Soluções de armazenamento em nuvem', 'www.cloudware.com.br'),
       (4, 'DataStream_Labs', 'DataStream Labs', '45678901000164', '51923456789', 'Mariana Souza',
        'Av. dos Inovadores, 101, Porto Alegre, RS', 'Análise de dados e Big Data', 'www.datastreamlabs.com.br'),
       (5, 'SoftTech_Corp', 'SoftTech Corp', '56789012000155', '85934567890', 'Rafael Martins',
        'Rua da Computação, 321, Fortaleza, CE', 'Desenvolvimento de aplicativos', 'www.softtechcorp.com.br'),
       (6, 'CyberSec_Brasil', 'CyberSec Brasil', '67890123000146', '61945678901', 'Camila Oliveira',
        'Setor de TI, 654, Brasília, DF', 'Segurança cibernética', 'www.cybersecbrasil.com.br'),
       (7, 'AI_Innovations', 'AI Innovations', '78901234000137', '41956789012', 'Eduardo Lima',
        'Rua dos Algoritmos, 123, Curitiba, PR', 'Inteligência artificial e automação', 'www.aiinnovations.com.br'),
       (8, 'Quantum_Solutions', 'Quantum Solutions', '89012345000128', '71967890123', 'Juliana Ferreira',
        'Av. Quântica, 456, Salvador, BA', 'Desenvolvimento de tecnologia quântica', 'www.quantumsolutions.com.br'),
       (9, 'DevOps_Masters', 'DevOps Masters', '90123456000119', '19978901234', 'Thiago Pereira',
        'Rua dos DevOps, 789, Campinas, SP', 'Consultoria e automação DevOps', 'www.devopsmasters.com.br'),
       (10, 'ByteLab_gmail', 'ByteLab', '01234567000109', '27987654321', 'Paula Duarte',
        'Rua dos Códigos, 321, Vitória, ES', 'Laboratório de inovação em software', 'www.bytelab.com.br'),
       (11, 'SmartGrid_Tech', 'SmartGrid Tech', '12345678000210', '31912345678', 'Anderson Moreira',
        'Rua das Energias, 200, Belo Horizonte, MG', 'Tecnologia para redes inteligentes', 'www.smartgridtech.com.br'),
       (12, 'VR_World', 'VR World', '23456789000291', '21998765432', 'Sofia Campos',
        'Rua da Realidade, 400, Rio de Janeiro, RJ', 'Desenvolvimento de soluções em realidade virtual',
        'www.vrworld.com.br'),
       (13, 'Blockchain_Solutions', 'Blockchain Solutions', '34567890000282', '11987654321', 'Gustavo Silva',
        'Rua do Blockchain, 500, São Paulo, SP', 'Soluções em blockchain e criptografia',
        'www.blockchainsolutions.com.br'),
       (14, 'RoboTech_gmail', 'RoboTech', '45678901000273', '51923456789', 'Letícia Barros',
        'Av. Robótica, 600, Porto Alegre, RS', 'Desenvolvimento de robótica e automação', 'www.robotech.com.br'),
       (15, 'Innovatech_gmail', 'Innovatech', '56789012000264', '85934567890', 'Bruno Lima',
        'Rua da Inovação, 700, Fortaleza, CE', 'Inovações em TI e hardware', 'www.innovatech.com.br'),
       (16, 'WebDev_Solutions', 'WebDev Solutions', '67890123000255', '61945678901', 'Fernanda Almeida',
        'Avenida Web, 123, Brasília, DF', 'Desenvolvimento de websites e e-commerce', 'www.webdevsolutions.com.br'),
       (17, 'AI_Robotics', 'AI Robotics', '78901234000246', '41956789012', 'Leonardo Nunes',
        'Rua da Automação, 234, Curitiba, PR', 'Soluções em IA e robótica', 'www.airobotics.com.br'),
       (18, 'GreenTech_gmail', 'GreenTech', '89012345000237', '71967890123', 'Patrícia Dias',
        'Rua Sustentável, 345, Salvador, BA', 'Tecnologia sustentável e energias renováveis', 'www.greentech.com.br'),
       (19, 'Infinity_Networks', 'Infinity Networks', '90123456000228', '19978901234', 'Ricardo Rocha',
        'Av. das Redes, 567, Campinas, SP', 'Soluções de rede e conectividade', 'www.infinitynetworks.com.br'),
       (20, 'CodeCraft_gmail', 'CodeCraft', '01234567000219', '27987654321', 'Carla Teixeira',
        'Rua dos Programadores, 123, Vitória, ES', 'Desenvolvimento de software sob medida', 'www.codecraft.com.br');

/*
Competence
    String description;
*/
INSERT INTO tb_competence (id, description)
VALUES (1, 'Python'),
       (2, 'Java'),
       (3, 'JavaScript'),
       (4, 'TypeScript'),
       (5, 'C++'),
       (6, 'PHP'),
       (7, 'Ruby'),
       (8, 'C#'),
       (9, 'Swift'),
       (10, 'Kotlin'),
       (11, 'Front-end'),
       (12, 'Back-end'),
       (13, 'Full-stack'),
       (14, 'Gestão de Tempo'),
       (15, 'Liderança'),
       (16, 'Trabalho em Equipe '),
       (17, 'Desenvolvimento Ágil'),
       (18, 'Trabalhar Sobre pressão'),
       (19, 'Pensamento Crítico'),
       (20, 'Comunicação'),
       (21, 'Proatividade '),
       (22, 'Flexibilidade e Adaptabilidade a Novas Tecnologias'),
       (23, 'Bancos de Dados Relacionais'),
       (24, 'Bancos de Dados Não Relacionais'),
       (25, 'Negociação e Gerenciamento de Conflitos');
/*
Student
   String phoneNumber;
   String enrollment;
   String name;
   LocalDate birthdate;
   String course
*/
INSERT INTO tb_student (id, username, phone_number, enrollment, name, birthdate, course)
VALUES (1, 'Yuri_Souza', '83991234567', '20180010001',
        'Yuri Adreano Santos de Souza', '1999-05-15', 'Sistemas para Internet'),
       (2, 'Haniel_Silva', '83998901235', '20220010018', 'Haniel Costa Da Silva',
        '2000-01-24 ', 'Sistemas para Internet'),
       (3, 'Marcio_Silva', '83998901235', '20220010023', 'Marcio Jose da Silva',
        '1997-09-03', 'Sistemas para Internet'),
       (4, 'Allan_Amancio', '83992345678', '20190010002', 'Allan Alves Amancio',
        '1998-11-23', 'Sistemas para Internet'),
       (5, 'Matheus_Sousa', '83990123458', '20190010030',
        'Matheus Pereira de Sousa', '1998-01-17', 'Sistemas para Internet'),
       (6, 'Brian_Trajano', '83991234570', '20200010031',
        'Brian Rafael Azevedo Trajano', '1997-11-08', 'Sistemas para Internet'),
       (7, 'Ian_Mendonca', '83992345671', '20210010032', 'Ian Ribeiro de Mendonça',
        '1999-05-25', 'Sistemas para Internet'),
       (8, 'George_Lima', '83995678901', '20180010005', 'George Barbosa de Lima',
        '1999-09-18', 'Sistemas para Internet'),
       (9, 'Marcela_Kramer', '83996789012', '20190010006',
        'Marcela Barreto de Oliveira Kramer', '1998-01-12', 'Sistemas para Internet'),
       (10, 'Renato_Melo', '83998901234', '20210010008', 'Renato Bezerra de Melo',
        '1998-07-20', 'Sistemas para Internet'),
       (11, 'Yago_Aguiar', '83999012345', '20220010009',
        'Yago César do Nascimento Aguiar', '1997-02-14', 'Sistemas para Internet'),
       (12, 'Amanda_Araujo', '83990123456', '20190010010',
        'Amanda Cruz de Araujo', '1999-08-30', 'Sistemas para Internet'),
       (13, 'Olivia_Oliva', '83991234568', '20200010011',
        'Olivia da Costa Oliva', '1998-04-09', 'Sistemas para Internet'),
       (14, 'Raqueline_Gomes', '83992345679', '20210010012',
        'Raqueline da Silva Gomes', '1997-10-17', 'Sistemas para Internet'),
       (15, 'Geraldo_Neto', '83993456790', '20220010013',
        'Geraldo da Silva Neto', '1999-03-22', 'Sistemas para Internet'),
       (16, 'Juliana_Cavalcante', '83996789013', '20200010016',
        'Juliana Ferreira Cavalcante', '1997-12-01', 'Sistemas para Internet'),
       (17, 'Pedro_Bravim', '83997890124', '20210010017', 'Pedro Gimenes Bravim',
        '1999-07-25', 'Sistemas para Internet'),
       (18, 'Juan_Farias', '83999012346', '20180010019', 'Juan Leite Farias',
        '1999-05-16', 'Sistemas para Internet'),
       (19, 'Joao_Dias', '83997890126', '20210010037', 'João Victor Batista Dias',
        '1998-07-03', 'Sistemas para Internet'),
       (20, 'Alic_Andrade', '83998901237', '20220010038',
        'Alic Victor Santos de Andrade', '1997-09-22', 'Sistemas para Internet'),
       (21, 'Luis_Kilmer', '839989039423', '20220010006',
        'Luis Kilmer Bernardo da Silva', '2003-12-03', 'Sistemas para Internet'),
       (22, 'Joao_Kleber', '839989039424', '20220110007',
        'Joao Kleber da Silva', '2002-12-03', 'Engenharia de Software');
/*
tb_student_competence_skill
student competence (N..N)
    Long student_id;
    Long competence_id;
*/
INSERT INTO tb_student_competence_skill(student_id, competence_id)
VALUES (1, 1),
       (1, 5),
       (1, 8),
       (2, 3),
       (2, 7),
       (2, 12),
       (2, 18),
       (3, 2),
       (3, 6),
       (3, 11),
       (4, 4),
       (4, 9),
       (4, 14),
       (4, 19),
       (5, 7),
       (5, 10),
       (5, 15),
       (6, 1),
       (6, 8),
       (6, 13),
       (6, 16),
       (7, 3),
       (7, 6),
       (7, 12),
       (7, 17),
       (8, 2),
       (8, 5),
       (8, 11),
       (8, 20),
       (9, 4),
       (9, 9),
       (9, 14),
       (10, 7),
       (10, 13),
       (10, 18),
       (10, 23),
       (11, 1),
       (11, 6),
       (11, 8),
       (11, 19),
       (12, 2),
       (12, 10),
       (12, 15),
       (12, 22),
       (13, 3),
       (13, 7),
       (13, 11),
       (13, 16),
       (14, 4),
       (14, 8),
       (14, 12),
       (14, 20),
       (15, 5),
       (15, 9),
       (15, 13),
       (15, 22),
       (16, 6),
       (16, 10),
       (16, 14),
       (16, 18),
       (17, 1),
       (17, 7),
       (17, 12),
       (17, 15),
       (18, 3),
       (18, 8),
       (18, 13),
       (18, 19),
       (19, 2),
       (19, 4),
       (19, 7),
       (19, 20),
       (20, 5),
       (20, 8),
       (20, 12),
       (20, 22);
/* 
tb_internship_offer
    String principalActivity
    Integer weeklyWorkload
    Double remunerationValue
    Double transportVoucher
    String prerequisites
    Long companyResponsible
    OfferStatus status ENUM Type

 */
INSERT INTO tb_internship_offer (principal_activity, weekly_workload, remuneration_value, transport_voucher,
                                 prerequisites, company_id, status)
VALUES ('Desenvolvimento de Software', 20, 1500.00, 200.00, 'Conhecimento em Java e SQL', 1, 'ABERTA'),
       ('Marketing Digital', 25, 1200.00, 0.00, 'Experiência com redes sociais', 2, 'ABERTA'),
       ('Suporte Técnico', 30, 0.00, 100.00, 'Boa comunicação e conhecimento básico de TI', 3, 'ABERTA'),
       ('Gestão de Projetos', 20, 1600.00, 250.00, 'Formação em administração', 4, 'CANCELADA'),
       ('Design Gráfico', 15, 1400.00, 100.00, 'Experiência com Adobe Creative Suite', 5, 'ABERTA'),
       ('Consultoria em TI', 20, 1800.00, 200.00, 'Certificação em ITIL', 6, 'ABERTA'),
       ('Desenvolvimento Web', 25, 1700.00, 180.00, 'Conhecimento em HTML, CSS e JavaScript', 7, 'ABERTA'),
       ('Administração de Redes', 30, 1600.00, 220.00, 'Certificação CCNA', 8, 'CANCELADA'),
       ('Análise de Dados', 25, 1500.00, 190.00, 'Experiência com ferramentas de BI', 9, 'ABERTA'),
       ('Engenharia de Software', 20, 2000.00, 250.00, 'Formação em Engenharia da Computação', 10, 'ABERTA'),
       ('Recursos Humanos', 20, 1400.00, 120.00, 'Experiência em recrutamento e seleção', 11, 'ABERTA'),
       ('Produção de Conteúdo', 15, 1200.00, 0.00, 'Habilidades de redação e SEO', 12, 'ABERTA'),
       ('Financeiro', 30, 0.00, 150.00, 'Conhecimento em Excel e finanças', 13, 'CANCELADA'),
       ('Vendas', 20, 1300.00, 80.00, 'Experiência com vendas e atendimento ao cliente', 14, 'ABERTA'),
       ('Pesquisa de Mercado', 25, 1400.00, 100.00, 'Habilidades analíticas e conhecimento em pesquisa', 15,
        'FECHADA'),
       ('Desenvolvimento Mobile', 30, 1700.00, 200.00, 'Experiência com Android e iOS', 16, 'ABERTA'),
       ('Redação Publicitária', 15, 1250.00, 120.00, 'Experiência com copywriting e campanhas publicitárias', 17,
        'ABERTA'),
       ('Análise de Sistemas', 20, 1500.00, 180.00, 'Conhecimento em UML e análise de requisitos', 18, 'CANCELADA'),
       ('Gestão de Eventos', 25, 1400.00, 150.00, 'Experiência em organização de eventos', 19, 'ABERTA'),
       ('Qualidade de Software', 20, 1550.00, 0.00, 'Certificação em testes de software', 20, 'ABERTA'),
       ('Desenvolvimento Backend', 30, 1800.00, 220.00, 'Experiência com Node.js e Python', 1, 'CANCELADA'),
       ('Suporte ao Cliente', 20, 1300.00, 100.00, 'Boa comunicação e empatia', 2, 'ABERTA'),
       ('Administração de Banco de Dados', 25, 1600.00, 0.00, 'Experiência com SQL e administração de DB', 3,
        'FECHADA'),
       ('Design de Interação', 20, 1400.00, 150.00, 'Conhecimento em UX/UI', 4, 'ABERTA'),
       ('Marketing de Conteúdo', 25, 1500.00, 160.00,
        'Experiência com criação de conteúdo e estratégias de marketing', 5, 'CANCELADA'),
       ('Segurança da Informação', 30, 2000.00, 250.00, 'Certificação em segurança da informação', 6, 'ABERTA'),
       ('Automação de Processos', 20, 1700.00, 220.00, 'Experiência com ferramentas de automação', 7, 'FECHADA'),
       ('Gestão de TI', 30, 1800.00, 200.00, 'Experiência em gerenciamento de equipes de TI', 8, 'ABERTA'),
       ('Controle de Qualidade', 25, 1500.00, 180.00, 'Conhecimento em técnicas de controle de qualidade', 9,
        'CANCELADA'),
       ('Engenharia de Dados', 20, 1900.00, 220.00, 'Experiência com big data e ferramentas de engenharia', 10,
        'ABERTA');

/* 
tb_internship_offer_necessary_skill
tb_internship_offer competence (0-N ..0-N)
    Long internship_offer_id
    Long competence_id
*/
INSERT INTO tb_internship_offer_necessary_skill (internship_offer_id, competence_id)
VALUES (1, 1),
       (1, 2),
       (1, 5),
       (1, 8),
       (1, 12),
       (2, 3),
       (2, 6),
       (2, 7),
       (2, 11),
       (2, 15),
       (3, 1),
       (3, 4),
       (3, 10),
       (3, 14),
       (3, 16),
       (4, 2),
       (4, 8),
       (4, 13),
       (4, 17),
       (4, 18),
       (5, 1),
       (5, 7),
       (5, 9),
       (5, 12),
       (5, 19),
       (6, 3),
       (6, 5),
       (6, 11),
       (6, 15),
       (6, 20),
       (7, 2),
       (7, 4),
       (7, 6),
       (7, 8),
       (7, 14),
       (8, 1),
       (8, 7),
       (8, 9),
       (8, 12),
       (8, 21),
       (9, 3),
       (9, 6),
       (9, 10),
       (9, 13),
       (9, 22),
       (10, 2),
       (10, 4),
       (10, 8),
       (10, 16),
       (10, 23),
       (11, 1),
       (11, 7),
       (11, 12),
       (11, 19),
       (11, 24),
       (12, 3),
       (12, 5),
       (12, 11),
       (12, 14),
       (12, 17),
       (13, 2),
       (13, 6),
       (13, 8),
       (13, 13),
       (13, 20),
       (14, 1),
       (14, 4),
       (14, 10),
       (14, 15),
       (14, 23),
       (15, 2),
       (15, 5),
       (15, 9),
       (15, 16),
       (15, 22),
       (16, 1),
       (16, 6),
       (16, 8),
       (16, 14),
       (16, 24),
       (17, 3),
       (17, 5),
       (17, 10),
       (17, 12),
       (17, 18),
       (18, 2),
       (18, 4),
       (18, 9),
       (18, 13),
       (18, 21),
       (19, 1),
       (19, 6),
       (19, 7),
       (19, 16),
       (19, 23),
       (20, 3),
       (20, 5),
       (20, 11),
       (20, 14),
       (20, 19),
       (21, 2),
       (21, 7),
       (21, 12),
       (21, 13),
       (21, 24),
       (22, 4),
       (22, 6),
       (22, 10),
       (22, 15),
       (22, 22),
       (23, 1),
       (23, 8),
       (23, 13),
       (23, 17),
       (23, 20),
       (24, 3),
       (24, 5),
       (24, 9),
       (24, 14),
       (24, 21),
       (25, 2),
       (25, 4),
       (25, 6),
       (25, 16),
       (25, 18),
       (26, 1),
       (26, 5),
       (26, 10),
       (26, 13),
       (26, 23),
       (27, 2),
       (27, 4),
       (27, 11),
       (27, 14),
       (27, 19),
       (28, 1),
       (28, 7),
       (28, 12),
       (28, 15),
       (28, 24),
       (29, 3),
       (29, 6),
       (29, 9),
       (29, 13),
       (29, 22),
       (30, 2),
       (30, 5),
       (30, 8),
       (30, 16),
       (30, 18);
/*
tb_internship_offer_desirable_skill
tb_internship_offer competence (0-N ..0-N)
   Long internship_offer_id
   Long competence_id
*/
INSERT INTO tb_internship_offer_desirable_skill (internship_offer_id, competence_id)
VALUES (1, 3),
       (1, 6),
       (1, 11),
       (1, 15),
       (1, 17),

       (2, 1),
       (2, 4),
       (2, 8),
       (2, 12),
       (2, 18),

       (3, 2),
       (3, 5),
       (3, 7),
       (3, 12),
       (3, 19),

       (5, 2),
       (5, 6),
       (5, 8),
       (5, 11),
       (5, 18),

       (6, 7),
       (6, 9),
       (6, 12),

       (7, 3),
       (7, 5),
       (7, 9),
       (7, 12),
       (7, 20),

       (8, 4),
       (8, 6),
       (8, 10),
       (8, 13),
       (8, 24),

       (9, 2),
       (9, 7),
       (9, 11),
       (9, 16),
       (9, 19),

       (10, 3),
       (10, 6),
       (10, 11),
       (11, 2),
       (11, 4),
       (11, 8),

       (12, 1),
       (12, 4),
       (12, 6),
       (12, 10),
       (12, 15),

       (13, 3),
       (13, 8),
       (13, 11),
       (13, 14),

       (15, 3),
       (15, 7),
       (15, 10),
       (15, 14),
       (15, 21),

       (16, 2),
       (16, 5),
       (16, 8),
       (16, 12),

       (18, 2),
       (18, 6),
       (18, 12),

       (19, 3),
       (19, 5),
       (19, 9),
       (19, 15),

       (20, 4),
       (20, 7),
       (20, 11),
       (20, 16),

       (21, 1),
       (21, 6),
       (21, 8),
       (21, 12),

       (22, 2),
       (22, 4),
       (22, 8),
       (23, 1),
       (23, 3),

       (23, 6),
       (23, 9),

       (24, 2),
       (24, 5),
       (24, 8),

       (25, 3),
       (25, 6),

       (26, 2),
       (26, 4),
       (26, 8),

       (27, 1),
       (27, 6),
       (27, 8),

       (28, 3),
       (28, 7),
       (28, 12),

       (29, 2),
       (29, 5),
       (29, 8),

       (30, 1),
       (30, 4),
       (30, 7);

/* Candidature
    Long student reference Student 1 to 20 ids
    InternshipOffer internshipOffer
    CandidatureStatus status
    String message
    LocalDateTime createdDate
*/INSERT INTO tb_Candidature (id, student_id, internship_offer_id, status, message, created_date)
  VALUES
      -- Oferta 1
      (1, 2, 1, 'PENDENTE', 'Gostaria de aplicar para essa vaga.', '2024-09-02 11:30:00'),
      (2, 3, 1, 'PENDENTE', 'Acredito que meu perfil se encaixa.', '2024-09-03 14:00:00'),
      (3, 4, 1, 'PENDENTE', 'Estou muito interessado!', '2024-09-04 09:15:00'),
      (4, 5, 1, 'PENDENTE', 'Em busca de novas oportunidades.', '2024-09-05 16:45:00'),
      (5, 6, 1, 'PENDENTE', 'Espero ser um bom candidato.', '2024-09-06 12:00:00'),
      (6, 7, 1, 'PENDENTE', 'Acho que posso contribuir muito.', '2024-09-07 10:30:00'),
      (7, 8, 1, 'PENDENTE', 'Motivado para o desafio.', '2024-09-08 11:00:00'),
      (8, 9, 1, 'PENDENTE', 'Tenho experiência relevante.', '2024-09-09 13:30:00'),
      (9, 10, 1, 'PENDENTE', 'Estou pronto para a entrevista.', '2024-09-10 15:00:00'),
      (10, 11, 1, 'PENDENTE', 'Estou pronto para a entrevista.', '2024-09-10 15:00:00'),
      (11, 12, 1, 'PENDENTE', 'Estou pronto para a entrevista.', '2024-09-10 15:00:00'),
      (12, 13, 1, 'PENDENTE', 'Estou pronto para a entrevista.', '2024-09-10 15:00:00'),
      (13, 14, 1, 'PENDENTE', 'Estou pronto para a entrevista.', '2024-09-10 15:00:00'),
      -- Oferta 2
      (14, 11, 2, 'PENDENTE', 'Muito interessado na vaga.', '2024-09-01 08:30:00'),
      (15, 12, 2, 'PENDENTE', 'Gostaria de saber mais sobre a vaga.', '2024-09-02 09:45:00'),
      (16, 13, 2, 'PENDENTE', 'Experiência relevante para a posição.', '2024-09-03 14:30:00'),
      (17, 14, 2, 'PENDENTE', 'Pronto para novos desafios.', '2024-09-04 16:00:00'),
      (18, 15, 2, 'PENDENTE', 'Confiante no meu perfil.', '2024-09-05 10:00:00'),
      -- Oferta 3
      --Vazia
      -- Oferta 4
      (19, 10, 4, 'PENDENTE', 'Estou interessado.', '2024-09-01 14:00:00'),
      (20, 11, 4, 'PENDENTE', 'Gostaria de saber mais.', '2024-09-02 16:00:00'),
      (21, 12, 4, 'PENDENTE', 'Pronto para a vaga.', '2024-09-03 18:00:00'),
      -- Oferta 5
      --Vazia
      -- Oferta 6
      --Vazia
      -- Oferta 7
      (23, 17, 7, 'PENDENTE', 'Estou interessado.', '2024-09-01 11:00:00'),
      (24, 18, 7, 'PENDENTE', 'Experiência relevante.', '2024-09-02 13:00:00'),
      -- Oferta 8
      --Vazia
      -- Oferta 9
      (25, 19, 9, 'PENDENTE', 'Acho que me encaixo na vaga.', '2024-09-01 16:00:00'),
      (26, 20, 9, 'PENDENTE', 'Pronto para a entrevista.', '2024-09-02 18:00:00'),
      -- Oferta 10
      (27, 3, 10, 'PENDENTE', 'Empolgado para a posição.', '2024-09-01 09:30:00'),
      (28, 4, 10, 'PENDENTE', 'Tenho o perfil desejado.', '2024-09-02 11:00:00'),
      (29, 5, 10, 'PENDENTE', 'Espero ser um bom candidato.', '2024-09-03 13:00:00'),
      -- Oferta 11
      (30, 6, 11, 'PENDENTE', 'Interessado na vaga.', '2024-09-01 10:00:00'),
      (31, 7, 11, 'PENDENTE', 'Gostaria de aplicar para essa vaga.', '2024-09-02 11:30:00'),
      (32, 8, 11, 'PENDENTE', 'Acredito que meu perfil se encaixa.', '2024-09-03 14:00:00'),
      (33, 9, 11, 'PENDENTE', 'Estou muito interessado!', '2024-09-04 09:15:00'),
      -- Oferta 12
      (34, 10, 12, 'PENDENTE', 'Em busca de novas oportunidades.', '2024-09-01 16:45:00'),
      (35, 11, 12, 'PENDENTE', 'Espero ser um bom candidato.', '2024-09-02 12:00:00'),
      (36, 12, 12, 'PENDENTE', 'Acho que posso contribuir muito.', '2024-09-03 10:30:00'),
      -- Oferta 13
      (37, 13, 13, 'PENDENTE', 'Motivado para o desafio.', '2024-09-01 11:00:00'),
      (38, 14, 13, 'PENDENTE', 'Tenho experiência relevante.', '2024-09-02 13:30:00'),
      -- Oferta 14
      (39, 15, 14, 'PENDENTE', 'Estou pronto para a entrevista.', '2024-09-01 15:00:00'),
      (40, 16, 14, 'PENDENTE', 'Acho que me encaixo bem.', '2024-09-02 17:00:00'),
      (41, 17, 14, 'PENDENTE', 'Experiência relevante para a posição.', '2024-09-03 14:30:00'),
      (42, 18, 14, 'PENDENTE', 'Pronto para novos desafios.', '2024-09-04 16:00:00'),
      (43, 19, 14, 'PENDENTE', 'Confiante no meu perfil.', '2024-09-05 10:00:00'),
      -- Oferta 15
      (44, 20, 15, 'PENDENTE', 'Muito interessado na vaga.', '2024-09-01 08:30:00'),
      (45, 21, 15, 'PENDENTE', 'Gostaria de saber mais sobre a vaga.', '2024-09-02 09:45:00'),
      (46, 21, 15, 'PENDENTE', 'Experiência relevante para a posição.', '2024-09-03 14:30:00'),
      (47, 17, 15, 'PENDENTE', 'Pronto para novos desafios.', '2024-09-04 16:00:00'),
      -- Oferta 16
      (48, 18, 16, 'PENDENTE', 'Confiante no meu perfil.', '2024-09-01 10:00:00'),
      (49, 19, 16, 'PENDENTE', 'Tenho as habilidades necessárias.', '2024-09-02 12:00:00'),
      (50, 20, 16, 'PENDENTE', 'Espero ser considerado.', '2024-09-03 14:00:00'),
      -- Oferta 17
      (51, 21, 17, 'PENDENTE', 'Estou empolgado para a oportunidade.', '2024-09-01 11:00:00'),
      (52, 17, 17, 'PENDENTE', 'Tenho as habilidades certas.', '2024-09-02 12:30:00'),
      (53, 18, 17, 'PENDENTE', 'Pronto para contribuir com a equipe.', '2024-09-03 14:00:00'),
      -- Oferta 18
      (54, 19, 18, 'PENDENTE', 'Muito interessado na vaga.', '2024-09-01 15:30:00'),
      (55, 20, 18, 'PENDENTE', 'Gostaria de saber mais.', '2024-09-02 17:00:00');
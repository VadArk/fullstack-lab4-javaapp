create table users_words (
                             id integer primary key auto_increment,
                             username varchar(30),
                             email varchar(30),
                             favourite_word varchar(50)
);

insert into users_words values (1, 'user1', 'email1@email.com', 'email');
insert into users_words values (2, 'user2', 'email2@email.com', 'email');
insert into users_words values (3, 'user3', 'email3@email.com', 'dog');
insert into users_words values (4, 'user4', 'email4@email.com', 'dog');
insert into users_words values (5, 'user5', 'email5@email.com', 'dog');
insert into users_words values (6, 'user6', 'email6@email.com', 'haaa-haaa');

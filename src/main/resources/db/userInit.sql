SET foreign_key_checks = 0;
drop table if exists User;
SET foreign_key_checks = 1;
create table User (
    id bigint not null auto_increment,
    createdAt datetime(6),
    modifiedAt datetime(6),
    password varchar(255) not null,
    username varchar(100) not null,
    primary key (id));
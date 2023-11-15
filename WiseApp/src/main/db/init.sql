USE wisedb;

create table wise(
    id int unsigned not null primary key,
    createDate datetime not null,
    content varchar(100) not null,
    author varchar(100) not null
    );
create database AgendaEMail;
use AgendaEMail;

create table adrese(
  id int primary key auto_increment not null,
  nume char(20) not null,
  email char(30) not null
);

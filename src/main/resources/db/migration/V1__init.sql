-- starting the sequence with 101 to insert some sample data with primary key values
create sequence user_id_seq start with 101 increment by 50;

create table users
(
  id         bigint       not null default nextval('user_id_seq'),
  name       varchar(100) not null,
  email      varchar(100) not null unique,
  created_at timestamp    not null default now(),
  updated_at timestamp,
  primary key (id)
);

create sequence bookmark_id_seq start with 101 increment by 50;

create table bookmarks
(
  id         bigint       not null default nextval('bookmark_id_seq'),
  title      varchar(200) not null,
  url        varchar(500) not null,
  user_id    bigint       not null references users (id),
  created_at timestamp    not null default now(),
  updated_at timestamp,
  primary key (id)
);

insert into users(id, name, email) values
(1, 'Siva', 'siva@gmail.com'),
(2, 'John', 'john@gmail.com');

insert into bookmarks(id, title, url, user_id, created_at) values
(1, 'SivaLabs Blog', 'https://sivalabs.in', 1, '2020-09-10'),
(2, 'Spring Blog', 'https://spring.io/blog', 1, '2021-10-10'),
(3, 'JetBrains Blog', 'https://blog.jetbrains.com', 1, '2021-06-26'),
(4, 'Testcontainers', 'https://testcontainers.com', 2, '2023-12-05'),
(5, 'Spring Cloud AWS', 'https://awspring.io', 2, '2024-08-15');
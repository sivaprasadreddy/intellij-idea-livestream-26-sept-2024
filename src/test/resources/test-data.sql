delete from bookmarks;
delete from users;

insert into users(id, name, email) values
(1, 'Siva', 'siva@gmail.com'),
(2, 'John', 'john@gmail.com');

insert into bookmarks(id, title, url, user_id, created_at) values
(1, 'SivaLabs Blog', 'https://sivalabs.in', 1, '2020-09-10'),
(2, 'Spring Blog', 'https://spring.io/blog', 1, '2021-10-10'),
(3, 'JetBrains Blog', 'https://blog.jetbrains.com', 1, '2021-06-26'),
(4, 'Testcontainers', 'https://testcontainers.com', 2, '2023-12-05'),
(5, 'Spring Cloud AWS', 'https://awspring.io', 2, '2024-08-15');

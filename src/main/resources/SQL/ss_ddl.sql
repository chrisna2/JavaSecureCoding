use hr;
#drop table authorities;
#drop table users;
CREATE TABLE users(
   username varchar(50) not null primary key,
   password varchar(50) not null,
   enabled boolean not null);

CREATE TABLE authorities (
  username varchar(50) not null,
  authority varchar(50) not null,
  constraint fk_authorities_users foreign key(username) references users(username));
  create unique index ix_auth_username on authorities (username,authority);


INSERT INTO users (username, password, enabled)
VALUES
  ('user1', 'passwd', TRUE),
  ('admin1', 'admin', TRUE),
  ('admin2', 'admin', TRUE);

INSERT INTO authorities (username, authority)
VALUES
 ('user1', 'ROLE_USER'),
 ('admin1', 'ROLE_ADMIN'),
 ('admin2', 'ROLE_ADMIN');
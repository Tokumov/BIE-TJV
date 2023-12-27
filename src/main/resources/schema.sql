create table if not exists users(
    username varchar(50) not null primary key,
    password varchar(500) not null,
    enabled boolean not null,
    real_name varchar(20)
    );

create table if not exists authorities (
    username varchar(50) not null,
    authority varchar(50) not null,
    constraint fk_authorities_users foreign key(username) references users(username)
    );
create unique index if not exists ix_auth_username on authorities (username,authority);

create table if not exists clients(
                                      id bigserial primary key,
                                      name varchar (256) not null,
    surname varchar(256) not null

    );

create table if not exists orders(
                                     id_orders bigserial primary key ,
                                     total_cost bigint,
                                     client_id_orders bigint references clients
);
create table if not exists menu(
                                   id_menu bigserial primary key,
                                   name_menu varchar(256),
    price bigint

    );
create table if not exists menu_and_orders(
                                              orders_id_menu bigint references orders,
                                              menu_id_orders bigint references menu,
                                              primary key(orders_id_menu,menu_id_orders)
    );
create table if not exists booking(
                                      id_booking bigserial ,
                                      time_of_booking time,
                                      date_of_booking date,
                                      client_id_booking bigint references clients,
                                      primary key(id_booking,client_id_booking)
    );
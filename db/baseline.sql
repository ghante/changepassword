CREATE TABLE item
(
       item_id SERIAL PRIMARY KEY,
       description character varying(255) NOT NULL,
       name character varying(255) NOT NULL,
       price numeric(19,2) NOT NULL,
       type character varying(255) NOT NULL,
       quantity bigint NOT NULL
 );

CREATE TABLE account
(
      account_id SERIAL PRIMARY KEY,
      account_name character varying(255) NOT NULL,
      password character varying(255) NOT NULL,
      enabled boolean NOT NULL
);

CREATE TABLE account_role
(
        role_id SERIAL PRIMARY KEY,
        account_name character varying(255) NOT NULL,
        role character varying(255) NOT NULL
);

CREATE TABLE reserve_order
(
        order_id SERIAL PRIMARY KEY,
        account_id bigint NOT NULL,
        item_id bigint NOT NULL,
        reservation_timestamp timestamp without time zone NOT NULL
);

insert into account (account_name, password, enabled) values ('AdminCat','admin', true);
insert into account (account_name, password, enabled) values ('UserCat','user', true);

insert into account_role (account_name, role) values ('AdminCat', 'ROLE_ADMIN');
insert into account_role (account_name, role) values ('UserCat', 'ROLE_USER');

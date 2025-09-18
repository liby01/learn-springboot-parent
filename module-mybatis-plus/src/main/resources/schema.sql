-- 用户表
drop table if exists t_user;
create table t_user (
                        id   bigint primary key auto_increment,
                        name varchar(64) not null,
                        password varchar(255) not null
);

-- 订单表
drop table if exists t_order;
create table t_order (
                         id         bigint primary key auto_increment,
                         user_id    bigint not null,
                         amount     decimal(10,2) not null,
                         created_at datetime not null default current_timestamp,
                         index idx_user_id(user_id)
);

drop table if exists sys_user;
create table sys_user (
                          id        bigint primary key auto_increment,
                          username  varchar(64) not null unique,
                          password  varchar(100) not null,   -- 存BCrypt加密后的密码
                          roles     varchar(128) not null default 'ROLE_ADMIN', -- 逗号分隔：ROLE_ADMIN,ROLE_USER
                          enabled   tinyint(1) not null default 1,             -- 1启用 0禁用
                          created_at timestamp default current_timestamp
);
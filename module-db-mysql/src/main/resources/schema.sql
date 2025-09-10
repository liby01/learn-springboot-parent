drop table if exists t_user;
create table t_user (
                        id bigint primary key auto_increment,
                        name varchar(64) not null
);

create database dynamic_datasource_base_prd;

create table if not exists base_info
(
    base_id          bigint                              not null primary key,
    base_code        varchar(50)                         null,
    creator_id       bigint                              null,
    creator          varchar(100)                        null,
    create_time      timestamp default CURRENT_TIMESTAMP not null,
    modifier_id      bigint                              null,
    modifier         varchar(100)                        null,
    last_modify_time timestamp default CURRENT_TIMESTAMP not null,
    deleted          smallint  default 0                 not null,
    record_version   integer   default 1                 null
);

SELECT t.*
FROM base_info t
LIMIT 501
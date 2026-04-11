create table mybatis_plus.user
(
    id    bigint auto_increment comment '主键ID'
        primary key,
    name  varchar(30) null comment '姓名',
    age   int         null comment '年龄',
    email varchar(50) null comment '邮箱',
    address varchar(50) null comment '地址',
    phone varchar(50) null comment '手机号',
    balance decimal(10,2) null comment '余额',
    sex   int         null comment '性别'
        comment '性别 1:男 2:女',
    create_time timestamp null comment '创建时间',
    update_time timestamp null comment '更新时间'
    comment '用户表'
);


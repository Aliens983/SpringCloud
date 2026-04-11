create table mybatis_plus.`order`
(
    id           bigint auto_increment comment 'Primary Key ID'
        primary key,
    order_no     varchar(64)                              not null comment 'Business Order Number (Unique)',
    user_id      bigint                                   not null comment 'Associated User ID',
    total_amount decimal(10, 2) default 0.00              not null comment 'Total Price',
    status       tinyint        default 0                 not null comment 'Order Status: 0-Pending, 1-Paid, 2-Shipped',
    created_at   datetime       default CURRENT_TIMESTAMP not null comment 'Creation Time',
    updated_at   datetime       default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment 'Update Time',
    constraint uk_order_no
        unique (order_no)
)
    comment 'Order Table';

create index idx_user_id
    on mybatis_plus.`order` (user_id);


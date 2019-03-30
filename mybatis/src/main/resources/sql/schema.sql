#数据库 java j1806-ssm
drop database if exists `j1806-psi`;
create database `j1806-psi` default character set utf8;
# goods 表
drop table if exists goods;
create table goods
(
  id       bigint unsigned not null auto_increment comment 'ID',
  name     varchar(20) comment '商品名称',
  category bigint unsigned comment '类别ID',
  merchant bigint unsigned comment '商家',
  price    int comment '价格(单位/分)',
  stock    int comment '库存',
  primary key (id)
) engine = InnoDB
  default charset = utf8 comment '商品表';
-- 表 category
drop table if exists category;
create table category
(
  id       bigint unsigned not null auto_increment comment 'ID',
  name     varchar(20) comment '类别名称',
  parent bigint comment '上一级',
  primary key (id)
)engine = InnoDB
 default charset = utf8 comment '类别表';
-- 表merchant
drop table if exists merchant;
create table merchant
(
  id       bigint unsigned not null auto_increment comment 'ID',
  name     varchar(20) comment '商家名称',
  telephone varchar(12) comment '联系方式',
  primary key (id)
)engine = InnoDB
 default charset = utf8 comment '商家表';

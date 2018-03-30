/*==============================================================*/
/* DBMS name:      PostgreSQL 8                                 */
/* Created on:     2015/9/29 10:55:48                           */
/*==============================================================*/


drop table shuadan_category;

drop table shuadan_product_apply;

drop table shuadan_product_attr;

drop table shuadan_product_images;

drop table shuadan_products;

drop table wb_account;

drop table wb_act_log;

drop table wb_comments;

drop table wb_order;

drop table wb_pay;

drop table wb_pay_refund;

/*==============================================================*/
/* Table: shuadan_category                                      */
/*==============================================================*/
create table shuadan_category (
   id                   int8                 not null,
   name                 varchar(50)          null,
   text                 varchar(50)          null,
   parentid             int8                 null,
   status               int4                 null,
   constraint pk_shuadan_category primary key (id)
);

comment on table shuadan_category is
'刷单，分类表';

comment on column shuadan_category.name is
'代码';

comment on column shuadan_category.text is
'名称';

comment on column shuadan_category.parentid is
'上一级';

comment on column shuadan_category.status is
'状态';

/*==============================================================*/
/* Table: shuadan_product_apply                                 */
/*==============================================================*/
create table shuadan_product_apply (
   id                   int8                 not null,
   product_id           int8                 null,
   site_username        varchar(50)          null,
   remarks              varchar(256)         null,
   num                  int4                 null,
   price                numeric(10,2)        null,
   apply_user           varchar(25)          null,
   create_date          date                 null,
   constraint pk_shuadan_product_apply primary key (id)
);

comment on table shuadan_product_apply is
'刷单产品申请';

comment on column shuadan_product_apply.site_username is
'网站账号';

comment on column shuadan_product_apply.remarks is
'备注';

comment on column shuadan_product_apply.num is
'数量';

comment on column shuadan_product_apply.price is
'价格';

comment on column shuadan_product_apply.apply_user is
'申请者';

/*==============================================================*/
/* Table: shuadan_product_attr                                  */
/*==============================================================*/
create table shuadan_product_attr (
   id                   int8                 null,
   product_id           int8                 null,
   att_name             varchar(50)          null,
   att_text             varchar(250)         null
);

comment on table shuadan_product_attr is
'刷单要求';

/*==============================================================*/
/* Table: shuadan_product_images                                */
/*==============================================================*/
create table shuadan_product_images (
   id                   int8                 not null,
   product_id           int8                 null,
   tooltip              varchar(512)         null,
   url                  varchar(512)         null,
   postion              int4                 null,
   status               int4                 null,
   create_date          date                 null,
   constraint pk_shuadan_product_images primary key (id)
);

comment on table shuadan_product_images is
'刷单的产品图片';

comment on column shuadan_product_images.tooltip is
'备注';

comment on column shuadan_product_images.url is
'链接';

comment on column shuadan_product_images.postion is
'位置';

comment on column shuadan_product_images.status is
'状态';

/*==============================================================*/
/* Table: shuadan_products                                      */
/*==============================================================*/
create table shuadan_products (
   id                   int8                 not null,
   category_id          int4                 null,
   title                varchar(150)         null,
   remarks              text                 null,
   site                 varchar(250)         null,
   url                  varchar(512)         null,
   countdown            int8                 null,
   price                decimal(10,2)        null,
   num                  int4                 null,
   status               int4                 null,
   groupindex           varchar(50)          null,
   create_user          varchar(25)          null,
   create_date          date                 null,
   update_date          date                 null,
   constraint pk_shuadan_products primary key (id)
);

comment on table shuadan_products is
'刷单-产品';

comment on column shuadan_products.category_id is
'类别id';

comment on column shuadan_products.title is
'标题';

comment on column shuadan_products.remarks is
'备注';

comment on column shuadan_products.site is
'网站';

comment on column shuadan_products.url is
'商品链接';

comment on column shuadan_products.countdown is
'倒计时';

comment on column shuadan_products.price is
'单价';

comment on column shuadan_products.num is
'数量';

comment on column shuadan_products.status is
'状态';

comment on column shuadan_products.groupindex is
'组号，一组一拍';

comment on column shuadan_products.create_user is
'发布者';

comment on column shuadan_products.create_date is
'创建时间';

comment on column shuadan_products.update_date is
'更新时间';

/*==============================================================*/
/* Table: wb_account                                            */
/*==============================================================*/
create table wb_account (
   id                   int4                 not null,
   name                 varchar(20)          null,
   pwd                  varchar(15)          null,
   mobile               varchar(20)          null,
   sex                  inet                 null,
   role                 varchar(10)          null,
   province             varchar(20)          null,
   cite                 varchar(50)          null,
   user_level           int4                 null,
   user_money           char(10)             null,
   avatar               varchar(512)         null,
   createdate           date                 null,
   lastdate             date                 null,
   lastipaddress        varchar(20)          null,
   constraint pk_wb_account primary key (id)
);

comment on table wb_account is
'账号';

comment on column wb_account.name is
'用户名';

comment on column wb_account.mobile is
'电话';

comment on column wb_account.sex is
'性别';

comment on column wb_account.role is
'角色';

comment on column wb_account.province is
'省份';

comment on column wb_account.cite is
'城市';

comment on column wb_account.user_level is
'用户等级';

comment on column wb_account.user_money is
'用户金额';

comment on column wb_account.avatar is
'用户头像';

/*==============================================================*/
/* Table: wb_act_log                                            */
/*==============================================================*/
create table wb_act_log (
   id                   int8                 not null,
   title                varchar(256)         null,
   msg                  varchar(512)         null,
   content              varchar(2048)        null,
   error                text                 null,
   create_user          varchar(25)          null,
   create_ip            varchar(50)          null,
   create_date          date                 null,
   constraint pk_wb_act_log primary key (id)
);

comment on table wb_act_log is
'系统跟踪日志';

/*==============================================================*/
/* Table: wb_comments                                           */
/*==============================================================*/
create table wb_comments (
   id                   int8                 not null,
   url                  varchar(512)         null,
   from_user            varchar(50)          null,
   reply_user           varchar(50)          null,
   content              varchar(512)         null,
   create_date          date                 null,
   create_ip            varchar(20)          null,
   constraint pk_wb_comments primary key (id)
);

comment on table wb_comments is
'评论';

/*==============================================================*/
/* Table: wb_order                                              */
/*==============================================================*/
create table wb_order (
   id                   int8                 not null,
   title                varchar(512)         null,
   url                  varchar(512)         null,
   pay_money            decimal(10,2)        null,
   remarks              varchar(512)         null,
   content              varchar(512)         null,
   create_date          date                 null,
   constraint pk_wb_order primary key (id)
);

comment on table wb_order is
'订单';

comment on column wb_order.title is
'标题';

comment on column wb_order.url is
'商品链接';

comment on column wb_order.pay_money is
'支付钱款';

comment on column wb_order.remarks is
'备注';

comment on column wb_order.content is
'内容';

/*==============================================================*/
/* Table: wb_pay                                                */
/*==============================================================*/
create table wb_pay (
   id                   number               not null,
   order_id             number               null,
   pay_type             varchar(50)          null,
   title                varchar(256)         null,
   pay_money            number(10,2)         null,
   remarks              varchar(4000)        null,
   product_url          varchar(512)         null,
   trade_no             varchar(50)          null,
   trade_status         varchar(50)          null,
   create_date          date                 null,
   update_date          date                 null,
   constraint pk_wb_pay primary key (id)
);

comment on table wb_pay is
'支付';

comment on column wb_pay.order_id is
'订单号';

comment on column wb_pay.pay_type is
'支付类型';

comment on column wb_pay.title is
'订单名称';

comment on column wb_pay.pay_money is
'支付金额';

comment on column wb_pay.remarks is
'订单描述';

comment on column wb_pay.product_url is
'产品url';

comment on column wb_pay.trade_no is
'交易号，支付方提供';

comment on column wb_pay.trade_status is
'支付状态';

comment on column wb_pay.create_date is
'创建日期';

comment on column wb_pay.update_date is
'更新日期';

/*==============================================================*/
/* Table: wb_pay_refund                                         */
/*==============================================================*/
create table wb_pay_refund (
   batch_no             varchar(50)          not null,
   order_id             number               null,
   refund_money         number(10,2)         null,
   trade_no             varchar(50)          null,
   remarks              varchar(256)         null,
   refund_status        varchar(50)          null,
   create_date          date                 null,
   update_date          date                 null,
   constraint pk_wb_pay_refund primary key (batch_no)
);

comment on table wb_pay_refund is
'支付退款';

comment on column wb_pay_refund.batch_no is
'退款批次号';

comment on column wb_pay_refund.order_id is
'订单号';

comment on column wb_pay_refund.refund_money is
'退款金额';

comment on column wb_pay_refund.trade_no is
'交易号，支付方提供';

comment on column wb_pay_refund.remarks is
'退款描述';

comment on column wb_pay_refund.refund_status is
'退款状态';

comment on column wb_pay_refund.create_date is
'创建日期';

comment on column wb_pay_refund.update_date is
'更新日期';


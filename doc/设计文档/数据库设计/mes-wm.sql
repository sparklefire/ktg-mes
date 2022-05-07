-- ----------------------------
-- 1、仓库表
-- ----------------------------
drop table if exists wm_warehouse;
create table wm_warehouse (
  warehouse_id           bigint(20)      not null auto_increment    comment '仓库ID',
  warehouse_code         varchar(64)     not null                   comment '仓库编码',
  warehouse_name         varchar(255)    not null                   comment '仓库名称',
  location               varchar(500)                               comment '位置',
  area                   double(12,2)                               comment '面积',
  charge                 varchar(64)                                comment '负责人',
  remark                 varchar(500)    default ''                 comment '备注',
  attr1                  varchar(64)     default null               comment '预留字段1',
  attr2                  varchar(255)     default null              comment '预留字段2',
  attr3                  int(11)         default 0                  comment '预留字段3',
  attr4                  int(11)         default 0                  comment '预留字段4',
  create_by              varchar(64)     default ''                 comment '创建者',
  create_time 	         datetime                                   comment '创建时间',
  update_by              varchar(64)     default ''                 comment '更新者',
  update_time            datetime                                   comment '更新时间',
  primary key (warehouse_id)
) engine=innodb auto_increment=200 comment = '仓库表';


-- ----------------------------
-- 2、库区表
-- ----------------------------
drop table if exists wm_storage_location;
create table wm_storage_location (
  location_id           bigint(20)      not null auto_increment     comment '库区ID',
  location_code         varchar(64)     not null                    comment '库区编码',
  location_name         varchar(255)    not null                    comment '库区名称',
  warehouse_id          bigint(20)      not null                    comment '仓库ID',
  area                  double(12,2)                               comment '面积',
  remark                varchar(500)    default ''                 comment '备注',
  attr1                 varchar(64)     default null               comment '预留字段1',
  attr2                 varchar(255)    default null              comment '预留字段2',
  attr3                 int(11)         default 0                  comment '预留字段3',
  attr4                 int(11)         default 0                  comment '预留字段4',
  create_by             varchar(64)     default ''                 comment '创建者',
  create_time 	        datetime                                   comment '创建时间',
  update_by             varchar(64)     default ''                 comment '更新者',
  update_time           datetime                                   comment '更新时间',
  primary key (location_id)
) engine=innodb auto_increment=200 comment = '库区表';




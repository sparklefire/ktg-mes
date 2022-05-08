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
  area                  double(12,2)                                comment '面积',
  area_flag             char(1)         default 'N'                 comment '是否开启库位管理',
  remark                varchar(500)    default ''                  comment '备注',
  attr1                 varchar(64)     default null                comment '预留字段1',
  attr2                 varchar(255)    default null                comment '预留字段2',
  attr3                 int(11)         default 0                   comment '预留字段3',
  attr4                 int(11)         default 0                   comment '预留字段4',
  create_by             varchar(64)     default ''                  comment '创建者',
  create_time 	        datetime                                    comment '创建时间',
  update_by             varchar(64)     default ''                  comment '更新者',
  update_time           datetime                                    comment '更新时间',
  primary key (location_id)
) engine=innodb auto_increment=200 comment = '库区表';


-- ----------------------------
-- 3、库位表
-- ----------------------------
drop table if exists wm_storage_area;
create table wm_storage_area (
  area_id               bigint(20)      not null auto_increment     comment '库位ID',
  area_code             varchar(64)     not null                    comment '库位编码',
  area_name             varchar(255)    not null                    comment '库位名称',
  location_id           bigint(20)      not null                    comment '库区ID',
  area                  double(8,2)                                 comment '面积',
  max_loa               double(8,2)                                 comment '最大载重量',
  position_x            int(11)                                     comment '库位位置X',
  position_y            int(11)                                     comment '库位位置y',
  position_z            int(11)                                     comment '库位位置z',
  enable_flag           char(1)                                     comment '是否启用',
  remark                varchar(500)    default ''                  comment '备注',
  attr1                 varchar(64)     default null                comment '预留字段1',
  attr2                 varchar(255)    default null                comment '预留字段2',
  attr3                 int(11)         default 0                   comment '预留字段3',
  attr4                 int(11)         default 0                   comment '预留字段4',
  create_by             varchar(64)     default ''                  comment '创建者',
  create_time 	        datetime                                    comment '创建时间',
  update_by             varchar(64)     default ''                  comment '更新者',
  update_time           datetime                                    comment '更新时间',
  primary key (area_id)
) engine=innodb auto_increment=200 comment = '库位表';




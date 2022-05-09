-- ----------------------------
-- 1、生产工单表
-- ----------------------------
drop table if exists pro_workorder;
create table pro_workorder (
  workorder_id                bigint(20)      not null auto_increment    comment '工单ID',
  workorder_code              varchar(64)     not null                   comment '工单编码',
  workorder_name              varchar(255)    not null                   comment '工单名称',
  order_source                varchar(64)     not null                   comment '来源类型',
  source_code                 varchar(64)                                comment '来源单据',
  product_id                  bigint(20)      not null                   comment '产品ID',
  product_code                varchar(64)     not null                   comment '产品编号',
  product_name                varchar(255)    not null                   comment '产品名称',
  product_spc                 varchar(255)                               comment '规格型号',
  unit_of_measure             varchar(64)     not null                   comment '单位',
  quantity                    double(14,2)    default 0 not null         comment '生产数量',
  client_id                   bigint(20)                                 comment '客户ID',
  client_code                 varchar(64)                                comment '客户编码',
  client_name                 varchar(255)                               comment '客户名称',
  request_date                datetime        not null                   comment '需求日期',
  parent_id                   bigint(20)      default 0,not null         comment '父工单',
  ancestors                   varchar(500)    not null                   comment '所有父节点ID',
  status                      varchar(64)     default 'PREPARE'          comment '单据状态',
  remark                      varchar(500)    default ''                 comment '备注',
  attr1                       varchar(64)     default null               comment '预留字段1',
  attr2                       varchar(255)    default null               comment '预留字段2',
  attr3                       int(11)         default 0                  comment '预留字段3',
  attr4                       int(11)         default 0                  comment '预留字段4',
  create_by                   varchar(64)     default ''                 comment '创建者',
  create_time 	              datetime                                   comment '创建时间',
  update_by                   varchar(64)     default ''                 comment '更新者',
  update_time                 datetime                                   comment '更新时间',
  primary key (workorder_id)
) engine=innodb auto_increment=200 comment = '生产工单表';


-- ----------------------------
-- 2、生产工单BOM组成表
-- ----------------------------
drop table if exists pro_workorder_bom;
create table pro_workorder_bom (
  line_id                     bigint(20)      not null auto_increment    comment '行ID',
  workorder_id                bigint(20)      not null                   comment '生产工单ID',
  item_id                     bigint(20)      not null                   comment 'BOM物料ID',
  item_code                   varchar(64)     not null                   comment 'BOM物料编号',
  item_name                   varchar(255)    not null                   comment 'BOM物料名称',
  item_spc                    varchar(255)                               comment '规格型号',
  unit_of_measure             varchar(64)     not null                   comment '单位',
  item_or_product             varchar(20)     not null                   comment '物料产品标识',
  quantity                    double(14,2)    default 0 not null         comment '预计使用量',
  remark                      varchar(500)    default ''                 comment '备注',
  attr1                       varchar(64)     default null               comment '预留字段1',
  attr2                       varchar(255)    default null               comment '预留字段2',
  attr3                       int(11)         default 0                  comment '预留字段3',
  attr4                       int(11)         default 0                  comment '预留字段4',
  create_by                   varchar(64)     default ''                 comment '创建者',
  create_time 	              datetime                                   comment '创建时间',
  update_by                   varchar(64)     default ''                 comment '更新者',
  update_time                 datetime                                   comment '更新时间',
  primary key (line_id)
) engine=innodb auto_increment=200 comment = '生产工单BOM组成表';



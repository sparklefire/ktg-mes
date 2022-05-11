-- ----------------------------
-- 1、工装夹具类型表
-- ----------------------------
drop table if exists tm_tool_type;
create table tm_tool_type (
  tool_type_id                bigint(20)      not null auto_increment    comment '工装夹具类型ID',
  tool_type_code              varchar(64)     not null                   comment '类型编码',
  tool_type_name              varchar(255)    not null                   comment '类型名称',
  code_flag                   char(1)         default 'Y' not null       comment '是否编码管理',
  mainten_type                varchar(20)                                comment '保养维护类型',
  mainten_period              int(11)                                    comment '保养周期',
  remark                      varchar(500)    default ''                 comment '备注',
  attr1                       varchar(64)     default null               comment '预留字段1',
  attr2                       varchar(255)    default null               comment '预留字段2',
  attr3                       int(11)         default 0                  comment '预留字段3',
  attr4                       int(11)         default 0                  comment '预留字段4',
  create_by                   varchar(64)     default ''                 comment '创建者',
  create_time 	              datetime                                   comment '创建时间',
  update_by                   varchar(64)     default ''                 comment '更新者',
  update_time                 datetime                                   comment '更新时间',
  primary key (tool_type_id)
) engine=innodb auto_increment=200 comment = '工装夹具类型表';



-- ----------------------------
-- 2、工装夹具清单表
-- ----------------------------
drop table if exists tm_tool;
create table tm_tool (
  tool_id                     bigint(20)      not null auto_increment    comment '工装夹具ID',
  tool_code                   varchar(64)     not null                   comment '工装夹具编码',
  tool_name                   varchar(255)    not null                   comment '工装夹具名称',
  brand                       varchar(255)                               comment '品牌',
  spec                        varchar(255)                               comment '型号',
  tool_type_id                bigint(11)      not null                   comment '工装夹具类型ID',  
  tool_type_code              varchar(64)                                comment '工装夹具类型编码',
  tool_type_name              varchar(255)                               comment '工装夹具类型名称',
  code_flag                   char(1)         default 'Y' not null       comment '是否单独编码管理',
  quantity                    int(11)         default 1 not null         comment '数量',
  quantity_avail              int(11)         default 1                  comment '可用数量',     
  mainten_type                varchar(20)                                comment '保养维护类型',
  next_mainten_period         int(11)                                    comment '下一次保养周期',
  next_mainten_date           datetime                                   comment '下一次保养日期',
  status                      varchar(64)     default 'STORE'            comment '状态[MES_TOOL_STATUS]',
  remark                      varchar(500)    default ''                 comment '备注',
  attr1                       varchar(64)     default null               comment '预留字段1',
  attr2                       varchar(255)    default null               comment '预留字段2',
  attr3                       int(11)         default 0                  comment '预留字段3',
  attr4                       int(11)         default 0                  comment '预留字段4',
  create_by                   varchar(64)     default ''                 comment '创建者',
  create_time 	              datetime                                   comment '创建时间',
  update_by                   varchar(64)     default ''                 comment '更新者',
  update_time                 datetime                                   comment '更新时间',
  primary key (tool_id)
) engine=innodb auto_increment=200 comment = '工装夹具清单表';
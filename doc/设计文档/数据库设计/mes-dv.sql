-- ----------------------------
-- 1、设备类型表
-- ----------------------------
drop table if exists dv_machinery_type;
create table dv_machinery_type (
  machinery_type_id           bigint(20)      not null auto_increment    comment '设备类型ID',
  machinery_type_code         varchar(64)     not null                   comment '设备类型编码',
  machinery_type_name         varchar(255)    not null                   comment '设备类型名称',
  parent_type_id              bigint(20)      default 0                  comment '父类型ID',
  ancestors                   varchar(255)    not null                   comment '所有父节点ID',
  enable_flag                 char(1)         default 'Y' not null       comment '是否启用',
  remark                      varchar(500)    default ''                 comment '备注',
  attr1                       varchar(64)     default null               comment '预留字段1',
  attr2                       varchar(255)    default null               comment '预留字段2',
  attr3                       int(11)         default 0                  comment '预留字段3',
  attr4                       int(11)         default 0                  comment '预留字段4',
  create_by                   varchar(64)     default ''                 comment '创建者',
  create_time 	              datetime                                   comment '创建时间',
  update_by                   varchar(64)     default ''                 comment '更新者',
  update_time                 datetime                                   comment '更新时间',
  primary key (machinery_type_id)
) engine=innodb auto_increment=200 comment = '设备类型表';


-- ----------------------------
-- 2、设备表
-- ----------------------------
drop table if exists dv_machinery;
create table dv_machinery (
  machinery_id                bigint(20)      not null auto_increment    comment '设备类型ID',
  machinery_code              varchar(64)     not null                   comment '设备类型编码',
  machinery_name              varchar(255)    not null                   comment '设备类型名称',
  machinery_brand             varchar(255)                               comment '品牌',
  machinery_spec              varchar(255)                               comment '规格型号',
  machinery_type_id           bigint(20)      not null                   comment '设备类型ID',
  machinery_type_code         varchar(64)                                comment '设备类型编码',
  machinery_type_name         varchar(255)                               comment '设备类型名称',
  workshop_id                 bigint(20)      not null                   comment '所属车间ID',
  workshop_code               varchar(64)                                comment '所属车间编码',
  workshop_name               varchar(255)                               comment '所属车间名称',
  status                      varchar(64)     default 'STOP' not null    comment '设备状态',
  remark                      varchar(500)    default ''                 comment '备注',
  attr1                       varchar(64)     default null               comment '预留字段1',
  attr2                       varchar(255)    default null               comment '预留字段2',
  attr3                       int(11)         default 0                  comment '预留字段3',
  attr4                       int(11)         default 0                  comment '预留字段4',
  create_by                   varchar(64)     default ''                 comment '创建者',
  create_time 	              datetime                                   comment '创建时间',
  update_by                   varchar(64)     default ''                 comment '更新者',
  update_time                 datetime                                   comment '更新时间',
  primary key (machinery_id)
) engine=innodb auto_increment=200 comment = '设备表';


-- ----------------------------
-- 3、设备点检保养项目表
-- ----------------------------
drop table if exists dv_subject;
create table dv_subject (
  subject_id                  bigint(20)      not null auto_increment    comment '项目ID',
  subject_code                varchar(64)     not null                   comment '项目编码',
  subject_name                varchar(255)                               comment '项目名称',
  subject_type                varchar(64)     default 0                  comment '项目类型',
  subject_content             varchar(500)    not null                   comment '项目内容',
  subject_standard            varchar(255)                               comment '标准',
  enable_flag                 char(1)         default 'Y' not null       comment '是否启用',
  remark                      varchar(500)    default ''                 comment '备注',
  attr1                       varchar(64)     default null               comment '预留字段1',
  attr2                       varchar(255)    default null               comment '预留字段2',
  attr3                       int(11)         default 0                  comment '预留字段3',
  attr4                       int(11)         default 0                  comment '预留字段4',
  create_by                   varchar(64)     default ''                 comment '创建者',
  create_time 	              datetime                                   comment '创建时间',
  update_by                   varchar(64)     default ''                 comment '更新者',
  update_time                 datetime                                   comment '更新时间',
  primary key (subject_id)
) engine=innodb auto_increment=200 comment = '设备点检保养项目表';

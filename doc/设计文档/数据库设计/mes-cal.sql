-- ----------------------------
-- 1、班组表
-- ----------------------------
drop table if exists cal_team;
create table cal_team (
  team_id                bigint(20)      not null auto_increment    comment '班组ID',
  team_code              varchar(64)     not null                   comment '班组编号',
  team_name              varchar(255)    not null                   comment '班组名称', 
  calendar_type          varchar(64)                                comment '班组类型',
  remark                 varchar(500)    default ''                 comment '备注',
  attr1                  varchar(64)     default null               comment '预留字段1',
  attr2                  varchar(255)     default null              comment '预留字段2',
  attr3                  int(11)         default 0                  comment '预留字段3',
  attr4                  int(11)         default 0                  comment '预留字段4',
  create_by              varchar(64)     default ''                 comment '创建者',
  create_time 	         datetime                                   comment '创建时间',
  update_by              varchar(64)     default ''                 comment '更新者',
  update_time            datetime                                   comment '更新时间',
  primary key (team_id)
) engine=innodb auto_increment=200 comment = '班组表';


-- ----------------------------
-- 2、班组成员表
-- ----------------------------
drop table if exists cal_team_member;
create table cal_team_member (
  member_id                bigint(20)      not null auto_increment    comment '班组成员ID',
  team_id                  bigint(20)      not null                   comment '班组ID',
  user_id                  bigint(20)      not null                   comment '用户ID', 
  user_name                varchar(64)     not null                   comment '用户名', 
  nick_name                varchar(64)                                comment '用户昵称',
  tel                      varchar(64)                                comment '电话',
  remark                   varchar(500)    default ''                 comment '备注',
  attr1                    varchar(64)     default null               comment '预留字段1',
  attr2                    varchar(255)    default null               comment '预留字段2',
  attr3                    int(11)         default 0                  comment '预留字段3',
  attr4                    int(11)         default 0                  comment '预留字段4',
  create_by                varchar(64)     default ''                 comment '创建者',
  create_time 	           datetime                                   comment '创建时间',
  update_by                varchar(64)     default ''                 comment '更新者',
  update_time              datetime                                   comment '更新时间',
  primary key (member_id)
) engine=innodb auto_increment=200 comment = '班组成员表';


-- ----------------------------
-- 3、排班计划
-- ----------------------------
drop table if exists cal_plan;
create table cal_plan (
  plan_id                  bigint(20)      not null auto_increment    comment '计划ID',
  plan_code                varchar(64)     not null                  comment '计划编号',
  plan_name                varchar(255)    not null                   comment '计划名称', 
  calendar_type            varchar(64)                                comment '班组类型',
  start_date               datetime        not null                   comment '开始日期',
  end_date                 datetime        not null                   comment '结束日期',
  shift_type               varchar(64)                                comment '轮班方式',
  shift_method             varchar(64)                                comment '倒班方式',
  shift_count              int(11)                                    comment '数',
  status                   varchar(64)     default 'PREPARE'          comment '状态',
  remark                   varchar(500)    default ''                 comment '备注',
  attr1                    varchar(64)     default null               comment '预留字段1',
  attr2                    varchar(255)    default null               comment '预留字段2',
  attr3                    int(11)         default 0                  comment '预留字段3',
  attr4                    int(11)         default 0                  comment '预留字段4',
  create_by                varchar(64)     default ''                 comment '创建者',
  create_time 	           datetime                                   comment '创建时间',
  update_by                varchar(64)     default ''                 comment '更新者',
  update_time              datetime                                   comment '更新时间',
  primary key (plan_id)
) engine=innodb auto_increment=200 comment = '排班计划表';



-- ----------------------------
-- 4、计划班次
-- ----------------------------
drop table if exists cal_shift;
create table cal_shift (
  shift_id                  bigint(20)      not null auto_increment    comment '班次ID',
  plan_id                   bigint(20)      not null                   comment '计划ID',
  order_num                 int(2)          not null                   comment '序号', 
  shift_name                varchar(64)     not null                   comment '班次名称',
  start_time                varchar(10)     not null                   comment '开始时间',
  end_time                  varchar(10)     not null                   comment '结束时间',
  remark                    varchar(500)    default ''                 comment '备注',
  attr1                     varchar(64)     default null               comment '预留字段1',
  attr2                     varchar(255)    default null               comment '预留字段2',
  attr3                     int(11)         default 0                  comment '预留字段3',
  attr4                     int(11)         default 0                  comment '预留字段4',
  create_by                 varchar(64)     default ''                 comment '创建者',
  create_time 	            datetime                                   comment '创建时间',
  update_by                 varchar(64)     default ''                 comment '更新者',
  update_time               datetime                                   comment '更新时间',
  primary key (shift_id)
) engine=innodb auto_increment=200 comment = '计划班次表';


-- ----------------------------
-- 5、计划班组
-- ----------------------------
drop table if exists cal_plan_team;
create table cal_plan_team (
  record_id                 bigint(20)      not null auto_increment    comment '流水号',
  plan_id                   bigint(20)      not null                   comment '计划ID',
  team_id                   bigint(20)      not null                   comment '班组ID',
  team_code                 varchar(64)                                comment '班组编号',
  team_name                 varchar(64)                                comment '班组名称',
  remark                    varchar(500)    default ''                 comment '备注',
  attr1                     varchar(64)     default null               comment '预留字段1',
  attr2                     varchar(255)    default null               comment '预留字段2',
  attr3                     int(11)         default 0                  comment '预留字段3',
  attr4                     int(11)         default 0                  comment '预留字段4',
  create_by                 varchar(64)     default ''                 comment '创建者',
  create_time 	            datetime                                   comment '创建时间',
  update_by                 varchar(64)     default ''                 comment '更新者',
  update_time               datetime                                   comment '更新时间',
  primary key (record_id)
) engine=innodb auto_increment=200 comment = '计划班组表';


-- ----------------------------
-- 6、节假日设置
-- ----------------------------
drop table if exists cal_holiday;
create table cal_holiday (
  holiday_id                bigint(20)      not null auto_increment    comment '流水号',  
  the_day                   datetime                                   comment '日期',
  holiday_type              varchar(64)                                comment '日期类型',
  start_time                datetime                                   comment '开始时间',
  end_time                  datetime                                   comment '结束时间',
  remark                    varchar(500)    default ''                 comment '备注',
  attr1                     varchar(64)     default null               comment '预留字段1',
  attr2                     varchar(255)    default null               comment '预留字段2',
  attr3                     int(11)         default 0                  comment '预留字段3',
  attr4                     int(11)         default 0                  comment '预留字段4',
  create_by                 varchar(64)     default ''                 comment '创建者',
  create_time 	            datetime                                   comment '创建时间',
  update_by                 varchar(64)     default ''                 comment '更新者',
  update_time               datetime                                   comment '更新时间',
  primary key (holiday_id)
) engine=innodb auto_increment=200 comment = '节假日设置';


-- ----------------------------
-- 7、班组排班表
-- ----------------------------
drop table if exists cal_teamshift;
create table cal_teamshift (
  record_id                 bigint(20)      not null auto_increment    comment '流水号',
  the_day                   varchar(64)     not null                   comment '日期',
  team_id                   bigint(20)      not null                   comment '班组ID',
  team_name                 varchar(255)                               comment '班组名称',
  shift_id                  bigint(20)      not null                   comment '班次ID',
  shift_name                varchar(255)                               comment '班次名称',
  order_num                 int(11)                                    comment '序号',
  plan_id                   bigint(20)                                 comment '计划ID',
  calendar_type             varchar(64)                                comment '班组类型',
  shift_type                varchar(64)                                comment '轮班方式',
  remark                    varchar(500)    default ''                 comment '备注',
  attr1                     varchar(64)     default null               comment '预留字段1',
  attr2                     varchar(255)    default null               comment '预留字段2',
  attr3                     int(11)         default 0                  comment '预留字段3',
  attr4                     int(11)         default 0                  comment '预留字段4',
  create_by                 varchar(64)     default ''                 comment '创建者',
  create_time 	            datetime                                   comment '创建时间',
  update_by                 varchar(64)     default ''                 comment '更新者',
  update_time               datetime                                   comment '更新时间',
  primary key (record_id)
) engine=innodb auto_increment=200 comment = '班组排班表';


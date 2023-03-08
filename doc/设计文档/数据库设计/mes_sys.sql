-- ----------------------------
-- 1、编码生成规则表
-- ----------------------------
drop table if exists sys_auto_code_rule;
create table sys_auto_code_rule (
  rule_id           bigint(20)      not null auto_increment    comment '规则ID',
  rule_code         varchar(64)     not null                   comment '规则编码',
  rule_name         varchar(255)    not null                   comment '规则名称',
  rule_desc     	varchar(500)    default null               comment '描述',
  max_length        int(11)                                    comment '最大长度',
  is_padded         char(1)         not null                   comment '是否补齐',
  padded_char       varchar(20)          	                   comment '补齐字符',
  padded_method     char(1)         default 'L'                comment '补齐方式',
  enable_flag       char(1)         default 'Y' not null       comment '是否启用',
  remark            varchar(500)    default ''                 comment '备注',
  attr1             varchar(64)     default null               comment '预留字段1',
  attr2             varchar(255)     default null              comment '预留字段2',
  attr3             int(11)         default 0                  comment '预留字段3',
  attr4             int(11)         default 0                  comment '预留字段4',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (rule_id)
) engine=innodb auto_increment=200 comment = '编码生成规则表';


-- ----------------------------
-- 2、编码生成规则组成表
-- ----------------------------
drop table if exists sys_auto_code_part;
create table sys_auto_code_part (
  part_id           bigint(20)      not null auto_increment    comment '分段ID',
  rule_id           bigint(20)      not null                   comment '规则ID',
  part_index        int(11)         not null                   comment '分段序号',
  part_type         varchar(20)     not null                   comment '分段类型，INPUTCHAR：输入字符，NOWDATE：当前日期时间，FIXCHAR：固定字符，SERIALNO：流水号',
  part_code			varchar(64)                                comment '分段编号',
  part_name         varchar(255)                               comment '分段名称',
  part_length       int(11)         not null                   comment '分段长度',
  date_format      varchar(20)          	                   comment '日期时间格式',
  input_character   varchar(64)                                comment '输入字符',
  fix_character     varchar(64)                                comment '固定字符',
  seria_start_no    int(11)         					       comment '流水号起始值',
  seria_step        int(11)         					       comment '流水号步长',
  seria_now_no      int(11)         					       comment '流水号当前值',
  cycle_flag        char(1)         					       comment '流水号是否循环',
  cycle_method      varchar(20)     					       comment '循环方式，YEAR：按年，MONTH：按月，DAY：按天，HOUR：按小时，MINITE：按分钟，OTHER：按传入字符变',
  remark            varchar(500)    default ''                 comment '备注',
  attr1             varchar(64)     default null               comment '预留字段1',
  attr2             varchar(255)     default null              comment '预留字段2',
  attr3             int(11)         default 0                  comment '预留字段3',
  attr4             int(11)         default 0                  comment '预留字段4',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (part_id)
) engine=innodb auto_increment=200 comment = '编码生成规则组成表';


-- ----------------------------
-- 3、编码生成记录表
-- ----------------------------
drop table if exists sys_auto_code_result;
create table sys_auto_code_result (
  code_id           bigint(20)      not null auto_increment    comment '记录ID',
  rule_id           bigint(20)      not null                   comment '规则ID',
  gen_date          varchar(20)     not null                   comment '生成日期时间',
  gen_index         int(11)                                    comment '最后产生的序号',
  last_result		varchar(64)                                comment '最后产生的值',
  last_serial_no    int(11)                                    comment '最后产生的流水号',
  last_input_char   varchar(64)                                comment '最后传入的参数',
  remark            varchar(500)    default ''                 comment '备注',
  attr1             varchar(64)     default null               comment '预留字段1',
  attr2             varchar(255)    default null               comment '预留字段2',
  attr3             int(11)         default 0                  comment '预留字段3',
  attr4             int(11)         default 0                  comment '预留字段4',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (code_id)
) engine=innodb auto_increment=200 comment = '编码生成记录表';


-- ----------------------------
-- 4、附件表
-- ----------------------------
drop table if exists sys_attachment;
create table sys_attachment (
  attachment_id     bigint(20)      not null auto_increment    comment '附件ID',
  source_doc_id     bigint(20)                                 comment '关联的业务单据ID',
  source_doc_type   varchar(64)                                comment '业务单据类型',
  file_url          varchar(255)    not null                   comment '访问URL',
  base_path         varchar(64)                                comment '域名',
  file_name         varchar(255)                               comment '文件名',
  orignal_name      varchar(255)                               comment '原来的文件名',
  file_type         varchar(64)                                comment '文件类型',
  file_size         double(12,2)                               comment '文件大小', 
  remark            varchar(500)    default ''                 comment '备注',
  attr1             varchar(64)     default null               comment '预留字段1',
  attr2             varchar(255)     default null              comment '预留字段2',
  attr3             int(11)         default 0                  comment '预留字段3',
  attr4             int(11)         default 0                  comment '预留字段4',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (attachment_id)
) engine=innodb auto_increment=200 comment = '附件表';


-- ----------------------------
-- 5、消息表
-- ----------------------------
drop table if exists sys_messsage;
create table sys_message (
  message_id        bigint(20)      not null auto_increment    comment '附件ID',
  message_type      varchar(64)     not null                   comment '消息类型',
  message_level     varchar(64)     not null                   comment '消息级别',
  message_title     varchar(64)                                comment '标题',
  message_content   longblob                                   comment '内容',
  sender_id         bigint(20)                                 comment '发送人ID',
  sender_name       varchar(64)                                comment '发送人名称',
  sender_nick       varchar(64)                                comment '发送人昵称', 
  recipient_id      bigint(20)      not null                   comment '接收人ID',
  recipient_name    varchar(64)                                comment '接收人名称',
  recipient_nick    varchar(64)                                comment '接收人昵称',
  process_time      datetime                                   comment '处理时间',
  call_back         varchar(255)                               comment '回调地址',
  status            varchar(64)     not null default 'UNREAD'  comment '状态',
  deleted_flag      char(1)         not null default 'N'       comment '是否删除',
  remark            varchar(500)    default ''                 comment '备注',
  attr1             varchar(64)     default null               comment '预留字段1',
  attr2             varchar(255)     default null              comment '预留字段2',
  attr3             int(11)         default 0                  comment '预留字段3',
  attr4             int(11)         default 0                  comment '预留字段4',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (message_id)
) engine=innodb auto_increment=200 comment = '消息表';

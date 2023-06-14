-- ----------------------------
-- 1、物料产品表
-- ----------------------------
drop table if exists md_item;
create table md_item (
  item_id           bigint(20)      not null auto_increment    comment '产品物料ID',
  item_code         varchar(64)     not null                   comment '产品物料编码',
  item_name         varchar(255)    not null                   comment '产品物料名称',
  specification     varchar(500)    default null               comment '规格型号',
  unit_of_measure   varchar(64)     not null                   comment '单位',
  item_or_product   varchar(20)     not null                   comment '产品物料标识',
  item_type_id      bigint(20)      default 0                  comment '物料类型ID',
  item_type_code    varchar(64)     default ''                 comment '物料类型编码',
  item_type_name    varchar(255)    default ''                 comment '物料类型名称',
  enable_flag       char(1)         default 'Y' not null       comment '是否启用',
  safe_stock_flag   char(1)         default 'N' not null       comment '是否设置安全库存',
  min_stock         double(12,4)    default 0                  comment '最低库存量',
  max_stock         double(12,4)    default 0                  comment '最大库存量',
  remark            varchar(500)    default ''                 comment '备注',
  attr1             varchar(64)     default null               comment '预留字段1',
  attr2             varchar(255)     default null               comment '预留字段2',
  attr3             int(11)         default 0                  comment '预留字段3',
  attr4             int(11)         default 0                  comment '预留字段4',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (item_id)
) engine=innodb auto_increment=200 comment = '物料产品表';

-- ----------------------------
-- 2、物料产品分类表
-- ----------------------------
drop table if exists md_item_type;
create table md_item_type (
  item_type_id      bigint(20)      not null auto_increment    comment '产品物料类型ID',
  item_type_code    varchar(64)     not null                   comment '产品物料类型编码',
  item_type_name    varchar(255)    not null                   comment '产品物料类型名称',
  parent_type_id    bigint          default 0 not null         comment '父类型ID',
  ancestors         varchar(255)    not null                   comment '所有层级父节点',
  item_or_product   varchar(20)     not null                   comment '产品物料标识',
  order_num         int(11)         default 1                  comment '排列顺序',
  enable_flag       char(1)         default 'Y' not null       comment '是否启用',
  remark            varchar(500)    default ''                 comment '备注',
  attr1             varchar(64)     default null               comment '预留字段1',
  attr2             varchar(255)     default null               comment '预留字段2',
  attr3             int(11)         default 0                  comment '预留字段3',
  attr4             int(11)         default 0                  comment '预留字段4',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (item_type_id)
) engine=innodb auto_increment=200 comment = '物料产品分类表';

-- ----------------------------
-- 3、产品BOM关系表
-- ----------------------------
drop table if exists md_product_bom;
create table md_product_bom (
  bom_id            bigint(20)      not null auto_increment    comment '流水号',
  item_id           bigint(20)      not null                   comment '物料产品ID',
  bom_item_id       bigint(20)      not null                   comment 'BOM物料ID',
  bom_item_code     varchar(64)     not null                   comment 'BOM物料编码',
  bom_item_name     varchar(255)    not null                   comment 'BOM物料名称',
  bom_item_spec     varchar(500)                               comment 'BOM物料规格',
  unit_of_measure   varchar(64)     not null                   comment 'BOM物料单位',
  item_or_product   varchar(20)     not null                   comment '产品物料标识',
  quantity          double(12,4)    default 0 not null         comment '物料使用比例',
  enable_flag       char(1)         default 'Y' not null       comment '是否启用',
  remark            varchar(500)    default ''                 comment '备注',
  attr1             varchar(64)     default null               comment '预留字段1',
  attr2             varchar(255)     default null               comment '预留字段2',
  attr3             int(11)         default 0                  comment '预留字段3',
  attr4             int(11)         default 0                  comment '预留字段4',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (bom_id)
) engine=innodb auto_increment=200 comment = '产品BOM关系表';


-- ----------------------------
-- 4、供应商表
-- ----------------------------
drop table if exists md_vendor;
create table md_vendor (
  vendor_id         bigint(20)      not null auto_increment    comment '供应商ID',
  vendor_code       varchar(64)     not null                   comment '供应商编码',
  vendor_name       varchar(255)    not null                   comment '供应商名称',
  vendor_nick       varchar(255)                               comment '供应商简称',
  vendor_en         varchar(255)                               comment '供应商英文名称',
  vendor_des        varchar(500)                               comment '供应商简介',  
  vendor_logo       varchar(255)                               comment '供应商LOGO地址',  
  vendor_level      varchar(64)                                comment '供应商等级',  
  vendor_score      int(11)                                    comment '供应商评分',
  address           varchar(500)                               comment '供应商地址',
  website           varchar(255)                               comment '供应商官网地址',
  email             varchar(255)                               comment '供应商邮箱地址',
  tel               varchar(64)                                comment '供应商电话',
  contact1          varchar(64)                                comment '联系人1',
  contact1_tel      varchar(64)                                comment '联系人1-电话',
  contact1_email    varchar(255)                               comment '联系人1-邮箱',
  contact2          varchar(64)                                comment '联系人2',
  contact2_tel      varchar(64)                                comment '联系人2-电话',
  contact2_email    varchar(255)                               comment '联系人2-邮箱',
  credit_code       varchar(64)                                comment '统一社会信用代码',  
  enable_flag       char(1)         default 'Y' not null       comment '是否启用',
  remark            varchar(500)    default ''                 comment '备注',
  attr1             varchar(64)     default null               comment '预留字段1',
  attr2             varchar(255)     default null               comment '预留字段2',
  attr3             int(11)         default 0                  comment '预留字段3',
  attr4             int(11)         default 0                  comment '预留字段4',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (vendor_id)
) engine=innodb auto_increment=200 comment = '供应商表';


-- ----------------------------
-- 5、客户表
-- ----------------------------
drop table if exists md_client;
create table md_client (
  client_id         bigint(20)      not null auto_increment    comment '客户ID',
  client_code       varchar(64)     not null                   comment '客户编码',
  client_name       varchar(255)    not null                   comment '客户名称',
  client_nick       varchar(255)                               comment '客户简称',
  client_en         varchar(255)                               comment '客户英文名称',
  client_des        varchar(500)                               comment '客户简介',  
  client_logo       varchar(255)                               comment '客户LOGO地址',  
  client_type       varchar(64)     default 'ENTERPRISE'       comment '客户类型',  
  address           varchar(500)                               comment '客户地址',
  website           varchar(255)                               comment '客户官网地址',
  email             varchar(255)                               comment '客户邮箱地址',
  tel               varchar(64)                                comment '客户电话',
  contact1          varchar(64)                                comment '联系人1',
  contact1_tel      varchar(64)                                comment '联系人1-电话',
  contact1_email    varchar(255)                               comment '联系人1-邮箱',
  contact2          varchar(64)                                comment '联系人2',
  contact2_tel      varchar(64)                                comment '联系人2-电话',
  contact2_email    varchar(255)                               comment '联系人2-邮箱',
  credit_code       varchar(64)                                comment '统一社会信用代码',  
  enable_flag       char(1)         default 'Y' not null       comment '是否启用',
  remark            varchar(500)    default ''                 comment '备注',
  attr1             varchar(64)     default null               comment '预留字段1',
  attr2             varchar(255)     default null               comment '预留字段2',
  attr3             int(11)         default 0                  comment '预留字段3',
  attr4             int(11)         default 0                  comment '预留字段4',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (client_id)
) engine=innodb auto_increment=200 comment = '客户表';

-- ----------------------------
-- 6、单位表
-- ----------------------------
drop table if exists md_unit_measure;
create table md_unit_measure (
  measure_id         bigint(20)      not null auto_increment    comment '单位ID',
  measure_code       varchar(64)     not null                   comment '单位编码',
  measure_name       varchar(255)    not null                   comment '单位名称',
  primary_flag       char(1)         default 'Y' not null       comment '是否是主单位',
  primary_id         bigint(20)                                 comment '主单位ID',
  change_rate        double(12,4)                               comment '与主单位换算比例',  
  enable_flag       char(1)         default 'Y' not null        comment '是否启用',
  remark            varchar(500)    default ''                  comment '备注',
  attr1             varchar(64)     default null                comment '预留字段1',
  attr2             varchar(255)     default null               comment '预留字段2',
  attr3             int(11)         default 0                   comment '预留字段3',
  attr4             int(11)         default 0                   comment '预留字段4',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (measure_id)
) engine=innodb auto_increment=200 comment = '单位表';


-- ----------------------------
-- 7、车间表
-- ----------------------------
drop table if exists md_workshop;
create table md_workshop (
  workshop_id         bigint(20)      not null auto_increment      comment '车间ID',
  workshop_code       varchar(64)     not null                     comment '车间编码',
  workshop_name       varchar(255)    not null                     comment '车间名称',
  area                double(12,2)                                 comment '面积',
  charge              varchar(64)                                  comment '负责人',
  enable_flag         char(1)         default 'Y' not null         comment '是否启用',
  remark              varchar(500)    default ''                   comment '备注',
  attr1               varchar(64)     default null                 comment '预留字段1',
  attr2               varchar(255)     default null                comment '预留字段2',
  attr3               int(11)         default 0                    comment '预留字段3',
  attr4               int(11)         default 0                    comment '预留字段4',
  create_by           varchar(64)     default ''                   comment '创建者',
  create_time 	      datetime                                     comment '创建时间',
  update_by           varchar(64)     default ''                   comment '更新者',
  update_time         datetime                                     comment '更新时间',
  primary key (workshop_id)
) engine=innodb auto_increment=200 comment = '车间表';


-- ----------------------------
-- 8、工作站表
-- ----------------------------
drop table if exists md_workstation;
create table md_workstation (
  workstation_id         bigint(20)      not null auto_increment      comment '工作站ID',
  workstation_code       varchar(64)     not null                     comment '工作站编码',
  workstation_name       varchar(255)    not null                     comment '工作站名称',
  workstation_address    varchar(255)                                 comment '工作站地点',
  workshop_id            bigint(20)                                   comment '所在车间ID',
  workshop_code          varchar(64)                                  comment '所在车间编码',
  workshop_name          varchar(255)                                 comment '所在车间名称',
  process_id             bigint(20)                                   comment '工序ID',
  process_code           varchar(64)                                  comment '工序编码',
  process_name           varchar(255)                                 comment '工序名称',
  warehouse_id           bigint(20)      not null                     comment '线边库ID',
  warehouse_code         varchar(64)                                  comment '线边库编码',
  warehouse_name         varchar(255)                                 comment '线边库名称',
  location_id            bigint(20)      not null                     comment '库区ID',
  location_code          varchar(64)                                  comment '库区编码',
  location_name          varchar(255)                                 comment '库区名称',
  area_id                bigint(20)      not null                     comment '库位ID',
  area_code              varchar(64)                                  comment '库位编码',
  area_name              varchar(255)                                 comment '库位名称', 
  enable_flag            char(1)         default 'Y' not null         comment '是否启用',
  remark                 varchar(500)    default ''                   comment '备注',
  attr1                  varchar(64)     default null                 comment '预留字段1',
  attr2                  varchar(255)     default null                comment '预留字段2',
  attr3                  int(11)         default 0                    comment '预留字段3',
  attr4                  int(11)         default 0                    comment '预留字段4',
  create_by              varchar(64)     default ''                   comment '创建者',
  create_time 	         datetime                                     comment '创建时间',
  update_by              varchar(64)     default ''                   comment '更新者',
  update_time            datetime                                     comment '更新时间',
  primary key (workstation_id)
) engine=innodb auto_increment=200 comment = '工作站表';


-- ----------------------------
-- 9、设备资源表
-- ----------------------------
drop table if exists md_workstation_machine;
create table md_workstation_machine (
  record_id              bigint(20)      not null auto_increment      comment '记录ID',
  workstation_id         bigint(20)      not null                     comment '工作站ID',
  machinery_id           bigint(20)      not null                     comment '设备ID',
  machinery_code         varchar(64)                                  comment '设备编码',
  machinery_name         varchar(255)                                 comment '设备名称',
  quantity               int(4)          default 1                    comment '数量',
  remark                 varchar(500)    default ''                   comment '备注',
  attr1                  varchar(64)     default null                 comment '预留字段1',
  attr2                  varchar(255)     default null                comment '预留字段2',
  attr3                  int(11)         default 0                    comment '预留字段3',
  attr4                  int(11)         default 0                    comment '预留字段4',
  create_by              varchar(64)     default ''                   comment '创建者',
  create_time 	         datetime                                     comment '创建时间',
  update_by              varchar(64)     default ''                   comment '更新者',
  update_time            datetime                                     comment '更新时间',
  primary key (record_id)
) engine=innodb auto_increment=200 comment = '设备资源表';


-- ----------------------------
-- 10、人力资源表
-- ----------------------------
drop table if exists md_workstation_worker;
create table md_workstation_worker (
  record_id              bigint(20)      not null auto_increment      comment '记录ID',
  workstation_id         bigint(20)      not null                     comment '工作站ID',
  post_id                bigint(20)      not null                     comment '岗位ID',
  post_code              varchar(64)                                  comment '岗位编码',
  post_name              varchar(255)                                 comment '岗位名称',
  quantity               int(4)          default 1 not null           comment '数量',
  remark                 varchar(500)    default ''                   comment '备注',
  attr1                  varchar(64)     default null                 comment '预留字段1',
  attr2                  varchar(255)     default null                comment '预留字段2',
  attr3                  int(11)         default 0                    comment '预留字段3',
  attr4                  int(11)         default 0                    comment '预留字段4',
  create_by              varchar(64)     default ''                   comment '创建者',
  create_time 	         datetime                                     comment '创建时间',
  update_by              varchar(64)     default ''                   comment '更新者',
  update_time            datetime                                     comment '更新时间',
  primary key (record_id)
) engine=innodb auto_increment=200 comment = '人力资源表';


-- ----------------------------
-- 11、工装夹具资源表
-- ----------------------------
drop table if exists md_workstation_tool;
create table md_workstation_tool (
  record_id              bigint(20)      not null auto_increment      comment '记录ID',
  workstation_id         bigint(20)      not null                     comment '工作站ID',
  tool_type_id           bigint(20)      not null                     comment '工装夹具类型ID',
  tool_type_code         varchar(64)                                  comment '类型编码',
  tool_type_name         varchar(255)                                 comment '类型名称',
  quantity               int(4)          default 1 not null           comment '数量',
  remark                 varchar(500)    default ''                   comment '备注',
  attr1                  varchar(64)     default null                 comment '预留字段1',
  attr2                  varchar(255)     default null                comment '预留字段2',
  attr3                  int(11)         default 0                    comment '预留字段3',
  attr4                  int(11)         default 0                    comment '预留字段4',
  create_by              varchar(64)     default ''                   comment '创建者',
  create_time 	         datetime                                     comment '创建时间',
  update_by              varchar(64)     default ''                   comment '更新者',
  update_time            datetime                                     comment '更新时间',
  primary key (record_id)
) engine=innodb auto_increment=200 comment = '工装夹具资源表';


-- ----------------------------
-- 12、产品SOP表
-- ----------------------------
drop table if exists md_product_sop;
create table md_product_sop (
  sop_id                 bigint(20)      not null auto_increment      comment '记录ID',
  item_id                bigint(20)      not null                     comment '物料产品ID',
  order_num              int(4)                                       comment '排列顺序',
  process_id             bigint(20)                                   comment '对应的工序',
  process_code           varchar(64)                                  comment '工序编号',
  process_name           varchar(255)                                 comment '工序名称',
  sop_title               varchar(255)                                comment '标题',
  sop_description        varchar(500)                                 comment '详细描述',
  sop_url                varchar(255)                                 comment '图片地址',
  remark                 varchar(500)    default ''                   comment '备注',
  attr1                  varchar(64)     default null                 comment '预留字段1',
  attr2                  varchar(255)     default null                comment '预留字段2',
  attr3                  int(11)         default 0                    comment '预留字段3',
  attr4                  int(11)         default 0                    comment '预留字段4',
  create_by              varchar(64)     default ''                   comment '创建者',
  create_time 	         datetime                                     comment '创建时间',
  update_by              varchar(64)     default ''                   comment '更新者',
  update_time            datetime                                     comment '更新时间',
  primary key (sop_id)
) engine=innodb auto_increment=200 comment = '产品SOP表';


DROP TABLE IF EXISTS `md_product_sip`;
CREATE TABLE `md_product_sip`  (
  `sip_id` bigint(0) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `item_id` bigint(0) NOT NULL COMMENT '物料产品ID',
  `order_num` int(0) NULL DEFAULT NULL COMMENT '排列顺序',
  `process_id` bigint(0) NULL DEFAULT NULL COMMENT '对应的工序',
  `process_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工序编号',
  `process_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '工序名称',
  `sip_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标题',
  `sip_description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细描述',
  `sip_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片地址',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '备注',
  `attr1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '预留字段1',
  `attr2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '预留字段2',
  `attr3` int(0) NULL DEFAULT 0 COMMENT '预留字段3',
  `attr4` int(0) NULL DEFAULT 0 COMMENT '预留字段4',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`sip_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '产品SIP表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

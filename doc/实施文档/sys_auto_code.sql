/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云-MySQL
 Source Server Type    : MySQL
 Source Server Version : 50737
 Source Host           : 101.43.244.58:3306
 Source Schema         : j2eedb

 Target Server Type    : MySQL
 Target Server Version : 50737
 File Encoding         : 65001

 Date: 20/09/2022 13:59:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_auto_code_part
-- ----------------------------
DROP TABLE IF EXISTS `sys_auto_code_part`;
CREATE TABLE `sys_auto_code_part`  (
  `part_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分段ID',
  `rule_id` bigint(20) NOT NULL COMMENT '规则ID',
  `part_index` int(11) NOT NULL COMMENT '分段序号',
  `part_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分段类型，INPUTCHAR：输入字符，NOWDATE：当前日期时间，FIXCHAR：固定字符，SERIALNO：流水号',
  `part_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分段编号',
  `part_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '分段名称',
  `part_length` int(11) NOT NULL COMMENT '分段长度',
  `date_format` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `input_character` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '输入字符',
  `fix_character` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '固定字符',
  `seria_start_no` int(11) NULL DEFAULT NULL COMMENT '流水号起始值',
  `seria_step` int(11) NULL DEFAULT NULL COMMENT '流水号步长',
  `seria_now_no` int(11) NULL DEFAULT NULL COMMENT '流水号当前值',
  `cycle_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流水号是否循环',
  `cycle_method` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '循环方式，YEAR：按年，MONTH：按月，DAY：按天，HOUR：按小时，MINITE：按分钟，OTHER：按传入字符变',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `attr1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段1',
  `attr2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段2',
  `attr3` int(11) NULL DEFAULT 0 COMMENT '预留字段3',
  `attr4` int(11) NULL DEFAULT 0 COMMENT '预留字段4',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`part_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 289 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '编码生成规则组成表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_auto_code_part
-- ----------------------------
INSERT INTO `sys_auto_code_part` VALUES (200, 205, 2, 'SERIALNO', 'P1', '流水号', 8, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-04-26 21:13:17', 'admin', '2022-04-26 22:47:49');
INSERT INTO `sys_auto_code_part` VALUES (201, 205, 1, 'FIXCHAR', 'P0', '前缀', 4, NULL, NULL, 'ITEM', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-04-26 22:44:03', 'admin', '2022-08-15 15:59:19');
INSERT INTO `sys_auto_code_part` VALUES (202, 206, 1, 'FIXCHAR', 'P1', '前缀', 10, NULL, NULL, 'ITEM_TYPE_', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-04-26 23:02:12', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (203, 206, 2, 'SERIALNO', 'P2', '流水号', 4, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-04-26 23:02:42', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (204, 207, 1, 'FIXCHAR', 'PREFIX', '前缀', 1, NULL, NULL, 'C', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-06 21:21:04', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (205, 207, 2, 'SERIALNO', 'SERIAL', '流水号部分', 5, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-06 21:21:44', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (206, 208, 1, 'FIXCHAR', 'PREFIX', '前缀', 1, NULL, NULL, 'V', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-06 22:50:38', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (207, 208, 2, 'SERIALNO', 'SERIAL', '流水号', 5, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-06 22:51:02', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (208, 209, 1, 'FIXCHAR', 'PREFIX', '前缀', 2, NULL, NULL, 'WS', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-07 17:49:16', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (209, 209, 2, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-07 17:49:40', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (210, 210, 1, 'FIXCHAR', 'PREFIX', '前缀', 2, NULL, NULL, 'WH', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-07 22:00:17', 'admin', '2022-08-16 18:58:36');
INSERT INTO `sys_auto_code_part` VALUES (211, 210, 2, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-07 22:00:40', 'admin', '2022-07-30 11:26:14');
INSERT INTO `sys_auto_code_part` VALUES (212, 211, 1, 'FIXCHAR', 'PREFIX', '前缀', 1, NULL, NULL, 'L', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-08 14:50:29', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (213, 211, 2, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-08 14:52:12', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (214, 212, 1, 'FIXCHAR', 'PREFIX', '前缀', 1, NULL, NULL, 'A', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-08 18:38:29', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (215, 212, 2, 'SERIALNO', 'SERIAL', '流水号', 4, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-08 18:38:51', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (216, 213, 1, 'FIXCHAR', 'PREFIX', '前缀', 7, NULL, NULL, 'M_TYPE_', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-08 19:46:42', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (217, 213, 2, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-08 19:47:03', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (218, 214, 1, 'FIXCHAR', 'PREFIX', '前缀', 1, NULL, NULL, 'M', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-08 21:26:59', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (219, 214, 2, 'SERIALNO', 'SERIAL', '流水号', 4, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-08 21:27:18', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (220, 215, 1, 'FIXCHAR', 'PREFIX', '前缀', 2, NULL, NULL, 'MO', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-09 11:40:23', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (222, 215, 2, 'NOWDATE', 'DATEPART', '年月日部分', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-09 11:58:57', 'admin', '2022-05-09 12:46:34');
INSERT INTO `sys_auto_code_part` VALUES (223, 215, 3, 'SERIALNO', 'SERIAL', '流水号部分', 4, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'DAY', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-09 11:59:31', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (224, 216, 1, 'FIXCHAR', 'PREFIX', '前缀', 2, NULL, NULL, 'WS', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-10 21:55:51', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (225, 216, 2, 'SERIALNO', 'SERIAL', '流水号', 4, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-10 21:56:19', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (226, 217, 1, 'FIXCHAR', 'PREFIX', '前缀', 2, NULL, NULL, 'TT', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-11 00:22:02', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (227, 217, 2, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-11 00:22:25', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (228, 218, 1, 'FIXCHAR', 'PREFIX', '前缀', 1, NULL, NULL, 'T', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-11 22:07:44', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (229, 218, 2, 'SERIALNO', 'SERIAL', '流水号', 5, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-11 22:08:17', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (230, 219, 1, 'FIXCHAR', 'PREFIX', '前缀', 7, NULL, NULL, 'PROCESS', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-12 00:10:13', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (231, 219, 2, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-12 00:10:33', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (232, 220, 1, 'FIXCHAR', 'PREFIX', '前缀', 1, NULL, NULL, 'R', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-12 23:07:01', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (233, 220, 2, 'SERIALNO', 'SERIAL', '流水号', 4, NULL, NULL, NULL, 1000, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-12 23:07:23', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (234, 221, 1, 'FIXCHAR', 'PREFIX', '固定前缀', 4, NULL, NULL, 'TASK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-15 18:22:53', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (235, 221, 2, 'NOWDATE', 'YEAR', '年份', 4, 'yyyy', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-15 18:23:39', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (236, 221, 3, 'SERIALNO', 'SERIAL', '流水号', 4, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'YEAR', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-15 18:24:03', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (237, 222, 1, 'FIXCHAR', 'PREFIX', '前缀', 1, NULL, NULL, 'I', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-17 21:57:46', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (238, 222, 2, 'SERIALNO', 'SERIAL', '流水号', 4, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-17 21:58:05', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (239, 223, 1, 'FIXCHAR', 'PREFIX', '前缀', 3, NULL, NULL, 'QCT', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-17 22:43:31', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (240, 223, 2, 'NOWDATE', 'YEAR', '年份', 4, 'yyyy', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-17 22:44:04', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (241, 223, 3, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'YEAR', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-17 22:44:25', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (242, 224, 1, 'FIXCHAR', 'PREFIX', '前缀', 2, NULL, NULL, 'DF', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-19 11:33:52', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (243, 224, 2, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-19 11:34:11', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (244, 225, 1, 'FIXCHAR', 'PREFIX', '前缀', 3, NULL, NULL, 'IQC', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-19 16:29:59', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (245, 225, 2, 'NOWDATE', 'DATE', '年月日', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-19 16:30:28', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (246, 225, 3, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'DAY', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-19 16:31:00', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (247, 226, 1, 'FIXCHAR', 'PREFIX', '前缀', 1, NULL, NULL, 'R', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-22 20:51:47', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (248, 226, 2, 'NOWDATE', 'DATE', '日期', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-05-22 20:52:10', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (249, 226, 3, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'DAY', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-22 20:52:58', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (250, 227, 1, 'FIXCHAR', 'PREFIX', '固定前缀', 1, NULL, NULL, 'T', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-06-06 19:54:45', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (251, 227, 2, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-06-06 19:55:06', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (252, 228, 1, 'FIXCHAR', 'PREFIX', '前缀', 4, NULL, NULL, 'PLAN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-06-06 22:08:39', 'admin', '2022-07-31 16:42:59');
INSERT INTO `sys_auto_code_part` VALUES (253, 228, 2, 'NOWDATE', 'YEAR', '年', 4, 'yyyy', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-06-06 22:08:59', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (254, 228, 3, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'YEAR', NULL, NULL, NULL, 0, 0, 'admin', '2022-06-06 22:09:24', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (255, 229, 1, 'FIXCHAR', 'PREFIX', '前缀', 3, NULL, NULL, 'RTV', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-06-13 16:06:14', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (256, 229, 2, 'NOWDATE', 'DATE', '日期', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-06-13 16:06:42', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (257, 229, 3, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'DAY', NULL, NULL, NULL, 0, 0, 'admin', '2022-06-13 16:07:10', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (258, 230, 1, 'FIXCHAR', 'PREFIX', '固定前缀', 3, NULL, NULL, 'SUB', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-06-16 20:28:22', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (259, 230, 2, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-06-16 20:28:44', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (260, 231, 1, 'FIXCHAR', 'PREFIX', '前缀', 4, NULL, NULL, 'PLAN', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-06-16 21:50:22', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (261, 231, 2, 'NOWDATE', 'YEAR', '年份', 4, 'yyyy', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-06-16 21:50:43', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (262, 231, 3, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'YEAR', NULL, NULL, NULL, 0, 0, 'admin', '2022-06-16 21:51:07', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (263, 232, 1, 'FIXCHAR', '1', '1', 3, NULL, NULL, 'BAT', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-07-14 12:02:54', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (264, 232, 2, 'NOWDATE', '2', '2', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-07-14 12:03:16', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (265, 233, 1, 'FIXCHAR', 'PREFIX', '前缀', 5, NULL, NULL, 'ISSUE', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-07-17 19:32:46', 'admin', '2022-07-17 19:35:44');
INSERT INTO `sys_auto_code_part` VALUES (266, 233, 2, 'NOWDATE', 'DATE', '年月日', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-07-17 19:33:22', 'admin', '2022-07-17 19:35:57');
INSERT INTO `sys_auto_code_part` VALUES (267, 233, 3, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'DAY', NULL, NULL, NULL, 0, 0, 'admin', '2022-07-17 19:33:45', 'admin', '2022-07-17 19:36:05');
INSERT INTO `sys_auto_code_part` VALUES (271, 234, 1, 'INPUTCHAR', 'PREFIX', '1', 3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-07-30 14:20:49', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (275, 236, 1, 'FIXCHAR', '前缀', '固定字符', 2, NULL, NULL, 'IF', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-08-19 10:48:20', 'admin', '2022-08-19 14:13:30');
INSERT INTO `sys_auto_code_part` VALUES (276, 236, 2, 'NOWDATE', '后缀', '固定字段', 4, 'yyyyMMddss', NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-08-19 14:12:19', 'admin', '2022-08-19 14:19:39');
INSERT INTO `sys_auto_code_part` VALUES (277, 237, 1, 'FIXCHAR', 'PREFIX', '前缀', 4, NULL, NULL, 'IPQC', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-08-29 22:07:43', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (278, 237, 2, 'NOWDATE', 'DATE', '年月日', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-08-29 22:08:18', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (279, 237, 3, 'SERIALNO', 'SERIAL', '流水号', 4, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'DAY', NULL, NULL, NULL, 0, 0, 'admin', '2022-08-29 22:08:46', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (280, 238, 1, 'FIXCHAR', 'PREFIX', '前缀', 3, NULL, NULL, 'OQC', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-09-01 20:30:53', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (281, 238, 2, 'NOWDATE', 'DATE', '日期', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-09-01 20:32:11', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (282, 238, 3, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'DAY', NULL, NULL, NULL, 0, 0, 'admin', '2022-09-01 20:32:38', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (283, 239, 1, 'FIXCHAR', '001', '前缀', 5, NULL, NULL, 'PBACK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-09-03 23:49:07', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (284, 239, 2, 'NOWDATE', '002', '日期', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-09-03 23:49:31', 'admin', '2022-09-03 23:49:44');
INSERT INTO `sys_auto_code_part` VALUES (285, 239, 3, 'SERIALNO', '003', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'N', NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-09-03 23:50:10', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (286, 240, 1, 'FIXCHAR', 'PREFIX', '前缀', 2, NULL, NULL, 'RT', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-09-15 23:19:25', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (287, 240, 2, 'NOWDATE', 'DATE', '日期', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-09-15 23:19:47', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (288, 240, 3, 'SERIALNO', 'SERIAL', '流水号', 4, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'DAY', NULL, NULL, NULL, 0, 0, 'admin', '2022-09-15 23:20:09', '', NULL);

-- ----------------------------
-- Table structure for sys_auto_code_result
-- ----------------------------
DROP TABLE IF EXISTS `sys_auto_code_result`;
CREATE TABLE `sys_auto_code_result`  (
  `code_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `rule_id` bigint(20) NOT NULL COMMENT '规则ID',
  `gen_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '生成日期时间',
  `gen_index` int(11) NULL DEFAULT NULL COMMENT '最后产生的序号',
  `last_result` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后产生的值',
  `last_serial_no` int(11) NULL DEFAULT NULL COMMENT '最后产生的流水号',
  `last_input_char` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '最后传入的参数',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `attr1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段1',
  `attr2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段2',
  `attr3` int(11) NULL DEFAULT 0 COMMENT '预留字段3',
  `attr4` int(11) NULL DEFAULT 0 COMMENT '预留字段4',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`code_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 420 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '编码生成记录表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_auto_code_result
-- ----------------------------
INSERT INTO `sys_auto_code_result` VALUES (200, 205, '20220808095803', 76, 'ITEM00000076', 76, NULL, '', NULL, NULL, 0, 0, '', '2022-04-26 22:28:52', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (201, 206, '20220813231829', 62, 'ITEM_TYPE_0062', 62, NULL, '', NULL, NULL, 0, 0, '', '2022-04-27 10:17:19', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (202, 207, '20220802144206', 10, 'C00010', 10, NULL, '', NULL, NULL, 0, 0, '', '2022-05-06 21:22:23', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (203, 208, '20220805160743', 10, 'V00010', 10, NULL, '', NULL, NULL, 0, 0, '', '2022-05-06 22:52:14', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (204, 209, '20220805111938', 12, 'WS012', 12, NULL, '', NULL, NULL, 0, 0, '', '2022-05-07 17:54:01', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (205, 210, '20220729090506', 2, 'WH002', 2, NULL, '', NULL, NULL, 0, 0, '', '2022-05-07 22:01:31', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (206, 211, '20220729085518', 10, 'L010', 10, NULL, '', NULL, NULL, 0, 0, '', '2022-05-08 14:52:34', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (207, 212, '20220810082205', 11, 'A0011', 11, NULL, '', NULL, NULL, 0, 0, '', '2022-05-08 18:39:15', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (208, 213, '20220823091541', 24, 'M_TYPE_024', 24, NULL, '', NULL, NULL, 0, 0, '', '2022-05-08 19:47:31', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (209, 214, '20220819091251', 26, 'M0026', 26, NULL, '', NULL, NULL, 0, 0, '', '2022-05-08 21:38:26', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (210, 215, '20220509234547', 21, 'MO202205090021', 21, NULL, '', NULL, NULL, 0, 0, '', '2022-05-09 12:46:53', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (212, 216, '20220818103207', 34, 'WS0034', 34, NULL, '', NULL, NULL, 0, 0, '', '2022-05-10 21:59:44', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (213, 217, '20220816195823', 25, 'TT025', 25, NULL, '', NULL, NULL, 0, 0, '', '2022-05-11 00:22:40', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (214, 218, '20220804162652', 10, 'T00010', 10, NULL, '', NULL, NULL, 0, 0, '', '2022-05-11 22:35:05', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (215, 219, '20220729122036', 46, 'PROCESS046', 46, NULL, '', NULL, NULL, 0, 0, '', '2022-05-12 00:10:54', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (217, 220, '20220809171633', 29, 'R1028', 1028, NULL, '', NULL, NULL, 0, 0, '', '2022-05-12 23:11:24', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (219, 215, '20220514155158', 26, 'MO202205140026', 26, NULL, '', NULL, NULL, 0, 0, '', '2022-05-14 15:24:54', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (220, 215, '20220515134342', 5, 'MO202205150005', 5, NULL, '', NULL, NULL, 0, 0, '', '2022-05-15 13:41:34', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (221, 221, '20220828090047', 69, 'TASK20220069', 69, NULL, '', NULL, NULL, 0, 0, '', '2022-05-15 18:29:41', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (222, 222, '20220812142031', 13, 'I0013', 13, NULL, '', NULL, NULL, 0, 0, '', '2022-05-17 21:58:16', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (223, 223, '20220901212456', 42, 'QCT2022042', 42, NULL, '', NULL, NULL, 0, 0, '', '2022-05-17 22:44:37', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (224, 224, '20220819144546', 8, 'DF008', 8, NULL, '', NULL, NULL, 0, 0, '', '2022-05-19 11:37:14', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (225, 225, '20220519230422', 21, 'IQC20220519021', 21, NULL, '', NULL, NULL, 0, 0, '', '2022-05-19 16:49:49', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (226, 226, '20220522220740', 8, 'R20220522008', 8, NULL, '', NULL, NULL, 0, 0, '', '2022-05-22 20:53:25', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (227, 227, '20220814214249', 25, 'T025', 25, NULL, '', NULL, NULL, 0, 0, '', '2022-06-06 19:55:18', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (228, 228, '20220823150049', 121, 'PLAN2022121', 121, NULL, '', NULL, NULL, 0, 0, '', '2022-06-06 22:09:41', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (229, 229, '20220613162434', 2, 'RTV20220613002', 2, NULL, '', NULL, NULL, 0, 0, '', '2022-06-13 16:19:52', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (230, 226, '20220613164021', 1, 'R20220613001', 1, NULL, '', NULL, NULL, 0, 0, '', '2022-06-13 16:40:22', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (231, 229, '20220614161107', 3, 'RTV20220614003', 3, NULL, '', NULL, NULL, 0, 0, '', '2022-06-14 11:11:21', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (232, 226, '20220614122043', 3, 'R20220614003', 3, NULL, '', NULL, NULL, 0, 0, '', '2022-06-14 11:34:53', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (233, 230, '20220812221323', 4, 'SUB004', 4, NULL, '', NULL, NULL, 0, 0, '', '2022-06-16 20:29:01', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (234, 231, '20220901230257', 50, 'PLAN2022050', 50, NULL, '', NULL, NULL, 0, 0, '', '2022-06-16 21:51:19', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (235, 226, '20220717124046', 2, 'R20220717002', 2, NULL, '', NULL, NULL, 0, 0, '', '2022-07-17 12:40:42', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (236, 233, '20220717194751', 2, 'ISSUE20220717002', 2, NULL, '', NULL, NULL, 0, 0, '', '2022-07-17 19:36:18', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (237, 233, '20220725150745', 8, 'ISSUE20220725008', 8, NULL, '', NULL, NULL, 0, 0, '', '2022-07-25 11:16:26', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (238, 215, '20220727133912', 3, 'MO202207270003', 3, NULL, '', NULL, NULL, 0, 0, '', '2022-07-27 13:39:10', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (239, 229, '20220727135402', 3, 'RTV20220727003', 3, NULL, '', NULL, NULL, 0, 0, '', '2022-07-27 13:53:57', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (240, 215, '20220728231952', 1, 'MO202207280001', 1, NULL, '', NULL, NULL, 0, 0, '', '2022-07-28 23:19:52', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (241, 211, '20220729085519', 1, 'L011', 11, NULL, '', NULL, NULL, 0, 0, '', '2022-07-29 08:55:19', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (242, 211, '20220801170339', 6, 'L017', 17, NULL, '', NULL, NULL, 0, 0, '', '2022-07-29 08:55:20', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (243, 229, '20220729091253', 4, 'RTV20220729004', 4, NULL, '', NULL, NULL, 0, 0, '', '2022-07-29 08:56:52', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (244, 210, '20220729090520', 3, 'WH005', 5, NULL, '', NULL, NULL, 0, 0, '', '2022-07-29 09:05:11', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (245, 210, '20220801161858', 52, 'WH060', 60, NULL, '', NULL, NULL, 0, 0, '', '2022-07-29 09:05:39', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (246, 215, '20220729111608', 3, 'MO202207290003', 3, NULL, '', NULL, NULL, 0, 0, '', '2022-07-29 09:30:33', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (247, 219, '20220729122132', 3, 'PROCESS049', 49, NULL, '', NULL, NULL, 0, 0, '', '2022-07-29 12:21:02', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (248, 215, '20220730170708', 6, 'MO202207300006', 6, NULL, '', NULL, NULL, 0, 0, '', '2022-07-30 14:41:08', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (249, 226, '20220730164005', 3, 'R20220730003', 3, NULL, '', NULL, NULL, 0, 0, '', '2022-07-30 16:30:37', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (250, 219, '20220730170439', 3, 'PROCESS052', 52, NULL, '', NULL, NULL, 0, 0, '', '2022-07-30 17:02:43', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (251, 215, '20220731161554', 1, 'MO202207310001', 1, NULL, '', NULL, NULL, 0, 0, '', '2022-07-31 16:15:54', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (252, 226, '20220801162854', 33, 'R20220801033', 33, NULL, '', NULL, NULL, 0, 0, '', '2022-08-01 11:11:28', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (253, 215, '20220801154407', 1, 'MO202208010001', 1, NULL, '', NULL, NULL, 0, 0, '', '2022-08-01 15:44:07', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (254, 210, '20220801161857', 1, 'WH060', 60, NULL, '', NULL, NULL, 0, 0, '', '2022-08-01 16:18:57', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (255, 210, '20220801161857', 1, 'WH060', 60, NULL, '', NULL, NULL, 0, 0, '', '2022-08-01 16:18:57', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (256, 210, '20220809140656', 13, 'WH073', 73, NULL, '', NULL, NULL, 0, 0, '', '2022-08-01 16:26:43', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (257, 219, '20220801172652', 3, 'PROCESS055', 55, NULL, '', NULL, NULL, 0, 0, '', '2022-08-01 17:14:02', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (258, 215, '20220802174313', 13, 'MO202208020013', 13, NULL, '', NULL, NULL, 0, 0, '', '2022-08-02 09:27:31', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (259, 225, '20220802180956', 2, 'IQC20220802002', 2, NULL, '', NULL, NULL, 0, 0, '', '2022-08-02 17:54:11', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (260, 229, '20220802180930', 2, 'RTV20220802002', 2, NULL, '', NULL, NULL, 0, 0, '', '2022-08-02 18:01:18', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (261, 215, '20220803152758', 3, 'MO202208030003', 3, NULL, '', NULL, NULL, 0, 0, '', '2022-08-03 15:19:17', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (262, 218, '20220810105807', 7, 'T00017', 17, NULL, '', NULL, NULL, 0, 0, '', '2022-08-04 16:34:19', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (263, 209, '20220813133944', 14, 'WS026', 26, NULL, '', NULL, NULL, 0, 0, '', '2022-08-05 11:19:39', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (264, 226, '20220805131441', 4, 'R20220805004', 4, NULL, '', NULL, NULL, 0, 0, '', '2022-08-05 13:09:41', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (265, 208, '20220808161115', 4, 'V00014', 14, NULL, '', NULL, NULL, 0, 0, '', '2022-08-05 16:27:10', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (266, 205, '20220812160022', 12, 'ITEM00000088', 88, NULL, '', NULL, NULL, 0, 0, '', '2022-08-08 10:01:12', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (267, 229, '20220808105742', 2, 'RTV20220808002', 2, NULL, '', NULL, NULL, 0, 0, '', '2022-08-08 10:44:56', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (268, 207, '20220813112402', 14, 'C00024', 24, NULL, '', NULL, NULL, 0, 0, '', '2022-08-08 15:52:04', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (269, 208, '20220814231644', 10, 'V00024', 24, NULL, '', NULL, NULL, 0, 0, '', '2022-08-08 16:11:16', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (270, 219, '20220809091746', 2, 'PROCESS057', 57, NULL, '', NULL, NULL, 0, 0, '', '2022-08-09 09:17:40', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (271, 219, '20220813232649', 11, 'PROCESS068', 68, NULL, '', NULL, NULL, 0, 0, '', '2022-08-09 09:22:42', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (272, 215, '20220809173457', 7, 'MO202208090007', 7, NULL, '', NULL, NULL, 0, 0, '', '2022-08-09 12:27:34', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (273, 210, '20220809140657', 1, 'WH074', 74, NULL, '', NULL, NULL, 0, 0, '', '2022-08-09 14:06:57', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (274, 210, '20220814231315', 5, 'WH079', 79, NULL, '', NULL, NULL, 0, 0, '', '2022-08-09 14:06:58', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (275, 220, '20220813113700', 12, 'R1040', 1040, NULL, '', NULL, NULL, 0, 0, '', '2022-08-09 17:16:34', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (276, 226, '20220809174609', 2, 'R20220809002', 2, NULL, '', NULL, NULL, 0, 0, '', '2022-08-09 17:36:22', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (277, 211, '20220810082146', 5, 'L022', 22, NULL, '', NULL, NULL, 0, 0, '', '2022-08-10 08:21:39', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (278, 211, '20220824224223', 20, 'L042', 42, NULL, '', NULL, NULL, 0, 0, '', '2022-08-10 08:21:48', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (279, 212, '20220810082207', 2, 'A0013', 13, NULL, '', NULL, NULL, 0, 0, '', '2022-08-10 08:22:06', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (280, 212, '20220810083445', 4, 'A0017', 17, NULL, '', NULL, NULL, 0, 0, '', '2022-08-10 08:22:08', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (281, 218, '20220814194551', 6, 'T00023', 23, NULL, '', NULL, NULL, 0, 0, '', '2022-08-10 10:58:10', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (282, 215, '20220810141929', 7, 'MO202208100007', 7, NULL, '', NULL, NULL, 0, 0, '', '2022-08-10 12:30:21', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (283, 215, '20220811155054', 2, 'MO202208110002', 2, NULL, '', NULL, NULL, 0, 0, '', '2022-08-11 15:50:52', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (284, 215, '20220812163345', 36, 'MO202208120036', 36, NULL, '', NULL, NULL, 0, 0, '', '2022-08-12 11:40:15', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (285, 225, '20220812202238', 5, 'IQC20220812005', 5, NULL, '', NULL, NULL, 0, 0, '', '2022-08-12 13:54:10', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (286, 222, '20220822090243', 21, 'I0034', 34, NULL, '', NULL, NULL, 0, 0, '', '2022-08-12 14:20:34', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (287, 205, '20220813012808', 21, 'ITEM00000109', 109, NULL, '', NULL, NULL, 0, 0, '', '2022-08-12 16:00:23', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (288, 226, '20220812214513', 3, 'R20220812003', 3, NULL, '', NULL, NULL, 0, 0, '', '2022-08-12 16:51:18', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (289, 205, '20220813133124', 10, 'ITEM00000119', 119, NULL, '', NULL, NULL, 0, 0, '', '2022-08-13 01:28:09', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (290, 226, '20220813152657', 5, 'R20220813005', 5, NULL, '', NULL, NULL, 0, 0, '', '2022-08-13 01:47:52', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (291, 215, '20220813232357', 10, 'MO202208130010', 10, NULL, '', NULL, NULL, 0, 0, '', '2022-08-13 08:50:33', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (292, 207, '20220813134013', 7, 'C00031', 31, NULL, '', NULL, NULL, 0, 0, '', '2022-08-13 11:24:03', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (293, 220, '20220815153054', 12, 'R1052', 1052, NULL, '', NULL, NULL, 0, 0, '', '2022-08-13 11:37:04', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (294, 209, '20220816172957', 8, 'WS034', 34, NULL, '', NULL, NULL, 0, 0, '', '2022-08-13 13:39:46', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (295, 207, '20220813134017', 6, 'C00037', 37, NULL, '', NULL, NULL, 0, 0, '', '2022-08-13 13:40:15', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (296, 207, '20220815132333', 20, 'C00057', 57, NULL, '', NULL, NULL, 0, 0, '', '2022-08-13 13:40:18', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (297, 225, '20220813225044', 3, 'IQC20220813003', 3, NULL, '', NULL, NULL, 0, 0, '', '2022-08-13 15:14:15', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (298, 205, '20220815085111', 12, 'ITEM00000131', 131, NULL, '', NULL, NULL, 0, 0, '', '2022-08-13 21:48:41', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (299, 230, '20220815103546', 5, 'SUB009', 9, NULL, '', NULL, NULL, 0, 0, '', '2022-08-13 22:00:17', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (300, 206, '20220815143043', 4, 'ITEM_TYPE_0066', 66, NULL, '', NULL, NULL, 0, 0, '', '2022-08-13 23:18:41', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (301, 219, '20220815164921', 9, 'PROCESS077', 77, NULL, '', NULL, NULL, 0, 0, '', '2022-08-13 23:32:16', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (302, 215, '20220814231425', 5, 'MO202208140005', 5, NULL, '', NULL, NULL, 0, 0, '', '2022-08-14 10:40:29', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (303, 212, '20220818105233', 4, 'A0021', 21, NULL, '', NULL, NULL, 0, 0, '', '2022-08-14 11:19:56', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (304, 227, '20220814214249', 1, 'T026', 26, NULL, '', NULL, NULL, 0, 0, '', '2022-08-14 21:42:49', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (305, 227, '20220817195511', 33, 'T058', 58, NULL, '', NULL, NULL, 0, 0, '', '2022-08-14 21:42:54', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (306, 210, '20220817170523', 21, 'WH100', 100, NULL, '', NULL, NULL, 0, 0, '', '2022-08-14 23:13:23', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (307, 208, '20220816133617', 7, 'V00031', 31, NULL, '', NULL, NULL, 0, 0, '', '2022-08-14 23:16:45', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (308, 205, '20220815110633', 5, 'ITEM00000136', 136, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 08:51:13', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (309, 218, '20220823093332', 36, 'T00059', 59, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 09:53:55', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (310, 215, '20220815185151', 18, 'MO202208150018', 18, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 10:12:07', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (311, 226, '20220815143729', 8, 'R20220815008', 8, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 10:44:44', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (312, 205, '20220815140036', 3, 'ITEM00000139', 139, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 11:06:35', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (313, 225, '20220815111142', 1, 'IQC20220815001', 1, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 11:11:42', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (314, 207, '20220815132338', 6, 'C00063', 63, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 13:23:35', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (315, 207, '20220815132340', 4, 'C00067', 67, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 13:23:39', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (316, 207, '20220822100445', 95, 'C00162', 162, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 13:23:41', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (317, 230, '20220818164931', 21, 'SUB030', 30, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 14:40:05', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (318, 205, '20220817093425', 51, 'ITEM00000190', 190, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 15:43:11', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (319, 219, '20220818141839', 28, 'PROCESS105', 105, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 16:50:54', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (320, 229, '20220815172658', 2, 'RTV20220815002', 2, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 17:12:49', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (321, 220, '20220815200538', 2, 'R1054', 1054, NULL, '', NULL, NULL, 0, 0, '', '2022-08-15 19:52:44', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (322, 220, '20220816155501', 6, 'R1060', 1060, NULL, '', NULL, NULL, 0, 0, '', '2022-08-16 00:59:25', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (323, 226, '20220816173419', 18, 'R20220816018', 18, NULL, '', NULL, NULL, 0, 0, '', '2022-08-16 09:24:37', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (324, 215, '20220816232550', 38, 'MO202208160038', 38, NULL, '', NULL, NULL, 0, 0, '', '2022-08-16 10:18:25', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (325, 225, '20220816201615', 4, 'IQC20220816004', 4, NULL, '', NULL, NULL, 0, 0, '', '2022-08-16 10:32:14', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (326, 208, '20220816140813', 5, 'V00036', 36, NULL, '', NULL, NULL, 0, 0, '', '2022-08-16 13:36:19', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (327, 208, '20220823111843', 12, 'V00048', 48, NULL, '', NULL, NULL, 0, 0, '', '2022-08-16 14:08:15', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (328, 229, '20220816195356', 6, 'RTV20220816006', 6, NULL, '', NULL, NULL, 0, 0, '', '2022-08-16 17:35:14', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (329, 220, '20220818105353', 6, 'R1066', 1066, NULL, '', NULL, NULL, 0, 0, '', '2022-08-16 19:55:41', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (330, 217, '20220819093714', 7, 'TT032', 32, NULL, '', NULL, NULL, 0, 0, '', '2022-08-16 19:58:24', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (331, 215, '20220817222518', 14, 'MO202208170014', 14, NULL, '', NULL, NULL, 0, 0, '', '2022-08-17 08:47:52', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (332, 209, '20220820094836', 17, 'WS051', 51, NULL, '', NULL, NULL, 0, 0, '', '2022-08-17 08:55:43', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (333, 229, '20220817091138', 2, 'RTV20220817002', 2, NULL, '', NULL, NULL, 0, 0, '', '2022-08-17 09:11:36', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (334, 205, '20220818115033', 32, 'ITEM00000222', 222, NULL, '', NULL, NULL, 0, 0, '', '2022-08-17 09:34:26', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (335, 206, '20220822091118', 19, 'ITEM_TYPE_0085', 85, NULL, '', NULL, NULL, 0, 0, '', '2022-08-17 11:14:35', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (336, 226, '20220817205507', 22, 'R20220817022', 22, NULL, '', NULL, NULL, 0, 0, '', '2022-08-17 14:03:20', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (337, 225, '20220817203604', 5, 'IQC20220817005', 5, NULL, '', NULL, NULL, 0, 0, '', '2022-08-17 15:57:28', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (338, 210, '20220817170524', 1, 'WH101', 101, NULL, '', NULL, NULL, 0, 0, '', '2022-08-17 17:05:24', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (339, 233, '20220817231312', 6, 'ISSUE20220817006', 6, NULL, '', NULL, NULL, 0, 0, '', '2022-08-17 23:02:13', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (340, 226, '20220818211659', 12, 'R20220818012', 12, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 09:30:26', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (341, 210, '20220822145345', 35, 'WH136', 136, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 09:39:40', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (342, 225, '20220818165508', 12, 'IQC20220818012', 12, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 09:41:21', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (343, 229, '20220818180526', 7, 'RTV20220818007', 7, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 10:10:40', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (344, 215, '20220818230633', 77, 'MO202208180077', 77, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 10:33:04', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (345, 220, '20220819091351', 17, 'R1083', 1083, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 10:53:54', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (346, 212, '20220820170019', 5, 'A0026', 26, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 11:07:01', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (347, 216, '20220818164828', 11, 'WS0045', 45, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 11:39:49', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (348, 227, '20220818160823', 8, 'T066', 66, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 13:40:18', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (349, 205, '20220818134540', 1, 'ITEM00000223', 223, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 13:45:40', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (350, 205, '20220818141238', 3, 'ITEM00000226', 226, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 14:04:54', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (351, 205, '20220819101459', 33, 'ITEM00000259', 259, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 14:13:35', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (352, 219, '20220818144345', 2, 'PROCESS107', 107, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 14:43:21', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (353, 227, '20220818175718', 3, 'T069', 69, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 16:08:27', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (354, 216, '20220910104817', 51, 'WS0096', 96, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 16:48:30', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (355, 230, '20220823102743', 18, 'SUB048', 48, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 16:56:44', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (356, 227, '20220820110823', 7, 'T076', 76, NULL, '', NULL, NULL, 0, 0, '', '2022-08-18 17:57:45', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (357, 219, '20220824212645', 79, 'PROCESS186', 186, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 00:22:13', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (358, 226, '20220819211451', 22, 'R20220819022', 22, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 08:46:50', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (359, 215, '20220819211957', 44, 'MO202208190044', 44, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 08:47:15', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (360, 225, '20220819213654', 41, 'IQC20220819041', 41, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 08:49:46', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (361, 214, '20220821094813', 15, 'M0041', 41, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 09:12:52', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (362, 220, '20220819173023', 13, 'R1096', 1096, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 09:13:52', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (363, 217, '20220821133355', 15, 'TT047', 47, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 09:37:15', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (364, 236, '20220819111319', 3, '32323232323232323232323232ITEM_TYPE_', 14, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 11:13:16', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (365, 236, '20220819142129', 64, 'IF2022081929', 72, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 11:16:04', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (366, 236, '20220819145515', 12, 'IF2022081915', 1094, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 14:21:30', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (367, 236, '20220819205101', 16, 'IF2022081901', 21, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 16:18:20', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (368, 236, '20220823103219', 76, 'IF2022082319', 48, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 20:51:03', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (369, 229, '20220819205514', 1, 'RTV20220819001', 1, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 20:55:14', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (370, 220, '20220824213017', 25, 'R1121', 1121, NULL, '', NULL, NULL, 0, 0, '', '2022-08-19 21:12:27', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (371, 209, '20220820160129', 7, 'WS058', 58, NULL, '', NULL, NULL, 0, 0, '', '2022-08-20 09:48:37', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (372, 215, '20220820222241', 39, 'MO202208200039', 39, NULL, '', NULL, NULL, 0, 0, '', '2022-08-20 10:00:14', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (373, 226, '20220820215444', 21, 'R20220820021', 21, NULL, '', NULL, NULL, 0, 0, '', '2022-08-20 10:13:38', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (374, 227, '20220820110825', 2, 'T078', 78, NULL, '', NULL, NULL, 0, 0, '', '2022-08-20 11:08:24', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (375, 227, '20220820220731', 17, 'T095', 95, NULL, '', NULL, NULL, 0, 0, '', '2022-08-20 11:08:26', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (376, 225, '20220820160353', 3, 'IQC20220820003', 3, NULL, '', NULL, NULL, 0, 0, '', '2022-08-20 13:27:37', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (377, 209, '20220824222208', 21, 'WS079', 79, NULL, '', NULL, NULL, 0, 0, '', '2022-08-20 16:01:30', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (378, 227, '20220823083639', 10, 'T105', 105, NULL, '', NULL, NULL, 0, 0, '', '2022-08-20 22:07:33', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (379, 215, '20220821192938', 5, 'MO202208210005', 5, NULL, '', NULL, NULL, 0, 0, '', '2022-08-21 00:51:58', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (380, 214, '20220821094815', 1, 'M0042', 42, NULL, '', NULL, NULL, 0, 0, '', '2022-08-21 09:48:15', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (381, 212, '20220902103729', 4, 'A0030', 30, NULL, '', NULL, NULL, 0, 0, '', '2022-08-21 13:28:13', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (382, 214, '20220823173340', 13, 'M0055', 55, NULL, '', NULL, NULL, 0, 0, '', '2022-08-21 13:31:39', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (383, 217, '20220823205336', 11, 'TT058', 58, NULL, '', NULL, NULL, 0, 0, '', '2022-08-21 13:45:53', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (384, 226, '20220821225220', 7, 'R20220821007', 7, NULL, '', NULL, NULL, 0, 0, '', '2022-08-21 15:16:36', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (385, 215, '20220822234830', 19, 'MO202208220019', 19, NULL, '', NULL, NULL, 0, 0, '', '2022-08-22 08:38:59', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (386, 226, '20220822192029', 25, 'R20220822025', 25, NULL, '', NULL, NULL, 0, 0, '', '2022-08-22 08:48:16', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (387, 219, '20220822090456', 1, 'PROCESS140', 140, NULL, '', NULL, NULL, 0, 0, '', '2022-08-22 09:04:56', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (388, 219, '20220822090503', 1, 'PROCESS152', 152, NULL, '', NULL, NULL, 0, 0, '', '2022-08-22 09:05:03', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (389, 206, '20220824214316', 7, 'ITEM_TYPE_0092', 92, NULL, '', NULL, NULL, 0, 0, '', '2022-08-22 09:11:56', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (390, 229, '20220822155824', 7, 'RTV20220822007', 7, NULL, '', NULL, NULL, 0, 0, '', '2022-08-22 09:13:49', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (391, 207, '20220822100442', 1, 'C00153', 153, NULL, '', NULL, NULL, 0, 0, '', '2022-08-22 10:04:42', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (392, 207, '20220822102252', 5, 'C00167', 167, NULL, '', NULL, NULL, 0, 0, '', '2022-08-22 10:05:02', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (393, 207, '20220824215506', 31, 'C00198', 198, NULL, '', NULL, NULL, 0, 0, '', '2022-08-22 10:22:53', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (394, 210, '20220823083833', 3, 'WH139', 139, NULL, '', NULL, NULL, 0, 0, '', '2022-08-22 14:53:46', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (395, 225, '20220822154721', 1, 'IQC20220822001', 1, NULL, '', NULL, NULL, 0, 0, '', '2022-08-22 15:47:21', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (396, 226, '20220823203609', 20, 'R20220823020', 20, NULL, '', NULL, NULL, 0, 0, '', '2022-08-23 00:41:07', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (397, 215, '20220823200644', 52, 'MO202208230052', 52, NULL, '', NULL, NULL, 0, 0, '', '2022-08-23 07:51:01', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (398, 210, '20220824224111', 17, 'WH156', 156, NULL, '', NULL, NULL, 0, 0, '', '2022-08-23 08:38:34', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (399, 229, '20220823205401', 5, 'RTV20220823005', 5, NULL, '', NULL, NULL, 0, 0, '', '2022-08-23 08:43:32', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (400, 227, '20220823184025', 12, 'T117', 117, NULL, '', NULL, NULL, 0, 0, '', '2022-08-23 08:58:07', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (401, 225, '20220823192837', 7, 'IQC20220823007', 7, NULL, '', NULL, NULL, 0, 0, '', '2022-08-23 09:47:54', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (402, 236, '20220823173612', 17, 'IF2022082312', 51, NULL, '', NULL, NULL, 0, 0, '', '2022-08-23 10:41:04', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (403, 208, '20220823181426', 10, 'V00058', 58, NULL, '', NULL, NULL, 0, 0, '', '2022-08-23 15:09:35', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (404, 236, '20220823192141', 10, 'IF2022082341', 154, NULL, '', NULL, NULL, 0, 0, '', '2022-08-23 19:08:33', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (405, 236, '20220824215239', 10, 'IF2022082439', 92, NULL, '', NULL, NULL, 0, 0, '', '2022-08-23 19:21:45', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (406, 215, '20220828084935', 6, 'MO202208280006', 6, NULL, '', NULL, NULL, 0, 0, '', '2022-08-28 08:36:52', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (407, 237, '20220829224554', 4, 'IPQC202208290004', 4, NULL, '', NULL, NULL, 0, 0, '', '2022-08-29 22:09:01', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (408, 225, '20220831211548', 3, 'IQC20220831003', 3, NULL, '', NULL, NULL, 0, 0, '', '2022-08-31 20:57:09', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (409, 238, '20220901212705', 4, 'OQC20220901004', 4, NULL, '', NULL, NULL, 0, 0, '', '2022-09-01 20:35:11', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (410, 226, '20220902104056', 4, 'R20220902004', 4, NULL, '', NULL, NULL, 0, 0, '', '2022-09-02 10:36:07', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (411, 233, '20220902104302', 1, 'ISSUE20220902001', 1, NULL, '', NULL, NULL, 0, 0, '', '2022-09-02 10:43:02', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (412, 239, '20220904223417', 10, 'PBACK20220904010', 10, NULL, '', NULL, NULL, 0, 0, '', '2022-09-03 23:50:32', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (413, 237, '20220909211934', 1, 'IPQC202209090001', 1, NULL, '', NULL, NULL, 0, 0, '', '2022-09-09 21:19:38', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (414, 226, '20220910111422', 2, 'R20220910002', 2, NULL, '', NULL, NULL, 0, 0, '', '2022-09-10 10:57:18', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (415, 226, '20220912232033', 1, 'R20220912001', 1, NULL, '', NULL, NULL, 0, 0, '', '2022-09-12 23:20:33', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (416, 226, '20220913223354', 1, 'R20220913001', 1, NULL, '', NULL, NULL, 0, 0, '', '2022-09-13 22:33:54', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (417, 233, '20220913225049', 2, 'ISSUE20220913002', 2, NULL, '', NULL, NULL, 0, 0, '', '2022-09-13 22:35:02', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (418, 240, '20220915232017', 1, 'RT202209150001', 1, NULL, '', NULL, NULL, 0, 0, '', '2022-09-15 23:20:17', '', NULL);
INSERT INTO `sys_auto_code_result` VALUES (419, 226, '20220916212404', 1, 'R20220916001', 1, NULL, '', NULL, NULL, 0, 0, '', '2022-09-16 21:24:06', '', NULL);

-- ----------------------------
-- Table structure for sys_auto_code_rule
-- ----------------------------
DROP TABLE IF EXISTS `sys_auto_code_rule`;
CREATE TABLE `sys_auto_code_rule`  (
  `rule_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '规则ID',
  `rule_code` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则编码',
  `rule_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '规则名称',
  `rule_desc` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `max_length` int(11) NULL DEFAULT NULL COMMENT '最大长度',
  `is_padded` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '是否补齐',
  `padded_char` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '补齐字符',
  `padded_method` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'L' COMMENT '补齐方式',
  `enable_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT 'Y' COMMENT '是否启用',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '备注',
  `attr1` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段1',
  `attr2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '预留字段2',
  `attr3` int(11) NULL DEFAULT 0 COMMENT '预留字段3',
  `attr4` int(11) NULL DEFAULT 0 COMMENT '预留字段4',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`rule_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 241 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '编码生成规则表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_auto_code_rule
-- ----------------------------
INSERT INTO `sys_auto_code_rule` VALUES (206, 'ITEM_TYPE_CODE', '物料分类编码', NULL, 14, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-04-26 23:01:09', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (207, 'CLIENT_CODE', '客户编码规则', NULL, 6, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-06 21:20:29', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (208, 'VENDOR_CODE', '供应商编码规则', NULL, 6, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-06 22:50:13', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (209, 'WORKSHOP_CODE', '车间编码生成规则', NULL, 5, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-07 17:48:52', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (210, 'WAREHOUSE_CODE', '仓库编码规则', NULL, 5, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-07 21:59:51', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (211, 'LOCATION_CODE', '库区编码生成规则', NULL, 4, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-08 14:49:56', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (212, 'AREA_CODE', '库位编码规则', NULL, 5, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-08 18:38:08', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (213, 'MACHINERY_TYPE_CODE', '设备类型编码规则', NULL, 10, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-08 19:46:09', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (214, 'MACHINERY_CODE', '设备编码规则', NULL, 13, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-08 21:26:39', 'admin', '2022-08-23 09:15:17');
INSERT INTO `sys_auto_code_rule` VALUES (215, 'WORKORDER_CODE', '生产工单编码规则1', NULL, 14, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-09 11:39:59', 'admin', '2022-08-20 09:12:40');
INSERT INTO `sys_auto_code_rule` VALUES (216, 'WORKSTATION_CODE', '工作站编码规则', NULL, 6, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-10 21:55:24', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (217, 'TOOL_TYPE_CODE', '工装夹具类型编码', NULL, 5, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-11 00:21:37', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (218, 'TOOL_CODE', '工装夹具编码规则', NULL, 6, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-11 22:07:17', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (219, 'PROCESS_CODE', '工序编码规则', NULL, 10, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-12 00:09:45', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (220, 'ROUTE_CODE', '工艺流程编码规则', NULL, 5, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-12 23:06:36', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (221, 'TASK_CODE', '生产任务编码规则', NULL, 12, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-15 18:22:29', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (222, 'QC_INDEX_CODE', '检测项编码规则', NULL, 5, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-17 21:57:23', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (223, 'QC_TEMPLATE_CODE', '检测模板编码规则', NULL, 10, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-17 22:43:08', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (224, 'DEFECT_CODE', '常见缺陷编码', NULL, 5, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-19 11:33:27', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (225, 'QC_IQC_CODE', '来料检验单编码规则', NULL, 14, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-19 16:28:07', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (226, 'ITEMRECPT_CODE', '物料入库单编码规则', NULL, 12, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-05-22 20:51:29', 'admin', '2022-05-22 20:53:12');
INSERT INTO `sys_auto_code_rule` VALUES (227, 'CAL_TEAM_CODE', '班组编码规则', NULL, 4, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-06-06 19:54:22', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (228, 'CAL_PLAN_CODE', '排班计划编号', NULL, 11, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-06-06 22:08:10', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (229, 'WM_RTVENDOR_CODE', '供应商退货单编码', NULL, 14, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-06-13 15:48:07', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (230, 'SUBJECT_CODE', '设备点检保养项目编码', NULL, 6, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-06-16 20:27:54', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (231, 'CHECKPLAN_CODE', '点检编码规则', NULL, 11, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-06-16 21:50:00', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (232, 'BATCH_CODE', '批次规则', NULL, 11, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-07-14 12:02:10', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (233, 'ISSUE_CODE', '生产领料单编码', NULL, 16, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-07-17 19:32:10', 'admin', '2022-07-17 19:32:57');
INSERT INTO `sys_auto_code_rule` VALUES (234, '1', '2', NULL, 1, 'Y', '3', 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-07-29 16:34:20', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (236, 'ITEM_CODE', '物料规则', NULL, 10, 'N', '32', 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-08-19 10:44:20', 'admin', '2022-08-19 11:26:02');
INSERT INTO `sys_auto_code_rule` VALUES (237, 'IPQC_CODE', '过程检验单编码', NULL, 16, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-08-29 22:07:13', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (238, 'OQC_CODE', '出货编码规则', NULL, 14, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-09-01 20:30:31', '', NULL);
INSERT INTO `sys_auto_code_rule` VALUES (239, 'PBACK_CODE', '生产退料单编码', '生产退料单编码', 16, 'N', '0', 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-09-03 23:47:11', 'admin', '2022-09-03 23:47:57');
INSERT INTO `sys_auto_code_rule` VALUES (240, 'RTISSUE_CODE', '生产退库单编号规则', NULL, 14, 'N', NULL, 'L', 'Y', NULL, NULL, NULL, 0, 0, 'admin', '2022-09-15 23:18:40', 'admin', '2022-09-15 23:19:04');

SET FOREIGN_KEY_CHECKS = 1;

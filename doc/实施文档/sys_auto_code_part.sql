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
) ENGINE = InnoDB AUTO_INCREMENT = 304 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '编码生成规则组成表' ROW_FORMAT = Dynamic;

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
INSERT INTO `sys_auto_code_part` VALUES (289, 241, 1, 'FIXCHAR', 'PREFIX', '前缀', 2, NULL, NULL, 'PR', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-09-23 10:58:17', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (290, 241, 2, 'NOWDATE', 'DATE', '年月日', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-09-23 10:58:44', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (291, 241, 3, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'DAY', NULL, NULL, NULL, 0, 0, 'admin', '2022-09-23 10:59:06', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (292, 242, 1, 'FIXCHAR', 'PREFIX', '前缀', 3, NULL, NULL, 'REP', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-09-28 22:01:19', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (293, 242, 2, 'NOWDATE', 'DATE', '日期', 4, 'yyyy', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-09-28 22:01:39', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (294, 242, 3, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'YEAR', NULL, NULL, NULL, 0, 0, 'admin', '2022-09-28 22:02:00', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (295, 243, 1, 'FIXCHAR', 'PERFIX', '前缀', 2, NULL, NULL, 'PS', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-10-05 19:46:02', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (296, 243, 2, 'NOWDATE', 'DATA', '日期', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-10-05 19:46:24', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (297, 243, 3, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'DAY', NULL, NULL, NULL, 0, 0, 'admin', '2022-10-05 19:46:48', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (298, 244, 1, 'FIXCHAR', 'PREFIX', '前缀', 2, NULL, NULL, 'RS', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-10-06 21:40:42', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (299, 244, 2, 'NOWDATE', 'DATE', '日期', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-10-06 21:41:03', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (300, 244, 3, 'SERIALNO', 'SERIAL', '流水号', 3, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'DAY', NULL, NULL, NULL, 0, 0, 'admin', '2022-10-06 21:41:22', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (301, 245, 1, 'FIXCHAR', 'PREFIX', '前缀', 4, NULL, NULL, 'PACK', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-10-11 01:22:38', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (302, 245, 2, 'NOWDATE', 'DATE', '日期', 8, 'yyyyMMdd', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 0, 0, 'admin', '2022-10-11 01:23:09', '', NULL);
INSERT INTO `sys_auto_code_part` VALUES (303, 245, 3, 'SERIALNO', 'SERIAL', '流水号', 4, NULL, NULL, NULL, 1, 1, NULL, 'Y', 'DAY', NULL, NULL, NULL, 0, 0, 'admin', '2022-10-11 01:23:35', '', NULL);

SET FOREIGN_KEY_CHECKS = 1;

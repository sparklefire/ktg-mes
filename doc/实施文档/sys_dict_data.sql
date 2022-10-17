SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`  (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) NULL DEFAULT 0 COMMENT '字典排序',
  `dict_label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 200 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_data
-- ----------------------------
INSERT INTO `sys_dict_data` VALUES (1, 1, '男', '0', 'sys_user_sex', '', 'info', 'Y', '0', 'admin', '2022-04-07 00:29:32', 'admin', '2022-08-19 20:53:31', '性别男');
INSERT INTO `sys_dict_data` VALUES (2, 2, '女', '1', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '性别女');
INSERT INTO `sys_dict_data` VALUES (3, 3, '未知', '2', 'sys_user_sex', '', '', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '性别未知');
INSERT INTO `sys_dict_data` VALUES (4, 1, '显示', '0', 'sys_show_hide', '', 'primary', 'Y', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '显示菜单');
INSERT INTO `sys_dict_data` VALUES (5, 2, '隐藏', '1', 'sys_show_hide', '', 'danger', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '隐藏菜单');
INSERT INTO `sys_dict_data` VALUES (6, 1, '正常', '0', 'sys_normal_disable', '', 'primary', 'Y', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (7, 2, '停用', '1', 'sys_normal_disable', '', 'danger', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (8, 1, '正常', '0', 'sys_job_status', '', 'primary', 'Y', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (9, 2, '暂停', '1', 'sys_job_status', '', 'danger', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (10, 1, '默认', 'DEFAULT', 'sys_job_group', '', '', 'Y', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '默认分组');
INSERT INTO `sys_dict_data` VALUES (11, 2, '系统', 'SYSTEM', 'sys_job_group', '', '', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '系统分组');
INSERT INTO `sys_dict_data` VALUES (12, 1, '是', 'Y', 'sys_yes_no', '', 'primary', 'Y', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '系统默认是');
INSERT INTO `sys_dict_data` VALUES (13, 2, '否', 'N', 'sys_yes_no', '', 'danger', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '系统默认否');
INSERT INTO `sys_dict_data` VALUES (14, 1, '通知', '1', 'sys_notice_type', '', 'warning', 'Y', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '通知');
INSERT INTO `sys_dict_data` VALUES (15, 2, '公告', '2', 'sys_notice_type', '', 'success', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '公告');
INSERT INTO `sys_dict_data` VALUES (16, 1, '正常', '0', 'sys_notice_status', '', 'primary', 'Y', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (17, 2, '关闭', '1', 'sys_notice_status', '', 'danger', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '关闭状态');
INSERT INTO `sys_dict_data` VALUES (18, 1, '新增', '1', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '新增操作');
INSERT INTO `sys_dict_data` VALUES (19, 2, '修改', '2', 'sys_oper_type', '', 'info', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '修改操作');
INSERT INTO `sys_dict_data` VALUES (20, 3, '删除', '3', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '删除操作');
INSERT INTO `sys_dict_data` VALUES (21, 4, '授权', '4', 'sys_oper_type', '', 'primary', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '授权操作');
INSERT INTO `sys_dict_data` VALUES (22, 5, '导出', '5', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '导出操作');
INSERT INTO `sys_dict_data` VALUES (23, 6, '导入', '6', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '导入操作');
INSERT INTO `sys_dict_data` VALUES (24, 7, '强退', '7', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '强退操作');
INSERT INTO `sys_dict_data` VALUES (25, 8, '生成代码', '8', 'sys_oper_type', '', 'warning', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '生成操作');
INSERT INTO `sys_dict_data` VALUES (26, 9, '清空数据', '9', 'sys_oper_type', '', 'danger', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '清空操作');
INSERT INTO `sys_dict_data` VALUES (27, 1, '成功', '0', 'sys_common_status', '', 'primary', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '正常状态');
INSERT INTO `sys_dict_data` VALUES (28, 2, '失败', '1', 'sys_common_status', '', 'danger', 'N', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '停用状态');
INSERT INTO `sys_dict_data` VALUES (100, 1, '物料', 'ITEM', 'mes_item_product', NULL, 'default', 'N', '0', 'admin', '2022-04-16 16:34:46', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (101, 2, '产品', 'PRODUCT', 'mes_item_product', NULL, 'default', 'N', '0', 'admin', '2022-04-16 16:35:02', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (102, 1, '输入字符', 'INPUTCHAR', 'sys_autocode_parttype', NULL, 'default', 'N', '0', 'admin', '2022-04-26 15:33:47', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (103, 2, '当前日期时间', 'NOWDATE', 'sys_autocode_parttype', NULL, 'default', 'N', '0', 'admin', '2022-04-26 15:34:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (104, 3, '固定字符', 'FIXCHAR', 'sys_autocode_parttype', NULL, 'default', 'N', '0', 'admin', '2022-04-26 15:34:27', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (105, 4, '流水号', 'SERIALNO', 'sys_autocode_parttype', NULL, 'default', 'N', '0', 'admin', '2022-04-26 15:34:53', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (106, 1, '按年', 'YEAR', 'sys_autocode_cyclemethod', NULL, 'default', 'N', '0', 'admin', '2022-04-26 15:35:17', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (107, 2, '按月', 'MONTH', 'sys_autocode_cyclemethod', NULL, 'default', 'N', '0', 'admin', '2022-04-26 15:35:29', 'admin', '2022-04-26 15:35:55', NULL);
INSERT INTO `sys_dict_data` VALUES (108, 3, '按天', 'DAY', 'sys_autocode_cyclemethod', NULL, 'default', 'N', '0', 'admin', '2022-04-26 15:36:13', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (109, 4, '按小时', 'HOUR', 'sys_autocode_cyclemethod', NULL, 'default', 'N', '0', 'admin', '2022-04-26 15:36:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (110, 5, '按分钟', 'MINUTE', 'sys_autocode_cyclemethod', NULL, 'default', 'N', '0', 'admin', '2022-04-26 15:36:59', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (111, 6, '按传入字符', 'OTHER', 'sys_autocode_cyclemethod', NULL, 'default', 'N', '0', 'admin', '2022-04-26 15:37:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (112, 1, '企业客户', 'ENTERPRISE', 'mes_client_type', NULL, 'default', 'N', '0', 'admin', '2022-05-06 20:54:37', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (113, 2, '个人', 'PERSON', 'mes_client_type', NULL, 'default', 'N', '0', 'admin', '2022-05-06 20:55:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (114, 1, '优质供应商', 'A', 'mes_vendor_level', NULL, 'default', 'N', '0', 'admin', '2022-05-06 22:45:52', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (115, 2, '正常', 'B', 'mes_vendor_level', NULL, 'default', 'N', '0', 'admin', '2022-05-06 22:46:02', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (116, 3, '重点关注', 'C', 'mes_vendor_level', NULL, 'default', 'N', '0', 'admin', '2022-05-06 22:46:15', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (117, 4, '劣质供应商', 'D', 'mes_vendor_level', NULL, 'default', 'N', '0', 'admin', '2022-05-06 22:46:41', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (118, 5, '黑名单', 'E', 'mes_vendor_level', NULL, 'default', 'N', '0', 'admin', '2022-05-06 22:46:54', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (119, 1, '草稿', 'PREPARE', 'mes_order_status', NULL, 'default', 'N', '0', 'admin', '2022-05-09 10:35:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (120, 2, '已确认', 'CONFIRMED', 'mes_order_status', NULL, 'default', 'N', '0', 'admin', '2022-05-09 10:36:37', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (121, 3, '审批中', 'APPROVING', 'mes_order_status', NULL, 'default', 'N', '0', 'admin', '2022-05-09 10:39:30', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (122, 4, '已审批', 'APPROVED', 'mes_order_status', NULL, 'default', 'N', '0', 'admin', '2022-05-09 10:39:45', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (123, 5, '已完成', 'FINISHED', 'mes_order_status', NULL, 'default', 'N', '0', 'admin', '2022-05-09 10:40:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (124, 1, '客户订单', 'ORDER', 'mes_workorder_sourcetype', NULL, 'default', 'N', '0', 'admin', '2022-05-09 11:23:47', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (125, 2, '库存备货', 'STORE', 'mes_workorder_sourcetype', NULL, 'default', 'N', '0', 'admin', '2022-05-09 11:24:04', 'admin', '2022-05-09 11:24:24', NULL);
INSERT INTO `sys_dict_data` VALUES (126, 1, '定期维护', 'REGULAR', 'mes_mainten_type', NULL, 'default', 'N', '0', 'admin', '2022-05-10 23:40:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (127, 2, '按使用次数维护', 'USAGE', 'mes_mainten_type', NULL, 'default', 'N', '0', 'admin', '2022-05-10 23:41:31', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (128, 1, '在库', 'STORE', 'mes_tool_status', NULL, 'default', 'N', '0', 'admin', '2022-05-11 20:51:13', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (129, 2, '被领用', 'ISSUE', 'mes_tool_status', NULL, 'default', 'N', '0', 'admin', '2022-05-11 20:51:36', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (130, 3, '报废', 'SCRAP', 'mes_tool_status', NULL, 'default', 'N', '0', 'admin', '2022-05-11 20:52:02', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (131, 4, '维修中', 'REPARE', 'mes_tool_status', NULL, 'default', 'N', '0', 'admin', '2022-05-11 20:52:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (132, 1, 'S-to-S', 'SS', 'mes_link_type', NULL, 'default', 'N', '0', 'admin', '2022-05-13 21:51:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (133, 2, 'F-to-F', 'FF', 'mes_link_type', NULL, 'default', 'N', '0', 'admin', '2022-05-13 21:51:51', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (134, 3, 'S-to-F', 'SF', 'mes_link_type', NULL, 'default', 'N', '0', 'admin', '2022-05-13 21:52:05', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (135, 4, 'F-to-S', 'FS', 'mes_link_type', NULL, 'default', 'N', '0', 'admin', '2022-05-13 21:52:21', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (136, 1, '秒', 'SECOND', 'mes_time_type', NULL, 'default', 'N', '0', 'admin', '2022-05-14 08:41:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (137, 2, '分钟', 'MINUTE', 'mes_time_type', NULL, 'default', 'N', '0', 'admin', '2022-05-14 08:41:25', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (138, 3, '小时', 'HOUR', 'mes_time_type', NULL, 'default', 'N', '0', 'admin', '2022-05-14 08:41:46', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (139, 4, '天', 'DAY', 'mes_time_type', NULL, 'default', 'N', '0', 'admin', '2022-05-14 08:41:57', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (140, 5, '周', 'WEEK', 'mes_time_type', NULL, 'default', 'N', '0', 'admin', '2022-05-14 08:42:12', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (141, 6, '月', 'MONTH', 'mes_time_type', NULL, 'default', 'N', '0', 'admin', '2022-05-14 08:42:26', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (142, 1, '尺寸', 'SIZE', 'mes_index_type', NULL, 'default', 'N', '0', 'admin', '2022-05-17 21:26:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (143, 2, '外观', 'APPEARANCE', 'mes_index_type', NULL, 'default', 'N', '0', 'admin', '2022-05-17 21:28:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (144, 3, '重量', 'WEIGHT', 'mes_index_type', NULL, 'default', 'N', '0', 'admin', '2022-05-17 21:28:31', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (145, 4, '性能', 'PERFORMANCE', 'mes_index_type', NULL, 'default', 'N', '0', 'admin', '2022-05-17 21:29:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (146, 5, '成分', 'COMPONENT', 'mes_index_type', NULL, 'default', 'N', '0', 'admin', '2022-05-17 21:30:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (147, 1, '[来料检验]', 'IQC', 'mes_qc_type', NULL, 'default', 'N', '0', 'admin', '2022-05-18 09:38:58', 'admin', '2022-05-18 10:38:43', NULL);
INSERT INTO `sys_dict_data` VALUES (148, 2, '[首检]', 'FIRST', 'mes_qc_type', NULL, 'default', 'N', '0', 'admin', '2022-05-18 09:39:19', 'admin', '2022-09-08 22:22:34', NULL);
INSERT INTO `sys_dict_data` VALUES (149, 3, '[末检]', 'FINAL', 'mes_qc_type', NULL, 'default', 'N', '0', 'admin', '2022-05-18 09:39:46', 'admin', '2022-09-08 22:22:39', NULL);
INSERT INTO `sys_dict_data` VALUES (150, 4, '[巡检]', 'PATROL', 'mes_qc_type', NULL, 'default', 'N', '0', 'admin', '2022-05-18 09:40:05', 'admin', '2022-09-08 22:22:55', NULL);
INSERT INTO `sys_dict_data` VALUES (151, 5, '[成品检验]', 'FQC', 'mes_qc_type', NULL, 'default', 'N', '0', 'admin', '2022-05-18 09:40:27', 'admin', '2022-05-18 10:39:08', NULL);
INSERT INTO `sys_dict_data` VALUES (152, 6, '[发货检验]', 'OQC', 'mes_qc_type', NULL, 'default', 'N', '0', 'admin', '2022-05-18 09:40:52', 'admin', '2022-05-18 10:39:14', NULL);
INSERT INTO `sys_dict_data` VALUES (153, 1, '致命缺陷', 'CR', 'mes_defect_level', NULL, 'default', 'N', '0', 'admin', '2022-05-19 10:24:05', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (154, 2, '严重缺陷', 'MAJ', 'mes_defect_level', NULL, 'default', 'N', '0', 'admin', '2022-05-19 10:24:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (155, 3, '轻微缺陷', 'MIN', 'mes_defect_level', NULL, 'default', 'N', '0', 'admin', '2022-05-19 10:24:33', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (156, 1, '检验通过', 'ACCEPT', 'mes_qc_result', NULL, 'default', 'N', '0', 'admin', '2022-05-19 16:38:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (157, 2, '检验不通过', 'REJECT', 'mes_qc_result', NULL, 'default', 'N', '0', 'admin', '2022-05-19 16:38:22', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (159, 1, '白班', 'SINGLE', 'mes_shift_type', NULL, 'default', 'N', '0', 'admin', '2022-06-06 21:33:56', 'admin', '2022-06-06 21:35:05', NULL);
INSERT INTO `sys_dict_data` VALUES (160, 2, '两班倒', 'SHIFT_TWO', 'mes_shift_type', NULL, 'default', 'N', '0', 'admin', '2022-06-06 21:34:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (161, 3, '三班倒', 'SHIFT_THREE', 'mes_shift_type', NULL, 'default', 'N', '0', 'admin', '2022-06-06 21:34:38', 'admin', '2022-06-06 21:34:45', NULL);
INSERT INTO `sys_dict_data` VALUES (162, 1, '按天', 'DAY', 'mes_shift_method', NULL, 'default', 'N', '0', 'admin', '2022-06-06 21:39:51', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (163, 2, '按周', 'WEEK', 'mes_shift_method', NULL, 'default', 'N', '0', 'admin', '2022-06-06 21:40:14', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (164, 3, '按月', 'MONTH', 'mes_shift_method', NULL, 'default', 'N', '0', 'admin', '2022-06-06 21:40:31', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (165, 4, '按季度', 'QUARTER', 'mes_shift_method', NULL, 'default', 'N', '0', 'admin', '2022-06-06 21:40:55', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (166, 1, '注塑', 'ZS', 'mes_calendar_type', NULL, 'default', 'N', '0', 'admin', '2022-06-08 13:27:23', 'admin', '2022-06-11 21:05:57', NULL);
INSERT INTO `sys_dict_data` VALUES (167, 2, '机加工', 'CNC', 'mes_calendar_type', NULL, 'default', 'N', '0', 'admin', '2022-06-08 13:27:35', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (168, 3, '组装', 'ZZ', 'mes_calendar_type', NULL, 'default', 'N', '0', 'admin', '2022-06-08 13:27:55', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (169, 4, '仓库', 'CK', 'mes_calendar_type', NULL, 'default', 'N', '0', 'admin', '2022-06-08 13:28:24', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (170, 1, '设备点检', 'CHECK', 'mes_dvsubject_type', NULL, 'default', 'N', '0', 'admin', '2022-06-16 16:50:51', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (171, 2, '设备保养', 'MAINTEN', 'mes_dvsubject_type', NULL, 'default', 'N', '0', 'admin', '2022-06-16 16:51:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (172, 1, '小时', 'HOUR', 'mes_cycle_type', NULL, 'default', 'N', '0', 'admin', '2022-06-16 20:47:54', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (173, 2, '天', 'DAY', 'mes_cycle_type', NULL, 'default', 'N', '0', 'admin', '2022-06-16 20:49:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (174, 3, '周', 'WEEK', 'mes_cycle_type', NULL, 'default', 'N', '0', 'admin', '2022-06-16 20:49:21', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (175, 4, '月', 'MONTH', 'mes_cycle_type', NULL, 'default', 'N', '0', 'admin', '2022-06-16 20:49:32', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (176, 5, '季度', 'QUARTER', 'mes_cycle_type', NULL, 'default', 'N', '0', 'admin', '2022-06-16 20:50:00', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (177, 6, '年', 'YEAR', 'mes_cycle_type', NULL, 'default', 'N', '0', 'admin', '2022-06-16 20:50:22', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (178, 1, '设备点检计划', 'CHECK', 'dv_plan_type', NULL, 'default', 'N', '0', 'admin', '2022-06-19 17:03:44', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (179, 2, '设备保养计划', 'MAINTEN', 'dv_plan_type', NULL, 'default', 'N', '0', 'admin', '2022-06-19 17:03:56', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (180, 1, 'QR二维码', 'QR_CODE', 'mes_barcode_formart', NULL, 'default', 'N', '0', 'admin', '2022-08-01 11:06:50', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (181, 2, 'EAN码', 'EAN_CODE', 'mes_barcode_formart', NULL, 'default', 'N', '0', 'admin', '2022-08-01 11:07:40', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (182, 3, 'UPC码', 'UPC_CODE', 'mes_barcode_formart', NULL, 'default', 'N', '0', 'admin', '2022-08-01 11:07:58', 'admin', '2022-08-01 11:08:03', NULL);
INSERT INTO `sys_dict_data` VALUES (183, 1, '物料产品条码', 'ITEM', 'mes_barcode_type', NULL, 'default', 'N', '0', 'admin', '2022-08-01 11:09:07', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (184, 2, '小包装条码', 'BOX_SMALL', 'mes_barcode_type', NULL, 'default', 'N', '0', 'admin', '2022-08-01 11:10:19', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (185, 3, '库存条码', 'STOCK', 'mes_barcode_type', NULL, 'default', 'N', '0', 'admin', '2022-08-01 11:10:40', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (186, 1, '修复成功', 'SUCCESS', 'mes_repair_result', NULL, 'default', 'N', '0', 'admin', '2022-08-06 11:27:48', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (187, 2, '报废', 'SCRAP', 'mes_repair_result', NULL, 'default', 'N', '0', 'admin', '2022-08-06 11:28:18', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (188, 0, '汉族', '0', 'nation_type', NULL, 'default', 'N', '0', 'admin', '2022-08-15 14:26:52', 'admin', '2022-08-15 14:27:35', NULL);
INSERT INTO `sys_dict_data` VALUES (189, 1, '首检', 'FIRST', 'mes_ipqc_type', NULL, 'default', 'N', '0', 'admin', '2022-08-29 20:19:20', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (190, 2, '末检', 'FINAL', 'mes_ipqc_type', NULL, 'default', 'N', '0', 'admin', '2022-08-29 20:19:38', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (191, 3, '自检', 'SELF', 'mes_ipqc_type', NULL, 'default', 'N', '0', 'admin', '2022-08-29 20:19:52', 'admin', '2022-08-29 20:19:57', NULL);
INSERT INTO `sys_dict_data` VALUES (192, 4, '巡检', 'PATROL', 'mes_ipqc_type', NULL, 'default', 'N', '0', 'admin', '2022-08-29 20:20:13', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (193, 5, '点检', 'CHECK', 'mes_ipqc_type', NULL, 'default', 'N', '0', 'admin', '2022-08-29 20:20:28', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (194, 6, '终检', 'FQC', 'mes_ipqc_type', NULL, 'default', 'N', '0', 'admin', '2022-08-29 20:20:44', 'admin', '2022-08-29 20:58:07', NULL);
INSERT INTO `sys_dict_data` VALUES (195, 1, '自行报工', 'SELF', 'mes_feedback_type', NULL, 'default', 'N', '0', 'admin', '2022-10-02 15:52:45', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (196, 2, '统一报工', 'UNI', 'mes_feedback_type', NULL, 'default', 'N', '0', 'admin', '2022-10-02 15:53:03', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (197, 1, '停机', 'STOP', 'mes_machinery_status', NULL, 'default', 'N', '0', 'admin', '2022-10-09 19:24:34', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (198, 2, '生产中', 'WORKING', 'mes_machinery_status', NULL, 'default', 'N', '0', 'admin', '2022-10-09 19:24:54', '', NULL, NULL);
INSERT INTO `sys_dict_data` VALUES (199, 3, '维修中', 'REPAIR', 'mes_machinery_status', NULL, 'default', 'N', '0', 'admin', '2022-10-09 19:25:28', '', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

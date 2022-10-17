SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`  (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`) USING BTREE,
  UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 129 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO `sys_dict_type` VALUES (1, '用户性别', 'sys_user_sex', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '用户性别列表');
INSERT INTO `sys_dict_type` VALUES (2, '菜单状态', 'sys_show_hide', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '菜单状态列表');
INSERT INTO `sys_dict_type` VALUES (3, '系统开关', 'sys_normal_disable', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '系统开关列表');
INSERT INTO `sys_dict_type` VALUES (4, '任务状态', 'sys_job_status', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '任务状态列表');
INSERT INTO `sys_dict_type` VALUES (5, '任务分组', 'sys_job_group', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '任务分组列表');
INSERT INTO `sys_dict_type` VALUES (6, '系统是否', 'sys_yes_no', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '系统是否列表');
INSERT INTO `sys_dict_type` VALUES (7, '通知类型', 'sys_notice_type', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '通知类型列表');
INSERT INTO `sys_dict_type` VALUES (8, '通知状态', 'sys_notice_status', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '通知状态列表');
INSERT INTO `sys_dict_type` VALUES (9, '操作类型', 'sys_oper_type', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '操作类型列表');
INSERT INTO `sys_dict_type` VALUES (10, '系统状态', 'sys_common_status', '0', 'admin', '2022-04-07 00:29:32', '', NULL, '登录状态列表');
INSERT INTO `sys_dict_type` VALUES (100, '物料or产品', 'mes_item_product', '0', 'admin', '2022-04-16 16:34:20', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (101, '编码规则组成类型', 'sys_autocode_parttype', '0', 'admin', '2022-04-26 15:32:42', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (102, '编码规则组成循环方式', 'sys_autocode_cyclemethod', '0', 'admin', '2022-04-26 15:33:02', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (103, '客户类型', 'mes_client_type', '0', 'admin', '2022-05-06 20:53:28', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (104, '供应商级别', 'mes_vendor_level', '0', 'admin', '2022-05-06 22:44:56', 'admin', '2022-05-06 22:45:35', NULL);
INSERT INTO `sys_dict_type` VALUES (105, '单据状态【通用】', 'mes_order_status', '0', 'admin', '2022-05-09 10:34:41', 'admin', '2022-05-09 10:35:10', NULL);
INSERT INTO `sys_dict_type` VALUES (106, '生产工单来源', 'mes_workorder_sourcetype', '0', 'admin', '2022-05-09 11:23:22', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (107, '维护类型表', 'mes_mainten_type', '0', 'admin', '2022-05-10 23:40:36', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (108, '工装夹具状态', 'mes_tool_status', '0', 'admin', '2022-05-11 20:50:46', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (109, '工序关系类型', 'mes_link_type', '0', 'admin', '2022-05-13 21:50:44', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (110, '时间单位', 'mes_time_type', '0', 'admin', '2022-05-14 08:40:53', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (111, '检测项类型', 'mes_index_type', '0', 'admin', '2022-05-17 21:26:05', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (112, '检测类型', 'mes_qc_type', '0', 'admin', '2022-05-18 09:38:32', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (113, '缺陷等级', 'mes_defect_level', '0', 'admin', '2022-05-19 10:23:38', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (114, '检验结果', 'mes_qc_result', '0', 'admin', '2022-05-19 16:37:45', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (116, '倒班方式', 'mes_shift_type', '0', 'admin', '2022-06-06 21:33:17', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (117, '轮班方式', 'mes_shift_method', '0', 'admin', '2022-06-06 21:39:26', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (118, '排班类型', 'mes_calendar_type', '0', 'admin', '2022-06-08 13:26:56', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (119, '设备点检保养项目类型', 'mes_dvsubject_type', '0', 'admin', '2022-06-16 16:50:14', 'admin', '2022-06-16 16:50:29', NULL);
INSERT INTO `sys_dict_type` VALUES (120, '设备点检频率', 'mes_cycle_type', '0', 'admin', '2022-06-16 20:47:19', 'admin', '2022-06-16 20:51:22', NULL);
INSERT INTO `sys_dict_type` VALUES (121, '设备点检保养计划类型', 'dv_plan_type', '0', 'admin', '2022-06-19 17:03:18', 'admin', '2022-06-19 17:04:17', NULL);
INSERT INTO `sys_dict_type` VALUES (122, '条码格式', 'mes_barcode_formart', '0', 'admin', '2022-08-01 11:05:54', 'admin', '2022-08-01 11:06:15', NULL);
INSERT INTO `sys_dict_type` VALUES (123, '条码类型', 'mes_barcode_type', '0', 'admin', '2022-08-01 11:08:27', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (124, '维修结果', 'mes_repair_result', '0', 'admin', '2022-08-06 11:27:05', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (125, '民族', 'nation_type', '0', 'admin', '2022-08-15 14:25:55', 'admin', '2022-08-15 14:26:34', '民族');
INSERT INTO `sys_dict_type` VALUES (126, '过程质量检验类型', 'mes_ipqc_type', '0', 'admin', '2022-08-29 20:18:48', 'admin', '2022-08-29 20:18:59', NULL);
INSERT INTO `sys_dict_type` VALUES (127, '生产报工类型', 'mes_feedback_type', '0', 'admin', '2022-10-02 15:51:21', '', NULL, NULL);
INSERT INTO `sys_dict_type` VALUES (128, '设备状态', 'mes_machinery_status', '0', 'admin', '2022-10-09 19:23:54', '', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;

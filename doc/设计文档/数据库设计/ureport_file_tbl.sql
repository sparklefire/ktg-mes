SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ureport_file_tbl
-- ----------------------------
DROP TABLE IF EXISTS `ureport_file_tbl`;
CREATE TABLE `ureport_file_tbl`  (
  `id_` int(11) NOT NULL AUTO_INCREMENT,
  `name_` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `content_` mediumblob NULL,
  `create_time_` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time_` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id_`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

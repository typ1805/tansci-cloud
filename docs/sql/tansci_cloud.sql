/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : tansci_cloud

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 17/06/2022 13:15:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for data_source
-- ----------------------------
DROP TABLE IF EXISTS `data_source`;
CREATE TABLE `data_source`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据库名称',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0、启用，1、禁用',
  `type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型：1、mysql，2、MariaDB，3、Oracle11g，4、Oracle12c+，5、PostgreSql，6、SQLServer2008，7、SQLServer2012+，8、其他数据库',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主机',
  `port` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '端口',
  `username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据源' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of data_source
-- ----------------------------
INSERT INTO `data_source` VALUES ('0c600cf490fcea96ebc08f500ae55cc0', 'test', 1, '1', '10.167.1.71', '3306', 'root', 'root', '2022-06-15 16:44:23', '2022-06-13 16:07:43', NULL);
INSERT INTO `data_source` VALUES ('0ddb99a447c69f374e1e609e5aee848d', 'tansci_cloud', 1, '1', '127.0.0.1', '3306', 'root', 'admin', '2022-06-16 09:20:52', '2022-06-16 09:20:52', '');

-- ----------------------------
-- Table structure for log_error_info
-- ----------------------------
DROP TABLE IF EXISTS `log_error_info`;
CREATE TABLE `log_error_info`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `req_param` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '异常名称',
  `message` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '异常信息',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户名称',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求url',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求IP',
  `version` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本号',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '异常日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of log_error_info
-- ----------------------------

-- ----------------------------
-- Table structure for log_info
-- ----------------------------
DROP TABLE IF EXISTS `log_info`;
CREATE TABLE `log_info`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `module` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '功能模块',
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作类型',
  `message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作描述',
  `req_param` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `res_param` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '响应参数',
  `take_up_time` int(64) NULL DEFAULT NULL COMMENT '耗时',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户id',
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作用户名称',
  `method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作方法',
  `uri` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求url',
  `ip` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求IP',
  `version` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本号',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '操作日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of log_info
-- ----------------------------

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `user_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `os` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '操作系统',
  `browser` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '浏览器',
  `ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'IP地址',
  `login_time` datetime NULL DEFAULT NULL COMMENT '登录时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '登录日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log` VALUES ('62b85c17d56a8eafd60e65543bd4d4bb', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `sys_dic`;
CREATE TABLE `sys_dic`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `parent_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '父id',
  `group_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '分组名称',
  `type` int(16) NULL DEFAULT NULL COMMENT '类型：0、系统，1、业务',
  `dic_value` int(16) NULL DEFAULT NULL COMMENT '值',
  `dic_label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `sort` int(16) NULL DEFAULT NULL COMMENT '排序',
  `text1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段1',
  `text2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段2',
  `text3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '预留字段3',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dic
-- ----------------------------
INSERT INTO `sys_dic` VALUES ('0036451d38e4be3263268fe7bcdcb8d8', '13001dfb0dc30ad710c5bf5633fd10da', 'data_source', 1, 3, 'Oracle11G数据库', 3, NULL, NULL, NULL, '2022-06-15 16:17:38', '2022-06-15 16:17:38', NULL);
INSERT INTO `sys_dic` VALUES ('13001dfb0dc30ad710c5bf5633fd10da', '723702a73f1d432092d10140c8b789a5', 'data_source', 1, -1, '数据源', 2, NULL, NULL, NULL, '2022-06-15 16:15:11', '2022-06-15 16:15:11', NULL);
INSERT INTO `sys_dic` VALUES ('311c2aca10b5a895409ec2b9f40ba9b7', '13001dfb0dc30ad710c5bf5633fd10da', 'data_source', 1, 4, 'Oracle12c+数据库', 4, NULL, NULL, NULL, '2022-06-15 16:18:05', '2022-06-15 16:18:05', NULL);
INSERT INTO `sys_dic` VALUES ('40e4dacce4149d3d98c2eb1e66d6b0f0', '13001dfb0dc30ad710c5bf5633fd10da', 'data_source', 1, 6, 'SQLServer2008数据库', 6, NULL, NULL, NULL, '2022-06-15 16:19:20', '2022-06-15 16:19:20', NULL);
INSERT INTO `sys_dic` VALUES ('4487b41c9e3f56f3d123d431259a2b7a', '13001dfb0dc30ad710c5bf5633fd10da', 'data_source', 1, 5, 'PostgreSql数据库', 5, NULL, NULL, NULL, '2022-06-15 16:18:45', '2022-06-15 16:18:45', NULL);
INSERT INTO `sys_dic` VALUES ('523702a73f1d432092d10140c8b789a6', '523702a73f1d432092d10140c8b789a7', 'sys_dic_type', 0, 0, '系统', 1, NULL, NULL, NULL, NULL, '2021-07-06 16:18:05', '');
INSERT INTO `sys_dic` VALUES ('523702a73f1d432092d10140c8b789a7', '723702a73f1d432092d10140c8b789a5', 'sys_dic_type', 0, -1, '系统数据字典类型', 0, NULL, NULL, NULL, '2021-07-06 17:08:04', '2021-07-06 12:44:36', '系统数据字典类型');
INSERT INTO `sys_dic` VALUES ('523702a73f1d432092d10140c8b789a8', '523702a73f1d432092d10140c8b789a7', 'sys_dic_type', 0, 1, '业务', 2, NULL, NULL, NULL, NULL, '2021-07-06 16:18:19', '');
INSERT INTO `sys_dic` VALUES ('723702a73f1d432092d10140c8b789a5', '0', 'sys_dic', 0, 0, '数据字典', 0, NULL, NULL, NULL, '2021-07-06 14:40:33', '2021-07-06 11:38:58', NULL);
INSERT INTO `sys_dic` VALUES ('8c05f9fdf0f1a187a96e87abb4b69d36', '13001dfb0dc30ad710c5bf5633fd10da', 'data_source', 1, 1, 'MySQL数据库', 1, NULL, NULL, NULL, '2022-06-15 16:15:44', '2022-06-15 16:15:44', NULL);
INSERT INTO `sys_dic` VALUES ('8c2cd10ce0c27aeecc6d333bc42612f8', '13001dfb0dc30ad710c5bf5633fd10da', 'data_source', 1, 7, 'SQLServer2012+数据库', 7, NULL, NULL, NULL, '2022-06-15 16:19:44', '2022-06-15 16:19:44', NULL);
INSERT INTO `sys_dic` VALUES ('9379737ab32abb5f9a7320557842f551', '13001dfb0dc30ad710c5bf5633fd10da', 'data_source', 1, 2, 'MariaDB数据库', 2, NULL, NULL, NULL, '2022-06-15 16:17:02', '2022-06-15 16:17:02', NULL);

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `parent_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父ID',
  `chinese_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '中文名称',
  `english_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '英文名称',
  `status` int(1) NOT NULL COMMENT '状态：0：删除，1：正常，2：禁用',
  `type` int(1) NOT NULL COMMENT '类型：0、菜单，1、按钮，2、链接',
  `url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'URL',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '图标',
  `level` int(10) NOT NULL COMMENT '级别',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '菜单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('09a724dfb41f47e50cb88ecd577c1656', 'user', '09a724dfb41f47e50cb88ecd577c1658', '用户管理', 'User', 1, 0, '/system/User', 'Avatar', 2, 2, '2022-06-17 12:56:07', '2022-04-29 15:18:20', NULL);
INSERT INTO `sys_menu` VALUES ('09a724dfb41f47e50cb88ecd577c1657', 'role', '09a724dfb41f47e50cb88ecd577c1658', '权限管理', 'Role', 1, 0, '/system/Role', 'ScaleToOriginal', 2, 2, '2022-06-17 12:56:56', '2022-04-29 15:18:20', NULL);
INSERT INTO `sys_menu` VALUES ('09a724dfb41f47e50cb88ecd577c1658', 'system', '0', '系统管理', 'System Manage', 1, 0, '/system', 'Setting', 1, 2, '2022-04-29 15:18:17', '2022-04-29 15:18:20', NULL);
INSERT INTO `sys_menu` VALUES ('09a724dfb41f47e50cb88ecd577c1659', 'menu', '09a724dfb41f47e50cb88ecd577c1658', '菜单管理', 'Menu', 1, 0, '/system/Menu', 'Menu', 2, 3, '2022-05-27 09:46:54', '2022-04-29 15:18:20', NULL);
INSERT INTO `sys_menu` VALUES ('0e67c7947fa842e31ba21d2ba16af932', 'dataSource', '42e7e4594adccefcafc327e12a92f157', '数据源', 'DataSource', 1, 0, '/data/DataSource', 'Coin', 2, 1, '2022-06-15 15:20:12', '2022-06-15 15:19:56', '');
INSERT INTO `sys_menu` VALUES ('42e7e4594adccefcafc327e12a92f157', 'data', '0', '元数据管理', 'Data', 1, 0, '/data', 'Coin', 1, 3, '2022-06-16 10:13:24', '2022-06-15 15:18:37', '');
INSERT INTO `sys_menu` VALUES ('6f4857af914cdc0e8ce69d48a7866d27', 'index', '0', '首页', 'Index', 1, 0, '/Index', 'HomeFilled', 1, 1, '2022-06-17 11:01:46', '2022-06-17 10:59:50', '');
INSERT INTO `sys_menu` VALUES ('74fef625ee6a5a60c43e6d1cd5359e50', 'log', '09a724dfb41f47e50cb88ecd577c1658', '日志管理', 'Log', 1, 0, '/system/Log', 'Cpu', 2, 5, '2022-05-27 09:47:06', '2022-05-27 09:44:54', '');
INSERT INTO `sys_menu` VALUES ('7e460a7961432a59f604971555b3d0af', 'taskConfig', 'f1f784850a9dd4295ea261335ec2c4ea', '调度配置', 'TaskConfig', 1, 0, '/scheduled/TaskConfig', 'Timer', 2, 1, '2022-06-17 12:47:31', '2022-06-16 10:20:03', '');
INSERT INTO `sys_menu` VALUES ('8aa39bae3add690b6507f5fadc7c3c3a', 'dicInfo', '09a724dfb41f47e50cb88ecd577c1658', '字典管理', 'DicInfo', 1, 0, '/system/DicInfo', 'Reading', 2, 4, '2022-05-27 09:47:18', '2022-05-27 09:43:41', '');
INSERT INTO `sys_menu` VALUES ('c50e6f238b744a1cf4f91d7cf34f8949', 'taskLog', 'f1f784850a9dd4295ea261335ec2c4ea', '调度日志', 'TaskLog', 1, 0, '/scheduled/TaskLog', 'DocumentCopy', 2, 2, '2022-06-16 10:21:21', '2022-06-16 10:21:21', '');
INSERT INTO `sys_menu` VALUES ('c716b99aa13a5a5cbf096e51235bb847', 'dataRetrieval', '42e7e4594adccefcafc327e12a92f157', '数据元', 'DataRetrieval', 1, 0, '/data/DataRetrieval', 'Collection', 2, 2, '2022-06-16 10:14:34', '2022-06-15 17:00:35', '');
INSERT INTO `sys_menu` VALUES ('f1f784850a9dd4295ea261335ec2c4ea', 'scheduled', '0', '调度管理', 'Scheduled', 1, 0, '/scheduled', 'AlarmClock', 1, 3, '2022-06-16 10:18:32', '2022-06-16 10:18:32', '');
INSERT INTO `sys_menu` VALUES ('4a0268893bade9be3d9302a2d2244577', 'sqlExecutor', '42e7e4594adccefcafc327e12a92f157', '执行器', 'SqlExecutor', 1, 0, '/data/SqlExecutor', 'Monitor', 2, 3, '2022-06-22 15:05:01', '2022-06-22 15:04:07', '');

-- ----------------------------
-- Table structure for sys_menu_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu_role`;
CREATE TABLE `sys_menu_role`  (
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `menu_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单id',
  PRIMARY KEY (`role_id`, `menu_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu_role
-- ----------------------------
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', '09a724dfb41f47e50cb88ecd577c1656');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', '09a724dfb41f47e50cb88ecd577c1657');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', '09a724dfb41f47e50cb88ecd577c1658');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', '09a724dfb41f47e50cb88ecd577c1659');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', '0e67c7947fa842e31ba21d2ba16af932');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', '42e7e4594adccefcafc327e12a92f157');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', '74fef625ee6a5a60c43e6d1cd5359e50');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', '7e460a7961432a59f604971555b3d0af');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', '8aa39bae3add690b6507f5fadc7c3c3a');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', 'c50e6f238b744a1cf4f91d7cf34f8949');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', 'c716b99aa13a5a5cbf096e51235bb847');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', 'f1f784850a9dd4295ea261335ec2c4ea');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1777', '09a724dfb41f47e50cb88ecd577c1659');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1777', '0e67c7947fa842e31ba21d2ba16af932');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1777', '42e7e4594adccefcafc327e12a92f157');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1777', '74fef625ee6a5a60c43e6d1cd5359e50');
INSERT INTO `sys_menu_role` VALUES ('09a724dfb41f47e50cb88ecd577c1777', 'c716b99aa13a5a5cbf096e51235bb847');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `status` int(1) NOT NULL COMMENT '状态：0、默认，1、启用，2、禁用',
  `creator` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', '管理员权限', 1, 'bc3ac26e69731b617eb80274453f6dbb', '2022-04-29 15:19:51', '2022-04-29 15:19:54', NULL);
INSERT INTO `sys_role` VALUES ('09a724dfb41f47e50cb88ecd577c1777', '普通用户权限', 1, 'bc3ac26e69731b617eb80274453f6dbb', '2022-04-29 15:19:51', '2022-04-29 15:19:54', NULL);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `nickname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0：正常，1：删除，2：禁用',
  `type` int(1) NULL DEFAULT NULL COMMENT '类型：0、普通用户，1、管理员',
  `open_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '微信唯一标识',
  `birthday` date NULL DEFAULT NULL COMMENT '出生日期',
  `sex` int(1) NULL DEFAULT NULL COMMENT '性别：0：男；1：女',
  `email` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '地址',
  `id_card` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('bc3ac26e69731b617eb80274453f6db1', 'test01', '测试', '$2a$10$tlWWfjTObqLsC6ONrhNL/.GIpAoFu205TXPK6xUPHHr1kA/paK4lq', 0, 0, NULL, '2022-02-14', 1, 'test1@qq.com', '18893817562', '兰州安宁区永新华世界湾', '', '2022-05-18 15:26:41', '2022-04-29 15:22:20', '');
INSERT INTO `sys_user` VALUES ('bc3ac26e69731b617eb80274453f6db2', 'test02', '测试', '$2a$10$tlWWfjTObqLsC6ONrhNL/.GIpAoFu205TXPK6xUPHHr1kA/paK4lq', 0, 0, NULL, '2022-02-14', 0, 'admin@qq.com', '18893817562', '兰州安宁区永新华世界湾', '', '2022-04-29 15:22:18', '2022-04-29 15:22:20', '');
INSERT INTO `sys_user` VALUES ('bc3ac26e69731b617eb80274453f6dbb', 'admin', '管理员', '$2a$10$tlWWfjTObqLsC6ONrhNL/.GIpAoFu205TXPK6xUPHHr1kA/paK4lq', 0, 1, NULL, '2022-02-14', 0, 'admin@qq.com', '18893817562', '兰州安宁区永新华世界湾', '', '2022-04-29 15:22:18', '2022-04-29 15:22:20', '');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `user_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
  `role_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('bc3ac26e69731b617eb80274453f6db1', '09a724dfb41f47e50cb88ecd577c1777');
INSERT INTO `sys_user_role` VALUES ('bc3ac26e69731b617eb80274453f6dbb', '09a724dfb41f47e50cb88ecd577c1666');

-- ----------------------------
-- Table structure for task_config
-- ----------------------------
DROP TABLE IF EXISTS `task_config`;
CREATE TABLE `task_config`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键ID',
  `server_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务服务名称',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务名称',
  `task_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务编码',
  `expression` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务执行规则时间：cron表达式',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0、未启动，1、正常',
  `creater` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '任务配置' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of task_config
-- ----------------------------
INSERT INTO `task_config` VALUES ('1', 'taskTestService', '自定义任务测试', 'T1000214524DFS', '*/20 * * * * ?', 0, 'admin', '2022-06-16 11:44:46', '2022-02-25 10:53:15', NULL);

-- ----------------------------
-- Table structure for task_log
-- ----------------------------
DROP TABLE IF EXISTS `task_log`;
CREATE TABLE `task_log`  (
  `id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键id',
  `task_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '调度id',
  `server_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '任务服务名称',
  `status` int(1) NULL DEFAULT NULL COMMENT '状态：0、成功，1、失败',
  `execution_time` datetime NULL DEFAULT NULL COMMENT '执行时间',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '调度执行日志' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of task_log
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

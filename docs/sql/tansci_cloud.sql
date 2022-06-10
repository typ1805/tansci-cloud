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

 Date: 10/06/2022 10:19:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
INSERT INTO `log_info` VALUES ('3750228d-f1cb-40a1-a806-0c6faf26086d', '菜单-获取菜单树', 'SELECT', '获取菜单树', '{\"type\":\"0\",\"status\":\"1\"}', '{\"code\":200,\"fail\":false,\"message\":\"操作成功\",\"result\":[{\"children\":[{\"chineseName\":\"用户管理\",\"createTime\":\"2022-04-29T15:18:20\",\"englishName\":\"User\",\"icon\":\"UserFilled\",\"id\":\"09a724dfb41f47e50cb88ecd577c1656\",\"level\":2,\"name\":\"user\",\"parentId\":\"09a724dfb41f47e50cb88ecd577c1658\",\"sort\":2,\"status\":1,\"type\":0,\"updateTime\":\"2022-05-27T09:46:30\",\"url\":\"/system/User\"},{\"chineseName\":\"权限管理\",\"createTime\":\"2022-04-29T15:18:20\",\"englishName\":\"Role\",\"icon\":\"Coin\",\"id\":\"09a724dfb41f47e50cb88ecd577c1657\",\"level\":2,\"name\":\"role\",\"parentId\":\"09a724dfb41f47e50cb88ecd577c1658\",\"sort\":2,\"status\":1,\"type\":0,\"updateTime\":\"2022-05-27T09:46:44\",\"url\":\"/system/Role\"},{\"chineseName\":\"菜单管理\",\"createTime\":\"2022-04-29T15:18:20\",\"englishName\":\"Menu\",\"icon\":\"Menu\",\"id\":\"09a724dfb41f47e50cb88ecd577c1659\",\"level\":2,\"name\":\"menu\",\"parentId\":\"09a724dfb41f47e50cb88ecd577c1658\",\"sort\":3,\"status\":1,\"type\":0,\"updateTime\":\"2022-05-27T09:46:54\",\"url\":\"/system/Menu\"},{\"chineseName\":\"日志管理\",\"createTime\":\"2022-05-27T09:44:54\",\"englishName\":\"Log\",\"icon\":\"Cpu\",\"id\":\"74fef625ee6a5a60c43e6d1cd5359e50\",\"level\":2,\"name\":\"log\",\"parentId\":\"09a724dfb41f47e50cb88ecd577c1658\",\"remarks\":\"\",\"sort\":5,\"status\":1,\"type\":0,\"updateTime\":\"2022-05-27T09:47:06\",\"url\":\"/system/Log\"},{\"chineseName\":\"字典管理\",\"createTime\":\"2022-05-27T09:43:41\",\"englishName\":\"DicInfo\",\"icon\":\"Reading\",\"id\":\"8aa39bae3add690b6507f5fadc7c3c3a\",\"level\":2,\"name\":\"dicInfo\",\"parentId\":\"09a724dfb41f47e50cb88ecd577c1658\",\"remarks\":\"\",\"sort\":4,\"status\":1,\"type\":0,\"updateTime\":\"2022-05-27T09:47:18\",\"url\":\"/system/DicInfo\"}],\"chineseName\":\"系统管理\",\"createTime\":\"2022-04-29T15:18:20\",\"englishName\":\"System Manage\",\"icon\":\"Setting\",\"id\":\"09a724dfb41f47e50cb88ecd577c1658\",\"level\":1,\"name\":\"system\",\"parentId\":\"0\",\"sort\":2,\"status\":1,\"type\":0,\"updateTime\":\"2022-04-29T15:18:17\",\"url\":\"/system\"}],\"success\":true}', 53, 'bc3ac26e69731b617eb80274453f6dbb', 'admin', 'com.tansci.controller.SysMenuController.list', '/sysMenu/list', '127.0.0.1', '1.0.0', '2022-06-02 10:32:22');

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
INSERT INTO `sys_dic` VALUES ('523702a73f1d432092d10140c8b789a6', '523702a73f1d432092d10140c8b789a7', 'sys_dic_type', 0, 0, '系统', 1, NULL, NULL, NULL, NULL, '2021-07-06 16:18:05', '');
INSERT INTO `sys_dic` VALUES ('523702a73f1d432092d10140c8b789a7', '723702a73f1d432092d10140c8b789a5', 'sys_dic_type', 0, -1, '系统数据字典类型', 0, NULL, NULL, NULL, '2021-07-06 17:08:04', '2021-07-06 12:44:36', '系统数据字典类型');
INSERT INTO `sys_dic` VALUES ('523702a73f1d432092d10140c8b789a8', '523702a73f1d432092d10140c8b789a7', 'sys_dic_type', 0, 1, '业务', 2, NULL, NULL, NULL, NULL, '2021-07-06 16:18:19', '');
INSERT INTO `sys_dic` VALUES ('723702a73f1d432092d10140c8b789a5', '0', 'sys_dic', 0, 0, '数据字典', 0, NULL, NULL, NULL, '2021-07-06 14:40:33', '2021-07-06 11:38:58', NULL);

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
INSERT INTO `sys_menu` VALUES ('09a724dfb41f47e50cb88ecd577c1656', 'user', '09a724dfb41f47e50cb88ecd577c1658', '用户管理', 'User', 1, 0, '/system/User', 'UserFilled', 2, 2, '2022-05-27 09:46:30', '2022-04-29 15:18:20', NULL);
INSERT INTO `sys_menu` VALUES ('09a724dfb41f47e50cb88ecd577c1657', 'role', '09a724dfb41f47e50cb88ecd577c1658', '权限管理', 'Role', 1, 0, '/system/Role', 'Coin', 2, 2, '2022-05-27 09:46:44', '2022-04-29 15:18:20', NULL);
INSERT INTO `sys_menu` VALUES ('09a724dfb41f47e50cb88ecd577c1658', 'system', '0', '系统管理', 'System Manage', 1, 0, '/system', 'Setting', 1, 2, '2022-04-29 15:18:17', '2022-04-29 15:18:20', NULL);
INSERT INTO `sys_menu` VALUES ('09a724dfb41f47e50cb88ecd577c1659', 'menu', '09a724dfb41f47e50cb88ecd577c1658', '菜单管理', 'Menu', 1, 0, '/system/Menu', 'Menu', 2, 3, '2022-05-27 09:46:54', '2022-04-29 15:18:20', NULL);
INSERT INTO `sys_menu` VALUES ('74fef625ee6a5a60c43e6d1cd5359e50', 'log', '09a724dfb41f47e50cb88ecd577c1658', '日志管理', 'Log', 1, 0, '/system/Log', 'Cpu', 2, 5, '2022-05-27 09:47:06', '2022-05-27 09:44:54', '');
INSERT INTO `sys_menu` VALUES ('8aa39bae3add690b6507f5fadc7c3c3a', 'dicInfo', '09a724dfb41f47e50cb88ecd577c1658', '字典管理', 'DicInfo', 1, 0, '/system/DicInfo', 'Reading', 2, 4, '2022-05-27 09:47:18', '2022-05-27 09:43:41', '');

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
INSERT INTO `sys_role` VALUES ('09a724dfb41f47e50cb88ecd577c1666', '管理员权限', 1, NULL, '2022-04-29 15:19:51', '2022-04-29 15:19:54', NULL);
INSERT INTO `sys_role` VALUES ('09a724dfb41f47e50cb88ecd577c1777', '普通用户权限', 1, NULL, '2022-04-29 15:19:51', '2022-04-29 15:19:54', NULL);
INSERT INTO `sys_role` VALUES ('2fe348dd241fd357353b19bf56c9b4aa', '符合法规和', 1, 'bc3ac26e69731b617eb80274453f6dbb', '2022-05-26 11:46:24', '2022-05-26 11:46:24', '');

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
INSERT INTO `sys_user` VALUES ('bc3ac26e69731b617eb80274453f6db1', 'test001', '测试', 'pw123456', 1, 0, NULL, '2022-02-14', 1, 'test1@qq.com', '18893817562', '兰州安宁区永新华世界湾', '', '2022-05-18 15:26:41', '2022-04-29 15:22:20', '');
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
INSERT INTO `task_config` VALUES ('1', 'taskTestService', '自定义任务测试', 'T1000214524DFS', '*/20 * * * * ?', 1, 'admin', '2022-02-25 12:58:43', '2022-02-25 10:53:15', NULL);

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

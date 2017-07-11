/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : gan

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2017-07-10 10:41:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_organization`
-- ----------------------------
DROP TABLE IF EXISTS `sys_organization`;
CREATE TABLE `sys_organization` (
`organization_id`  int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' ,
`pid`  int(10) NULL DEFAULT NULL COMMENT '所属上级' ,
`name`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织名称' ,
`description`  varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组织描述' ,
`ctime`  bigint(20) NULL DEFAULT NULL COMMENT '创建时间' ,
PRIMARY KEY (`organization_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='组织'
AUTO_INCREMENT=8

;

-- ----------------------------
-- Records of sys_organization
-- ----------------------------
BEGIN;
INSERT INTO `sys_organization` VALUES ('1', null, '总部', '北京总部', '1'), ('4', null, '河北分部', '河北石家庄', '1488122466236'), ('5', null, '河南分部', '河南郑州', '1488122480265'), ('6', null, '湖北分部', '湖北武汉', '1488122493265'), ('7', null, '湖南分部', '湖南长沙', '1488122502752');
COMMIT;

-- ----------------------------
-- Table structure for `sys_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
`permission_id`  int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' ,
`system_id`  int(10) UNSIGNED NOT NULL COMMENT '所属系统' ,
`pid`  int(10) NULL DEFAULT NULL COMMENT '所属上级' ,
`name`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '名称' ,
`type`  tinyint(4) NULL DEFAULT NULL COMMENT '类型(1:目录,2:菜单,3:按钮)' ,
`permission_value`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限值' ,
`uri`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路径' ,
`icon`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标' ,
`status`  tinyint(4) NULL DEFAULT NULL COMMENT '状态(0:禁止,1:正常)' ,
`ctime`  bigint(20) NULL DEFAULT NULL COMMENT '创建时间' ,
`orders`  bigint(20) NULL DEFAULT NULL COMMENT '排序' ,
PRIMARY KEY (`permission_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='权限'
AUTO_INCREMENT=86

;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_permission` VALUES ('1', '1', '0', '系统组织管理', '1', '', '', 'zmdi zmdi-accounts-list', '1', '1', '1'), ('2', '1', '1', '系统管理', '2', 'upms:system:read', '/manage/system/index', '', '1', '2', '2'), ('3', '1', '1', '组织管理', '2', 'upms:organization:read', '/manage/organization/index', '', '1', '3', '3'), ('4', '1', '0', '角色用户管理', '1', '', '', 'zmdi zmdi-accounts', '1', '4', '4'), ('5', '1', '4', '角色管理', '2', 'upms:role:read', '/manage/role/index', '', '1', '6', '6'), ('6', '1', '4', '用户管理', '2', 'upms:user:read', '/manage/user/index', '', '1', '5', '5'), ('7', '1', '0', '权限资源管理', '1', '', '', 'zmdi zmdi-lock-outline', '1', '7', '7'), ('12', '1', '0', '其他数据管理', '1', '', '', 'zmdi zmdi-more', '1', '12', '12'), ('14', '1', '12', '会话管理', '2', 'upms:session:read', '/manage/session/index', '', '1', '14', '14'), ('15', '1', '12', '日志记录', '2', 'upms:log:read', '/manage/log/index', '', '1', '15', '15'), ('17', '2', '0', '标签类目管理', '1', null, null, 'zmdi zmdi-menu', '1', '17', '17'), ('18', '2', '17', '标签管理', '2', 'cms:tag:read', '/manage/tag/index', null, '1', '18', '18'), ('19', '2', '17', '类目管理', '2', 'cms:category:read', '/manage/category/index', null, '1', '19', '19'), ('20', '2', '0', '文章评论管理', '1', null, null, 'zmdi zmdi-collection-text', '1', '20', '20'), ('21', '2', '20', '文章管理', '2', 'cms:article:read', '/manage/article/index', null, '1', '21', '21'), ('22', '2', '20', '回收管理', '2', 'cms:article:read', '/manage/article/recycle', null, '1', '22', '22'), ('24', '1', '2', '新增系统', '3', 'upms:system:create', '/manage/system/create', 'zmdi zmdi-plus', '1', '24', '24'), ('25', '1', '2', '编辑系统', '3', 'upms:system:update', '/manage/system/update', 'zmdi zmdi-edit', '1', '25', '25'), ('26', '1', '2', '删除系统', '3', 'upms:system:delete', '/manage/system/delete', 'zmdi zmdi-close', '1', '26', '26'), ('27', '1', '3', '新增组织', '3', 'upms:organization:create', '/manage/organization/create', 'zmdi zmdi-plus', '1', '27', '27'), ('28', '1', '3', '编辑组织', '3', 'upms:organization:update', '/manage/organization/update', 'zmdi zmdi-edit', '1', '28', '28'), ('29', '1', '3', '删除组织', '3', 'upms:organization:delete', '/manage/organization/delete', 'zmdi zmdi-close', '1', '29', '29'), ('30', '1', '6', '新增用户', '3', 'upms:user:create', '/manage/user/create', 'zmdi zmdi-plus', '1', '30', '30'), ('31', '1', '6', '编辑用户', '3', 'upms:user:update', '/manage/user/update', 'zmdi zmdi-edit', '1', '31', '31'), ('32', '1', '6', '删除用户', '3', 'upms:user:delete', '/manage/user/delete', 'zmdi zmdi-close', '1', '32', '32'), ('33', '1', '5', '新增角色', '3', 'upms:role:create', '/manage/role/create', 'zmdi zmdi-plus', '1', '33', '33'), ('34', '1', '5', '编辑角色', '3', 'upms:role:update', '/manage/role/update', 'zmdi zmdi-edit', '1', '34', '34'), ('35', '1', '5', '删除角色', '3', 'upms:role:delete', '/manage/role/delete', 'zmdi zmdi-close', '1', '35', '35'), ('36', '1', '39', '新增权限', '3', 'upms:permission:create', '/manage/permission/create', 'zmdi zmdi-plus', '1', '36', '36'), ('37', '1', '39', '编辑权限', '3', 'upms:permission:update', '/manage/permission/update', 'zmdi zmdi-edit', '1', '37', '37'), ('38', '1', '39', '删除权限', '3', 'upms:permission:delete', '/manage/permission/delete', 'zmdi zmdi-close', '1', '38', '38'), ('39', '1', '7', '权限管理', '2', 'upms:permission:read', '/manage/permission/index', null, '1', '39', '39'), ('46', '1', '5', '角色权限', '3', 'upms:role:permission', '/manage/role/permission', 'zmdi zmdi-key', '1', '1488091928257', '1488091928257'), ('48', '1', '6', '用户组织', '3', 'upms:user:organization', '/manage/user/organization', 'zmdi zmdi-accounts-list', '1', '1488120011165', '1488120011165'), ('50', '1', '6', '用户角色', '3', 'upms:user:role', '/manage/user/role', 'zmdi zmdi-accounts', '1', '1488120554175', '1488120554175'), ('51', '1', '6', '用户权限', '3', 'upms:user:permission', '/manage/user/permission', 'zmdi zmdi-key', '1', '1488092013302', '1488092013302'), ('53', '1', '14', '强制退出', '3', 'upms:session:forceout', '/manage/session/forceout', 'zmdi zmdi-run', '1', '1488379514715', '1488379514715'), ('54', '2', '18', '新增标签', '3', 'cms:tag:create', '/manage/tag/create', 'zmdi zmdi-plus', '1', '1489417315159', '1489417315159'), ('55', '2', '18', '编辑标签', '3', 'cms:tag:update', 'zmdi zmdi-edit', 'zmdi zmdi-widgets', '1', '1489417344931', '1489417344931'), ('56', '2', '18', '删除标签', '3', 'cms:tag:delete', '/manage/tag/delete', 'zmdi zmdi-close', '1', '1489417372114', '1489417372114'), ('57', '1', '15', '删除权限', '3', 'upms:log:delete', '/manage/log/delete', 'zmdi zmdi-close', '1', '1489503867909', '1489503867909'), ('58', '2', '19', '编辑类目', '3', 'cms:category:update', '/manage/category/update', 'zmdi zmdi-edit', '1', '1489586600462', '1489586600462'), ('59', '2', '19', '删除类目', '3', 'cms:category:delete', '/manage/category/delete', 'zmdi zmdi-close', '1', '1489586633059', '1489586633059'), ('60', '2', '19', '新增类目', '3', 'cms:category:create', '/manage/category/create', 'zmdi zmdi-plus', '1', '1489590342089', '1489590342089'), ('61', '2', '0', '其他数据管理', '1', '', '', 'zmdi zmdi-more', '1', '1489835455359', '1489835455359'), ('62', '2', '20', '评论管理', '2', 'cms:comment:read', '/manage/comment/index', '', '1', '1489591408224', '1489591408224'), ('63', '2', '62', '删除评论', '3', 'cms:comment:delete', '/manage/comment/delete', 'zmdi zmdi-close', '1', '1489591449614', '1489591449614'), ('64', '2', '79', '单页管理', '2', 'cms:page:read', '/manage/page/index', '', '1', '1489591332779', '1489591332779'), ('65', '2', '64', '新增单页', '3', 'cms:page:create', '/manage/page/create', 'zmdi zmdi-plus', '1', '1489591614473', '1489591614473'), ('66', '2', '64', '编辑单页', '3', 'cms:page:update', '/manage/page/update', 'zmdi zmdi-edit', '1', '1489591653000', '1489591653000'), ('67', '2', '64', '删除单页', '3', 'cms:page:delete', '/manage/page/delete', 'zmdi zmdi-close', '1', '1489591683552', '1489591683552'), ('68', '2', '61', '菜单管理', '2', 'cms:menu:read', '/manage/menu/index', 'zmdi zmdi-widgets', '1', '1489591746846', '1489591746846'), ('69', '2', '68', '新增菜单', '3', 'cms:menu:create', '/manage/menu/create', 'zmdi zmdi-plus', '1', '1489591791747', '1489591791747'), ('70', '2', '68', '编辑菜单', '3', 'cms:menu:update', '/manage/menu/update', 'zmdi zmdi-edit', '1', '1489591831878', '1489591831878'), ('71', '2', '68', '删除菜单', '3', 'cms:menu:delete', '/manage/menu/delete', 'zmdi zmdi-close', '1', '1489591865454', '1489591865454'), ('72', '2', '61', '系统设置', '2', 'cms:setting:read', '/manage/setting/index', 'zmdi zmdi-widgets', '1', '1489591981165', '1489591981165'), ('73', '2', '72', '新增设置', '3', 'cms:setting:create', '/manage/setting/create', 'zmdi zmdi-plus', '1', '1489592024762', '1489592024762'), ('74', '2', '72', '编辑设置', '3', 'cms:setting:update', '/manage/setting/update', 'zmdi zmdi-edit', '1', '1489592052582', '1489592052582'), ('75', '2', '72', '删除设置', '3', 'cms:setting:delete', '/manage/setting/delete', 'zmdi zmdi-close', '1', '1489592081426', '1489592081426'), ('76', '2', '21', '新增文章', '3', 'cms:article:create', '/manage/article/create', 'zmdi zmdi-plus', '1', '1489820150404', '1489820150404'), ('77', '2', '21', '编辑文章', '3', 'cms:article:update', '/manage/article/update', 'zmdi zmdi-edit', '1', '1489820178269', '1489820178269'), ('78', '2', '21', '删除文章', '3', 'cms:article:delete', '/manage/article/delete', 'zmdi zmdi-close', '1', '1489820207607', '1489820207607'), ('79', '2', '0', '单页专题管理', '1', '', '', 'zmdi zmdi-view-web', '1', '1489835320327', '1489835320327'), ('80', '2', '79', '专题管理', '2', 'cms:topic:read', '/manage/topic/index', 'zmdi zmdi-widgets', '1', '1489591507566', '1489591507566'), ('81', '2', '80', '新增专题', '3', 'cms:topic:create', '/manage/topic/create', 'zmdi zmdi-plus', '1', '1489843327028', '1489843327028'), ('82', '2', '80', '编辑专题', '3', 'cms:topic:update', '/manage/topic/update', 'zmdi zmdi-edit', '1', '1489843351513', '1489843351513'), ('83', '2', '80', '删除专题', '3', 'cms:topic:delete', '/manage/topic/delete', 'zmdi zmdi-close', '1', '1489843379953', '1489843379953'), ('84', '2', '68', '上移菜单', '3', 'cms:menu:up', '/manage/menu/up', 'zmdi zmdi-long-arrow-up', '1', '1489846486548', '1489846486548'), ('85', '2', '68', '下移菜单', '3', 'cms:menu:down', '/manage/menu/down', 'zmdi zmdi-long-arrow-down', '1', '1489846578051', '1489846578051');
COMMIT;

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
`role_id`  int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' ,
`name`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名称' ,
`title`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色标题' ,
`description`  varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述' ,
`ctime`  bigint(20) NOT NULL COMMENT '创建时间' ,
`orders`  bigint(20) NOT NULL COMMENT '排序' ,
PRIMARY KEY (`role_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='角色'
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_role` VALUES ('1', 'super', '超级管理员', '拥有所有权限', '1', '1'), ('2', 'admin', '管理员', '拥有除权限管理系统外的所有权限', '1487471013117', '1487471013117');
COMMIT;

-- ----------------------------
-- Table structure for `sys_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
`role_permission_id`  int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' ,
`role_id`  int(10) UNSIGNED NOT NULL COMMENT '角色编号' ,
`permission_id`  int(10) UNSIGNED NOT NULL COMMENT '权限编号' ,
PRIMARY KEY (`role_permission_id`),
FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`role_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
INDEX `FK_Reference_23` (`role_id`) USING BTREE 
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='角色权限关联表'
AUTO_INCREMENT=126

;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_role_permission` VALUES ('1', '1', '1'), ('2', '1', '2'), ('3', '1', '3'), ('4', '1', '4'), ('5', '1', '5'), ('6', '1', '6'), ('7', '1', '7'), ('8', '1', '39'), ('12', '1', '12'), ('14', '1', '14'), ('15', '1', '15'), ('17', '1', '17'), ('19', '1', '19'), ('20', '1', '20'), ('21', '1', '21'), ('24', '1', '24'), ('27', '1', '27'), ('28', '1', '28'), ('29', '1', '29'), ('30', '1', '30'), ('31', '1', '31'), ('32', '1', '32'), ('33', '1', '33'), ('34', '1', '34'), ('35', '1', '35'), ('36', '1', '36'), ('37', '1', '37'), ('38', '1', '38'), ('39', '1', '46'), ('40', '1', '51'), ('44', '1', '48'), ('45', '1', '50'), ('47', '1', '53'), ('48', '1', '18'), ('49', '1', '54'), ('50', '1', '54'), ('51', '1', '55'), ('52', '1', '54'), ('53', '1', '55'), ('54', '1', '56'), ('55', '1', '57'), ('56', '1', '58'), ('57', '1', '58'), ('58', '1', '59'), ('59', '1', '60'), ('60', '1', '61'), ('61', '1', '62'), ('62', '1', '62'), ('63', '1', '63'), ('64', '1', '62'), ('65', '1', '63'), ('66', '1', '64'), ('67', '1', '62'), ('68', '1', '63'), ('69', '1', '64'), ('70', '1', '65'), ('71', '1', '62'), ('72', '1', '63'), ('73', '1', '64'), ('74', '1', '65'), ('75', '1', '66'), ('76', '1', '62'), ('77', '1', '63'), ('78', '1', '64'), ('79', '1', '65'), ('80', '1', '66'), ('81', '1', '67'), ('82', '1', '68'), ('83', '1', '69'), ('84', '1', '69'), ('85', '1', '70'), ('86', '1', '69'), ('87', '1', '70'), ('88', '1', '71'), ('89', '1', '72'), ('90', '1', '72'), ('91', '1', '73'), ('92', '1', '72'), ('93', '1', '73'), ('94', '1', '74'), ('95', '1', '72'), ('96', '1', '73'), ('97', '1', '74'), ('98', '1', '75'), ('99', '1', '76'), ('100', '1', '76'), ('101', '1', '77'), ('102', '1', '76'), ('103', '1', '77'), ('105', '1', '79'), ('106', '1', '80'), ('107', '1', '81'), ('108', '1', '81'), ('109', '1', '82'), ('110', '1', '81'), ('111', '1', '82'), ('112', '1', '83'), ('113', '1', '84'), ('114', '1', '84'), ('115', '1', '85');
INSERT INTO `sys_role_permission` VALUES ('121', '1', '78'), ('122', '1', '78'), ('124', '1', '25'), ('125', '1', '26');
COMMIT;

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
`user_id`  int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' ,
`username`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '帐号' ,
`password`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码MD5(密码+盐)' ,
`salt`  varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '盐' ,
`realname`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '姓名' ,
`avatar`  varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像' ,
`phone`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电话' ,
`email`  varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱' ,
`sex`  tinyint(4) NULL DEFAULT NULL COMMENT '性别' ,
`locked`  tinyint(4) NULL DEFAULT NULL COMMENT '状态(0:正常,1:锁定)' ,
`ctime`  bigint(20) NULL DEFAULT NULL COMMENT '创建时间' ,
PRIMARY KEY (`user_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='用户'
AUTO_INCREMENT=3

;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
BEGIN;
INSERT INTO `sys_user` VALUES ('1', 'admin', '3038D9CB63B3152A79B8153FB06C02F7', '66f1b370c660445a8657bf8bf1794486', '张恕征', '/resources/zheng-admin/images/avatar.jpg', '', '469741414@qq.com', '1', '0', '1'), ('2', 'test', '285C9762F5F9046F5893F752DFAF3476', 'd2d0d03310444ad388a8b290b0fe8564', '张恕征', '/resources/zheng-admin/images/avatar.jpg', '', '469741414@qq.com', '1', '0', '1493394720495');
COMMIT;

-- ----------------------------
-- Table structure for `sys_user_organization`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_organization`;
CREATE TABLE `sys_user_organization` (
`user_organization_id`  int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' ,
`user_id`  int(10) UNSIGNED NOT NULL COMMENT '用户编号' ,
`organization_id`  int(10) UNSIGNED NOT NULL COMMENT '组织编号' ,
PRIMARY KEY (`user_organization_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='用户组织关联表'
AUTO_INCREMENT=24

;

-- ----------------------------
-- Records of sys_user_organization
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_organization` VALUES ('19', '1', '1'), ('20', '1', '4'), ('21', '1', '5'), ('22', '1', '6'), ('23', '1', '7');
COMMIT;

-- ----------------------------
-- Table structure for `sys_user_permission`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_permission`;
CREATE TABLE `sys_user_permission` (
`user_permission_id`  int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' ,
`user_id`  int(10) UNSIGNED NOT NULL COMMENT '用户编号' ,
`permission_id`  int(10) UNSIGNED NOT NULL COMMENT '权限编号' ,
`type`  tinyint(4) NOT NULL COMMENT '权限类型(-1:减权限,1:增权限)' ,
PRIMARY KEY (`user_permission_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='用户权限关联表'
AUTO_INCREMENT=26

;

-- ----------------------------
-- Records of sys_user_permission
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_permission` VALUES ('3', '1', '22', '-1'), ('4', '1', '22', '1'), ('5', '2', '24', '-1'), ('6', '2', '26', '-1'), ('7', '2', '27', '-1'), ('8', '2', '29', '-1'), ('9', '2', '32', '-1'), ('10', '2', '51', '-1'), ('11', '2', '48', '-1'), ('12', '2', '50', '-1'), ('13', '2', '35', '-1'), ('14', '2', '46', '-1'), ('15', '2', '37', '-1'), ('16', '2', '38', '-1'), ('17', '2', '57', '-1'), ('18', '2', '56', '-1'), ('19', '2', '59', '-1'), ('20', '2', '78', '-1'), ('21', '2', '67', '-1'), ('22', '2', '83', '-1'), ('23', '2', '71', '-1'), ('24', '2', '75', '-1');
COMMIT;

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
`user_role_id`  int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '编号' ,
`user_id`  int(10) UNSIGNED NOT NULL COMMENT '用户编号' ,
`role_id`  int(10) NULL DEFAULT NULL COMMENT '角色编号' ,
PRIMARY KEY (`user_role_id`)
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8mb4 COLLATE=utf8mb4_general_ci
COMMENT='用户角色关联表'
AUTO_INCREMENT=8

;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
BEGIN;
INSERT INTO `sys_user_role` VALUES ('4', '1', '1'), ('5', '1', '2'), ('6', '2', '1'), ('7', '2', '2');
COMMIT;

-- ----------------------------
-- Auto increment value for `sys_organization`
-- ----------------------------
ALTER TABLE `sys_organization` AUTO_INCREMENT=8;

-- ----------------------------
-- Auto increment value for `sys_permission`
-- ----------------------------
ALTER TABLE `sys_permission` AUTO_INCREMENT=86;

-- ----------------------------
-- Auto increment value for `sys_role`
-- ----------------------------
ALTER TABLE `sys_role` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for `sys_role_permission`
-- ----------------------------
ALTER TABLE `sys_role_permission` AUTO_INCREMENT=126;

-- ----------------------------
-- Auto increment value for `sys_user`
-- ----------------------------
ALTER TABLE `sys_user` AUTO_INCREMENT=3;

-- ----------------------------
-- Auto increment value for `sys_user_organization`
-- ----------------------------
ALTER TABLE `sys_user_organization` AUTO_INCREMENT=24;

-- ----------------------------
-- Auto increment value for `sys_user_permission`
-- ----------------------------
ALTER TABLE `sys_user_permission` AUTO_INCREMENT=26;

-- ----------------------------
-- Auto increment value for `sys_user_role`
-- ----------------------------
ALTER TABLE `sys_user_role` AUTO_INCREMENT=8;

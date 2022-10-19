/*
Navicat MySQL Data Transfer

Source Server         : EMQ
Source Server Version : 50736
Source Host           : 118.31.33.180:3306
Source Database       : shorterm

Target Server Type    : MYSQL
Target Server Version : 50736
File Encoding         : 65001

Date: 2022-08-13 22:22:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_Id` int(9) NOT NULL AUTO_INCREMENT COMMENT 'ID号',
  `admin_Name` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `tel` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '电话号码',
  `institutions_id` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `user_state` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `role` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `email` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮箱',
  `pwd` varchar(16) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `true_name` varchar(15) COLLATE utf8_bin DEFAULT NULL COMMENT '角色(0管理员，1教师，2学生)',
  PRIMARY KEY (`admin_Id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1006 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='管理员信息表';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', '管理员', '15349964856', '城院罗老师测试', '启用', '罗老师', '123@qq.com', '1', '0');
INSERT INTO `admin` VALUES ('1001', '超级管理员', '13658377857', '城院罗老师测试', '启用', '罗老师', '1253838283@qq.com', '123456', '0');
INSERT INTO `admin` VALUES ('1002', '15', '11', '城院罗老师测试', '启用', '罗老师', '11', '123456', '11');
INSERT INTO `admin` VALUES ('1003', '23', '22', '城院罗老师测试', '禁用', '罗老师', '22', '123456', '22');
INSERT INTO `admin` VALUES ('1005', null, null, '城院罗老师测试', '启用', null, null, '123456', null);

-- ----------------------------
-- Table structure for announcement
-- ----------------------------
DROP TABLE IF EXISTS `announcement`;
CREATE TABLE `announcement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '公告内容',
  `color` int(11) DEFAULT NULL COMMENT '字体颜色',
  `size` int(11) DEFAULT NULL COMMENT '字体大小',
  `bg_color` int(11) DEFAULT NULL COMMENT '背景颜色',
  `bg_size` int(11) DEFAULT NULL COMMENT '背景高度',
  `locations` int(11) DEFAULT NULL COMMENT '公告位置',
  `speed` int(11) DEFAULT NULL COMMENT '公告速度',
  `model` int(11) DEFAULT NULL COMMENT '公告模式',
  `start_date` datetime DEFAULT NULL COMMENT '开始时间',
  `end_date` datetime DEFAULT NULL COMMENT '结束时间',
  `user_id` int(11) DEFAULT NULL COMMENT '用户编号',
  `equipment_id` int(11) DEFAULT NULL COMMENT '设备编号',
  `state` int(11) DEFAULT NULL COMMENT '公告状态,0为发布中,1为未发布',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of announcement
-- ----------------------------
INSERT INTO `announcement` VALUES ('1', '憨憨', '2', '5', '1', '10', '0', '5', '1', '2022-07-01 06:45:17', '2022-07-01 06:45:17', '1', '1', '1');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '类别名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('1', 'test1');

-- ----------------------------
-- Table structure for colorset
-- ----------------------------
DROP TABLE IF EXISTS `colorset`;
CREATE TABLE `colorset` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '颜色名称',
  `R` int(11) DEFAULT NULL,
  `G` int(11) DEFAULT NULL,
  `B` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of colorset
-- ----------------------------
INSERT INTO `colorset` VALUES ('1', '白', '255', '255', '255');
INSERT INTO `colorset` VALUES ('2', '黑', '0', '0', '0');

-- ----------------------------
-- Table structure for equipment
-- ----------------------------
DROP TABLE IF EXISTS `equipment`;
CREATE TABLE `equipment` (
  `equipment_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '设备编号',
  `equipment_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '设备名称',
  `state` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '设备状态编号',
  `institutions_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '所属机构编号',
  `group_id` int(11) DEFAULT NULL COMMENT '分组编号',
  `system_update` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '设备MAC地址',
  `resolution_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '分辨率编号',
  `current_plan` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '当前计划编号',
  PRIMARY KEY (`equipment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of equipment
-- ----------------------------
INSERT INTO `equipment` VALUES ('1', '测试误删', '离线', '城院罗老师测试', '11111', '已是最新', 'A0BB3ED2F376', '1920x1080(横)', '0');

-- ----------------------------
-- Table structure for equipmentstate
-- ----------------------------
DROP TABLE IF EXISTS `equipmentstate`;
CREATE TABLE `equipmentstate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '设备状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of equipmentstate
-- ----------------------------
INSERT INTO `equipmentstate` VALUES ('1', '离线');
INSERT INTO `equipmentstate` VALUES ('2', '播放');
INSERT INTO `equipmentstate` VALUES ('3', '空闲');

-- ----------------------------
-- Table structure for file
-- ----------------------------
DROP TABLE IF EXISTS `file`;
CREATE TABLE `file` (
  `file_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '所属类别',
  `displayed` tinyint(1) DEFAULT NULL,
  `file_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '文件夹名称',
  `checked` tinyint(1) DEFAULT NULL COMMENT '是否选中',
  `my_style` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`file_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of file
-- ----------------------------
INSERT INTO `file` VALUES ('1', '图片', '0', '啧啧啧', '0', 'display: none;');
INSERT INTO `file` VALUES ('2', '图文', '0', '测试查询', '0', 'display: none;');
INSERT INTO `file` VALUES ('3', '视频', '0', '视频', '0', 'display: none;');
INSERT INTO `file` VALUES ('4', '视频', '0', '11', '0', 'display:none');

-- ----------------------------
-- Table structure for groups
-- ----------------------------
DROP TABLE IF EXISTS `groups`;
CREATE TABLE `groups` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '分组编号',
  `group_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '分组名称',
  `institutions_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '所属机构编号',
  `number` int(11) DEFAULT NULL COMMENT '设备数量',
  `describes` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '分组描述',
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of groups
-- ----------------------------
INSERT INTO `groups` VALUES ('1', '11111', '城院罗老师测试', '1', '1');

-- ----------------------------
-- Table structure for institutions
-- ----------------------------
DROP TABLE IF EXISTS `institutions`;
CREATE TABLE `institutions` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '机构编号',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '机构名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of institutions
-- ----------------------------

-- ----------------------------
-- Table structure for material
-- ----------------------------
DROP TABLE IF EXISTS `material`;
CREATE TABLE `material` (
  `material_id` int(11) NOT NULL AUTO_INCREMENT,
  `material_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '素材名称',
  `file_name` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '所属文件夹',
  `data` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '素材存储地址',
  `displayed` tinyint(1) DEFAULT NULL,
  `checked` tinyint(1) DEFAULT NULL,
  `my_style` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`material_id`)
) ENGINE=InnoDB AUTO_INCREMENT=63 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of material
-- ----------------------------
INSERT INTO `material` VALUES ('55', '上传图片', '啧啧啧', 'http://localhost:8080/img/2022-07-187/4d6e7b47-184f-4072-8797-e6d936455478.png', '0', '0', 'display:none;');
INSERT INTO `material` VALUES ('56', '上传图片', '啧啧啧', 'http://localhost:8080/img/2022-07-187/a80976d3-7888-49b3-bbe0-1944f3d14678.png', '0', '0', 'display:none;');
INSERT INTO `material` VALUES ('57', '上传图片', '啧啧啧', 'http://localhost:8080/img/2022-07-187/6d2825d9-50c5-48c8-97c0-1a00b4bcf7ae.jpg', '0', '0', 'display:none;');
INSERT INTO `material` VALUES ('61', '上传图片', '啧啧啧', 'http://localhost:8080/img/2022-07-188/0e386b5f-a6b7-4efc-9a79-507513238f70.png', '0', '0', 'display:none;');
INSERT INTO `material` VALUES ('62', '上传图片', '啧啧啧', 'http://localhost:8080/img/2022-07-188/2f8b6292-6b56-4a4f-83a2-67b2b13ce116.png', '0', '0', 'display:none;');

-- ----------------------------
-- Table structure for plan
-- ----------------------------
DROP TABLE IF EXISTS `plan`;
CREATE TABLE `plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `over` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '缩略图',
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '计划名称',
  `state_id` int(11) unsigned zerofill DEFAULT NULL COMMENT '计划状态,1为待发布,2为发布中,3为已结束,4为发布成功,5为发布失败',
  `model` int(11) DEFAULT NULL COMMENT '播放模式，0表示按时段播放，1表示持续播放',
  `date` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '播放时间',
  `user_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户编号',
  `reviewer_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '审核人编号',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `synchronous` tinyint(1) DEFAULT NULL COMMENT '是否多屏同步，0表示false，1表示true',
  `program_id` int(11) DEFAULT NULL COMMENT '节目编号',
  `equipment_id` int(11) DEFAULT NULL COMMENT '设备编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of plan
-- ----------------------------
INSERT INTO `plan` VALUES ('1', 'yun.jpg', '111', '00000000001', '0', '2022-07-14', '1', '1', '2022-07-02 10:29:09', '1', '1', '1');
INSERT INTO `plan` VALUES ('2', null, '111', '00000000001', '1', '2022.6.9 15:51', '管理员', null, '2022-07-05 07:51:16', '1', '3', '1');

-- ----------------------------
-- Table structure for planstate
-- ----------------------------
DROP TABLE IF EXISTS `planstate`;
CREATE TABLE `planstate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '计划状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of planstate
-- ----------------------------
INSERT INTO `planstate` VALUES ('1', '待发布');
INSERT INTO `planstate` VALUES ('2', '发布中');
INSERT INTO `planstate` VALUES ('3', '已结束');
INSERT INTO `planstate` VALUES ('4', '发布成功');
INSERT INTO `planstate` VALUES ('5', '发布失败');

-- ----------------------------
-- Table structure for program
-- ----------------------------
DROP TABLE IF EXISTS `program`;
CREATE TABLE `program` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '节目名称',
  `over` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '缩略图存储地址',
  `resolution_id` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '分辨率',
  `time` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '节目时长',
  `size` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '节目大小',
  `state` int(11) DEFAULT NULL COMMENT '节目状态，0表示未使用，1表示已使用',
  `user_id` varchar(11) COLLATE utf8_bin DEFAULT NULL COMMENT '用户编号',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `material1` int(11) DEFAULT NULL COMMENT '素材1',
  `material2` int(11) DEFAULT NULL COMMENT '素材2',
  `material3` int(11) DEFAULT NULL COMMENT '素材3',
  `material4` int(11) DEFAULT NULL COMMENT '素材4',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of program
-- ----------------------------
INSERT INTO `program` VALUES ('1', '111', 'http://localhost:8080/img/2022-07-187/4d6e7b47-184f-4072-8797-e6d936455478.png', '1920*1080', '5秒', '15.76KB', '0', '1', '2022-07-15 10:27:03', '1', '1', '1', '1');
INSERT INTO `program` VALUES ('2', '1111', 'http://localhost:8080/img/2022-07-187/4d6e7b47-184f-4072-8797-e6d936455478.png', '1920*1080', '5s', '16.66KB', '0', '1', '2022-07-05 09:29:52', '1', '1', '1', '1');
INSERT INTO `program` VALUES ('28', '1', 'http://localhost:8080/img/2022-07-187/4d6e7b47-184f-4072-8797-e6d936455478.png', '1920*1080(横)', '5s', '25.66KB', '0', '管理员', '2022-07-06 16:25:45', '1', '1', '1', '1');
INSERT INTO `program` VALUES ('29', 'QQ', 'http://localhost:8080/img/2022-07-188/bfef7e5e-10dd-4638-b085-74e26f1163f5.png', '1920*1080(横)', '5s', '25.66KB', '0', '管理员', '2022-07-06 16:30:14', '1', '1', '1', '1');
INSERT INTO `program` VALUES ('30', 'uichi', 'http://localhost:8080/img/2022-07-188/0e386b5f-a6b7-4efc-9a79-507513238f70.png', '1920*1080(横)', '5s', '25.66KB', '0', '管理员', '2022-07-07 02:36:49', '1', '1', '1', '1');
INSERT INTO `program` VALUES ('31', 'hiwefhi', 'http://localhost:8080/img/2022-07-188/2f8b6292-6b56-4a4f-83a2-67b2b13ce116.png', '1920*1080(横)', '5s', '25.66KB', '0', '管理员', '2022-07-07 02:43:20', '1', '1', '1', '1');

-- ----------------------------
-- Table structure for pubAnnouncement
-- ----------------------------
DROP TABLE IF EXISTS `pubAnnouncement`;
CREATE TABLE `pubAnnouncement` (
  `pub_announcement_id` int(11) NOT NULL AUTO_INCREMENT,
  `pub_announcement_contetn` varchar(255) DEFAULT NULL,
  `pub_announcement_text_color` varchar(255) DEFAULT NULL,
  `pub_announcement_size` varchar(255) DEFAULT NULL,
  `pub_announcement_bgcolor` varchar(255) DEFAULT NULL,
  `pub_announcement_bghigh` varchar(255) DEFAULT NULL,
  `pub_announcement_texthigh` varchar(255) DEFAULT NULL,
  `pub_announcement_playspeed` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pub_announcement_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pubAnnouncement
-- ----------------------------
INSERT INTO `pubAnnouncement` VALUES ('1', 'test1', '#ffffff', '5', '#000000', '10', '10', '2');

-- ----------------------------
-- Table structure for pubProgram
-- ----------------------------
DROP TABLE IF EXISTS `pubProgram`;
CREATE TABLE `pubProgram` (
  `pub_program_id` int(11) NOT NULL AUTO_INCREMENT,
  `pub_program_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pub_program_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of pubProgram
-- ----------------------------

-- ----------------------------
-- Table structure for resolution
-- ----------------------------
DROP TABLE IF EXISTS `resolution`;
CREATE TABLE `resolution` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '分辨率',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of resolution
-- ----------------------------
INSERT INTO `resolution` VALUES ('1', '1920 * 1080 (横)');
INSERT INTO `resolution` VALUES ('2', '1080 * 1920 (竖)');
INSERT INTO `resolution` VALUES ('3', '3840 * 2160 (横)');
INSERT INTO `resolution` VALUES ('4', '2160 * 3840 (竖)');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `pwd` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `institutionsId` int(11) DEFAULT NULL COMMENT '机构编号',
  `userStateId` int(11) DEFAULT NULL COMMENT '用户状态',
  `trueName` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户真实姓名',
  `phoneNumber` int(11) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `update` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '1', '1', '1', 'aaa', '123', '123@qq.com', '2022-06-29 14:58:35');
INSERT INTO `user` VALUES ('2', '2', '2', '2', '2', '2', '2', '2', '2022-06-16 19:06:48');

-- ----------------------------
-- Table structure for userstate
-- ----------------------------
DROP TABLE IF EXISTS `userstate`;
CREATE TABLE `userstate` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '用户状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of userstate
-- ----------------------------
INSERT INTO `userstate` VALUES ('1', '启用');
INSERT INTO `userstate` VALUES ('2', '停用');

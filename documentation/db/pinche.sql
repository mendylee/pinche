/*
Navicat MySQL Data Transfer

Source Server         : 测试环境
Source Server Version : 50724
Source Host           : 192.168.10.118:3306
Source Database       : pinche

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2019-10-26 16:43:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `sys_car`
-- ----------------------------
DROP TABLE IF EXISTS `sys_car`;
CREATE TABLE `sys_car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `color` tinyint(1) DEFAULT '0' COMMENT '汽车颜色',
  `licence` varchar(50) DEFAULT NULL COMMENT '车牌号',
  `type` tinyint(2) NOT NULL DEFAULT '1' COMMENT '汽车类型：1.小型汽车,2.SUV,3.商务车,4.其它/未知',
  `brand` varchar(50) DEFAULT NULL COMMENT '汽车品牌',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `licence` (`licence`) USING BTREE,
  KEY `brand` (`brand`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `sys_address`
-- ----------------------------
DROP TABLE IF EXISTS `sys_address`;
CREATE TABLE `sys_address` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `address` varchar(50) DEFAULT NULL COMMENT '常用地址',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `ums_user`
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `car_id` int(11) DEFAULT NULL,
  `username` varchar(50) NOT NULL COMMENT '用户名称',
  `password` varchar(60) NOT NULL COMMENT '登录密码',
  `nickname` varchar(255) DEFAULT NULL COMMENT '用户昵称',
  `head_img_url` varchar(1024) DEFAULT NULL COMMENT '用户头像',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `sex` tinyint(1) DEFAULT '0' COMMENT '性别：0,女，1.男',
  `enabled` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否禁用,0禁用，1启用',
  `address` varchar(255) DEFAULT NULL COMMENT '家庭住址,如：东区H23',
  `open_id` varchar(32) DEFAULT NULL COMMENT '第三方ID',
  `is_del` tinyint(1) NOT NULL DEFAULT '0' COMMENT '是否删除',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `username` (`username`) USING BTREE,
  KEY `mobile` (`mobile`) USING BTREE,
  KEY `open_id` (`open_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `ums_share_car`
-- ----------------------------
DROP TABLE IF EXISTS `ums_share_car`;
CREATE TABLE `ums_share_car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名称',
  `address` varchar(255) DEFAULT NULL COMMENT '家庭住址,如：东区H23',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `source` varchar(50) NOT NULL COMMENT '起始地',
  `target` varchar(50) NOT NULL COMMENT '出发地',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1.上班，2.回家',
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0,已取消，1已发布',
  `stall` tinyint(2) NOT NULL DEFAULT '1' COMMENT '座位数',
  `date` tinyint(1) NOT NULL DEFAULT '1' COMMENT '乘坐日期:1今天，2明天',
  `start_time` BIGINT(8) DEFAULT NULL COMMENT '乘坐时间',
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `source` (`source`) USING BTREE,
  KEY `target` (`target`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `ums_share_car_history`
-- ----------------------------
DROP TABLE IF EXISTS `ums_share_car_history`;
CREATE TABLE `ums_share_car_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名称',
  `address` varchar(255) DEFAULT NULL COMMENT '家庭住址,如：东区H23',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `source` varchar(50) NOT NULL COMMENT '起始地',
  `target` varchar(50) NOT NULL COMMENT '出发地',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1.上班，2.回家',
  `stall` tinyint(2) NOT NULL DEFAULT '1' COMMENT '座位数',
  `date` tinyint(1) NOT NULL DEFAULT '1' COMMENT '乘坐日期:1今天，2明天',
  `start_time` BIGINT(8) DEFAULT NULL COMMENT '乘坐时间',
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `source` (`source`) USING BTREE,
  KEY `target` (`target`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `ums_share_car_order`
-- ----------------------------
DROP TABLE IF EXISTS `ums_share_car_order`;
CREATE TABLE `ums_share_car_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` VARCHAR(50) NOT NULL COMMENT '订单ID', 
  `uid` int(11) NOT NULL COMMENT '乘客ID',
  `owner_id` int(11) NOT NULL COMMENT '车主ID',
  `source` varchar(50) NOT NULL COMMENT '起始地',
  `target` varchar(50) NOT NULL COMMENT '出发地',
  `stall` tinyint(2) NOT NULL DEFAULT '1' COMMENT '座位数',
  `start_time` BIGINT(8) DEFAULT NULL COMMENT '乘坐时间',
  `state` tinyint(2) NOT NULL COMMENT '订单状态:0,已取消，1已发布,2,进行中,3.已完成',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `state` (`state`) USING BTREE,
  KEY `source` (`source`) USING BTREE,
  KEY `target` (`target`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `ums_share_car`
-- ----------------------------
DROP TABLE IF EXISTS `ums_dispatch_car`;
CREATE TABLE `ums_dispatch_car` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名称',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `car_id` int(11) NOT NULL COMMENT '汽车ID',
  `color` tinyint(1) DEFAULT '0' COMMENT '汽车颜色',
  `licence` varchar(50) DEFAULT NULL COMMENT '车牌号',
  `source` varchar(50) NOT NULL COMMENT '起始地',
  `passes` varchar(255) NOT NULL COMMENT '途径地',
  `target` varchar(50) NOT NULL COMMENT '目的地',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1.上班，2.回家',
  `state` tinyint(1) NOT NULL DEFAULT '1' COMMENT '0.已取消，1.已发布',
  `stall` tinyint(2) NOT NULL DEFAULT '1' COMMENT '座位数',
  `date` tinyint(1) NOT NULL DEFAULT '1' COMMENT '乘坐日期:1今天，2明天',
  `start_time` BIGINT(8) DEFAULT NULL COMMENT '发车时间',
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `source` (`source`) USING BTREE,
  KEY `target` (`target`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `ums_dispatch_car_history`
-- ----------------------------
DROP TABLE IF EXISTS `ums_dispatch_car_history`;
CREATE TABLE `ums_dispatch_car_history` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名称',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `car_id` int(11) NOT NULL COMMENT '汽车ID',
  `color` tinyint(1) DEFAULT '0' COMMENT '汽车颜色',
  `licence` varchar(50) DEFAULT NULL COMMENT '车牌号',
  `source` varchar(50) NOT NULL COMMENT '起始地',
  `passes` varchar(255) NOT NULL COMMENT '途径地',
  `target` varchar(50) NOT NULL COMMENT '目的地',
  `type` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1.上班，2.回家',
  `stall` tinyint(2) NOT NULL DEFAULT '1' COMMENT '座位数',
  `date` tinyint(1) NOT NULL DEFAULT '1' COMMENT '乘坐日期:1今天，2明天',
  `start_time` BIGINT(8) DEFAULT NULL COMMENT '发车时间',
  `remark` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `source` (`source`) USING BTREE,
  KEY `passes` (`passes`) USING BTREE,
  KEY `target` (`target`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `ums_share_car_order`
-- ----------------------------
DROP TABLE IF EXISTS `ums_dispatch_car_order`;
CREATE TABLE `ums_dispatch_car_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '车主ID',
  `order_id` VARCHAR(50) NOT NULL COMMENT '订单ID', 
  `passengers` int(11) NOT NULL COMMENT '乘客ID列表',
  `source` varchar(50) NOT NULL COMMENT '起始地',
  `passes` varchar(50) NOT NULL COMMENT '起始地',
  `target` varchar(50) NOT NULL COMMENT '出发地',
  `stall` tinyint(2) NOT NULL DEFAULT '1' COMMENT '座位数',
  `start_time` BIGINT(8) DEFAULT NULL COMMENT '乘坐时间',
  `state` tinyint(2) NOT NULL COMMENT '订单状态:0,已取消，1已发布,2,进行中,3.已完成',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `state` (`state`) USING BTREE,
  KEY `source` (`source`) USING BTREE,
  KEY `target` (`target`) USING BTREE,
  KEY `uid` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
--  Table structure for `t_tx_exception`
-- ----------------------------
DROP TABLE IF EXISTS `t_tx_exception`;
CREATE TABLE `t_tx_exception` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `group_id` varchar(64) DEFAULT NULL,
  `unit_id` varchar(32) DEFAULT NULL,
  `mod_id` varchar(128) DEFAULT NULL,
  `transaction_state` tinyint(4) DEFAULT NULL,
  `registrar` tinyint(4) DEFAULT NULL,
  `remark` varchar(4096) DEFAULT NULL,
  `ex_state` tinyint(4) DEFAULT NULL COMMENT '0 未解决 1已解决',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

/*
 Navicat Premium Data Transfer

 Source Server         : master-mysql
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : localhost:3307
 Source Schema         : sharding-jdbc-ms

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 10/03/2020 23:49:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for coupon_0
-- ----------------------------
DROP TABLE IF EXISTS `coupon_0`;
CREATE TABLE `coupon_0` (
  `id` bigint(64) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for coupon_1
-- ----------------------------
DROP TABLE IF EXISTS `coupon_1`;
CREATE TABLE `coupon_1` (
  `id` bigint(64) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Table structure for member
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `member_no` varchar(30) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '会员编号',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=292 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin ROW_FORMAT=COMPACT COMMENT='会员表';

SET FOREIGN_KEY_CHECKS = 1;

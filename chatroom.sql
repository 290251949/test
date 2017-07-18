/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50717
Source Host           : 127.0.0.1:3306
Source Database       : chatroom

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-07-18 17:50:20
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for friend
-- ----------------------------
DROP TABLE IF EXISTS `friend`;
CREATE TABLE `friend` (
  `name1` varchar(255) DEFAULT NULL,
  `name2` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of friend
-- ----------------------------
INSERT INTO `friend` VALUES ('哈尼779', '哈尼780');
INSERT INTO `friend` VALUES ('哈尼781', '哈尼779');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `from` varchar(255) DEFAULT NULL,
  `to` varchar(255) DEFAULT NULL,
  `msg` varchar(255) DEFAULT NULL,
  `isRead` tinyint(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('哈尼779', '哈尼780', '啊啊啊啊啊啊', '1');
INSERT INTO `message` VALUES ('哈尼780', '哈尼779', '哈罗', '1');
INSERT INTO `message` VALUES ('哈尼780', '哈尼779', '你好啊', '1');
INSERT INTO `message` VALUES ('哈尼779', '哈尼780', 'kkk', '1');
INSERT INTO `message` VALUES ('哈尼780', '哈尼779', '愚蠢', '1');
INSERT INTO `message` VALUES ('哈尼780', '哈尼779', '可以吗', '1');
INSERT INTO `message` VALUES ('哈尼779', '哈尼780', '可以', '1');
INSERT INTO `message` VALUES ('哈尼779', '哈尼780', 'halo', '1');
INSERT INTO `message` VALUES ('哈尼780', '哈尼779', '你是真的皮', '1');
INSERT INTO `message` VALUES ('哈尼779', '哈尼780', '你哈', '1');
INSERT INTO `message` VALUES ('哈尼779', '哈尼780', '你皮任你皮，把你当瓜皮', '1');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `head` varchar(255) DEFAULT NULL,
  `label` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('哈尼779', '12345678', '1.png', '再牛逼的肖邦，也弹不出我的悲伤。');
INSERT INTO `user` VALUES ('哈尼780', '12345678', '2.png', '你的牌打得也太好了。');
INSERT INTO `user` VALUES ('哈尼781', '12345678', '3.png', '快点啊，我等得我花都谢了。');

/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50646
Source Host           : 127.0.0.1:3306
Source Database       : book

Target Server Type    : MYSQL
Target Server Version : 50646
File Encoding         : 65001

Date: 2020-07-09 18:33:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `author` varchar(100) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `imgPath` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES ('2', '数据结构与算法', '78.50', '严敏君', '8', '89', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('4', '木虚肉盖饭', '16.00', '小胖', '1003', '47', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('5', 'C++编程思想', '45.50', '刚哥', '24', '85', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('6', '蛋炒饭', '9.90', '周星星', '16', '49', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('7', '赌神', '66.50', '龙伍', '125', '535', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('8', 'Java编程思想', '99.50', '阳哥', '47', '36', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('9', 'JavaScript从入门到精通', '9.90', '婷姐', '85', '95', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('10', 'cocos2d-x游戏编程入门', '49.00', '国哥', '52', '62', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('11', 'C语言程序设计', '28.00', '谭浩强', '52', '74', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('12', 'Lua语言程序设计', '51.50', '雷丰阳', '48', '82', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('13', '西游记', '12.00', '罗贯中', '19', '9999', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('14', '水浒传', '33.05', '华仔', '22', '88', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('15', '操作系统原理', '133.05', '刘优', '122', '188', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('16', '数据结构 java版', '173.15', '封大神', '21', '81', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('17', 'UNIX高级环境编程', '99.15', '乐天', '210', '810', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('18', 'javaScript高级编程', '69.15', '国哥', '210', '810', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('19', '大话设计模式', '89.15', '国哥', '20', '10', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('20', '人月神话', '88.15', '刚哥', '20', '80', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('25', '1qwe', '156.00', 'qwe', '16561', '960', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('26', '111', '111.00', '111', '111', '111', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('27', '221', '222.00', '22', '2', '2', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('29', '120', '123.00', '123', '213', '123', 'static/img/default.jpg');
INSERT INTO `t_book` VALUES ('30', 'qwe', '12.00', 'qwe', '123', '123', 'static/img/default.jpg');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `orderId` varchar(50) NOT NULL DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`orderId`),
  KEY `user_id` (`userId`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('123456', '2020-04-09 22:57:58', '100.00', '2', '1');
INSERT INTO `t_order` VALUES ('15864459352041', '2020-04-09 23:25:35', '2100.00', '1', '1');
INSERT INTO `t_order` VALUES ('15864910831541', '2020-04-10 11:58:03', '94.50', '2', '1');
INSERT INTO `t_order` VALUES ('15864914931221', '2020-04-10 12:04:53', '140.00', '2', '1');
INSERT INTO `t_order` VALUES ('15866099891543', '2020-04-11 20:59:49', '107.00', '0', '3');
INSERT INTO `t_order` VALUES ('15866100006593', '2020-04-11 21:00:00', '45.50', '1', '3');
INSERT INTO `t_order` VALUES ('15866100069923', '2020-04-11 21:00:06', '45.50', '2', '3');
INSERT INTO `t_order` VALUES ('15866100143453', '2020-04-11 21:00:14', '149.90', '2', '3');
INSERT INTO `t_order` VALUES ('15867002083631', '2020-04-12 22:03:28', '45.50', '2', '1');
INSERT INTO `t_order` VALUES ('15869206133891', '2020-04-15 11:16:53', '55.40', '0', '1');
INSERT INTO `t_order` VALUES ('15869601075171', '2020-04-15 22:15:07', '55.40', '0', '1');

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `count` int(11) DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `totalPrice` decimal(11,2) DEFAULT NULL,
  `orderId` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`orderId`),
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`orderId`) REFERENCES `t_order` (`orderId`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
INSERT INTO `t_order_item` VALUES ('1', 'java从入门到入土', '1', '100.00', '100.00', '123456');
INSERT INTO `t_order_item` VALUES ('2', 'java从入门到精通', '2', '1000.00', '2000.00', '15864459352041');
INSERT INTO `t_order_item` VALUES ('3', 'java从入门到月门', '1', '100.00', '100.00', '15864459352041');
INSERT INTO `t_order_item` VALUES ('4', '数据结构与算法', '1', '78.50', '78.50', '15864910831541');
INSERT INTO `t_order_item` VALUES ('5', '木虚肉盖饭', '1', '16.00', '16.00', '15864910831541');
INSERT INTO `t_order_item` VALUES ('6', '数据结构与算法', '1', '78.50', '78.50', '15864914931221');
INSERT INTO `t_order_item` VALUES ('7', '木虚肉盖饭', '1', '16.00', '16.00', '15864914931221');
INSERT INTO `t_order_item` VALUES ('8', 'C++编程思想', '1', '45.50', '45.50', '15864914931221');
INSERT INTO `t_order_item` VALUES ('9', 'C++编程思想', '2', '45.50', '91.00', '15866099891543');
INSERT INTO `t_order_item` VALUES ('10', '木虚肉盖饭', '1', '16.00', '16.00', '15866099891543');
INSERT INTO `t_order_item` VALUES ('11', 'C++编程思想', '1', '45.50', '45.50', '15866100006593');
INSERT INTO `t_order_item` VALUES ('12', 'C++编程思想', '1', '45.50', '45.50', '15866100069923');
INSERT INTO `t_order_item` VALUES ('13', '蛋炒饭', '1', '9.90', '9.90', '15866100143453');
INSERT INTO `t_order_item` VALUES ('14', 'C++编程思想', '1', '45.50', '45.50', '15866100143453');
INSERT INTO `t_order_item` VALUES ('15', '木虚肉盖饭', '1', '16.00', '16.00', '15866100143453');
INSERT INTO `t_order_item` VALUES ('16', '数据结构与算法', '1', '78.50', '78.50', '15866100143453');
INSERT INTO `t_order_item` VALUES ('17', 'C++编程思想', '1', '45.50', '45.50', '15867002083631');
INSERT INTO `t_order_item` VALUES ('18', 'C++编程思想', '1', '45.50', '45.50', '15869206133891');
INSERT INTO `t_order_item` VALUES ('19', '蛋炒饭', '1', '9.90', '9.90', '15869206133891');
INSERT INTO `t_order_item` VALUES ('20', 'C++编程思想', '1', '45.50', '45.50', '15869601075171');
INSERT INTO `t_order_item` VALUES ('21', '蛋炒饭', '1', '9.90', '9.90', '15869601075171');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(200) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', 'admin', 'admin@126.com', '1');
INSERT INTO `t_user` VALUES ('3', 'qweqwe', 'qweqwe', 'qwe@123.com', '0');
INSERT INTO `t_user` VALUES ('4', 'bbj666', '666666', 'bbj@168.com', '0');
INSERT INTO `t_user` VALUES ('5', 'abc666', '666666', 'bbj@168.com', '0');
INSERT INTO `t_user` VALUES ('6', 'adq123', 'adq123', '123@123.com', '0');
INSERT INTO `t_user` VALUES ('7', 'qweasd', 'qweasd', 'qwe@qq.com', '0');
INSERT INTO `t_user` VALUES ('8', 'new', 'new', '123@new.com', '0');
INSERT INTO `t_user` VALUES ('9', 'qweas', 'qweas', 'qweas@qq.copm', '0');
INSERT INTO `t_user` VALUES ('10', 'zxczxc', 'zxczxc', 'qwe12@qwec.com', '0');

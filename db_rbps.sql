/*
Navicat MySQL Data Transfer

Source Server         : localhost3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : db_rbps

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2018-11-15 14:59:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_auth`
-- ----------------------------
DROP TABLE IF EXISTS `t_auth`;
CREATE TABLE `t_auth` (
  `authId` int(11) NOT NULL AUTO_INCREMENT,
  `authName` varchar(20) DEFAULT NULL,
  `authPath` varchar(100) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `authDescription` varchar(200) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `iconCls` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`authId`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_auth
-- ----------------------------
INSERT INTO `t_auth` VALUES ('1', '纵拓互联', '', '-1', null, 'closed', 'icon-home');
INSERT INTO `t_auth` VALUES ('2', '权限管理', '', '1', null, 'closed', 'icon-permission');
INSERT INTO `t_auth` VALUES ('3', '档案管理', '123', '1', '我擦', 'closed', 'icon-student');
INSERT INTO `t_auth` VALUES ('4', '考勤管理', '123', '1', '', 'closed', 'icon-course');
INSERT INTO `t_auth` VALUES ('5', '住宿管理', 'zsgl.html', '3', null, 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('6', '学生信息管理', 'toUserInfo.do', '3', null, 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('7', '学籍管理', 'xjgl.html', '3', null, 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('8', '奖惩管理', 'jcgl.html', '3', null, 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('9', '课程设置', 'kcsz.html', '4', null, 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('10', '选课情况', 'xkqk.html', '4', null, 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('11', '成绩录入', 'cjlr.html', '4', null, 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('12', '用户管理', 'userManage.html', '2', null, 'open', 'icon-userManage');
INSERT INTO `t_auth` VALUES ('13', '部门管理', 'roleManage.html', '2', '', 'open', 'icon-roleManage');
INSERT INTO `t_auth` VALUES ('14', '菜单管理', 'authManage.html', '2', null, 'open', 'icon-menuManage');
INSERT INTO `t_auth` VALUES ('15', '修改密码', '', '1', null, 'open', 'icon-modifyPassword');
INSERT INTO `t_auth` VALUES ('16', '安全退出', '', '1', null, 'open', 'icon-exit');
INSERT INTO `t_auth` VALUES ('24', '成绩管理', 'jcgl.html', '3', '成绩管理', 'open', 'icon-item');
INSERT INTO `t_auth` VALUES ('25', '档案管理', 'userManage.html', '3', '学生档案管理', 'open', 'icon-item');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) DEFAULT NULL,
  `authIds` varchar(50) DEFAULT NULL,
  `roleDescription` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '超级管理员', '1,2,12,13,14,3,5,6,7,8,24,25,4,9,10,11,15,16', '具有最高权限');
INSERT INTO `t_role` VALUES ('2', '人事部', '1,3,5,6,7,8,24,25,16', '管理学生宿舍信息');
INSERT INTO `t_role` VALUES ('3', '行政部', '1,2,12,13,14,3,4,9,10,11,15,16', '负责学生的生活，学习及心里问题');
INSERT INTO `t_role` VALUES ('8', '教师', '', '讲课的');
INSERT INTO `t_role` VALUES ('9', '学生', '', '你懂的');
INSERT INTO `t_role` VALUES ('18', '辅导员', '', '略');
INSERT INTO `t_role` VALUES ('19', 'A', null, null);
INSERT INTO `t_role` VALUES ('23', 'B', '', 'AB');
INSERT INTO `t_role` VALUES ('24', 'AAAA', '', 'dddd');
INSERT INTO `t_role` VALUES ('25', 'CCCCC我靠', '', '我靠2');
INSERT INTO `t_role` VALUES ('26', 'test', '1,2,12', 'test');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `userType` tinyint(4) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `userDescription` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `FK_t_user` (`roleId`),
  CONSTRAINT `FK_t_user` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '123456', '1', '1', null);
INSERT INTO `t_user` VALUES ('2', 'marry', '123456', '2', '2', '好');
INSERT INTO `t_user` VALUES ('3', 'java', 'javacc', '2', '3', 'guanli元');
INSERT INTO `t_user` VALUES ('15', 'zhangsan', 'javacc', '2', '18', '');
INSERT INTO `t_user` VALUES ('28', 'java', '123', '2', '2', 'hao 管理');
INSERT INTO `t_user` VALUES ('29', 'java', '123444', '2', '3', 'hao 管理  today');
INSERT INTO `t_user` VALUES ('31', 'xiaoli', '123456', '2', '3', '这是一个宿舍管理员');
INSERT INTO `t_user` VALUES ('32', 'test', '123456', '2', '2', '');
INSERT INTO `t_user` VALUES ('33', 'test1', '123', '2', '19', '');

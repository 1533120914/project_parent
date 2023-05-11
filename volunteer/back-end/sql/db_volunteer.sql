/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : db_volunteer

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2023-05-06 18:58:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `admin`
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(32) NOT NULL COMMENT '管理员名称',
  `password` varchar(32) NOT NULL COMMENT '管理员密码',
  `avatar` varchar(64) DEFAULT NULL COMMENT '管理员头像',
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='管理员表';

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('1', 'admin', '12345', '1683366647177137C36E4C25747FE8ACBC2CC516F6F7F.jpg', '0');

-- ----------------------------
-- Table structure for `task`
-- ----------------------------
DROP TABLE IF EXISTS `task`;
CREATE TABLE `task` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `type_id` int(11) DEFAULT NULL,
  `type_name` varchar(32) DEFAULT NULL,
  `title` varchar(32) NOT NULL COMMENT '任务标题',
  `description` varchar(32) NOT NULL COMMENT '任务描述',
  `volunteer_id` int(11) DEFAULT NULL,
  `volunteer_name` varchar(32) DEFAULT NULL,
  `is_delete` int(11) DEFAULT '0',
  `state` int(11) DEFAULT '0',
  `create_by` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=55 DEFAULT CHARSET=utf8mb4 COMMENT='任务类型表';

-- ----------------------------
-- Records of task
-- ----------------------------
INSERT INTO `task` VALUES ('1', '1', '派发物资', '4-1日1号楼派发物资', '口罩,体温计,水,方便面', '2', '李白', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('2', '1', '派发物资', '4-1日2号楼派发物资', '口罩,体温计,水,方便面', '2', '李白', '0', '2', '1', '2023-04-01 00:00:00', '2023-05-06 12:17:29');
INSERT INTO `task` VALUES ('3', '1', '派发物资', '4-1日3号楼派发物资', '口罩,体温计,水,方便面', '2', '李白', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('4', '1', '派发物资', '4-1日4号楼派发物资', '口罩,体温计,水,方便面', '2', '李白', '0', '2', '1', '2023-04-01 00:00:00', '2023-05-06 12:27:35');
INSERT INTO `task` VALUES ('5', '1', '派发物资', '4-1日5号楼派发物资', '口罩,体温计,水,方便面', '2', '李白', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('6', '1', '派发物资', '4-2日1号楼派发物资', '口罩,体温计,水,方便面', '2', '李白', '0', '2', '1', '2023-04-02 00:00:00', '2023-05-06 12:33:28');
INSERT INTO `task` VALUES ('7', '1', '派发物资', '4-2日2号楼派发物资', '口罩,体温计,水,方便面', '2', '李白', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('8', '1', '派发物资', '4-2日3号楼派发物资', '口罩,体温计,水,方便面', '2', '李白', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('9', '1', '派发物资', '4-2日4号楼派发物资', '口罩,体温计,水,方便面', '2', '李白', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('10', '1', '派发物资', '4-2日5号楼派发物资', '口罩,体温计,水,方便面', '2', '李白', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('11', '2', '核酸检测', '4-1日1号楼派发物资', '配合医护人员进行核算检测', '2', '李白', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('12', '2', '核酸检测', '4-1日2号楼派发物资', '配合医护人员进行核算检测', '2', '李白', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('13', '2', '核酸检测', '4-1日3号楼派发物资', '配合医护人员进行核算检测', '3', '杜甫', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('14', '2', '核酸检测', '4-1日4号楼派发物资', '配合医护人员进行核算检测', '3', '杜甫', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('15', '2', '核酸检测', '4-1日5号楼派发物资', '配合医护人员进行核算检测', '3', '杜甫', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('16', '2', '核酸检测', '4-2日1号楼派发物资', '配合医护人员进行核算检测', '3', '杜甫', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('17', '2', '核酸检测', '4-2日2号楼派发物资', '配合医护人员进行核算检测', '3', '杜甫', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('18', '2', '核酸检测', '4-2日3号楼派发物资', '配合医护人员进行核算检测', '3', '杜甫', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('19', '2', '核酸检测', '4-2日4号楼派发物资', '配合医护人员进行核算检测', '3', '杜甫', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('20', '2', '核酸检测', '4-2日5号楼派发物资', '配合医护人员进行核算检测', '4', '王维', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('21', '3', '体温检测', '4-1日1号楼体温检测', '检测有没有体温异常的同学', '4', '王维', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('22', '3', '体温检测', '4-1日2号楼体温检测', '检测有没有体温异常的同学', '4', '王维', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('23', '3', '体温检测', '4-1日3号楼体温检测', '检测有没有体温异常的同学', '4', '王维', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('24', '3', '体温检测', '4-1日4号楼体温检测', '检测有没有体温异常的同学', '4', '王维', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('25', '3', '体温检测', '4-1日5号楼体温检测', '检测有没有体温异常的同学', '4', '王维', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('26', '3', '体温检测', '4-2日1号楼体温检测', '检测有没有体温异常的同学', '4', '王维', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('27', '3', '体温检测', '4-2日2号楼体温检测', '检测有没有体温异常的同学', '4', '王维', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('28', '3', '体温检测', '4-2日3号楼体温检测', '检测有没有体温异常的同学', '4', '王维', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('29', '3', '体温检测', '4-2日4号楼体温检测', '检测有没有体温异常的同学', '4', '王维', '0', '2', '1', '2023-04-02 00:00:00', '2023-05-06 17:53:58');
INSERT INTO `task` VALUES ('30', '3', '体温检测', '4-2日5号楼体温检测', '检测有没有体温异常的同学', '5', '苏轼', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('31', '4', '食堂检测', '4-1日1号楼食堂检测', '检测食堂有没有不规范的就餐行为', '5', '苏轼', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('32', '4', '食堂检测', '4-1日2号楼食堂检测', '检测食堂有没有不规范的就餐行为', '5', '苏轼', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('33', '4', '食堂检测', '4-1日3号楼食堂检测', '检测食堂有没有不规范的就餐行为', '5', '苏轼', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('34', '4', '食堂检测', '4-1日4号楼食堂检测', '检测食堂有没有不规范的就餐行为', '5', '苏轼', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('35', '4', '食堂检测', '4-1日5号楼食堂检测', '检测食堂有没有不规范的就餐行为', '5', '苏轼', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('36', '4', '食堂检测', '4-2日1号楼食堂检测', '检测食堂有没有不规范的就餐行为', '5', '苏轼', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('37', '4', '食堂检测', '4-2日2号楼食堂检测', '检测食堂有没有不规范的就餐行为', '5', '苏轼', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('38', '4', '食堂检测', '4-2日3号楼食堂检测', '检测食堂有没有不规范的就餐行为', '5', '苏轼', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('39', '4', '食堂检测', '4-2日4号楼食堂检测', '检测食堂有没有不规范的就餐行为', '5', '苏轼', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('40', '4', '食堂检测', '4-2日5号楼食堂检测', '检测食堂有没有不规范的就餐行为', '5', '苏轼', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('41', '5', '宿舍检查', '4-1日1号楼宿舍检查', '检查宿舍有没有异常的同学', '5', '苏轼', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('42', '5', '宿舍检查', '4-1日2号楼宿舍检查', '检查宿舍有没有异常的同学', '5', '苏轼', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('43', '5', '宿舍检查', '4-1日3号楼宿舍检查', '检查宿舍有没有异常的同学', '5', '苏轼', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('44', '5', '宿舍检查', '4-1日4号楼宿舍检查', '检查宿舍有没有异常的同学', '5', '苏轼', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('45', '5', '宿舍检查', '4-1日5号楼宿舍检查', '检查宿舍有没有异常的同学', '5', '苏轼', '0', '2', '1', '2023-04-01 00:00:00', null);
INSERT INTO `task` VALUES ('46', '5', '宿舍检查', '4-2日1号楼宿舍检查', '检查宿舍有没有异常的同学', '5', '苏轼', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('47', '5', '宿舍检查', '4-2日2号楼宿舍检查', '检查宿舍有没有异常的同学', '5', '苏轼', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('48', '5', '宿舍检查', '4-2日3号楼宿舍检查', '检查宿舍有没有异常的同学', '6', '李清照', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('49', '5', '宿舍检查', '4-2日4号楼宿舍检查', '检查宿舍有没有异常的同学', '6', '李清照', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('50', '5', '宿舍检查', '4-2日5号楼宿舍检查', '检查宿舍有没有异常的同学', '6', '李清照', '0', '2', '1', '2023-04-02 00:00:00', null);
INSERT INTO `task` VALUES ('51', '6', '打扫食堂', '5-6日打扫食堂', '一号食堂大扫除', null, null, '0', '0', '1', '2023-05-06 12:50:42', null);
INSERT INTO `task` VALUES ('52', '2', '核酸检测', '5-6日核酸检测', '5-6日配合医护人员核酸检测', null, null, '1', '1', '1', '2023-05-06 12:53:44', null);
INSERT INTO `task` VALUES ('53', '4', '食堂检测', '5-6日宿舍检测', '略', null, null, '1', '0', '1', '2023-05-06 12:55:14', null);
INSERT INTO `task` VALUES ('54', '1', '派发物资', '测试', '测试', '2', '李白', '0', '2', null, '2023-05-06 18:23:19', '2023-05-06 18:56:34');

-- ----------------------------
-- Table structure for `task_picture`
-- ----------------------------
DROP TABLE IF EXISTS `task_picture`;
CREATE TABLE `task_picture` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `task_id` int(11) DEFAULT NULL,
  `file_name` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of task_picture
-- ----------------------------
INSERT INTO `task_picture` VALUES ('5', '2', '1683346603248B8CE8F9163954351A1293F0D7925BE98.jpg');
INSERT INTO `task_picture` VALUES ('6', '2', '168334660324837EBA1E43FFF4B468BA5138A52C5AA4C.jpg');
INSERT INTO `task_picture` VALUES ('7', '2', '168334664700313220E7491894EC189CBAFA3467869A4.jpg');
INSERT INTO `task_picture` VALUES ('8', '29', '1683366819766C4BCEE96B05D45989120DB025DA1A7D9.jpg');
INSERT INTO `task_picture` VALUES ('9', '29', '1683366819766E780512204A2435582E8034F29340850.jpg');
INSERT INTO `task_picture` VALUES ('10', '29', '1683366819766350A09B9471447519A646C8BE4D64E45.jpg');
INSERT INTO `task_picture` VALUES ('11', '54', '1683370591095798C5346EBE54C3F95EABA2362784C6F.jpg');
INSERT INTO `task_picture` VALUES ('12', '54', '16833705910957A24E3A822C94744AB051A0C9F959CED.jpg');

-- ----------------------------
-- Table structure for `task_type`
-- ----------------------------
DROP TABLE IF EXISTS `task_type`;
CREATE TABLE `task_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) NOT NULL COMMENT '任务类型名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COMMENT='任务类型表';

-- ----------------------------
-- Records of task_type
-- ----------------------------
INSERT INTO `task_type` VALUES ('1', '派发物资');
INSERT INTO `task_type` VALUES ('2', '核酸检测');
INSERT INTO `task_type` VALUES ('3', '体温检测');
INSERT INTO `task_type` VALUES ('4', '食堂检测');
INSERT INTO `task_type` VALUES ('5', '宿舍检查');
INSERT INTO `task_type` VALUES ('6', '打扫食堂');

-- ----------------------------
-- Table structure for `volunteer`
-- ----------------------------
DROP TABLE IF EXISTS `volunteer`;
CREATE TABLE `volunteer` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `username` varchar(32) NOT NULL COMMENT '志愿者姓名',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `avatar` varchar(64) DEFAULT NULL COMMENT '头像',
  `sex` varchar(2) DEFAULT NULL,
  `mobile` varchar(32) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `modify_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COMMENT='志愿者表';

-- ----------------------------
-- Records of volunteer
-- ----------------------------
INSERT INTO `volunteer` VALUES ('1', '许婉', '88888', '16833667564788DF666015A804ECEB4A15A7193D700E2.jpg', '女', '18918918989', '1', '2023-05-06 09:32:06', '2023-05-06 18:13:45');
INSERT INTO `volunteer` VALUES ('2', '李白', '12345', '1683370553778BEE30D5B70074830BB2FE5C64EB40925.jpg', '男', '18918918989', '0', '2023-05-06 18:38:12', '2023-05-06 18:55:54');
INSERT INTO `volunteer` VALUES ('3', '杜甫', '12345', '', '男', '18918918989', '0', '2023-05-06 18:38:12', null);
INSERT INTO `volunteer` VALUES ('4', '王维', '12345', '', '男', '18918918989', '0', '2023-05-06 18:38:12', null);
INSERT INTO `volunteer` VALUES ('5', '苏轼', '12345', '', '男', '18918918989', '0', '2023-05-06 18:38:12', null);
INSERT INTO `volunteer` VALUES ('6', '辛弃疾', '12345', '', '男', '18918918989', '0', '2023-05-06 18:38:12', null);
INSERT INTO `volunteer` VALUES ('7', '李清照', '12345', '', '女', '18918918989', '0', '2023-05-06 18:38:12', null);

-- ----------------------------
-- View structure for `task_apply_count_view`
-- ----------------------------
DROP VIEW IF EXISTS `task_apply_count_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `task_apply_count_view` AS select count(0) AS `count`,`task`.`volunteer_name` AS `volunteer_name` from `task` where (`task`.`volunteer_name` is not null) group by `task`.`volunteer_name` order by `count` desc ;

-- ----------------------------
-- View structure for `task_count_view`
-- ----------------------------
DROP VIEW IF EXISTS `task_count_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `task_count_view` AS select count(0) AS `count`,`task`.`state` AS `state` from `task` group by `task`.`state` order by `task`.`state` ;

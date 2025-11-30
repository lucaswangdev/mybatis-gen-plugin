#
# SQL Export
# Created by Querious (303012)
# Created: November 30, 2025 at 23:17:39 GMT+8
# Encoding: Unicode (UTF-8)
#


SET @ORIG_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS;
SET FOREIGN_KEY_CHECKS = 0;

SET @ORIG_UNIQUE_CHECKS = @@UNIQUE_CHECKS;
SET UNIQUE_CHECKS = 0;

SET @ORIG_TIME_ZONE = @@TIME_ZONE;
SET TIME_ZONE = '+00:00';

SET @ORIG_SQL_MODE = @@SQL_MODE;
SET SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO';



DROP TABLE IF EXISTS `user`;


CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `user_name` varchar(100) DEFAULT NULL COMMENT '用户名',
  `sex` varchar(1) DEFAULT NULL COMMENT '性别',
  `address` varchar(100) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';




LOCK TABLES `user` WRITE;
INSERT INTO `user` (`id`, `gmt_create`, `gmt_modified`, `user_name`, `sex`, `address`) VALUES 
	(2,'2024-08-18 23:16:46','2024-08-18 23:16:46','xxxName','1','Updated Address'),
	(3,'2024-08-18 00:00:42','2024-08-18 00:00:42','SelectiveUser',NULL,NULL),
	(4,'2024-08-18 00:21:06','2024-08-18 00:21:06','SelectiveUser',NULL,NULL),
	(5,'2024-08-18 00:41:30','2024-08-18 00:41:30','SelectiveUser',NULL,NULL),
	(6,'2024-08-18 00:51:57','2024-08-18 00:51:57','SelectiveUser',NULL,NULL),
	(7,'2024-08-18 10:22:58','2024-08-18 10:22:58','SelectiveUser',NULL,NULL),
	(8,'2024-08-18 10:42:09','2024-08-18 10:42:09','Lucas1','M','Address1'),
	(9,'2024-08-18 10:42:09','2024-08-18 10:42:09','Lucas2','M','Address2'),
	(10,'2024-08-18 10:42:09','2024-08-18 10:42:09','SelectiveUser',NULL,NULL),
	(11,'2024-08-18 10:54:41','2024-08-18 10:54:41','Lucas1','M','Address1'),
	(12,'2024-08-18 10:54:41','2024-08-18 10:54:41','Lucas2','M','Address2'),
	(13,'2024-08-18 10:54:41','2024-08-18 10:54:41','SelectiveUser',NULL,NULL),
	(14,'2024-08-18 11:13:19','2024-08-18 11:13:19','Lucas1','M','Address1'),
	(15,'2024-08-18 11:13:19','2024-08-18 11:13:19','Lucas2','M','Address2'),
	(16,'2024-08-18 11:13:19','2024-08-18 11:13:19','SelectiveUser',NULL,NULL),
	(17,'2024-08-18 11:18:18','2024-08-18 11:18:18','Lucas1','M','Address1'),
	(18,'2024-08-18 11:18:18','2024-08-18 11:18:18','Lucas2','M','Address2'),
	(19,'2024-08-18 11:18:18','2024-08-18 11:18:18','SelectiveUser',NULL,NULL),
	(21,'2024-08-18 19:32:43','2024-08-18 19:32:43','Lucas1','M','Address1'),
	(22,'2024-08-18 19:32:43','2024-08-18 19:32:43','Lucas2','M','Address2'),
	(23,'2024-08-18 19:32:43','2024-08-18 19:32:43','SelectiveUser',NULL,NULL),
	(24,'2024-08-18 19:32:44','2024-08-18 19:32:44','TestUser','1','Test Address'),
	(25,'2024-08-18 23:16:45','2024-08-18 23:16:45','Lucas1','M','Address1'),
	(26,'2024-08-18 23:16:45','2024-08-18 23:16:45','Lucas2','M','Address2'),
	(27,'2024-08-18 23:16:45','2024-08-18 23:16:45','SelectiveUser',NULL,NULL),
	(28,'2024-08-18 23:16:45','2024-08-18 23:16:45','TestUser','1','Test Address');
UNLOCK TABLES;






SET FOREIGN_KEY_CHECKS = @ORIG_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS = @ORIG_UNIQUE_CHECKS;

SET @ORIG_TIME_ZONE = @@TIME_ZONE;
SET TIME_ZONE = @ORIG_TIME_ZONE;

SET SQL_MODE = @ORIG_SQL_MODE;



# Export Finished: November 30, 2025 at 23:17:39 GMT+8


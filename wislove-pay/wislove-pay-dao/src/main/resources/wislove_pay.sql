/*
Navicat MySQL Data Transfer

Source Server         : test.office.cdmn.com_3306
Source Server Version : 50722
Source Host           : test.office.cdmn.com:3306
Source Database       : medcare_pay

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2018-11-16 10:34:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for merchant_pay_account_info
-- ----------------------------
DROP TABLE IF EXISTS `merchant_pay_account_info`;
CREATE TABLE `merchant_pay_account_info` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `pay_way` varchar(255) DEFAULT '' COMMENT '支付方式 0:余额 * 1:支付宝 * 2:微信 * 3：银联 * 4：代付 * 5:线下',
  `appid` varchar(100) DEFAULT '' COMMENT 'appid',
  `cdmn_pay_private_key` varchar(5000) DEFAULT '' COMMENT '关心堂去第三方支付系统支付时，签名用的私钥',
  `third_pay_public_key` varchar(5000) DEFAULT '' COMMENT '第三方支付系统公钥',
  `mch_id` varchar(100) DEFAULT '' COMMENT 'mchId',
  `api_key` varchar(100) DEFAULT '' COMMENT 'api key',
  `seller_email` varchar(50) DEFAULT '' COMMENT '邮箱',
  `cdmn_app_id` varchar(64) DEFAULT NULL COMMENT '关心堂支付系统应用ID',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  `t_sharding` varchar(35) DEFAULT NULL COMMENT '分片字段（yyyy-MM-dd）',
  `app_secret` varchar(100) DEFAULT NULL COMMENT '微信公众号 开发者密码',
  `cert_url` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商户账户信息';

-- ----------------------------
-- Table structure for order_record
-- ----------------------------
DROP TABLE IF EXISTS `order_record`;
CREATE TABLE `order_record` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '下单用户的id',
  `pay_user_id` varchar(32) DEFAULT NULL COMMENT '支付用户的id',
  `account` varchar(50) DEFAULT NULL COMMENT '下单用户的账号',
  `pay_way` varchar(32) DEFAULT NULL COMMENT '支付方法（0:余额 * 1:支付宝 * 2:微信 * 3：银联 * 4：代付 * 5:线下)',
  `pay_type` int(2) DEFAULT NULL COMMENT '支付类型（1.支付 2.转账 3.退款 10.其他）',
  `appid` varchar(100) DEFAULT NULL COMMENT '第三方平台给的应用ID',
  `mch_id` varchar(100) DEFAULT NULL COMMENT '微信商户号，支付宝收款支付宝id',
  `subject` varchar(256) DEFAULT NULL COMMENT '微信商品描述，支付宝商品标题',
  `particular` varchar(200) DEFAULT NULL COMMENT '微信商品详情，支付宝商品描述',
  `out_trade_no` varchar(255) DEFAULT NULL COMMENT '商户订单号（微信，支付宝）',
  `total_fee` bigint(30) DEFAULT NULL COMMENT '总金额（微信，支付宝）单位：分',
  `spbill_create_ip` varchar(20) DEFAULT NULL COMMENT '微信用户终端ip',
  `trade_type` varchar(50) DEFAULT NULL COMMENT '微信交易类型',
  `time_start` varchar(50) DEFAULT NULL COMMENT '微信交易起始时间，支付宝发送请求时间(yyyyMMddHHmmss)',
  `time_expire` varchar(50) DEFAULT NULL COMMENT '交易结束时间（微信，支付宝）(yyyyMMddHHmmss)',
  `limit_pay` varchar(50) DEFAULT NULL COMMENT '微信指定能不能用信用卡 (1.能  2.不能)',
  `method` varchar(255) DEFAULT NULL COMMENT '支付宝调用的接口名称',
  `month` varchar(20) DEFAULT NULL COMMENT '月份',
  `pay_status` varchar(255) DEFAULT NULL COMMENT '支付状态 1:未支付； * 2：代付； * 3：已支付； * 4：超时； * 5: 发起退款 * 6：已退款 * 7：退款失败 * 8: 线下支付 * 9：完成 10:支付失败',
  `create_date` varchar(50) DEFAULT NULL COMMENT '创建日期',
  `hosp_id` varchar(255) DEFAULT NULL COMMENT '医院id',
  `hosp_name` varchar(150) DEFAULT NULL COMMENT '医院名称',
  `hosp_type` varchar(100) DEFAULT NULL COMMENT '医院类型',
  `order_type` varchar(50) DEFAULT NULL COMMENT '订单类型（type_app:预约就诊 * type_check:检查检验 * type_medical:处方药品 * type_question:咨询问诊）',
  `order_create_date` varchar(50) DEFAULT NULL COMMENT '订单创建日期',
  `order_status` varchar(30) DEFAULT NULL COMMENT '订单状态',
  `relevance_hosp_out_trade_no` varchar(100) DEFAULT NULL COMMENT '关联医院订单号',
  `see_doctor_user_id` varchar(100) DEFAULT NULL COMMENT '就诊用户的Id',
  `see_doctor_account` varchar(100) DEFAULT NULL COMMENT '就诊用户的账号',
  `service_status` varchar(100) DEFAULT NULL COMMENT '订单服务状态',
  `order_pay_type` varchar(50) DEFAULT NULL COMMENT '订单支付类型',
  `pay_user_account` varchar(60) DEFAULT NULL COMMENT '订单支付用户帐户',
  `service_doctor_account` varchar(100) DEFAULT '' COMMENT '服务医生账号',
  `pay_user_name` varchar(50) DEFAULT NULL COMMENT '订单支付用户姓名',
  `service_doctor_name` varchar(50) DEFAULT NULL COMMENT '服务医生姓名',
  `down_order_user_name` varchar(50) DEFAULT NULL COMMENT '下单用户姓名',
  `t_sharding` varchar(100) DEFAULT NULL COMMENT '分片字段（yyyy-MM-dd）',
  `see_doctor_user_name` varchar(50) DEFAULT NULL COMMENT '就诊用户的姓名',
  `t_pay_order_user_type` varchar(50) DEFAULT NULL COMMENT '订单支付用户类型',
  `t_pay_order_user_latter_name` varchar(50) DEFAULT NULL COMMENT '支付订单的用户姓名首字母',
  `t_pay_order_user_id_card` varchar(50) DEFAULT NULL COMMENT '支付订单的用户身份证号',
  `t_pay_order_user_sex` varchar(50) DEFAULT NULL COMMENT '支付订单的用户性别',
  `t_pay_order_user_birthday` varchar(50) DEFAULT NULL COMMENT '支付订单的用户生日',
  `transfer_status` varchar(50) DEFAULT NULL COMMENT '是否已经转款',
  `pay_fail_reason` varchar(255) DEFAULT NULL COMMENT '失败原因',
  `cdmn_app_id` varchar(64) DEFAULT '' COMMENT '关心堂支付系统应用ID',
  `pay_channel` varchar(255) DEFAULT NULL COMMENT '支付渠道(1：app支付，3.扫码支付，4.公众号支付,5.h5支付)',
  `return_url` varchar(255) DEFAULT NULL COMMENT '同步跳转URL',
  `other_params` varchar(1000) DEFAULT NULL COMMENT '其他参数',
  `timestamp` varchar(100) DEFAULT NULL COMMENT '请求发送时间，格式"yyyy-MM-dd HH:mm:ss"',
  `cdmn_notify_url` varchar(255) DEFAULT NULL COMMENT '关心堂商户异步通知地址',
  `t_server` varchar(30) DEFAULT NULL COMMENT '模块名称',
  `openid` varchar(100) DEFAULT NULL COMMENT 'openid',
  PRIMARY KEY (`id`),
  KEY `suoyin1` (`out_trade_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单记录';

-- ----------------------------
-- Table structure for pay_blotter
-- ----------------------------
DROP TABLE IF EXISTS `pay_blotter`;
CREATE TABLE `pay_blotter` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `user_id` varchar(32) DEFAULT NULL COMMENT '下单用户的id',
  `pay_user_id` varchar(32) DEFAULT NULL COMMENT '支付用户的id',
  `account` varchar(11) DEFAULT NULL COMMENT '下单用户的账号',
  `pay_way` varchar(32) DEFAULT NULL COMMENT '支付方法（0:余额 * 1:支付宝 * 2:微信 * 3：银联 * 4：代付 * 5:线下，目前只支持1,2,5)',
  `pay_type` int(2) DEFAULT NULL COMMENT '支付类型（1.支付 2.转账 3.退款 10.其他）',
  `appid` varchar(32) DEFAULT NULL COMMENT '支付宝，微信给开发者的id',
  `mch_id` varchar(32) DEFAULT NULL COMMENT '微信商户号,支付宝收款支付宝id',
  `is_subscribe` varchar(1) DEFAULT NULL COMMENT '微信是否关注公众账号,Y-关注，N-未关注',
  `trade_type` varchar(16) DEFAULT NULL COMMENT '微信交易类型（APP）',
  `bank_type` varchar(16) DEFAULT NULL COMMENT '微信付款银行',
  `total_fee` bigint(30) DEFAULT NULL COMMENT '微信总金额或支付宝订单金额(单位：分)',
  `fee_type` varchar(8) DEFAULT NULL COMMENT '微信货币种类',
  `cash_fee` bigint(30) DEFAULT NULL COMMENT '微信现金支付金额，支付宝实收金额(单位：分)',
  `cash_fee_type` varchar(16) DEFAULT NULL COMMENT '现金支付货币类型',
  `coupon_fee` bigint(30) DEFAULT NULL COMMENT '微信代金券金额，支付宝集分宝金额(单位：分)',
  `coupon_count` int(2) DEFAULT NULL COMMENT '微信代金券使用数量',
  `coupon_fee_$n` bigint(30) DEFAULT NULL COMMENT '单个代金券支付金额(单位：分)',
  `transaction_id` varchar(64) DEFAULT NULL COMMENT '微信支付订单号或支付宝交易号',
  `out_trade_no` varchar(64) DEFAULT NULL COMMENT '商户订单号(微信，支付宝)',
  `time_end` datetime DEFAULT NULL COMMENT '微信支付完成时间',
  `timestamp` bigint(20) DEFAULT NULL COMMENT '时间戳',
  `notify_id` varchar(128) DEFAULT NULL COMMENT '通知校验ID',
  `out_biz_no` varchar(64) DEFAULT NULL COMMENT '商户业务号',
  `buyer_id` varchar(128) DEFAULT NULL COMMENT '微信用户在商户appid下的唯一标识，支付宝买家用户号',
  `buyer_logon_id` varchar(100) DEFAULT NULL COMMENT '买家支付宝账号',
  `seller_email` varchar(100) DEFAULT NULL COMMENT '卖家支付宝用户号',
  `invoice_amount` bigint(30) DEFAULT NULL COMMENT '开票金额(单位：分)',
  `refund_fee` bigint(30) DEFAULT NULL COMMENT '总退款金额(单位：分)',
  `subject` varchar(256) DEFAULT NULL COMMENT '订单标题',
  `particular` varchar(2000) DEFAULT NULL COMMENT '微信商品详情，支付宝商品描述',
  `gmt_refund` date DEFAULT NULL COMMENT '交易退款时间',
  `gmt_close` date DEFAULT NULL COMMENT '交易结束时间',
  `fund_bill_list` varchar(512) DEFAULT NULL COMMENT '支付金额信息',
  `unionpay_type` varchar(32) DEFAULT NULL COMMENT '银联类型',
  `payer_type` int(1) DEFAULT NULL COMMENT '自己付或代付(1.自己付 2.代付)',
  `status` varchar(16) DEFAULT NULL COMMENT '微信业务结果，支付宝交易状态',
  `gmt_payment` datetime DEFAULT NULL COMMENT '支付宝交易付款时间',
  `month` varchar(20) DEFAULT NULL COMMENT '月份',
  `t_sharding` varchar(100) DEFAULT NULL COMMENT '分片字段（yyyy-MM-dd）',
  PRIMARY KEY (`id`),
  KEY `23rsdfsd` (`out_trade_no`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付流水';

-- ----------------------------
-- Table structure for pay_config
-- ----------------------------
DROP TABLE IF EXISTS `pay_config`;
CREATE TABLE `pay_config` (
  `id` varchar(32) NOT NULL,
  `hospital_id` varchar(255) DEFAULT '',
  `pay_way` varchar(255) DEFAULT '',
  `appid` varchar(100) DEFAULT '',
  `private_key` varchar(5000) DEFAULT '',
  `public_key` varchar(5000) DEFAULT '',
  `mch_id` varchar(100) DEFAULT '',
  `api_key` varchar(100) DEFAULT '',
  `seller_email` varchar(50) DEFAULT '',
  `hospital_type` varchar(50) DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pay_merchant
-- ----------------------------
DROP TABLE IF EXISTS `pay_merchant`;
CREATE TABLE `pay_merchant` (
  `id` varchar(32) NOT NULL COMMENT '主键',
  `merchant_name` varchar(255) DEFAULT NULL COMMENT '商户名称',
  `merchant_id` varchar(64) DEFAULT NULL COMMENT '商户ID',
  `account` varchar(255) DEFAULT NULL COMMENT '商户帐户',
  `password` varchar(255) DEFAULT NULL COMMENT '商户密码',
  `phone` varchar(255) DEFAULT NULL COMMENT '商户电话',
  `t_sharding` varchar(35) DEFAULT NULL COMMENT '分片字段（yyyy-MM-dd）',
  `create_date` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付商户';

-- ----------------------------
-- Table structure for pay_merchant_app
-- ----------------------------
DROP TABLE IF EXISTS `pay_merchant_app`;
CREATE TABLE `pay_merchant_app` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `merchant_id` varchar(64) DEFAULT NULL COMMENT '商户ID ',
  `app_name` varchar(255) DEFAULT NULL COMMENT '应用名称',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `cdmn_app_id` varchar(64) DEFAULT NULL COMMENT '关心堂支付系统应用ID',
  `pay_merchant_app_public_key` varchar(2000) DEFAULT NULL COMMENT '商户应用公钥',
  `status` varchar(255) DEFAULT NULL COMMENT '应用状态 0.待审核 1.审核通过 2.审核未通过 3.已删除',
  `notify_url` varchar(255) DEFAULT NULL COMMENT '商户异步通知地址',
  `t_server` varchar(30) DEFAULT NULL COMMENT '模块名称',
  `t_sharding` varchar(35) DEFAULT NULL COMMENT '分片字段（yyyy-MM-dd）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付应用';

-- ----------------------------
-- Table structure for pay_notify
-- ----------------------------
DROP TABLE IF EXISTS `pay_notify`;
CREATE TABLE `pay_notify` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `out_trade_no` varchar(64) DEFAULT NULL COMMENT '订单号',
  `cdmn_app_id` varchar(64) DEFAULT NULL COMMENT '关心堂应用ID',
  `notify_status` varchar(255) DEFAULT NULL COMMENT '通知状态 通知成功：success 通知失败：fail 通知中：notifying',
  `already_notify_count` int(255) DEFAULT NULL COMMENT '已经通知次数',
  `last_notify_date` datetime DEFAULT NULL COMMENT '上次通知时间',
  `notify_content` varchar(2000) DEFAULT NULL COMMENT '通知内容',
  `notify_url` varchar(255) DEFAULT NULL COMMENT '通知url',
  `t_server` varchar(30) DEFAULT NULL COMMENT '模块名称',
  `next_notify_date` datetime DEFAULT NULL COMMENT '下次通知时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='支付通知';

-- ----------------------------
-- Table structure for pay_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE `pay_order` (
  `id` varchar(50) NOT NULL COMMENT '订单id',
  `price` decimal(10,2) DEFAULT NULL,
  `pay_time` varchar(30) DEFAULT NULL COMMENT '支付时间',
  `order_state` varchar(10) DEFAULT NULL COMMENT '订单状态',
  `pay_way` tinyint(2) DEFAULT NULL COMMENT '支付方式',
  `cratre_time` varchar(30) DEFAULT NULL COMMENT '创建时间',
  `good_id` varchar(50) DEFAULT NULL COMMENT '商品名',
  `is_del` tinyint(2) DEFAULT NULL COMMENT '是否删除',
  `user_id` varchar(50) DEFAULT NULL COMMENT '用户id',
  `t_sharding` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for refund_order
-- ----------------------------
DROP TABLE IF EXISTS `refund_order`;
CREATE TABLE `refund_order` (
  `id` varchar(64) NOT NULL COMMENT '主键',
  `t_sharding` varchar(64) DEFAULT NULL COMMENT '分片字段',
  `t_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `t_month_sharding` varchar(60) DEFAULT NULL COMMENT '月份分片字段',
  `order_no` varchar(255) DEFAULT NULL COMMENT '商户订单号',
  `pay_way` varchar(32) DEFAULT NULL COMMENT '支付方式',
  `order_pay_total_fee` bigint(255) DEFAULT NULL COMMENT '订单支付总金额',
  `refund_fee` bigint(255) DEFAULT NULL COMMENT '退款金额',
  `refund_reason` varchar(50) DEFAULT NULL COMMENT '退款原因',
  `refund_no` varchar(64) DEFAULT NULL COMMENT '退款订单号',
  `refund_status` varchar(20) DEFAULT NULL COMMENT '退款状态(WAITING_REFUND 等待退款, ALREADY_REFUND 已经退款，REFUND_FAIL 退款失败)',
  `refund_status_msg` varchar(50) DEFAULT NULL COMMENT '退款状态消息',
  `refund_data` varchar(2500) DEFAULT NULL COMMENT '退款数据',
  `refund_date` datetime DEFAULT NULL COMMENT '退款日期',
  `refund_flow_no` varchar(64) DEFAULT NULL COMMENT '退款流水号',
  `cdmn_appid` varchar(100) DEFAULT NULL COMMENT '退款关心堂appID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='退款订单';

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `realname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for xt_log
-- ----------------------------
DROP TABLE IF EXISTS `xt_log`;
CREATE TABLE `xt_log` (
  `id` varchar(50) NOT NULL,
  `method_name` varchar(50) DEFAULT NULL COMMENT '方法名',
  `request_time` varchar(50) DEFAULT NULL COMMENT '请求时间',
  `response_time` varchar(50) DEFAULT NULL COMMENT '响应时间',
  `service_name` varchar(50) DEFAULT NULL COMMENT '类名',
  `app_name` varchar(50) DEFAULT NULL COMMENT '项目名',
  `ip` varchar(20) DEFAULT NULL COMMENT 'ip',
  `create_time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `is_del` tinyint(4) DEFAULT NULL COMMENT '是否删除',
  `t_sharding` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

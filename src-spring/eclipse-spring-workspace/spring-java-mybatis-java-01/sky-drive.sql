-- 暂未考虑id等容量超出限制的情况
-- 参考 阿里 Java 开发手册，在数据库中不设置实体外键
-- 每个表格包含至少 id, utc_create, utc_modified
-- 使用 utc+0
-- 主键使用 pk_ 前缀
-- MySQL 编码使用 UTF8MB4
-- 库名 大写 + 下划线
-- 表名，字段 小写 + 下划线
-- int 不指定长度
-- char, varchar 长度一般为 2^n-1

SET @@session.time_zone='+00:00';
DROP DATABASE IF EXISTS SKY_DRIVE;
CREATE DATABASE SKY_DRIVE;
USE SKY_DRIVE;


DROP TABLE IF EXISTS user_account;
CREATE TABLE user_account(
    pk_user_id INT PRIMARY KEY,
    user_password char(31),
    user_name varchar(31),
    user_room_used INT,
    user_room_total INT,
    utc_create DATETIME DEFAULT CURRENT_TIMESTAMP,
    utc_modified DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)DEFAULT CHARSET = "UTF8MB4";


DROP TABLE IF EXISTS file_local;
CREATE TABLE file_local(
    pk_file_md5 char(32) PRIMARY KEY,
    file_user_link INT ,
    file_local_store VARCHAR(255),
    file_size INT,
    utc_create DATETIME DEFAULT CURRENT_TIMESTAMP,
    utc_modified DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)DEFAULT CHARSET = "UTF8MB4";


DROP TABLE IF EXISTS dir_user;
CREATE TABLE dir_user(
    pk_dir_id CHAR(31) PRIMARY KEY,
    parent_id CHAR(31),
    dir_name varchar(31),
    user_id INT,
    utc_create DATETIME DEFAULT CURRENT_TIMESTAMP,
    utc_modified DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)DEFAULT CHARSET = "UTF8MB4";


DROP TABLE IF EXISTS file_user;
CREATE TABLE file_user(
    pk_file_id CHAR(31) PRIMARY KEY,
    file_md5 CHAR(32),
    parent_id CHAR(31),
    file_name varchar(31),
    share_password CHAR(31),
    user_id INT,
    utc_create DATETIME DEFAULT CURRENT_TIMESTAMP,
    utc_modified DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
)DEFAULT CHARSET = "UTF8MB4";


select pk_user_id from user_account
where pk_user_id = 1;

select pk_user_id, user_password from user_account
where pk_user_id=1 and user_password="123";
    

insert into user_account(pk_user_id,user_password,user_room_used,user_room_total)
value (1,"123",0,20*1024);

SELECT * FROM user_account;

SELECT * FROM file_local;

SELECT * FROM file_user;
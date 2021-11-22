# 规范

## 命名规范

1. 前端，网页 URL 使用: `-` 连接
2. 后端使用: 小驼峰命名法
3. 前后端交互 (一般为 thymeleaf 参数) 使用: `_` 连接 （页面视图仍使用 `-`）

## 文件结构

1. 每个页面视图对应一个 `Controller`

## 数据逻辑结构设计

- 暂未考虑id等容量超出限制的情况
- 参考 阿里 Java 开发手册，在数据库中不设置实体外键
- 每个表格包含至少 id, utc_create, utc_modified
- 主键使用 pk_ 前缀
- MySQL 编码使用 UTF8MB4
- 库名 大写 + 下划线
- 表名，字段 小写 + 下划线
- int, varchar 不指定长度

### DB1 用户账户数据库 (user_account)

|字段|含义|类型|长度|是否为空|
|--|--|--|--|--|
|pk_user_id|用户id|int||否|
|user_password|用户密码|char|31|否|
|user_name|用户昵称|varchar|31|否|

### DB2 文件路径映射数据库 (file_local)

|字段|含义|类型|长度|是否为空|
|--|--|--|--|--|
|pk_file_md5|文件MD5码|char|32|否|
|file_user_link|文件用户数|int||否|
|file_local_store|文件本地存储路径|varchar|31|否|

### DB4 用户目录存储映射表 (dir_user)

|字段|含义|类型|长度|是否为空|
|--|--|--|--|--|
|pk_dir_id|目录id|int||否|
|parent_id|父节点id|int||否|
|dir_name|目录名称|varchar|31|否|
|user_id|用户id|int||否|

### DB5 用户文件存储映射表 (file_user)

|字段|含义|类型|长度|是否为空|
|--|--|--|--|--|
|pk_file_id|文件id|int||否|
|file_md5|文件MD5码|char|32|否|
|parent_id|父节点id|int||否|
|file_name|文件名称|varchar|31|否|
|share_password|共享码|char|9|否|
|user_id|用户id|int||否|

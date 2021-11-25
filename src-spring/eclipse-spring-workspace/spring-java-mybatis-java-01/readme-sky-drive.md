# 规范

## 命名规范

1. 前端，网页 URL 使用: `-` 连接
2. 后端使用: 小驼峰命名法
3. 前后端交互 (一般为 thymeleaf 参数) 使用: `_` 连接 （页面视图仍使用 `-`）
4. 文件夹，文件名都不使用复数
5. 方法名以动词开头
6. 布尔类型方法明确指向（肯定/否定）
7. 前后端交互：名词+动词
8. 后端方法，类：动词+名词

## 文件结构

1. 每个页面视图对应一个 `Controller`
2. HttpSession 中存储 userId, parentId

## 知识点

1. 前后端 json 数据交互（jackson-databind）
2. 文件传输
3. 前端 md5
4. 后端 md5
5. 用户权限认证
6. 判断常用文件类型
7. getRealPath
8. 时间戳

## 功能检测

### 文件校验

1. 前后端 MD5 码传输
2. 前端 MD5 码生成
3. 后端 MD5 码生成

### 登录注册

### 页面访问

1. index 页面
2. login 页面
3. register 页面

## 数据逻辑结构设计

- 设计上的失误，pojo.File 和 java.io.File 类型重名
- 暂未考虑id等容量超出限制的情况
- 暂不支持递归删除目录
- 允许同名文件，同名目录
- 参考 阿里 Java 开发手册，在数据库中不设置实体外键
- 每个表格包含至少 id, utc_create, utc_modified
- 主键使用 pk_ 前缀
- MySQL 编码使用 UTF8MB4
- 库名 大写 + 下划线
- 表名，字段 小写 + 下划线
- int 不指定长度
- user id 考虑到要方便用户记忆，就在长度和类型上做了取舍
- file/dir id 使用 时间戳+user id 转十六进制的格式（所以长度要大于user id）
- file/dir id 不使用 md5 是因为同一个用户可能存两份相同的文件
- 不设置自增是不清楚如何获取刚插入的元组的id，中间的时间间隔是否会有新的数据插入
- UTF8 的 char 字符占位较多，而我存放的大多为十六进制，空间上还有很大的优化距离
- 每位用户初始化的根目录id为user id，因为所有用户的目录都在同一个表里，都是 0 或 1 的话，会混淆
- 但当前的id设置，父节点每个用户都是不同的，极端些，这样的话在dir_user中其实可以不用 user id
- 分享码简单些，也调成 31 位，和 file id 的生成思路一致

### DB1 用户账户数据库 (user_account)

|字段|含义|类型|长度|是否为空|
|--|--|--|--|--|
|pk_user_id|用户id|int||否|
|user_password|用户密码|char|31|否|
|user_name|用户昵称|varchar|31|否|
|user_room_used|用户使用的容量|INT|否|
|user_room_total|用户拥有的总容量|INT|否|
|utc_create|建表时间|DATETIME||否|
|utc_modified|最后修改时间|DATETIME||否|

### DB2 文件路径映射数据库 (file_local)

|字段|含义|类型|长度|是否为空|
|--|--|--|--|--|
|pk_file_md5|文件MD5码|char|32|否|
|file_user_link|文件用户数|int||否|
|file_local_store|文件本地存储路径|varchar|255|否|
|file_size|文件大小|int||否|
|utc_create|建表时间|DATETIME||否|
|utc_modified|最后修改时间|DATETIME||否|

### DB4 用户目录存储映射表 (dir_user)

|字段|含义|类型|长度|是否为空|
|--|--|--|--|--|
|pk_dir_id|目录id|char|31|否|
|parent_id|父节点id|char|31|否|
|dir_name|目录名称|varchar|31|否|
|user_id|用户id|int||否|
|utc_create|建表时间|DATETIME||否|
|utc_modified|最后修改时间|DATETIME||否|

### DB5 用户文件存储映射表 (file_user)

|字段|含义|类型|长度|是否为空|
|--|--|--|--|--|
|pk_file_id|文件id|char|31|否|
|file_md5|文件MD5码|char|32|否|
|parent_id|父节点id|char|31|否|
|file_name|文件名称|varchar|31|否|
|share_password|共享码|char|31|否|
|user_id|用户id|int||否|
|utc_create|建表时间|DATETIME||否|
|utc_modified|最后修改时间|DATETIME||否|

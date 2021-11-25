package com.cc01cc.spring.mapper;

import java.util.List;

import com.cc01cc.spring.pojo.Dir;
import com.cc01cc.spring.pojo.File;
import com.cc01cc.spring.pojo.User;

/**
 * @author cc01cc
 * @date 2021-11-21
 * @Description: TODO 需要优化为 DDD 架构，详情比较 Repository 和 Dao 的区别
 * 
 */
public interface BaseMapper {
    
    // 所有的 save, update, delete 操作之前都需要 find 确保没有重复/数据存在

    // @Select("select pk_user_id, user_password from user_account where
    // pk_user_id=#{userId} and user_password=#{userPassword}")
    public User findByIdAndPassword(User user);

    // @Insert("insert into busertable(pk_user_id,user_password) value
    // (#{userId},#{userPassword})")
    public int saveUser(User user);

    // @Select("select pk_user_id from user_account where user_id=#{userId}")
    // public User findById(User user);
    public User findById(String userId);
    // public String findById(String userId);
    
    
    // ============================================================== //
    // 用户文件目录存储
    // ============================================================== //

    // Mybatis 官方文档里没有找到 update, insert, delete 的返回值说明
    // 参考其他资料，似乎会返回 0 和 1 或者布尔类型
    
    public boolean saveFileLocal(File file);
    public boolean saveFileUser(File file);
    public boolean saveDirUser(Dir dir);



    /**
     * @Title: findFileByMD5
     * @Description: TODO
     * @param @param  md5
     * @param @return
     * @return File 不清楚 使用 int 是返回查询的条数还是值。暂用 File 代替
     * @throws
     */
    public User findUserById(String userId);
    public File findFileByMD5(String md5);
    public File findFileByFileId(String fileId);
    public Dir findDirByDirId(String dirId);
    public File findFileByFileSharePassword(String fileSharePassword);
    
    // 删除没有用户需要的文件时需要
    public List<File> findFileByFileUserLink(String fileUserLink);
    public List<File> findFileByParentId(String fileParentId);
    public List<Dir> findDirByParentId(String dirParentId);
    

    // 需要配合 findFileUserLink 使用，可以组成事务
    // 不能只能使用 fileUserLink 因为寻找元组也需要参数
    public boolean updateFileUserLink(File file);
    // public boolean updateFileLocalStore(File file);
    // 因为目录名和目录 id 分离，所以目录名称的更改不会影响子文件/目录的 ParentId
    // 所以 更改 ParentId 的场景一般为 用户移动子文件夹/目录的位置
    // TODO 文件位置的移动，保留功能（这涉及到前端浏览器内嵌页面）
    // public boolean updateDirParentId(Dir dir);
    // public boolean updateFileParentId(Dir dir);
    public boolean updateDirName(Dir dir);
    public boolean updateFileName(File file);
    public boolean updateFileSharePassword(File file);
    public boolean updateUserRoomUsed(User user);
    // TODO 更改用户总容量，保留功能
//    public boolean updateUserRoomTotal(User user);
    
    // 只有 fileUserLink 为 0 时才可根据 md5 删除
    public boolean deleteFileByFileMD5(String md5);
    public boolean deleteFileByFileId(String fileId);
    // 确保没有以此 dirId 为 parentId 的 元组才可以执行
    public boolean deleteDirByDirId(String DirId);
}

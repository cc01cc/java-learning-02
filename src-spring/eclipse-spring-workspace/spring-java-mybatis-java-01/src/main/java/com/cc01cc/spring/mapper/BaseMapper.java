package com.cc01cc.spring.mapper;

import com.cc01cc.spring.pojo.User;

/**
 * @author cc01cc
 * @date 2021-11-21 
 * @Description: TODO 需要优化为 DDD 架构，详情比较 Repository 和 Dao 的区别
 * 
 */
public interface BaseMapper {

    // @Select("select pk_user_id, user_password from user_account where
    // pk_user_id=#{userId} and user_password=#{userPassword}")
    public User findByIdAndPassword(User user);

    // @Insert("insert into busertable(pk_user_id,user_password) value
    // (#{userId},#{userPassword})")
    public int saveUser(User user);

    // @Select("select pk_user_id from user_account where user_id=#{userId}")
    public User findById(User user);
}

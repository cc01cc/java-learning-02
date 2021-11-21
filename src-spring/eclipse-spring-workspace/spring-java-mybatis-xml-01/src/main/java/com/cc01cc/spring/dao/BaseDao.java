//package com.cc01cc.spring.dao;
//
//import org.springframework.stereotype.Repository;
//
//import com.cc01cc.spring.pojo.User;
//
//@Repository
//public interface BaseDao {
//
//    // @Select("select pk_user_id, user_password from user_account where
//    // pk_user_id=#{userId} and user_password=#{userPassword}")
//    public User findByIdAndPassword(User user);
//
//    // @Insert("insert into busertable(pk_user_id,user_password) value
//    // (#{userId},#{userPassword})")
//    public int addUser(User user);
//
//    // @Select("select pk_user_id from user_account where user_id=#{userId}")
//    public User findById(User user);
//}

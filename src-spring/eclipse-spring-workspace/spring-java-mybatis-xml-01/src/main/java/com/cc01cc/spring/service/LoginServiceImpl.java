package com.cc01cc.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc01cc.spring.dao.BaseDao;
import com.cc01cc.spring.pojo.User;



@Service
public class LoginServiceImpl implements LoginService{
    
//    @Autowired
//    private BaseDao baseDao;
//    @Autowired
//    private User userGet = new User();
//    @Override
//    public boolean checkIdentity(User user){
//        userGet = baseDao.findByIdAndPassword(user);
//        if(userGet!=null) {
//            return true;
//        }
////        System.out.println(user.toString());
////        if(user.getUserId().equals("1")
////                &&user.getUserPassword().equals("123")) {
////            return true;
////        }
//        return false;
//    }
    
//    private final UserMapper userMapper;
//    
//    public LoginServiceImpl( UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }
//    
//  @Override
//  public boolean checkIdentity(User user){
//      userGet = userMapper.findByIdAndPassword(user);
//      if(userGet!=null) {
//          return true;
//      }
    
    
//    private final BaseDao baseDao;
//
//    public LoginServiceImpl(BaseDao baseDao) {
//        this.baseDao = baseDao;
//    }

    @Override
    public boolean checkIdentity(User user) {
        /*
         * userGet = baseDao.findByIdAndPassword(user); if (userGet != null) { return
         * true; }
         */
    
        System.out.println(user.toString());
        if (user.getUserId().equals("1")
                && user.getUserPassword().equals("123")) {
            return true;
        }
      return false;
  }
}

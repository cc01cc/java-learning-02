package com.cc01cc.spring.service;

import org.springframework.stereotype.Service;

import com.cc01cc.spring.pojo.User;


@Service
public class RegisterServiceImpl implements RegisterService{
    
    
//    @Autowired
//    BaseDao baseDao;
    
    
    @Override
    public boolean register(User user) {
        if(isNew(user)) {
//            int i = baseDao.addUser(user);
//            System.out.println(i);
            return true;
        }
        return false;
    }
    
    
    public boolean isNew(User user) {
//        if(baseDao.findById(user)==null) return true;
//        else return false;
        return true;
    }
    
}

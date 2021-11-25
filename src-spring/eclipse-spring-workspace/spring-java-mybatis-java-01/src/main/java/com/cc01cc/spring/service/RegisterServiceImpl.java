package com.cc01cc.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc01cc.spring.mapper.BaseMapper;
import com.cc01cc.spring.pojo.User;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    BaseMapper baseMapper;

    @Override
    public boolean register(User user) {
        if (isNew(user)) {
            user.setUserRoomTotal(10*1024);
            user.setUserRoomUsed(0);
            int i = baseMapper.saveUser(user);
            System.out.println(i);
            return true;
        }
        return false;
    }

    public boolean isNew(User user) {
        System.out.println("isNew : " + user);
        User userGet = baseMapper.findById(user.getUserId());
//        String userGet = baseMapper.findById(user.getUserId());
        System.out.println("userGet : " + userGet);
        if (userGet== null)
            return true;
        else
            return false;
    }

}

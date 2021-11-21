package com.cc01cc.spring.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.cc01cc.spring.pojo.User;

public interface LoginService {
    public boolean checkIdentity(User user);
}

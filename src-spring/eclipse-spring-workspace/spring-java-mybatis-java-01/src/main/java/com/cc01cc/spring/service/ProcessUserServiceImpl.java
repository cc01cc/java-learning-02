/**  
 *   Copyright 2021 cc01cc
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */


 /**
 *   @Title: ProcessUserServiceImpl.java
 *   @Description: TODO
 *   @author cc01cc
 *   @date 2021-11-25 
 */  

package com.cc01cc.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cc01cc.spring.mapper.BaseMapper;
import com.cc01cc.spring.pojo.User;

/**
 * @author cc01cc
 * @date 2021-11-25 
 * @Description: TODO
 * 
 */
@Service
public class ProcessUserServiceImpl implements ProcessUserService{

    /** 
     * <p>Title: checkUserRoomUsed</p>
     * <p>Description: </p>
     * @param userId
     * @param fileSizeFromFront
     * @return
     * @see com.cc01cc.spring.service.ProcessUserService#checkUserRoomUsed(java.lang.String, int)
     *
     */
    @Override
    public boolean checkUserRoomUsed(String userId, int fileSize) {
        User user = baseMapper.findUserById(userId);
        if(fileSize+user.getUserRoomUsed()>user.getUserRoomTotal()) {
            return false;
        }else {
            user.setUserRoomUsed(fileSize+ user.getUserRoomUsed());
        }
        return true;
    }

    /** 
     * <p>Title: findUserByUserId</p>
     * <p>Description: </p>
     * @param user
     * @return
     * @see com.cc01cc.spring.service.ProcessUserService#findUserByUserId(com.cc01cc.spring.pojo.User)
     *
     */
    @Autowired
    BaseMapper baseMapper;
    
    @Override
    public User findUserByUserId(String userId) {
        User userTodo = baseMapper.findUserById(userId);
        return userTodo;
    }

    /** 
     * <p>Title: updateUserRoomUsed</p>
     * <p>Description: </p>
     * @param userId
     * @param fileSizeFromFront
     * @return
     * @see com.cc01cc.spring.service.ProcessUserService#updateUserRoomUsed(java.lang.String, int)
     *
     */
    @Override
    public boolean updateUserRoomUsed(String userId, int fileSize) {
        User user = baseMapper.findUserById(userId);
        if(fileSize+user.getUserRoomUsed()>user.getUserRoomTotal()) {
            return false;
        }else {
            user.setUserRoomUsed(fileSize+ user.getUserRoomUsed());
            baseMapper.updateUserRoomUsed(user);
        }
        return true;
    }

}

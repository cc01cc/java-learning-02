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
 *   @Title: HomeServiceImpl.java
 *   @Description: TODO
 *   @author cc01cc
 *   @date 2021-12-22 
 */  

package com.cco1cc.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cco1cc.springboot.entity.UserInfo;
import com.cco1cc.springboot.repository.UserInfoRepository;

/**
 * @author cc01cc
 * @date 2021-12-22 
 * @Description: TODO
 * 
 */
@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    UserInfoRepository userInfoRepository;
    
    @Override
    public boolean addUserInfo(UserInfo userInfo) {
        try {
            userInfoRepository.save(userInfo);
        }catch(Exception e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }
}

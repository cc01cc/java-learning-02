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
*   @Title: LoginServiceImpl.java
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
 * @author       cc01cc
 * @date         2021-12-22
 * @Description: TODO
 * 
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserInfoRepository userInfoRepository;

    /**
     * <p>
     * Title: checkLoginInfo
     * </p>
     * <p>
     * Description:
     * </p>
     * 
     * @param  userInfo
     * @return
     * @see             com.cco1cc.springboot.service.LoginService#checkLoginInfo(com.cco1cc.springboot.entity.UserInfo)
     *
     */
    @Override
    public boolean checkLoginInfo(UserInfo userInfo) {
        System.out.println("UserInfo is:" + userInfo);
        UserInfo result = userInfoRepository.findByUserAccountAndUserPassword(
                userInfo.getUserAccount(), userInfo.getUserPassword());
        if (result != null) {
            return true;
        }
        return false;

    }

    public UserInfo findByUserId(UserInfo userInfo) {
        UserInfo result = userInfoRepository.findByPkUserInfoId(userInfo.getPkUserInfoId());
        return result;
    }
}

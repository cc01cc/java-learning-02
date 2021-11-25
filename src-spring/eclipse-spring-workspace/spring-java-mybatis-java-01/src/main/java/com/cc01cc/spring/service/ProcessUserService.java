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
 *   @Title: ProcessUserService.java
 *   @Description: TODO
 *   @author cc01cc
 *   @date 2021-11-25 
 */  

package com.cc01cc.spring.service;

import com.cc01cc.spring.pojo.User;

/**
 * @author cc01cc
 * @date 2021-11-25 
 * @Description: TODO
 * 
 */
public interface ProcessUserService {

    // 本来想，只是获取用户的空间信息，但这包含两个值，不能用单个 int 表示，而返回 user 那不如直接返回 整个类
//    public int[] getUserRoomInfo(User user);
    public User findUserByUserId(String userId);

    /**
     * @Title: updateUserRoomUsed
     * @Description: TODO
     * @param @param userId
     * @param @param fileSizeFromFront
     * @param @return
     * @return boolean
     * @throws
     */
    public boolean updateUserRoomUsed(String userId, int fileSizeFromFront);
    
    public boolean checkUserRoomUsed(String userId, int fileSizeFromFront);
}

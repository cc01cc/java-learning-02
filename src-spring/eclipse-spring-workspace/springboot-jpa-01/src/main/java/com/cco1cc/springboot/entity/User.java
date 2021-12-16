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
 *   @Title: User.java
 *   @Description: TODO
 *   @author cc01cc
 *   @date 2021-12-16 
 */  

package com.cco1cc.springboot.entity;

/**
 * @author cc01cc
 * @date 2021-12-16 
 * @Description: TODO
 * 
 */
public class User {
    String userAccount;
//    String userLocation;
    String userpassword;
    /**
     * @return the userAccount
     */
    public String getUserAccount() {
        return userAccount;
    }
    /**
     * @param userAccount the userAccount to set
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }
    /**
     * @return the userpassword
     */
    public String getUserpassword() {
        return userpassword;
    }
    /**
     * @param userpassword the userpassword to set
     */
    public void setUserpassword(String userpassword) {
        this.userpassword = userpassword;
    }
    /** 
     * <p>Title: toString</p>
     * <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     *
     */
    @Override
    public String toString() {
        return "User [userAccount=" + userAccount + ", userpassword=" + userpassword + "]";
    }
    
}

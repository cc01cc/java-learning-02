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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author       cc01cc
 * @date         2021-12-16
 * @Description: TODO
 * 
 */
@Entity
@Table(name = "T_USER_INFO")
public class UserInfo {

    @Id
    @Column(name = "PK_USER_INFO_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int pkUserInfoId;

    @Column(name = "USER_ACCOUNT")
    String userAccount;

    @Column(name = "USER_PASSWORD")
    String userPassword;

    // @Column(name="USER_HOBBY")
    // byte userHobby;

    /**
     * @return the pkUserInfoId
     */
    public int getPkUserInfoId() {
        return pkUserInfoId;
    }

    /**
     * @param pkUserInfoId the pkUserInfoId to set
     */
    public void setPkUserInfoId(int pkUserInfoId) {
        this.pkUserInfoId = pkUserInfoId;
    }

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
     * @return the userPassword
     */
    public String getUserPassword() {
        return userPassword;
    }

    /**
     * @param userPassword the userPassword to set
     */
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /**
     * <p>
     * Title: toString
     * </p>
     * <p>
     * Description:
     * </p>
     * 
     * @return
     * @see    java.lang.Object#toString()
     *
     */
    @Override
    public String toString() {
        return "UserInfo [pkUserInfoId=" + pkUserInfoId + ", userAccount=" + userAccount
                + ", userPassword=" + userPassword + "]";
    }

}

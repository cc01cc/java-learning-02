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
*   @Title: UserHobby.java
*   @Description: TODO
*   @author cc01cc
*   @date 2021-12-21 
*/

package com.cco1cc.springboot.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author       cc01cc
 * @date         2021-12-21
 * @Description: TODO
 * 
 */
@Entity
@Table(name = "T_USER_HOBBY")
public class UserHobby {
    private static final long serialVersionUID = 1l;

    @Id
    @Column(name = "PK_USER_HOBBY_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pkUserHobbyId;

    @Column(name = "USER_ACCOUNT")
    String userAccount;

    @Column(name = "USER_HOBBY")
    String userHobby;

    @ManyToOne(cascade = {
            CascadeType.MERGE, CascadeType.REFRESH
    }, optional = false)
    @JoinColumn(name = "userInfo_userAccount")
    private UserInfo userInfo;

    /**
     * @return the pkUserHobbyId
     */
    public int getPkUserHobbyId() {
        return pkUserHobbyId;
    }

    /**
     * @param pkUserHobbyId the pkUserHobbyId to set
     */
    public void setPkUserHobbyId(int pkUserHobbyId) {
        this.pkUserHobbyId = pkUserHobbyId;
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
     * @return the userHobby
     */
    public String getUserHobby() {
        return userHobby;
    }

    /**
     * @param userHobby the userHobby to set
     */
    public void setUserHobby(String userHobby) {
        this.userHobby = userHobby;
    }

    /**
     * @return the userInfo
     */
    public UserInfo getUserInfo() {
        return userInfo;
    }

    /**
     * @param userInfo the userInfo to set
     */
    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
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
        return "UserHobby [pkUserHobbyId=" + pkUserHobbyId + ", userAccount=" + userAccount
                + ", userHobby=" + userHobby + ", userInfo=" + userInfo + "]";
    }

}

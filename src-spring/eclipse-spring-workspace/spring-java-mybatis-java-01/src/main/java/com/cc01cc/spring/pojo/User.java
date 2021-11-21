package com.cc01cc.spring.pojo;

//@Alias("User")
public class User {
    private String userId;
    private String userPassword;
//    用户昵称暂不提供
//    private String userName;
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    @Override
    public String toString() {
        return "User [userId=" + userId + ", userPassword=" + userPassword + "]";
    }
    
}

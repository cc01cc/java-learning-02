package com.cc01cc.spring.pojo;

// 别名添加失败，暂用全限定词
// 为了避免别名混淆，自定义的别名使用 : "ZEO 前缀 + 类名" 格式
// TODO @Alias("ZEOUser")
public class User {
    private String userId;
    private String userPassword;

    // 用户昵称暂不提供
    // private String userName;
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

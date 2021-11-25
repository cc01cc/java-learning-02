package com.cc01cc.spring.pojo;

// 别名添加失败，暂用全限定词
// 为了避免别名混淆，自定义的别名使用 : "ZEO 前缀 + 类名" 格式
// TODO @Alias("ZEOUser")
public class User {
    private String userId;
    private String userPassword;
    private int userRoomUsed;
    private int userRoomTotal;

    /**
     * @return the userRoomUsed
     */
    public int getUserRoomUsed() {
        return userRoomUsed;
    }

    /**
     * @param userRoomUsed the userRoomUsed to set
     */
    public void setUserRoomUsed(int userRoomUsed) {
        this.userRoomUsed = userRoomUsed;
    }

    /**
     * @return the userRoomTotal
     */
    public int getUserRoomTotal() {
        return userRoomTotal;
    }

    /**
     * @param userRoomTotal the userRoomTotal to set
     */
    public void setUserRoomTotal(int userRoomTotal) {
        this.userRoomTotal = userRoomTotal;
    }

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

    /** 
     * <p>Title: toString</p>
     * <p>Description: </p>
     * @return
     * @see java.lang.Object#toString()
     *
     */
    @Override
    public String toString() {
        return "User [userId=" + userId + ", userPassword=" + userPassword + ", userRoomUsed="
                + userRoomUsed + ", userRoomTotal=" + userRoomTotal + "]";
    }

}

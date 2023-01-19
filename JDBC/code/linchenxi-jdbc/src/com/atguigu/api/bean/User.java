package com.atguigu.api.bean;

/**
 * @projectName: linchenxi-jdbc
 * @package: com.atguigu.api.bean
 * @className: User
 * @author: 林晨曦
 * @description: TODO
 * @date: 2023/1/14 16:17
 * @version: 1.0
 */

public class User {

    private long id;
    private String account;
    private String password;
    private String nickname;

    public User(long id, String account, String password, String nickname) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.nickname = nickname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}

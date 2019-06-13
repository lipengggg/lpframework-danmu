package com.lpframework.chat;

import java.io.Serializable;

/**
 *
 * @author lipeng
 * @version Id: User.java, v 0.1 2019/6/13 18:40 lipeng Exp $$
 */
public class User implements Serializable{
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

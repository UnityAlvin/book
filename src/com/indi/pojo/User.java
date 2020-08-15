package com.indi.pojo;

public class User {
    private Integer id;
    private String username;
    private String password;
    private String email;
    //0普通用户，1管理员
    private Integer type = 0;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", type=" + type +
                '}';
    }

    public User() {
    }

    public User(Integer id, String username, String password, String email, Integer type) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

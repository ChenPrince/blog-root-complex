package com.zerodg.vwentity.entity;

/**
 * @program: qfsx_root
 * @description: 测试
 * @author: ztz-prince
 * @create:
 * @date:2019-06-25-10:46
 **/
public class TestUser {

    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    private String name;

    private String password;

    private Integer age;

}

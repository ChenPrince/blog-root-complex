package com.zerodg.zdutil.util;

import io.swagger.annotations.ApiModelProperty;

/**
 * @program: demo
 * @description: requestdto
 * @author: ztz-prince
 * @create:
 * @date:2018-12-19-01:25
 **/
public class RequestDTO {
    @ApiModelProperty(value = "Swagger测试用token", name = "token", dataType = "String")
    private String token;

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}


package com.zerodg.vwentity.dto.Test;

import com.zerodg.zdutil.util.RequestDTO;
import io.swagger.annotations.ApiModelProperty;

/**
 * @program: zd_root
 * @description:
 * @author: ztz-prince
 * @create:
 * @date:2019-06-12-11:45
 **/

public class Test_UserInputDTO  extends RequestDTO {

    /**
     * 用户名
     * @return name 用户名
     */
    @ApiModelProperty(value = "用户名", name = "name", dataType = "String")
    private String name;
    /**
     * 用户密码
     * @return name 用户密码
     */
    @ApiModelProperty(value = "用户密码", name = "password", dataType = "String")
    private String password;

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
}

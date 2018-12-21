package com.zerodg.zdutil.util;

import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * @program: demo
 * @description: test
 * @author: ztz-prince
 * @create:
 * @date:2018-12-19-01:26
 **/
public class TestDTO extends RequestDTO {
    /**
     * ID
     */
    @NotNull(message = "ID不能为空")
    private Integer id;


    /**
     * 获取 ID
     * @return id ID
     */
    @ApiModelProperty(value = "ID", name = "id", dataType = "Integer")
    public Integer getId() {
        return id;
    }

    /**
     * 设置 ID
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }
}

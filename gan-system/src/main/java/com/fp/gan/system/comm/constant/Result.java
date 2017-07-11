package com.fp.gan.system.comm.constant;


import com.fp.gan.system.comm.base.BaseResult;

/**
 * 系统常量枚举类
 */
public class Result extends BaseResult {

    public Result(ResultConstant upmsResultConstant, Object data) {
        super(upmsResultConstant.getCode(), upmsResultConstant.getMessage(), data);
    }

}

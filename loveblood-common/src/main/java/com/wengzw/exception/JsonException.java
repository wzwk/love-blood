package com.wengzw.exception;

import com.wengzw.entity.ResponseResult;
import com.wengzw.enums.RespStatusEnum;
import com.wengzw.error.ErrorInfo;
import lombok.Getter;

/**
 * @author wengzw
 * @date 2022/7/29 15:39
 */
public class JsonException extends RuntimeException {
    private static final long serialVersionUID = -6859013370470905290L;

    @Getter
    private final ResponseResult res;

    public JsonException(String message) {
        super(message);
        res = ResponseResult.fail(RespStatusEnum.FAIL,message);
    }

    public JsonException(ResponseResult jsonResult) {
        this.res = jsonResult;
    }

    public JsonException(Integer code, String msg) {
        res = new ResponseResult(RespStatusEnum.FAIL,code,msg);
    }

    public JsonException() {
        res = new ResponseResult(RespStatusEnum.FAIL, 500, "服务器其他错误");
    }

    public JsonException(ErrorInfo error) {
        res = new ResponseResult(RespStatusEnum.FAIL, error.getCode(), error.getMessage());
    }

    @Override
    public String getMessage() {
        return res.getMsg();
    }
}


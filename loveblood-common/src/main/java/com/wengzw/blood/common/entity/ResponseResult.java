package com.wengzw.blood.common.entity;

/**
 * @author wengzw
 * @date 2022/7/29 15:40
 */


import com.wengzw.blood.common.enums.RespStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 统一的接口返回格式
 *
 * @param <T> 定义泛型，扩展性高
 *            定义为final调用时候直接调用，安全和效率原因
 *            标准化：有些类执行标准函数，并不打算修改它们
 */
@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public final class ResponseResult<T> {
    /**
     * 响应状态 成功 失败
     */
    private String status;
    /**
     * 响应编码 200 -1
     */
    private Integer code;

    /**
     * 响应信息 方便操作者手动添加信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T data;

    /**
     * @param status 只返回成功失败
     */
    public ResponseResult(RespStatusEnum status) {
        this(status, null);
    }

    /**
     * @param status 返回成功失败
     * @param data   数据
     */
    public ResponseResult(RespStatusEnum status, T data) {
        this(status, status.getCode(), data);
    }

    /**
     * 赋值 有参构造函数
     * @param status
     * @param code
     * @param data
     */
    public ResponseResult(RespStatusEnum status, Integer code, T data) {
        this.status = status.getStatus();
        this.code = code;
        this.data = data;
    }

    public ResponseResult(RespStatusEnum status, String mag, T data) {
        this.status = status.getStatus();
        this.code = status.getCode();
        this.msg = mag;
        this.data = data;
    }

    /**
     * @return 默认成功响应
     */
    public static ResponseResult<Void> success() {
        return new ResponseResult<>(RespStatusEnum.SUCCESS);
    }

    /**
     * 自定义成功的响应信息
     * <p>通常用作插入成功等并显示具体操作通知如: return BasicResultVO.success("发送信息成功")</p>
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(String msg) {
        return new ResponseResult<>(RespStatusEnum.SUCCESS, msg, null);
    }

    /**
     * 带有数据的成功响应信息
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(RespStatusEnum.SUCCESS, data);
    }

    /**
     * @return 默认失败响应
     */
    public static <T> ResponseResult<T> fail() {
        return new ResponseResult<>(
                RespStatusEnum.FAIL,
                RespStatusEnum.FAIL.getCode(),
                null
        );
    }

    /**
     * 自定义错误信息的失败响应
     *
     * @param msg 错误信息
     * @return 自定义错误信息的失败响应
     */
    public static <T> ResponseResult<T> fail(String msg) {
        return fail(RespStatusEnum.FAIL, msg);
    }

    /**
     * 自定义错误信息的失败响应及状态
     * @param status
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> fail(RespStatusEnum status, String msg) {
        return new ResponseResult<>(status, msg, null);
    }

}


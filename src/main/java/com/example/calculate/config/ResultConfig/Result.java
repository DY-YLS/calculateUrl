package com.example.calculate.config.ResultConfig;

import lombok.Data;

/**
 * http请求最外层对象,统一返回接口
 *
 * @param <T>
 */
@Data
public class Result<T> {
    //返回码
    private Integer code;
    //标识成功或者失败
    private Boolean success;
    //提示信息
    private String message;
    //返回具体内容
    private T data;

    /**
     * 返回正确结果，且带有返回内容
     *
     * @param object
     * @return
     */
    public static Result success(Object object) {
        return success(ResultEnum.SUCCESS.getMsg(), object);
    }

    /**
     * 返回正确结果，自定义msg和数据
     *
     * @return
     */
    public static Result success(String msg, Object object) {
        Result result = new Result();
        result.setSuccess(true);
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMessage(msg);
        result.setData(object);
        return result;
    }

    /**
     * 返回正确结果，没有返回内容
     *
     * @return
     */
    public static Result success() {
        return success(null);
    }

    /**
     * 返回错误结果
     *
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code, String msg) {
        Result result = new Result();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(msg);
        return result;
    }

    public static Result error(String msg) {
        return error(-1, msg);
    }

    public static Result error(ResultEnum resultEnum) {
        return error(resultEnum.getCode(), resultEnum.getMsg());
    }

}
